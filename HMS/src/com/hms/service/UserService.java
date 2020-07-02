package com.hms.service;

import java.sql.SQLException;

import com.hms.bean.UserBean;
import com.hms.dao.UserDao;

public class UserService 
{
	UserDao userDao = new UserDao();
	
	public boolean verifyUser(UserBean user) throws SQLException, ClassNotFoundException
	{
		return userDao.verifyUser(user);
	}
}
