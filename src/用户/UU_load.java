package 用户;

import javax.swing.*;

import 信息处理_管理员.T_guanli_main;
import 信息处理_管理员.T_yonghu_main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UU_load 
{
	private JFrame frame;
	private JTextField t_user;
	private JPasswordField t_password;
	private JButton b_quit;
	private JButton b_user_load;
	private JButton b_guanli_load;
	private JButton b_regist ;
	private JButton b_clean;
	
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "dkd110";
	private String url = "jdbc:mysql://127.0.0.1:3306/t_info";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rst = null;
	
	public UU_load() 
	{
		frame = new JFrame("登录");
		frame.setBounds(100, 100, 450, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
//		frame.pack();
//		frame.setVisible(true);
		
		JLabel l_user = new JLabel("用户名");
		l_user.setBounds(32, 51, 54, 15);
		frame.getContentPane().add(l_user);
		
		JLabel l_password = new JLabel("密  码");
		l_password.setBounds(32, 119, 54, 15);
		frame.getContentPane().add(l_password);
		
		JLabel l_tip = new JLabel("请选择用户类型");
		l_tip.setHorizontalAlignment(SwingConstants.CENTER);
		l_tip.setBounds(32, 76, 190, 33);
		frame.getContentPane().add(l_tip);
		
		t_user = new JTextField();
		t_user.setBounds(84, 48, 138, 21);
		frame.getContentPane().add(t_user);
		t_user.setColumns(10);
		
		t_password = new JPasswordField();
		t_password.setBounds(84, 116, 138, 21);
		frame.getContentPane().add(t_password);
		t_password.setColumns(10);
		
		b_user_load = new JButton("用户登录");
		b_user_load.setBounds(249, 47, 93, 23);
		frame.getContentPane().add(b_user_load);
		
		b_guanli_load = new JButton("管理员登录");
		b_guanli_load.setFont(new Font("方正准圆_GBK", Font.PLAIN, 10));
		b_guanli_load.setBounds(249, 81, 93, 23);
		frame.getContentPane().add(b_guanli_load);
		
		b_regist = new JButton("注册");
		b_regist.setBounds(249, 114, 93, 23);
		frame.getContentPane().add(b_regist);
		
		b_clean = new JButton("清除");
		b_clean.setBounds(270, 10, 72, 23);
		frame.getContentPane().add(b_clean);
		
		b_quit = new JButton("退出");
		b_quit.setBounds(352, 10, 72, 23);
		frame.getContentPane().add(b_quit);
		
		String[] listData = new String[] {"用户","管理员"};
		JComboBox<String> box = new JComboBox<>(listData);
		
		box.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
//					System.out.println(box.getSelectedItem() );
					if(box.getSelectedItem().equals("用户"))
					{
						b_regist.setEnabled(true);
						b_guanli_load.setEnabled(false);
						new Monitor_yonghu();
						b_user_load.setEnabled(true);
					}
					else if(box.getSelectedItem().equals("管理员"))
					{
						b_regist.setEnabled(false);
						b_user_load.setEnabled(false);
						new Monitor_guanli();
						b_guanli_load.setEnabled(true);
					}
//					else if(box.getSelectedItem().equals("选择刷新，释放按钮"))
//					{
//						
//					}
//					else if(box.getSelectedItem().equals("刷新"))
//					{
//						m3();
//					}
//					else
//					{
//						load.setEnabled(false);
//						guanli.setEnabled(false);
//					}
				}
			}
		});
		
		box.setBounds(50,10,150,21);
		frame.setVisible(true);
		frame.getContentPane().add(box);
		
//		b_guanli_load = new JButton("\u7BA1\u7406\u5458\u767B\u5F55");
//		b_guanli_load.setBounds(245, 184, 93, 23);
//		frame.getContentPane().add(b_guanli_load);
		
		b_user_load.setEnabled(true);
		b_guanli_load.setEnabled(false);
		
		Monitor_yonghu m1 = new Monitor_yonghu();
		b_user_load.addActionListener(m1);
		
		Monitor_guanli m2 = new Monitor_guanli();
		b_guanli_load.addActionListener(m2);
		
		Monitor_regist m3 = new Monitor_regist();
		b_regist.addActionListener(m3);
		
		Monitor_clean m4 = new Monitor_clean();
		b_clean.addActionListener(m4);
		
		Monitor_quit m5 = new Monitor_quit();
		b_quit.addActionListener(m5);
		
	}

	class Monitor_yonghu implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
				char[] mm = t_password.getPassword();
				String pw = String.valueOf(mm); 
				
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				try {
					conn = DriverManager.getConnection(url, user, password);
					String sql = "select name,password from account where name=? and password=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,t_user.getText());
					pstmt.setString(2, pw);
					rst = pstmt.executeQuery();
					if(rst.next())
					{
						JOptionPane.showMessageDialog(null, "欢迎使用");
						new T_yonghu_main();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户名或密码错误");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
	}
	
	
	class Monitor_guanli implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
				char[] mm = t_password.getPassword();
				String pw = String.valueOf(mm); 
				
				try 
				{
					Class.forName(driver);
				} 
				catch (ClassNotFoundException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				try {
					conn = DriverManager.getConnection(url, user, password);
					String sql = "select id,pw from guanli where id=? and pw=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,t_user.getText());
					pstmt.setString(2, pw);
					rst = pstmt.executeQuery();
					if(rst.next())
					{
						JOptionPane.showMessageDialog(null, "管理员登录成功");
						new T_guanli_main();
						frame.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "用户名或密码错误");
					}
				} 
				catch (SQLException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
		}
		
	}
	
	
	class Monitor_regist implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			new U_regist();
		}
	}
	
	
	class Monitor_clean implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			t_user.setText("");
			t_password.setText("");
		}
	}
	
	
	class Monitor_quit implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.exit(0);
		}
	}

	public static void main(String[] args)
	{
		new UU_load();
	}

}
