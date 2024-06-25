package model;

import java.io.Serializable;
import java.sql.Date;

public class PhieuDkModel implements Serializable{
	private int soPhieu;
	private String maDg;
	private String tenDg;
	private Date ngayDk;
	private String trangThai;
	public PhieuDkModel(int soPhieu, String maDg, String tenDg, Date ngayDk, String trangThai) {
		super();
		this.soPhieu = soPhieu;
		this.maDg = maDg;
		this.tenDg = tenDg;
		this.ngayDk = ngayDk;
		this.trangThai = trangThai;
	}
	public int getSoPhieu() {
		return soPhieu;
	}
	public void setSoPhieu(int soPhieu) {
		this.soPhieu = soPhieu;
	}
	public String getMaDg() {
		return maDg;
	}
	public void setMaDg(String maDg) {
		this.maDg = maDg;
	}
	public String getTenDg() {
		return tenDg;
	}
	public void setTenDg(String tenDg) {
		this.tenDg = tenDg;
	}
	public Date getNgayDk() {
		return ngayDk;
	}
	public void setNgayDk(Date ngayDk) {
		this.ngayDk = ngayDk;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public String toString() {
		return "PhieuDkModel [soPhieu=" + soPhieu + ", maDg=" + maDg + ", tenDg=" + tenDg + ", ngayDk=" + ngayDk
				+ ", trangThai=" + trangThai + "]";
	}
	
}
