//白石
package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataBaseDAO;
import model.Reserve;

@WebServlet("/ReserveCheckServlet")
public class ReserveCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//予約情報の取得
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		Reserve reserveAll = (Reserve)session.getAttribute("reserveAll");

		//データベースに予約内容を保存
		DataBaseDAO dataBaseDAO = new DataBaseDAO();
		dataBaseDAO.reserveWrite(reserveAll);
		
		//保存したらセッションスコープから予約内容を削除
		session.removeAttribute("reserveAll");
		
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserveComplete.jsp");
		dispatcher.forward(request, response);
	}
}