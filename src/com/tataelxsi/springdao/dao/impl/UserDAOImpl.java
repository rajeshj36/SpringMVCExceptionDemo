package com.tataelxsi.springdao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tataelxsi.springdao.dao.UserDAO;
import com.tataelxsi.springdao.dto.User;
import com.tataelxsi.springdao.exceptions.DataBaseException;
import com.tataelxsi.springdao.mapper.UserMapper;

import org.springframework.jdbc.core.ResultSetExtractor;

public class UserDAOImpl implements UserDAO {
	private DataSource dataSource;
	List<User> users = new ArrayList<User>();
	private JdbcTemplate jdbcTemplateObject;
	User user = null;

	@Override
	public List<User> getAllUsers() throws DataBaseException {

		try {
			String SQL = "select * from users";
			users = jdbcTemplateObject.query(SQL, new UserMapper());
		} catch (Exception e) {
			// e.printStackTrace();
			DataBaseException dbe = new DataBaseException("Error in seletion");
			dbe.printStackTrace();
			throw dbe;
		} finally {
			try {

			} catch (Exception e) {

			}

		}
		return users;
	}

	public void createUser(User u) throws DataBaseException {
		try {
			// String query =
			// "insert into users(id, name, address) values(?, ?, ?)";
			String SQL = "insert into users(id, name, address) values(?, ?, ?)";
			jdbcTemplateObject.update(SQL, u.getId(), u.getName(),
					u.getAddress());
			return;
		} catch (Exception e) {
			// e.printStackTrace();
			DataBaseException dbe = new DataBaseException("Error in insertion");
			dbe.printStackTrace();
			throw dbe;
		} finally {
			try {

			} catch (Exception e) {

			}

		}
	}

	public void deleteUser(Integer id) throws DataBaseException {
		try {
			String SQL = "delete from users where id = ?";
			jdbcTemplateObject.update(SQL, id);
			System.out.println("Deleted Record with ID = " + id);
			return;
		} catch (Exception e) {
			// e.printStackTrace();
			DataBaseException dbe = new DataBaseException("Error in deletion");
			dbe.printStackTrace();
			throw dbe;
		}
	}

	public void updateUser(User u) throws DataBaseException {
		try {
			String SQL = "update users set name = ? ,address = ? where id = ?";
			jdbcTemplateObject.update(SQL, u.getName(), u.getAddress(),
					u.getId());
			System.out.println("Updated Record with ID = " + u.getId());
			return;
		} catch (Exception e) {
			DataBaseException dbe = new DataBaseException("Error in updation");
			dbe.printStackTrace();
			throw dbe;

		} finally {
			try {

			} catch (Exception e) {

			}

		}
	}

	public User getUserById(int id) throws DataBaseException {
		try {
			String sql = "select * from users where id = " + id;
			return jdbcTemplateObject.query(sql,
					new ResultSetExtractor<User>() {

						@Override
						public User extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							if (rs.next()) {
								User userdto = new User();
								userdto.setId(rs.getInt("id"));
								userdto.setName(rs.getString("name"));
								userdto.setAddress(rs.getString("address"));
								return userdto;
							}

							return null;
						}

					});
		} catch (Exception e) {
			// e.printStackTrace();
			DataBaseException dbe = new DataBaseException(
					"Error in Getting the user");
			dbe.printStackTrace();
			throw dbe;
		} finally {
			try {

			} catch (Exception e) {

			}

		}
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

}
