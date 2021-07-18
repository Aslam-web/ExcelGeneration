package org.excel.project.ExcelGen.controllers;

import org.excel.project.ExcelGen.grades.GradeTen;
import org.excel.project.ExcelGen.grades.GradeTwelve;

public class SendMailController {

	public static void send(GradeTen tenth, String filename) {	
		GradeTenController gradeTenController = new GradeTenController(filename);
		gradeTenController.startAction();
	}
	
	public static void send(GradeTwelve twelth, String filename) {
		System.out.println("Yet to be implented ...");
		// controller for grade twelve
//		GradeTwelveController.startAction(filename);
	}
	
}