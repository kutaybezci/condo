package com.kutaybezci.condo.dal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kutaybezci.condo.dal.UserDao;
import com.kutaybezci.condo.dal.model.UserEntity;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserEntity getUser(String userName) {
		try {

			UserEntity user = (UserEntity) jdbcTemplate.queryForObject("SELECT * FROM user where user_name = ? ",
					new Object[] { userName }, new BeanPropertyRowMapper(UserEntity.class));
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
