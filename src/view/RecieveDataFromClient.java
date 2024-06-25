package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import model.PhieuDkModel;

public class RecieveDataFromClient extends Thread {
	private Socket clienSocket;

	public RecieveDataFromClient(Socket clienSocket) {
		this.clienSocket = clienSocket;
	}

	@Override
	public void run() {

		try (ObjectInputStream in = new ObjectInputStream(clienSocket.getInputStream())) {
			while (true) {
				Object receivedData = in.readObject();

				if (receivedData instanceof PhieuDkModel) {
					PhieuDkModel receivedPhieu = (PhieuDkModel) receivedData;
					System.out.println(receivedPhieu);
					// Lưu dữ liệu vào cơ sở dữ liệu
					Server gui_Admin = new Server();
//					gui_Admin.saveToDataBase(receivedPhieu);
					// Lấy instance của lớp Invoice
					QuanLiPhieuMuon qlLiPhieuMuon = new QuanLiPhieuMuon();
					qlLiPhieuMuon.updateTablePhieuDk(receivedPhieu);
					// // Cập nhật bảng trong Invoice với dữ liệu mới
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		} catch (IOException e) {
			System.out.println("Error receiving data from client: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error deserializing received data: " + e.getMessage());
		}
		
	}
	

}
