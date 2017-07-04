package com.huiyang.wang.common.entity.enums;

import com.saysth.commons.entity.Labeled;

public enum PayType implements Labeled {
	PTS("普通商户"), FWS("服务商");

	private String label;
	private String name;

	private PayType(String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return this.label;
	}

	public String getName() {
		return this.name();
	}
}
