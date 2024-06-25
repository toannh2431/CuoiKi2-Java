package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.border.SoftBevelBorder;

import DAO.DocGiaDAO;
import database.JDBCUtil;
import model.DocGiaModel;

import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class Login_User extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JPasswordField jtextfield_pw ;
	private JTextField jtextfield_user;
	private JPanel contentPane;
	public String username;
	private DocGiaModel docGiaModel;
	private String tenTk;
	private String gioiTinh;
	private String hoTen;
	private String gmail;
	private String sdt;
	public static String tenTaiKhoan;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_User frame = new Login_User();
					frame.setLocationRelativeTo(null);
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
	public Login_User() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\nd.png"));
		setTitle("Đăng nhập hệ thống");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 505, 481);
		this.setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setLocation(new Point(1000, 2000));
		contentPane.setBackground(new Color(240, 240, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(283, 0, 216, 455);
		panel.setBackground(new Color(255, 215, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Chào bạn!");
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_3.setBounds(57, 95, 98, 39);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Bạn chưa có tài khoản");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(53, 131, 132, 14);
		panel.add(lblNewLabel_4);
		
		JButton jbutton_dkngay = new JButton("Đăng ký ngay");
		jbutton_dkngay.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_dkngay.setFocusPainted(false);
		jbutton_dkngay.setFont(new Font("Arial", Font.BOLD, 11));
		jbutton_dkngay.setForeground(new Color(255, 255, 255));
		jbutton_dkngay.setBackground(new Color(0, 128, 0));
		jbutton_dkngay.setBounds(46, 156, 122, 23);
		jbutton_dkngay.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Register_User dk = new Register_User();
				dispose();
				dk.setVisible(true);
			}
		});
		panel.add(jbutton_dkngay);
		
		JLabel lblNewLabel = new JLabel("Đăng Nhập");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setForeground(new Color(0, 128, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(98, 89, 96, 21);
		contentPane.add(lblNewLabel);
		
		jtextfield_user = new JTextField();
		jtextfield_user.setFont(new Font("Arial", Font.PLAIN, 11));
		jtextfield_user.setBackground(new Color(245, 245, 245));
		jtextfield_user.setBounds(72, 151, 179, 28);
		contentPane.add(jtextfield_user);
		jtextfield_user.setColumns(10);
		jtextfield_user.setForeground(Color.gray);
		jtextfield_user.setText("Nhập tên tài khoản");
		jtextfield_user.addFocusListener(new FocusListener() {
			String text = "Nhập tên tài khoản";
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(jtextfield_user.getText().isEmpty()) {
					jtextfield_user.setForeground(Color.gray);
					jtextfield_user.setText(text);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (jtextfield_user.getText().equals(text))
		        {
					jtextfield_user.setText("");
					jtextfield_user.setForeground(Color.BLACK);
		        }
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(38, 151, 35, 28);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\user1.png"));
		
		jtextfield_pw = new JPasswordField();
		jtextfield_pw.setFont(new Font("Arial", Font.PLAIN, 11));
		jtextfield_pw.setColumns(10);
		jtextfield_pw.setBackground(new Color(245, 245, 245));
		jtextfield_pw.setBounds(72, 190, 179, 28);
		jtextfield_pw.setForeground(Color.gray);
		jtextfield_pw.setText("*****************");
		jtextfield_pw.addFocusListener(new FocusListener() {
			String text = "*****************";
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(jtextfield_pw.getText().isEmpty()) {
					jtextfield_pw.setForeground(Color.gray);
					jtextfield_pw.setText(text);
				}
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if (jtextfield_pw.getText().equals(text))
		        {
					jtextfield_pw.setText("");
					jtextfield_pw.setForeground(Color.BLACK);
		        }
			}
		});
		contentPane.add(jtextfield_pw);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBounds(38, 190, 35, 28);
		contentPane.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\Password1.png"));
		lblNewLabel_2.setBounds(10, 0, 15, 25);
		panel_1_1.add(lblNewLabel_2);
		
		
		JLabel jlabel_quenMatKhau = new JLabel("Bạn quên mật khẩu?");
		jlabel_quenMatKhau.setFont(new Font("Arial", Font.PLAIN, 12));
		jlabel_quenMatKhau.setBounds(98, 229, 126, 21);
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		jlabel_quenMatKhau.setCursor(cursor);
		jlabel_quenMatKhau.addMouseListener(new MouseAdapter() {
		 
			
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				jlabel_quenMatKhau.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				jlabel_quenMatKhau.setForeground(Color.red);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				QuenMatKhau layMK = new QuenMatKhau();
				layMK.setVisible(true);
				
			}
		});
		
		
		contentPane.add(jlabel_quenMatKhau);
		
		JButton jButton_dangnhap = new JButton("Đăng nhập");
		jButton_dangnhap.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jButton_dangnhap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dangNhapHeThong();
			}
		});
		jButton_dangnhap.setFocusPainted(false);
		jButton_dangnhap.setFont(new Font("Arial", Font.BOLD, 11));
		jButton_dangnhap.setBackground(new Color(0, 128, 0));
		jButton_dangnhap.setForeground(new Color(255, 255, 255));
		jButton_dangnhap.setBounds(98, 261, 106, 23);
		contentPane.add(jButton_dangnhap);
		
		JButton btnNewButton = new JButton("Quay lại");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BatDau bd = new BatDau();
				dispose();
				bd.setVisible(true);
			}
		});
		btnNewButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnNewButton.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\comeback.png"));
		btnNewButton.setBackground(new Color(255, 255, 240));
		btnNewButton.setBounds(10, 403, 81, 28);
		contentPane.add(btnNewButton);
		
	}
	public void dangNhapHeThong() {
		username = jtextfield_user.getText();
		String password = new String (jtextfield_pw.getPassword());
		String text = "Nhập tên tài khoản";
		String text2 = "*****************";
		
		if(username.equals("") || username.equals(text)) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống");
			return;
		}
		if(password.equals("") || password.equals(text2)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống");
			return;
		}
		if(kiemTraTaiKhoan(username, password)) {
			JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
			this.setVisible(false);
			tenTaiKhoan = username;
			
			Client user = new Client();
			user.setVisible(true);
		
			
		}else {
			JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu");
		}
	}
	public boolean kiemTraTaiKhoan(String ten, String matKhau) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM account_user WHERE tenTaiKhoan='"+ten+"' AND matKhau='"+matKhau+"'";
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
	
	

