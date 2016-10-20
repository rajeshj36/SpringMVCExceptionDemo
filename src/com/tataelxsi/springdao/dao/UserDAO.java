package com.tataelxsi.springdao.dao;



import java.util.List;

import javax.sql.DataSource;

import com.tataelxsi.springdao.dto.User;
import com.tataelxsi.springdao.exceptions.DataBaseException;




public interface UserDAO {
	public List<User> getAllUsers() throws DataBaseException;

	public void createUser(User u) throws DataBaseException;

	public void deleteUser(Integer id) throws DataBaseException;

	public void updateUser(User u)throws DataBaseException;
	public void setDataSource(DataSource ds) throws DataBaseException;
	public User getUserById(int id) throws DataBaseException;
}




