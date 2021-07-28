package org.excel.project.ExcelGen.controllers;

import org.excel.project.ExcelGen.grades.Grades;

public class SendMailController {

	public static void send(Grades.GradeTen tenth, String filename) {	
		GradeTenController gradeTenController = new GradeTenController(filename);
		gradeTenController.startAction();
	}
	
	public static void send(Grades.GradeTwelve twelth, String filename) {
		System.out.println("Yet to be implented ...");
		// controller for grade twelve
//		GradeTwelveController.startAction(filename);
	}
	
}