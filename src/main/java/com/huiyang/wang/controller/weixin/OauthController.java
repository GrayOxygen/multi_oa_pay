package com.huiyang.wang.controller.weixin;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huiyang.wang.common.util.OauthUrlUtils;
import com.huiyang.wang.mapper.OfficialAccountMapper;
import com.huiyang.wang.model.OfficialAccount;
import com.saysth.weixin.sdk.UserApi;
import com.saysth.weixin.sdk.UserApi.OauthScope;
import com.saysth.weixin.sdk.entity.OfficialAccountBean;
import com.saysth.weixin.sdk.exception.WeixinException;
import com.saysth.weixin.sdk.response.UserResult;
import com.saysth.weixin.sdk.token.OauthAccessToken;
import com.saysth.weixin.sdk.util.WeixinService;
import com.saysth.weixin.sdk.web.OauthControllerSupport;

/**
 * WEB授权认证处理
 * 
 */
@Controller
@RequestMapping("/wx")
public class OauthController extends OauthControllerSupport {
	private static final Logger log = LoggerFactory.getLogger(OauthController.class);

	@Autowired
	private OfficialAccountMapper oAccMgr;
	@Autowired
	private WeixinService weixinService;

	/**
	 * 获取过portal的链接
	 * 
	 * @param appid
	 * @param url
	 * @param res
	 * @return
	 */
	@RequestMapping("/ajaxGetOauthUrl")
	public void ajaxGetOauthUrl(String appid, String url, HttpServletResponse res) {
		String oauthUrl = OauthUrlUtils.getOauthUrl(appid, url, OauthScope.BASE);
		response(res, oauthUrl);
	}

	/**
	 * <pre>
	 * 将oauth的redirUrl设置到这里，最终将跳转至state中指定的目标链接，如果链接中有{openid}，将被替换成当前访问用户的openid。
	 * 结合onAuthSuccess，可进行获取信息后的回调处理
	 * </pre>
	 * 
	 * 网页授权入口
	 * 
	 * @param appid
	 *            公众号信息
	 * @param state
	 *            目标链接
	 * @param code
	 *            微信回调code
	 * @param req
	 * @return
	 */
	@RequestMapping("/p")
	public String portal(String appid, String code, String state, HttpServletRequest req) {
		printRequestBrief(req);
		try {
			String url = oauthPortal(appid, code, state, req);
			return "redirect:" + url;
		} catch (WeixinException e) {
			log.error("ERROR", e);
			return ERR_500;
		}
	}

	/**
	 * 已关注去目标页，未关注则去关注页
	 * 
	 * @param appid
	 *            公众号信息
	 * @param state
	 *            目标链接
	 * @param code
	 *            微信回调code
	 * @param req
	 * @return
	 * @throws WeixinException
	 */
	@RequestMapping("/targetOrSub")
	public String targetOrSub(String appid, String code, String state, HttpServletRequest req) {
		printRequestBrief(req);
		try {
			String url = oauthTargetOrSub(appid, code, state, req);
			return "redirect:" + url;
		} catch (WeixinException e) {
			log.error("ERROR", e);
			return ERR_500;
		}
	}

