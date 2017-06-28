package com.huiyang.wang.common.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;
import com.saysth.commons.entity.IdEntity;
import com.saysth.commons.utils.json.JsonUtils;

/**
 * 统一定义id的entity基类，同时加上了Morphia和JPA的注解以支持切换
 * <p/>
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. 子类可重载getId()函数重定义id的列名映射和生成策略.
 *
 * @author
 */
public abstract class BaseEntity implements IdEntity<String>, Serializable {
	private String id;
	@NotNull
	public String getId() {
		return id;
	}

	public void setId(String id) {
		// 如id值为空，则赋值为null
		this.id = id;
	}

	// @javax.persistence.Transient
	@JSONField(serialize = false)
	public boolean isNew() {
		return id == null;
	}

	@Override
	public String toString() {
		return getClass().getName() + "@" + JsonUtils.toJson(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseEntity))
			return false;
		final BaseEntity other = (BaseEntity) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
			else
				return super.equals(obj);
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		if (getId() == null) {
			return super.hashCode();
		} else {
			return this.getId().hashCode();
		}
	}

}
