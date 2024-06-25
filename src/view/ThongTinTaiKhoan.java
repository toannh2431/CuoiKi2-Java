package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class ThongTinTaiKhoan extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField jtextField_tenTk;
	public JTextField jtextField_hoTen;
	public JTextField jtextField_gmail;
	public JTextField jtextField_sdt;
	public JRadioButton jradio_nam;
	public JRadioButton jradio_nu;
	private Client gui_User;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client gui_User = new Client();
					ThongTinTaiKhoan frame = new ThongTinTaiKhoan(gui_User);
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
	public ThongTinTaiKhoan(Client gui_User) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\tttk.png"));
		this.gui_User = gui_User;
		setResizable(false);
		setBounds(100, 100, 373, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(211, 211, 211));
		contentPane.setBorder(new CompoundBorder());

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(211, 211, 211));
		panel.setForeground(new Color(50, 205, 50));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(23, 23, 313, 408);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên tài khoản");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 69, 94, 27);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ tên");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 141, 84, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gmail");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 272, 94, 27);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Số điện thoại");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 340, 94, 27);
		panel.add(lblNewLabel_3);
		
		jtextField_tenTk = new JTextField();
		jtextField_tenTk.setBorder(new EmptyBorder(0, 0, 0, 0));
		jtextField_tenTk.setForeground(new Color(50, 205, 50));
		jtextField_tenTk.setEditable(false);
		jtextField_tenTk.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_tenTk.setBounds(102, 66, 201, 32);
		panel.add(jtextField_tenTk);
		jtextField_tenTk.setColumns(10);
		
		jtextField_hoTen = new JTextField();
		jtextField_hoTen.setBorder(new EmptyBorder(0, 0, 0, 0));
		jtextField_hoTen.setForeground(new Color(50, 205, 50));
		jtextField_hoTen.setEditable(false);
		jtextField_hoTen.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_hoTen.setColumns(10);
		jtextField_hoTen.setBounds(102, 138, 201, 32);
		panel.add(jtextField_hoTen);
		
		jtextField_gmail = new JTextField();
		jtextField_gmail.setBorder(new EmptyBorder(0, 0, 0, 0));
		jtextField_gmail.setForeground(new Color(50, 205, 50));
		jtextField_gmail.setEditable(false);
		jtextField_gmail.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_gmail.setColumns(10);
		jtextField_gmail.setBounds(102, 269, 201, 32);
		panel.add(jtextField_gmail);
		
		jtextField_sdt = new JTextField();
		jtextField_sdt.setBorder(new EmptyBorder(0, 0, 0, 0));
		jtextField_sdt.setForeground(new Color(50, 205, 50));
		jtextField_sdt.setEditable(false);
		jtextField_sdt.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_sdt.setColumns(10);
		jtextField_sdt.setBounds(102, 337, 201, 32);
		panel.add(jtextField_sdt);
		
		JLabel lblNewLabel_1_1 = new JLabel("Giới tính");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(10, 200, 84, 27);
		panel.add(lblNewLabel_1_1);
		
		jradio_nam = new JRadioButton("Nam");
		jradio_nam.setEnabled(false);
		jradio_nam.setBackground(new Color(211, 211, 211));
		jradio_nam.setForeground(new Color(50, 205, 50));
		jradio_nam.setFont(new Font("Arial", Font.PLAIN, 12));
		jradio_nam.setBounds(102, 202, 69, 23);
		panel.add(jradio_nam);
		
		jradio_nu = new JRadioButton("Nữ");
		jradio_nu.setEnabled(false);
		jradio_nu.setBackground(new Color(211, 211, 211));
		jradio_nu.setForeground(new Color(50, 205, 50));
		jradio_nu.setFont(new Font("Arial", Font.PLAIN, 12));
		jradio_nu.setBounds(217, 202, 69, 23);
		panel.add(jradio_nu);
		
		JLabel lblNewLabel_4 = new JLabel("Thông tin tài khoản");
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_4.setBounds(91, 11, 158, 27);
		panel.add(lblNewLabel_4);
		setLocationRelativeTo(null);
	}
	public void setThongTinTaiKhoan(String tenTaiKhoan, String tenDocGia,String gioiTinh ,String gmail, String sdt) {
	    jtextField_tenTk.setText(tenTaiKhoan);
	    jtextField_hoTen.setText(tenDocGia);
	    if(gioiTinh.equals("Nam")) {
	    	jradio_nam.setSelected(true);
	    }else {
	    	jradio_nu.setSelected(true);
	    }
	    jtextField_gmail.setText(gmail);
	    jtextField_sdt.setText(sdt);
	}
}
