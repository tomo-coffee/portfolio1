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

import dao.CustomerDAO;
import model.Shohin_Customer;
import model.TourokuCheckLogic;


@WebServlet("/TourokuCheck")
public class TourokuCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String exit = request.getParameter("top");
		String top = request.getParameter("top");

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String mail = request.getParameter("mail");
		String address = request.getParameter("address");
		String shimei = request.getParameter("shimei");
		String kana = request.getParameter("kana");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String tel = request.getParameter("tel");
		String cardnum = request.getParameter("cardnum");
//		String action = request.getParameter("action");

		if (exit != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else if (top != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
			if (gender == null) {
				gender = "";
			} else if (gender.equals("0")) {
				gender = "男性";
			} else if (gender.equals("1")) {
				gender = "女性";
			} 

			CustomerDAO cDAO = new CustomerDAO();
			String errMessage = "";
			errMessage += cDAO.tourokuCheck(id);
			//			System.out.println(message);
			errMessage += TourokuCheckLogic.check(id, password, mail, address, shimei, kana, tel, cardnum);

			if (errMessage != "") {
				Shohin_Customer registerCustomer = new Shohin_Customer(id, password, mail, address, shimei, kana, age, gender, tel, cardnum);
				List<Shohin_Customer> registerUser = new ArrayList<Shohin_Customer>();
				registerUser.add(registerCustomer);

				HttpSession session = request.getSession();
				session.setAttribute("registerUser", registerUser);
				request.setAttribute("errMessage", errMessage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/touroku.jsp");
				dispatcher.forward(request, response);
			} else {
				Shohin_Customer registerCustomer = new Shohin_Customer(id, password, mail, address, shimei, kana, age, gender, tel, cardnum);
				List<Shohin_Customer> registerUser = new ArrayList<Shohin_Customer>();
				registerUser.add(registerCustomer);

				HttpSession session = request.getSession();
				session.setAttribute("registerUser", registerUser);

				String check = request.getParameter("check");
				if (check != null){
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tourokuCheck.jsp");
					dispatcher.forward(request, response);
				} 
			}


		}


	}

}
