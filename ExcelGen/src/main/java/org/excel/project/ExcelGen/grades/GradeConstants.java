package org.excel.project.ExcelGen.grades;

public class GradeConstants {
	public static GradeTen TENTH;
	public static GradeTwelve TWELVE;
	
	static {
		TENTH = new GradeTen();
		TWELVE = new GradeTwelve();
	}
}
