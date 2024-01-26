package model;

import java.util.regex.Pattern;

public class TourokuCheckLogic {

	/*
	 * 個人情報入力の値チェックを行います。
	 * このメソッドはチェック結果に応じて以下の内容を返します。
	 * 問題なし：空文字
	 * 問題あり：エラーメッセージ
	 */
	public static String check(String id,
								String password,
								String mail,
								String address,
								String shimei,
								String kana,
								String tel,
								String cardnum) {
		String errMessage = "";

		// 必須項目チェック
		
		/*IDの確認*/
		String value = "[A-Za-z0-9]+$"; // 半角英数字の正規表現
		
		if(id.length() == 0) {
			errMessage += "IDを入力してください。<br>";
		}else if(id.length() < 5 || id.length() > 8) {//入力はあるが、5～8の文字でない場合
			errMessage += "IDは\"5～8桁\"の半角英数字で入力してください。<br>";
		}else if(!(id.matches(value))) { //半角英数字でない場合
			errMessage += "IDは5～8桁の\"半角英数字\"で入力してください。<br>";
		}
		
		if(password.length() == 0) {
			errMessage += "パスワードを入力してください。<br>";
		}else if(!(password.length() == 8)) { //パスワードが8桁でないとき
			errMessage += "パスワードは\"8桁\"の数字で入力してください。<br>";
		}else if(!isNumber(password)) {
			errMessage += "パスワードには数字のみを入力してください。<br>";
		}
		
		if (mail.length() == 0 ) {
			errMessage += "郵便番号を入力してください。<br>";
		}else if (mail.contains("-")) {
			errMessage += "ハイフンは含めずに入力してください。<br>";
		} else if (mail.length() != 7) {//入力はあるが、7文字でない場合
			errMessage += "郵便番号は7文字で入力してください。<br>";
		} else if (!isNumber(mail)) {
			errMessage += "郵便番号には整数を入力してください。<br>";
		}
		
		/*住所の確認*/
		if (address.length() == 0) {
			errMessage += "住所を入力してください。<br>";
		}
		
		/*氏名の確認*/
		if (shimei.length() == 0) {
			errMessage += "氏名を入力してください。<br>";
		}else if (shimei.length() >= 20) {//入力はあるが、20文字を超えていた場合
			errMessage += "氏名は20文字以内で入力してください。<br>";
		}else if (!isSpace(shimei)) {
			errMessage += "氏名の姓と名の間には、半角スペースを入れてください。<br>";
		}
		
		/*ふりがなの確認*/
		if (kana.length() == 0) {
			errMessage += "ふりがなを入力してください。<br>";
		}else if (kana.length() >= 50 ) {
			errMessage += "ふりがなは50文字以内で入力してください。<br>";
			//スペースチェック
//		}else if (!isSpace(kana)) {
//			errMessage += "ふりがなの姓と名の間は、半角スペースを入れてください。<br>";	
//			//ひらがなチェック
//		} else if (!isHiragana(kana)) {
//			errMessage += "ふりがなはひらがなで入力してください。<br>";
		} else if (!isHurigana(kana)) {
			errMessage += "ふりがなの姓と名の間は、半角スペースを入れてください。<br>";
			errMessage += "ふりがなはひらがなで入力してください。<br>";
		}
		
		/*電話番号の確認*/
		if (tel.length() == 0 ) {
			errMessage += "電話番号を入力してください。<br>";
		}else if (tel.length() > 20){
			errMessage += "電話番号は20文字以内で入力してください。<br>";
		}else if (tel.contains("-")) {
			errMessage += "電話番号は、ハイフンを除いて入力してください。<br>";
		}else if (!isNumber(tel)) {
			errMessage += "電話番号には整数を入力してください。<br>";
		}
		
		
		/*クレジットカード番号の確認*/
		if (cardnum.length() == 0) {
			errMessage += "クレジットカード番号を入力してください。<br>";
		}else if (cardnum.length() >= 25) {
			errMessage += "クレジットカード番号は25文字以内で入力してください。<br>";
		}else if (!isNumber(cardnum)) {
			errMessage += "クレジットカード番号には整数を入力してください。<br>";
		}

		return errMessage;
	}

	/*（ネットから取得：1018未確定）
	 * 渡された文字列がint型に変換可能かチェックします。
	 * このメソッドはチェック結果に応じて以下の内容を返します。
	 * 空文字、または変換可能	：true
	 * 変換不可能				：false
	 */
	public static boolean isNumber(String val) {
		boolean result = false;
		
		if (val != null) {
	        Pattern pattern = Pattern.compile("^[0-9]+$|-[0-9]+$");
	        result = pattern.matcher(val).matches();
//	        result = true;
			} else {
				return false;
			}
				return result;
	}
	//参照URL
	//https://medium-company.com/java%e3%81%a7%e6%95%b0%e5%80%a4%e3%83%81%e3%82%a7%e3%83%83%e3%82%af%e3%82%92%e5%ae%9f%e8%a3%85%e3%81%99%e3%82%8b%e6%96%b9%e6%b3%95/
	
	/*（ネットから取得）
	 * 渡された文字列がひらがなかチェックします
	 * 空文字、またはひらがな	：true
	 * 変換不可能				：false
	 */
	public static boolean isHiragana(String val) {
		boolean result = false;
		
	    if (val != null) {
	        Pattern pattern = Pattern.compile("^[\u3040-\u309F]+$");
	        result = pattern.matcher(val).matches();
//	        result =true;
	    }else {
	    	return false;
	    }
	    
	    return result;
	}
	
	
	/*（ネットから取得）
	 * 渡された文字列に全角スペースが含まれているかチェックします
	 * 含まれている				：true
	 * 含まれていない			：false
	 */
	public static boolean isSpace(String val) {
		boolean result = false;
		
		if (val != null) {
	        Pattern pattern = Pattern.compile("^[\\D ]+");
	        result = pattern.matcher(val).matches();
	        
	    }else {
	    	return false;
	    }
	    
	    return result;
	}
	
	public static boolean isHurigana(String kana) {
		return kana.matches("[あ-ん]+\s[あ-ん]+");
	}
	
}