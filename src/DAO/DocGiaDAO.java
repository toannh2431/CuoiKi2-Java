package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.JDBCUtil;
import model.DocGiaModel;
import model.SachModel;

public class DocGiaDAO implements DAOInterface<DocGiaModel> {

	@Override
	public int insert(DocGiaModel t) {
		int ketQua = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "INSERT INTO docgia(tenTaiKhoan,tenDocGia,gioiTinh,gmail,soDienThoai)"
					+ " VALUES(\""+t.getTenTaiKhoan()+"\",\""+t.getTenDocGia()+"\",\""+t.getGioiTinh()+"\",\""+t.getGmail()+"\",\""+t.getSoDienThoai()+"\")";
			ketQua = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}

	@Override
	public void udate(DocGiaModel t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(DocGiaModel t) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "DELETE FROM docgia WHERE tenTaiKhoan ='"+t.getTenTaiKhoan()+"'";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<DocGiaModel> selectAll() {
		ArrayList<DocGiaModel> ketQua= new ArrayList<DocGiaModel>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM docgia";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String tenTaiKhoan = rs.getString("tenTaiKhoan");
				String tenDocGia = rs.getString("tenDocGia");
				String gioiTinh = rs.getString("gioiTinh");
				String gmail = rs.getString("gmail");
				String soDienThoai = rs.getString("soDienThoai");
				DocGiaModel nguoiMuon = new DocGiaModel(tenTaiKhoan, tenDocGia, gioiTinh, gmail, soDienThoai);
				ketQua.add(nguoiMuon);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
		}
	public ArrayList<DocGiaModel> selectAllFindCode(String ma) {
		ArrayList<DocGiaModel> ketQua= new ArrayList<DocGiaModel>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM docgia WHERE tenTaiKhoan like '%"+ma+"%' or tenDocGia like '%"+ma+"%'";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				String tenTaiKhoan = rs.getString("tenTaiKhoan");
				String tenDocGia = rs.getString("tenDocGia");
				String gioiTinh = rs.getString("gioiTinh");
				String gmail = rs.getString("gmail");
				String soDienThoai = rs.getString("soDienThoai");
				DocGiaModel nguoiMuon = new DocGiaModel(tenTaiKhoan, tenDocGia, gioiTinh, gmail, soDienThoai);
				ketQua.add(nguoiMuon);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
		}
	

}
