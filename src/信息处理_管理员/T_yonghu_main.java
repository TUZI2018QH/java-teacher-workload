package 信息处理_管理员;
import javax.swing.JFrame;
import javax.swing.JMenuItem;

import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import 功能.F_link;
import 工作量信息.M_single;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
public class T_yonghu_main 
{
		private JFrame frame;
		private String driver = "com.mysql.jdbc.Driver";
		private String user = "root";
		private String password = "dkd110";
		private String url = "jdbc:mysql://127.0.0.1:3306/T_info?characterEncoding=utf8&useSSL=false";
		private Connection conn = null;
		private PreparedStatement pstmt = null;
		private ResultSet rst = null;
		
		private JPanel panel;
		private JTable table = null;
		private JScrollPane jsp = null;
		@SuppressWarnings("rawtypes")
		private Vector row,cnames;
		private JTextField textField;
		
		public T_yonghu_main()
		{
			frame = new JFrame("用户界面");
			frame.setBounds(100, 100, 766, 455);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.getContentPane().setLayout(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
//			JPanel main = new JPanel();
//			frame.add(main);
			
			JPopupMenu popupMenu = new JPopupMenu();
			addPopup(frame.getContentPane(), popupMenu);
			
			JMenu view = new JMenu("查看信息");
			view.setIcon(new ImageIcon("/com/sun/javafx/scene/web/skin/Paste_16x16_JFX.png"));
			popupMenu.add(view);
			
			JMenuItem m_xinxi = new JMenuItem("教师授课信息");
			view.add(m_xinxi);
			
			JSeparator separator_1 = new JSeparator();
			view.add(separator_1);
			
			JMenu m_work = new JMenu("工作量");
			view.add(m_work);
			
			JMenuItem m_work_single = new JMenuItem("单个教学任务总课时");
			m_work.add(m_work_single);
			
			JMenuItem m_work_lilun = new JMenuItem("理论课实验课工作量");
			m_work.add(m_work_lilun);
			
			JMenuItem m_work_design = new JMenuItem("毕业设计指导工作量");
			m_work.add(m_work_design);
			
			JMenuItem m_work_dabian = new JMenuItem("毕业答辩工作量");
			m_work.add(m_work_dabian);
			
			JMenuItem m_work_kecheng = new JMenuItem("课程设计工作量");
			m_work.add(m_work_kecheng);
			
			JMenuItem flush = new JMenuItem("刷新");
			flush.setIcon(new ImageIcon("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png"));
			popupMenu.add(flush);
			
			JSeparator separator = new JSeparator();
			popupMenu.add(separator);
			
			JMenuItem quit = new JMenuItem("退出");
			quit.setIcon(new ImageIcon("/javax/swing/plaf/metal/icons/ocean/close.gif"));
			popupMenu.add(quit);
			frame.getContentPane().setLayout(null);
			
			panel = new JPanel();
			panel.setBounds(0, 0, 646, 426);
			frame.getContentPane().add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			
			JLabel label = new JLabel("此区域操作");
			label.setHorizontalAlignment(SwingConstants.CENTER);
			label.setBounds(647, 0, 113, 426);
			frame.getContentPane().add(label);
			
			textField = new JTextField();
			textField.setBounds(647, 0, 3, 426);
			frame.getContentPane().add(textField);
			textField.setColumns(10);
			
			frame.setVisible(true);
			
			Monitor_m_xinxi m1 = new Monitor_m_xinxi();
			m_xinxi.addActionListener(m1);
			
			Monitor_m_single m2 = new Monitor_m_single();
			m_work_single.addActionListener(m2);
			
			Monitor_m_lilun m3 = new Monitor_m_lilun();
			m_work_lilun.addActionListener(m3);
			
			Monitor_m_design m4 = new Monitor_m_design();
			m_work_design.addActionListener(m4);
			
			Monitor_m_dabian m5 = new Monitor_m_dabian();
			m_work_dabian.addActionListener(m5);
			
			Monitor_m_kecheng m6 = new Monitor_m_kecheng();
			m_work_kecheng.addActionListener(m6);
			
			Monitor_flush m7 = new Monitor_flush();
			flush.addActionListener(m7);
			
			Monitor_quit m8 = new Monitor_quit();
			quit.addActionListener(m8);
		}
		
		class Monitor_m_xinxi implements ActionListener
		{
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.removeAll();
				panel.repaint();
				panel.updateUI();
				// TODO Auto-generated method stub
				cnames = new Vector();
				cnames.add("教师号");
				cnames.add("姓名");
				cnames.add("性别");
				cnames.add("职称");
				cnames.add("任课教程");
				cnames.add("班级");
				cnames.add("班级数目");
				cnames.add("理论课时");
				cnames.add("实验课时");
				row = new Vector();
				
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
					String sql = "select * from info";
					pstmt = conn.prepareStatement(sql);
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						Vector hang = new Vector();
						hang.add(rst.getInt(1));
						hang.add(rst.getString(2));
						hang.add(rst.getString(3));
						hang.add(rst.getString(4));
						hang.add(rst.getString(5));
						hang.add(rst.getString(6));
						hang.add(rst.getInt(7));
						hang.add(rst.getFloat(8));
						hang.add(rst.getFloat(9));
						row.add(hang);
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
					{				//使用完毕 关闭连接
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
				
				table = new JTable(row,cnames);
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(SwingConstants.CENTER);
				table.setDefaultRenderer(Object.class, tcr);
				table.setEnabled(false);
				jsp = new JScrollPane(table);
				jsp.setEnabled(false);
				jsp.setBounds(10, 10, 736, 350);
				panel.add(jsp,BorderLayout.CENTER);		
			}
		}
		
		class Monitor_m_single implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.removeAll();
				panel.repaint();
				panel.updateUI();
				
				JTextArea single_view = new JTextArea();
				single_view = new JTextArea();
				single_view.setBounds(10,10,200,200);
				single_view.setEditable(false);
				panel.add(single_view);
				
				JTextField single_news;
				single_news = new JTextField();
				single_news.setEditable(false);
				single_news.setFont(new Font("宋体", Font.PLAIN, 19));
				single_news.setHorizontalAlignment(SwingConstants.CENTER);
				single_news.setBounds(44, 157, 213, 80);
				panel.add(single_news,BorderLayout.SOUTH);
//				single_news.setColumns(10);
				
				// TODO Auto-generated method stub
				LinkedList<M_single> a = new LinkedList<>();
				LinkedList<Float> a1 = new LinkedList<>();
				M_single s = null;//需要使用到 对应信息类  创建新的信息接收，再从中读取
				
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					String sql = "select * from single";
					pstmt = conn.prepareStatement(sql);
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						s = new M_single(rst.getString(1),rst.getString(2),rst.getFloat(3));
						a.add(s);
						a1.add(rst.getFloat(3));
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally
				{
					try {
						rst.close();
						pstmt.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				single_view.setText("");//信息框在显示信息前后清除一下框内内容
				for(int i = 0;i<a.size();i++)
				{
					if(a.get(i) == null)
						break;
					else
						single_view.setText(single_view.getText() + a.get(i) + System.getProperty("line.separator") );
				}//文本域中的内容追加，这样才能使文本的内容不会被覆盖 导致输出不全，‘System.getProperty("line.separator")’ 在文本域中输出起换行作用
				
				float sum = 0;//  sum 用于 统计 总课时
				for(int i = 0;i<a.size();i++)
				{
					if(a1.get(i) == null) // 如果 list 中的内容指向了空时，则结束循环
						break;
					else
						sum = sum + a1.get(i);//否则 将得到的 课时数累加起来 得到总课时
				}
				single_news.setText("总课时："+sum+"");//将 计算出的 总课时数 显示在 消息框中
			}
		}
		
		class Monitor_m_lilun implements ActionListener
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) 
			{			
				panel.removeAll();
				panel.repaint();
				panel.updateUI();
				// TODO Auto-generated method stub
				cnames = new Vector<>();
				cnames.add("课程名称");
				cnames.add("专业班级");
				cnames.add("学时");
				cnames.add("课程系数");
				cnames.add("合班系数");
				cnames.add("环节系数");
				cnames.add("工作量");
				
				row = new Vector<>();
				
		
				F_link link = new F_link();
				Connection conn = link.getconnection();
				
				try 
				{
					String sql = "select * from theory_experimental";
					pstmt = conn.prepareStatement(sql);
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						Vector line = new Vector<>();
						line.add(rst.getString(1));
						line.add(rst.getString(2));
						line.add(rst.getString(3));
						line.add(rst.getString(4));
						line.add(rst.getString(5));
						line.add(rst.getString(6));
						line.add(rst.getString(7));
						row.add(line);
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
				
				table = new JTable(row,cnames);
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(SwingConstants.CENTER);
				table.setDefaultRenderer(Object.class,tcr);
				table.setEnabled(false);
				jsp = new JScrollPane(table);
				jsp.setSize(500,500);
				
				panel.add(jsp);

			}
		}
		
