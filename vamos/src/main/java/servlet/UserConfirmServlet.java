//制作者：渡邊奈津子
package servlet;
//利用者登録確認画面サーブレット
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DataBaseDAO;
import model.User;

@WebServlet("/UserConfirmServlet")
public class UserConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//利用者情報を登録したセッションスコープを呼び出し
		HttpSession session = request.getSession();
		User us = (User)session.getAttribute("user");
		//DataBaseDAOのuserWriteメソッドを呼び出してセッションスコープから書き込む
		DataBaseDAO dao = new DataBaseDAO();
		dao.userWrite(us);
		
		
		session.setAttribute("loginUser", us);
		session.removeAttribute("user");
		
		//userDone.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userDone.jsp");
		dispatcher.forward(request, response);
	}

}
