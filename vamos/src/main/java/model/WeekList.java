//佐藤 ver2
package model;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class WeekList implements Serializable{

	final int CALENDARSIZE = 7;
	boolean[] table;
	String[] dateTable;
	String today;
	Calendar memoly;
	List<Reserve> allReserveList;
	
	public WeekList() {}

	public WeekList(List<Reserve> allReserveList,Calendar s) {
		this.allReserveList = allReserveList;
		boolean[]table = {true,true,true,true,true,true,true};
		String[] dateTable = {"","","","","","",""};
		s.set(Calendar.HOUR_OF_DAY, 0);
		s.set(Calendar.MINUTE, 0);
		s.set(Calendar.SECOND,0);
		s.set(Calendar.MILLISECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("M/d");
		
		Calendar todaycal = Calendar.getInstance();
		todaycal.add(Calendar.DAY_OF_MONTH, 1);
		Date d = todaycal.getTime();
		this.today= sdf.format(d);
		
		Calendar f = Calendar.getInstance();
		f = (Calendar) s.clone();
		f.add(Calendar.DAY_OF_MONTH, CALENDARSIZE);
		Date dispCalFinish = f.getTime();
		
		
		
		for(Reserve reserve : allReserveList){
			Date startDate = new Date();
			Date finishDate = new Date();
			startDate = reserve.getStartDate();
			finishDate = reserve.getFinishDate();
			if(dispCalFinish.compareTo(reserve.getStartDate()) == 1){
				Calendar st = Calendar.getInstance();
				Calendar fin = Calendar.getInstance();
				st.setTime(startDate);
				fin.setTime(finishDate);
				Calendar c = Calendar.getInstance();
				c = (Calendar) s.clone();//スタート日付の初期値に設定
				for(int i=0 ;i<7;i++) {
					if(c.compareTo(st)>=0&&c.compareTo(fin)<=0) {
						table[i] = false;
					}
					c.add(Calendar.DAY_OF_MONTH, 1);
				}
			}
		}
		for(int i=0 ;i<7;i++) {
			dateTable[i]=sdf.format(s.getTime());
			s.add(Calendar.DAY_OF_MONTH, 1);
		}
		this.memoly = f;
		this.dateTable = dateTable;
		this.table = table;
	}
	
	public boolean[] getTable() {
		return table;
	}

	public void setTable(boolean[] table) {
		this.table = table;
	}

	public String[] getDateTable() {
		return dateTable;
	}

	public void setDateTable(String[] dateTable) {
		this.dateTable = dateTable;
	}

	public Calendar getMemoly() {
		return memoly;
	}

	public void setMemoly(Calendar memoly) {
		this.memoly = memoly;
	}

	public List<Reserve> getAllReserveList() {
		return allReserveList;
	}

	public void setAllReserveList(List<Reserve> allReserveList) {
		this.allReserveList = allReserveList;
	}

	public String getToday() {
		return today;
	}

	public void setToday(String today) {
		this.today = today;
	}
	
	
	
	
}
