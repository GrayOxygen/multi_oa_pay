package com.huiyang.wang.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.huiyang.wang.common.api.ApiRetMsg;
import com.huiyang.wang.common.entity.enums.PayOrderStatus;
import com.huiyang.wang.common.exception.AppException;
import com.huiyang.wang.common.util.WechatPayUtils;
import com.huiyang.wang.mapper.PayOrderMapper;
import com.huiyang.wang.model.OfficialAccount;
import com.huiyang.wang.model.PayOrder;
import com.saysth.commons.utils.StrUtils;
import com.saysth.weixin.sdk.PayApi;
import com.saysth.weixin.sdk.enums.YesNoType;
import com.saysth.weixin.sdk.exception.WeixinException;
import com.saysth.weixin.sdk.pay.entity.OrderQuery;
import com.saysth.weixin.sdk.pay.entity.OrderQueryResult;
import com.saysth.weixin.sdk.pay.entity.UnifiedOrder;
import com.saysth.weixin.sdk.pay.entity.UnifiedOrderResult;
import com.saysth.weixin.sdk.pay.enums.TradeState;
import com.saysth.weixin.sdk.pay.enums.TradeType;

/**
 * 支付服务
 */
@Service
public class PayService implements InitializingBean {
	private final static Logger log = LoggerFactory.getLogger(PayService.class);

	@Autowired
	private PayOrderMapper payOrderMapper;
	@Autowired
	private OfficialAccountManager oaMgr;

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Transactional
	public PayOrder multiPay(String openId, String parentAppId, String parentMchId, String subMchId, Integer amount, String spBillCreateIp, String notifyUrl) throws AppException {

		OfficialAccount oa = oaMgr.getOfficialAccount("0");
		OfficialAccount childOA = oaMgr.getOfficialAccount("1");
		Assert.hasText(openId, "openId must not null");
		Assert.notNull(amount, "amount must not null");

		PayOrder order = new PayOrder();
		// order.setAppId(oa.getAppId());
		// order.setMchId(oa.getMchId());
		order.setAppId(oa.getAppid());
		order.setMchId(oa.getMchId());
		order.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setSubAppId(childOA.getAppId());
		order.setSubMchId(childOA.getMchId());
		order.setTradeType(TradeType.JSAPI);
		order.setStatus(PayOrderStatus.CREATED);
		order.setNonceStr(StrUtils.getShortUUID());
		order.setAttach("测试");
		order.setBody("测试");
		order.setDetail("测试");
		order.setProductId("1");
		// order.setOpenId(openId);
		order.setSubOpenId(openId);
		order.setTotalFee(amount);
		order.setSpBillCreateIp(spBillCreateIp);
		order.setNotifyUrl(notifyUrl);
		order.setCtime(new Date());
		payOrderMapper.save(order);
		try {
			UnifiedOrder payPackage = order.toUnifiedOrder();
			UnifiedOrderResult result = PayApi.commitOrder(payPackage, oa.getKey());
			if (result.isSuccess()) {
				order.setStatus(PayOrderStatus.COMMITTED);
				order.setPrepayId(result.getPrepay_id());
				order.setCodeUrl(result.getCode_url());
				payOrderMapper.update(order);
				log.info("charge order committed,orderId:{}, ", order.getId());
				return order;
			} else {
				return null;
			}
		} catch (Exception e) {
			log.error("", e);
			throw new AppException(ApiRetMsg.ERR_WEIXIN_EXCEPTION, ApiRetMsg.getMsg(ApiRetMsg.ERR_WEIXIN_EXCEPTION));
		}
	}

