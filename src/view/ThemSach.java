	package view;
	
	import java.awt.EventQueue;
	
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import javax.swing.border.EmptyBorder;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	
	import java.awt.Color;
	import java.awt.Font;
	import javax.swing.JTextField;
	import javax.swing.SpinnerModel;
	import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	import javax.swing.border.LineBorder;
	import javax.swing.border.TitledBorder;
	
	import DAO.SachDAO;
import database.JDBCUtil;
import model.SachModel;
	
	import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
	import java.awt.Window.Type;
	import javax.swing.JSpinner;
import java.awt.Cursor;
import java.awt.Toolkit;
	
	public class ThemSach extends JFrame {
	
		private static final long serialVersionUID = 1L;
		private JPanel contentPane;
		private JTextField jtextField_maSach;
		private JTextField jtextField_tenSach;
		private JTextField jtextField_tacGia;
		private JComboBox jcomboBox_namXuatBan;
		private JSpinner soLuong;
		private JComboBox jcomboBox_theLoai;
		private QuanLiSach qlSach;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						QuanLiSach qlSach = new QuanLiSach();
						ThemSach frame = new ThemSach(qlSach);
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
		public ThemSach(QuanLiSach qlSach) {
			setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\addbook.png"));
			this.qlSach = qlSach;
			setResizable(false);
			setBounds(100, 100, 403, 476);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(204, 217, 179));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(204, 217, 179));
			panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Th\u00EAm s\u00E1ch m\u1EDBi", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 128, 0)));
			panel.setBounds(10, 11, 366, 415);
			contentPane.add(panel);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Mã sách");
			lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setBounds(10, 37, 75, 25);
			panel.add(lblNewLabel);
			
			jtextField_maSach = new JTextField();
			jtextField_maSach.setFont(new Font("Arial", Font.PLAIN, 13));
			jtextField_maSach.setBounds(119, 35, 236, 31);
			panel.add(jtextField_maSach);
			jtextField_maSach.setColumns(10);
			
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
			
			JButton jbutton_themSach = new JButton("Thêm sách\r\n");
			jbutton_themSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbutton_themSach.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ThemSach ts = new ThemSach(qlSach);
					if (jtextField_maSach.getText().equals("")) {
						JOptionPane.showMessageDialog(ts, "Vui lòng nhập mã sách!");
						return;
					}
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
					if(kiemTraTrungMaSach()) {
						JOptionPane.showMessageDialog(ts, "Mã sách đã tồn tại !");
						return;
					}
					AddSach();
					
				}
			});
			jbutton_themSach.setFocusPainted(false);
			jbutton_themSach.setForeground(new Color(34, 139, 34));
			jbutton_themSach.setBackground(new Color(255, 255, 0));
			jbutton_themSach.setFont(new Font("Arial", Font.PLAIN, 15));
			jbutton_themSach.setBounds(69, 373, 115, 31);
			panel.add(jbutton_themSach);
			
			jtextField_tenSach = new JTextField();
			jtextField_tenSach.setFont(new Font("Arial", Font.PLAIN, 13));
			jtextField_tenSach.setColumns(10);
			jtextField_tenSach.setBounds(119, 85, 236, 31);
			panel.add(jtextField_tenSach);
			
			jcomboBox_theLoai = new JComboBox();
			jcomboBox_theLoai.setFont(new Font("Arial", Font.PLAIN, 13));
			jcomboBox_theLoai.setModel(new DefaultComboBoxModel(new String[] {"", "Sách giáo khoa", "Giáo trình", "Sách thiếu nhi", "Sách tâm lý,tình cảm", "Sách tôn giáo", "Sách văn hóa xã hội", "Sách lịch sử", "Sách nấu ăn", "Sách khoa học", "Tiểu thuyết", "Truyện tranh"}));
			jcomboBox_theLoai.setBounds(119, 137, 236, 31);
			panel.add(jcomboBox_theLoai);
			
			jtextField_tacGia = new JTextField();
			jtextField_tacGia.setFont(new Font("Arial", Font.PLAIN, 13));
			jtextField_tacGia.setColumns(10);
			jtextField_tacGia.setBounds(119, 183, 236, 31);
			panel.add(jtextField_tacGia);
			
			jcomboBox_namXuatBan = new JComboBox();
			jcomboBox_namXuatBan.setFont(new Font("Arial", Font.PLAIN, 13));
			jcomboBox_namXuatBan.setModel(new DefaultComboBoxModel(new String[] {"", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024"}));
			jcomboBox_namXuatBan.setBounds(119, 235, 236, 31);
			panel.add(jcomboBox_namXuatBan);
			
			JButton jbutton_thoat = new JButton("Thoát");
			jbutton_thoat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			jbutton_thoat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					qlSach.displayAllBook();
				}
			});
			jbutton_thoat.setForeground(new Color(0, 128, 0));
			jbutton_thoat.setBackground(new Color(255, 255, 0));
			jbutton_thoat.setFont(new Font("Arial", Font.PLAIN, 15));
			jbutton_thoat.setBounds(207, 373, 89, 31);
			panel.add(jbutton_thoat);
			
			SpinnerModel spinnerModel = new SpinnerNumberModel(0, 0, 100, 1);
			soLuong = new JSpinner(spinnerModel);
			soLuong.setFont(new Font("Arial", Font.PLAIN, 13));
			soLuong.setBounds(121, 294, 51, 25);
			panel.add(soLuong);
			setLocationRelativeTo(null);
		}
		public void AddSach() {
			QuanLiSach qlSach = new QuanLiSach();
			String maSach = jtextField_maSach.getText();
			String tenSach = jtextField_tenSach.getText();
			int SoLuong = (int) soLuong.getValue();
			String theLoai = (String) jcomboBox_theLoai.getSelectedItem();
			String tacGia = jtextField_tacGia.getText();
			String namXuatBanStr =   (String) jcomboBox_namXuatBan.getSelectedItem();
			int namXuatBan = Integer.parseInt(namXuatBanStr);
			SachModel sachModel = new SachModel(maSach, tenSach, SoLuong, theLoai, tacGia, namXuatBan);
			SachDAO dao = new SachDAO();
			dao.insert(sachModel);
			JOptionPane.showMessageDialog(qlSach, "Thêm sách thành công!");
			jtextField_maSach.setText("");
			jtextField_tenSach.setText("");
			soLuong.setValue(0);
			jcomboBox_theLoai.setSelectedIndex(-1);
			jtextField_tacGia.setText("");
			jcomboBox_namXuatBan.setSelectedIndex(-1);
			
		}
		public boolean kiemTraTrungMaSach() {
			String maSach = jtextField_maSach.getText();
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "SELECT * FROM sach";
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					String ms = rs.getString("maSach");
					if(ms.equals(maSach)) {
						return true;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}
	}
