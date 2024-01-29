package model;
//予約情報を持つJavaBeans
import java.io.Serializable;
import java.util.Date;

public class Reserve implements Serializable{
	//フィールド
 	private String reserveId;
	private String userId;
	private String shisetuId;
	private int numberOfpeople;
	private Date startDate;
	private Date finishDate;
	private int sumFee;

	//コンストラクタ（引数0）
	public Reserve() {}
	
	//コンストラクタ（引数6）
	public Reserve
	(String reserveId, String userId, String shisetuId, int numberOfpeople, Date startDate, Date finishDate) {
		this.userId = userId;
		this.reserveId = reserveId;
		this.shisetuId = shisetuId;
		this.numberOfpeople = numberOfpeople;
		this.startDate = startDate;
		this.finishDate = finishDate;
	}
	
	//コンストラクタ（引数7）
		public Reserve
		(String reserveId, String userId, String shisetuId, int numberOfpeople, Date startDate, Date finishDate, int sumFee) {
			this.userId = userId;
			this.reserveId = reserveId;
			this.shisetuId = shisetuId;
			this.numberOfpeople = numberOfpeople;
			this.startDate = startDate;
			this.finishDate = finishDate;
			this.sumFee = sumFee;
		}
		
	//getterとsetter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public String getShisetuId() {
		return shisetuId;
	}

	public void setShisetuId(String shisetuId) {
		this.shisetuId = shisetuId;
	}

	public int getNumberOfpeople() {
		return numberOfpeople;
	}

	public void setNumberOfpeople(int numberOfpeople) {
		this.numberOfpeople = numberOfpeople;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public int getSumFee() {
		return sumFee;
	}

	public void setSumFee(int sumFee) {
		this.sumFee = sumFee;
	}	
}