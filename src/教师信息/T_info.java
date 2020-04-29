package 教师信息;

public class T_info 
{
	private int T_id;	//教师号
	private String T_name;//姓名
	private String T_gender;//性别
	private String T_jobtitle;//职称
	private String T_course;//任课教程
	private String T_classes;//班级
	private int T_classnum;//班级数目
	private float T_theory;//理论课时
	private float T_experimental;//实验课时
	
	
	public T_info()
	{
		super();
	}
	
	public T_info(int id,String name,String gender,
			String jobtitle,String course,
			String classes,int classnum,
			float theory,float experimental)//构造方法
	{
		this.T_id = id;
		this.T_name = name;
		this.T_gender = gender;
		this.T_jobtitle = jobtitle;
		this.T_course = course;
		this.T_classes = classes;
		this.T_classnum = classnum;
		this.T_theory = theory;
		this.T_experimental = experimental;
	}
	
	
	
	public void setT_id(int t_id) {
		T_id = t_id;
	}

	public void setT_name(String t_name) {
		T_name = t_name;
	}

	public void setT_gender(String t_gender) {
		T_gender = t_gender;
	}

	public void setT_jobtitle(String t_jobtitle) {
		T_jobtitle = t_jobtitle;
	}

	public void setT_course(String t_course) {
		T_course = t_course;
	}

	public void setT_classes(String t_classes) {
		T_classes = t_classes;
	}

	public void setT_classnum(int t_classnum) {
		T_classnum = t_classnum;
	}

	public void setT_theory(float t_theory) {
		T_theory = t_theory;
	}

	public void setT_experimental(float t_experimental) {
		T_experimental = t_experimental;
	}
	
	
	
	public int getT_id() {
		return T_id;
	}

	public String getT_name() {
		return T_name;
	}

	public String getT_gender() {
		return T_gender;
	}

	public String getT_jobtitle() {
		return T_jobtitle;
	}

	public String getT_course() {
		return T_course;
	}

	public String getT_classes() {
		return T_classes;
	}

	public int getT_classnum() {
		return T_classnum;
	}

	public float getT_theory() {
		return T_theory;
	}

	public float getT_experimental() {
		return T_experimental;
	}
	
	
	public String toString()
	{
		return getT_id() +"   "+getT_name()+"   "+getT_gender()+"   "+getT_jobtitle()+
				"   "+getT_course()+"   "+getT_classes()+"   "+getT_classnum()+"   "+
				getT_theory()+"   "+getT_experimental();
	}
}
