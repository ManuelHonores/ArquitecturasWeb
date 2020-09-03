package entity;

import dao.DAOClientImpl;

public class Main {

	public static void main(String[] args) {
		
		try {
			DAOClientImpl dao = new DAOClientImpl();
			//dao.createTable();
			dao.add();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
