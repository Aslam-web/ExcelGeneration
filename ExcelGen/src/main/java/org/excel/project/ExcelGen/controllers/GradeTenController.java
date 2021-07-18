package org.excel.project.ExcelGen.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.Message;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.excel.project.ExcelGen.student.StudentGradeTen;

import io.github.cdimascio.dotenv.Dotenv;

class GradeTenController {

	public final String DATA_STORE = "data/";

	private String filename;
	private SendController sendControler;
	private Set<StudentGradeTen> studentEntries = new HashSet<>();

	public GradeTenController(String filename) {
		this.filename = filename;
		this.sendControler = new SendController();
	}

	public void startAction() {

		System.out.println("Process Starting ...");

		// 1. read data from the excel file and store them in Set<StudentGradeTen>
		// studentEntries
		boolean dataStored = storeDataFromExcel();
		if (!dataStored) {
			System.out.println("Unable to read data");
			System.out.println("Process Completed : FAILED !!!");
			return;
		}

		System.out.println("------------------\nDisplaying data:");
		print();

		Iterator<StudentGradeTen> iter = studentEntries.iterator();

		while (iter.hasNext()) {

			StudentGradeTen s = iter.next();

			System.out.println("\n-------------------------------------------------------------\n");
			System.out.println("Processing for student : " + s.getStudentName() + " (" + s.getStudentRoll() + ") ");

			// 2. creating a list of **ONLY** the details that is going to be displayed
			// in the attachment
			List<String> sList = getDetailsAsList(s);

			// 3. create excel for each entry - returns the location of stored file
			String attachment = createSingleExcel(s, sList);

			// 4. send the excel created
			sendSingleExcel(s, attachment);
		}

		System.out.println("\n-------------------------------------------------------------\n");
		System.out.println("Process Completed : SUCCESS !!!");
	}

	private boolean storeDataFromExcel() {

		try (Workbook workbook = new HSSFWorkbook(new FileInputStream(filename))) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iter = sheet.rowIterator();

			// ignoring the first row - the headers
			iter.next();

			Row row;
			StudentGradeTen student;
			while (iter.hasNext()) {

				// got the whole row
				row = iter.next();
				student = new StudentGradeTen();
				// assigning values to all fields of StudentGradeTen object
				student.setStudentName(row.getCell(0).getStringCellValue());
				student.setStudentRoll((long) row.getCell(1).getNumericCellValue());

				student.setMathMark((int) row.getCell(2).getNumericCellValue());
				student.setScienceMark((int) row.getCell(3).getNumericCellValue());
				student.setSocialMark((int) row.getCell(4).getNumericCellValue());
				student.setEnglishMark((int) row.getCell(5).getNumericCellValue());
				student.setLanguage2Mark((int) row.getCell(6).getNumericCellValue());
				student.setTotalMarks((int) row.getCell(7).getNumericCellValue());

				student.setParentName(row.getCell(8).getStringCellValue());
				student.setParentEmail(row.getCell(9).getStringCellValue());

				this.studentEntries.add(student);
			}

			System.out.println("All entries stored ");
			return true;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {

		}

		return false;
	}

	private List<String> getDetailsAsList(StudentGradeTen s) {
		List<String> r = new ArrayList<>();

		r.add(s.getStudentName());
		r.add(String.valueOf(s.getStudentRoll()));

		r.add(String.valueOf(s.getMathMark()));
		r.add(String.valueOf(s.getScienceMark()));
		r.add(String.valueOf(s.getSocialMark()));
		r.add(String.valueOf(s.getEnglishMark()));
		r.add(String.valueOf(s.getLanguage2Mark()));

		r.add(String.valueOf(s.getTotalMarks()));

		return r;
	}

	private String createSingleExcel(StudentGradeTen s, List<String> details) {

		// details to be appended
		String[] det = { "Student Name", "Roll Number", "Math", "Science", "Social", "English", "Language 2", "Total" };
		String destination = this.DATA_STORE + s.getStudentName() + " Record.xls";
		String attachmentLocation = "";

		try (Workbook workbook = new HSSFWorkbook(); FileOutputStream output = new FileOutputStream(destination)) {

			Sheet sheet = workbook.createSheet(s.getStudentName() + "'s Record");

			// 8 rows, 2 columns
			CellStyle style = workbook.createCellStyle();
			style.setWrapText(true);
			style.setAlignment(HorizontalAlignment.RIGHT);
			Row row;
			for (int i = 0; i < details.size(); i++) {
				row = sheet.createRow(i);
				row.createCell(0).setCellValue(det[i]);
				Cell c =  row.createCell(1);
				c.setCellValue(details.get(i));
				c.setCellStyle(style);
			}
			sheet.autoSizeColumn(0);
			sheet.autoSizeColumn(1);
			sheet.

			// actual writing happens :
			workbook.write(output);
			attachmentLocation = new File(destination).getAbsolutePath();
			System.out.println("Created excel file : " + attachmentLocation);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return attachmentLocation;
	}

	private void sendSingleExcel(StudentGradeTen s, String attachment) {
//		sendControler.createSession("sample@gmail.com", "********", s);
		sendControler.createSession(Dotenv.load().get("MY_ACCOUNT_EMAIL"), Dotenv.load().get("MY_ACCOUNT_PASSWORD"), s);
		Message message = sendControler.createMimeMessage(attachment);
		sendControler.send(message);
	}

	private void print() {
		Iterator<StudentGradeTen> iter = this.studentEntries.iterator();
		while (iter.hasNext()) {
			StudentGradeTen s = iter.next();
			System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", s.getStudentName(), s.getStudentRoll(),
					s.getMathMark(), s.getScienceMark(), s.getSocialMark(), s.getEnglishMark(), s.getLanguage2Mark(),
					s.getTotalMarks(), s.getParentName(), s.getParentEmail());

		}
	}
}