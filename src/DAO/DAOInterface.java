package DAO;


import java.util.ArrayList;

import model.PhieuMuonModel;

public interface DAOInterface<T> {
	public int insert(T t);
	
	public void udate(T t);
	
	public void delete(T t);
	
	public ArrayList<T> selectAll();

	
	
	

	

}
