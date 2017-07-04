package com.huiyang.wang.controller.weixin.common;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.saysth.commons.http.HttpHelper;
import com.saysth.commons.utils.json.JsonUtils;
import com.saysth.weixin.sdk.token.AccessToken;
import com.saysth.weixin.sdk.token.OauthAccessToken;

/**
 * 
 * @author 王辉阳
 * @date 2017年7月4日 下午7:25:07
 * @Description 中控服务器(redis缓存有时效性的数据)：负责微信有失效性相关的参数维护
 */
@Controller
@RequestMapping("/central_control")
public class TokenController {
	private static final Logger log = LoggerFactory.getLogger(TokenController.class);
	@Resource(name = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

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
		if (StringUtils.isAnyBlank(appid, secret)) {
			return null;
		}
		// 1. 检查redis缓存
		if (redisTemplate.boundValueOps(appid + "_AccessToken").getExpire() > 0) {
			String accessToken = redisTemplate.boundValueOps(appid + "_AccessToken").get();
			if (accessToken != null) {
				log.debug("get access token from redis  :   token[ " + accessToken + " ] ，  appid  [ " + appid + " ]");
				return accessToken;
			}
		}

		// 2. 调用接口获取access token
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
		String html = null;
		AccessToken result = null;
		try {
			html = HttpHelper.connect(url).get().html();

			result = (AccessToken) JsonUtils.parse(html, AccessToken.class);
			redisTemplate.boundValueOps(appid + "_AccessToken").append(result.getAccessToken());
			redisTemplate.boundValueOps(appid + "_AccessToken").expire(result.getExpiresIn(), TimeUnit.SECONDS);

		} catch (Exception e) {
			log.error("中控服务器异常", e);
		} finally {
			if (result == null) {
				return null;
			}
			return result.getAccessToken();
		}

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
		if (StringUtils.isAnyBlank(appid, secret, code)) {
			return null;
		}
		// 1. 检查redis缓存
		if (redisTemplate.boundValueOps(appid + "_OauthToken").getExpire() > 0) {
			String accessToken = redisTemplate.boundValueOps(appid + "_OauthToken").get();
			if (accessToken != null) {
				log.debug("get oauth token from redis  :   token[ " + accessToken + " ] ，  appid  [ " + appid + " ]");
				return accessToken;
			}
		}

		// 2. 调用接口获取oauth token
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
		String html = null;
		OauthAccessToken result = null;
		try {
			html = HttpHelper.connect(url).get().html();

			result = (OauthAccessToken) JsonUtils.parse(html, OauthAccessToken.class);
			redisTemplate.boundValueOps(appid + "_OauthToken").append(result.getAccessToken());
			redisTemplate.boundValueOps(appid + "_OauthToken").expire(result.getExpiresIn(), TimeUnit.SECONDS);
			log.debug(result.getAccessToken());

		} catch (Exception e) {
			log.error("中控服务器异常", e);
		} finally {
			if (result == null) {
				return null;
			}
			return result.getAccessToken();
		}

	}

	@ResponseBody
	@RequestMapping("/jsApiTicket")
	public String jsApiTicket(String appid, String secret) {
		if (StringUtils.isAnyBlank(appid, secret)) {
			return null;
		}
		return "";
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
