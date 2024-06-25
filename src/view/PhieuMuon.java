package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DAO.PhieuMuonDAO;
import database.JDBCUtil;
import model.PhieuMuonModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class PhieuMuon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField jtextField_soPhieu;
	public JTextField jtextField_maDg;
	public JTextField jtextField_tenDg;
	public JTextField jtextField_ngayMuon;
	private QuanLiPhieuMuon qLiPhieuMuon;
	private JComboBox jcomboBox_trangThai;
	private Date selectedDate;
	private JDateChooser jDateChooser_hanTra;
	private Date formattedSelectedDate;
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
					PhieuMuon frame = new PhieuMuon(qlLiPhieuMuon);
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
	public PhieuMuon(QuanLiPhieuMuon qLiPhieuMuon) {
		setResizable(false);
		this.qLiPhieuMuon = qLiPhieuMuon;
		setBounds(100, 100, 394, 501);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 255, 250));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(24, 11, 333, 440);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phiếu mượn");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(109, 9, 121, 31);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Số phiếu");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 52, 64, 23);
		panel.add(lblNewLabel_1);
		
		jtextField_soPhieu = new JTextField();
		jtextField_soPhieu.setEditable(false);
		jtextField_soPhieu.setBounds(98, 51, 212, 31);
		panel.add(jtextField_soPhieu);
		jtextField_soPhieu.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã độc giả");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 112, 90, 23);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Tên độc giả");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(10, 169, 80, 23);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Ngày mượn");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(10, 227, 90, 23);
		panel.add(lblNewLabel_1_3);
		
		jtextField_maDg = new JTextField();
		jtextField_maDg.setEditable(false);
		jtextField_maDg.setColumns(10);
		jtextField_maDg.setBounds(98, 109, 212, 31);
		panel.add(jtextField_maDg);
		
		jtextField_tenDg = new JTextField();
		jtextField_tenDg.setEditable(false);
		jtextField_tenDg.setColumns(10);
		jtextField_tenDg.setBounds(98, 166, 212, 31);
		panel.add(jtextField_tenDg);
		
		jtextField_ngayMuon = new JTextField();
		jtextField_ngayMuon.setEditable(false);
		jtextField_ngayMuon.setColumns(10);
		jtextField_ngayMuon.setBounds(98, 224, 212, 31);
		panel.add(jtextField_ngayMuon);
		
		
		
		
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
		jDateChooser_hanTra.setBounds(98, 281, 212, 31);
		panel.add(jDateChooser_hanTra);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Trạng thái");
		lblNewLabel_1_3_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_3_1_1.setBounds(10, 339, 90, 23);
		panel.add(lblNewLabel_1_3_1_1);
		
		 jcomboBox_trangThai = new JComboBox();
		jcomboBox_trangThai.setModel(new DefaultComboBoxModel(new String[] {"", "Đang mượn"}));
		jcomboBox_trangThai.setBounds(98, 336, 212, 31);
		panel.add(jcomboBox_trangThai);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Hạn trả");
		lblNewLabel_1_3_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_3_1.setBounds(10, 281, 90, 23);
		panel.add(lblNewLabel_1_3_1);
		
		JButton jbutton_xacNhan = new JButton("Xác nhận");
		jbutton_xacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhieuMuon phieuMuon = new PhieuMuon(qLiPhieuMuon);
				if (jDateChooser_hanTra.getDate() == null) {
				    JOptionPane.showMessageDialog(phieuMuon, "Vui lòng chọn hạn trả !");
				    return;
				}
				if(jcomboBox_trangThai.getSelectedItem().equals("")) {
					JOptionPane.showMessageDialog(phieuMuon, "Vui lòng chọn trạng thái !");
					return;
				}
				xacNhanMuon();
				dispose();
				qLiPhieuMuon.displayPhieuDk();
				qLiPhieuMuon.displayPhieuMuon();
				qLiPhieuMuon.dm_chiTietPhieuDk.setRowCount(0);
			}
		});
		jbutton_xacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_xacNhan.setBackground(new Color(192, 192, 192));
		jbutton_xacNhan.setFont(new Font("Arial", Font.PLAIN, 14));
		jbutton_xacNhan.setBounds(109, 391, 109, 31);
		panel.add(jbutton_xacNhan);
		setLocationRelativeTo(null);
	}
	public void xacNhanMuon() {
	    int soPhieu = Integer.valueOf(jtextField_soPhieu.getText());
	    String maDg = jtextField_maDg.getText();
	    String tenDg = jtextField_tenDg.getText();
	    java.sql.Date ngayMuon = java.sql.Date.valueOf(jtextField_ngayMuon.getText());

	    selectedDate = jDateChooser_hanTra.getDate(); 

	    if (selectedDate != null) {
	        java.sql.Date hanTra = new java.sql.Date(selectedDate.getTime());
	        String trangThai = jcomboBox_trangThai.getSelectedItem() + "";
	        PhieuMuonModel model = new PhieuMuonModel(soPhieu, maDg, tenDg, ngayMuon, hanTra, trangThai);
	        PhieuMuonDAO dao = new PhieuMuonDAO();
	        dao.insert(model);
	        JOptionPane.showMessageDialog(this, "Mượn thành công");
	        qLiPhieuMuon.xoaPhieuDk();
	    } else {
	        JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày hạn trả");
	    }
	}
	
	
	
}
