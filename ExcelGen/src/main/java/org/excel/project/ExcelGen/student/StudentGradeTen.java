package org.excel.project.ExcelGen.student;

public class StudentGradeTen implements Student{
	private String studentName;
	private long studentRoll;
	
	private int mathMark;
	private int scienceMark;
	private int socialMark;
	private int englishMark;
	private int language2Mark;
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
	public int getMathMark() {
		return mathMark;
	}
	public int getScienceMark() {
		return scienceMark;
	}
	public int getSocialMark() {
		return socialMark;
	}
	public int getEnglishMark() {
		return englishMark;
	}
	public int getLanguage2Mark() {
		return language2Mark;
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
	public void setMathMark(int mathMark) {
		this.mathMark = mathMark;
	}
	public void setScienceMark(int scienceMark) {
		this.scienceMark = scienceMark;
	}
	public void setSocialMark(int socialMark) {
		this.socialMark = socialMark;
	}
	public void setEnglishMark(int englishMark) {
		this.englishMark = englishMark;
	}
	public void setLanguage2Mark(int language2Mark) {
		this.language2Mark = language2Mark;
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
