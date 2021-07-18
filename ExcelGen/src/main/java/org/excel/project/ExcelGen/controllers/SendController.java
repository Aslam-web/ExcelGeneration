package org.excel.project.ExcelGen.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.excel.project.ExcelGen.student.Student;

class SendController {

	private Session session;

	private Student s;
	private String fromEmail;
	private String password;
	private String toEmail;

	public void createSession(String fromEmail, String password, Student student) {

		this.fromEmail = fromEmail;
		this.password = password;
		this.s = student;

		Map<String, String> m = new HashMap<>();

		m.put("mail.smtp.auth", "true");
		m.put("mail.smtp.starttls.enable", "true");
		m.put("mail.smtp.host", "smtp.gmail.com");
		m.put("mail.smtp.port", "587");

		Properties properties = new Properties();
		properties.putAll(m);

		session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				System.out.println("connecting to server");
				return new PasswordAuthentication(fromEmail, password);
			}
		});
	}

	public Message createMimeMessage(String attachment) {

		Message message = new MimeMessage(session);
		try {
			
			message.setFrom(new InternetAddress(this.fromEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(s.getParentEmail()));
			message.setSubject("Results Have been published");

			Multipart multipart = new MimeMultipart();

			// begin text
			MimeBodyPart text1 = new MimeBodyPart();
			text1.setText(getTextBody(true));

			// file
			MimeBodyPart file = new MimeBodyPart();
			file.attachFile(attachment);

			// end text
			MimeBodyPart text2 = new MimeBodyPart();
			text2.setText(getTextBody(false));

			multipart.addBodyPart(text1);
			multipart.addBodyPart(file,1);
			multipart.addBodyPart(text2);

			message.setContent(multipart);

			System.out.println("Message Sending to: " + s.getParentEmail());

		} catch (Exception e) {
			System.out.printf("SOME ERROR OCCURED IN CREATING EMAIL TO %s!!!\n", s.getParentEmail());
			e.printStackTrace();
		}

		return message;

	}

	public boolean send(Message message) {

		try {

			Transport.send(message);
			System.out.printf("Message successfully sent to: <%s>; Parent : %s; Student : %s\n",
					s.getParentEmail(), s.getParentName(), s.getStudentName());

			return true;
		} catch (MessagingException e) {
			System.out.print("MESSAGE SENDING FAILED TO: <" + this.toEmail + ">");
			System.out.println("\tProblem : " + e.getMessage());
			return false;
		}
	}

	private String getTextBody(boolean first) {

		return first
				? "Dear Mr/Mrs " + s.getParentName() + "\n\nThe results have been Published"
						+ "\nThe details about the result for your Son/Daughter " + s.getStudentName()
						+ " Have been attached as an Excel File"

				:

				"\n\n\nThanks & Regards" + "\nMr M.N Aslam," + "\nDean of Student Affairs,"
						+ "\nHaaris Infotech School," + "\nEmail" + ":aslam1qqqq@gmail.com,"
						+ "\nPhone: +91 63799 71782.";
	}
}