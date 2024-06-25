package view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import database.JDBCUtil;
import model.SachModel;

public class SendDataToClient implements Runnable {
	private Socket clienSocket;	
	public SendDataToClient(Socket clienSocket) {
		this.clienSocket = clienSocket;
	}

	

	@Override
	public void run() {
		try {
			
				List<SachModel> bookList = new ArrayList<SachModel>();
				try (ObjectOutputStream out = new ObjectOutputStream(clienSocket.getOutputStream());
					     Connection con = JDBCUtil.getConnection();
					     Statement st = con.createStatement();
					     ResultSet rs = st.executeQuery("SELECT * FROM sach ORDER BY maSach asc")) {
					    while (rs.next()) {
					        String maSach = rs.getString("maSach");
					        String tenSach = rs.getString("tenSach");
					        int sl = rs.getInt("soLuong");
					        String theLoai = rs.getString("theLoai");
					        String tacGia = rs.getString("tacGia");
					        int namXuatBan = rs.getInt("namXuatBan");
					        SachModel sachModel = new SachModel(maSach, tenSach, sl, theLoai, tacGia, namXuatBan);
					        bookList.add(sachModel);
					     
					        
					    }
					    out.writeObject(bookList);
					    
					   Thread.sleep(2000);
					} 
				
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

	}

}
