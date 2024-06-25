package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import javax.swing.border.SoftBevelBorder;

import database.JDBCUtil;

import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class QuenMatKhau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtextfield_tenTk;
	private JTextField jtextfield_email;
	private JTextField jtextfield_matKhau;
	private JTextField jtextfield_matKhau2;
	private JTextField jtextfield_otpEmail;
	private JLabel jlabel_tb2;
	private JLabel jlabel_tb1;
	private JLabel jlabel_tb3;
	private JLabel jlabel_tb4;
	private JLabel lblNewLabel_1;
	private String otp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuenMatKhau frame = new QuenMatKhau();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QuenMatKhau() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\changepassword.png"));
		setResizable(false);
		setTitle("Đổi mật khẩu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Thay đổi mật khẩu");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 181, 24);
		contentPane.add(lblNewLabel);

		jtextfield_tenTk = new JTextField();
		jtextfield_tenTk.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextfield_tenTk.setBackground(new Color(245, 245, 245));
		jtextfield_tenTk.setBounds(58, 86, 207, 35);
		String text = "Tên tài khoản";
		jtextfield_tenTk.setText(text);
		jtextfield_tenTk.setForeground(Color.gray);
		jtextfield_tenTk.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jtextfield_tenTk.getText().isEmpty()) {
					jtextfield_tenTk.setForeground(Color.gray);
					jtextfield_tenTk.setText(text);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (jtextfield_tenTk.getText().equals(text)) {
					jtextfield_tenTk.setText("");
					jtextfield_tenTk.setForeground(Color.BLACK);
				}

			}
		});
		contentPane.add(jtextfield_tenTk);
		jtextfield_tenTk.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Vui lòng nhập tên tài khoản sử dụng để đăng nhập");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel_2.setBounds(10, 35, 255, 24);
		contentPane.add(lblNewLabel_2);

		jtextfield_email = new JTextField();
		jtextfield_email.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextfield_email.setBackground(new Color(245, 245, 245));
		jtextfield_email.setColumns(10);
		jtextfield_email.setBounds(58, 148, 207, 35);
		String text1 = "Tài khoản gmail";
		jtextfield_email.setText(text1);
		jtextfield_email.setForeground(Color.gray);
		jtextfield_email.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jtextfield_email.getText().isEmpty()) {
					jtextfield_email.setForeground(Color.gray);
					jtextfield_email.setText(text1);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (jtextfield_email.getText().equals(text1)) {
					jtextfield_email.setText("");
					jtextfield_email.setForeground(Color.BLACK);
				}

			}
		});
		contentPane.add(jtextfield_email);

		jtextfield_matKhau = new JTextField();
		jtextfield_matKhau.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextfield_matKhau.setBackground(new Color(245, 245, 245));
		jtextfield_matKhau.setBounds(58, 207, 207, 35);
		String text2 = "Nhập mật khẩu mới";
		jtextfield_matKhau.setText(text2);
		jtextfield_matKhau.setForeground(Color.gray);
		jtextfield_matKhau.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jtextfield_matKhau.getText().isEmpty()) {
					jtextfield_matKhau.setForeground(Color.gray);
					jtextfield_matKhau.setText(text2);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (jtextfield_matKhau.getText().equals(text2)) {
					jtextfield_matKhau.setText("");
					jtextfield_matKhau.setForeground(Color.BLACK);
				}

			}
		});
		contentPane.add(jtextfield_matKhau);
		jtextfield_matKhau.setColumns(10);
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

		jtextfield_matKhau2 = new JTextField();
		jtextfield_matKhau2.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextfield_matKhau2.setBackground(new Color(245, 245, 245));
		jtextfield_matKhau2.setColumns(10);
		jtextfield_matKhau2.setBounds(58, 267, 207, 35);
		String text3 = "Nhập lại mật khẩu";
		jtextfield_matKhau2.setText(text3);
		jtextfield_matKhau2.setForeground(Color.gray);
		jtextfield_matKhau2.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jtextfield_matKhau2.getText().isEmpty()) {
					jtextfield_matKhau2.setForeground(Color.gray);
					jtextfield_matKhau2.setText(text3);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (jtextfield_matKhau2.getText().equals(text3)) {
					jtextfield_matKhau2.setText("");
					jtextfield_matKhau2.setForeground(Color.BLACK);
				}

			}
		});
		contentPane.add(jtextfield_matKhau2);

		jtextfield_otpEmail = new JTextField();
		jtextfield_otpEmail.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextfield_otpEmail.setBackground(new Color(245, 245, 245));
		jtextfield_otpEmail.setColumns(10);
		jtextfield_otpEmail.setBounds(215, 326, 50, 24);
		String text4 = "Mã OTP";
		jtextfield_otpEmail.setText(text4);
		jtextfield_otpEmail.setForeground(Color.gray);
		jtextfield_otpEmail.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				if (jtextfield_otpEmail.getText().isEmpty()) {
					jtextfield_otpEmail.setForeground(Color.gray);
					jtextfield_otpEmail.setText(text4);
				}

			}

			@Override
			public void focusGained(FocusEvent e) {
				if (jtextfield_otpEmail.getText().equals(text4)) {
					jtextfield_otpEmail.setText("");
					jtextfield_otpEmail.setForeground(Color.red);
				}

			}
		});
		contentPane.add(jtextfield_otpEmail);

		JLabel lblNewLabel_1_1_1 = new JLabel("*");
		lblNewLabel_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1.setBounds(39, 214, 16, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		
		JButton jButton_doiMk = new JButton("Đổi mật khẩu");
		jButton_doiMk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				QuenMatKhau thayDoiMatKhau = new QuenMatKhau();
				String text = "Tên tài khoản";
				String text1 = "Tài khoản gmail";
				String text2 = "Nhập mật khẩu mới";
				String text3 = "Nhập lại mật khẩu";
				String text4 = "Mã OTP";
				
				if (jtextfield_tenTk.getText().equals("") || jtextfield_tenTk.getText().equals(text)) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Tên tài khoản không được để trống!");
					return;
				}
				if (jtextfield_email.getText().equals("") || jtextfield_email.getText().equals(text1)) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Gmail không được để trống!");
					return;
				}
				if (jtextfield_matKhau.getText().equals("") || jtextfield_matKhau.getText().equals(text2)) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Mật khẩu không được để trống!");
					return;
				}if (jtextfield_matKhau2.getText().equals("") || jtextfield_matKhau2.getText().equals(text3)) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Nhập lại mật khẩu không được để trống!");
					return;
				}
				if(otp==null) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Vui lòng ấn gửi OTP và nhập OTP từ gmail!");
					return;
				}
				if (jtextfield_otpEmail.getText().equals("") || jtextfield_otpEmail.getText().equals(text4)) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Vui lòng nhập Otp!");
					return;
				}
				if(!CheckUserName(jtextfield_tenTk.getText())) {
					jlabel_tb1.setText("Tên tài khoản không tồn tại.");
					jlabel_tb1.setForeground(Color.red);
					return;
				}
				
				
				if(!isGmailFormat(jtextfield_email.getText())) {
					jlabel_tb2.setText("Gmail không đúng định dạng");
					jlabel_tb2.setForeground(Color.red);
					jlabel_tb1.setText("");
					return;
				}
				if(!(jtextfield_matKhau.getText().equals(jtextfield_matKhau2.getText()))) {
					jlabel_tb4.setText("Mật khẩu nhập lại không khớp");
					jlabel_tb4.setForeground(Color.red);
					jlabel_tb2.setText("");
					jlabel_tb1.setText("");
					return;
					}
				
				if(!(otp.equals(jtextfield_otpEmail.getText()))) {
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Mã otp không đúng");
					return;
				}
				if(!(checkEmailMatchesAccount(jtextfield_tenTk.getText(), jtextfield_email.getText()))) {
					jlabel_tb2.setText("Gmail và tài khoản không trùng khớp !");
					jlabel_tb2.setForeground(Color.red);
					jlabel_tb1.setText("");
					jlabel_tb4.setText("");
					return;
				}
				changePassword();
				jtextfield_tenTk.setText(text);
				jtextfield_tenTk.setForeground(Color.gray);
		        jtextfield_email.setText(text1);
		        jtextfield_email.setForeground(Color.gray);
		        jtextfield_matKhau.setText(text2);
		        jtextfield_matKhau.setForeground(Color.gray);
		        jtextfield_matKhau2.setText(text3);
		        jtextfield_matKhau2.setForeground(Color.gray);
		        jtextfield_otpEmail.setText(text4);
		        jtextfield_otpEmail.setForeground(Color.gray);
				
			}
		});
		jButton_doiMk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton_doiMk.setFocusPainted(false);
		jButton_doiMk.setBackground(new Color(255, 0, 0));
		jButton_doiMk.setFont(new Font("Arial", Font.PLAIN, 14));
		jButton_doiMk.setBounds(58, 361, 207, 28);
		contentPane.add(jButton_doiMk);

		JPanel panel = new JPanel();
		panel.setBounds(18, 148, 30, 24);
		contentPane.add(panel);

		JLabel lblNewLabel_1_1 = new JLabel("");
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\email2.png"));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setForeground(Color.RED);

		JButton jButton_guiOTP = new JButton("Gửi Otp");
		jButton_guiOTP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton_guiOTP.setFocusPainted(false);
		jButton_guiOTP.setFont(new Font("Arial", Font.PLAIN, 12));
		jButton_guiOTP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuenMatKhau thayDoiMatKhau = new QuenMatKhau();
				String email = jtextfield_email.getText();
				if (email.isEmpty() || email.equals(text1)) {
					JOptionPane.showMessageDialog(jtextfield_email, "Vui lòng nhập email");
				} else {
					sendEmail(email);
					JOptionPane.showMessageDialog(thayDoiMatKhau, "Hệ thống đã gửi otp về gmail của bạn!");
				}
			}
		});
		jButton_guiOTP.setForeground(new Color(255, 255, 255));
		jButton_guiOTP.setBackground(new Color(0, 128, 0));
		jButton_guiOTP.setBounds(58, 326, 143, 24);
		contentPane.add(jButton_guiOTP);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("*");
		lblNewLabel_1_1_1_1.setForeground(Color.RED);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_1.setBounds(39, 274, 16, 14);
		contentPane.add(lblNewLabel_1_1_1_1);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("*");
		lblNewLabel_1_1_1_2.setForeground(Color.RED);
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1_1_2.setBounds(32, 93, 16, 14);
		contentPane.add(lblNewLabel_1_1_1_2);

		JButton jbutton_quaylai = new JButton("Quay lại");
		jbutton_quaylai.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_quaylai.setFocusPainted(false);
		jbutton_quaylai.setFont(new Font("Arial", Font.PLAIN, 11));
		jbutton_quaylai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login_User dn = new Login_User();
				dispose();
				dn.setVisible(true);
			}
		});
		jbutton_quaylai.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\comeback.png"));
		jbutton_quaylai.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		jbutton_quaylai.setBackground(new Color(255, 250, 250));
		jbutton_quaylai.setBounds(10, 406, 74, 28);

		contentPane.add(jbutton_quaylai);
		
		jlabel_tb1 = new JLabel("");
		jlabel_tb1.setForeground(new Color(255, 0, 0));
		jlabel_tb1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb1.setBounds(58, 123, 193, 14);
		contentPane.add(jlabel_tb1);
		
		jlabel_tb2 = new JLabel("");
		jlabel_tb2.setForeground(Color.RED);
		jlabel_tb2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb2.setBounds(58, 182, 193, 14);
		contentPane.add(jlabel_tb2);
		
		jlabel_tb3 = new JLabel("");
		jlabel_tb3.setForeground(Color.RED);
		jlabel_tb3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb3.setBounds(58, 242, 193, 14);
		contentPane.add(jlabel_tb3);
		
		jlabel_tb4 = new JLabel("");
		jlabel_tb4.setForeground(Color.RED);
		jlabel_tb4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb4.setBounds(58, 301, 193, 14);
		contentPane.add(jlabel_tb4);
		
		lblNewLabel_1 = new JLabel("Vui lòng nhập email đã đăng ký.");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(10, 58, 255, 24);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}

	public boolean CheckUserName(String username) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM account_user";
			ResultSet rs = st.executeQuery(sql);
			String username1 = "";
			while (rs.next()) {
				username1 = rs.getString("tenTaiKhoan");
				if (username.equals(username1)) {
					return true;
				}
			}
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean isGmailFormat(String email) {
		String emailPattern = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|googlemail\\.com|vku\\.udn\\.vn)$";

		Pattern pattern = Pattern.compile(emailPattern);
		Matcher matcher = pattern.matcher(email);

		return matcher.matches();
	}

	public void sendEmail(String to) {
		String username = "toannh.23itb@vku.udn.vn";
		String password = "kxxlkqidxmdsedfz";
		// properties: khai báo các thuộc tính
		Properties pros = new Properties();
		pros.put("mail.smtp.host", "smtp.gmail.com");
		pros.put("mail.smtp.port", "587"); // TLS 587 SSL 465
		pros.put("mail.smtp.auth", "true");
		pros.put("mail.smtp.starttls.enable", "true");

		// tạo authenticator
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
		otp = taoMaOtp();
		try {
			// Kiểu nội dung
			message.addHeader("Content-type", "text/HTML; charset=UTF-8");
			// Người gửi
			message.setFrom(username);
			// Người nhận
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
			// Tiêu đề
			message.setSubject("Thay đổi mật khẩu");
			// Nội dung
			message.setContent("<html lang=\"en\">\r\n" + "<body>\r\n" + "    <h3>Bạn đang thay đổi mật khẩu</h3>\r\n"
					+ "    <p style=\"color: red;font-size: 17px;\">Mã xác thực là: </p>\r\n" + "</body>\r\n"
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
	public boolean checkEmailMatchesAccount(String tenTaiKhoan,String gmail) {
		Connection con = JDBCUtil.getConnection();
		String gmail1 = "";
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM account_user WHERE tenTaiKhoan like '%"+tenTaiKhoan+"%'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				gmail1 = rs.getString("gmail");
				if(gmail.equals(gmail1)) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public void changePassword() {
			QuenMatKhau thayDoiMatKhau = new QuenMatKhau();
			
			try {
			    Connection con = JDBCUtil.getConnection();
			    String sql = "UPDATE account_user SET matKhau = ? WHERE tenTaiKhoan = ?";
			    PreparedStatement st = con.prepareStatement(sql);
			    st.setString(1, jtextfield_matKhau.getText()); // Đặt mật khẩu mới vào tham số 1
			    st.setString(2, jtextfield_tenTk.getText()); // Đặt tên tài khoản vào tham số 2
			    int rowsUpdated = st.executeUpdate();
			    if (rowsUpdated > 0) {
			        JOptionPane.showMessageDialog(thayDoiMatKhau, "Đổi mật khẩu thành công!");
			        
			    } else {
			        JOptionPane.showMessageDialog(thayDoiMatKhau, "Lỗi! Không thể đổi mật khẩu.");
			    }
			    st.close();
			    con.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			}
			
		
	
	}
}
