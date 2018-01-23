package com.kutaybezci.condo.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.kutaybezci.condo.bl.UserBo;
import com.kutaybezci.condo.bl.exceptions.LoginFailed;
import com.kutaybezci.condo.bl.types.User;

@ShellComponent
public class UserCommand {

	@Autowired
	private UserBo userBo;
	private User user;

	@ShellMethod("For login supply username password")
	public String login(String userName, String password) throws LoginFailed {
		this.user = userBo.login(userName, password);
		return String.format("Logged on as: %s", user.getUserName());
	}
}
