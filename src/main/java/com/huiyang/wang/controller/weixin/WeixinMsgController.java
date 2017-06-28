package com.huiyang.wang.controller.weixin;

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

	@Autowired
	private OfficialAccountMapper oAccMgr;
//	@Autowired
//	private CacheManager cacheMgr;

	/**
	 * 服务器配置验证时，讲token和oaid appId key信息放进redis，待到消息响应时再拿
	 */
	@Override
	protected String getTokenAppidAESKey(String oaid) {
		oaid = "3";
		String info = "";
		OfficialAccount oa = oAccMgr.get(oaid);
		if (oa != null) {
			info = oa.getToken() + "," + oa.getAppId() + "," + oa.getKey();
		}
		return info;
	}

}
