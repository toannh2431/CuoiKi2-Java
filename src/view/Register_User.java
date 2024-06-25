package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DocGiaDAO;
import database.JDBCUtil;
import model.DocGiaModel;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class Register_User extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtextfield_pw;
	private JTextField jtextfield_hoTen;
	private JTextField jtextfield_user;
	private JTextField jtextfield_sdt;
	private JTextField jtextfield_email;
	private JRadioButton jradio_nam;
	private JRadioButton jradio_nu;
	private ButtonGroup buttonGroup;
	private JLabel jlabel_tb3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register_User frame = new Register_User();
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
	public Register_User() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\register.png"));
		setTitle("Đăng ký tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 484);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		panel.setBounds(0, 0, 206, 459);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Chào mừng bạn trở lại");
		lblNewLabel_3.setBounds(10, 116, 206, 26);
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Bạn đã có tài khoản");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(44, 153, 132, 14);
		panel.add(lblNewLabel_4);
		
		JButton jButton_DnNgay = new JButton("Đăng nhập ngay");
		jButton_DnNgay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton_DnNgay.setFocusPainted(false);
		jButton_DnNgay.setFont(new Font("Arial", Font.BOLD, 11));
		jButton_DnNgay.setForeground(Color.WHITE);
		jButton_DnNgay.setBackground(new Color(0, 128, 0));
		jButton_DnNgay.setBounds(32, 178, 138, 23);
		jButton_DnNgay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login_User dn = new Login_User();
				dispose();
				dn.setVisible(true);
			}
		});
		panel.add(jButton_DnNgay);
		
		JLabel lblngK = new JLabel("Đăng ký");
		lblngK.setForeground(new Color(0, 128, 0));
		lblngK.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblngK.setBounds(311, 11, 96, 21);
		contentPane.add(lblngK);
		
		jtextfield_pw = new JTextField();
		jtextfield_pw.setFont(new Font("Arial", Font.PLAIN, 11));
		jtextfield_pw.setColumns(10);
		jtextfield_pw.setBackground(new Color(245, 245, 245));
		jtextfield_pw.setBounds(236, 122, 219, 28);
		
		contentPane.add(jtextfield_pw);
		
		jtextfield_hoTen = new JTextField();
		jtextfield_hoTen.setFont(new Font("Arial", Font.PLAIN, 11));
		
		jtextfield_hoTen.setColumns(10);
		jtextfield_hoTen.setBackground(new Color(245, 245, 245));
		jtextfield_hoTen.setBounds(236, 194, 219, 28);
			
		
		contentPane.add(jtextfield_hoTen);
		
		jtextfield_user = new JTextField();
		jtextfield_user.setFont(new Font("Arial", Font.PLAIN, 11));
		jtextfield_user.setColumns(10);
		jtextfield_user.setBackground(new Color(245, 245, 245));
		jtextfield_user.setBounds(236, 55, 219, 28);
		contentPane.add(jtextfield_user);
		
		JButton jButton_dangky = new JButton("Đăng ký");
		jButton_dangky.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton_dangky.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register_User dk = new Register_User();
				String username = jtextfield_user.getText();
				if(jtextfield_user.getText().equals("")) {
					JOptionPane.showMessageDialog(dk, "Tài khoản không được để trống!");
					return;
				}
				if(jtextfield_pw.getText().equals("")) {
					JOptionPane.showMessageDialog(dk, "Mật khẩu không được để trống!");
					return;
				}
				if(jtextfield_hoTen.getText().equals("")) {
					JOptionPane.showMessageDialog(dk, "Họ tên không được để trống!");
					return;
				}
				if(!jradio_nam.isSelected() && !jradio_nu.isSelected()) {
					JOptionPane.showMessageDialog(dk, "Vui lòng chọn giới tính");
					return;
				}
				if(jtextfield_email.getText().equals("")) {
					JOptionPane.showMessageDialog(dk, "Gmail không được để trống!");
					return;
				}
				if(jtextfield_sdt.getText().equals("")) {
					JOptionPane.showMessageDialog(dk, "Số điện thoại không được để trống!");
					return;
				}
				if(!isGmailFormat(jtextfield_email.getText())) {
					jlabel_tb3.setText("Gmail không đúng định dạng.");
					return;
				}
				dangKy(username);
				
			}
		});
		jButton_dangky.setFocusPainted(false);
		jButton_dangky.setFont(new Font("Arial", Font.BOLD, 12));
		jButton_dangky.setForeground(Color.WHITE);
		jButton_dangky.setBackground(new Color(0, 128, 0));
		jButton_dangky.setBounds(293, 411, 108, 23);
		contentPane.add(jButton_dangky);
		
		JLabel lblNewLabel = new JLabel("Tên tài khoản");
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(236, 35, 77, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setForeground(new Color(0, 128, 0));
		lblMtKhu.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMtKhu.setBounds(236, 100, 77, 21);
		contentPane.add(lblMtKhu);
		
		JLabel lblNewLabel_1_1 = new JLabel("Họ tên");
		lblNewLabel_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(236, 172, 115, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Giới tính");
		lblNewLabel_1_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(236, 233, 115, 21);
		contentPane.add(lblNewLabel_1_1_1);
		
		jtextfield_sdt = new JTextField();
		jtextfield_sdt.setFont(new Font("Arial", Font.PLAIN, 11));
		jtextfield_sdt.setColumns(10);
		jtextfield_sdt.setBackground(new Color(245, 245, 245));
		jtextfield_sdt.setBounds(236, 357, 219, 28);
		contentPane.add(jtextfield_sdt);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tài khoản email");
		lblNewLabel_1_1_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(236, 265, 115, 21);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		jtextfield_email = new JTextField();
		jtextfield_email.setFont(new Font("Arial", Font.PLAIN, 11));
		jtextfield_email.setColumns(10);
		jtextfield_email.setBackground(new Color(245, 245, 245));
		jtextfield_email.setBounds(236, 290, 219, 28);
		contentPane.add(jtextfield_email);
		
		
		jradio_nam = new JRadioButton("Nam");
		jradio_nam.setFocusPainted(false);
		jradio_nam.setFont(new Font("Arial", Font.PLAIN, 12));
		jradio_nam.setBounds(321, 232, 61, 23);
		contentPane.add(jradio_nam);
		
		jradio_nu = new JRadioButton("Nữ");
		jradio_nu.setFocusPainted(false);
		jradio_nu.setFont(new Font("Arial", Font.PLAIN, 12));
		jradio_nu.setBounds(401, 232, 53, 23);
		contentPane.add(jradio_nu);
		buttonGroup = new ButtonGroup();
        buttonGroup.add(jradio_nam);
        buttonGroup.add(jradio_nu);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Số điện thoại");
		lblNewLabel_1_1_1_1_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1_1.setBounds(236, 336, 115, 21);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(169, 169, 169));
		separator.setBounds(236, 264, 219, 2);
		contentPane.add(separator);
		
		JLabel jlabel_tb1 = new JLabel("");
		jlabel_tb1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb1.setBounds(236, 87, 219, 14);
		contentPane.add(jlabel_tb1);
		
		JLabel jlabel_tb2 = new JLabel("");
		jlabel_tb2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb2.setBounds(236, 157, 219, 14);
		contentPane.add(jlabel_tb2);
		
		jlabel_tb3 = new JLabel("");
		jlabel_tb3.setForeground(new Color(255, 0, 0));
		jlabel_tb3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb3.setBounds(236, 321, 219, 14);
		contentPane.add(jlabel_tb3);
		
		JLabel jlabel_tb1_3 = new JLabel("");
		jlabel_tb1_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jlabel_tb1_3.setBounds(236, 386, 219, 14);
		contentPane.add(jlabel_tb1_3);
	}
	public void dangKyThongTin() {
		String username = jtextfield_user.getText();
		String hoTen = jtextfield_hoTen.getText();
		String gioiTinh = "";
		if(jradio_nam.isSelected()) {
			gioiTinh = "Nam";
		}else{
			gioiTinh = "Nữ";
		}
		String gmail = jtextfield_email.getText();
		String sdt = jtextfield_sdt.getText();
		
		DocGiaModel dg = new DocGiaModel(username, hoTen, gioiTinh, gmail, sdt);
		DocGiaDAO dao = new DocGiaDAO();
		dao.insert(dg);
	}
	public void dangKyTaiKhoan()  {
		String username = jtextfield_user.getText();
		String password = jtextfield_pw.getText();
		String gmail = jtextfield_email.getText();
		Connection con = JDBCUtil.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			String sql = "INSERT INTO account_user(tenTaiKhoan,matKhau,gmail) VALUES(\""+username+"\",\""+password+"\",\""+gmail+"\")";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void dangKy(String tenTaiKhoan) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM docgia";
			ResultSet rs = st.executeQuery(sql);
			String username = "";
			while (rs.next()) {
				username = rs.getString("tenTaiKhoan");
				if(tenTaiKhoan.equals(username)) {
					Register_User dk = new Register_User();
					JOptionPane.showMessageDialog(dk, "Tên tài khoản đã được sử dụng");
					return;
				}
			}
			
				Register_User dk = new Register_User();
				dangKyTaiKhoan();
				dangKyThongTin();
				JOptionPane.showMessageDialog(dk, "Đăng ký thành công!");
				jtextfield_user.setText("");
				jtextfield_email.setText("");
				jtextfield_hoTen.setText("");
				jtextfield_pw.setText("");
				jtextfield_sdt.setText("");
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
		
		
	
	public static boolean isGmailFormat(String email) {
		String gmailPattern = "^[a-zA-Z0-9._%+-]+@(gmail\\.com|googlemail\\.com|vku\\.udn\\.vn)$";

        Pattern pattern = Pattern.compile(gmailPattern);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
	}
	}

