package com.huiyang.wang.common.util;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.saysth.commons.http.HttpHelper;
import com.saysth.commons.utils.StrUtils;
import com.saysth.weixin.sdk.UserApi;
import com.saysth.weixin.sdk.entity.OfficialAccountBean;
import com.saysth.weixin.sdk.exception.WeixinException;
import com.saysth.weixin.sdk.response.UserResult;

/**
 * @author 王辉阳
 * @date 2017年7月4日 下午6:30:27
 * @Description token中控服务器实现：redis存储token，定时刷新，暂时用该项目做为中控服务器，需要刷新的参数就访问该项目的接口，获取到后会自动存在redis中
 */
public class WeixinUtils {
	private static final Logger log = LoggerFactory.getLogger(WeixinUtils.class);

	private String wxServiceUrl;

	@Resource(name = "redisTemplate")
	private RedisTemplate redisTemplate;

	public String getAccessToken(String appid, String secret) {
		String url = getWxServiceUrl() + "/accessToken/get.do?appid=" + appid + "&secret=" + secret;
		String token = null;
		try {
			token = HttpHelper.connect(url).get().html();

			redisTemplate.boundValueOps(appid).append(token);
			redisTemplate.boundValueOps(appid).expire(1, TimeUnit.MILLISECONDS);

		} catch (Exception e) {
			log.error("微信中控服务器失联", e);
		}
		if (StrUtils.isBlank(token)) {
			log.error("从微信中控服务器获取token失败");
		}
		return token;
	}

	public String getOauthToken(String appid, String secret, String code) {
		String url = getWxServiceUrl() + "/oauthToken/get.do?appid=" + appid + "&secret=" + secret + "&code=" + code;
		String token = null;
		try {
			token = HttpHelper.connect(url).get().html();
		} catch (Exception e) {
			log.error("微信中控服务器失联", e);
		}
		if (StrUtils.isBlank(token)) {
			log.error("从微信中控服务器获取token失败");
		}
		return token;
	}

	public String getJsApiTicket(String appid, String secret) {
		String url = getWxServiceUrl() + "/jsApiTicket/get.do?appid=" + appid + "&secret=" + secret;
		String ticket = null;
		try {
			ticket = HttpHelper.connect(url).get().html();
		} catch (Exception e) {
			log.error("微信中控服务器失联", e);
		}
		if (StrUtils.isBlank(ticket)) {
			log.error("从微信中控服务器获取JsApiTicket失败");
		}
		return ticket;
	}

	public String getApiTicket(String appid, String secret) {
		String url = getWxServiceUrl() + "/apiTicket/get.do?appid=" + appid + "&secret=" + secret;
		String ticket = null;
		try {
			ticket = HttpHelper.connect(url).get().html();
		} catch (Exception e) {
			log.error("微信中控服务器失联", e);
		}
		if (StrUtils.isBlank(ticket)) {
			log.error("从微信中控服务器获取ApiTicket失败");
		}
		return ticket;
	}

	public UserResult getRealtimeFan(String openid, OfficialAccountBean oa) {
		String accessToken = getAccessToken(oa.getAppid(), oa.getSecret());
		if (StrUtils.isNotBlank(accessToken)) {
			try {
				return UserApi.getUserInfo(accessToken, openid);
			} catch (WeixinException e) {
				log.error(e.getCode(), e);
			}
		}
		return null;
	}

	public String getWxServiceUrl() {
		return this.wxServiceUrl;
	}

	public void setWxServiceUrl(String wxServiceUrl) {
		this.wxServiceUrl = wxServiceUrl;
	}
}
