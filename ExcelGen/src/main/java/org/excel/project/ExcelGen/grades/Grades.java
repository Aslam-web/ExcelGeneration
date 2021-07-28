package org.excel.project.ExcelGen.grades;

// marker interfaces
public interface Grades {

	public static interface GradeTen {}
	public static interface GradeTwelve {}

	GradeTen TENTH = new GradeTen() {};
	GradeTwelve TWELTH = new GradeTwelve() {};
}