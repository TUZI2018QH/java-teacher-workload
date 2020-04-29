package 用户;

import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class U_regist// extends U_load
{
	private JFrame frame;
	private Label name,password1,password2;
	private JTextField account;
	private JPasswordField pw1,pw2;
	private JButton regist,clean;
	
	public U_regist()
	{
		frame = new JFrame();
		frame.setSize(300,300);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pic\\regist.png"));
		GridLayout glt = new GridLayout(0, 1);
		frame.setLayout(glt);
		
		name = new Label("用  户  名");
		password1 = new Label("密        码");
		password2 = new Label("确认密码");
		
		account = new JTextField(12);
		pw1 = new JPasswordField(12);
		pw2 = new JPasswordField(12);
		
		regist = new JButton("注册");
		clean = new JButton("重置");
		
		JPanel pane1,pane2,pane3,pane4;
		pane1 = new JPanel();
		pane2 = new JPanel();
		pane3 = new JPanel();
		pane4 = new JPanel();
		
		pane1.add(name);
		pane1.add(account);
		pane2.add(password1);
		pane2.add(pw1);
		pane3.add(password2);
		pane3.add(pw2);
		pane4.add(regist);
		pane4.add(clean);
		
		frame.add(pane1);
		frame.add(pane2);
		frame.add(pane3);
		frame.add(pane4);
		
		frame.setTitle("注册");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		
		Monitor m1 = new Monitor();
		regist.addActionListener(m1);
	}
	
	class Monitor implements ActionListener
	{
		//@SuppressWarnings("deprecation")
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String driver = "com.mysql.jdbc.Driver";
			String user = "root";
			String password = "dkd110";
			String url = "jdbc:mysql://127.0.0.1:3306/t_info";
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rst = null;
			
			String xm = account.getText();
			char[] mm = pw1.getPassword();
			String pw = String.valueOf(mm);
//			int charge=0;		
			LinkedList<String> list = new LinkedList<>();//用于存储用户名信息
			
			try
			{
				Class.forName(driver);
			} 
			catch (ClassNotFoundException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try 
			{
				conn = DriverManager.getConnection(url, user, password);
				String sql ="select name from account";
				pstmt = conn.prepareStatement(sql);
				rst = pstmt.executeQuery();
				
				while(rst.next())
				{
					list.add(rst.getString("name"));
				}
			} 
			catch (SQLException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
				try 
				{
					rst.close();
					pstmt.close();
					conn.close();
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			Iterator<String> i = list.iterator();//获取 list 的迭代器
			int charge =0;
			while(i.hasNext())
			{
				if( i.next().equals(account.getText()) )
				{
					charge = 1;
					//break;
				}
				else if(account.getText().isEmpty())
				{
					charge = 2;
				}
				else if(pw1.getText().isEmpty())
				{
					charge = 3;
				}
				else if(pw2.getText().isEmpty())
				{
					charge = 4;
				}
				else if(pw1.getText().equals(pw2.getText()) == false)
				{
					charge = 5;
				}
				else if(pw1.getText().length() < 6)
				{
					charge = 6;
				}
				else if(pw2.getText().length() < 6)
				{
					charge = 7;
				}
			}
			
			if(charge == 1)
				JOptionPane.showMessageDialog(null, "用户已存在","温馨提示",0);
			else if(charge == 2)
				JOptionPane.showMessageDialog(null, "用户名为空","温馨提示",0);
			else if(charge == 3)
				JOptionPane.showMessageDialog(null, "密码为空","温馨提示",0);
			else if(charge == 4)
				JOptionPane.showMessageDialog(null, "请确认密码","温馨提示",0);
			else if(charge == 5)
				JOptionPane.showMessageDialog(null, "两次密码不相同","温馨提示",0);
			else if(charge == 6)
				JOptionPane.showMessageDialog(null, "密码长度小于六位","温馨提示",0);
			else if(charge == 7)
				JOptionPane.showMessageDialog(null, "密码长度小于六位","温馨提示",0);
			else
			{
				try 
				{
					Class.forName(driver);
				} 
				catch (ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try 
				{
					conn = DriverManager.getConnection(url, user, password);
					String sql = "insert into account(name,password) values(?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, xm);
					pstmt.setString(2, pw);
					pstmt.executeUpdate();
//					int row = pstmt.executeUpdate();
//					if(row != 0 && !pw.isEmpty() && !xm.isEmpty())
//					{
//						JOptionPane.showMessageDialog(null, "注册成功！", "温馨提示", 1);
//					}
//					else
//					{
////						regist_a(row);
//						JOptionPane.showMessageDialog(null, "注册失败，已存在该用户！", "温馨提示", 0);
//					}
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				finally
				{
					try 
					{
						pstmt.close();
						conn.close();
					} 
					catch (SQLException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				JOptionPane.showMessageDialog(null, "注册成功！", "温馨提示", 1);
				frame.dispose();
			}			
		}
	}
	
//	public void regist_a(int row)
//	{
//		String driver = "com.mysql.jdbc.Driver";
//		String user = "root";
//		String password = "dkd110";
//		String url = "jdbc:mysql://127.0.0.1:3306/t_info";
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rst = null;
//		ArrayList<String> a = new ArrayList<>();
//		
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			conn = DriverManager.getConnection(url, user, password);
//			String sql = "select * from account";
//			pstmt = conn.prepareStatement(sql);
//			rst = pstmt.executeQuery();
//			
//			while(rst.next())
//			{
//				a.add(rst.getString(1));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		int charge =0;
//		Iterator<String> i = a.iterator();
//		while(i.hasNext())
//		{
//			if(i.next().equals(account.getText()))
//			{
//				charge = 1;
//			}
//			else if(account.getText().isEmpty())
//			{
//				charge = 2;
//			}
//			else if(pw1.getText().isEmpty())
//			{
//				charge = 3;
//			}
//			else if(pw2.getText().isEmpty())
//			{
//				charge = 4;
//			}
//			else if(pw1.getText().equals(pw2.getText()) == false)
//			{
//				charge = 5;
//			}
//			else
//			{
//				charge = 6;
//			}
//		}
//		
//		switch(charge)
//		{
//			case 1:
//				JOptionPane.showMessageDialog(null, "用户名已存在", "温馨提示", 0);
//				break;
//			case 2:
//				JOptionPane.showMessageDialog(null, "用户名为空", "温馨提示", 0);
//				break;
//			case 3:
//				JOptionPane.showMessageDialog(null, "密码为空", "温馨提示", 0);
//				break;
//			case 4:
//				JOptionPane.showMessageDialog(null, "请确认密码", "温馨提示", 0);
//				break;
//			case 5:
//				JOptionPane.showMessageDialog(null, "两次密码不相同", "温馨提示", 0);
//				break;
//			case 6:
//				JOptionPane.showMessageDialog(null, "注册成功！", "温馨提示", 1);
//				break;			
//		}
//	}
	
//	public static void main(String[] args) 
//	{
//		new U_regist();
//	}
}
