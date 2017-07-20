package com.huiyang.wang.controller.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huiyang.wang.mapper.OfficialAccountMapper;
import com.huiyang.wang.model.OfficialAccount;
import com.saysth.weixin.sdk.web.WeixinControllerSupport;

/**
 * 微信消息
 * 
 */
@Controller
@RequestMapping("/wx")
public class WeixinMsgController extends WeixinControllerSupport {
	private static final Logger log = LoggerFactory.getLogger(WeixinMsgController.class);


	@Autowired
	private OfficialAccountMapper oAccMgr;
	// @Autowired
	// private CacheManager cacheMgr;

	/**
	 * 服务器配置验证时，讲token和oaid appId key信息放进redis，待到消息响应时再拿
	 */
	@Override
	protected String getTokenAppidAESKey(String oaid) {
		String info = "";
		OfficialAccount oa = oAccMgr.getByAppId("wxf05189f68ef5d4c7");
		if (oa != null) {
			info = oa.getToken() + "," + oa.getAppId() + "," + oa.getKey();
		}
		return info;
	}

}
