package 工作量信息;

public class M_course 
{



	private String course;
	private String classes;
	private float weeks;
	private float zhidao;
	private float week_hour;
	private float zhuanye;
	private float huanjie;
	private float result;


	public M_course() {}
	
	public M_course(String course,String classes,float weeks,float zhidao,float week_hour,float zhuanye,float huanjie)//,float result) 
	{
		this.course = course;
		this.classes = classes;
		this.weeks = weeks;
		this.zhidao = zhidao;
		this.week_hour = week_hour;
		this.zhuanye = zhuanye;
		this.huanjie = huanjie;
//		this.result = result;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	public void setWeeks(float weeks) {
		this.weeks = weeks;
	}

	public void setZhidao(float zhidao) {
		this.zhidao = zhidao;
	}

	public void setWeek_hour(float week_hour) {
		this.week_hour = week_hour;
	}

	public void setZhuanye(float zhuanye) {
		this.zhuanye = zhuanye;
	}

	public void setHuanjie(float huanjie) {
		this.huanjie = huanjie;
	}
	
	public void setResult(float result) {
		this.result = result;
	}
	
	
	public String getCourse() {
		return course;
	}

	public String getClasses() {
		return classes;
	}

	
	public float getWeeks() {
		return weeks;
	}

	public float getZhidao() {
		return zhidao;
	}

	public float getWeek_hour() {
		return week_hour;
	}

	public float getZhuanye() {
		return zhuanye;
	}

	public float getHuanjie() {
		return huanjie;
	}
	
	public float getResult() {
		return result;
	}
	
	public String toString()
	{
		return getCourse()+"\t " + getClasses()+"\t "+ getWeeks() + "\t "+getZhidao() + "\t " + getWeek_hour() + "\t " + getZhuanye() + "\t "+getHuanjie();
	}
}
