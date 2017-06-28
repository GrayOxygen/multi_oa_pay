package com.huiyang.wang.service;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huiyang.wang.common.api.ApiRetMsg;
import com.huiyang.wang.common.exception.AppException;
import com.huiyang.wang.mapper.OfficialAccountMapper;
import com.huiyang.wang.model.OfficialAccount;
import com.saysth.weixin.sdk.util.SignUtil;
import com.saysth.weixin.sdk.util.WeixinService;

/**
 * 公众号管理类，和公众号高度相关的行为请聚合于该类
 * 
 * @author KelvinZ
 */
@Service
public class OfficialAccountManager {
	private static final Logger log = LoggerFactory.getLogger(OfficialAccountManager.class);
	@Autowired
	private OfficialAccountMapper oaDao;
	@Autowired
	private WeixinService wxService;

	/**
	 * 根据主键ID查询
	 * 
	 * @param id
	 * @return
	 */
	public OfficialAccount getOfficialAccount(String id) {
		return oaDao.get(id);
	}

	/**
	 * 根据appid查询
	 * 
	 * @param appid
	 * @return
	 */
	public OfficialAccount getOfficialAccountByAppid(String appid) {
		return oaDao.getByAppId(appid);
	}

	/**
	 * 获取微信接口调用凭证
	 * 
	 * @return
	 * @throws AppException
	 */
	public String getAccessToken(String appId) throws AppException {
		final OfficialAccount oa = getOfficialAccountByAppid(appId);
		final String token = getAccessToken(oa);// 获取token信息
		if (StringUtils.isBlank(token)) {
			throw new AppException(ApiRetMsg.ERR_WEIXIN_SERVICE, ApiRetMsg.getMsg(ApiRetMsg.ERR_WEIXIN_SERVICE));
		}
		return token;
	}

	/**
	 * 获取微信接口调用凭证
	 * 
	 * @param oa
	 * @return
	 */
	public String getAccessToken(OfficialAccount oa) {
		Assert.notNull(oa, "oa不能为null");
		Assert.hasText(oa.getAppId(), "appId不能为空");
		Assert.hasText(oa.getSecret(), "secret不能为空");
		final String token = wxService.getAccessToken(oa.getAppId(), oa.getSecret());// 获取token信息
		return token;
	}

	public String getJsApiTicket(OfficialAccount oa) {
		Assert.notNull(oa, "oa不能为null");
		Assert.hasText(oa.getAppId(), "appId不能为空");
		Assert.hasText(oa.getSecret(), "secret不能为空");
		final String ticket = wxService.getJsApiTicket(oa.getAppId(), oa.getSecret());
		return ticket;
	}

	public String getApiTicket(OfficialAccount oa) {
		Assert.notNull(oa, "oa不能为null");
		Assert.hasText(oa.getAppId(), "appId不能为空");
		Assert.hasText(oa.getSecret(), "secret不能为空");
		final String ticket = wxService.getApiTicket(oa.getAppId(), oa.getSecret());
		return ticket;
	}

	/**
	 * 获取JsAPI必要参数
	 * 
	 * @param oa
	 * @param url
	 * @return
	 */
	public Map<String, String> getWX(OfficialAccount oa, String url) {
		Map<String, String> wx = SignUtil.sign(getJsApiTicket(oa), url);
		wx.put("appId", oa.getAppId());
		return wx;
	}

	/**
	 * 批量删除 软删除
	 * 
	 * @param ids
	 * @author 王辉阳
	 * @date 2016年1月30日 下午4:00:33
	 */
	@Transactional
	public void batchDelete(String[] ids) {
		oaDao.batchSoftOpt(true, Arrays.asList(ids));
	}

	public void update(OfficialAccount module) {
		module.setMtime(new Date());
		oaDao.save(module);
	}

}
