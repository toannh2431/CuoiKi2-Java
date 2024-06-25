package model;

import java.io.Serializable;

public class SachModel implements Serializable {
	private String maSach;
	private String tenSach;
	private int soLuong;
	private String theLoai;
	private String tacGia;
	private int namXuatBan;
	public SachModel(String maSach, String tenSach, int soLuong, String theLoai, String tacGia, int namXuatBan) {
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.soLuong = soLuong;
		this.theLoai = theLoai;
		this.tacGia = tacGia;
		this.namXuatBan = namXuatBan;
	}
	public SachModel() {
	}
	public String getMaSach() {
		return maSach;
	}
	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}
	public String getTenSach() {
		return tenSach;
	}
	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	public Integer getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}
	public String getTheLoai() {
		return theLoai;
	}
	public void setTheLoai(String theLoai) {
		this.theLoai = theLoai;
	}
	public String getTacGia() {
		return tacGia;
	}
	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}
	public int getNamXuatBan() {
		return namXuatBan;
	}
	public void setNamXuatBan(int namXuatBan) {
		this.namXuatBan = namXuatBan;
	}
	@Override
	public String toString() {
		return "SachModel [maSach=" + maSach + ", tenSach=" + tenSach + ", soLuong=" + soLuong + ", theLoai=" + theLoai
				+ ", tacGia=" + tacGia + ", namXuatBan=" + namXuatBan + "]";
	}
	
	
}