	/**
	 * 将第三方openid和我们公众号的openid对应，发送绑定票据
	 * 
	 * @param appid
	 *            公众号信息
	 * @param code
	 *            微信回调的用于换取oauthToken的code
	 * @param state
	 *            对方提供的获取openid的页面URL
	 * @param req
	 * @return
	 * @throws WeixinException
	 */
	@RequestMapping("sendBind")
	public String sendBind(String appid, String code, String state, HttpServletRequest req) throws WeixinException {
		printRequestBrief(req);

		OfficialAccount oa = oAccMgr.getByAppId(appid);
		OauthAccessToken token = UserApi.getOauthAccessToken(appid, oa.getSecret(), code);
		String openid = token.getOpenid();

		UserResult user;
		if (StringUtils.equals(token.getScope(), OauthScope.USER_INFO.getLabel())) {
			user = UserApi.getUserInfoByOauth(token.getAccessToken(), openid);
		} else {
			user = new UserResult();
			user.setOpenid(openid);
		}
		// user.setDevCode(oa.getDevCode());
		if (log.isDebugEnabled()) {
			log.debug("UserResult: " + user.toString());
		}
		onOauthSuccess(token, user, req);

		String url = state;
		String ticket = getTicket(openid);

		url = StringUtils.replace(url, "{ticket}", ticket);
		if (log.isDebugEnabled()) {
			log.debug(user.getOpenid() + " accessing: " + state);
		}

		try {
			return "redirect:" + oauthPortal(appid, code, url, req);
		} catch (WeixinException e) {
			log.error("ERROR", e);
			return ERR_500;
		}
	}

	/**
	 * 微信公众号推送用户消息至该入口
	 * 
	 * @author 王辉阳
	 * @date 2016年2月19日 上午11:08:58
	 * @param oaid
	 * @return
	 */
	@RequestMapping("gate")
	public void gate(String oaid, String signature, String timestamp, String nonce, String echostr, HttpServletRequest req, HttpServletResponse res) {
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
		// 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		responseJson(res, echostr);
		System.out.println(oaid);
	}

	@Override
	protected String getAccessToken(String appid, String secret) {
		return weixinService.getAccessToken(appid, secret);
	}

	@Override
	protected OfficialAccountBean getOfficialAccount(String appid) {
		OfficialAccount oa = oAccMgr.getByAppId(appid);
		// OfficialAccountBean bean = new OfficialAccountBean();
		// BeanUtils.copyProperties(oa, bean);
		// bean.setAppid(oa.getAppId());
		// return bean;
		return oa;
	}

	@Override
	protected void onOauthSuccess(OauthAccessToken token, UserResult user, HttpServletRequest req) {
		if (log.isDebugEnabled()) {
			log.debug("onOauthSuccess");
		}
		// Fan fan = oAccMgr.getFan(user.getOpenid());
		// if (fan == null) {
		// 创建客户端用户
		// fan = new Fan(user);
		// fan.setSub(false);
		// oAccMgr.insertFan(fan);
		// log.info("save fan, fan id {}", fan.getId());
		// } else if (fan.getId() != null) {
		// TODO:base方式，待测试，会不会覆盖老的
		// Fan newFan = new Fan(user);
		// newFan.setSub(fan.getSub());
		// newFan.setCtime(fan.getCtime());
		// com.saysth.commons.utils.reflection.BeanUtils.copyNotNullProperties(newFan,
		// fan);
		// oAccMgr.save(fan);
		// log.info("update fan,OpenId: {}", fan.getId());
		// }

		HttpSession session = req.getSession();
		session.setAttribute("wxUser", user);

		handleSession(user.getOpenid(), req);
	}

	/**
	 * 把wxid相关信息存入session
	 * 
	 * @param wxid
	 */
	public void handleSession(String wxid, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("WXID", wxid);
		log.debug("handleSession, session:{} save WXID:{}", session.getId(), wxid);
	}

	/**
	 * 生成用于交换openid的ticket
	 * 
	 * @param openid
	 * @return
	 */
	private String getTicket(String openid) {
		// String ticket = StrUtils.get64UUID();
		// RedisCache cache = (RedisCache) cacheMgr.getCache("BIND_TICKET.");
		// cache.put(ticket, openid, Constants.TIME.MIN);
		// return ticket;
		return "";
	}

	/**
	 * 根据ticket获取openid
	 * 
	 * @param ticket
	 * @return
	 */
	private String getOpenidByTicket(String ticket) {
		// RedisCache cache = (RedisCache) cacheMgr.getCache("BIND_TICKET.");
		// ValueWrapper vw = cache.get(ticket);
		// if (vw != null) {
		// return vw.get().toString();
		// }
		// return null;
		return "";
	}

}
