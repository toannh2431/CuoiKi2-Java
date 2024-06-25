package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DAO.SachDAO;
import database.JDBCUtil;
import model.ChiTietPhieuModel;
import model.DocGiaModel;
import model.PhieuDkModel;
import model.SachModel;
import view.QuanLiSach.CustomTableCellRenderer;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class Client extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField jtextfield_timKiem;
	public DefaultTableModel dm_giodksach;
	private JTable table_giodk;
	private JScrollPane js_giodk;
	private JTable table;
	public DefaultTableModel dm_thongTinSach;
	public JTable table_ttSach;
	private JScrollPane js_ttSach;
	private JPanel cardpanel;
	private JTable table_1;
	private JTable table_2;
	private DefaultTableModel dm_sachdk;
	private JTable table_sachdk;
	private JScrollPane js_sachdk;
	private DefaultTableModel dm_sachDangMuon;
	private JTable table_sachdangMuon;
	private JScrollPane js_sachDangMuon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DACS1\\icon and picture\\guiUser.png"));
		setTitle("Thư viện");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Hệ thống");
		mnNewMenu.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\heThong.png"));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu);

		JMenuItem thongTinTaiKhoan = new JMenuItem("Thông tin tài khoản");
		thongTinTaiKhoan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongTinTaiKhoan thongTinTaiKhoan = new ThongTinTaiKhoan(Client.this);
				thongTinTaiKhoan.setVisible(true);
				Login_User login_User = new Login_User();

				Connection con = JDBCUtil.getConnection();
				try {
					Statement st = con.createStatement();
					String sql = "SELECT * FROM docgia WHERE tenTaiKhoan = '" + login_User.tenTaiKhoan + "'";
					ResultSet rs = st.executeQuery(sql);
					if (rs.next()) {
						String tenTaiKhoan = rs.getString("tenTaiKhoan");
						String hoTen = rs.getString("tenDocGia");
						String gioiTinh = rs.getString("gioiTinh");
						String gmail = rs.getString("gmail");
						String sdt = rs.getString("soDienThoai");
						thongTinTaiKhoan.setThongTinTaiKhoan(tenTaiKhoan, hoTen, gioiTinh, gmail, sdt);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(thongTinTaiKhoan);

		JMenuItem DangXuat = new JMenuItem("Đăng xuất");
		DangXuat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login_User login_User = new Login_User();
				login_User.setVisible(true);
			}
		});
		mnNewMenu.add(DangXuat);

		JMenuItem Thoat = new JMenuItem("Thoát");
		Thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(Thoat);

		JMenu mnNewMenu_1 = new JMenu("Tính năng");
		mnNewMenu_1.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\function.png"));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnNewMenu_1);

		JMenuItem timKiemSach = new JMenuItem("Tìm kiếm sách");
		timKiemSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardpanel.getLayout();
				cl.show(cardpanel, "TimKiemSach");
			}
		});
		mnNewMenu_1.add(timKiemSach);

		JMenuItem muonSach = new JMenuItem("Mượn sách");
		muonSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) cardpanel.getLayout();
				cl.show(cardpanel, "MuonSach");
			}
		});
		mnNewMenu_1.add(muonSach);
		getContentPane().setLayout(null);

		CardLayout cardLayout = new CardLayout();
		cardpanel = new JPanel(cardLayout);
		cardpanel.setBounds(0, 0, 984, 632);
		getContentPane().add(cardpanel);

		JPanel jpanel_timKiemSach = new JPanel();
		jpanel_timKiemSach.setBounds(0, 0, 984, 531);
		cardpanel.add(jpanel_timKiemSach, "TimKiemSach");
		jpanel_timKiemSach.setLayout(null);

		JPanel jtextfield_tacGia = new JPanel();
		jtextfield_tacGia.setBorder(new LineBorder(new Color(0, 0, 0)));
		jtextfield_tacGia.setBounds(10, 32, 591, 205);
		jpanel_timKiemSach.add(jtextfield_tacGia);
		jtextfield_tacGia.setLayout(null);

		jtextfield_timKiem = new JTextField();
		jtextfield_timKiem.addCaretListener(new CaretListener() {

			@Override
			public void caretUpdate(CaretEvent e) {
				timKiem();
			}
		});
		jtextfield_timKiem.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextfield_timKiem.setBounds(193, 52, 296, 36);
		jtextfield_tacGia.add(jtextfield_timKiem);
		jtextfield_timKiem.setColumns(10);

		JButton jbutton_huy = new JButton("Hủy");
		jbutton_huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtextfield_timKiem.setText("");
			}
		});

		jbutton_huy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_huy.setFocusPainted(false);
		jbutton_huy.setBackground(new Color(105, 105, 105));
		jbutton_huy.setForeground(new Color(255, 255, 255));
		jbutton_huy.setFont(new Font("Arial", Font.PLAIN, 13));
		jbutton_huy.setBounds(505, 52, 76, 36);
		jtextfield_tacGia.add(jbutton_huy);

		JLabel lblNewLabel_1 = new JLabel("Thông tin tìm kiếm");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_1.setBounds(51, 55, 132, 31);
		jtextfield_tacGia.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("Nhập mã sách, tên sách, tác giả hoặc thể loại");
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setFont(new Font("Arial", Font.ITALIC, 12));
		lblNewLabel_3.setBounds(216, 91, 262, 30);
		jtextfield_tacGia.add(lblNewLabel_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(611, 32, 363, 205);
		jpanel_timKiemSach.add(panel_3);
		panel_3.setLayout(null);

		dm_giodksach = new DefaultTableModel();
		dm_giodksach.addColumn("Mã sách");
		dm_giodksach.addColumn("Tên sách");
		dm_giodksach.addColumn("Số lượng");

		table_giodk = new JTable(dm_giodksach);
		table_giodk.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		TableColumnModel columnModel = table_giodk.getColumnModel();
		int columnCount = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			TableColumn column = columnModel.getColumn(columnIndex);
			column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_giodk = new JScrollPane(table_giodk);
		js_giodk.setBounds(10, 11, 343, 152);
		displayGioDkSach();
		panel_3.add(js_giodk);
		JPopupMenu jPopupMenu = new JPopupMenu();
		JMenuItem menuItem_xoaSach = new JMenuItem("Xóa sách khỏi giỏ");
		menuItem_xoaSach.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xoaSachKhoiGio();

			}
		});

		jPopupMenu.add(menuItem_xoaSach);

		table_giodk.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = table_giodk.rowAtPoint(e.getPoint());
					int col = table_giodk.columnAtPoint(e.getPoint());

					if (row >= 0 && col >= 0) {
						table_giodk.clearSelection(); // Xóa bất kỳ lựa chọn nào trước khi chọn mới
						table_giodk.addRowSelectionInterval(row, row); // Chọn hàng
						table_giodk.setColumnSelectionInterval(0, table_giodk.getColumnCount() - 1); // Chọn toàn bộ cột

						// Hiển thị menu ngữ cảnh chỉ khi có hàng được chọn
						if (table_giodk.getSelectedRowCount() > 0) {
							jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
						}
					} else {
						table_giodk.clearSelection();
					}
				}
			}
		});
		table_giodk.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = table_giodk.rowAtPoint(e.getPoint());
					table_giodk.getSelectionModel().setSelectionInterval(row, row);
				}
			}
		});

		JButton jbutton_dkMuonSach = new JButton("Đăng ký");
		jbutton_dkMuonSach.addActionListener(new ActionListener() {

			private String tenDocGia;
			private int soPhieu;

			public void actionPerformed(ActionEvent e) {
//				dkMuonSach();
//				new Thread(() -> {
//					sendRequestRegister("Mượn sách");
//				}).start();

				new Thread(() -> {
					Client user = new Client();
					DefaultTableModel model = (DefaultTableModel) table_giodk.getModel();
					Vector<Vector> data = model.getDataVector();
					if (data.isEmpty()) {
						JOptionPane.showMessageDialog(user, "Không có sách trong giỏ !");
						return;
					}
					Login_User login_User = new Login_User();
					Connection con = JDBCUtil.getConnection();
					try {
						Statement st = con.createStatement();
						String sql = "SELECT * FROM docgia WHERE tenTaiKhoan = '" + login_User.tenTaiKhoan + "'";
						ResultSet rs = st.executeQuery(sql);
						if (rs.next()) {
							tenDocGia = rs.getString("tenDocGia");

						}
						String sql2 = "SELECT MAX(soPhieu) as maxloan FROM phieudksach";
						ResultSet rs2 = st.executeQuery(sql2);
						if (rs2.next()) {
							soPhieu = rs2.getInt("maxloan");
							soPhieu++;
						}

						LocalDate ngayHtai = LocalDate.now();
						Date ngayDk = Date.valueOf(ngayHtai);
						String trangThai = "Có thể mượn";
						PhieuDkModel phieuDkModel = new PhieuDkModel(soPhieu, login_User.tenTaiKhoan, tenDocGia, ngayDk,
								trangThai);

						sendDataToServer(phieuDkModel, soPhieu);
						xoaToanBoGioDk();

						dm_giodksach.setRowCount(0);

						JOptionPane.showMessageDialog(user, "Đăng ký mượn sách thành công !");

						System.out.println(phieuDkModel.getSoPhieu() + phieuDkModel.getMaDg());

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}).start();
			}
		});
		jbutton_dkMuonSach.setFocusPainted(false);
		jbutton_dkMuonSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_dkMuonSach.setForeground(new Color(255, 255, 255));
		jbutton_dkMuonSach.setBackground(new Color(105, 105, 105));
		jbutton_dkMuonSach.setFont(new Font("Arial", Font.PLAIN, 13));
		jbutton_dkMuonSach.setBounds(143, 169, 89, 25);
		panel_3.add(jbutton_dkMuonSach);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBounds(10, 265, 964, 241);
		jpanel_timKiemSach.add(panel_4);
		panel_4.setLayout(null);

		dm_thongTinSach = new DefaultTableModel();
		dm_thongTinSach.addColumn("Mã sách");
		dm_thongTinSach.addColumn("Tên sách");
		dm_thongTinSach.addColumn("Thể loại");
		dm_thongTinSach.addColumn("Tác giả");
		dm_thongTinSach.addColumn("Năm xuất bản");
		table_ttSach = new JTable(dm_thongTinSach);
		table_ttSach.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		TableColumnModel columnModel1 = table_ttSach.getColumnModel();
		int columnCount1 = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount1; columnIndex++) {
			TableColumn column = columnModel1.getColumn(columnIndex);
			column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_ttSach = new JScrollPane(table_ttSach);
		js_ttSach.setBounds(10, 11, 944, 219);
		panel_4.add(js_ttSach);

		new Thread(() -> {
			receiveBookDataFromServer();
		}).start();
		JPopupMenu jPopupMenu_ttsach = new JPopupMenu();
		JMenuItem menuItem_themVaoGio = new JMenuItem("Thêm vào giỏ");
		menuItem_themVaoGio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				ThemVaoGio themVaoGio = new ThemVaoGio(Client.this);
				themVaoGio.jlabel_slkd.setText(String.valueOf(soLuongKhaDung()));
				themVaoGio.setVisible(true);

			}
		});

		jPopupMenu_ttsach.add(menuItem_themVaoGio);

		table_ttSach.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = table_ttSach.rowAtPoint(e.getPoint());
					int col = table_ttSach.columnAtPoint(e.getPoint());

					if (row >= 0 && col >= 0) {
						table_ttSach.clearSelection(); // Xóa bất kỳ lựa chọn nào trước khi chọn mới
						table_ttSach.addRowSelectionInterval(row, row); // Chọn hàng
						table_ttSach.setColumnSelectionInterval(0, table_ttSach.getColumnCount() - 1); // Chọn toàn bộ
																										// cột

						// Hiển thị menu ngữ cảnh chỉ khi có hàng được chọn
						if (table_ttSach.getSelectedRowCount() > 0) {
							jPopupMenu_ttsach.show(e.getComponent(), e.getX(), e.getY());
						}
					} else {
						table_ttSach.clearSelection();
					}
				}
			}
		});
		table_ttSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = table_ttSach.rowAtPoint(e.getPoint());
					table_ttSach.getSelectionModel().setSelectionInterval(row, row);
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Tìm kiếm sách");
		lblNewLabel.setForeground(new Color(255, 165, 0));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 110, 25);
		jpanel_timKiemSach.add(lblNewLabel);

		JLabel lblGingK = new JLabel("Giỏ đăng ký sách");
		lblGingK.setForeground(new Color(255, 165, 0));
		lblGingK.setFont(new Font("Arial", Font.BOLD, 14));
		lblGingK.setBounds(611, 11, 125, 25);
		jpanel_timKiemSach.add(lblGingK);

		JLabel lblThngTinSch = new JLabel("Thông tin sách ");
		lblThngTinSch.setForeground(new Color(255, 165, 0));
		lblThngTinSch.setFont(new Font("Arial", Font.BOLD, 14));
		lblThngTinSch.setBounds(10, 241, 125, 25);
		jpanel_timKiemSach.add(lblThngTinSch);

		JLabel jlabel_chao = new JLabel("");
		jlabel_chao.setForeground(new Color(255, 0, 0));
		jlabel_chao.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
		jlabel_chao.setBounds(830, 0, 140, 30);
		jpanel_timKiemSach.add(jlabel_chao);
		Login_User user = new Login_User();
		jlabel_chao.setText("Chào " + user.tenTaiKhoan + " !");

