package com.huiyang.wang.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.huiyang.wang.model.User;

public interface UserMapper {
	@Select("SELECT * FROM users WHERE id = #{userId}")
	User getUser(@Param("userId") String userId);

	@Insert("insert into users (id,name,age) values (#{user.id},#{user.name},#{user.age})")
	void add(@Param("user") User user);

	@Insert("update users set  name= #{user.name},age=#{user.age}  where id =#{user.id}  ")
	void update(@Param("user") User user);

	@Insert("delete from users where id = #{id}")
	void delete(@Param("id") String id);
}