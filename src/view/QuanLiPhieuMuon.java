package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import org.jfree.chart.annotations.XYLineAnnotation;

import DAO.PhieuMuonDAO;
import database.JDBCUtil;
import model.PhieuDkModel;
import model.PhieuMuonModel;
import view.QuanLiSach.CustomTableCellRenderer;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLiPhieuMuon extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtextField_timKiemPhieuDk;
	private JTable table_1;
	private JScrollPane js_chiTiet;
	private JPanel cardpanel;
	private JTable table_phieuMuon;
	private JScrollPane js_phieuMuon;
	private JTextField jtextField_timKiemPhieuMuon;
	private DefaultTableModel dm_phieuMuon;
	private DefaultTableModel dm_chiTietPhieuMuon;
	private JTable table_chiTietPhieuMuon;
	private JScrollPane js_chiTietPhieuMuon;
	private JButton jbutton_xacNhan;
	private JButton jbutton_nhanTra;
	public DefaultTableModel dm_phieuDk;
	private JTable table_phieuDk;
	private JScrollPane js_phieuDk;
	public DefaultTableModel dm_chiTietPhieuDk;
	private JTable table_chiTietPhieuDk;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiPhieuMuon frame = new QuanLiPhieuMuon();
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
	public QuanLiPhieuMuon() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel jpanel_header = new JPanel();
		jpanel_header.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		jpanel_header.setBounds(0, 0, 789, 71);
		contentPane.add(jpanel_header);
		jpanel_header.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Quản lí phiếu mượn");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\loan slip.png"));
		lblNewLabel_2.setForeground(new Color(154, 205, 50));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(281, 0, 213, 38);
		jpanel_header.add(lblNewLabel_2);
		
		jbutton_xacNhan = new JButton("Phiếu chờ Xác nhận");
		jbutton_xacNhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbutton_xacNhan.setBackground(Color.GREEN);
				jbutton_nhanTra.setBackground(new Color(255,215,0));
				CardLayout cl = (CardLayout) cardpanel.getLayout();
				cl.show(cardpanel,"XacNhan");
			}
		});
		jbutton_xacNhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_xacNhan.setFocusPainted(false);
		jbutton_xacNhan.setBackground(new Color(255, 215, 0));
		jbutton_xacNhan.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_xacNhan.setBounds(240, 38, 151, 23);
		jpanel_header.add(jbutton_xacNhan);
		
		jbutton_nhanTra = new JButton("Phiếu mượn\r\n");
		jbutton_nhanTra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbutton_nhanTra.setBackground(Color.GREEN);
				jbutton_xacNhan.setBackground(new Color(255,215,0));
				CardLayout cl = (CardLayout) cardpanel.getLayout();
				cl.show(cardpanel,"NhanTra");
			}
		});
		jbutton_nhanTra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_nhanTra.setFocusPainted(false);
		jbutton_nhanTra.setBackground(new Color(255, 215, 0));
		jbutton_nhanTra.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_nhanTra.setBounds(401, 38, 147, 23);
		jpanel_header.add(jbutton_nhanTra);
		
		CardLayout cardLayout = new CardLayout();
		cardpanel = new JPanel(cardLayout);
		cardpanel.setBounds(0, 71, 789, 462);
		contentPane.add(cardpanel);
		
		JPanel panel_xacNhan = new JPanel();
		panel_xacNhan.setBounds(0, 0, 789, 462);
		cardpanel.add(panel_xacNhan,"XacNhan");
		panel_xacNhan.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Thông tin tìm kiếm");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(126, 14, 133, 22);
		panel_xacNhan.add(lblNewLabel);
		
		jtextField_timKiemPhieuDk = new JTextField();
		jtextField_timKiemPhieuDk.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				timKiemPhieuDk();
			}
		});
		
		jtextField_timKiemPhieuDk.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_timKiemPhieuDk.setBounds(265, 13, 258, 27);
		panel_xacNhan.add(jtextField_timKiemPhieuDk);
		jtextField_timKiemPhieuDk.setColumns(10);
		
		JButton jbutton_huyBo = new JButton("Hủy bỏ");
		jbutton_huyBo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtextField_timKiemPhieuDk.setText("");
				
			}
		});
		jbutton_huyBo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_huyBo.setFocusPainted(false);
		jbutton_huyBo.setForeground(new Color(255, 255, 255));
		jbutton_huyBo.setBackground(new Color(105, 105, 105));
		jbutton_huyBo.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_huyBo.setBounds(530, 12, 103, 29);
		panel_xacNhan.add(jbutton_huyBo);
		
		JLabel lblNewLabel_1 = new JLabel("Phiếu mượn đã đăng ký");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(24, 59, 153, 22);
		panel_xacNhan.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(20, 81, 769, 169);
		panel_xacNhan.add(panel);
		panel.setLayout(null);
		
		dm_phieuDk = new DefaultTableModel();
		dm_phieuDk.addColumn("Số phiếu");
		dm_phieuDk.addColumn("Mã độc giả");
		dm_phieuDk.addColumn("Tên độc giả");
		dm_phieuDk.addColumn("Ngày đăng ký");
		dm_phieuDk.addColumn("Trạng thái");
		
		table_phieuDk = new JTable(dm_phieuDk);
		table_phieuDk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hienThiChiTietPhieuDk();
			}
		});
		table_phieuDk.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		TableColumnModel columnModel = table_phieuDk.getColumnModel();
		int columnCount = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
		    TableColumn column = columnModel.getColumn(columnIndex);
		    column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_phieuDk = new JScrollPane(table_phieuDk); 
		displayPhieuDk();
		js_phieuDk.setBounds(10, 11, 749, 147);
		panel.add(js_phieuDk);
		JPopupMenu jPopupMenu = new JPopupMenu();
		JMenuItem menuItem_xoaPhieuDk = new JMenuItem("Xóa phiếu");
		menuItem_xoaPhieuDk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xoaPhieuDk();
				xoaSachPhieuDk();
				displayPhieuDk();
			}
		});
		
		jPopupMenu.add(menuItem_xoaPhieuDk);

		table_phieuDk.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				 if (SwingUtilities.isRightMouseButton(e)) {
			            int row = table_phieuDk.rowAtPoint(e.getPoint());
			            int col = table_phieuDk.columnAtPoint(e.getPoint());

			            if (row >= 0 && col >= 0) {
			            	table_phieuDk.clearSelection(); // Xóa bất kỳ lựa chọn nào trước khi chọn mới
			            	table_phieuDk.addRowSelectionInterval(row, row); // Chọn hàng
			            	table_phieuDk.setColumnSelectionInterval(0, table_phieuDk.getColumnCount() - 1); // Chọn toàn bộ cột

			                // Hiển thị menu ngữ cảnh chỉ khi có hàng được chọn
			                if (table_phieuDk.getSelectedRowCount() > 0) {
			                	jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
			                }
			            } else {
			            	table_phieuDk.clearSelection();
			            }
			        }
			    }
			});
		table_phieuDk.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                if (SwingUtilities.isRightMouseButton(e)) {
	                    int row = table_phieuDk.rowAtPoint(e.getPoint());
	                    table_phieuDk.getSelectionModel().setSelectionInterval(row, row);
	                }
	            }
	        });
		
		JLabel lblNewLabel_1_1 = new JLabel("Chi tiết phiếu đăng ký");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(24, 265, 138, 22);
		panel_xacNhan.add(lblNewLabel_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(20, 286, 769, 126);
		panel_xacNhan.add(panel_1);
		panel_1.setLayout(null);
		
		dm_chiTietPhieuDk = new DefaultTableModel();
		dm_chiTietPhieuDk.addColumn("Số phiếu");
		dm_chiTietPhieuDk.addColumn("Mã sách");
		dm_chiTietPhieuDk.addColumn("Tên sách");
		dm_chiTietPhieuDk.addColumn("Số lượng");

		table_chiTietPhieuDk = new JTable(dm_chiTietPhieuDk);
		table_chiTietPhieuDk.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		TableColumnModel clmodel = table_chiTietPhieuDk.getColumnModel();
		int clCount = clmodel.getColumnCount();
		for (int columnIndex = 0; columnIndex < clCount; columnIndex++) {
		    TableColumn column = clmodel.getColumn(columnIndex);
		    column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_chiTiet = new JScrollPane(table_chiTietPhieuDk); 
		js_chiTiet.setBounds(10, 11, 749, 104);
		panel_1.add(js_chiTiet);
		
		
		JButton jbutton_xacNhanMuon = new JButton("Xác nhận mượn");
		jbutton_xacNhanMuon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layDuLieuPhieuDk();
			}
		});
		jbutton_xacNhanMuon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_xacNhanMuon.setFocusPainted(false);
		jbutton_xacNhanMuon.setForeground(Color.WHITE);
		jbutton_xacNhanMuon.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_xacNhanMuon.setBackground(new Color(105, 105, 105));
		jbutton_xacNhanMuon.setBounds(325, 422, 127, 29);
		panel_xacNhan.add(jbutton_xacNhanMuon);
