package 信息处理_管理员;
/**
 * 此页面集合了主界面中所有操作，因此显得十分冗长
 * 第一次编辑：2018-12-18-19：15
 * 最后一次编辑：2018-12-
 * BY：dkd
 * */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import 功能.F_fun;
import 功能.F_link;
import 工作量信息.M_single;
import 教师信息.T_info;

import java.awt.event.*;
import java.sql.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class T_guanli_main 
{
	private JFrame frame;
	private JTabbedPane tbp = new JTabbedPane(JTabbedPane.TOP);//创建卡片面板
	private JPanel course_message,deal_message,work_message;//三个面板分别为 教师授课信息、信息处理、工作量处理
	
	private JTextField id;
	private JTextField name;
	private JTextField gender;
	private JTextField jobtitle;
	private JTextField course;
	private JTextField classes;
	private JTextField classnum;
	private JTextField theory;
	private JTextField experimental;
	
	private JTextField add_id;
	private JTextField add_name;
	private JTextField add_gender;
	private JTextField add_jobtitle;
	private JTextField add_course;
	private JTextField add_classes;
	private JTextField add_classnum;
	private JTextField add_theory;
	private JTextField add_experimental;
	private JTextField show;
	
	private JTextField single_course_name;
	private JTextField single_classes;
	private JTextField single_hour;
	private JTextField single_news;
	private JTextArea single_view;
	
	private JTextField course_name;
	private JTextField course_classes;
	private JTextField course_weeks;
	private JTextField course_zhidao;
	private JTextField course_week_hour;
	private JTextField course_zhuanye;
	private JTextField course_huanjie;
	private JTextField course_news;
	private JTable course_view;
	private JScrollPane jsp_course;
	
	private JTextField theory_name;
	private JTextField theory_classes;
	private JTextField theory_xueshi;
	private JTextField theory_kecheng;
	private JTextField theory_heban;
	private JTextField theory_huanjie;
	private JTextField theory_red_light;
	private JTextField theory_green_light;
	private JTextField theory_news;
	private JTable theory_view;
	private JScrollPane jsp_theory;
	
	private JTextField graduate_classes;
	private JTextField graduate_stu_num;
	private JTextField graduate_weeks;
	private JTextField graduate_week_hour;
	private JTextField graduate_huanjie;
	private JTextField graduate_news;
	private JTable graduate_view;
	private JScrollPane jsp_graduate;

	private JTextField answer_classes;
	private JTextField answer_stu_num;
	private JTextField answer_weeks;
	private JTextField answer_huanjie;
	private JTextField answer_news;
	private JTable answer_view;
	private JScrollPane jsp_answer;
	
	private String driver = "com.mysql.jdbc.Driver";
	private String user = "root";
	private String password = "dkd110";
	private String url = "jdbc:mysql://127.0.0.1:3306/T_info?characterEncoding=utf8&useSSL=false";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rst = null;
	
//	private JPanel view,add,delete,amend,search;//此处面板隶属 pane1面板
	@SuppressWarnings({ "unchecked", "rawtypes" })
	

	public T_guanli_main()
	{
		frame = new JFrame("教师工作量管理");
		frame.setSize(766, 455);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("pic\\mani.png"));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		tbp.setBounds(0,0,732,425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(tbp);
		

		
		
///////////////////////////////////////////////////////////////////		
		course_message = new JPanel();//主界面的 信息显示  使用动态数组 加 滚动面板
		course_message.setBounds(0,0,635,400);
		tbp.addTab("教师授课信息", null, course_message, null);
		BorderLayout blt = new BorderLayout();
		course_message.setLayout(blt);
		JButton flush = new JButton("刷新"); 
		course_message.add(flush,BorderLayout.SOUTH);
		
		JTable table = null;
		JScrollPane jsp = null;
		Vector row,cnames;
		
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
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{				//使用完毕 关闭连接
				rst.close();
				pstmt.close();
				conn.close();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		table = new JTable(row,cnames);
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setDefaultRenderer(Object.class, tcr);
		jsp = new JScrollPane(table);
		jsp.setEnabled(false);
		course_message.add(jsp);
///////////////////////////////////////////////////////////////////		
		
	
		
////////////////////////////////////////////////////////		
		deal_message = new JPanel();
		tbp.addTab("信息处理", deal_message);
		deal_message.setLayout(null);
		
		JTabbedPane deal_main = new JTabbedPane(JTabbedPane.LEFT);//使用左边卡片模式
		deal_main.setBounds(0, 0, 635, 400);
		deal_message.add(deal_main);
/////////////////////////////////////////////////////////
		

////////////////////////////////////////////////////////////		
		JPanel add = new JPanel();
		deal_main.addTab("添加&删除", null, add, null);
		add.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("教师号");
		lblNewLabel.setBounds(131, 40, 54, 15);
		add.add(lblNewLabel);
		
		JLabel label_10 = new JLabel("姓名");
		label_10.setBounds(131, 65, 54, 15);
		add.add(label_10);
		
		JLabel label_11 = new JLabel("性别");
		label_11.setBounds(131, 90, 54, 15);
		add.add(label_11);
		
		JLabel label_12 = new JLabel("职称");
		label_12.setBounds(131, 115, 54, 15);
		add.add(label_12);
		
		JLabel label_13 = new JLabel("任课教程");
		label_13.setBounds(131, 140, 54, 15);
		add.add(label_13);
		
		JLabel label_14 = new JLabel("班级");
		label_14.setBounds(131, 165, 54, 15);
		add.add(label_14);
		
		JLabel label_15 = new JLabel("班级数目");
		label_15.setBounds(131, 190, 54, 15);
		add.add(label_15);
		
		JLabel label_16 = new JLabel("理论课时");
		label_16.setBounds(131, 215, 54, 15);
		add.add(label_16);
		
		JLabel label_17 = new JLabel("实验课时");
		label_17.setBounds(131, 240, 54, 15);
		add.add(label_17);
		
		add_id = new JTextField();
		add_id.setHorizontalAlignment(SwingConstants.CENTER);
		add_id.setBounds(195, 37, 247, 21);
		add.add(add_id);
		add_id.setColumns(10);
		
		add_name = new JTextField();
		add_name.setHorizontalAlignment(SwingConstants.CENTER);
		add_name.setColumns(10);
		add_name.setBounds(195, 62, 247, 21);
		add.add(add_name);
		
		add_gender = new JTextField();
		add_gender.setHorizontalAlignment(SwingConstants.CENTER);
		add_gender.setColumns(10);
		add_gender.setBounds(195, 87, 247, 21);
		add.add(add_gender);
		
		add_jobtitle = new JTextField();
		add_jobtitle.setHorizontalAlignment(SwingConstants.CENTER);
		add_jobtitle.setColumns(10);
		add_jobtitle.setBounds(195, 112, 247, 21);
		add.add(add_jobtitle);
		
		add_course = new JTextField();
		add_course.setHorizontalAlignment(SwingConstants.CENTER);
		add_course.setColumns(10);
		add_course.setBounds(195, 137, 247, 21);
		add.add(add_course);
		
		add_classes = new JTextField();
		add_classes.setHorizontalAlignment(SwingConstants.CENTER);
		add_classes.setColumns(10);
		add_classes.setBounds(195, 162, 247, 21);
		add.add(add_classes);
		
		add_classnum = new JTextField();
		add_classnum.setHorizontalAlignment(SwingConstants.CENTER);
		add_classnum.setColumns(10);
		add_classnum.setBounds(195, 187, 247, 21);
		add.add(add_classnum);
		
		add_theory = new JTextField();
		add_theory.setHorizontalAlignment(SwingConstants.CENTER);
		add_theory.setColumns(10);
		add_theory.setBounds(195, 212, 247, 21);
		add.add(add_theory);
		
		add_experimental = new JTextField();
		add_experimental.setHorizontalAlignment(SwingConstants.CENTER);
		add_experimental.setColumns(10);
		add_experimental.setBounds(195, 237, 247, 21);
		add.add(add_experimental);
		
		JButton add_tianjia = new JButton("添加");
		add_tianjia.setBounds(131, 343, 93, 23);
		add.add(add_tianjia);
		
		JButton add_chongzhi = new JButton("重置");
		add_chongzhi.setBounds(242, 343, 93, 23);
		add.add(add_chongzhi);
		
		JButton add_delete = new JButton("删除");
		add_delete.setBounds(349, 343, 93, 23);
		add.add(add_delete);
		
		show = new JTextField();
		show.setText("NWES");
		show.setHorizontalAlignment(SwingConstants.CENTER);
		show.setBounds(131, 268, 311, 21);
		show.setEditable(false);
		show.setBackground(Color.PINK);
		add.add(show);
		show.setColumns(10);
//////////////////////////////////////////////////////////		
		
		
		
	
		
		
///////////////////////////////////////////////////////		
		JPanel amend = new JPanel();
		deal_main.addTab("查找&修改", null, amend, null);
		amend.setLayout(null);
		
		JLabel lblNewLabel_0 = new JLabel("教师号");
		lblNewLabel_0.setBounds(131, 40, 54, 15);
		amend.add(lblNewLabel_0);
		
		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(131, 65, 54, 15);
		amend.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setBounds(131, 90, 54, 15);
		amend.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("职称");
		lblNewLabel_3.setBounds(131, 115, 54, 15);
		amend.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("任课教程");
		lblNewLabel_4.setBounds(131, 140, 54, 15);
		amend.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("班级");
		lblNewLabel_5.setBounds(131, 165, 54, 15);
		amend.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("班级数目");
		lblNewLabel_6.setBounds(131, 190, 54, 15);
		amend.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("理论课时");
		lblNewLabel_7.setBounds(131, 215, 54, 15);
		amend.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("实验课时");
		lblNewLabel_8.setBounds(131, 240, 54, 15);
		amend.add(lblNewLabel_8);
		
		id = new JTextField();
		id.setBounds(195, 37, 247, 21);
		id.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(195, 62, 247, 21);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(name);
		name.setColumns(10);
		
		gender = new JTextField();
		gender.setBounds(195, 87, 247, 21);
		gender.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(gender);
		gender.setColumns(10);
		
		jobtitle = new JTextField();
		jobtitle.setBounds(195, 112, 247, 21);
		jobtitle.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(jobtitle);
		jobtitle.setColumns(10);
		
		course = new JTextField();
		course.setBounds(195, 137, 247, 21);
		course.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(course);
		course.setColumns(10);
		
		classes = new JTextField();
		classes.setBounds(195, 162, 247, 21);
		classes.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(classes);
		classes.setColumns(10);
		
		classnum = new JTextField();
		classnum.setBounds(195, 187, 247, 21);
		classnum.setHorizontalAlignment(SwingConstants.CENTER);
		amend.add(classnum);
		classnum.setColumns(10);
		
		theory = new JTextField();
		theory.setColumns(10);
		theory.setHorizontalAlignment(SwingConstants.CENTER);
		theory.setBounds(195, 212, 247, 21);
		amend.add(theory);
		
		experimental = new JTextField();
		experimental.setColumns(10);
		experimental.setHorizontalAlignment(SwingConstants.CENTER);
		experimental.setBounds(195, 237, 247, 21);
		amend.add(experimental);
		
		JButton add_queren = new JButton("查找");
		add_queren.setBounds(131, 323, 93, 43);
		amend.add(add_queren);
		
		JButton add_amend = new JButton("修改");
		add_amend.setBounds(349, 323, 93, 43);
		amend.add(add_amend);
		
		JButton amend_chongzhi = new JButton("重置");
		amend_chongzhi.setBounds(241, 323, 93, 43);
		amend.add(amend_chongzhi);
		
		JLabel deal_background_1 = new JLabel("");
		deal_background_1.setIcon(new ImageIcon("pic\\t_info_back.gif"));
		deal_background_1.setBounds(380, 0, 347, 400);
		deal_message.add(deal_background_1);
		
		JLabel deal_background_2 = new JLabel("");
		deal_background_2.setIcon(new ImageIcon("pic\\t_info_back.gif"));
		deal_background_2.setBounds(-55, 0, 737, 400);
		deal_message.add(deal_background_2);
////////////////////////////////////////////////////////		
		
		
		
		
		
		
////////////////////////////////////////////////////////		
		work_message = new JPanel();
		tbp.addTab("工作量处理", work_message);
		work_message.setLayout(null);
		
		JTabbedPane work_main = new JTabbedPane(JTabbedPane.TOP);
		work_main.setBounds(65, 0, 632, 396);
		work_message.add(work_main);
//////////////////////////////////////////////////////////		
		
		
/////////////////////////////////////////////////////////		
		JPanel single = new JPanel();
		work_main.addTab("单个教学任务总课时",single);
		single.setLayout(null);
		
		JLabel label_21course_name = new JLabel("课程名称");
		label_21course_name.setBounds(44, 36, 54, 15);
		single.add(label_21course_name);
		
		JLabel label_22_classes = new JLabel("班    级");
		label_22_classes.setBounds(44, 61, 54, 15);
		single.add(label_22_classes);
		
		JLabel label_23_hour = new JLabel("课    时");
		label_23_hour.setBounds(44, 86, 54, 15);
		single.add(label_23_hour);
		
		JLabel label_24_tishi = new JLabel("删除后使用计算刷新");
		label_24_tishi.setBounds(44, 247, 213, 15);
		single.add(label_24_tishi);
		
		single_course_name = new JTextField();
		single_course_name.setBounds(108, 33, 149, 21);
		single.add(single_course_name);
		single_course_name.setColumns(10);
		
		single_classes = new JTextField();
		single_classes.setBounds(108, 58, 149, 21);
		single.add(single_classes);
		single_classes.setColumns(10);
		
		single_hour = new JTextField();
		single_hour.setColumns(10);
		single_hour.setBounds(108, 83, 149, 21);
		single.add(single_hour);
		
		single_news = new JTextField();
		single_news.setEditable(false);
		single_news.setFont(new Font("宋体", Font.PLAIN, 19));
		single_news.setHorizontalAlignment(SwingConstants.CENTER);
		single_news.setBounds(44, 157, 213, 80);
		single.add(single_news);
		single_news.setColumns(10);
		
		JScrollPane jsp2 = new JScrollPane();
		jsp2.setBounds(289, 36, 270, 199);
		single.add(jsp2);
		
		single_view = new JTextArea();
		single_view.setEditable(false);
		jsp2.setViewportView(single_view);
		
		JButton single_add = new JButton("添加");
		single_add.setBounds(44, 124, 64, 23);
		single.add(single_add);
		
		JButton single_calc = new JButton("计算");
		single_calc.setBounds(118, 124, 64, 23);
		single.add(single_calc);
		
		JButton single_delete = new JButton("删除");
		single_delete.setBounds(193, 124, 64, 23);
		single.add(single_delete);
		
//////////////////////////////////////////////////////////////
		
		
		
//////////////////////////////////////////////////////////////		
		JPanel all = new JPanel();
		work_main.addTab("nothing",all);
		all.setLayout(null);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("pic\\all_back.gif"));
		label_1.setBounds(10, 0, 627, 350);
		all.add(label_1);
//////////////////////////////////////////////////////////////	
		
		
//////////////////////////////////////////////////////////////		
		JPanel design = new JPanel();
		work_main.addTab("课程设计工作量", design);
		design.setLayout(null);
		
		JLabel course_lanme = new JLabel("课 程 名 称");
		course_lanme.setBounds(29, 13, 72, 15);
		design.add(course_lanme);
		
		JLabel course_lclasses = new JLabel("专 业 班 级");
		course_lclasses.setHorizontalAlignment(SwingConstants.LEFT);
		course_lclasses.setBounds(321, 13, 72, 15);
		design.add(course_lclasses);
		
		JLabel course_lweeks = new JLabel("周       数");
		course_lweeks.setHorizontalAlignment(SwingConstants.LEFT);
		course_lweeks.setBounds(29, 41, 72, 15);
		design.add(course_lweeks);
		
		JLabel course_lzhidao = new JLabel("指导学生系数");
		course_lzhidao.setBounds(321, 41, 83, 15);
		design.add(course_lzhidao);
		
		JLabel course_lweek_hour = new JLabel("周 学 时 数");
		course_lweek_hour.setBounds(29, 69, 72, 15);
		design.add(course_lweek_hour);
		
		JLabel course_lzhuanye = new JLabel("专 业 系 数");
		course_lzhuanye.setBounds(321, 69, 72, 15);
		design.add(course_lzhuanye);
		
		JLabel course_lhuanjie = new JLabel("环 节 系 数");
		course_lhuanjie.setBounds(29, 97, 72, 15);
		design.add(course_lhuanjie);
		
		course_name = new JTextField();
		course_name.setBounds(111, 10, 157, 21);
		design.add(course_name);
		course_name.setColumns(10);
		
		course_classes = new JTextField();
		course_classes.setColumns(10);
		course_classes.setBounds(403, 10, 157, 21);
		design.add(course_classes);
		
		course_weeks = new JTextField();
		course_weeks.setColumns(10);
		course_weeks.setBounds(111, 38, 157, 21);
		design.add(course_weeks);
		
		course_zhidao = new JTextField();
		course_zhidao.setColumns(10);
		course_zhidao.setBounds(403, 38, 157, 21);
		design.add(course_zhidao);
		
		course_week_hour = new JTextField();
		course_week_hour.setColumns(10);
		course_week_hour.setBounds(111, 66, 157, 21);
		design.add(course_week_hour);
		
		course_zhuanye = new JTextField();
		course_zhuanye.setColumns(10);
		course_zhuanye.setBounds(403, 66, 157, 21);
		design.add(course_zhuanye);
		
		course_huanjie = new JTextField();
		course_huanjie.setColumns(10);
		course_huanjie.setBounds(111, 94, 157, 21);
		design.add(course_huanjie);
		
		course_news = new JTextField();
		course_news.setEditable(false);
		course_news.setBackground(new Color(51, 255, 204));
		course_news.setForeground(Color.RED);
		course_news.setHorizontalAlignment(SwingConstants.CENTER);
		course_news.setFont(new Font("宋体", Font.PLAIN, 20));
		course_news.setBounds(321, 93, 239, 22);
		design.add(course_news);
		course_news.setColumns(10);
		
		jsp_course = new JScrollPane();
		jsp_course.setBounds(10, 125, 607, 134);
		design.add(jsp_course);
		

		
		JButton course_add = new JButton("添加");
		course_add.setBounds(123, 295, 93, 45);
		design.add(course_add);
		
		JButton course_calc = new JButton("计算");
		course_calc.setBounds(268, 295, 93, 45);
		design.add(course_calc);
		
		JButton course_delete = new JButton("删除");
		course_delete.setBounds(421, 295, 93, 45);
		design.add(course_delete);
///////////////////////////////////////////////////////////////		
		

//////////////////////////////////////////////////////////////		
		JPanel thoery = new JPanel();
		work_main.addTab("理论课实验课工作量", thoery);
		thoery.setLayout(null);
		
		JLabel theory_lname = new JLabel("课程名称");
		theory_lname.setBounds(53, 35, 54, 15);
		thoery.add(theory_lname);
		
		JLabel theory_lclasses = new JLabel("专业班级");
		theory_lclasses.setBounds(53, 60, 54, 15);
		thoery.add(theory_lclasses);
		
		JLabel theory_lxueshi = new JLabel("学    时");
		theory_lxueshi.setBounds(346, 32, 54, 15);
		thoery.add(theory_lxueshi);
		
		JLabel theory_lkecheng = new JLabel("课程系数");
		theory_lkecheng.setBounds(346, 57, 54, 15);
		thoery.add(theory_lkecheng);
		
		JLabel theory_lheban = new JLabel("合班系数");
		theory_lheban.setBounds(53, 88, 54, 15);
		thoery.add(theory_lheban);
		
		JLabel theory_lhuanjie = new JLabel("环节系数");
		theory_lhuanjie.setBounds(346, 85, 54, 15);
		thoery.add(theory_lhuanjie);
		
		theory_name = new JTextField();
		theory_name.setBounds(117, 32, 178, 21);
		thoery.add(theory_name);
		theory_name.setColumns(10);
		
		theory_classes = new JTextField();
		theory_classes.setColumns(10);
		theory_classes.setBounds(117, 57, 178, 21);
		thoery.add(theory_classes);
		
		theory_xueshi = new JTextField();
		theory_xueshi.setColumns(10);
		theory_xueshi.setBounds(410, 29, 178, 21);
		thoery.add(theory_xueshi);
		
		theory_kecheng = new JTextField();
		theory_kecheng.setColumns(10);
		theory_kecheng.setBounds(410, 54, 178, 21);
		thoery.add(theory_kecheng);
		
		theory_heban = new JTextField();
		theory_heban.setColumns(10);
		theory_heban.setBounds(117, 85, 178, 21);
		thoery.add(theory_heban);
		
		theory_huanjie = new JTextField();
		theory_huanjie.setColumns(10);
		theory_huanjie.setBounds(410, 82, 178, 21);
		thoery.add(theory_huanjie);
		
		theory_red_light = new JTextField();
		theory_red_light.setBounds(346, 110, 117, 21);
		theory_red_light.setEditable(false);
		thoery.add(theory_red_light);
		theory_red_light.setColumns(10);
		
		theory_green_light = new JTextField();
		theory_green_light.setColumns(10);
		theory_green_light.setEditable(false);
		theory_green_light.setBounds(471, 110, 117, 21);
		thoery.add(theory_green_light);
		
		theory_news = new JTextField();
		theory_news.setBounds(53, 113, 242, 21);
		theory_news.setEditable(false);
		theory_news.setHorizontalAlignment(SwingConstants.CENTER);
		thoery.add(theory_news);
		theory_news.setColumns(10);
		
		JButton theory_add = new JButton("添加");
		theory_add.setBounds(527, 141, 93, 56);
		thoery.add(theory_add);
		
		JButton theory_calc = new JButton("计算");
		theory_calc.setBounds(527, 207, 93, 67);
		theory_calc.setToolTipText("计算并刷新");
		thoery.add(theory_calc);
		
		JButton theory_delete = new JButton("删除");
		theory_delete.setBounds(527, 284, 93, 56);
		thoery.add(theory_delete);
		
		jsp_theory = new JScrollPane();
		jsp_theory.setBounds(22, 141, 495, 199);
		thoery.add(jsp_theory);
//////////////////////////////////////////////////////////////
		
		
//////////////////////////////////////////////////////////////		
		JPanel graduate_design = new JPanel();
		work_main.addTab("毕业设计指导工作量", graduate_design);
		graduate_design.setLayout(null);
		
		JLabel graduate_lclasses = new JLabel("专业班级");
		graduate_lclasses.setForeground(Color.WHITE);
		graduate_lclasses.setBounds(27, 24, 54, 15);
		graduate_design.add(graduate_lclasses);
		
		JLabel graduate__lstu_num = new JLabel("学生人数");
		graduate__lstu_num.setForeground(Color.WHITE);
		graduate__lstu_num.setBounds(27, 49, 54, 15);
		graduate_design.add(graduate__lstu_num);
		
		JLabel graduate_lweeks = new JLabel("周    数");
		graduate_lweeks.setForeground(Color.WHITE);
		graduate_lweeks.setBounds(27, 74, 54, 15);
		graduate_design.add(graduate_lweeks);
		
		JLabel graduate_lweek_hour = new JLabel("学 时 数");
		graduate_lweek_hour.setForeground(Color.WHITE);
		graduate_lweek_hour.setBounds(27, 99, 54, 15);
		graduate_design.add(graduate_lweek_hour);
		
		JLabel graduate_lhuanjie = new JLabel("环节系数");
		graduate_lhuanjie.setForeground(Color.WHITE);
		graduate_lhuanjie.setBounds(27, 124, 54, 15);
		graduate_design.add(graduate_lhuanjie);
		
		graduate_classes = new JTextField();
		graduate_classes.setHorizontalAlignment(SwingConstants.CENTER);
		graduate_classes.setBounds(91, 21, 124, 21);
		graduate_design.add(graduate_classes);
		graduate_classes.setColumns(10);
		
		graduate_stu_num = new JTextField();
		graduate_stu_num.setHorizontalAlignment(SwingConstants.CENTER);
		graduate_stu_num.setColumns(10);
		graduate_stu_num.setBounds(91, 46, 124, 21);
		graduate_design.add(graduate_stu_num);
		
		graduate_weeks = new JTextField();
		graduate_weeks.setHorizontalAlignment(SwingConstants.CENTER);
		graduate_weeks.setColumns(10);
		graduate_weeks.setBounds(91, 71, 124, 21);
		graduate_design.add(graduate_weeks);
		
		graduate_week_hour = new JTextField();
		graduate_week_hour.setHorizontalAlignment(SwingConstants.CENTER);
		graduate_week_hour.setColumns(10);
		graduate_week_hour.setBounds(91, 96, 124, 21);
		graduate_design.add(graduate_week_hour);
		
		graduate_huanjie = new JTextField();
		graduate_huanjie.setHorizontalAlignment(SwingConstants.CENTER);
		graduate_huanjie.setColumns(10);
		graduate_huanjie.setBounds(91, 121, 124, 21);
		graduate_design.add(graduate_huanjie);
		
		graduate_news = new JTextField();
		graduate_news.setFont(new Font("幼圆", Font.PLAIN, 23));
		graduate_news.setHorizontalAlignment(SwingConstants.CENTER);
		graduate_news.setEditable(false);
		graduate_news.setBounds(27, 180, 188, 80);
		graduate_design.add(graduate_news);
		graduate_news.setColumns(10);
		
		JButton graduate_add = new JButton("添加");
		graduate_add.setBounds(10, 317, 93, 23);
		graduate_design.add(graduate_add);
		
		JButton graduate_calc = new JButton("计算");
		graduate_calc.setBounds(113, 317, 93, 23);
		graduate_design.add(graduate_calc);
		
		JButton graduate_delete = new JButton("删除");
		graduate_delete.setBounds(216, 317, 93, 23);
		graduate_design.add(graduate_delete);
		
		jsp_graduate = new JScrollPane();
		jsp_graduate.setBounds(246, 24, 371, 283);
		graduate_design.add(jsp_graduate);
		
		JLabel graduate_background_1 = new JLabel("");
		graduate_background_1.setIcon(new ImageIcon("pic\\graduate_background.gif"));
		graduate_background_1.setBounds(0, -1, 500, 351);
		graduate_design.add(graduate_background_1);
		
		JLabel graduate_background_2 = new JLabel("");
		graduate_background_2.setIcon(new ImageIcon("pic\\graduate_background.gif"));
		graduate_background_2.setBounds(497, -1, 130, 351);
		graduate_design.add(graduate_background_2);
//////////////////////////////////////////////////////////////		
		
		
//////////////////////////////////////////////////////////////		
		JPanel graduate_answer = new JPanel();
		work_main.addTab("毕业答辩工作量", graduate_answer);
		graduate_answer.setLayout(null);
		
		JLabel answer_lclasses = new JLabel("专    业");
		answer_lclasses.setBounds(10, 288, 54, 15);
		graduate_answer.add(answer_lclasses);
		
		JLabel answer_lstu_num = new JLabel("学生人数");
		answer_lstu_num.setBounds(10, 313, 54, 15);
		graduate_answer.add(answer_lstu_num);
		
		JLabel answer_lweeks = new JLabel("周    数");
		answer_lweeks.setBounds(231, 288, 54, 15);
		graduate_answer.add(answer_lweeks);
		
		JLabel answer_lhuanjie = new JLabel("环节系数");
		answer_lhuanjie.setBounds(231, 313, 54, 15);
		graduate_answer.add(answer_lhuanjie);
		
		answer_classes = new JTextField();
		answer_classes.setBounds(66, 285, 162, 21);
		graduate_answer.add(answer_classes);
		answer_classes.setColumns(10);
		
		answer_stu_num = new JTextField();
		answer_stu_num.setColumns(10);
		answer_stu_num.setBounds(66, 310, 162, 21);
		graduate_answer.add(answer_stu_num);
		
		answer_weeks = new JTextField();
		answer_weeks.setColumns(10);
		answer_weeks.setBounds(288, 285, 162, 21);
		graduate_answer.add(answer_weeks);
		
		answer_huanjie = new JTextField();
		answer_huanjie.setColumns(10);
		answer_huanjie.setBounds(288, 310, 162, 21);
		graduate_answer.add(answer_huanjie);
		
		answer_news = new JTextField();
		answer_news.setEditable(false);
		answer_news.setBounds(460, 10, 157, 21);
		graduate_answer.add(answer_news);
		answer_news.setColumns(10);
		
		JButton answer_calc = new JButton("计算");
		answer_calc.setFont(new Font("宋体", Font.PLAIN, 11));
		answer_calc.setIcon(new ImageIcon(T_guanli_main.class.getResource("/com/sun/javafx/scene/web/skin/FontBackgroundColor_16x16_JFX.png")));
		answer_calc.setBounds(460, 284, 77, 23);
		graduate_answer.add(answer_calc);
		
		JButton answer_delete = new JButton("删除");
		answer_delete.setFont(new Font("宋体", Font.PLAIN, 11));
		answer_delete.setIcon(new ImageIcon(T_guanli_main.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		answer_delete.setBounds(543, 284, 77, 23);
		graduate_answer.add(answer_delete);
		
		JButton answer_add = new JButton("添加");
		answer_add.setIcon(new ImageIcon(T_guanli_main.class.getResource("/com/sun/javafx/scene/web/skin/FontColor_16x16_JFX.png")));
		answer_add.setBounds(460, 309, 160, 23);
		graduate_answer.add(answer_add);
		
		jsp_answer = new JScrollPane();
		jsp_answer.setBounds(10, 10, 440, 265);
		graduate_answer.add(jsp_answer);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("pic\\graduate_answer.gif"));
		label.setBounds(460, 10, 157, 321);
		graduate_answer.add(label);
		
		JLabel work_main_back_1 = new JLabel("");
		work_main_back_1.setIcon(new ImageIcon("pic\\work_main_back.gif"));
		work_main_back_1.setBounds(1, 0, 500, 396);
		work_message.add(work_main_back_1);
		
		JLabel work_main_back_2 = new JLabel("pic\\work_main_back.gif");
		work_main_back_2.setIcon(new ImageIcon("pic\\work_main_back.gif"));
		work_main_back_2.setBounds(497, 0, 230, 396);
		work_message.add(work_main_back_2);
		
		JButton exit = new JButton("");
		exit.setIcon(new ImageIcon(T_guanli_main.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-error.png")));
		exit.setBounds(732, 402, 28, 23);
		frame.getContentPane().add(exit);
//////////////////////////////////////////////////////////////		

		
		Monitor_exit m0 = new Monitor_exit();
		exit.addActionListener(m0);
//////////////////////////////////////////////////////////////	
		Monitor_search m1 = new Monitor_search();
		add_queren.addActionListener(m1);
		
		Monitor_amend m2 = new Monitor_amend();
		add_amend.addActionListener(m2);
		
		Monitor_chongzhi m3 = new Monitor_chongzhi();		
		amend_chongzhi.addActionListener(m3);
		
		Monitor_add m4 = new Monitor_add();
		add_tianjia.addActionListener(m4);
		
		Monitor_add_chongzhi m5 = new Monitor_add_chongzhi();
		add_chongzhi.addActionListener(m5);
		
		Monitor_add_delete m6 = new Monitor_add_delete();
		add_delete.addActionListener(m6);		
	
	/*********************************************************/			
	//		查看页面的 刷新 功能	
		Monitor_flush m7 = new Monitor_flush();
		flush.addActionListener(m7);
		
	/*********************************************************/	
	//      single 内部监听器实例及注册	
		Monitor_single_add m8 = new Monitor_single_add();
		single_add.addActionListener(m8);
		
		Monitor_single_calc m9 = new Monitor_single_calc();
		single_calc.addActionListener(m9);
		
		Monitor_single_delete m10 = new Monitor_single_delete();
		single_delete.addActionListener(m10);
		
	/*********************************************************/	
	//      course 内部监听器实例及注册	
		Monitor_course_add m11 = new Monitor_course_add();
		course_add.addActionListener(m11);
		
		Monitor_course_calc m12 = new Monitor_course_calc();
		course_calc.addActionListener(m12);
		
		Monitor_course_delete m13 = new Monitor_course_delete();
		course_delete.addActionListener(m13);
		
	/*********************************************************/	
	//      theory 内部监听器实例及注册				
		Monitor_theory_add m14 = new Monitor_theory_add();
		theory_add.addActionListener(m14);
		
		Monitor_theory_calc m15 = new Monitor_theory_calc();
		theory_calc.addActionListener(m15);
		
		Monitor_theory_delete m16 = new Monitor_theory_delete();
		theory_delete.addActionListener(m16);
		
	/*********************************************************/	
	//      graduate_design 内部监听器实例及注册	
		Monitor_graduate_add m17 = new Monitor_graduate_add();
		graduate_add.addActionListener(m17);
		
		Monitor_graduate_calc m18 = new Monitor_graduate_calc();
		graduate_calc.addActionListener(m18);
		
		Monitor_graduate_delete m19 = new Monitor_graduate_delete();
		graduate_delete.addActionListener(m19);
	/*********************************************************/	
	//      graduate_answer 内部监听器实例及注册	
		Monitor_answer_add m20 = new Monitor_answer_add();
		answer_add.addActionListener(m20);
		
		Monitor_answer_calc m21 = new Monitor_answer_calc();
		answer_calc.addActionListener(m21);
		
		Monitor_answer_delete m22 = new Monitor_answer_delete();
		answer_delete.addActionListener(m22);
	}

////////////////////////////////////////////////////////
	class Monitor_search implements ActionListener//查找
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			id.setEnabled(false);// 查找成功后 将 教师号一行设为 不可编辑 
			LinkedList<String> a = new LinkedList<>();//用LinkedList 来装新的信息（待修改的信息）
			F_fun f = new F_fun();//实例化 功能类
			int nid = Integer.parseInt(id.getText());//将教师号栏 转为 int 型数据  以便传递给 下面的 sql 语句
			
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
					a.add(rst.getString(1));//将数据库中的 教师号信息全部取出来
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
			
			Iterator<String> i = a.iterator();//获取 链表的迭代器
			int charge =0;//做判断条件使用  为 1 查找成功  非1 查询失败 

			while(i.hasNext())
			{
				if( i.next().equals(id.getText()) )//将链表中的数据 与 界面中教师号栏的进行比对
				{
					
					T_info m = new T_info();
					m = f.get(nid);//调用 查询功能
					id.setText(m.getT_id()+"");//将查询到的信息 显示在对应的栏位中 以方便修改
					name.setText(m.getT_name());
					gender.setText(m.getT_gender());
					jobtitle.setText(m.getT_jobtitle());
					course.setText(m.getT_course());
					classes.setText(m.getT_classes());
					classnum.setText(m.getT_classnum()+"");
					theory.setText(m.getT_theory()+"");
					experimental.setText(m.getT_experimental()+"");
					charge = 1;
//					break;
					
				}
//				else
//				{
//					charge = 1;
//					id.setText("查无此人");
//					name.setText("十分抱歉");
//					gender.setText("您所查询的信息不存在");
//					jobtitle.setText("您可以：");
//					course.setText("----------");
//					classes.setText("换一个教师号");
//					classnum.setText("或手动添加");
//					theory.setText("----------");
//					experimental.setText("谢谢！");
					
//					break;
//					continue;
//				}
		
			}
			if(charge != 1)//返回查询失败信息
			{
				JOptionPane.showMessageDialog(null, "查无此人");
				id.setText("查无此人");
				name.setText("十分抱歉");
				gender.setText("您所查询的信息不存在");
				jobtitle.setText("您可以：");
				course.setText("----------");
				classes.setText("换一个教师号");
				classnum.setText("或手动添加");
				theory.setText("----------");
				experimental.setText("谢谢！");
			}
		}
	}
	
	class Monitor_amend implements ActionListener//修改
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
//			id.setEnabled(false);
			F_fun f = new F_fun();
			int nid = Integer.parseInt(id.getText());
			int nclassnum = Integer.parseInt(classnum.getText());
			float ntheory = Float.parseFloat(theory.getText());
			float nexperimental = Float.parseFloat(experimental.getText());
			T_info m = new T_info(nid,name.getText(),
									gender.getText(),
									jobtitle.getText(),
									course.getText(),
									classes.getText(),
									nclassnum,ntheory,nexperimental);
			f.update(m, nid);
			JOptionPane.showMessageDialog(null, "修改成功", "温馨提示", 1);
		}
	}
	
	class Monitor_chongzhi implements ActionListener//重置
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			id.setEnabled(true);
			id.setText("");
			name.setText("");
			gender.setText("");
			jobtitle.setText("");
			course.setText("");
			classes.setText("");
			classnum.setText("");
			theory.setText("");
			experimental.setText("");			
		}
	}
	
	class Monitor_add implements ActionListener//添加
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int bid = Integer.parseInt(add_id.getText());
			int bclassnum = Integer.parseInt(add_classnum.getText());
			float btheory = Float.parseFloat(add_theory.getText());
			float bexperimental = Float.parseFloat(add_experimental.getText());
			LinkedList<String> a = new LinkedList<>();
			T_info m = new T_info();
			F_fun f = new F_fun();
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "select * from info";
				pstmt = conn.prepareStatement(sql);
				rst = pstmt.executeQuery();
				while(rst.next())
				{
					a.add(rst.getString(1));
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
			
			m = new T_info(bid,add_name.getText(),add_gender.getText(),
							add_jobtitle.getText(),add_course.getText(),
							add_classes.getText(),bclassnum,btheory,bexperimental);
					
			
			Iterator<String> i = a.iterator();
			int charge = 0;
			while(i.hasNext())
			{
				if( i.next().equals(add_id.getText()) )
				{										
					show.setText("已存在该教师号");	
					charge = 1;
//					break;
				}
			}
			if(charge != 1)
			{
				f.add(m);
				show.setText("添加成功！");
			}
			
//			if(charge == 1)
//				JOptionPane.showMessageDialog(null, "教师号已存在");
//			else if(charge == 2)
//				
//			else if(charge == 3)
//			{
//				
//				JOptionPane.showMessageDialog(null, "添加成功");//, title, messageType);
//			}
		}
	}
	
	class Monitor_add_chongzhi implements ActionListener//重置
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			add_id.setText("");
			add_name.setText("");
			add_gender.setText("");
			add_jobtitle.setText("");
			add_course.setText("");
			add_classes.setText("");
			add_classnum.setText("");
			add_theory.setText("");
			add_experimental.setText("");
			show.setText("NEWS");
			
		}
	}
	
	class Monitor_add_delete implements ActionListener//删除
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int bid = Integer.parseInt(add_id.getText());
			F_fun f = new F_fun();
			LinkedList<String> a = new LinkedList<>();
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "select T_id from info";
				pstmt = conn.prepareStatement(sql);
				rst = pstmt.executeQuery();
				while(rst.next())
				{
					a.add(rst.getString(1));
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
			
			Iterator<String> i = a.iterator();
			while(i.hasNext())
			{
				if( i.next().equals(add_id.getText()) )
				{
					f.delete(bid);
					show.setText("删除成功！");
					break;
				}
				else
				{
					show.setText("未找到该教师号，请重新输入");
				}
			}
		}
	}
	
	class Monitor_flush implements ActionListener//刷新
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			new T_guanli_main();
			frame.dispose();
		}	
	}
	
	class Monitor_exit implements ActionListener //退出
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			// TODO Auto-generated method stub
			System.exit(0);
		}
	}
