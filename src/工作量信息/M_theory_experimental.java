package 工作量信息;

public class M_theory_experimental 
{
	private String course;
	private String classes;
	private float xueshi;
	private float kecheng;
	private float heban;
	private float huanjie;
	private float result;
	
	public M_theory_experimental() {}
	
	public M_theory_experimental(String course,String classes,float xueshi,float kecheng,float heban,float huanjie) 
	{
		this.course = course;
		this.classes = classes;
		this.xueshi = xueshi;
		this.kecheng = kecheng;
		this.heban = heban;
		this.huanjie = huanjie;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public void setXueshi(float xueshi) {
		this.xueshi = xueshi;
	}

	public void setKecheng(float kecheng) {
		this.kecheng = kecheng;
	}

	public void setHeban(float heban) {
		this.heban = heban;
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

	public float getXueshi() {
		return xueshi;
	}

	public float getKecheng() {
		return kecheng;
	}

	public float getHeban() {
		return heban;
	}

	public float getHuanjie() {
		return huanjie;
	}

	public float getResult() {
		return result;
	}
	
	public String toString()
	{
		return getCourse()+"\t "+getClasses()+"\t "+getXueshi()+"\t "+getKecheng()+"\t "+getHeban()+"\t "+getHuanjie();
	}
}
