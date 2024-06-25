package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.OverlayLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.Cursor;
import java.awt.Toolkit;

public class BatDau extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BatDau frame = new BatDau();
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
	public BatDau() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\iconBd.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 609, 467);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		JLabel lblNewLabel_2 = new JLabel("Chào mừng bạn đến với thư viện");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel_2.setForeground(Color.ORANGE);
		lblNewLabel_2.setBounds(130, 58, 365, 35);
		getContentPane().add(lblNewLabel_2);
		
		lblNewLabel_1 = new JLabel("Đăng nhập với quyền");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(250, 143, 123, 26);
		getContentPane().add(lblNewLabel_1);
		
		JButton jbutton_Admin = new JButton("Admin");
		jbutton_Admin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_Admin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jbutton_Admin.setForeground(Color.WHITE);
		jbutton_Admin.setBackground(Color.LIGHT_GRAY);
		jbutton_Admin.setBounds(250, 190, 111, 23);
		jbutton_Admin.setBorder(BorderFactory.createLoweredBevelBorder());
		jbutton_Admin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login_Admin ad = new Login_Admin();
				ad.setVisible(true);
				
			}
		});
		getContentPane().add(jbutton_Admin);
		
		JButton jbutton_nguoidung = new JButton("Người dùng");
		jbutton_nguoidung.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_nguoidung.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jbutton_nguoidung.setForeground(Color.WHITE);
		jbutton_nguoidung.setBackground(Color.LIGHT_GRAY);
		jbutton_nguoidung.setBounds(250, 236, 111, 23);
		jbutton_nguoidung.setBorder(BorderFactory.createLoweredBevelBorder());
		jbutton_nguoidung.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Login_User dn = new Login_User();
				dispose();
				dn.setVisible(true);
				
				
			}
		});
		getContentPane().add(jbutton_nguoidung);
		
		JLabel anhnen = new JLabel("");
		anhnen.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\khoidau2.jpg"));
		anhnen.setFont(new Font("Tahoma", Font.PLAIN, 11));
		anhnen.setForeground(Color.WHITE);
		anhnen.setBounds(0, -11, 604, 464);	
		getContentPane().add(anhnen);
		contentPane = new JPanel();
		contentPane.setLayout(new OverlayLayout(contentPane));

		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 614, 453);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\84898\\Documents\\Zalo Received Files\\khoidau2.jpg"));
		contentPane.add(lblNewLabel);
		setVisible(true); 
	}
}
