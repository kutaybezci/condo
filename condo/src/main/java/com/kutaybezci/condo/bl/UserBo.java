package com.kutaybezci.condo.bl;

import com.kutaybezci.condo.bl.exceptions.LoginFailed;
import com.kutaybezci.condo.bl.types.User;

public interface UserBo {

	User login(String userName, String password) throws LoginFailed;

}
