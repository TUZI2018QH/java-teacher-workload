package 功能;

import java.sql.*;

public class F_link
{
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "dkd110";
	private static String url = "jdbc:mysql://127.0.0.1:3306/T_info?characterEncoding=utf8";
	private static Connection conn = null;
	public Connection getconnection()
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
}