//	------------------------------------------------------------------
		JPanel jpanel_muonSach = new JPanel();
		jpanel_muonSach.setBounds(0, 0, 984, 531);
		cardpanel.add(jpanel_muonSach, "MuonSach");
		jpanel_muonSach.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(38, 28, 400, 478);
		jpanel_muonSach.add(panel);
		panel.setLayout(null);

		dm_sachdk = new DefaultTableModel();
		dm_sachdk.addColumn("Mã sách");
		dm_sachdk.addColumn("Tên sách");
		dm_sachdk.addColumn("Số lượng");

		table_sachdk = new JTable(dm_sachdk);
		table_sachdk.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_sachdk.setColumnSelectionAllowed(true);
		TableColumnModel columnModel2 = table_sachdk.getColumnModel();
		int columnCount2 = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount2; columnIndex++) {
			TableColumn column = columnModel2.getColumn(columnIndex);
			column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_sachdk = new JScrollPane(table_sachdk);
		js_sachdk.setBounds(24, 22, 348, 431);
		panel.add(js_sachdk);
		displaySachDk();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(531, 28, 400, 478);
		jpanel_muonSach.add(panel_1);
		panel_1.setLayout(null);

		dm_sachDangMuon = new DefaultTableModel();
		dm_sachDangMuon.addColumn("Mã sách");
		dm_sachDangMuon.addColumn("Tên sách");
		dm_sachDangMuon.addColumn("Số lượng");

		table_sachdangMuon = new JTable(dm_sachDangMuon);
		table_sachdangMuon.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table_sachdangMuon.setColumnSelectionAllowed(true);
		TableColumnModel columnModel3 = table_sachdangMuon.getColumnModel();
		int columnCount3 = columnModel3.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount3; columnIndex++) {
			TableColumn column = columnModel3.getColumn(columnIndex);
			column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_sachDangMuon = new JScrollPane(table_sachdangMuon);
		js_sachDangMuon.setBounds(29, 24, 348, 431);
		panel_1.add(js_sachDangMuon);
		displaySachDangMuon();

		JLabel lblNewLabel_2 = new JLabel("Sách đã đăng ký");
		lblNewLabel_2.setForeground(new Color(255, 165, 0));
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(51, 0, 113, 25);
		jpanel_muonSach.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Sách đang mượn");
		lblNewLabel_2_1.setForeground(new Color(255, 165, 0));
		lblNewLabel_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(532, 0, 113, 25);
		jpanel_muonSach.add(lblNewLabel_2_1);
		setLocationRelativeTo(null);
	}

	private void receiveBookDataFromServer() {
		if (!isServerRunning()) {
			JOptionPane.showMessageDialog(this, "Máy chủ đang ngưng hoạt động");
			dispose();
			return;
		}
		try (Socket socket = new Socket("localhost", 8080);

				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			System.out.println("Đã kết nối đến server");
			while (true) {
				// Đọc dữ liệu từ server

				List<SachModel> bookList = (List<SachModel>) in.readObject();

				// Cập nhật dữ liệu lên giao diện
				updateTableModel(bookList);

			}
		} catch (IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Máy chủ đã ngắt kết nối");
			dispose();
			System.out.println("Xảy ra lỗi");// Xử lý khi có lỗi xảy ra
		}
	}

	private boolean isServerRunning() {
		try {
			// Thử kết nối đến server
			Socket socket = new Socket("localhost", 8080);
			socket.close();
			return true;
		} catch (IOException e) {
			// Không kết nối được đến server
			return false;
		}
	}

	private void updateTableModel(List<SachModel> model) {
		for (SachModel sachModel : model) {
			dm_thongTinSach.addRow(new Object[] { sachModel.getMaSach(), sachModel.getTenSach(), sachModel.getTheLoai(),
					sachModel.getTacGia(), sachModel.getNamXuatBan() });
		}
	}

	public void sendDataToServer(PhieuDkModel phieuDkModel, int soPhieu) {
		try (Socket socket = new Socket("localhost", 8080);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
			ChiTietPhieuModel chiTietPhieuModel = null;
			DefaultTableModel model = (DefaultTableModel) table_giodk.getModel();
			Vector<Vector> data = model.getDataVector();
			for (Vector row : data) {
				String maSach = row.get(0).toString();
				String tenSach = row.get(1).toString();
				int sl = Integer.valueOf(row.get(2).toString());
				chiTietPhieuModel = new ChiTietPhieuModel(soPhieu, maSach, tenSach, sl);
			}
			out.writeObject(phieuDkModel);
			out.writeObject(chiTietPhieuModel);
			out.flush();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error sending data to server: " + e.getMessage());
		}
	}

	

	

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void displayThongTinSach() {
		SachDAO dao = new SachDAO();
		ArrayList<SachModel> list = dao.selectAll();
		dm_thongTinSach.setRowCount(0);
		for (SachModel sachModel : list) {
			dm_thongTinSach.addRow(new Object[] { sachModel.getMaSach(), sachModel.getTenSach(), sachModel.getTheLoai(),
					sachModel.getTacGia(), sachModel.getNamXuatBan() });
		}
	}

	public void displayGioDkSach() {
		Connection con = JDBCUtil.getConnection();
		Login_User user = new Login_User();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM giodksach WHERE maDocGia= '" + user.tenTaiKhoan + "'";
			ResultSet rs = st.executeQuery(sql);
			dm_giodksach.setRowCount(0);
			while (rs.next()) {
				String maSach = rs.getString("maSach");
				String tenSach = rs.getString("tenSach");
				int soLuong = rs.getInt("soLuong");
				dm_giodksach.addRow(new Object[] { maSach, tenSach, soLuong });
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void xoaSachKhoiGio() {
		int selectRow = table_giodk.getSelectedRow();
		String maSach = table_giodk.getValueAt(selectRow, 0) + "";
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM giodksach WHERE masach = '" + maSach + "'";
			st.executeUpdate(sql);
			this.displayGioDkSach();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dkMuonSach() {
		Connection con = JDBCUtil.getConnection();
		Login_User user = new Login_User();
		String maDocGia = user.tenTaiKhoan;
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM docgia WHERE tenTaiKhoan = '" + maDocGia + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				String tenDocGia = rs.getString("tenDocGia");
				LocalDate ngayDk = LocalDate.now();
				String trangThai = "Có thể mượn";
				Connection con2 = JDBCUtil.getConnection();
				Statement st2 = con2.createStatement();
				String sql2 = "INSERT INTO phieudksach(maDocGia,tenDocGia,ngayDk,trangThai) VALUES (\"" + maDocGia
						+ "\",\"" + tenDocGia + "\",\"" + ngayDk + "\",\"" + trangThai + "\") ";
				st2.executeUpdate(sql2);
				themSachVaoBangChiTietPhieu();
				JOptionPane.showMessageDialog(this, "Đăng ký mượn thành công !");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void themSachVaoBangChiTietPhieu() {
		Login_User user = new Login_User();
		String maDocGia = user.tenTaiKhoan;
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM phieudksach WHERE maDocGia = '" + maDocGia + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				int soPhieu = rs.getInt("soPhieu");
				Connection con2 = JDBCUtil.getConnection();
				Statement st2 = con2.createStatement();
				String sql2 = "SELECT * FROM giodksach WHERE maDocGia = '" + maDocGia + "'";
				ResultSet rs2 = st2.executeQuery(sql2);
				while (rs2.next()) {
					String maSach = rs2.getString("maSach");
					String tenSach = rs2.getString("tenSach");
					int sl = rs2.getInt("soLuong");
					Connection con3 = JDBCUtil.getConnection();
					Statement st3 = con3.createStatement();
					String sql3 = "INSERT INTO chitietphieu(soPhieu,maSach,tenSach,soLuong) VALUES(\"" + soPhieu
							+ "\",\"" + maSach + "\",\"" + tenSach + "\",\"" + sl + "\")";
					st3.executeUpdate(sql3);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void timKiem() {
		dm_thongTinSach.setRowCount(0);
		String search = jtextfield_timKiem.getText();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM sach WHERE maSach like '%" + search + "%' OR tenSach like '%" + search
					+ "%' OR tacGia like '" + search + "%' OR theLoai like '%" + search + "%'";
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				String maSach = rs.getString("maSach");
				String tenSach = rs.getString("tenSach");
				String tacGia = rs.getString("tacGia");
				String theLoai = rs.getString("theLoai");
				int namXuatBan = rs.getInt("namXuatBan");

				dm_thongTinSach.addRow(new Object[] { maSach, tenSach, tacGia, theLoai, namXuatBan });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int soLuongKhaDung() {
		int tongSachKho = 0;
		int tongSachDaMuon = 0;
		int soLuongKhaDung = 0;
		int selectRow = table_ttSach.getSelectedRow();
		String maSach = table_ttSach.getValueAt(selectRow, 0).toString();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM sach WHERE maSach ='" + maSach + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				tongSachKho += rs.getInt("soLuong");
			}
			Connection con2 = JDBCUtil.getConnection();
			Statement st2 = con2.createStatement();
			String sql2 = "SELECT * FROM chitietphieu WHERE maSach='" + maSach + "'";
			ResultSet rs2 = st2.executeQuery(sql2);
			while (rs2.next()) {
				tongSachDaMuon += rs2.getInt("soLuong");
			}
			soLuongKhaDung = tongSachKho - tongSachDaMuon;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return soLuongKhaDung;
	}

	public void displaySachDk() {
		Connection con = JDBCUtil.getConnection();
		Login_User login_User = new Login_User();
		int soPhieu = 0;
		dm_sachdk.setRowCount(0);
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM phieudksach WHERE maDocGia = '" + login_User.tenTaiKhoan + "'";
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				soPhieu = rs.getInt("soPhieu");
			}
			Connection con2 = JDBCUtil.getConnection();
			Statement st2 = con2.createStatement();
			String sql2 = "SELECT * FROM chitietphieu WHERE soPhieu= '" + soPhieu + "'";
			ResultSet rs2 = st2.executeQuery(sql2);
			while (rs2.next()) {
				String maSach = rs2.getString("maSach");
				String tenSach = rs2.getString("tenSach");
				int sl = rs2.getInt("soLuong");
				dm_sachdk.addRow(new Object[] { maSach, tenSach, sl });
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displaySachDangMuon() {
		Connection con = JDBCUtil.getConnection();
		Login_User login_User = new Login_User();

		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM phieumuon WHERE maDocGia = '" + login_User.tenTaiKhoan + "'";
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				int soPhieu = rs.getInt("soPhieu");
				Connection con2 = JDBCUtil.getConnection();
				Statement st2 = con2.createStatement();
				String sql2 = "SELECT * FROM chitietphieu WHERE soPhieu= '" + soPhieu + "'";
				ResultSet rs2 = st2.executeQuery(sql2);
				while (rs2.next()) {
					String maSach = rs2.getString("maSach");
					String tenSach = rs2.getString("tenSach");
					int sl = rs2.getInt("soLuong");
					dm_sachDangMuon.addRow(new Object[] { maSach, tenSach, sl });
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void xoaToanBoGioDk() {
		Login_User user = new Login_User();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "DELETE FROM giodksach WHERE maDocGia = '" + user.tenTaiKhoan + "'";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
