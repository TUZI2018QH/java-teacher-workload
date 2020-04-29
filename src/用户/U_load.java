package 用户;
import java.awt.*;
import javax.swing.*;

import 信息处理_管理员.T_guanli_main;

import java.awt.event.*;
import java.sql.*;
public class U_load 
{
	private JFrame frame;
	private Label user_name,user_pw;
	private JTextField name_text;
	private JPasswordField pw_text;
	private JButton regist,clean,load,quit;
	
	public U_load()
	{
		frame = new JFrame("登录");
		GridLayout glt = new GridLayout(0, 1);
		frame.setLayout(glt);
		
		JPanel pane1,pane2,pane3,pane4;
		pane1 = new JPanel();
		pane2 = new JPanel();
		pane3 = new JPanel();
		pane4 = new JPanel();
		pane4.setLayout(null);
		
		user_name = new Label("用户名");
		user_pw = new Label("密    码");
		
		name_text = new JTextField(12);
		pw_text = new JPasswordField(12);
		name_text.setToolTipText("输入用户名");
		name_text.setSelectedTextColor(Color.GREEN);
		pw_text.setToolTipText("输入密码");
		pw_text.setSelectedTextColor(Color.GREEN);
		
		regist = new JButton("注册");
		clean = new JButton("清除");
		load = new JButton("登录");
		quit = new JButton("退出");
		
		pane1.add(user_name);
		pane1.add(name_text);
		pane1.add(regist);
		pane2.add(user_pw);
		pane2.add(pw_text);
		pane2.add(clean);
		pane3.add(load);
		pane3.add(quit);
		
		frame.add(pane1);
		frame.add(pane2);
		frame.add(pane3);
		
		frame.setSize(300, 300);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pic\\load.png"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Monitor m1= new Monitor();
		load.addActionListener(m1);
		regist.addActionListener(m1);
		clean.addActionListener(m1);
		quit.addActionListener(m1);
	}
	
	class Monitor implements ActionListener
	{
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
			
			String xm = name_text.getText();//获取登录框中的用户名和密码
			char[] mm = pw_text.getPassword();
			String pw = String.valueOf(mm); 
			
			JButton button = (JButton)e.getSource();
			
			try
			{
				Class.forName(driver);
			} 
			catch (ClassNotFoundException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(button == load)
			{
				try 
				{
					conn = DriverManager.getConnection(url, user, password);
					String sql ="SELECT name,password from account where name=? and password=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,xm);
					pstmt.setString(2,pw);
					rst = pstmt.executeQuery();
					
					if(rst.next())
					{
						JOptionPane.showMessageDialog(null, "登陆成功，欢迎使用！", "温馨提示", 1);
						new T_guanli_main();
						frame.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新输入！", "温馨提示", 0);
					}
					name_text.setText("");
					pw_text.setText("");
					rst.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(button == regist)
			{
				new U_regist();
			}
			else if(button == clean)
			{
				name_text.setText("");
				pw_text.setText("");
			}
			else if(button == quit)
			{
				System.exit(0);
			}
		}
	}
}
