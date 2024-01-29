//制作者：髙尾＋佐藤　最終更新日2023/10/23 11:41
package servlet;

import java.io.IOException;
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

import enumType.ReserveField;
import model.DataBaseDAO;
import model.Reserve;
import model.Search;
import model.Shisetu;
import model.User;
/*
 * マイページのコントローラ
 */
@WebServlet("/Mypage")
public class  Mypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		//データベース、セッションスコープ
		DataBaseDAO dataBaseDAO = new DataBaseDAO();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loginUser");
		
		//セッションスコープに保存された予約情報を取得
		List<Reserve> reserve = dataBaseDAO.reserveRead();	//予約情報を全件取得
		List<Reserve> userReserve = new ArrayList<>();
		Search search = new Search();
		userReserve = search.searchReserve(reserve, ReserveField.userId, user.getSignInId());
		
		//日付経過分以外の予約を取得
		List<Reserve> noFinishDateList = new ArrayList<Reserve>();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		
		Date date = new Date();
		date = c.getTime();

		//メッセージ
		String Msg = "予約はありません";
		
		for(Reserve r : userReserve) {
			if(r.getFinishDate().compareTo(date) == 1) {
				noFinishDateList.add(r);
			}
		}
		
		//登録結果から結果メッセージを設定
		for(Reserve r : noFinishDateList) {
			if(r.getUserId().equals(user.getSignInId())) {
				Msg = "お客様の予約状況";
			} 
		}
			request.setAttribute("message", Msg);
		

	
		List<Shisetu> shisetu = dataBaseDAO.shisetuRead();
			
		session.setAttribute("noFinishDateList", noFinishDateList);
		session.setAttribute("reserveInfo", userReserve);
		session.setAttribute("shisetuInfo", shisetu);
		// マイページ画面へフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);		
	}	
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//マイページ画面へフォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
		dispatcher.forward(request, response);	
	}

}

