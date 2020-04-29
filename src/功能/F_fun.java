package 功能;

import java.sql.*;
import java.util.LinkedList;
import 教师信息.T_info;

public class F_fun 
{
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "dkd110";
	private String url = "jdbc:mysql://127.0.0.1:3306/t_info";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rst = null;
	
	
	public void add(T_info m)//添加信息
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn =DriverManager.getConnection(url, user, password);
			String sql = "insert into info values(?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, m.getT_id());
			pstmt.setString(2, m.getT_name());
			pstmt.setString(3, m.getT_gender());
			pstmt.setString(4, m.getT_jobtitle());
			pstmt.setString(5, m.getT_course());
			pstmt.setString(6, m.getT_classes());
			pstmt.setInt(7, m.getT_classnum());
			pstmt.setFloat(8, m.getT_theory());
			pstmt.setFloat(9, m.getT_experimental());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	///////////////////////////////////////////////////////////////
	public void delete(int T_id)//删除信息
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn =DriverManager.getConnection(url, user, password);
			String sql = "delete from info where T_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, T_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	
	//////////////////////////////////////////////////////////////////
	public void update(T_info m,int id)//更新
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "update info set T_id=?, T_name=?, T_gender=?,"+
						"T_jobtitle=?, T_course=?, T_classes=?,"+
						"T_classnum=?, T_theory=?, T_experimental=? where T_id="+id;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, m.getT_id());
			pstmt.setString(2, m.getT_name());
			pstmt.setString(3, m.getT_gender());
			pstmt.setString(4, m.getT_jobtitle());
			pstmt.setString(5, m.getT_course());
			pstmt.setString(6, m.getT_classes());
			pstmt.setInt(7, m.getT_classnum());
			pstmt.setFloat(8, m.getT_theory());
			pstmt.setFloat(9, m.getT_experimental());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	public T_info get(int id)//查询书籍
	{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		T_info m = null;
		try 
		{
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from info where T_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rst = pstmt.executeQuery();
			if(rst.next())
			{
				m = new T_info(
						rst.getInt(1),rst.getString(2),rst.getString(3),
						rst.getString(4),rst.getString(5),rst.getString(6),
						rst.getInt(7),rst.getFloat(8),rst.getFloat(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				rst.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return m;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	public LinkedList<T_info> getall()//查询所有书籍
	{
		LinkedList<T_info> ms = new LinkedList<>();
		T_info m = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select * from info";
			pstmt = conn.prepareStatement(sql);
			rst = pstmt.executeQuery();
			while(rst.next())
			{
				m = new T_info(rst.getInt(1),rst.getString(2),rst.getString(3),
						rst.getString(4),rst.getString(5),rst.getString(6),
						rst.getInt(7),rst.getFloat(8),rst.getFloat(9));
				ms.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				rst.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ms;
	}
}