//////////////////////////////////////////////////////////
//  single 内的功能监听器									//
	class Monitor_single_add implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bsingle_hour = Float.parseFloat(single_hour.getText());
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "insert into single values(?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, single_course_name.getText());
				pstmt.setString(2, single_classes.getText());
				pstmt.setFloat(3, bsingle_hour);
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			single_news.setText("添加成功！");
			JOptionPane.showMessageDialog(null, "添加成功！", "温馨提示", 1);
		}
	}
	
	class Monitor_single_calc implements ActionListener//使用的是文本域输出
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
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
	
	class Monitor_single_delete implements ActionListener//单个任务总课时删除
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "delete from single where single_course_name=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, single_course_name.getText());
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			single_news.setText("删除成功！");
			JOptionPane.showMessageDialog(null, "删除成功！", "温馨提示", 1);
		}
	}
	
//////////////////////////////////////////////////////////	
//	design 内的功能监听器
	class Monitor_course_add implements ActionListener//添加&计算
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bcourse_weeks = Float.parseFloat(course_weeks.getText());
			float bcourse_zhidao = Float.parseFloat(course_zhidao.getText());
			float bcourse_week_hour = Float.parseFloat(course_week_hour.getText());
			float bcourse_zhuanye = Float.parseFloat(course_zhuanye.getText());
			float bcourse_huanjie = Float.parseFloat(course_huanjie.getText());
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "insert into design values(?,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, course_name.getText());
				pstmt.setString(2, course_classes.getText());
				pstmt.setFloat(3, bcourse_weeks);
				pstmt.setFloat(4, bcourse_zhidao);
				pstmt.setFloat(5, bcourse_week_hour);
				pstmt.setFloat(6, bcourse_zhuanye);
				pstmt.setFloat(7, bcourse_huanjie);
				pstmt.setFloat(8, bcourse_weeks*bcourse_zhidao*bcourse_week_hour*bcourse_zhuanye*bcourse_huanjie);
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			course_news.setText("添加成功！");
			JOptionPane.showMessageDialog(null, "添加成功！", "温馨提示", 1);			
		}
	}
	
	class Monitor_course_calc implements ActionListener//显示&刷新
	{
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
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
			
			course_view = new JTable(row,cnames);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			course_view.setDefaultRenderer(Object.class, tcr);		
			jsp_course.setViewportView(course_view);
			
			course_name.setText("");
			course_classes.setText("");
			course_weeks.setText("");
			course_zhidao.setText("");
			course_week_hour.setText("");
			course_zhuanye.setText("");
			course_huanjie.setText("");
			
			course_news.setText("");
			course_news.setText("^_^");
			course_news.setText("        成功,结果在“工作量”列");
		}
	}
	
	class Monitor_course_delete implements ActionListener//删除
	{
		@Override		
		public void actionPerformed(ActionEvent e) 
		{	
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(url, user, password);
				String sql = "delete from design where design_course_name=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, course_name.getText());
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			course_news.setText("已删除！");
			JOptionPane.showMessageDialog(null, "已删除！", "温馨提示", 1);
		}	
	}
	
