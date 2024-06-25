package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import DAO.DocGiaDAO;
import model.DocGiaModel;
import view.QuanLiSach.CustomTableCellRenderer;

import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class QuanLiDocGia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField jtextField_timKiem;
	private DefaultTableModel dm;
	private JTable table;
	private JScrollPane js;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception{
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiDocGia frame = new QuanLiDocGia();
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
	public QuanLiDocGia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 806, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Quản lí độc giả");
		lblNewLabel_2.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\reader.png"));
		lblNewLabel_2.setForeground(new Color(154, 205, 50));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(314, 11, 169, 38);
		contentPane.add(lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(0, 72, 805, 11);
		contentPane.add(separator_1);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_1.setBounds(164, 94, 75, 29);
		contentPane.add(lblNewLabel_1);
		
		jtextField_timKiem = new JTextField();
		jtextField_timKiem.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				searchReader();
			}
		});
		jtextField_timKiem.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_timKiem.setColumns(10);
		jtextField_timKiem.setBounds(266, 94, 270, 31);
		contentPane.add(jtextField_timKiem);
		
		JButton jbutton_huy = new JButton("Hủy");
		jbutton_huy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtextField_timKiem.setText("");
			}
		});
		jbutton_huy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_huy.setFont(new Font("Arial", Font.PLAIN, 14));
		jbutton_huy.setFocusPainted(false);
		jbutton_huy.setBackground(Color.LIGHT_GRAY);
		jbutton_huy.setBounds(546, 94, 89, 31);
		contentPane.add(jbutton_huy);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(220, 220, 220));
		panel_1.setBounds(49, 174, 716, 323);
		contentPane.add(panel_1);
		
		dm = new DefaultTableModel();
		dm.addColumn("Tên tài khoản");
		dm.addColumn("Tên độc giả");
		dm.addColumn("Giới tính");
		dm.addColumn("Email");
		dm.addColumn("Số điện thoại");
		
		table = new JTable(dm);
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		dm.addRow(new Object[]{"Data 1", "Data 2", "Data 3"});
		dm.addRow(new Object[]{"Data 4", "Data 5", "Data 6"});
		dm.addRow(new Object[]{"Data 7", "Data 8", "Data 9"});
		dm.addRow(new Object[]{"Data 10", "Data 11", "Data 12"});
		TableColumnModel columnModel = table.getColumnModel();
		int columnCount = columnModel.getColumnCount();
		for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
		    TableColumn column = columnModel.getColumn(columnIndex);
		    column.setCellRenderer(new CustomTableCellRenderer());
		}
		js = new JScrollPane(table);
		displayReader();
		js.setBounds(10, 11, 696, 300);
		panel_1.add(js);
		JPopupMenu jPopupMenu = new JPopupMenu();
		JMenuItem menuItem_sua = new JMenuItem("Cập nhật");
		menuItem_sua.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				takeDataReader();
			}
		});
		JMenuItem menuItem_xoa = new JMenuItem("Xóa");
		menuItem_xoa.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteReader();
			}
		});
		jPopupMenu.add(menuItem_sua);
		jPopupMenu.add(menuItem_xoa);

		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				 if (SwingUtilities.isRightMouseButton(e)) {
			            int row = table.rowAtPoint(e.getPoint());
			            int col = table.columnAtPoint(e.getPoint());

			            if (row >= 0 && col >= 0) {
			                table.clearSelection(); // Xóa bất kỳ lựa chọn nào trước khi chọn mới
			                table.addRowSelectionInterval(row, row); // Chọn hàng
			                table.setColumnSelectionInterval(0, table.getColumnCount() - 1); // Chọn toàn bộ cột

			                // Hiển thị menu ngữ cảnh chỉ khi có hàng được chọn
			                if (table.getSelectedRowCount() > 0) {
			                    jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
			                }
			            } else {
			                table.clearSelection();
			            }
			        }
			    }
			});
		 table.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mousePressed(MouseEvent e) {
	                if (SwingUtilities.isRightMouseButton(e)) {
	                    int row = table.rowAtPoint(e.getPoint());
	                    table.getSelectionModel().setSelectionInterval(row, row);
	                }
	            }
	        });
		
		JLabel lblNewLabel = new JLabel("* nhập tên tài khoản hoặc tên độc giả");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(288, 134, 245, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Kích chuột phải vào hàng trong bảng để cập nhật hoặc xóa !");
		lblNewLabel_3.setForeground(new Color(0, 128, 0));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(239, 503, 383, 29);
		contentPane.add(lblNewLabel_3);
	}
	public void displayReader() {
		DocGiaDAO dao = new DocGiaDAO();
		ArrayList<DocGiaModel> list = dao.selectAll();
		dm.setRowCount(0);
		for (DocGiaModel docGiaModel : list) {
			dm.addRow(new Object[] {docGiaModel.getTenTaiKhoan(),docGiaModel.getTenDocGia(),docGiaModel.getGioiTinh(),docGiaModel.getGmail(),docGiaModel.getSoDienThoai()});
			
		}
	}
	public void takeDataReader() {
		int selectRow = table.getSelectedRow();
		if(selectRow == -1) {
			return;
		}
		String tenTaiKhoan = table.getValueAt(selectRow, 0)+"";
		String tenDocGia = table.getValueAt(selectRow, 1)+"";
		String gioiTinh = table.getValueAt(selectRow, 2)+"";
		String gmail = table.getValueAt(selectRow, 3)+"";
		String soDienThoai = table.getValueAt(selectRow, 4)+"";
		
		CapNhatDocGia capNhatDocGia = new CapNhatDocGia(this);
		
		capNhatDocGia.jtextField_tenTaiKhoan.setText(tenTaiKhoan);
		capNhatDocGia.jtextField_tenDocGia.setText(tenDocGia);
		if(gioiTinh.equals("Nam")) {
			capNhatDocGia.jradio_nam.setSelected(true);
		}else {
			capNhatDocGia.jradio_nu.setSelected(true);
		}
		capNhatDocGia.jtextField_gmail.setText(gmail);
		capNhatDocGia.jtextField_sdt.setText(soDienThoai);
		
		capNhatDocGia.setVisible(true);
	}
	public void deleteReader() {
		int selectRow = table.getSelectedRow();
		if(selectRow == -1) {
			return;
		}
		String tenTaiKhoan = table.getValueAt(selectRow, 0)+"";
		String tenDocGia = table.getValueAt(selectRow, 1)+"";
		String gioiTinh = table.getValueAt(selectRow, 2)+"";
		String gmail = table.getValueAt(selectRow, 3)+"";
		String soDienThoai = table.getValueAt(selectRow, 4)+"";
		DocGiaModel model = new DocGiaModel(tenTaiKhoan, tenDocGia, gioiTinh, gmail, soDienThoai);
		DocGiaDAO dao = new DocGiaDAO();
		dao.delete(model);
		JOptionPane.showMessageDialog(this, "Xóa độc giả thành công!");
		displayReader();
		jtextField_timKiem.setText("");
		
	}
	public void searchReader() {
		String ma = jtextField_timKiem.getText();
		DocGiaDAO dao = new DocGiaDAO();
		ArrayList<DocGiaModel> list = dao.selectAllFindCode(ma);
		dm.setRowCount(0);
		for (DocGiaModel docGiaModel : list) {
			dm.addRow(new Object[] {docGiaModel.getTenTaiKhoan(),docGiaModel.getTenDocGia(),docGiaModel.getGioiTinh(),docGiaModel.getGmail(),docGiaModel.getSoDienThoai()});
		}
				
	}
}
