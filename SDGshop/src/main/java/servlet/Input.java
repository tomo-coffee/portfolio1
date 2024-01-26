
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ShohinDAO;
import model.GetShohinLogic;
import model.Shohin_Customer;


@WebServlet("/Input")
public class Input extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/input.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//top.jspから情報を取得し、格納
		request.setCharacterEncoding("UTF-8");
		String exit = request.getParameter("exit");//「ログアウト」
		String next = request.getParameter("next");//「次へ」
		String shohinmei = request.getParameter("shohinmei");
		String delivery = request.getParameter("delivery");
		String nametag = request.getParameter("nametag");
		
		//エラーメッセージ
		String errorMsg = "";
		
/*	入力情報を確認 */
		//入力内容にて、ログアウトを押した場合はログイン画面に戻る
		if(exit != null) {
			//requestからセッションを取得する
			HttpSession sess = request.getSession();

			//セッションにある全ての要素名を取得する
			Enumeration vals = sess.getAttributeNames();

			//取得した要素名をループ処理で全て削除する
			while(vals.hasMoreElements()){
			  String nm = (String)vals.nextElement();
			  sess.removeAttribute(nm);
			}
			
			response.sendRedirect("login.jsp");
	
		//「次へ」を押した場合	
		}else if(next.equals(next)){
			
			//入力内容にて、商品名もしくは配送先が選択されていない場合は、エラーメッセージを渡す
			if(shohinmei == null || delivery == null) {
				errorMsg = "入力内容に誤りがあります。<br>";
				if(shohinmei == null && delivery == null) {
					errorMsg += "商品情報と配送先は必須項目です。";
				}else if(shohinmei == null) {
					errorMsg += "商品情報は必須項目です。";
				}else if(delivery == null){
					errorMsg += "配送先は必須項目です。";
				}
				
				request.setAttribute("errorMsg", errorMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
				dispatcher.forward(request, response);
			}
			
			//DBより金額の情報を取り出す
				GetShohinLogic getShohinLogic = new GetShohinLogic();
				ShohinDAO dao = new ShohinDAO();
				String price = dao.selectPrice(shohinmei);
				String rieki = dao.selectRieki(shohinmei);
				
//			//購入予定商品のみをリスト化
//				List<Shohin_Customer> shohinbuy = getShohinLogic.Shohininput(shohinmei,price,delivery,nametag);
//				
			//セッションスコープに購入予定商品をそれぞれ保存(戻るときに必要)
				HttpSession session = request.getSession();
				
				session.setAttribute("select_shohinmei", shohinmei);
				session.setAttribute("select_delivery", delivery);
				session.setAttribute("text_nametag", nametag);
				
			//登録する購入商品全てのデータをリストに設定
				Shohin_Customer b = new Shohin_Customer(shohinmei,price,rieki,nametag,delivery);
				List<Shohin_Customer> b_shohin = new ArrayList<Shohin_Customer>();
				b_shohin.add(b);
				
				//セッションスコープに保存
				session.setAttribute("b_shohin", b_shohin);

				//個人情報入力画面へフォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/input.jsp");
				dispatcher.forward(request, response);
			
		}
	
	}
}
