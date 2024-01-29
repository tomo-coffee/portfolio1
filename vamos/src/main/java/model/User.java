package model;
//利用者情報をもつJavaBeans
import java.io.Serializable;

public class User implements Serializable{
	//フィールド
	private String signInId;
	private String password;
	private String userName;
	private String phoneNumber;
	
	//コンストラクタ（引数0）
	public User() {}
	
	//コンストラクタ（引数4）
	public User(String signInId, String password, String userName, String phoneNumber) {
		this.signInId = signInId;
		this.password = password;
		this.userName = userName;
		this.phoneNumber = phoneNumber;
	}

	//getterとsetter
	public String getSignInId() {
		return signInId;
	}

	public void setSignInId(String signInId) {
		this.signInId = signInId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
