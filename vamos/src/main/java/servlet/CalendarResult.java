//佐藤
//リザーブ画面の予約状況だけを表示する。（iframeの飛び先）
package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Reserve;
import model.WeekList;


@WebServlet("/CalendarResult")
public class CalendarResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/calendar.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Calendarリザルト入った");
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
				request.getRequestDispatcher("WEB-INF/jsp/calendar.jsp");
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
				request.getRequestDispatcher("WEB-INF/jsp/calendar.jsp");
				dispatcher.forward(request, response);
			}
			
		}
	}

}
