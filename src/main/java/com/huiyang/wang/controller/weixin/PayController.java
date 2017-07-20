package com.huiyang.wang.controller.weixin;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiyang.wang.common.entity.enums.OAType;
import com.huiyang.wang.common.entity.enums.PayOrderStatus;
import com.huiyang.wang.common.util.JaxbParser;
import com.huiyang.wang.common.util.StringUtil;
import com.huiyang.wang.model.OfficialAccount;
import com.huiyang.wang.model.PayOrder;
import com.huiyang.wang.service.OfficialAccountManager;
import com.huiyang.wang.service.PayService;
import com.saysth.commons.utils.StringSubRepeatUtil;
import com.saysth.commons.utils.json.JsonUtils;
import com.saysth.commons.web.support.BaseController;
import com.saysth.weixin.sdk.pay.entity.PayResponse;
import com.saysth.weixin.sdk.pay.entity.PayResult;
import com.saysth.weixin.sdk.pay.utils.SignUtils;
import com.saysth.weixin.sdk.response.UserResult;
import com.saysth.weixin.sdk.util.SignUtil;

@Controller
@RequestMapping("/wx")
public class PayController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(PayController.class);

	@Autowired
	private OfficialAccountManager oaMgr;
	@Autowired
	private PayService payService;

	public static String getUrl(HttpServletRequest req) {
		StringBuffer requestURL = req.getRequestURL();
		String queryString = req.getQueryString();
		if (queryString != null) {
			requestURL.append("?").append(queryString);
		}
		return requestURL.toString();
	}

	/**
	 * 
	 * 微信支付结果通知回调接口
	 * 
	 * @author 王辉阳
	 * @date 2016年3月1日 上午10:50:40
	 * @param req
	 */
	@RequestMapping("/warn")
	public void warn(HttpServletRequest req) {
		Enumeration names = req.getAttributeNames();
		log.warn("=========Request Attribute=========");
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			log.warn(name + ": " + req.getAttribute(name));
		}
		log.warn("=========Request Attribute=========");

		log.warn("告警接口：  请求连接所带查询字符串", req.getQueryString());
	}

	public String getOpenId(HttpServletRequest req) {
		return (String) req.getSession().getAttribute("WXID");
	}

	public String geUnionId(HttpServletRequest req) {
		return ((UserResult) req.getSession().getAttribute("wxUser")).getUnionid();
	}

	/**
	 * 给客户临时做的一个公众号内不同的缴费功能转账至不同的银行账号
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/multiPay")
	public String multiPay(HttpServletRequest req, String source) throws Exception {
		System.out.println(source);
		// OfficialAccount oa = oaMgr.getOfficialAccount("0");
		OfficialAccount mchOA = oaMgr.getOfficialAccountByAppid("wx737ad133237892d7");

		Map<String, String> wx = oaMgr.getWX(mchOA, getUrl(req));

		// 获取链接相关的公众号信息，生成JSAPI所需参数返回给页面
		req.setAttribute("wx", wx);
		req.setAttribute("oa", mchOA);
		req.setAttribute("source", source);

		return "/h5/multiPayInSingleOA";
	}

	@RequestMapping("/multiPayOrder.json")
	@ResponseBody
	public Map<String, String> multiPayOrder(Integer amount, String source, HttpServletRequest req) throws Exception {
		String openId = getOpenId(req);
		String unionId = geUnionId(req);
		OfficialAccount childMchOA = null;
		OfficialAccount mchOA = oaMgr.getOfficialAccountByAppid("wx737ad133237892d7");
		if (OAType.XINJI.getName().equalsIgnoreCase(source)) {
			childMchOA = oaMgr.getOfficialAccountByAppid("wxf05189f68ef5d4c7");
		}
		if (OAType.BOHUI.getName().equalsIgnoreCase(source)) {
			childMchOA = oaMgr.getOfficialAccountByAppid("wx10a590dea6c4eae5");
		}
		if (OAType.XINHUAN.getName().equalsIgnoreCase(source)) {
			childMchOA = oaMgr.getOfficialAccountByAppid("wx40d365f3b7ba0644");
		}
		if (OAType.YINGHUANG.getName().equalsIgnoreCase(source)) {
			childMchOA = oaMgr.getOfficialAccountByAppid("wxcd907509d4344b06");
		}

		String notifyUrl = StringUtils.substringBeforeLast(req.getRequestURL().toString(), "/") + "/multiNotify.do";
		PayOrder order = payService.multiPay(openId, unionId, mchOA, childMchOA, amount, req.getRemoteAddr(), notifyUrl);

		log.info("Order committed: {}", JsonUtils.toJson(order));

		String pkg = "prepay_id=" + order.getPrepayId();
		Map<String, String> wx = SignUtil.signPay(mchOA.getAppId(), pkg, mchOA.getKey());
		wx.put("payId", order.getId());
		return wx;
	}

	@RequestMapping("/successPayStatus")
	@ResponseBody
	public void successPayStatus(@RequestBody String payId, HttpServletResponse res) {
		if (StringUtils.isBlank(payId)) {
			log.info("payId is null  更新支付状态失败");
			return;
		}
		String id=payId.split("=")[1];
		Map<String, Object> map = new HashMap<>();
		map.put("id_in", StringUtil.strToList(id));
		if (payService.count(map) == 1) {
			PayOrder order = new PayOrder();
			order.setId(id);
			order.setStatus(PayOrderStatus.SUCCESS);
			payService.update(order);
			log.info("修改状态成功");
		}
		log.info(payId+"修改状态完成");
	}

	@RequestMapping("/multiNotify")
	public void multiNotify(@RequestBody String payload, HttpServletResponse res) {
		if (StringUtils.isBlank(payload)) {
			log.info("payload is null");
			PayResponse response = new PayResponse();
			response.setReturnCode(PayResponse.FAIL);
			response.setReturnMsg("参数格式校验错误");
			response(res, JaxbParser.build().toXML(response));
			return;
		}
		log.debug("payload:{}", payload);
		PayResult result = JaxbParser.build().toObj(PayResult.class, payload);
		if (!result.isSuccess()) {
			log.info("错误的结果暂不处理");
		}

		OfficialAccount oa = oaMgr.getOfficialAccountByAppid(result.getAppid());
		Map<String, String> map;
		try {
			map = BeanUtils.describe(result);
			map.remove("class");
			map.remove("sign");
			map.remove("success");
			String sign = SignUtils.generateSign(map, oa.getKey());
			PayResponse response = new PayResponse();
			if (!sign.equals(result.getSign())) {
				response.setReturnCode(PayResponse.FAIL);
				response.setReturnMsg("参数格式校验错误");
				log.info("微信支付回调，签名错误,可能是黑客");
			} else {
				// payService.payNotify(result);
				response.setReturnCode(PayResponse.SUCCESS);
				response.setReturnMsg(PayResponse.SUCCESS);
			}

			response(res, JaxbParser.build().toXML(response));
		} catch (Exception e) {
			log.error("", e);
			PayResponse response = new PayResponse();
			response.setReturnCode(PayResponse.FAIL);
			response.setReturnMsg("参数格式校验错误");
			response(res, JaxbParser.build().toXML(response));
		}

	}
}