		class Monitor_m_design implements ActionListener
		{
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.removeAll();
				panel.repaint();
				panel.updateUI();
				// TODO Auto-generated method stub
				Vector row,cnames;
				
				cnames = new Vector<>();
				cnames.add("任课教程");
				cnames.add("专业班级");
				cnames.add("周数");
				cnames.add("指导学生系数");
				cnames.add("周学时数");
				cnames.add("专业系数");
				cnames.add("环节系数");
				cnames.add("工作量");
				
				row = new Vector<>();
				
				try {
					Class.forName(driver);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					conn = DriverManager.getConnection(url, user, password);
					String sql = "select * from design";
					pstmt = conn.prepareStatement(sql);
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						Vector line = new Vector<>();
						line.add(rst.getString(1));
						line.add(rst.getString(2));
						line.add(rst.getString(3));
						line.add(rst.getString(4));
						line.add(rst.getString(5));
						line.add(rst.getString(6));
						line.add(rst.getString(7));
						line.add(rst.getString(8));
						row.add(line);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				table = new JTable(row,cnames);
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(SwingConstants.CENTER);
				table.setDefaultRenderer(Object.class, tcr);	
				table.setEnabled(false);
				jsp = new JScrollPane(table);	
				jsp.setSize(500,500);
				panel.add(jsp);
			}
		}
		
