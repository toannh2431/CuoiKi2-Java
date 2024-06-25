package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

import database.JDBCUtil;
import model.PhieuMuonModel;
import model.SachModel;

public class PhieuMuonDAO implements DAOInterface<PhieuMuonModel>{

	@Override
	public int insert(PhieuMuonModel t) {
		int ketQua=0;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "INSERT INTO phieumuon(soPhieu,maDocGia,tenDocGia,ngayMuon,hanTra,trangThai)"
					+ " VALUES(\""+t.getSoPhieu()+"\",\""+t.getMaDocGia()+"\",\""+t.getTenDocGia()+"\",\""+t.getNgayMuon()+"\",\""+t.getHanTra()+"\",\""+t.getTrangThai()+"\")";
			ketQua = st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public void udate(PhieuMuonModel t) {
		
	}

	@Override
	public void delete(PhieuMuonModel t) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "DELETE FROM phieumuon WHERE soPhieu='"+t.getSoPhieu()+"'";
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<PhieuMuonModel> selectAll() {
		
		ArrayList<PhieuMuonModel> ketQua= new ArrayList<PhieuMuonModel>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM phieumuon ORDER BY soPhieu ASC";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int soPhieu = rs.getInt("soPhieu");
				String maDg = rs.getString("maDocGia");
				String tenDg = rs.getString("tenDocGia");
				Date nm = rs.getDate("ngayMuon");
				Date hanTra = rs.getDate("hanTra");
				LocalDate ngayHienTai = LocalDate.now();
				LocalDate ngayMuon = nm.toLocalDate();
				long diff = ChronoUnit.DAYS.between(ngayMuon, ngayHienTai);
				int daysDiff = (int) diff;
				if(daysDiff>7) {
					String tthai = "Quá hạn";
					Connection con2 = JDBCUtil.getConnection();
					Statement st2 = con2.createStatement();
					String sql2 = "UPDATE phieumuon set trangThai = '"+tthai+"'";
					st2.executeUpdate(sql2);
				}
				String trangThai = rs.getString("trangThai");
				PhieuMuonModel phieumuon = new PhieuMuonModel(soPhieu, maDg, tenDg, nm, hanTra, trangThai);
				ketQua.add(phieumuon);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<PhieuMuonModel> selectAllByCode(String search) {
		ArrayList<PhieuMuonModel> ketQua= new ArrayList<PhieuMuonModel>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement st = con.createStatement();
			String sql = "SELECT * FROM phieumuon WHERE soPhieu like '%"+search+"%' OR maDocGia like '%"+search+"%'  ORDER BY soPhieu ASC";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				int soPhieu = rs.getInt("soPhieu");
				String maDg = rs.getString("maDocGia");
				String tenDg = rs.getString("tenDocGia");
				Date ngayMuon = rs.getDate("ngayMuon");
				Date hanTra = rs.getDate("hanTra");
				String trangThai = rs.getString("trangThai");
				PhieuMuonModel phieumuon = new PhieuMuonModel(soPhieu, maDg, tenDg, ngayMuon, hanTra, trangThai);
				ketQua.add(phieumuon);
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	

}
