package servlet;

import java.io.IOException;
import java.util.Enumeration;
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


@WebServlet("/TourokuKanryou")
public class TourokuKanryou extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Shohin_Customer> registerUser = (List<Shohin_Customer>)session.getAttribute("registerUser");
		
		String back = request.getParameter("modoru");//戻る
		String login = request.getParameter("login");//戻る
		String register = request.getParameter("touroku");//登録
		
		String id = "";
		String password = "";
		String mail = "";
		String address = "";
		String shimei = "";
		String kana = "";
		String age = "";
		String gender = "";
		String tel = "";
		String cardnum = "";
		
		
		for(Shohin_Customer shohin_Customer : registerUser) {
			id = shohin_Customer.getId();
			password = shohin_Customer.getPassword();
			mail = shohin_Customer.getMail();
			address = shohin_Customer.getAddress();
			shimei = shohin_Customer.getShimei();
			kana = shohin_Customer.getKana();
			age = shohin_Customer.getAge();
			gender = shohin_Customer.getGender();
			tel = shohin_Customer.getTel();
			cardnum = shohin_Customer.getCardnum();
		}
		
		if(login != null) {
			//requestからセッションを取得する
			HttpSession sess = request.getSession();

			//セッションにある全ての要素名を取得する
			Enumeration vals = sess.getAttributeNames();

			//取得した要素名をループ処理で全て削除する
			while(vals.hasMoreElements()){
			  String nm = (String)vals.nextElement();
			  sess.removeAttribute(nm);
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);	
			
		}else if (back != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/touroku.jsp");
			dispatcher.forward(request, response);
			
		} else if (register != null) {
			CustomerDAO dao = new CustomerDAO();
			int insCnt = dao.insert(id, password, mail, address, shimei, kana, age, gender, tel, cardnum);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/tourokuKanryou.jsp");
			dispatcher.forward(request, response);
		}
		
		
	}

}
