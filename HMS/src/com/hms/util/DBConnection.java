package com.hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection 
{
	private static String Driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String url = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HMS";
	private static String username = "sa";
	private static String password = "root";
	
	public static Connection getConnection() throws ClassNotFoundException
	{
		Connection con = null;
		try
		{
			Class.forName(Driver);
			con = DriverManager.getConnection(url,username,password);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con)
	{
		if(con != null)
		{
			try
			{
				con.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(PreparedStatement ps)
	{
		if(ps != null)
		{
			try
			{
				ps.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
