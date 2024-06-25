package model;

import java.sql.Date;

public class DocGiaModel {
	private String tenTaiKhoan;
	private String tenDocGia;
	private String gioiTinh;
	private String gmail;
	private String soDienThoai;
	
	public DocGiaModel() {
	}
	
	public DocGiaModel(String tenTaiKhoan, String tenDocGia, String gioiTinh, String gmail, String soDienThoai) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.tenDocGia = tenDocGia;
		this.gioiTinh = gioiTinh;
		this.gmail = gmail;
		this.soDienThoai = soDienThoai;
	}

	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getTenDocGia() {
		return tenDocGia;
	}

	public void setTenDocGia(String tenDocGia) {
		this.tenDocGia = tenDocGia;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	
	
	
	
	
}
