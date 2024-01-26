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
public class ShohinDAO {
	private final String URL = "jdbc:postgresql://localhost:5432/sdgs";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	// コンストラクタ
	public ShohinDAO() {
		/* JDBCドライバの準備 */
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 「商品」テーブルから全てのデータを検索し
	 * 検索結果を返します。
	 */
	public List<Shohin_Customer> selectAll() {
		/* 1) SQL文の準備 */
		String sql = "SELECT * FROM Shohin ORDER BY shohin_id;";

		List<Shohin_Customer> shohinList = null;

		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			shohinList = makeShohinList(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return shohinList;
	}


	public String selectPrice(String shohin_mei) {
		String price = "";
		/* 1) SQL文の準備 */
		String sql = "SELECT hanbai_tanka FROM Shohin WHERE shohin_mei = '"+ shohin_mei +"' ORDER BY shohin_id;";

		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			price = price(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return price;
	}
	
	public String selectRieki(String shohin_mei) {
		String rieki = "";
		/* 1) SQL文の準備 */
		String sql = "SELECT rieki FROM Shohin WHERE shohin_mei = '"+ shohin_mei +"' ORDER BY shohin_id;";

		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			rieki = rieki(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return rieki;
	}
	
	/*
	 * 検索結果をリストで返します。
	 */
	public List<Shohin_Customer> makeShohinList(ResultSet rs) throws Exception {
		// 全検索結果を格納するリストを準備
		List<Shohin_Customer> shohinList = new ArrayList<Shohin_Customer>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String shohin_mei = rs.getString("shohin_mei");
			String hanbai_tanka = rs.getString("hanbai_tanka");

			// 1行分のデータを格納するインスタンス
			Shohin_Customer shohin = new Shohin_Customer(
					shohin_mei,
					hanbai_tanka
					);

			// リストに1行分のデータを追加する
			shohinList.add(shohin);
		}
		return shohinList;
	}

	/*
	 * 検索結果をString型で返します。
	 */
	public String price(ResultSet rs) throws Exception {
		String price = "";
		while (rs.next()) {
			price = rs.getString("hanbai_tanka");
		}
		return price;
	}
	public String rieki(ResultSet rs) throws Exception {
		String rieki = "";
		while (rs.next()) {
			rieki = rs.getString("rieki");
		}
		return rieki;
}
}