		class Monitor_m_dabian implements ActionListener
		{
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.removeAll();
				panel.repaint();
				panel.updateUI();
				Vector row,cnames;			//Vector 动态数组 
				cnames = new Vector<>();
				cnames.add("专业班级");
				cnames.add("学生人数");
				cnames.add("周数");
				cnames.add("环节系数");
				cnames.add("工作量");			
				row = new Vector<>();			
				F_link link = new F_link();
				conn = link.getconnection();
				String sql = "select * from answer";
				try {
					pstmt = conn.prepareStatement(sql);
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						Vector line = new Vector<>();
						line.add(rst.getString(1));
						line.add(rst.getString(2));
						line.add(rst.getString(3));
						line.add(rst.getString(4));
						line.add(rst.getString(5));				
						row.add(line);
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
				
				table = new JTable(row,cnames);
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(SwingConstants.CENTER);
				table.setDefaultRenderer(Object.class, tcr);
				table.setEnabled(false);
				jsp = new JScrollPane(table);
				jsp.setSize(500,500);
				panel.add(jsp);
				// TODO Auto-generated method stub
				
			}
		}
		
		class Monitor_m_kecheng implements ActionListener
		{
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				panel.removeAll();
				panel.repaint();
				panel.updateUI();
				// TODO Auto-generated method stub
				Vector row,cnames;
				
				cnames = new Vector<>();
				cnames.add("专业班级");
				cnames.add("学生人数");
				cnames.add("周数");
				cnames.add("学时数");
				cnames.add("环节系数");
				cnames.add("工作量");
				
				row = new Vector<>();
				
				
				F_link link = new F_link();
				conn = link.getconnection();
				
				try 
				{
					String sql = "select * from graduate_design";
					pstmt = conn.prepareStatement(sql);
					rst = pstmt.executeQuery();
					while(rst.next())
					{
						Vector line = new Vector<>();
						line.add(rst.getString(1));
						line.add(rst.getString(2));
						line.add(rst.getString(3));
						line.add(rst.getString(4));
						line.add(rst.getString(5));
						line.add(rst.getString(6));
						row.add(line);
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
				
				table = new JTable(row,cnames);
				DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
				tcr.setHorizontalAlignment(SwingConstants.CENTER);
				table.setDefaultRenderer(Object.class,tcr);
				table.setEnabled(false);
				jsp = new JScrollPane(table);
				jsp.setSize(500,500);
				panel.add(jsp);
			}
		}
		
		class Monitor_flush implements ActionListener
		{
			public void actionPerformed(ActionEvent e) 
			{
				new T_yonghu_main();
				frame.dispose();
			} 
		}
		
		class Monitor_quit implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// TODO Auto-generated method stub
				System.exit(0);
			}
		}
		
//		public static void main(String[] args) 
//		{
//			new eeeee();
//		}
		
		private static void addPopup(Component component, final JPopupMenu popup) {
			component.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}
				public void mouseReleased(MouseEvent e) {
					if (e.isPopupTrigger()) {
						showMenu(e);
					}
				}
				private void showMenu(MouseEvent e) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			});
		}
	

}
