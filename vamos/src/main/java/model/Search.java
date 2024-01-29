package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import enumType.ReserveField;
import enumType.ShisetuField;
import enumType.UserField;

public class Search {
	//リストをもらい引数で指定した文字や数値で検索する。
	
	//第一引数　検索対象リスト　第二引数　検索対象フィールド　第三引数　検索文字 第四引数　検索数値
	//キャパシティ・料金で検索する場合は第三引数に空文字を渡して第四引数で数字を渡す
	//それ以外の検索を行う場合には第四引数に適当な数字を入れて第三引数に検索したい文字列を渡す
	public List<Shisetu> searchShisetu(List<Shisetu> shisetuList,ShisetuField field,String word) {
		List<Shisetu> newShisetuList = new ArrayList<Shisetu>();
		
		switch(field) {
		case shisetuId:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getShisetuId().equals(word)) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		case shisetuMei:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getShisetuMei()==word) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		case address:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getAddress()==word) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		case property:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getProperty()==word) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		case tel:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getTel()==word) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		}
		return newShisetuList;
	}
	
	public List<Shisetu> searchShisetu(List<Shisetu> shisetuList,ShisetuField field,int value) {
		List<Shisetu> newShisetuList = new ArrayList<Shisetu>();
		
		switch(field) {
		case capacity:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getCapacity()==value) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		case fee:
			for(Shisetu shisetu:shisetuList) {
				if(shisetu.getFee()==value) {
					newShisetuList.add(shisetu);
				}
			}
			break;
		}
		return newShisetuList;
	}
	
	
	
	
	
	
	public List<Reserve> searchReserve(List<Reserve> reserveList,ReserveField field,String word) {
		List<Reserve> newReserveList = new ArrayList<Reserve>();
		
		switch(field) {
		case reserveId:
			
			for(Reserve reserve:reserveList) {
				if(reserve.getReserveId().equals(word)) {
					newReserveList.add(reserve);
				}
			}
			break;
		case userId:
			for(Reserve reserve:reserveList) {
				if(reserve.getUserId().equals(word)) {
					newReserveList.add(reserve);
				}
			}
			break;
		case shisetuId:
			for(Reserve reserve:reserveList) {
				if(reserve.getShisetuId().equals(word)) {
					newReserveList.add(reserve);
				}
			}
			break;
		}
		return newReserveList;
	}
	
	public List<Reserve> searchReserve(List<Reserve> reserveList,ReserveField field,int value) {
		List<Reserve> newReserveList = new ArrayList<Reserve>();
			for(Reserve reserve:reserveList) {
				if(reserve.getNumberOfpeople()==value) {
					newReserveList.add(reserve);
				}
			}
		return newReserveList;
	}
	
	
	
	
	public List<Reserve> searchReserve(List<Reserve> reserveList,ReserveField field,Date date) {
		List<Reserve> newReserveList = new ArrayList<Reserve>();
		switch(field) {
		case startDate:
			for(Reserve reserve:reserveList) {
				if(reserve.getStartDate()==date) {
					newReserveList.add(reserve);
				}
			}
		break;
		case finishDate:
			for(Reserve reserve:reserveList) {
				if(reserve.getFinishDate()==date) {
					newReserveList.add(reserve);
				}
			}
		break;
		}
		return newReserveList;
	}
	
	public List<User> searchUser(List<User> userList,UserField field,String word){
		List<User> newUserList = new ArrayList<User>();
		switch(field) {
		case signInId:
			for(User user : userList) {
				if(user.getSignInId().equals(word)) {
					newUserList.add(user);
				}
			}
			break;
		case password:
			for(User user : userList) {
				if(user.getPassword().equals(word)) {
					newUserList.add(user);
				}
			}
			break;
		case userName:
				for(User user : userList) {
					if(user.getUserName().equals(word)) {
						newUserList.add(user);
					}
				}
				break;
		case phoneNumber:
			for(User user : userList) {
				if(user.getPhoneNumber().equals(word)) {
					newUserList.add(user);
				}
			}
			break;
			}
		
			
		return newUserList;
	}


}
