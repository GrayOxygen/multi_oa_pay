package com.huiyang.wang.common.entity.enums;

import com.saysth.commons.entity.Labeled;

/**
 * 
 * @author 王辉阳
 * @date 2017年7月5日 下午12:04:24
 * @Description 涉及的几个公众号
 */
public enum OAType implements Labeled {
	BOHUI("博汇"), YINGHUANG("英煌"), XINHUAN("新环"), XINJI("新基");
	private String label;
	private String name;

	private OAType(String label) {
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
