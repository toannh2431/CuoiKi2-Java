package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.LookAndFeel;
import javax.swing.MenuElement;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicPopupMenuUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.jtattoo.plaf.texture.TextureLookAndFeel;
import com.jtattoo.plaf.texture.TexturePopupMenuSeparatorUI;

import DAO.SachDAO;
import model.SachModel;

import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class QuanLiSach extends JFrame {

	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JTextField jtextField_timKiem;
	public DefaultTableModel dm;
	public JTable table;
	public JScrollPane js;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws Exception {
		String str = "com.jtattoo.plaf.texture.TextureLookAndFeel";
		UIManager.setLookAndFeel(str);
		try {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLiSach frame = new QuanLiSach();
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
	public QuanLiSach() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 571);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel jpanel_qlSach = new JPanel();
		jpanel_qlSach.setLayout(null);
		jpanel_qlSach.setBackground(UIManager.getColor("Button.background"));
		jpanel_qlSach.setBounds(0, 0, 789, 531);
		contentPane.add(jpanel_qlSach);
		
		JLabel lblNewLabel_1 = new JLabel("Tìm kiếm");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_1.setBounds(56, 92, 75, 29);
		jpanel_qlSach.add(lblNewLabel_1);
		
		jtextField_timKiem = new JTextField();
		jtextField_timKiem.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				searchBook();
			}
		});
		jtextField_timKiem.setFont(new Font("Arial", Font.PLAIN, 12));
		jtextField_timKiem.setColumns(10);
		jtextField_timKiem.setBounds(141, 93, 255, 28);
		jpanel_qlSach.add(jtextField_timKiem);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(new Color(220, 220, 220));
		panel_1.setBounds(45, 173, 716, 322);
		jpanel_qlSach.add(panel_1);
		
		 	dm = new DefaultTableModel();
			dm.addColumn("Mã sách");
			dm.addColumn("Tên sách");
			dm.addColumn("Số lượng");
			dm.addColumn("Thể loại");
			dm.addColumn("Tác giả");
			dm.addColumn("Năm xuất bản");
			
			table = new JTable(dm);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			TableColumnModel columnModel = table.getColumnModel();
			int columnCount = columnModel.getColumnCount();
			for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			    TableColumn column = columnModel.getColumn(columnIndex);
			    column.setCellRenderer(new CustomTableCellRenderer());
			}
			js = new JScrollPane(table);
			displayAllBook();
			js.setBounds(10, 11, 696, 300);
			panel_1.add(js);
			
			JPopupMenu jPopupMenu = new JPopupMenu();
			JMenuItem menuItem_sua = new JMenuItem("Cập nhật");
			menuItem_sua.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					takeDataBook();
					
				}
			});
			JMenuItem menuItem_xoa = new JMenuItem("Xóa");
			menuItem_xoa.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					deleteBook();
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
		
		JButton jbutton_themSach = new JButton("Thêm sách");
		jbutton_themSach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		jbutton_themSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemSach ts = new ThemSach(QuanLiSach.this);
				ts.setVisible(true);
			}
		});
		jbutton_themSach.setForeground(new Color(240, 255, 240));
		jbutton_themSach.setFont(new Font("Arial", Font.BOLD, 15));
		jbutton_themSach.setFocusPainted(false);
		jbutton_themSach.setBackground(new Color(128, 128, 0));
		jbutton_themSach.setBounds(630, 93, 114, 29);
		jpanel_qlSach.add(jbutton_themSach);
		
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
		jbutton_huy.setBounds(406, 92, 89, 31);
		jpanel_qlSach.add(jbutton_huy);
		
		JLabel lblNewLabel_2 = new JLabel("Quản lí sách");
		lblNewLabel_2.setBackground(new Color(245, 245, 245));
		lblNewLabel_2.setIcon(new ImageIcon("D:\\DACS1\\icon and picture\\book1.png"));
		lblNewLabel_2.setForeground(new Color(154, 205, 50));
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel_2.setBounds(336, 11, 151, 38);
		jpanel_qlSach.add(lblNewLabel_2);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(0, 70, 803, 11);
		jpanel_qlSach.add(separator_1);
		
		JLabel lblNewLabel = new JLabel("*nhập mã sách hoặc tên sách");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel.setBounds(165, 128, 206, 20);
		jpanel_qlSach.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Kích chuột phải vào hàng trong bảng để cập nhật hoặc xóa !");
		lblNewLabel_3.setForeground(new Color(0, 128, 0));
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(231, 500, 383, 20);
		jpanel_qlSach.add(lblNewLabel_3);
	}
	
	static class CustomTableCellRenderer extends DefaultTableCellRenderer {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

	        if (!isSelected) {
	            if (row % 2 == 0) {
	                component.setBackground(Color.WHITE); // Màu nền cho hàng chẵn
	            } else {
	                component.setBackground(new Color(245,245,245)); // Màu nền cho hàng lẻ
	            }
	        }

	        return component;
	    }
	}
	
	public void displayAllBook() {
		
			SachDAO dao = new SachDAO();
			ArrayList<SachModel> list = dao.selectAll();
			dm.setRowCount(0);
			for (SachModel sachModel : list) {
				dm.addRow(new Object[] { sachModel.getMaSach(), sachModel.getTenSach(), sachModel.getSoLuong(),
						sachModel.getTheLoai(), sachModel.getTacGia(), sachModel.getNamXuatBan() });
			}

		 
	}
	
	public void takeDataBook() {
		int selectRow = table.getSelectedRow();
		if(selectRow == -1) {
			return;
		}
		String maSach = table.getValueAt(selectRow, 0)+"";
		String tenSach = table.getValueAt(selectRow, 1)+"";
		int sl = Integer.valueOf(table.getValueAt(selectRow, 2)+"");
		String theLoai = table.getValueAt(selectRow, 3)+"";
		String tacGia = table.getValueAt(selectRow, 4)+"";
		int namXuatBan = (int) table.getValueAt(selectRow, 5);
		
		CapNhatSach capNhatSach = new CapNhatSach(this);
		
		capNhatSach.jtextField_maSach.setText(maSach);
		capNhatSach.jtextField_tenSach.setText(tenSach);
		capNhatSach.soLuong.setValue(sl);
		capNhatSach.jcomboBox_namXuatBan.setSelectedItem(String.valueOf(namXuatBan));
		capNhatSach.jcomboBox_theLoai.setSelectedItem(theLoai);
		capNhatSach.jtextField_tacGia.setText(tacGia);
		
		capNhatSach.setVisible(true);
	}
	public void deleteBook() {
		int selectRow = table.getSelectedRow();
		if(selectRow == -1) {
			return;
		}
		String maSach = table.getValueAt(selectRow, 0)+"";
		String tenSach = table.getValueAt(selectRow, 1)+"";
		int sl = Integer.valueOf(table.getValueAt(selectRow, 2)+"");
		String theLoai = table.getValueAt(selectRow, 3)+"";
		String tacGia = table.getValueAt(selectRow, 4)+"";
		int namXuatBan = (int) table.getValueAt(selectRow, 5);
		SachModel model = new SachModel(maSach, tenSach, sl, theLoai, tacGia, namXuatBan);
		SachDAO dao = new SachDAO();
		dao.delete(model);
		JOptionPane.showMessageDialog(this, "Xóa sách thành công!");
		displayAllBook();
		jtextField_timKiem.setText("");
	}
	public void searchBook() {
		String search = jtextField_timKiem.getText();
		SachDAO dao = new SachDAO();
			ArrayList<SachModel> list = dao.selectAllFindCode(search);
			dm.setRowCount(0);
			for (SachModel sachModel : list) {
				dm.addRow(new Object[] {sachModel.getMaSach(),sachModel.getTenSach(),sachModel.getSoLuong(),sachModel.getTheLoai(),sachModel.getTacGia(),sachModel.getNamXuatBan()});
			}
	}
}




