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

import dao.Shohin_CustomerDAO;
import model.Shohin_Customer;

@WebServlet("/Close")
public class Close extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String done = request.getParameter("done");//追加
		HttpSession session = request.getSession();

		
	if (done != null) {//追加（ifで購入完了の時)
		
		String mail = "";
		String address = "";
		String shimei = "";
		String kana = "";
		String age = "";
		String gender = "";
		String buy_shohin_mei = "";
		String buy_unit_price = "";
		String rieki = "";
		String delivery = "";
		
		//個人情報のスコープを取得し、配列に入れる
		
		List<Shohin_Customer> listCustomer = (List<Shohin_Customer>)session.getAttribute("customer");//customerListから変更
		
		for (Shohin_Customer customer : listCustomer) {
			mail = customer.getMail();
			address = customer.getAddress();
			shimei = customer.getShimei();
			kana = customer.getKana();
			age = customer.getAge();
			gender = customer.getGender();
		}
			
		
		//商品選択のスコープを取得する
		List<Shohin_Customer> listShohin = (List<Shohin_Customer>)session.getAttribute("b_shohin");
		
		for (Shohin_Customer shohin : listShohin) {
			buy_shohin_mei = shohin.getBuy_shohin_mei();
			buy_unit_price = shohin.getBuy_unit_price();
			rieki = shohin.getRieki();
			delivery = shohin.getDelivery();
		}
		
		
		
		Shohin_CustomerDAO dao = new Shohin_CustomerDAO();
		int insCnt = dao.insert(shimei, kana, mail, address, age, gender, buy_shohin_mei, buy_unit_price, rieki, delivery);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/close.jsp");
		dispatcher.forward(request, response);
		}
	
	
	}

}