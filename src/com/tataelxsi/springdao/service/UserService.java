package com.tataelxsi.springdao.service;

import java.util.List;

import com.tataelxsi.springdao.dto.User;

public interface UserService {
	public List<User> getAllUsers() throws Exception;

	public void createUser(User user) throws Exception;

	public void deleteUser(int id);

	public void updateUser(User user);
	public User getUserById(int id);
}