/////////////////////////////////////////////////////////	
//	theory_experimental 内的功能监听器
	class Monitor_theory_add implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float btheory_xueshi = Float.parseFloat(theory_xueshi.getText());
			float btheory_kecheng = Float.parseFloat(theory_kecheng.getText());
			float btheory_heban = Float.parseFloat(theory_heban.getText());
			float btheory_huanjie = Float.parseFloat(theory_huanjie.getText());
			
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				conn =DriverManager.getConnection(url, user, password);
				String sql = "insert into theory_experimental values(?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, theory_name.getText());
				pstmt.setString(2, theory_classes.getText());
				pstmt.setFloat(3, btheory_xueshi);
				pstmt.setFloat(4, btheory_kecheng);
				pstmt.setFloat(5, btheory_heban);
				pstmt.setFloat(6, btheory_huanjie);
				pstmt.setFloat(7, btheory_xueshi*btheory_kecheng*btheory_heban*btheory_huanjie);
				pstmt.executeUpdate();				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally
			{
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			theory_news.setText("添加成功！");			
			JOptionPane.showMessageDialog(null, "添加成功！", "温馨提示", 1);	
			
			theory_green_light.setBackground(Color.GREEN);		
			Timer timer = new Timer(2000, new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					theory_green_light.setBackground(Color.WHITE);				
				}
			});
			timer.start();
		}
	}
	
	class Monitor_theory_calc implements ActionListener
	{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			Vector row,cnames;
			
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
			
			theory_view = new JTable(row,cnames);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			theory_view.setDefaultRenderer(Object.class,tcr);
			jsp_theory.setViewportView(theory_view);
			
			theory_name.setText("");
			theory_classes.setText("");
			theory_xueshi.setText("");
			theory_kecheng.setText("");
			theory_heban.setText("");
			theory_huanjie.setText("");
			
			theory_news.setText("");					
			theory_green_light.setBackground(Color.BLUE);
			Timer timer = new Timer(2000, new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					theory_news.setText("^_^");
					theory_green_light.setBackground(Color.WHITE);
				}
			});
			timer.start();
			theory_news.setText("     成功");
					
		}
	}
	
	class Monitor_theory_delete implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			F_link link = new F_link();
			Connection conn = link.getconnection();			
			String sql = "delete from theory_experimental where t_e_course=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, theory_name.getText());
				pstmt.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally
			{
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			theory_news.setText("已删除！");
			JOptionPane.showMessageDialog(null, "已删除！", "温馨提示", 1);
			
			theory_red_light.setBackground(Color.RED);
			Timer timer = new Timer(2000, new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					theory_red_light.setBackground(Color.WHITE);
				}
			});
			timer.start();
		}
	}
	
