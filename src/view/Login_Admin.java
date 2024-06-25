package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;

import database.JDBCUtil;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.DropMode;
import javax.swing.border.MatteBorder;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPasswordField;

public class Login_Admin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtextField_account;
	private JPasswordField jtextField_pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Admin frame = new Login_Admin();
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
	public Login_Admin() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\tieude.png"));
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(100, 100, 495, 399);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 0));
		panel.setBounds(0, 0, 241, 371);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 128, 0));
		lblNewLabel.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\admin2.png"));
		lblNewLabel.setBounds(89, 126, 73, 82);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Đăng nhập");
		lblNewLabel_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_1.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 17));
		lblNewLabel_1.setBounds(307, 27, 106, 30);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tên tài khoản");
		lblNewLabel_2.setForeground(new Color(0, 128, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(251, 84, 80, 14);
		contentPane.add(lblNewLabel_2);
		
		jtextField_account = new JTextField();
		jtextField_account.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				jtextField_account.setBackground(new Color(240,255,240));
			}
		});
		jtextField_account.setBackground(new Color(240, 255, 240));
		jtextField_account.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 128, 0)));
		jtextField_account.setBounds(251, 109, 207, 30);
		contentPane.add(jtextField_account);
		jtextField_account.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Mật khẩu");
		lblNewLabel_2_1.setForeground(new Color(0, 128, 0));
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(251, 147, 80, 14);
		contentPane.add(lblNewLabel_2_1);
		
		JButton jbutton_dangNhap = new JButton("Đăng nhập");
		jbutton_dangNhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_dangNhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dangNhapHeThong();
			}
		});
		jbutton_dangNhap.setFont(new Font("Times New Roman", Font.BOLD, 12));
		jbutton_dangNhap.setFocusPainted(false);
		jbutton_dangNhap.setAlignmentY(Component.TOP_ALIGNMENT);
		jbutton_dangNhap.setHorizontalTextPosition(SwingConstants.LEFT);
		jbutton_dangNhap.setBackground(new Color(0, 128, 0));
		jbutton_dangNhap.setForeground(new Color(255, 255, 255));
		jbutton_dangNhap.setBounds(251, 225, 207, 30);
		contentPane.add(jbutton_dangNhap);
		
		jtextField_pw = new JPasswordField();
		jtextField_pw.setBackground(new Color(240, 255, 240));
		jtextField_pw.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 128, 0)));
		jtextField_pw.setColumns(10);
		jtextField_pw.setBounds(251, 172, 207, 30);
		contentPane.add(jtextField_pw);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BatDau bd = new BatDau();
				dispose();
				bd.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\comeback.png"));
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton.setBackground(new Color(255, 255, 240));
		btnNewButton.setBounds(316, 321, 81, 28);
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);
	}
	public void dangNhapHeThong() {
		String username = jtextField_account.getText();
		String password = new String (jtextField_pw.getPassword());
		
		
		
		if(username.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống");
			return;
		}
		if(password.equals("")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
			return;
		}
		if(kiemTraTaiKhoan(username, password)) {
			JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
			this.setVisible(false);
			Server gui_Admin = new Server();
			gui_Admin.setVisible(true);
			
		}else {
			JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu");
		}
	}
	public boolean kiemTraTaiKhoan(String ten, String matKhau) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM account_admin WHERE tenTaiKhoan='"+ten+"' AND matKhau='"+matKhau+"'";
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()) {
				con.close();
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
