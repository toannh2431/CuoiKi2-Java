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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class CapNhatSach extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField jtextField_maSach;
	public JTextField jtextField_tenSach;
	public JTextField jtextField_tacGia;
	public QuanLiSach quanLiSach;
	public JComboBox jcomboBox_theLoai;
	public JComboBox jcomboBox_namXuatBan;
	public JSpinner soLuong;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiSach quanLiSach = new QuanLiSach();
					CapNhatSach frame = new CapNhatSach(quanLiSach);
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
	public CapNhatSach(QuanLiSach quanLiSach) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\updateBook.png"));
		setResizable(false);
		this.quanLiSach = quanLiSach;
		setBounds(100, 100, 405, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel contentPane_1 = new JPanel();
		contentPane_1.setLayout(null);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setBackground(new Color(204, 217, 179));
		contentPane_1.setBounds(0, 0, 399, 448);
		contentPane.add(contentPane_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "C\u1EADp nh\u1EADt", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 128, 0)));
		panel.setBackground(new Color(204, 217, 179));
		panel.setBounds(10, 11, 367, 415);
		contentPane_1.add(panel);
		
		JLabel lblNewLabel = new JLabel("Mã sách");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 37, 75, 25);
		panel.add(lblNewLabel);
		
		jtextField_maSach = new JTextField();
		jtextField_maSach.setEditable(false);
		jtextField_maSach.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_maSach.setColumns(10);
		jtextField_maSach.setBounds(119, 35, 236, 31);
		panel.add(jtextField_maSach);
		
		JLabel lblTnSch = new JLabel("Tên sách");
		lblTnSch.setForeground(Color.BLACK);
		lblTnSch.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTnSch.setBounds(10, 87, 75, 25);
		panel.add(lblTnSch);
		
		JLabel lblThLoi = new JLabel("Thể loại");
		lblThLoi.setForeground(Color.BLACK);
		lblThLoi.setFont(new Font("Arial", Font.PLAIN, 15));
		lblThLoi.setBounds(10, 139, 75, 25);
		panel.add(lblThLoi);
		
		JLabel lblTcGi = new JLabel("Tác giả");
		lblTcGi.setForeground(Color.BLACK);
		lblTcGi.setFont(new Font("Arial", Font.PLAIN, 15));
		lblTcGi.setBounds(10, 185, 75, 25);
		panel.add(lblTcGi);
		
		JLabel lblNmXutBn = new JLabel("Năm xuất bản");
		lblNmXutBn.setForeground(Color.BLACK);
		lblNmXutBn.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNmXutBn.setBounds(10, 237, 99, 25);
		panel.add(lblNmXutBn);
		
		JLabel lblSLng = new JLabel("Số lượng");
		lblSLng.setForeground(Color.BLACK);
		lblSLng.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSLng.setBounds(10, 294, 75, 25);
		panel.add(lblSLng);
		
		JButton jbutton_capNhat = new JButton("Cập nhật");
		jbutton_capNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CapNhatSach ts = new CapNhatSach(quanLiSach);
				
				if (jtextField_tenSach.getText().equals("")) {
					JOptionPane.showMessageDialog(ts, "Vui lòng nhập tên sách!");
					return;
				}
				
				if (jcomboBox_theLoai.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(ts, "Vui lòng chọn thể loại!");
					return;
				}
				if (jtextField_tacGia.getText().equals("")) {
					JOptionPane.showMessageDialog(ts, "Vui lòng nhập tác giả!");
					return;
				}
				if (jcomboBox_namXuatBan.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(ts, "Vui lòng chọn năm xuất bản!");
					return;
				}
				if ((int) soLuong.getValue() <= 0) {
				    JOptionPane.showMessageDialog(ts, "Số lượng không được bằng 0!");
				    return;
				}
				updateBook();
				dispose();
				quanLiSach.displayAllBook();
				quanLiSach.jtextField_timKiem.setText("");
				new Thread(()->{
					Server gui_Admin = new Server();
					
				
				}).start();
			}
		});
		jbutton_capNhat.setForeground(new Color(34, 139, 34));
		jbutton_capNhat.setFont(new Font("Arial", Font.PLAIN, 15));
		jbutton_capNhat.setFocusPainted(false);
		jbutton_capNhat.setBackground(Color.YELLOW);
		jbutton_capNhat.setBounds(69, 373, 115, 31);
		panel.add(jbutton_capNhat);
		
		jtextField_tenSach = new JTextField();
		jtextField_tenSach.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_tenSach.setColumns(10);
		jtextField_tenSach.setBounds(119, 85, 236, 31);
		panel.add(jtextField_tenSach);
		
		jcomboBox_theLoai = new JComboBox();
		jcomboBox_theLoai.setModel(new DefaultComboBoxModel(new String[] {"", "Sách giáo khoa", "Giáo trình", "Sách thiếu nhi", "Sách tâm lý,tình cảm", "Sách tôn giáo", "Sách văn hóa xã hội", "Sách lịch sử", "Sách nấu ăn", "Sách khoa học", "Tiểu thuyết", "Truyện tranh"}));
		jcomboBox_theLoai.setFont(new Font("Arial", Font.PLAIN, 13));
		jcomboBox_theLoai.setBounds(119, 137, 236, 31);
		panel.add(jcomboBox_theLoai);
		
		jtextField_tacGia = new JTextField();
		jtextField_tacGia.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_tacGia.setColumns(10);
		jtextField_tacGia.setBounds(119, 183, 236, 31);
		panel.add(jtextField_tacGia);
		
		jcomboBox_namXuatBan = new JComboBox();
		jcomboBox_namXuatBan.setModel(new DefaultComboBoxModel(new String[] {"", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"}));
		jcomboBox_namXuatBan.setFont(new Font("Arial", Font.PLAIN, 13));
		jcomboBox_namXuatBan.setBounds(119, 235, 236, 31);
		panel.add(jcomboBox_namXuatBan);
		
		JButton jbutton_thoat = new JButton("Thoát");
		jbutton_thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				quanLiSach.displayAllBook();
			}
		});
		jbutton_thoat.setForeground(new Color(0, 128, 0));
		jbutton_thoat.setFont(new Font("Arial", Font.PLAIN, 15));
		jbutton_thoat.setBackground(Color.YELLOW);
		jbutton_thoat.setBounds(207, 373, 89, 31);
		panel.add(jbutton_thoat);
		SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
		soLuong = new JSpinner(spinnerModel);
		soLuong.setFont(new Font("Arial", Font.PLAIN, 13));
		soLuong.setBounds(121, 294, 51, 25);
		panel.add(soLuong);
		setLocationRelativeTo(null);
	}
	public void updateBook() {
		int row = 0;
		
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "UPDATE sach set tenSach=?, soLuong=?, theLoai=?, tacGia=?, namXuatBan=? WHERE maSach= ? ";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,jtextField_tenSach.getText());
			st.setInt(2, (int) soLuong.getValue());
			st.setString(3,jcomboBox_theLoai.getSelectedItem()+"");
			st.setString(4,jtextField_tacGia.getText());
			st.setString(5,jcomboBox_namXuatBan.getSelectedItem()+"");
			st.setString(6,jtextField_maSach.getText());
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