//	----------------------------------------------------------------------------------------
		JPanel panel_nhanTra = new JPanel();
		panel_nhanTra.setBounds(0, 0, 789, 462);
		cardpanel.add(panel_nhanTra,"NhanTra");
		panel_nhanTra.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Thông tin tìm kiếm");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setBounds(129, 14, 133, 22);
		panel_nhanTra.add(lblNewLabel_3);
		
		jtextField_timKiemPhieuMuon = new JTextField();
		jtextField_timKiemPhieuMuon.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				timKiemPhieuMuon();
			}
		});
		jtextField_timKiemPhieuMuon.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_timKiemPhieuMuon.setColumns(10);
		jtextField_timKiemPhieuMuon.setBounds(263, 13, 258, 27);
		panel_nhanTra.add(jtextField_timKiemPhieuMuon);
		
		JButton jbutton_huyBo_1 = new JButton("Hủy bỏ");
		jbutton_huyBo_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtextField_timKiemPhieuMuon.setText("");
			}
		});
		jbutton_huyBo_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_huyBo_1.setForeground(Color.WHITE);
		jbutton_huyBo_1.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_huyBo_1.setFocusPainted(false);
		jbutton_huyBo_1.setBackground(new Color(105, 105, 105));
		jbutton_huyBo_1.setBounds(531, 13, 103, 29);
		panel_nhanTra.add(jbutton_huyBo_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Phiếu mượn");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(22, 58, 84, 22);
		panel_nhanTra.add(lblNewLabel_1_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(20, 78, 769, 169);
		panel_nhanTra.add(panel_2);
		
		
		dm_phieuMuon = new DefaultTableModel();
		dm_phieuMuon.addColumn("Số phiếu");
		dm_phieuMuon.addColumn("Mã độc giả");
		dm_phieuMuon.addColumn("Tên độc giả");
		dm_phieuMuon.addColumn("Ngày mượn");
		dm_phieuMuon.addColumn("Hạn trả");
		dm_phieuMuon.addColumn("Trạng thái");

		table_phieuMuon = new JTable(dm_phieuMuon);
		table_phieuMuon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				hienThiChiTietPhieuMuon();
			}
		});
		table_phieuMuon.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		TableColumnModel columnModel1 = table_phieuMuon.getColumnModel();
		int columnCount1 = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount1; columnIndex++) {
		    TableColumn column = columnModel1.getColumn(columnIndex);
		    column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_phieuMuon = new JScrollPane(table_phieuMuon); 
		displayPhieuMuon();
		js_phieuMuon.setBounds(10, 11, 749, 147);
		panel_2.add(js_phieuMuon);
		JPopupMenu jPopupMenu_phieuMuon = new JPopupMenu();
		JMenuItem menuItem_sua = new JMenuItem("Cập nhật phiếu");
		menuItem_sua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				layDuLieuPhieuMuon();
			}
		});
		JMenuItem menuItem_xoa = new JMenuItem("Xóa phiếu");
		menuItem_xoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xoaPhieuMuon();
				xoaSachPhieuMuon();
			}
		});
		jPopupMenu_phieuMuon.add(menuItem_sua);
		jPopupMenu_phieuMuon.add(menuItem_xoa);

		table_phieuMuon.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				 if (SwingUtilities.isRightMouseButton(e)) {
			            int row = table_phieuMuon.rowAtPoint(e.getPoint());
			            int col = table_phieuMuon.columnAtPoint(e.getPoint());

			            if (row >= 0 && col >= 0) {
			            	table_phieuMuon.clearSelection(); // Xóa bất kỳ lựa chọn nào trước khi chọn mới
			            	table_phieuMuon.addRowSelectionInterval(row, row); // Chọn hàng
			            	table_phieuMuon.setColumnSelectionInterval(0, table_phieuMuon.getColumnCount() - 1); // Chọn toàn bộ cột

			                // Hiển thị menu ngữ cảnh chỉ khi có hàng được chọn
			                if (table_phieuMuon.getSelectedRowCount() > 0) {
			                	jPopupMenu_phieuMuon.show(e.getComponent(), e.getX(), e.getY());
			                }
			            } else {
			            	table_phieuMuon.clearSelection();
			            }
			        }
			    }
			});
		table_phieuMuon.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                if (SwingUtilities.isRightMouseButton(e)) {
	                    int row = table_phieuMuon.rowAtPoint(e.getPoint());
	                    table_phieuMuon.getSelectionModel().setSelectionInterval(row, row);
	                }
	            }
	        });
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Chi tiết phiếu mượn");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(22, 258, 133, 22);
		panel_nhanTra.add(lblNewLabel_1_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1_1.setBounds(22, 283, 767, 168);
		panel_nhanTra.add(panel_1_1);
		
		dm_chiTietPhieuMuon = new DefaultTableModel();
		dm_chiTietPhieuMuon.addColumn("Số phiếu");
		dm_chiTietPhieuMuon.addColumn("Mã sách");
		dm_chiTietPhieuMuon.addColumn("Tên sách");
		dm_chiTietPhieuMuon.addColumn("Số lượng");
		
		table_chiTietPhieuMuon = new JTable(dm_chiTietPhieuMuon);
		table_chiTietPhieuMuon.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		TableColumnModel columnModel2 = table_chiTietPhieuMuon.getColumnModel();
		int columnCount2 = columnModel2.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount2; columnIndex++) {
		    TableColumn column = columnModel1.getColumn(columnIndex);
		    column.setCellRenderer(new CustomTableCellRenderer());
		}
		js_chiTietPhieuMuon = new JScrollPane(table_chiTietPhieuMuon); 
		js_chiTietPhieuMuon.setBounds(10, 11, 749, 104);
		panel_1_1.add(js_chiTietPhieuMuon);
		
		JButton jbutton_huyBo_1_1_1 = new JButton("Trả phiếu");
		jbutton_huyBo_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				traPhieu();
			}
		});
		jbutton_huyBo_1_1_1.setBounds(209, 126, 133, 29);
		panel_1_1.add(jbutton_huyBo_1_1_1);
		jbutton_huyBo_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_huyBo_1_1_1.setForeground(Color.WHITE);
		jbutton_huyBo_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_huyBo_1_1_1.setFocusPainted(false);
		jbutton_huyBo_1_1_1.setBackground(new Color(105, 105, 105));
		
		JButton jbutton_huyBo_1_1_1_1 = new JButton("Xóa phiếu đã trả");
		jbutton_huyBo_1_1_1_1.setForeground(Color.WHITE);
		jbutton_huyBo_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
		jbutton_huyBo_1_1_1_1.setFocusPainted(false);
		jbutton_huyBo_1_1_1_1.setBackground(new Color(105, 105, 105));
		jbutton_huyBo_1_1_1_1.setBounds(405, 126, 133, 29);
		panel_1_1.add(jbutton_huyBo_1_1_1_1);
		
	}
	public void updateTablePhieuDk(PhieuDkModel phieuDkModel) {
		dm_phieuDk.addRow(new Object[] {phieuDkModel.getSoPhieu(),phieuDkModel.getMaDg(),phieuDkModel.getTenDg(),phieuDkModel.getNgayDk(),phieuDkModel.getTrangThai()});
	}
	public void displayPhieuDk() {
		dm_phieuDk.setRowCount(0);
		Connection con = JDBCUtil.getConnection();
		try {
		    Statement st = con.createStatement();
		    String sql = "SELECT * FROM phieudksach";
		    ResultSet rs = st.executeQuery(sql);
		    while (rs.next()) {
		        int soPhieu = rs.getInt("soPhieu");
		        String maDg = rs.getString("maDocGia");
		        String tenDg = rs.getString("tenDocGia");
		        Date ngayDk = rs.getDate("ngaydk");
		        String trangThai = rs.getString("trangThai");
		        LocalDate ngayHienTai = LocalDate.now();
		        LocalDate ngayDK = ngayDk.toLocalDate();
		        long diff = ChronoUnit.DAYS.between(ngayDK, ngayHienTai);
		        int daysDiff = (int) diff;
		        if (daysDiff > 2) {
		            String tthai = "Quá hạn đến mượn";
		            dm_phieuDk.addRow(new Object[]{soPhieu, maDg, tenDg, ngayDk, tthai});

		            // Cập nhật trạng thái của phiếu đăng ký sách thành "Quá hạn đến mượn"
		            Connection con2 = JDBCUtil.getConnection();
		            Statement st2 = con2.createStatement();
		            String sql2 = "UPDATE phieudksach SET trangThai = '" + tthai + "' WHERE soPhieu = " + soPhieu;
		            st2.executeUpdate(sql2);
		            st2.close();
		            con2.close();
		        } else {
		            dm_phieuDk.addRow(new Object[]{soPhieu, maDg, tenDg, ngayDk, trangThai});
		        }
		    }
		    rs.close();
		    st.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		}
	}
	public void displayPhieuMuon() {
		PhieuMuonDAO dao = new PhieuMuonDAO();
		ArrayList<PhieuMuonModel> list = dao.selectAll();
		dm_phieuMuon.setRowCount(0);
		for (PhieuMuonModel phieuMuonModel : list) {
			dm_phieuMuon.addRow(new Object[] {phieuMuonModel.getSoPhieu(),phieuMuonModel.getMaDocGia(),phieuMuonModel.getTenDocGia(),phieuMuonModel.getNgayMuon(),phieuMuonModel.getHanTra(),phieuMuonModel.getTrangThai()});
			
		}
	}
	public void layDuLieuPhieuDk() {
		int selectRow = table_phieuDk.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuDk.getValueAt(selectRow, 0)+"");
			String maDg = table_phieuDk.getValueAt(selectRow, 1)+"";
			String tenDg = table_phieuDk.getValueAt(selectRow, 2)+"";
			LocalDate currentDate = LocalDate.now();
			PhieuMuon phieuMuon = new PhieuMuon(QuanLiPhieuMuon.this);
			phieuMuon.setVisible(true);
			phieuMuon.jtextField_soPhieu.setText(soPhieu+"");
			phieuMuon.jtextField_maDg.setText(maDg);
			phieuMuon.jtextField_tenDg.setText(tenDg);
			phieuMuon.jtextField_ngayMuon.setText(currentDate.toString());
		}
	}
	public void xoaPhieuDk() {
		int selectRow = table_phieuDk.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuDk.getValueAt(selectRow, 0)+"");
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "DELETE FROM phieudksach WHERE soPhieu = '"+soPhieu+"'";
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void xoaPhieuMuon() {
		int selectRow = table_phieuMuon.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuMuon.getValueAt(selectRow, 0)+"");
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "DELETE FROM phieumuon WHERE soPhieu = '"+soPhieu+"'";
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void xoaSachPhieuMuon() {
		int selectRow = table_phieuMuon.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuMuon.getValueAt(selectRow, 0)+"");
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "DELETE FROM chitietphieu WHERE soPhieu = '"+soPhieu+"'";
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void xoaSachPhieuDk() {
		int selectRow = table_phieuDk.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuDk.getValueAt(selectRow, 0)+"");
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "DELETE FROM chitietphieu WHERE soPhieu = '"+soPhieu+"'";
				st.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void timKiemPhieuDk() {
		String search = jtextField_timKiemPhieuDk.getText();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement st = con.createStatement();
			String sql = "SELECT * FROM phieudksach WHERE soPhieu like '%"+search+"%' OR maDocGia like '%"+search+"%'  ORDER BY soPhieu ASC";
			ResultSet rs = st.executeQuery(sql);
			dm_phieuDk.setRowCount(0);
			while(rs.next()) {
				int soPhieu = rs.getInt("soPhieu");
				String maDocGia = rs.getString("maDocGia");
				String tenDocGia = rs.getString("tenDocGia");
				Date ngaydk = rs.getDate("ngaydk");
				String trangThai = rs.getString("trangThai");
				dm_phieuDk.addRow(new Object[] {soPhieu,maDocGia,tenDocGia,ngaydk,trangThai});
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void timKiemPhieuMuon() {
		String search = jtextField_timKiemPhieuMuon.getText();
		PhieuMuonDAO dao = new PhieuMuonDAO();
		ArrayList<PhieuMuonModel> list = dao.selectAllByCode(search);
			dm_phieuMuon.setRowCount(0);
			for (PhieuMuonModel phieuMuonModel : list) {
			dm_phieuMuon.addRow(new Object[] {phieuMuonModel.getSoPhieu(),phieuMuonModel.getMaDocGia(),phieuMuonModel.getTenDocGia(),phieuMuonModel.getNgayMuon(),phieuMuonModel.getHanTra(),phieuMuonModel.getTrangThai()});
		}
	}
	public void hienThiChiTietPhieuDk() {
		int selectRow = table_phieuDk.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuDk.getValueAt(selectRow, 0)+"");
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "SELECT * FROM chitietphieu WHERE soPhieu = '"+soPhieu+"'";
				ResultSet rs = st.executeQuery(sql);
				dm_chiTietPhieuDk.setRowCount(0);
				while(rs.next()) {
					String maSach = rs.getString("maSach");
					String tenSach = rs.getString("tenSach");
					int sl = rs.getInt("soLuong");
					dm_chiTietPhieuDk.addRow(new Object[] {soPhieu,maSach,tenSach,sl});
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void hienThiChiTietPhieuMuon() {
		int selectRow = table_phieuMuon.getSelectedRow();
		if(selectRow!=-1) {
			int soPhieu = Integer.valueOf(table_phieuMuon.getValueAt(selectRow, 0)+"");
			Connection con = JDBCUtil.getConnection();
			try {
				Statement st = con.createStatement();
				String sql = "SELECT * FROM chitietphieu WHERE soPhieu = '"+soPhieu+"'";
				ResultSet rs = st.executeQuery(sql);
				dm_chiTietPhieuMuon.setRowCount(0);
				while(rs.next()) {
					String maSach = rs.getString("maSach");
					String tenSach = rs.getString("tenSach");
					int sl = rs.getInt("soLuong");
					dm_chiTietPhieuMuon.addRow(new Object[] {soPhieu,maSach,tenSach,sl});
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public void traPhieu() {
		int selectRow = table_phieuMuon.getSelectedRow();
		int soPhieu = Integer.valueOf(table_phieuMuon.getValueAt(selectRow,0).toString());
		Connection con = JDBCUtil.getConnection();
		String trangThai = "Đã trả";
		try {
			Statement st = con.createStatement();
			String sql = "Update phieumuon SET trangThai ='"+trangThai+"' WHERE soPhieu ='"+soPhieu+"'";
			st.executeUpdate(sql);
			JOptionPane.showMessageDialog(this, "Trả sách thành công !");
			displayPhieuMuon();
			dm_chiTietPhieuMuon.setRowCount(0);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void layDuLieuPhieuMuon() {
		int selectRow = table_phieuMuon.getSelectedRow();
		int soPhieu = Integer.valueOf(table_phieuMuon.getValueAt(selectRow, 0).toString());
		String maDg = table_phieuMuon.getValueAt(selectRow, 1).toString();
		String tenDg = table_phieuMuon.getValueAt(selectRow, 2).toString();
		String ngayMuon = table_phieuMuon.getValueAt(selectRow, 3).toString();
		java.util.Date hanTra = null;
		try {
		    hanTra = new SimpleDateFormat("yyyy-MM-dd").parse(table_phieuMuon.getValueAt(selectRow, 4).toString());
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		String trangThai = table_phieuMuon.getValueAt(selectRow, 5).toString();
		CapNhatPhieuMuon cn = new CapNhatPhieuMuon(QuanLiPhieuMuon.this);
		cn.setVisible(true);
		cn.jtextField_sp.setText(Integer.valueOf(soPhieu).toString());
		cn.jtextField_maDg.setText(maDg);
		cn.jtextField_tenDg.setText(tenDg);
		cn.jtextField_ngayMuon.setText(ngayMuon);
		cn.jDateChooser_hanTra.setDate(hanTra);
		cn.jcomboBox_trangThai.setSelectedItem(trangThai);
		
	}
}
