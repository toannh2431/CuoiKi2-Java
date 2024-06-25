package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import database.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CapNhatPhieuMuon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField jtextField_sp;
	public JTextField jtextField_maDg;
	public JTextField jtextField_tenDg;
	public JTextField jtextField_ngayMuon;
	private QuanLiPhieuMuon qlphieuMuon;
	public JDateChooser jDateChooser_hanTra;
	private Object selectedDate;
	private Date formattedSelectedDate;
	public JComboBox jcomboBox_trangThai;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiPhieuMuon qlLiPhieuMuon = new QuanLiPhieuMuon();
					CapNhatPhieuMuon frame = new CapNhatPhieuMuon(qlLiPhieuMuon);
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
	public CapNhatPhieuMuon(QuanLiPhieuMuon qlphieuMuon) {
		setResizable(false);
		this.qlphieuMuon = qlphieuMuon;
		setBounds(100, 100, 355, 452);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cập nhật phiếu mượn");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(67, 11, 216, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Số phiếu");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 59, 77, 21);
		contentPane.add(lblNewLabel_1);
		
		jtextField_sp = new JTextField();
		jtextField_sp.setEditable(false);
		jtextField_sp.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_sp.setBounds(97, 56, 216, 33);
		contentPane.add(jtextField_sp);
		jtextField_sp.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã độc giả");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 107, 77, 21);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tên độc giả");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(10, 160, 77, 21);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ngày mượn");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(10, 212, 77, 21);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Hạn trả");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(10, 267, 77, 21);
		contentPane.add(lblNewLabel_1_4);
		
		jtextField_maDg = new JTextField();
		jtextField_maDg.setEditable(false);
		jtextField_maDg.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_maDg.setColumns(10);
		jtextField_maDg.setBounds(97, 107, 216, 33);
		contentPane.add(jtextField_maDg);
		
		jtextField_tenDg = new JTextField();
		jtextField_tenDg.setEditable(false);
		jtextField_tenDg.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_tenDg.setColumns(10);
		jtextField_tenDg.setBounds(97, 160, 216, 33);
		contentPane.add(jtextField_tenDg);
		
		jtextField_ngayMuon = new JTextField();
		jtextField_ngayMuon.setEditable(false);
		jtextField_ngayMuon.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_ngayMuon.setColumns(10);
		jtextField_ngayMuon.setBounds(97, 212, 216, 33);
		contentPane.add(jtextField_ngayMuon);
		
		jDateChooser_hanTra = new JDateChooser();
		// Cấu hình định dạng ngày tháng
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		jDateChooser_hanTra.setDateFormatString("yyyy-MM-dd");

		// Lấy ngày được chọn từ JDateChooser

		if (selectedDate != null) {
		    // Định dạng ngày tháng thành số
		    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		    SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
		    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		    String month = monthFormat.format(selectedDate);
		    String day = dayFormat.format(selectedDate);
		    String year = yearFormat.format(selectedDate);

		    // Kết hợp thành chuỗi ngày/tháng/năm
		    String formattedDate = day + "/" + month + "/" + year;

		    // Gán chuỗi đã định dạng vào JFormattedTextField của JDateChooser
		    try {
		         formattedSelectedDate = dateFormat.parse(formattedDate);
		        jDateChooser_hanTra.setDate(formattedSelectedDate);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		jDateChooser_hanTra.setBounds(97, 264, 216, 33);
		contentPane.add(jDateChooser_hanTra);
		
		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhat();
				dispose();
				qlphieuMuon.displayPhieuMuon();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(0, 0, 255));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(114, 369, 106, 33);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Hạn trả");
		lblNewLabel_1_4_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(10, 313, 77, 21);
		contentPane.add(lblNewLabel_1_4_1);
		
		jcomboBox_trangThai = new JComboBox();
		jcomboBox_trangThai.setModel(new DefaultComboBoxModel(new String[] {"", "Đang mượn", "Đã trả", "Quá hạn"}));
		jcomboBox_trangThai.setFont(new Font("Arial", Font.PLAIN, 13));
		jcomboBox_trangThai.setBounds(97, 312, 216, 33);
		contentPane.add(jcomboBox_trangThai);
		setLocationRelativeTo(null);
	}
	public void capNhat() {
		int soPhieu = Integer.valueOf(jtextField_sp.getText());
		String trangThai = (String) jcomboBox_trangThai.getSelectedItem();
		 selectedDate = jDateChooser_hanTra.getDate(); 
		 if (selectedDate != null) {
		        java.sql.Date hanTra = new java.sql.Date(((Date) selectedDate).getTime());
		        Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "Update phieumuon set hanTra = '"+hanTra+"', trangThai= '"+trangThai+"' WHERE soPhieu = '"+soPhieu+"'";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(this, "Cập nhật thành công !");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 }
	}
}
