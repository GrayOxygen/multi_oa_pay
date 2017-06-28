package com.huiyang.wang.common.util;

import com.saysth.weixin.sdk.UserApi;
import com.saysth.weixin.sdk.UserApi.OauthScope;

/**
 * 生成各种网页授权链接的工具
 */
public class OauthUrlUtils {

	// 该对象不需要实例化
	private OauthUrlUtils() {
	}

	/**
	 * 生成网页授权读取用户信息的链接
	 *
	 * @param appid
	 *            微信公众号信息
	 * @param landingUrl
	 *            最终目标链接
	 * @param scope
	 *            应用授权作用域(base和userInfo)
	 * @return
	 */
	public static String getOauthUrl(String appid, String landingUrl, OauthScope scope) {
		String domain = ResourceUtil.getAppUrl();
		String redirectUri = domain + "/wx/p.do?appid=" + appid;
		String state = landingUrl.replace(domain, "");
		return UserApi.getOauthUrl(appid, redirectUri, scope, state);
	}

	/**
	 * 生成仅读取用户openid的网页授权链接
	 *
	 * @param appid
	 * @param landingUrl
	 *            同域链接，会自动判断并去掉多余的根路径部分
	 * @return
	 */
	public static String getOauthBaseUrl(String appid, String landingUrl) {
		return getOauthUrl(appid, landingUrl, OauthScope.BASE);
	}

	/**
	 * 生成读取用户详细资料的网页授权链接
	 *
	 * @param appid
	 * @param landingUrl
	 *            同域链接，会自动判断并去掉多余的根路径部分
	 * @return
	 */
	public static String getOauthUserInfoUrl(String appid, String landingUrl) {
		return getOauthUrl(appid, landingUrl, OauthScope.USER_INFO);
	}

	/**
	 * 生成网页授权读取用户信息的链接
	 *
	 * @param appid
	 *            微信公众号信息
	 * @param landingUrl
	 *            最终目标链接
	 * @param scope
	 *            应用授权作用域(base和userInfo)
	 * @return
	 */
	public static String getTargetOrSubUrl(String appid, String landingUrl, OauthScope scope) {
		String domain = ResourceUtil.getAppUrl();
		String redirectUri = domain + "/wx/targetOrSub.do?appid=" + appid;
		String state = landingUrl.replace(domain, "");
		return UserApi.getOauthUrl(appid, redirectUri, scope, state);
	}

}