package com.tataelxsi.springdao.service.impl;

import java.util.List;

import com.tataelxsi.springdao.dao.UserDAO;
import com.tataelxsi.springdao.dto.User;
import com.tataelxsi.springdao.exceptions.DataBaseException;
import com.tataelxsi.springdao.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDAO userDao;
	public void setUserDAO(UserDAO userDao) {
		this.userDao = userDao;
	}

	
	
	public List<User> getAllUsers() throws DataBaseException
	{
		return this.userDao.getAllUsers();
	}

	public void createUser(User user)throws Exception
	{
		
		this.userDao.createUser(user);
	}

	public void deleteUser(int id){
		this.userDao.deleteUser(id);
	}
	

	public void updateUser(User user){
		this.userDao.updateUser(user);
	}
	public User getUserById(int id){
			return this.userDao.getUserById(id);
		
	}
}
