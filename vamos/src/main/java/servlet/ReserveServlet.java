//白石＋佐藤 ver2
package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataBaseDAO;
import model.FeeCalcLogic;
import model.Reserve;
import model.Shisetu;
import model.User;
import model.WeekList;

@WebServlet("/ReserveServlet")
public class ReserveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserve.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得(予約者情報)
		request.setCharacterEncoding("UTF-8");
		//セッションスコープを準備
		HttpSession session = request.getSession();
		if(request.getParameter("a") != null) {
			if(request.getParameter("a").equals("prev")) {
				WeekList weekList = (WeekList)session.getAttribute("weekList");
				 Calendar calendar = weekList.getMemoly();
				 calendar.add(Calendar.DAY_OF_MONTH, -14);
				 SimpleDateFormat sdf = new SimpleDateFormat("M/d");
				 System.out.println(sdf.format(calendar.getTime()));
				 List<Reserve> ReserveList =weekList.getAllReserveList();
				 weekList  = new WeekList(ReserveList,calendar);
				 for(String s :weekList.getDateTable()) {
				 System.out.println(s);}
				 session.removeAttribute("weekList");
				 session.setAttribute("weekList", weekList);

				//予約画面にフォワード
				RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserve.jsp");
				dispatcher.forward(request, response);
				
			}else if(request.getParameter("a").equals("next")){
				 WeekList weekList = (WeekList)session.getAttribute("weekList");
				 Calendar calendar = weekList.getMemoly();
				 SimpleDateFormat sdf = new SimpleDateFormat("M/d");
				 System.out.println(sdf.format(calendar.getTime()));
				 List<Reserve> ReserveList =weekList.getAllReserveList();
				 weekList  = new WeekList(ReserveList,calendar);
				 session.removeAttribute("weekList");
				 session.setAttribute("weekList", weekList);
				 
				//予約画面にフォワード
				RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserve.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
		String numberOfpeople = request.getParameter("numberOfpeople");	//予約者の利用人数
		String startDate = request.getParameter("startDate");			//利用開始日
		String finishDate = request.getParameter("finishDate");			//利用終了日
		
		//Topページから受け取った予約施設情報
		List<Shisetu> shisetuInfo = (List<Shisetu>)session.getAttribute("shisetuInfo");
		
		//データベースへ接続し、登録情報を取得
		DataBaseDAO dataBaseDAO = new DataBaseDAO();
		
		//エラーメッセージ
		String Msg = "";
				
		//入力判定
		String judge = "OK";
		
		//入力値チェック
		int numberOfPeople = 0;
			//予約者の利用人数入力値チェック
			if (numberOfpeople.equals("") || numberOfpeople.length() == 0) {
				Msg += "利用人数を入力してください。<br>";
				judge = "NG";
			} else {
				numberOfPeople = Integer.valueOf(numberOfpeople);
			}
			
			int max_Capacity = shisetuInfo.get(0).getCapacity();	//予約された施設の収容人数を取得

			if (numberOfpeople != null && numberOfPeople > max_Capacity) {
				Msg += "利用人数が施設収容人数を超えています。<br>";
				judge = "NG";
			}
			
			
			//利用開始日の入力値チェック
			boolean dayCheck = true;
			if (startDate == null || startDate.length() == 0) {
				Msg += "利用開始日を選択してください。<br>";
				judge = "NG";
				dayCheck = false;
			}
			
			//利用終了日の入力値チェック
			if (finishDate == null || finishDate.length() == 0) {
				Msg += "利用終了日を選択してください。<br>";
				judge = "NG";
				dayCheck = false;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendarS = Calendar.getInstance();//String型をDATE型に
			Calendar calendarF = Calendar.getInstance();
			Date s;
			Date f;
			if (dayCheck) {
				try {
					s = sdf.parse(startDate);
					f = sdf.parse(finishDate);
					calendarS.setTime(s);
					calendarF.setTime(f);
					if(calendarS.compareTo(calendarF) == 1) {
						Msg += "利用終了日は利用開始日以降を選んでください";
						dayCheck = false;
					}
				} catch (ParseException e2) {
					e2.printStackTrace();
				}
			}
			
			if (dayCheck) {
				// 利用日の重複を判定する
				try {
					s = sdf.parse(startDate);
					f = sdf.parse(finishDate);//String型をDATE型に
					calendarS.setTime(s);//Date型をCalendar型に
					calendarF.setTime(f);
					List<Reserve> reserveAllList=dataBaseDAO.reserveRead();
					List<Reserve>reserveExtractList = new ArrayList<>();
					for(Reserve reserve :reserveAllList) {
						reserve.getReserveId();
						if(shisetuInfo.get(0).getShisetuId().equals(reserve.getShisetuId())) {
							//検索用リストに追加
							reserveExtractList.add(reserve);
						}
					}
					for(Reserve reserve :reserveExtractList) {
						System.out.println(reserve.getReserveId());
					}
					
					
					boolean hantei = true;
					for(Reserve reserve :reserveExtractList) {
						Calendar startDateCal = Calendar.getInstance();
						Calendar finishDateCal = Calendar.getInstance();
						startDateCal.setTime(reserve.getStartDate());
						finishDateCal.setTime(reserve.getFinishDate());
						
						if(calendarS.compareTo(finishDateCal) ==1 || calendarF.compareTo(startDateCal) ==-1 ) {
						}else {
							hantei = false;
						}
					}
					if(!hantei) {
						Msg += "利用日はすでに予約されています。<br>";
						judge = "NG";
					}
				} catch (ParseException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
			}
			

			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("Msg", Msg);
		
		//入力内容によって遷移画面を分岐(全てパスしたらtrue)
		if (judge != null && judge.equals("OK")) {		
			//日付情報をDate型に変換		
			Date stDate = null;
			try {
				stDate = sdf.parse(startDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			Date finDate = null;
			try {
				finDate = sdf.parse(finishDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			//予約者の情報を取得
			User loginUser = (User)session.getAttribute("loginUser");
			
			//予約情報を取得
			Reserve Reserve = new Reserve("予約番号", loginUser.getSignInId(), shisetuInfo.get(0).getShisetuId(), Integer.parseInt(numberOfpeople), stDate, finDate);
			session.setAttribute("reserveInfo", Reserve);
			
			//万が一、前の予約情報が残らないように、セッションスコープ情報を削除しておく
			session.removeAttribute("sumFee");
			session.removeAttribute("reserveAll");
			
			//使用日数分の料金を計算する
			FeeCalcLogic feeCalcLogic = new FeeCalcLogic();	
			int sumFee = feeCalcLogic.feeCalc(shisetuInfo.get(0).getFee(), Reserve);
			session.setAttribute("sumFee", sumFee);
			
			//予約終了日が予約開始日より前になっていなかったら登録 //以下if文の中身を修正
			if (sumFee > 0) {
				//合計利用料金を追加した全ての予約情報を保存
				Reserve reserveAll = new Reserve("予約番号", loginUser.getSignInId(), shisetuInfo.get(0).getShisetuId(), Integer.parseInt(numberOfpeople), stDate, finDate, sumFee);
				session.setAttribute("reserveAll", reserveAll);
				
				//フォワード(予約最終画面に遷移)
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("WEB-INF/jsp/reserveCheck.jsp");
				dispatcher.forward(request, response);
			} else {
				Msg += "利用終了日は利用開始日以降を選んでください。";
				
				//フォワード(再入力させるために同画面に遷移)
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("WEB-INF/jsp/reserve.jsp");
				dispatcher.forward(request, response);
			}
			
		} else {
			//フォワード(再入力させるために同画面に遷移)
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/reserve.jsp");
			dispatcher.forward(request, response);
		}
	}
}