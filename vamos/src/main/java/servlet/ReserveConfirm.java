//制作者：髙尾
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enumType.ReserveField;
import enumType.ShisetuField;
import model.DataBaseDAO;
import model.Reserve;
import model.Search;
import model.Shisetu;

@WebServlet("/ReserveConfirm")
public class ReserveConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserveConfirm.jsp");
		dispatcher.forward(request, response);
	}
	
	
		
			
	/*
	 * 「確認する」を押したときの処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String Key = request.getParameter("a");	//予約IDと施設IDを取得
		
		//予約IDと施設IDを切り離して、配列に格納
		String[] key = Key.split(",");
		System.out.println(key[0]);
		System.out.println(key[1]);
		
		//1件分の予約者情報を保存
		DataBaseDAO dataBaseDAO = new DataBaseDAO();
		HttpSession session = request.getSession();
		List<Reserve> Reserve = dataBaseDAO.reserveRead();	//予約情報を全件取得
		List<Reserve> userReserve = new ArrayList<>();
		Search search = new Search();
		userReserve = search.searchReserve(Reserve, ReserveField.reserveId, key[0]);
		session.setAttribute("checkReserve", userReserve);
		
		//1件分の予約施設情報を取得
		List<Shisetu> shisetu = dataBaseDAO.shisetuRead();  //施設情報を全件取得
		List<Shisetu> reserveShisetu = new ArrayList<>();
		reserveShisetu = search.searchShisetu(shisetu, ShisetuField.shisetuId, key[1]);
		session.setAttribute("reserveShisetu", reserveShisetu);
		
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserveConfirm.jsp");
		dispatcher.forward(request, response);
	}

}