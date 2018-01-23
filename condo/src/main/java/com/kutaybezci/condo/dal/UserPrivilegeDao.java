package com.kutaybezci.condo.dal;

import java.util.List;

import com.kutaybezci.condo.dal.model.UserPrivilegeEntity;

public interface UserPrivilegeDao {

	List<UserPrivilegeEntity> getUserPrivilege(long userId);

}
