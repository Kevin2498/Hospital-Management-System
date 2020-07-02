package com.hms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.hms.bean.UserBean;
import com.hms.util.DBConnection;

public class UserDao 
{
	
	Connection con = null;
	PreparedStatement ps=null;
	PreparedStatement ps1=null;
	ResultSet result=null;
	CallableStatement st = null;
	
	public boolean verifyUser(UserBean user) throws SQLException, ClassNotFoundException
	{
		con = DBConnection.getConnection();
		st = con.prepareCall("{call dbo.LoginProc(?,?)}");
		st.setString("Login", user.getUsername());
		st.setString("Password", user.getPassword());
		result = st.executeQuery();
		
		if(result.next())
		{
			String res = result.getString(1);
			
			if(res.equals("success"))
			{
				user.setUGPid(Integer.parseInt(result.getString("UG_Pid")));
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
		
		
	}
}
