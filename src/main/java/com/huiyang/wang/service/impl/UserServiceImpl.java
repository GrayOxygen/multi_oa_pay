package com.huiyang.wang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huiyang.wang.mapper.UserMapper;
import com.huiyang.wang.model.User;
import com.huiyang.wang.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(String userId) {
		return this.userMapper.getUser(userId);
	}

	@Transactional
	@Override
	public void add(User user) {
		this.userMapper.add(user);
	}

	@Override
	public void update(User user) {
		this.userMapper.update(user);
	}

	@Override
	public void delete(String id) {
		this.userMapper.delete(id);
	}

}