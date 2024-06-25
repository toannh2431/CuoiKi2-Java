package model;

import java.io.Serializable;

public class ChiTietPhieuModel implements Serializable{
	private int soPhieu;
	private String maSach;
	private String tenSach;
	private int sl;
	public ChiTietPhieuModel(int soPhieu, String maSach, String tenSach, int sl) {
		this.soPhieu = soPhieu;
		this.maSach = maSach;
		this.tenSach = tenSach;
		this.sl = sl;
	}
	public int getSoPhieu() {
		return soPhieu;
	}
	public void setSoPhieu(int soPhieu) {
		this.soPhieu = soPhieu;
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
	public int getSl() {
		return sl;
	}
	public void setSl(int sl) {
		this.sl = sl;
	}
	
}
