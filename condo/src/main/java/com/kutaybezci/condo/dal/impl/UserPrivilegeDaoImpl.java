package com.kutaybezci.condo.dal.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kutaybezci.condo.dal.UserPrivilegeDao;
import com.kutaybezci.condo.dal.model.UserPrivilegeEntity;

@Repository
public class UserPrivilegeDaoImpl implements UserPrivilegeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<UserPrivilegeEntity> getUserPrivilege(long userId) {
		try {

			List<UserPrivilegeEntity> userPrivileges = (List<UserPrivilegeEntity>) jdbcTemplate.query(
					"select * from user_privilege  where user_id= ? ", new Object[] { userId },
					new BeanPropertyRowMapper(UserPrivilegeEntity.class));
			return userPrivileges;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
