package com.tataelxsi.springdao.mapper;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tataelxsi.springdao.dto.User;

public class UserMapper implements RowMapper<User> {
   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	   User user = new User();
	   user.setId(rs.getInt("id"));
	   user.setName(rs.getString("name"));
	   user.setAddress(rs.getString("address"));
      return user;
   }
}