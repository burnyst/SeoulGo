package com.seoulmate.seoulgo.security;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailConfirm {
	
	public int sendMail(String mailAddress) {
		
		String host = "smtp.gmail.com";
		final String user = "hannnnnak@gmail.com"; 
		final String password = "viqlwzyrdsqlgipl";

		Properties props = new Properties();
		int random = (int)(Math.random() * 1000000);

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.timeout", 5000);
		props.put("mail.smtp.starttls.enable", true);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		session.setDebug(true);

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailAddress));
			message.setSubject("[Seoulmate] SeoulGo 인증번호 발송 메일입니다.");
			message.setText("인증번호는 " + random + "입니다."); // 메일 내용

			Transport.send(message);
			System.out.println("sendMail success");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return random;
	}
}