package com.kutaybezci.condo.bl.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.kutaybezci.condo.bl.UserBo;
import com.kutaybezci.condo.bl.exceptions.LoginFailed;
import com.kutaybezci.condo.bl.types.Privilege;
import com.kutaybezci.condo.bl.types.User;
import com.kutaybezci.condo.dal.UserDao;
import com.kutaybezci.condo.dal.UserPrivilegeDao;
import com.kutaybezci.condo.dal.model.UserEntity;
import com.kutaybezci.condo.dal.model.UserPrivilegeEntity;

@Service
public class UserBoImpl implements UserBo {
	@Autowired
	private ApplicationContext appContext;

	private User convertFromDto(final UserEntity userEntity, final List<UserPrivilegeEntity> priviledges) {
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
		if (priviledges != null) {
			user.setPrivileges(priviledges.stream().map(x -> convertToPrivilige(x)).collect(Collectors.toSet()));
		}
		return user;
	}

	private Privilege convertToPrivilige(UserPrivilegeEntity x) {
		try {
			return Privilege.valueOf(x.getPrivilege());
		} catch (Exception ex) {
			Logger.getLogger(UserBoImpl.class);
			return null;
		}
	}

	@Override
	@Validated
	public User login(@NotBlank String userName, @NotBlank String password) throws LoginFailed {
		UserDao userDao = appContext.getBean(UserDao.class);
		UserEntity userEntity = userDao.getUser(userName);

		if (userEntity == null || !password.equals(userEntity.getPassword())) {
			throw new LoginFailed();
		}
		UserPrivilegeDao userPrivilegeDao = appContext.getBean(UserPrivilegeDao.class);
		List<UserPrivilegeEntity> privileges = userPrivilegeDao.getUserPrivilege(userEntity.getUserId());
		User user = convertFromDto(userEntity, privileges);
		return user;
	}

}
