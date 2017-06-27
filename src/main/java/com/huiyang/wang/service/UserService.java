package com.huiyang.wang.service;

import com.huiyang.wang.model.User;

public interface UserService {
	public User getUser(String userId);

	public void add(User user);

	public void update(User user);

	public void delete(String id);
}
