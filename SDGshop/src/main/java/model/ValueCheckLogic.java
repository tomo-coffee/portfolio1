package model;

import java.util.regex.Pattern;

public class ValueCheckLogic {

	/*
	 * 個人情報入力の値チェックを行います。
	 * このメソッドはチェック結果に応じて以下の内容を返します。
	 * 問題なし：空文字
	 * 問題あり：エラーメッセージ
	 */
	public static String check(String mail,
								String address,
								String shimei,
								String kana,
								String tel,
								String cardnum) {
		String errMessage = "";

		// 必須項目チェック
		
		/*郵便番号の確認*/
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
			errMessage += "ふりがなは20文字以内で入力してください。<br>";
			//スペースチェック
		}else if (!isSpace(kana)) {
			errMessage += "ふりがなの姓と名の間は、半角スペースを入れてください。<br>";	
			//ひらがなチェック
		} else if (!isHiragana(kana)) {
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
		}else if (cardnum.length() > 25) {
			errMessage += "クレジットカード番号は25文字以内で入力してください。<br>";
		}else if (!isNumber(cardnum)) {
			errMessage += "クレジットカード番号には整数を入力してください。<br>";
		}

		return errMessage;
	}

	/*（ネットから取得）
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
			} else {
				return false;
			}
				return result;
	}
	
	
	/*（ネットから取得）
	 * 渡された文字列がひらがなかチェックします
	 * 空文字、またはひらがな	：true
	 * 変換不可能				：false
	 */
	public static boolean isHiragana(String val) {
		boolean result = false;
		
	    if (val != null) {
	        Pattern pattern = Pattern.compile("^[\u3040-\u309F ]+$");
	        result = pattern.matcher(val).matches();
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
	
}