	@Transactional
	public void queryOrder(String id) throws AppException {
		Assert.notNull(id);
		PayOrder order = payOrderMapper.getForUpdate(id);
		if (order == null) {
			log.error("pay order no exist, id:{}", id);
			throw new AppException(ApiRetMsg.ERR_CHARGE_ORDER_UNKNOWN, ApiRetMsg.getMsg(ApiRetMsg.ERR_CHARGE_ORDER_UNKNOWN));
		}
		if (order.getStatus() == PayOrderStatus.SUCCESS) {
			log.info("pay order:{} had handled,ignore", order.getId());
			return;
		}
		if (order.getStatus() == PayOrderStatus.CREATED) {
			log.info("pay order:{} not handled,ignore", order.getId());
			return;
		}
		OfficialAccount oa = oaMgr.getOfficialAccountByAppid(order.getAppId());
		Assert.notNull(oa, "officialAccount must not null");
		Assert.hasText(oa.getKey(), "key must not null");
		Assert.hasText(oa.getMchId(), "mchId must not null");
		OrderQuery query = new OrderQuery();
		query.setAppid(oa.getAppId());
		query.setMch_id(oa.getMchId());
		query.setOut_trade_no(id);
		try {
			OrderQueryResult result = PayApi.orderQuery(query, oa.getKey());
			log.info("pay order committed,orderId:{}, ", order.getId());
			order.setOpenId(result.getOpenid());
			if (result.getIs_subscribe() == null || result.getIs_subscribe() == YesNoType.N) {
				order.setSubscribed(false);
			} else {
				order.setSubscribed(true);
			}
			if (result.getSub_is_subscribe() == null || result.getSub_is_subscribe() == YesNoType.N) {
				order.setSubSubscribed(false);
			} else {
				order.setSubSubscribed(true);
			}
			// TODO:中间人攻击未作处理
			order.setBankType(result.getBank_type());
			order.setFeeType(result.getFee_type());
			order.setCashFee(result.getCash_fee() == null ? 0 : result.getCash_fee());
			order.setCashFeeType(result.getCash_fee_type());
			order.setCouponFee(result.getCoupon_fee() == null ? 0 : result.getCoupon_fee());
			order.setCouponCount(result.getCoupon_count());
			order.setTransactionId(result.getTransaction_id());
			order.setTimeEnd(result.getTime_end());
			order.setTradeState(result.getTrade_state());
			if (order.getTradeState() == TradeState.SUCCESS) {
				order.setStatus(PayOrderStatus.SUCCESS);
			}
			payOrderMapper.save(order);
			if (order.getTradeState() == TradeState.SUCCESS) {
				log.info("pay order:{} success", order.getId());
			} else {
				log.info("charge order:{} update", order.getId());
			}
		} catch (WeixinException e) {
			log.error("", e);
			throw new AppException(ApiRetMsg.ERR_WEIXIN_EXCEPTION, ApiRetMsg.getMsg(ApiRetMsg.ERR_WEIXIN_EXCEPTION));
		}
	}

	/**
	 * 获取
	 * 
	 * @author 王辉阳
	 * @date 2016年2月29日 上午9:45:40
	 * @param orderId
	 * @return
	 * @throws AppException
	 */
	public PayOrderStatus getOrderStatus(String orderId) throws AppException {
		PayOrder order = payOrderMapper.get(orderId);
		if (order == null) {
			throw new AppException(ApiRetMsg.ERR_CHARGE_ORDER_UNKNOWN, ApiRetMsg.getMsg(ApiRetMsg.ERR_CHARGE_ORDER_UNKNOWN));
		}
		return order.getStatus();
	}

	/**
	 * 根据商户号获取订单
	 * 
	 * @author 王辉阳
	 * @date 2016年2月29日 上午9:45:22
	 * @param out_trade_no
	 * @return
	 */
	public PayOrder getByOutTradeNo(String out_trade_no) {
		if (StringUtils.isEmpty(out_trade_no)) {
			return null;
		}
		return payOrderMapper.get(out_trade_no);
	}

	/**
	 * 更新订单的交易状态
	 * 
	 * @author 王辉阳
	 * @date 2016年2月29日 下午3:51:54
	 * @param ids
	 *            订单主键数组
	 */
	public void refreshTradeState(String[] ids) {
		for (String id : ids) {
			PayOrder order = payOrderMapper.get(id);
			OfficialAccount oa = oaMgr.getOfficialAccountByAppid(order.getAppId());
			OrderQueryResult result = WechatPayUtils.orderQuery(oa, order);

			if (null == result.getTrade_state()) {
				continue;
			}
			order.setTradeState(result.getTrade_state());
			payOrderMapper.save(order);
		}
	}

	public Long count(Map<String, Object> map) {
		return payOrderMapper.count(map);
	}

}
