package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Shohin_CustomerDAO {
	private final String URL = "jdbc:postgresql://localhost:5432/sdgs";
	private final String USER = "postgres";
	private final String PASSWORD = "test";
	
	public Shohin_CustomerDAO() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public int insert(String shimei, String kana, String mail, String address, String age, String gender,
			String shohin_mei, String price, String rieki, String delivery) {
		int insCnt = 0;
		String sql = "";
		sql = "INSERT INTO shohin_customer(shimei, kana, mail, address, age, gender, "
				+ "shohin_mei, price, rieki, delivery )";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
			
			st.setString(1, shimei);
			st.setString(2, kana);
			st.setString(3, mail);
			st.setString(4, address);
			if ("".equals(age)) {
				st.setString(5, "");
			} else {
				st.setString(5, age);
			}
			if ("".equals(gender)) {
				st.setString(6, "");
			} else {
				st.setString(6, gender);
			}
			st.setString(7, shohin_mei);
			st.setInt(8, Integer.parseInt(price));
			st.setInt(9, Integer.parseInt(rieki));
			st.setString(10, delivery);
			
			insCnt = st.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		
		return insCnt;
		
	}

	

}
