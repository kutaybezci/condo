package com.kutaybezci.condo.bl.impl;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.kutaybezci.condo.bl.UserBo;
import com.kutaybezci.condo.bl.exceptions.LoginFailed;
import com.kutaybezci.condo.bl.types.User;
import com.kutaybezci.condo.dal.UserDao;
import com.kutaybezci.condo.dal.model.UserEntity;

@Service
public class UserBoImpl implements UserBo {
	@Autowired
	private ApplicationContext appContext;

	private User convertFromDto(final UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}
		User user = new User();
		user.setActive(userEntity.isActive());
		user.setAddress(userEntity.getAddress());
		user.setBirthDate(userEntity.getBirthDate());
		user.setEmail(userEntity.getEmail());
		user.setFullName(userEntity.getFullName());
		user.setPassword(userEntity.getPassword());
		user.setPhone(userEntity.getPassword());
		user.setUserName(userEntity.getUserName());
		return user;
	}

	@Override
	@Validated
	public User login(@NotBlank String userName, @NotBlank String password) throws LoginFailed {
		UserDao userDao = appContext.getBean(UserDao.class);
		UserEntity userEntity = userDao.getUser(userName);
		User user = convertFromDto(userEntity);
		if (user == null || !password.equals(user.getPassword())) {
			throw new LoginFailed();
		}

		return user;
	}

}