////////////////////////////////////////////////////////
//	graduate 内的监听器
	class Monitor_graduate_add implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			float bgraduate_stu_num = Float.parseFloat(graduate_stu_num.getText());
			float bgraduate_weeks = Float.parseFloat(graduate_weeks.getText());
			float bgraduate_xueshi = Float.parseFloat(graduate_week_hour.getText());
			float bgraduate_huanjie = Float.parseFloat(graduate_huanjie.getText());
			
			F_link f = new F_link();
			conn = f.getconnection();
			
			
			try
			{
				String sql = "insert into graduate_design values(?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, graduate_classes.getText());
				pstmt.setFloat(2, bgraduate_stu_num);
				pstmt.setFloat(3, bgraduate_weeks);				
				pstmt.setFloat(4, bgraduate_xueshi);
				pstmt.setFloat(5, bgraduate_huanjie);
				pstmt.setFloat(6, bgraduate_stu_num * bgraduate_weeks * bgraduate_xueshi * bgraduate_huanjie);
				pstmt.executeUpdate();
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
			graduate_news.setText("添加成功！");			
			JOptionPane.showMessageDialog(null, "添加成功！", "温馨提示", 1);
		}
	}
	
	class Monitor_graduate_calc implements ActionListener
	{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) 
		{
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
			
			graduate_view = new JTable(row,cnames);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			graduate_view.setDefaultRenderer(Object.class,tcr);
			jsp_graduate.setViewportView(graduate_view);
			
			graduate_classes.setText("");
			graduate_stu_num.setText("");
			graduate_weeks.setText("");
			graduate_week_hour.setText("");
			graduate_huanjie.setText("");
			
			graduate_news.setText("     成功");
			Timer timer = new Timer(2000, new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					graduate_news.setText("^_^");					
				}
			});
			timer.start();			
		}
	}
	
	class Monitor_graduate_delete implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			F_link link = new F_link();
			conn = link.getconnection();			
			
			
			
			try 
			{
				String sql = "delete from graduate_design where g_d_name=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, graduate_classes.getText());
				pstmt.executeUpdate();
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
			
			JOptionPane.showMessageDialog(null, "已删除！", "温馨提示", 1);
			
			graduate_news.setText("已删除！");
			Timer timer = new Timer(2000, new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					graduate_news.setText("^…^");
				}
			});
			timer.start();
		}
	}
		
