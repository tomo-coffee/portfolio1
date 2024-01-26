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

import model.Shohin_Customer;
import model.ValueCheckLogic;


@WebServlet("/Check")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String address = request.getParameter("address");
		String shimei = request.getParameter("shimei");
		String kana = request.getParameter("kana");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
			if(gender == null) {
				gender = "未選択";
			}else if(gender.equals("0")) {
				gender = "男";
			}else if (gender.equals("1")){
				gender = "女";
			}
			
		String tel = request.getParameter("tel");
		String cardnum = request.getParameter("cardnum");
		
		//以下からは共通処理
		String check = request.getParameter("check");//確認
		String back = request.getParameter("back");//戻る
		String message = "";
		
		
	//「確認」ボタンを押した場合
	if (check != null) {		

		//エラーメッセージの確認
		String errMsg = ValueCheckLogic.check(mail, address, shimei, kana, tel, cardnum);
		
		//入力値に問題があればエラーメッセージを出す
		if (errMsg != "") {
			message = errMsg;

			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/input.jsp");
			dispatcher.forward(request, response);
		
		
		//問題が無ければ、登録	
		}else {
			
		//登録する情報を設定
		Shohin_Customer c = new Shohin_Customer(mail,address,shimei,kana,tel,cardnum,age,gender);
		
		//セッションスコープに登録情報を保存
		HttpSession session = request.getSession();
		List<Shohin_Customer> customer = new ArrayList<Shohin_Customer>();
		customer.add(c);
		session.setAttribute("customer", customer);
		
		//エラーメッセージを削除（確認画面に遷移後戻った場合、エラーメッセージが出るのを防ぐ）
		session.removeAttribute("message");
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/check.jsp");
		dispatcher.forward(request, response);
		
		}
	
	
		
	//「戻る」ボタンを押した場合
	}else if (back.equals(back)) {
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/top.jsp");
		dispatcher.forward(request, response);
	
		}
	}

}