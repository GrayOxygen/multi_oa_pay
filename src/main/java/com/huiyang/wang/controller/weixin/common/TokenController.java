package com.huiyang.wang.controller.weixin.common;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saysth.commons.http.HttpHelper;
import com.saysth.commons.utils.json.JsonUtils;
import com.saysth.weixin.sdk.token.AccessToken;
import com.saysth.weixin.sdk.token.JsApiTicket;

/**
 * 
 * @author 王辉阳
 * @date 2017年7月4日 下午7:25:07
 * @Description 中控服务器(redis缓存有时效性的数据)：负责微信有失效性相关的参数维护
 */
@Controller
@RequestMapping("/centralControl")
public class TokenController {
	private static final Logger log = LoggerFactory.getLogger(TokenController.class);
	@Autowired
	private RedisTemplate<String, String> stringRedisTemplate;
	// hash映射
	HashOperations<String, byte[], byte[]> hashOperations;
	HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

	/**
	 * 获取access token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	@SuppressWarnings("finally")
	@ResponseBody
	@RequestMapping("/accessToken/get.do")
	public String accessToken(String appid, String secret) {
		return getAccessToken(appid, secret);
	}

	/**
	 * 获取access token
	 * 
	 * @param appid
	 * @param secret
	 * @return
	 */
	private String getAccessToken(String appid, String secret) {
		if (StringUtils.isAnyBlank(appid, secret)) {
			return null;
		}
		// 1. 检查redis缓存
		if (stringRedisTemplate.boundValueOps(appid + "_AccessToken").getExpire() > 0) {
			String accessToken = stringRedisTemplate.boundValueOps(appid + "_AccessToken").get();
			if (accessToken != null) {
				log.debug("get access token from redis  :   token[ " + accessToken + " ] ，  appid  [ " + appid + " ]");
				return accessToken;
			}
		} else {

			// 2. 调用接口获取access token
			String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
			String html = null;
			AccessToken result = null;
			try {
				html = HttpHelper.connect(url).get().html();

				result = JsonUtils.parse(html, AccessToken.class);
				stringRedisTemplate.boundValueOps(appid + "_AccessToken").append(result.getAccessToken());
				stringRedisTemplate.boundValueOps(appid + "_AccessToken").expire(result.getExpiresIn(), TimeUnit.SECONDS);

			} catch (Exception e) {
				log.error("中控服务器异常", e);
			} finally {
				if (result != null) {
					return result.getAccessToken();
				}
			}
		}
		return null;
	}

	/**
	 * 获取oauth token参数
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/oauthToken/get.do")
	public String oauthToken(String appid, String secret, String code) {
		return null;
//
//		hashOperations = stringRedisTemplate.opsForHash();
//		if (StringUtils.isAnyBlank(appid, secret, code)) {
//			return null;
//		}
//		// 1. 检查redis缓存
//		log.info("oauth token 有效期 " + hashOperations.getOperations().getExpire(appid + "_OauthToken"));
//		if (hashOperations.getOperations().getExpire(appid + "_OauthToken") > 0) {
//			Map<byte[], byte[]> loadedHash = hashOperations.entries(appid + "_OauthToken");
//			OauthAccessToken oauthToken = (OauthAccessToken) mapper.fromHash(loadedHash);
//			log.info("没有失效：" + oauthToken);
//			if (oauthToken != null) {
//				log.debug("获取oauth token from redis  :   token[ " + oauthToken.getAccessToken() + " ] ，  appid  [ " + appid + " ]");
//				log.info("======from redis=========\n" + JsonUtils.toJson(oauthToken));
//				return JsonUtils.toJson(oauthToken);
//			}
//		}
//
//		// 2. 调用接口获取oauth token
//		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
//		String html = null;
//		OauthAccessToken result = null;
//		try {
//			html = HttpHelper.connect(url).timeout(30000).get().html();
//			result = JsonUtils.parse(html, OauthAccessToken.class);
//			if (!StringUtils.isEmpty(result.getErrmsg())) {
//				return null;
//			}
//
//			Map<byte[], byte[]> mappedHash = mapper.toHash(result);
//			hashOperations.putAll(appid + "_OauthToken", mappedHash);
//			hashOperations.getOperations().expire(appid + "_OauthToken", result.getExpiresIn(), TimeUnit.SECONDS);
//			log.debug("存放oauth token至redis缓存中： " + result.getAccessToken());
//
//			// 3. 获取union id
//			String unionIdURL = "https://api.weixin.qq.com/sns/userinfo?access_token=" + result.getAccessToken() + "&openid=" + result.getOpenid() + "&lang=zh_CN ";
//
//			String unionid_html = HttpHelper.connect(unionIdURL).timeout(30000).get().html();
//			OauthAccessToken unionIdResult = JsonUtils.parse(unionid_html, OauthAccessToken.class);
//			result.setUnionid(unionIdResult.getUnionid());
//
//			mappedHash = mapper.toHash(result);
//			hashOperations.putAll(appid + "_OauthToken", mappedHash);
//		} catch (Exception e) {
//			log.error("中控服务器异常", e);
//		}
//		log.info("================\n" + JsonUtils.toJson(result));
//		return JsonUtils.toJson(result);

	}

	@ResponseBody
	@RequestMapping("/jsApiTicket/get.do")
	public String jsApiTicket(String appid, String secret) {
		if (StringUtils.isAnyBlank(appid, secret)) {
			return null;
		}
		// 1. 检查redis缓存
		if (stringRedisTemplate.boundValueOps(appid + "_jsApiTicket").getExpire() > 0) {
			String jsApiTicket = stringRedisTemplate.boundValueOps(appid + "_jsApiTicket").get();
			if (jsApiTicket != null) {
				log.debug("get jsapi_ticket from redis  :    jsApiTicket[ " + jsApiTicket + " ] ，  appid  [ " + appid + " ]");
				return jsApiTicket;
			}
		} else {

			// 2. 调用接口获取
			String access_token = getAccessToken(appid, secret);
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi";
			String html = null;
			JsApiTicket result = null;
			try {
				html = HttpHelper.connect(url).get().html();

				result = JsonUtils.parse(html, JsApiTicket.class);
				stringRedisTemplate.boundValueOps(appid + "_jsApiTicket").append(result.getTicket());
				stringRedisTemplate.boundValueOps(appid + "_jsApiTicket").expire(result.getExpiresIn(), TimeUnit.SECONDS);
				log.debug(result.getTicket());

			} catch (Exception e) {
				log.error("中控服务器异常", e);
			} finally {
				if (result != null) {
					return result.getTicket();
				}
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("/apiTicket")
	public String apiTicket(String appid, String secret) {
		if (StringUtils.isAnyBlank(appid, secret)) {
			return null;
		}
		return "";
	}

}
