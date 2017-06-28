package com.huiyang.wang.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huiyang.wang.model.OfficialAccount;
import com.huiyang.wang.model.PayOrder;
import com.saysth.weixin.sdk.PayApi;
import com.saysth.weixin.sdk.exception.WeixinException;
import com.saysth.weixin.sdk.pay.entity.OrderQuery;
import com.saysth.weixin.sdk.pay.entity.OrderQueryResult;

/**
 * 
 * @author 王辉阳
 * 
 * @date 2016年2月29日 下午4:17:03
 * 
 * @Description 支付接口封装工具类
 */
public class WechatPayUtils {
	private static final Logger log = LoggerFactory.getLogger(WechatPayUtils.class);

	/**
	 * 查询订单
	 * 
	 * @author 王辉阳
	 * @date 2016年2月29日 下午4:17:21
	 * @param oa
	 *            公众号
	 * @param order
	 *            订单
	 * @return
	 */
	public static OrderQueryResult orderQuery(OfficialAccount oa, PayOrder order) {
		try {
			OrderQuery query = new OrderQuery();

			query.setAppid(oa.getAppId());
			query.setSub_appid(order.getSubAppId());
			query.setMch_id(oa.getMchId());
			query.setSub_mch_id(order.getSubMchId());
			query.setTransaction_id(order.getTransactionId());
			query.setNonce_str(order.getNonceStr());

			return PayApi.orderQuery(query, oa.getKey());
		} catch (WeixinException e) {
			e.printStackTrace();
		}
		return null;
	}

}