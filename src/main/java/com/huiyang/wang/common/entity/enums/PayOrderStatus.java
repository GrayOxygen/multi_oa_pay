package com.huiyang.wang.common.entity.enums;

import com.saysth.commons.entity.Labeled;

/**
 * 充值订单状态
 *
 * @author zhanghuan
 */
public enum PayOrderStatus implements Labeled {
	CREATED("创建订单"),//本地创建订单
	COMMITTED("提交订单"),//订单已预下到微信
	SUCCESS("支付成功");


	private String label;

	private PayOrderStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
