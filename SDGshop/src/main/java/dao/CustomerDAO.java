package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Shohin_Customer;

/*
 * 「商品」テーブルへのアクセスを担当する
 * DAOクラスです。
 */
public class CustomerDAO {
	private final String URL = "jdbc:postgresql://localhost:5432/sdgs";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	// コンストラクタ
	public CustomerDAO() {
		/* JDBCドライバの準備 */
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String tourokuCheck(String id){
		String message = "";

		String sql = "SELECT id ";
		sql += "FROM Customer ";
		sql += "WHERE id = '" + id + "' ;";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pStmt = con.prepareStatement(sql);){

			//				pStmt.setString(1,id);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				message = "IDが既に使われています<br>";
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();	
		}

		return message;

	}


	//		IDとPWが一致するユーザーを確認
	public String UserCheck(String id, String password){
		String userName = "";

		String sql = "SELECT * ";
		sql += "FROM Customer ";
		sql += "WHERE id = '" + id + "' ";
		sql += "AND password = '" + password + "' ;";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pStmt = con.prepareStatement(sql);){

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				userName = rs.getString("shimei");
				
			} else {
				userName = "Not";
				
			}
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();	
		}

		return userName;

	}

	public List<Shohin_Customer> selectUser(String id, String password) {
		/* 1) SQL文の準備 */
		String sql = "SELECT * ";
		sql += "FROM Customer ";
		sql += "WHERE id = '" + id + "' ";
		sql += "AND password = '" + password + "' ;";

		List<Shohin_Customer> customerList = null;

		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			customerList = makeCustomerList(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return customerList;
	}

	public int insert(String id, String password, String mail, String address, String shimei,
			String kana, String age, String gender, String tel, String cardnum) {
		int insCnt = 0;
		String sql = "";
		sql = "INSERT INTO customer(id, password, shimei, kana, mail, "
				+ "address, tel, cardnum, age, gender)";
		sql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pStmt = con.prepareStatement(sql);) {

			pStmt.setString(1, id);
			pStmt.setString(2, password);
			pStmt.setString(3, shimei);
			pStmt.setString(4, kana);
			pStmt.setString(5, mail);
			pStmt.setString(6, address);
			pStmt.setString(7, tel);
			pStmt.setString(8, cardnum);
			pStmt.setString(9, age);
			pStmt.setString(10, gender);


			insCnt = pStmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();	
		}

		return insCnt;
	}

	/*(内川使用)
	/*
	 * 「顧客」テーブルからIDとパスワードが一致するデータを検索します  
	 */
	public Shohin_Customer selectDB (String id, String password) {
		
		//戻り値の準備
		Shohin_Customer sclogin = new Shohin_Customer();
		
		/* 1) SQL文の準備 */
		String sql = "SELECT * ";
		sql += "FROM Customer ";
		sql += "WHERE id =  ?";
		sql += "AND password =  ? ;";
		
		/* 2) PostgreSQLへの接続 */
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
		
		/* 3) ?に値を入れる */
			st.setString(1, id);
			st.setString(2, password);
				
		/* 4) SQL文の実行 */
			ResultSet rs = st.executeQuery();
	
		/* 4) 値を入れる */
			if (rs.next()) {
				//取得できた場合
				sclogin.setId(rs.getString("id"));
				sclogin.setPassword(rs.getString("password"));
				sclogin.setShimei(rs.getString("shimei"));
				sclogin.setKana(rs.getString("kana"));
				sclogin.setMail(rs.getString("mail"));
				sclogin.setAddress(rs.getString("address"));
				sclogin.setTel(rs.getString("tel"));
				sclogin.setCardnum(rs.getString("cardnum"));
				sclogin.setAge(rs.getString("age"));
				sclogin.setGender(rs.getString("gender"));
			}else {
				sclogin = null;
			}
		 
			}catch (Exception e) {
				System.out.println("DBアクセス時にエラーが発生しました。");
				e.printStackTrace();
				
			}
			return sclogin;
			
	}
	/*
	 * 検索結果をリストで返します。
	 */
	public List<Shohin_Customer> makeCustomerList(ResultSet rs) throws Exception {
		// 全検索結果を格納するリストを準備
		List<Shohin_Customer> customerList = new ArrayList<Shohin_Customer>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String shimei = rs.getString("shimei");
			String kana = rs.getString("kana");
			String mail = rs.getString("mail");
			String address = rs.getString("address");
			String tel = rs.getString("tel");
			String cardnum = rs.getString("cardnum");
			String age = rs.getString("age");
			String gender = rs.getString("gender");

			//				System.out.println(shimei);

			// 1行分のデータを格納するインスタンス
			Shohin_Customer customer = new Shohin_Customer(mail, address, shimei, kana, age, gender, tel, cardnum);

			// リストに1行分のデータを追加する
			customerList.add(customer);
		}
		return customerList;

	}

}
