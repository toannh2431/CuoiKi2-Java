package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import database.JDBCUtil;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CapNhatDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField jtextField_tenTaiKhoan;
	public JTextField jtextField_tenDocGia;
	public JTextField jtextField_gmail;
	public JTextField jtextField_sdt;
	private QuanLiDocGia quanLiDocGia;
	public JRadioButton jradio_nam;
	public JRadioButton jradio_nu;
	private ButtonGroup buttonGroup;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiDocGia qlDocGia = new QuanLiDocGia();
					CapNhatDocGia frame = new CapNhatDocGia(qlDocGia);
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
	public CapNhatDocGia(QuanLiDocGia quanLiDocGia) {
		this.quanLiDocGia = quanLiDocGia;
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\updateReader.png"));
		setResizable(false);
		setBounds(100, 100, 368, 444);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(64, 224, 208));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(64, 224, 208));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "C\u1EADp nh\u1EADt \u0111\u1ED9c gi\u1EA3", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 255)));
		panel.setBounds(10, 11, 330, 383);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên tài khoản");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 41, 87, 21);
		panel.add(lblNewLabel);
		
		jtextField_tenTaiKhoan = new JTextField();
		jtextField_tenTaiKhoan.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_tenTaiKhoan.setEditable(false);
		jtextField_tenTaiKhoan.setBounds(124, 37, 196, 28);
		panel.add(jtextField_tenTaiKhoan);
		jtextField_tenTaiKhoan.setColumns(10);
		
		jtextField_tenDocGia = new JTextField();
		jtextField_tenDocGia.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_tenDocGia.setColumns(10);
		jtextField_tenDocGia.setBounds(124, 92, 196, 28);
		panel.add(jtextField_tenDocGia);
		
		JLabel lblNewLabel_1 = new JLabel("Tên độc giả");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 96, 87, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Giới tính");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 151, 87, 16);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Gmail");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 200, 87, 21);
		panel.add(lblNewLabel_3);
		
		jradio_nam = new JRadioButton("Nam");
		jradio_nam.setBackground(new Color(64, 224, 208));
		jradio_nam.setFont(new Font("Arial", Font.PLAIN, 13));
		jradio_nam.setBounds(124, 145, 58, 28);
		panel.add(jradio_nam);
		
		jradio_nu = new JRadioButton("Nữ");
		jradio_nu.setBackground(new Color(64, 224, 208));
		jradio_nu.setFont(new Font("Arial", Font.PLAIN, 13));
		jradio_nu.setBounds(195, 145, 58, 28);
		panel.add(jradio_nu);
		buttonGroup = new ButtonGroup();
		buttonGroup.add(jradio_nam);
		buttonGroup.add(jradio_nu);
		
		
		jtextField_gmail = new JTextField();
		jtextField_gmail.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_gmail.setColumns(10);
		jtextField_gmail.setBounds(124, 196, 196, 28);
		panel.add(jtextField_gmail);
		
		JLabel lblNewLabel_3_1 = new JLabel("Số điện thoại");
		lblNewLabel_3_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(10, 255, 87, 21);
		panel.add(lblNewLabel_3_1);
		
		jtextField_sdt = new JTextField();
		jtextField_sdt.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_sdt.setColumns(10);
		jtextField_sdt.setBounds(124, 251, 196, 28);
		panel.add(jtextField_sdt);
		
		JButton jbutton_capNhat = new JButton("Cập nhật");
		jbutton_capNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CapNhatDocGia capNhatDocGia = new CapNhatDocGia(quanLiDocGia);
				if(jtextField_tenDocGia.getText().equals("")) {
					JOptionPane.showMessageDialog(capNhatDocGia, "Tên độc giả không được để trống!");
					return;
				}
				if(!jradio_nam.isSelected() && !jradio_nu.isSelected()) {
					JOptionPane.showMessageDialog(capNhatDocGia, "Vui lòng chọn giới tính!");
					return;
				}
				if(jtextField_gmail.getText().equals("")) {
					JOptionPane.showMessageDialog(capNhatDocGia, "Gmail không được để trống!");
					return;
				}
				if(jtextField_sdt.getText().equals("")) {
					JOptionPane.showMessageDialog(capNhatDocGia, "Số điện thoại không được để trống!");
					return;
				}
				updateReader();
				dispose();
				quanLiDocGia.displayReader();
				quanLiDocGia.jtextField_timKiem.setText("");
			}
		});
		jbutton_capNhat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_capNhat.setForeground(new Color(255, 255, 255));
		jbutton_capNhat.setBackground(new Color(105, 105, 105));
		jbutton_capNhat.setFocusPainted(false);
		jbutton_capNhat.setFont(new Font("Arial", Font.PLAIN, 13));
		jbutton_capNhat.setBounds(59, 323, 89, 28);
		panel.add(jbutton_capNhat);
		
		JButton jbutton_thoat = new JButton("Thoát");
		jbutton_thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				quanLiDocGia.displayReader();
			}
		});
		jbutton_thoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_thoat.setForeground(new Color(255, 255, 255));
		jbutton_thoat.setBackground(new Color(105, 105, 105));
		jbutton_thoat.setFocusPainted(false);
		jbutton_thoat.setFont(new Font("Arial", Font.PLAIN, 13));
		jbutton_thoat.setBounds(183, 323, 89, 28);
		panel.add(jbutton_thoat);
		setLocationRelativeTo(null);
	}
	public void updateReader() {
		int row = 0;
		
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE docgia set tenDocGia=?, gioiTinh=?, gmail=?, soDienThoai=? WHERE tenTaiKhoan = ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,jtextField_tenDocGia.getText());
			if(jradio_nam.isSelected()) {
				st.setString(2,"Nam");
			}else {
				st.setString(2,"Nữ");
			}
			
			st.setString(3,jtextField_gmail.getText());
			st.setString(4,jtextField_sdt.getText());
			st.setString(5,jtextField_tenTaiKhoan.getText());
			row = st.executeUpdate();
			if(row>0) {
				JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}

