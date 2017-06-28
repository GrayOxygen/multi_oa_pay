package com.huiyang.wang.common.entity;

import java.io.Serializable;

import com.saysth.commons.entity.UnDeletable;

/**
 * 不可删实体
 *
 * @author KelvinZ
 */
public abstract class UnDeletedEntity<PK extends Serializable> extends BaseEntity implements UnDeletable {
	private Boolean deleted;

	/**
	 * 为节省MongoDB的空间,可以用null或者false表示未删除;true为已删除
	 */
	@Override
	public boolean isDeleted() {
		return (deleted == null) ? false : deleted;
	}

	/**
	 * 为节省MongoDB的空间,为false时保存null;true保存true
	 */
	@Override
	public void setDeleted(boolean deleted) {
		this.deleted = deleted ? true : null;
	}

}