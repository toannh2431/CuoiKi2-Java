package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import database.JDBCUtil;
import model.SachModel;

public class SachDAO implements DAOInterface<SachModel> {
	
	public int insert(SachModel t) {
		int ketQua=0;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "INSERT INTO sach(maSach,tenSach,soLuong,theLoai,tacGia,namXuatBan) "
					+ " VALUES(\""+t.getMaSach()+"\",\""+t.getTenSach()+"\",\""+t.getSoLuong()+"\",\""+t.getTheLoai()+"\",\""+t.getTacGia()+"\",\""+t.getNamXuatBan()+"\")";
			ketQua = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	
		
	}
	

	@Override
	public void udate(SachModel t) {
		
		
	}

	@Override
	public void delete(SachModel t) {
		
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "DELETE FROM sach WHERE maSach='"+t.getMaSach()+"'";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<SachModel> selectAll() {
		ArrayList<SachModel> ketQua= new ArrayList<SachModel>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM sach ORDER BY maSach ASC";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String maSach = rs.getString("maSach");
				String tenSach = rs.getString("tenSach");
				int soLuong = rs.getInt("soLuong");
				String theLoai = rs.getString("theLoai");
				String tacGia = rs.getString("tacGia");
				int namXuatBan = rs.getInt("namXuatBan");
				SachModel sach = new SachModel(maSach, tenSach, soLuong, theLoai, tacGia, namXuatBan);
				ketQua.add(sach);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<SachModel> selectAllFindCode(String ma){
		ArrayList<SachModel> ketQua = new ArrayList<SachModel>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM sach WHERE maSach like '%"+ma+"%' or tenSach like '%"+ma+"%'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String maSach = rs.getString("maSach");
				String tenSach = rs.getString("tenSach");
				int soLuong = rs.getInt("soLuong");
				String theLoai = rs.getString("theLoai");
				String tacGia = rs.getString("tacGia");
				int namXuatBan = rs.getInt("namXuatBan");
				SachModel sach = new SachModel(maSach, tenSach, soLuong, theLoai, tacGia, namXuatBan);
				ketQua.add(sach);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
	}
	
}
