//白石
package model;

public class FeeCalcLogic {
	public int feeCalc(int basicFee, Reserve reserve) {
		//日単価を設定
		int fee = basicFee;
		
		//利用開始日と利用終了日をlong型で算出
		long startDate = reserve.getStartDate().getTime();
		long finishDate = reserve.getFinishDate().getTime();
		
		//経過ミリ秒÷(1000ミリ秒×60秒×60分×24時間)。端数切り捨て。(ここで日数の差を出す)
		int rentDay = (int)((finishDate - startDate) / (1000 * 60 * 60 * 24));
		
		//合計使用料を計算
		int rentFee = fee * (rentDay + 1);	//1日分ズレるため、1を足して補正
		
		return rentFee;	//利用金額の合計を返す(日単価×借日数)
	}
}