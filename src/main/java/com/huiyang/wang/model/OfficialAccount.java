package com.huiyang.wang.model;

import java.util.Date;

import com.huiyang.wang.common.entity.UnDeletedEntity;
import com.huiyang.wang.common.entity.enums.PayType;
import com.saysth.weixin.sdk.entity.OfficialAccountBean;

/**
 * 微信公众号对象
 */
public class OfficialAccount extends UnDeletedEntity<String> implements OfficialAccountBean {
	/**
	 * 默认序列化ID
	 */
	private static final long serialVersionUID = -7999664917073852353L;

	private String name; // 名称
	private String appId;
	private String secret;
	private String mchId;//商户id
	private String key;// 交易签名算法key
						// key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置
	private PayType type;//商户类型：服务商或普通商户
	private Date ctime = new Date();
	private Date mtime;
	// 微信 公众号模板消息id
	private String templateId;

	// 服务器token配置
	private String token;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Override
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public PayType getType() {
		return type;
	}

	public void setType(PayType type) {
		this.type = type;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getMtime() {
		return mtime;
	}

	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	@Override
	public String toString() {
		return "OfficialAccount [name=" + name + ", appId=" + appId + ", secret=" + secret + ", mchId=" + mchId + ", key=" + key + ", type=" + type + ", ctime=" + ctime
				+ ", mtime=" + mtime + ", templateId=" + templateId + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String getAppid() {
		return appId;
	}

	@Override
	public String getDevCode() {
		return null;
	}

}
