package 工作量信息;

public class M_graduate_design
{
	private String classes;
	private float students;
	private float weeks;
	private float xueshi;
	private float huanjie;
	private float result;
	
	public M_graduate_design() {}
	
	public M_graduate_design(String classes , float students , float weeks , float xueshi , float huanjie) 
	{
		this.classes = classes;
		this.students = students;
		this.weeks = weeks;
		this.xueshi = xueshi;
		this.huanjie  = huanjie;
	}
	
	
	public void setClasses(String classes) {
		this.classes = classes;
	}

	public void setStudents(float students) {
		this.students = students;
	}

	public void setWeeks(float weeks) {
		this.weeks = weeks;
	}

	public void setXueshi(float xueshi) {
		this.xueshi = xueshi;
	}

	public void setHuanjie(float huanjie) {
		this.huanjie = huanjie;
	}

	public void setResult(float result) {
		this.result = result;
	}
	
	
	public String getClasses() {
		return classes;
	}

	public float getStudents() {
		return students;
	}

	public float getWeeks() {
		return weeks;
	}

	public float getXueshi() {
		return xueshi;
	}

	public float getHuanjie() {
		return huanjie;
	}

	public float getResult() {
		return result;
	}
	
	public String toString()
	{
		return getClasses() +"\t "+ getStudents() +"\t "+ getWeeks() +"\t "+ getXueshi() +"\t"+ getHuanjie();
	}
}
