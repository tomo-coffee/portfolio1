package model;
//施設情報をもつJavaBeans
import java.io.Serializable;

public class Shisetu implements Serializable{
	//フィールド
	private String shisetuId;
	private String shisetuMei;
	private int capacity;
	private String address;
	private String property;
	private String tel;
	private int fee;
	
	//コンストラクタ（引数0）
	public Shisetu() {}
	
	//コンストラクタ（引数7）
	public Shisetu
	(String shisetuId, String shisetuMei, int capacity, String address, String property, String tel, int fee) {
		this.shisetuId = shisetuId;
		this.shisetuMei = shisetuMei;
		this.capacity = capacity;
		this.address = address;
		this.property = property;
		this.tel = tel;
		this.fee = fee;
	}

	//getterとsetter
	public String getShisetuId() {
		return shisetuId;
	}

	public void setShisetuId(String shisetuId) {
		this.shisetuId = shisetuId;
	}

	public String getShisetuMei() {
		return shisetuMei;
	}

	public void setShisetuMei(String shisetuMei) {
		this.shisetuMei = shisetuMei;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}
	
}
