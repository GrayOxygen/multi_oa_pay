package com.huiyang.wang.model;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.huiyang.wang.common.entity.UnDeletedEntity;
import com.huiyang.wang.common.entity.enums.PayOrderStatus;
import com.saysth.weixin.sdk.enums.FeeType;
import com.saysth.weixin.sdk.pay.entity.UnifiedOrder;
import com.saysth.weixin.sdk.pay.enums.TradeState;
import com.saysth.weixin.sdk.pay.enums.TradeType;

/**
 * 
 * @author 王辉阳
 * @date 2017年7月4日 下午11:07:44
 * @Description 注意：调用支付接口时，商户有个加密文件，在支付时需要用到
 *              </p>
 *              (服务商的时候需要用到.p12的文件，windows下将商户号.p12放到d:/cert文件夹下(d:/cert/mchId.p12)，linux下位置为/home/tomcat/mchId.p12)
 */
public class PayOrder extends UnDeletedEntity<String> {
	private String appId;// 公众账号ID
	private String mchId;// 微信支付分配的商户号
	private String subAppId;// 微信分配的子商户公众账号ID
	private String subMchId;// 微信支付分配的子商户号
	private String deviceInfo;// 终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String nonceStr;// 随机字符串，不长于32位。随机数生成算法
	private String body;// 商品描述
	private String detail;// 商品详情
	private String attach;// 附加数据,该字段主要用于商户携带订单的自定义数据
	private Integer totalFee;// 总金额
	private String spBillCreateIp;// 终端IP
	private String timeStart;// 交易起始时间
	private String timeExpire;// 交易结束时间
	private String goodsTag;// 商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	private String notifyUrl;// 通知地址，接收微信支付异步通知回调地址
	private TradeType tradeType;// 交易类型
	private String productId; // 此id为二维码中包含的商品ID，商户自行定义
	private String openId; // 用户标识 openid

	private String subOpenId;// 用户在子商户appid下的唯一标识
	private String prepayId;// 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	private String codeUrl;// 二维码连接
	private Boolean subscribed;// 用户是否关注公众账号
	private Boolean subSubscribed;// 用户是否关注子公众账号
	private String bankType;// 付款银行
	private FeeType feeType;// 货币类型
	private Integer cashFee;// 现金支付金额
	private FeeType cashFeeType;// 现金支付货币类型
	private Integer couponFee;// 代金券或立减优惠金额
	private Integer couponCount;// 代金券或立减优惠使用数量
	private String transactionId;// 微信支付订单号
	private String timeEnd;// 支付完成时间

	private TradeState tradeState;// 订单的交易状态

	private PayOrderStatus status;// 系统中的订单状态

	private Date ctime;// 创建时间
	private Date mtime;// 修改时间

	public UnifiedOrder toUnifiedOrder() {
		if (StringUtils.isBlank(getId())) {
			throw new IllegalStateException("id is null");
		}
		if (StringUtils.isBlank(notifyUrl)) {
			throw new IllegalStateException("notify url is null");
		}
		UnifiedOrder unifiedOrder = new UnifiedOrder();
		unifiedOrder.setAppid(appId);
		unifiedOrder.setMch_id(mchId);
		unifiedOrder.setSub_appid(subAppId);
		unifiedOrder.setSub_mch_id(subMchId);
		unifiedOrder.setDevice_info(deviceInfo);
		unifiedOrder.setNonce_str(nonceStr);
		unifiedOrder.setBody(body);
		unifiedOrder.setDetail(detail);
		unifiedOrder.setAttach(attach);
		// 勿修改为其他值，否则影响通知逻辑(根据订单号找订单，订单表没存订单号)
		unifiedOrder.setOut_trade_no(getId());
		unifiedOrder.setTotal_fee(totalFee);
		unifiedOrder.setSpbill_create_ip(spBillCreateIp);
		unifiedOrder.setTime_start(timeStart);
		unifiedOrder.setTime_expire(timeExpire);
		unifiedOrder.setGoods_tag(goodsTag);
		unifiedOrder.setNotify_url(notifyUrl);
		unifiedOrder.setTrade_type(tradeType);
		unifiedOrder.setProduct_id(productId);
		unifiedOrder.setOpenid(openId);
		unifiedOrder.setSub_openid(subOpenId);

		return unifiedOrder;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public Integer getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Integer totalFee) {
		this.totalFee = totalFee;
	}

	public String getSpBillCreateIp() {
		return spBillCreateIp;
	}

	public void setSpBillCreateIp(String spBillCreateIp) {
		this.spBillCreateIp = spBillCreateIp;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getTimeExpire() {
		return timeExpire;
	}

	public void setTimeExpire(String timeExpire) {
		this.timeExpire = timeExpire;
	}

	public String getGoodsTag() {
		return goodsTag;
	}

	public void setGoodsTag(String goodsTag) {
		this.goodsTag = goodsTag;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSubOpenId() {
		return subOpenId;
	}

	public void setSubOpenId(String subOpenId) {
		this.subOpenId = subOpenId;
	}

	public String getPrepayId() {
		return prepayId;
	}

	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}

	public PayOrderStatus getStatus() {
		return status;
	}

	public void setStatus(PayOrderStatus status) {
		this.status = status;
	}

	public Boolean getSubscribed() {
		return subscribed;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	public Boolean getSubSubscribed() {
		return subSubscribed;
	}

	public void setSubSubscribed(Boolean subSubscribed) {
		this.subSubscribed = subSubscribed;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public FeeType getFeeType() {
		return feeType;
	}

	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}

	public Integer getCashFee() {
		return cashFee;
	}

	public void setCashFee(Integer cashFee) {
		this.cashFee = cashFee;
	}

	public FeeType getCashFeeType() {
		return cashFeeType;
	}

	public void setCashFeeType(FeeType cashFeeType) {
		this.cashFeeType = cashFeeType;
	}

	public Integer getCouponFee() {
		return couponFee;
	}

	public void setCouponFee(Integer couponFee) {
		this.couponFee = couponFee;
	}

	public Integer getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(String timeEnd) {
		this.timeEnd = timeEnd;
	}

	public TradeState getTradeState() {
		return tradeState;
	}

	public void setTradeState(TradeState tradeState) {
		this.tradeState = tradeState;
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

}
