package com.kutaybezci.condo.dal;

import com.kutaybezci.condo.dal.model.UserEntity;

public interface UserDao {

	UserEntity getUser(String userName);
	
}
