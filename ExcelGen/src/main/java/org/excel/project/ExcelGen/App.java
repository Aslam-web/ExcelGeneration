package org.excel.project.ExcelGen;

import org.excel.project.ExcelGen.controllers.SendMailController;
import org.excel.project.ExcelGen.grades.Grades;

public class App {
	
	public static void main(String[] args) {
		String file = "data/data.xls";
		SendMailController.send(Grades.TENTH, file);
	}
}