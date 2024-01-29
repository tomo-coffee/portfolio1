//佐藤
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataBaseDAO {
	List<Reserve> reserveList;
	List<User> userList;
	List<Shisetu> shisetuList;
	private String msg;//結果のメッセージ
	
	
	//データベース接続情報
	private final String URL = "jdbc:postgresql://localhost:5432/vamos";
    private final String USER = "postgres";
    private final String PASSWORD = "test";
    
    
    //コンストラクター
    public DataBaseDAO(){
    	//JDBCドライバの準備
    	try {
    		Class.forName("org.postgresql.Driver");
    	} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    
    public void reserveWrite(Reserve reserve) {//予約情報の書き込み
    	int r = 0;
    	String sql ="insert into reserve (user_id,shisetu_id,number_of_people,start_date,finish_date,sum_fee) values(?,?,?,?,?,?)";
    	try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		
    		
    		st.setString(1, reserve.getUserId());
    		st.setString(2, reserve.getShisetuId());
    		st.setInt(3, reserve.getNumberOfpeople());
    		
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate1 = simpleDateFormat.format(reserve.getStartDate());
            String formattedDate2 = simpleDateFormat.format(reserve.getFinishDate());
            Date date1 = Date.valueOf(formattedDate1);
            Date date2 = Date.valueOf(formattedDate2);
    		st.setDate(4, date1);//日付の変換が必要
    		st.setDate(5, date2);//日付の変換が必要
    		st.setInt(6, reserve.getSumFee());
    		
    		r = st.executeUpdate();
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("登録時に問題が発生しました。");
    		e.printStackTrace();
    	}
    	if(r > 0) {
	    	this.setMsg("登録が完了しました。");
	    	}
    	
    }
    
    public List<Reserve> reserveRead() {//予約情報の全件取得。フィールドのリストに設定する。
    	List<Reserve> reserveList = null;
    	String sql = "select * from reserve;";
    	try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		ResultSet rs = st.executeQuery();
    		reserveList = reserveList(rs);
    		
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("データ読み込み時に問題が発生しました。");
			e.printStackTrace();
    	}
    	return reserveList;
    }
    public void reserveUpdate(Reserve reserve) {
       	String sql = "update reserve set user_id=?,"
       			+ " shisetu_id=?,number_of_people=?,"
       			+ "start_date=?,finish_date=?,"
       			+ "sum_fee=?"
       			+ " where reserve_id = ?;";
       	int r = 0;

    	try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		
    		st.setString(1, reserve.getUserId());
    		st.setString(2, reserve.getShisetuId());
    		st.setInt(3, reserve.getNumberOfpeople());
    		
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate1 = simpleDateFormat.format(reserve.getStartDate());
            String formattedDate2 = simpleDateFormat.format(reserve.getFinishDate());
            Date date1 = Date.valueOf(formattedDate1);
            Date date2 = Date.valueOf(formattedDate2);
    		st.setDate(4, date1);//日付の変換が必要
    		st.setDate(5, date2);//日付の変換が必要
    		st.setInt(6, reserve.getSumFee());
    		st.setString(7, reserve.getReserveId());
    		
    		r = st.executeUpdate();
    		
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("データ登録時に問題が発生しました。");
			e.printStackTrace();
    	}
    	if(r > 0) {
	    	this.setMsg("登録が完了しました。");
	    	}
    }
    
    public void reserveDelete(Reserve reserve) {
    	String sql = "delete from reserve where reserve_id = ?;";
       	int r = 0;

    	try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		st.setInt(1, Integer.parseInt(reserve.getReserveId()));
    		r = st.executeUpdate();
    		
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("データ登録時に問題が発生しました。");
			e.printStackTrace();
    	}
    	if(r > 0) {
	    	this.setMsg("登録が完了しました。");
	    	}
    }
    
    
    
    	
    
    
    public void userWrite(User user) {//user情報の書き込み
    	int r = 0;
    	String sql ="insert into vamosuser values(?,?,?,?)";
    	try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		st.setString(1, user.getSignInId());
    		st.setString(2, user.getPassword());
    		st.setString(3, user.getUserName());
    		st.setString(4, user.getPhoneNumber());
    		
    		r = st.executeUpdate();
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("登録時に問題が発生しました。");
    		e.printStackTrace();
    	}
    	if(r > 0) {
	    	this.setMsg("登録が完了しました。");
	    	}
    	
    }
    
    public List<User> userRead() {//ユーザー情報の全件取得。フィールドのリストに設定する。
    	List<User> userList = null;
    	String sql = "select * from vamosuser;";
    	try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		ResultSet rs = st.executeQuery();
    		
    		userList = userList(rs);
    		
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("データ読み込み時に問題が発生しました。");
			e.printStackTrace();
    	}
    	return userList;
    }
    
    
    public void shisetuWrite(Shisetu shisetu) {
    	int r = 0;
    	String sql ="insert into shisetu values(?,?,?,?,?,?,?)";
    	try(Connection con =DriverManager.getConnection(URL,USER,PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		st.setString(1, shisetu.getShisetuId());
    		st.setString(2, shisetu.getShisetuMei());
    		st.setInt(3, shisetu.getCapacity());
    		st.setString(4, shisetu.getAddress());
    		st.setString(5, shisetu.getProperty());
    		st.setString(6, shisetu.getTel());
    		st.setInt(7, shisetu.getFee());
    		r = st.executeUpdate();
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("登録時に問題が発生しました。");
    		e.printStackTrace();
    	}
    	if(r > 0) {
	    	this.setMsg("登録が完了しました。");
	    	}
    }
    
    public List<Shisetu> shisetuRead() {//施設情報の全件取得。
    	List<Shisetu> shisetuList = null;
    	String sql = "select * from shisetu;";
    	try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
    		PreparedStatement st = con.prepareStatement(sql)){
    		ResultSet rs = st.executeQuery();
    		
    		shisetuList = shisetuList(rs);
    		
    	}catch(Exception e) {
    		System.out.println("DBアクセス時にエラーが発生しました。");
    		this.setMsg("データ読み込み時に問題が発生しました。");
			e.printStackTrace();
    	}
    	return shisetuList;
    }
    
    public List<Reserve> reserveList(ResultSet rs) throws Exception {
		// 全検索結果を格納するリストを準備
		List<Reserve> reserveList = new ArrayList<Reserve>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String reserveId = rs.getString("reserve_id");
			String userId = rs.getString("user_id");
			String shisetuId = rs.getString("shisetu_id");
			int number_of_people = rs.getInt("number_of_people");
			int sum_Fee = rs.getInt("sum_Fee");
			//date型に変換
			java.util.Date startDate = rs.getTimestamp("start_date");
			java.util.Date finishDate = rs.getTimestamp("finish_date");
			
			// 1行分のデータを格納するインスタンス
			Reserve reserve = new Reserve(reserveId,
										userId,
										shisetuId,
										number_of_people,
										startDate,
										finishDate,
										sum_Fee);
			// リストに1行分のデータを追加する
			reserveList.add(reserve);
		}
		return reserveList;
    }
    
    
    
    public List<User> userList(ResultSet rs) throws Exception {
		// 全検索結果を格納するリストを準備
		List<User> userList = new ArrayList<User>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String userId = rs.getString("user_id");
			String password = rs.getString("password");
			String userName = rs.getString("user_name");
			String tell = rs.getString("tell_number");
			// 1行分のデータを格納するインスタンス
			User user = new User(userId,
								password,
								userName,
								tell);
			// リストに1行分のデータを追加する
			userList.add(user);
		}
		return userList;
    }
    
    
    
    public List<Shisetu> shisetuList(ResultSet rs) throws Exception {
		// 全検索結果を格納するリストを準備
		List<Shisetu> shisetuList = new ArrayList<Shisetu>();

		while (rs.next()) {
			// 1行分のデータを読込む
			String shisetuId = rs.getString("shisetu_id");
			String shisetuMei = rs.getString("shisetu_mei");
			int capacity = rs.getInt("capacity");
			String address = rs.getString("address");
			String property = rs.getString("property");
			String tell = rs.getString("tell_number");
			int fee = rs.getInt("fee");
			

			// 1行分のデータを格納するインスタンス
			Shisetu shisetu = new Shisetu(shisetuId,
										shisetuMei,
										capacity,
										address,
										property,
										tell,
										fee);
			// リストに1行分のデータを追加する
			shisetuList.add(shisetu);
		}
		return shisetuList;
    }
    
	public void setMsg(String msg) {
		this.msg = msg;
	}
    

    
}
