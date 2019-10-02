package P1;
import java.util.Scanner;

public class Course implements DirectlyRelatable, Cloneable 
{

	private String courseID;
	private String courseName;
	private double credit;
	private String preReqID;
	private String coReqID;

	public Course(String courseID, String courseName, double credit, String preReqID, String coReqID) 
	{
		super();
		this.courseID = courseID;
		this.courseName = courseName;
		this.credit = credit;
		this.preReqID = preReqID;
		this.coReqID = coReqID;
		if (preReqID == null)
			this.preReqID = "";
		if (coReqID == null)
			this.coReqID = "";
	}
	public Course(Course otherCourse, String value) 
	{
		super();
		
		this.courseID = value; 
		this.courseName = otherCourse.courseName;
		this.credit = otherCourse.credit;
		this.preReqID = otherCourse.preReqID;
		this.coReqID = otherCourse.coReqID;
	}

	
	public Course clone() 
	{
		System.out.println("Enter a course ID for the copy of \"" + courseName + "\"  want to clone");
		
		Scanner keys = new Scanner(System.in);
		String value = keys.next();
		
		return new Course(this, value);
	}

	

	public String getCourseID()
	{
		return courseID;
	}
	public String getCoReqID() 
	{
		return coReqID;
	}
	public String getCourseName() 
	{
		return courseName;
	}
	public String getPreReqID() 
	{
		return preReqID;
	}
	public double getCredit() 
	{
		return credit;
	}

	
	public void setCourseName(String courseName) 
	{
		this.courseName = courseName;
	}
	public void setCredit(double credit) 
	{
		this.credit = credit;
	}
	public void setPreReqID(String preReqID) 
	{
		this.preReqID = preReqID;
	}
	
	public void setCoReqID(String coReqID)
	{
		this.coReqID = coReqID;
	}
	public void setCourseID(String courseID) 
	{
		this.courseID = courseID;
	}

	
	public boolean equals(Course otherCourse)
	{
		return ( this.courseName.equals( otherCourse.courseName ) && this.credit == otherCourse.credit
				&& this.preReqID.equals( otherCourse.preReqID ) && this.coReqID.equals( otherCourse.coReqID ));
	}
	
	
	
	public String toString() 
	{
		return "\n   Course Name : " + courseName + "\n   Course ID : " + courseID + "\n   Credit    : " + credit
				+ "\n   Prereq ID : " + preReqID + "\n   Coreq ID  : " + coReqID;
	}
	
	public boolean isDirectlyRelated(Course otherCourse) 
	{
		
		return (this.preReqID.equals(otherCourse.courseID) || this.coReqID.equals(otherCourse.courseID)
				|| otherCourse.preReqID.equals(this.courseID) || otherCourse.coReqID.equals(this.courseID));
	}
}