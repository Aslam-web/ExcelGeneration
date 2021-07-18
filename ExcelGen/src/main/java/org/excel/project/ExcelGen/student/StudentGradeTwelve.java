package org.excel.project.ExcelGen.student;

public class StudentGradeTwelve implements Student{
	private String studentName;
	private long studentRoll;
	
	private int mark1;
	private int mark2;
	private int mark3;
	private int mark4;
	private int mark5;
	private int mark6;
	private int totalMarks;

	private String parentName;
	private String parentEmail;
	
	// getters
	public String getStudentName() {
		return studentName;
	}
	public long getStudentRoll() {
		return studentRoll;
	}
	public int getMark1() {
		return mark1;
	}
	public int getMark2() {
		return mark2;
	}
	public int getMark3() {
		return mark3;
	}
	public int getMark4() {
		return mark4;
	}
	public int getMark5() {
		return mark5;
	}
	public int getMark6() {
		return mark6;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public String getParentName() {
		return parentName;
	}
	public String getParentEmail() {
		return parentEmail;
	}
	
	
	// setters
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public void setStudentRoll(long studentRoll) {
		this.studentRoll = studentRoll;
	}
	public void setMark1(int mark1) {
		this.mark1 = mark1;
	}
	public void setMark2(int mark2) {
		this.mark2 = mark2;
	}
	public void setMark3(int mark3) {
		this.mark3 = mark3;
	}
	public void setMark4(int mark4) {
		this.mark4 = mark4;
	}
	public void setMark5(int mark5) {
		this.mark5 = mark5;
	}
	public void setMark6(int mark6) {
		this.mark6 = mark6;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public void setParentEmail(String parentEmail) {
		this.parentEmail = parentEmail;
	}
	
}