////////////////////////////////////////////////////////
//answer 内监听器
	class Monitor_answer_add implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
		// TODO Auto-generated method stub
			float bstudents = Float.parseFloat(answer_stu_num.getText());
			float bweeks = Float.parseFloat(answer_weeks.getText());
			float bhuanjie = Float.parseFloat(answer_huanjie.getText());
			
			F_link f = new F_link();
			conn = f.getconnection();			
			String sql= "insert into answer values(?,?,?,?,?)";			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, answer_classes.getText());
				pstmt.setFloat(2, bstudents);
				pstmt.setFloat(3, bweeks);
				pstmt.setFloat(4, bhuanjie);
				pstmt.setFloat(5, bstudents * bweeks * bhuanjie);
				pstmt.executeLargeUpdate();
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
			answer_news.setText("添加成功！");			
			JOptionPane.showMessageDialog(null, "添加成功！", "温馨提示", 1);
		}
	}

	class Monitor_answer_calc implements ActionListener
	{
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public void actionPerformed(ActionEvent e) 
		{
		// TODO Auto-generated method stub
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
			
			answer_view = new JTable(row,cnames);
			DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
			tcr.setHorizontalAlignment(SwingConstants.CENTER);
			answer_view.setDefaultRenderer(Object.class, tcr);
			jsp_answer.setViewportView(answer_view);
			
			answer_news.setText("     成功");
			Timer timer = new Timer(2000, new ActionListener() //延时操作控件
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					answer_news.setText("^_^");					
				}
			});
			timer.start();		
		}
	}

	class Monitor_answer_delete implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
		// TODO Auto-generated method stub
			F_link link = new F_link();
			conn = link.getconnection();		
			String sql ="delete from answer where answer_classes=?";
			try 
			{
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, answer_classes.getText());
				pstmt.executeUpdate();
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
			
			JOptionPane.showMessageDialog(null, "已删除！", "温馨提示", 1);
						
			answer_news.setText("已删除！");
			Timer timer = new Timer(2000, new ActionListener() 
			{				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// TODO Auto-generated method stub
					answer_news.setText("^…^");
				}
			});
			timer.start();
		}
	}

