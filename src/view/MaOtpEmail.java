package view;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.ExtendedSSLSession;
import javax.swing.JOptionPane;


public class MaOtpEmail {
	public void sendEmail(String to) {
		String username = "toannh.23itb@vku.udn.vn";
		String password = "kxxlkqidxmdsedfz";	
		//properties: khai báo các thuộc tính
		Properties pros = new Properties();
		pros.put("mail.smtp.host", "smtp.gmail.com");
		pros.put("mail.smtp.port", "587"); //TLS 587 SSL 465
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.smtp.starttls.enable", "true");
		
		//tạo authenticator
		Authenticator auth = new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(username, password);
			}
		};
		
		// tạo phiên làm việc
		Session session = Session.getInstance(pros, auth);
		
		// Gửi email
		MimeMessage message = new MimeMessage(session);
		String otp = taoMaOtp();
		try {
			// Kiểu nội dung
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// Người gửi
			message.setFrom(username);
			// Người nhận
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
			// Tiêu đề
			message.setSubject("Thay đổi mật khẩu");
			// Nội dung
			message.setContent("<html lang=\"en\">\r\n"
					+ "<body>\r\n"
					+ "    <h3>Bạn đang thay đổi mật khẩu</h3>\r\n"
					+ "    <p style=\"color: red;font-size: 17px;\">Mã xác thực là: </p>\r\n"
					+ "</body>\r\n"
					+ "</html>" + otp, "text/HTML; charset=UTF-8");
			Transport.send(message);
			System.out.println("Gửi email thành công");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			System.out.println("Gửi email thất bại");
			
			e.printStackTrace();
		}
		
	}
	static String taoMaOtp() {
		Random random = new Random();
		int code = 1000 + random.nextInt(9000);
		return String.valueOf(code);
	}
	
}
