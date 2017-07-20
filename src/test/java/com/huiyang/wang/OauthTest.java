package com.huiyang.wang;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.huiyang.wang.common.exception.AppException;
import com.huiyang.wang.common.util.OauthUrlUtils;
import com.huiyang.wang.model.OfficialAccount;
import com.huiyang.wang.service.OfficialAccountManager;
import com.huiyang.wang.service.PayService;
import com.saysth.weixin.sdk.exception.WeixinException;
import com.saysth.weixin.sdk.pay.client.LocalHttpClient;
import com.saysth.weixin.sdk.util.WeixinService;

@SuppressWarnings("all")
@TestPropertySource("/application.properties")
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class OauthTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	private OfficialAccountManager oaMgr;
	@Autowired
	private PayService payService;
	@Autowired
	private WeixinService weixinService;

	@Test
	public void oauth() {
		OauthUrlUtils.getOauthBaseUrl("wxce11bdbd7690d99d", "/wx/makeMoney/index.do");

		try {
			String accessToken = oaMgr.getAccessToken("wxce11bdbd7690d99d");
			System.out.println(accessToken);
		} catch (AppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void show() throws WeixinException {
		OfficialAccount oa = oaMgr.getOfficialAccount("1");
		// System.out.println(QrCodeApi.getShortUrl(oaMgr.getAccessToken(oa),
		// OauthUrlUtils.getOauthBaseUrl(oa.getAppId(),
		// "/wx/show.do?cashierId=0423bc31c9214d00816f24bd7dec0515&id=b19f9893b02446af8f85906b404f2172")));
		System.out.println(OauthUrlUtils.getOauthBaseUrl(oa.getAppId(), "/wx/multiPay.do?source=yinghuang"));

		System.out.println(OauthUrlUtils.getOauthBaseUrl("wx40d365f3b7ba0644", "/wx/multiPay.do?source=xinhuan"));
		System.out.println(OauthUrlUtils.getOauthBaseUrl("wx10a590dea6c4eae5", "/wx/multiPay.do?source=bohui"));
		System.out.println(OauthUrlUtils.getOauthBaseUrl("wxf05189f68ef5d4c7", "/wx/multiPay.do?source=xinji"));

		// System.out.println(OauthUrlUtils.getOauthBaseUrl(oa.getAppId(),
		// "/wx/multiPay.do?busiType=xinji"));
	}

	@Test
	public void testToken() {
		// OfficialAccount oa = oaMgr.getOfficialAccount("-1");
		// System.out.println(oaMgr.getAccessToken(oa));
		LocalHttpClient.initMchKeyStore("1483579272");
	}

	@Test
	public void teddd() {
		System.out.println(weixinService.getAccessToken("wx737ad133237892d7", "af7b6b5474fc4cba69682344500c1b98"));
	}
}