//	public static void main(String[] args) 
//	{
////		T_main t = new T_main();
//		new T_main();
//	}
}

/**
 * 
  			course_name.setText("");
			LinkedList<M_course> a = new LinkedList<>();
			LinkedList<Float> a3 = new LinkedList<>();
			LinkedList<Float> a4 = new LinkedList<>();
			LinkedList<Float> a5 = new LinkedList<>();
			LinkedList<Float> a6 = new LinkedList<>();
			LinkedList<Float> a7 = new LinkedList<>();
			M_course c = null;
			
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
					c = new M_course(rst.getString(1),rst.getString(2),rst.getFloat(3),rst.getFloat(4),rst.getFloat(5),rst.getFloat(6),rst.getFloat(7));
					a.add(c);
					a3.add(rst.getFloat(3));
					a4.add(rst.getFloat(4));
					a5.add(rst.getFloat(5));
					a6.add(rst.getFloat(6));
					a7.add(rst.getFloat(7));
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
			
			//course_view.setText("");
			for(int i=0;i<a.size();i++)
			{
				if(a.get(i) == null)
					break;
				else
					course_view.setText(course_view.getText() + a.get(i) + System.getProperty("line.separator"));
			}
			
			float sum = 0;
			for(int i = 0 ;i<a.size();i++)
			{
				if(a3.get(i) == null && a4.get(i) == null && a5.get(i) == null && a6.get(i) == null && a7.get(i) == null)
					break;
				else
					sum = sum + (a3.get(i) * a4.get(i) * a5.get(i) * a6.get(i) * a7.get(i));
			}
			course_news.setText("课程设计工作量："+sum);

 
 *
 */
