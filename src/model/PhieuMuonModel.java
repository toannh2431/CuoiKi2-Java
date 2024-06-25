package model;

import java.sql.Date;

public class PhieuMuonModel {
	private int soPhieu;
	private String maDocGia;
	private String tenDocGia;
	private Date ngayMuon;
	private Date hanTra;
	private String trangThai;
	public PhieuMuonModel(int soPhieu, String maDocGia, String tenDocGia, Date ngayMuon, Date hanTra,
			String trangThai) {
		this.soPhieu = soPhieu;
		this.maDocGia = maDocGia;
		this.tenDocGia = tenDocGia;
		this.ngayMuon = ngayMuon;
		this.hanTra = hanTra;
		this.trangThai = trangThai;
	}
	public int getSoPhieu() {
		return soPhieu;
	}
	public void setSoPhieu(int soPhieu) {
		this.soPhieu = soPhieu;
	}
	public String getMaDocGia() {
		return maDocGia;
	}
	public void setMaDocGia(String maDocGia) {
		this.maDocGia = maDocGia;
	}
	public String getTenDocGia() {
		return tenDocGia;
	}
	public void setTenDocGia(String tenDocGia) {
		this.tenDocGia = tenDocGia;
	}
	public Date getNgayMuon() {
		return ngayMuon;
	}
	public void setNgayMuon(Date ngayMuon) {
		this.ngayMuon = ngayMuon;
	}
	public Date getHanTra() {
		return hanTra;
	}
	public void setHanTra(Date hanTra) {
		this.hanTra = hanTra;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	

	
}
