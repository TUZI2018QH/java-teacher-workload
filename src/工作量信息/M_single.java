package 工作量信息;

public class M_single 
{


	private String single_course_name;
	private String single_classes;
	private float single_hour;
	
	public M_single() {}
	public M_single(String single_course_name,String single_classes,float single_hour)
	{
		this.single_course_name = single_course_name;
		this.single_classes = single_classes;
		this.single_hour = single_hour;
	}
	
	public void setSingle_course_name(String single_course_name) {
		this.single_course_name = single_course_name;
	}
	public void setSingle_classes(String single_classes) {
		this.single_classes = single_classes;
	}
	public void setSingle_hour(float single_hour) {
		this.single_hour = single_hour;
	}
	
	
	public String getSingle_course_name() {
		return single_course_name;
	}
	public String getSingle_classes() {
		return single_classes;
	}
	public float getSingle_hour() {
		return single_hour;
	}
	
	public String toString()
	{
		return getSingle_course_name() +"    \t"+ getSingle_classes() +"\t         "+ getSingle_hour();
	}
}
