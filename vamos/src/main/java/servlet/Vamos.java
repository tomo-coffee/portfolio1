//作成者　佐藤
package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataBaseDAO;
import model.Reserve;
import model.Shisetu;
import model.User;
import model.WeekList;


@WebServlet("/Vamos")
public class Vamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//仮情報（後で消す）
//		User user = new User("00001","ABCDE","煉獄　雄大","080-1234-5678");
//		HttpSession session = request.getSession();
//		session.setAttribute("loginUser", user);


		DataBaseDAO dbDao = new DataBaseDAO();
		request.setAttribute("ShisetuAllList", dbDao.shisetuRead());
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/Vamos.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ログイン画面もしくは予約画面に分岐させる。
		//スコープに選択された施設を保存する
		HttpSession session = request.getSession();
		User user = new User();
		user = (User)session.getAttribute("loginUser");
		
		request.setCharacterEncoding("UTF-8");
		String shisetu = request.getParameter("a");
		
		DataBaseDAO dbDao = new DataBaseDAO();
		List<Shisetu>shisetuInfoList = new ArrayList<>();
		for(Shisetu shisetuValue :dbDao.shisetuRead()) {
			if(shisetuValue.getShisetuId().equals(shisetu)) {
				shisetuInfoList.add(shisetuValue);
				session.setAttribute("shisetuInfo", shisetuInfoList);
			}
			session.getAttribute("shisetuInfo");
		}
		List<Reserve> reserveList = new ArrayList<>();
		for(Reserve reserve :dbDao.reserveRead()) {
			if(reserve.getShisetuId().equals(shisetuInfoList.get(0).getShisetuId())) {
				reserveList.add(reserve);
			}
		}
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d");
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		WeekList weekList = new WeekList(reserveList,calendar);
		
		session.setAttribute("weekList", weekList);
		
		if(user == null) {//ログインしていないなら
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}else {//ログインしてるなら
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/reserve.jsp");
			dispatcher.forward(request, response);
		}
	}

}
