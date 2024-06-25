package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.JDBCUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ThemVaoGio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtextField_soLuong;
	private Client gui_User;
	public JLabel jlabel_slkd;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client gui_User = new Client();
					ThemVaoGio frame = new ThemVaoGio(gui_User);
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
	public ThemVaoGio(Client gui_User) {
		setResizable(false);
		this.gui_User = gui_User;
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\add to Basket.png"));
		setBounds(100, 100, 256, 192);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Số lượng khả dụng :");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel.setBounds(36, 11, 136, 26);
		contentPane.add(lblNewLabel);
		
		 jlabel_slkd = new JLabel("0");
		jlabel_slkd.setFont(new Font("Arial", Font.PLAIN, 13));
		jlabel_slkd.setBounds(182, 14, 58, 20);
		contentPane.add(jlabel_slkd);
		
		JLabel lblNewLabel_2 = new JLabel("Số lượng thêm\r\n        :");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_2.setBounds(36, 62, 136, 26);
		contentPane.add(lblNewLabel_2);
		
		jtextField_soLuong = new JTextField();
		jtextField_soLuong.setFont(new Font("Arial", Font.PLAIN, 13));
		jtextField_soLuong.setBounds(177, 64, 34, 23);
		contentPane.add(jtextField_soLuong);
		jtextField_soLuong.setColumns(10);
		
		JButton btnNewButton = new JButton("Thêm vào giỏ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemVaoGio themVaoGio = new ThemVaoGio(gui_User);
				themSachVaoGio();
				dispose();
				
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBackground(new Color(106, 90, 205));
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 13));
		btnNewButton.setBounds(60, 119, 121, 23);
		contentPane.add(btnNewButton);
		setLocationRelativeTo(null);
	}
	public void soLuongKhaDung() {
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * ";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void themSachVaoGio() {
		Login_User user = new Login_User();
		Connection con1 = JDBCUtil.getConnection();
		try {
			Statement st1 = con1.createStatement();
			String sql1 = "SELECT * FROM docgia WHERE tenTaiKhoan = '"+user.tenTaiKhoan+"'";
			ResultSet rs = st1.executeQuery(sql1);
			if(rs.next()) {
				String hoTen = rs.getString("tenDocGia");
				int selectRow = gui_User.table_ttSach.getSelectedRow();
				if(selectRow != -1) {
					String maSach = gui_User.table_ttSach.getValueAt(selectRow, 0)+"";
					String tenSach = gui_User.table_ttSach.getValueAt(selectRow, 1)+"";
					int soLuong = Integer.valueOf(jtextField_soLuong.getText());
					
					if(soLuong>3) {
						JOptionPane.showMessageDialog(this, "1 sách không mượn quá 3");
						return;
					}
					if(kiemTraSachDaCoTrongGio(maSach,user.tenTaiKhoan)) {
						JOptionPane.showMessageDialog(this, "Sách đã có trong giỏ");
						return;
			
					}
					if(kiemTraTongSoLuong(user.tenTaiKhoan)) {
						JOptionPane.showMessageDialog(this, "Tổng sách trong giỏ không vượt quá 5");
						return;
					}
					Connection con = JDBCUtil.getConnection();
					try {
						Statement st = con.createStatement();
						String sql = "INSERT INTO giodksach(maSach,tenSach,soLuong,maDocGia,hoTen) VALUES(\""+maSach+"\",\""+tenSach+"\",\""+soLuong+"\",\""+user.tenTaiKhoan+"\",\""+hoTen+"\")";
						st.executeUpdate(sql);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(this, "Thêm vào giỏ thành công !");
					gui_User.displayGioDkSach();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean kiemTraSachDaCoTrongGio(String maSach,String madg) {
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM giodksach WHERE maDocGia = '"+madg+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String msach = rs.getString("maSach");
				if(msach.equals(maSach)) {
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean kiemTraTongSoLuong(String maDocGia) {
		Connection con = JDBCUtil.getConnection();
		int sum = 0;
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM giodksach WHERE maDocGia = '"+maDocGia+"'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int sl = rs.getInt("soLuong");
				 sum += sl;
			}
			if(sum==5) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
