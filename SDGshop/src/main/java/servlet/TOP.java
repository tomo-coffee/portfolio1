package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CustomerDAO;
import model.GetShohinLogic;
import model.Shohin_Customer;


@WebServlet("/TOP")
public class TOP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);			
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMsg = "";
		String login = request.getParameter("login");
		String gest = request.getParameter("gest");
		String id = request.getParameter("id");
		String password = request.getParameter("password");

		if(gest != null) {
			//			SQLより商品情報を検索する
			GetShohinLogic getShohinLogic = new GetShohinLogic();
			List<Shohin_Customer> shohinList = getShohinLogic.execute();

			//		検索した情報をセッションスコープに保存
			HttpSession session = request.getSession();
			session.setAttribute("shohinList", shohinList);

			//			フォワード	
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
			dispatcher.forward(request, response);

		}else if(login.equals(login)) {
			if(id.equals("") || password.equals("")) {
				errorMsg += "入力内容に誤りがあります。<br>";
				if(id.equals("") && password.equals("")) {
					errorMsg += "IDとパスワードの入力が正しくありません。";
				}else if(id.equals("")) {
					errorMsg += "正しいIDを入力してください。";
				}else if(password.equals("")){
					errorMsg += "正しいパスワードを入力してください。";
				}
				request.setAttribute("errorMsg", errorMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}else {
				CustomerDAO dao = new CustomerDAO();
				String userName = dao.UserCheck(id,password);
				if(userName.equals("Not")) {
					errorMsg += "入力内容に誤りがあります。<br>";
					errorMsg += "IDもしくはパスワードに誤りがあります。";
					request.setAttribute("errorMsg", errorMsg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);

				}else{
					//		SQLより商品情報を検索する
					GetShohinLogic getShohinLogic = new GetShohinLogic();
					List<Shohin_Customer> shohinList = getShohinLogic.execute();
					List<Shohin_Customer> customerList = dao.selectUser(id, password);

					//		検索した情報をセッションスコープに保存
					HttpSession session = request.getSession();
					session.setAttribute("shohinList", shohinList);
					session.setAttribute("customerList", customerList);
					session.setAttribute("userName", userName);

					/*ログインがされているかどうか確認*/	
					CustomerDAO cdao = new CustomerDAO();
					Shohin_Customer logininfo = cdao.selectDB(id, password);
					
					if (logininfo != null) {
						session.setAttribute("info", logininfo);
					}
					
					//			フォワード	
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
					dispatcher.forward(request, response);
				}

			}
		}
	}

}
