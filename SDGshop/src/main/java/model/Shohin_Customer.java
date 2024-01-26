package model;

import java.io.Serializable;

public class Shohin_Customer implements Serializable {
//個人情報
	private String mail;
	private String address;
	private String shimei;
	private String kana;
	private String age;
	private String gender;
	private String tel;
	private String cardnum;

//ID、パスワード	
	private String id;
	private String password;
	
//企業側の商品情報
	private String shohin_mei;
	private String hanbai_tanka;
//	private int unit_price;//削除予定
	private int cost;

//ユーザが選んだ商品情報
	private String buy_shohin_mei;
	private String buy_unit_price;
	private String price; //追加
	private String rieki; //追加
	private String nametag;
	private String delivery;

	
	public Shohin_Customer() {}
	
	public Shohin_Customer(String mail, String address, String shimei, 
			String kana, String tel, String cardnum,String age, String gender) {
		this.mail = mail;
		this.address = address;
		this.shimei = shimei;
		this.kana = kana;
		this.tel = tel;
		this.cardnum = cardnum;
		this.age = age;
		this.gender = gender;
	}
	
	public Shohin_Customer(String mail, String address, String shimei, 
			String kana, String age, String gender) {
		this.mail = mail;
		this.address = address;
		this.shimei = shimei;
		this.kana = kana;
		this.age = age;
		this.gender = gender;
	}
	
	public Shohin_Customer(String buy_shohin_mei, String buy_unit_price, String rieki,String nametag, String delivery){
		this.buy_shohin_mei = buy_shohin_mei;
		this.buy_unit_price = buy_unit_price;
		this.rieki = rieki;
		this.nametag = nametag;
		this.delivery = delivery;
	}
	
	//topで使用
	public Shohin_Customer(String shohin_mei, String hanbai_tanka) {
		this.shohin_mei = shohin_mei;
		this.hanbai_tanka = hanbai_tanka;
	}
	
	public Shohin_Customer(String hanbai_tanka) {
		this.hanbai_tanka = hanbai_tanka;
	}
	
	public Shohin_Customer(String id, String password, String mail, String address, 
			String shimei, String kana, String age, String gender, String tel, String cardnum) {
		this.id = id;
		this.password = password;
		this.mail = mail;
		this.address = address;
		this.shimei = shimei;
		this.kana = kana;
		this.age = age;
		this.gender =  gender;
		this.tel = tel;
		this.cardnum = cardnum;
	}
	
	//getter
	public String getMail() {
		return mail;
	}

	public String getAddress() {
		return address;
	}

	public String getShimei() {
		return shimei;
	}

	public String getKana() {
		return kana;
	}

	public String getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getTel() {
		return tel;
	}

	public String getCardnum() {
		return cardnum;
	}

	public String getShohin_mei() {
		return shohin_mei;
	}

	public String getHanbai_tanka() {
		return hanbai_tanka;
	}

//	public int getUnit_price() {
//		return unit_price;
//	}

	public int getCost() {
		return cost;
	}

	public String getBuy_shohin_mei() {
		return buy_shohin_mei;
	}

	public String getBuy_unit_price() {
		return buy_unit_price;
	}

	public String getNametag() {
		return nametag;
	}

	public String getDelivery() {
		return delivery;
	}
	
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	
	public String getPrice() {
		return price;
	}

	public String getRieki() {
		return rieki;
	}

	
	//setter
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public void setShimei(String shimei) {
		this.shimei = shimei;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
