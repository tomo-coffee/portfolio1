//白石
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import enumType.ShisetuField;
import model.DataBaseDAO;
import model.LoginLogic;
import model.Search;
import model.Shisetu;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード(アカウント登録画面へ遷移)
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得(ログインID、パスワード)
		request.setCharacterEncoding("UTF-8");
		String signInId = request.getParameter("signInId");
		String password = request.getParameter("password");
		
		//セッションスコープ
		HttpSession session = request.getSession();
		
		//ID入力パターン(半角英数5文字以内)
		Pattern p = Pattern.compile("^[0-9a-zA-Z]{5}$");
		
		//エラーメッセージ
		String Msg = "";
		
		//入力判定
		String judge = "OK";
		
		//入力値チェック
			//IDが6文字以内かどうかの判定
			if (signInId == null || signInId.length() == 0) {
				Msg += "IDが入力されていません。<br>";
				judge = "NG";
			}
			
			if (signInId != null && p.matcher(signInId).matches()) {
				
			} else {
				Msg += "IDは半角英数字5文字で入力してください。<br>";
				Msg += "IDは大文字、小文字を区別します。<br>";
				judge = "NG";
			}
			
			//パスワードの入力判定
			if (password == null || password.length() == 0) {
				Msg += "パスワードを入力してください。<br>";
				judge = "NG";
			}
			
			if (password != null && p.matcher(password).matches()) {
				
			} else {
				Msg += "パスワードは半角英数字5文字で入力してください。<br>";
				Msg += "パスワードは大文字、小文字を区別します。<br>";
				judge = "NG";
			}
			
			//エラーメッセージをリクエストスコープに保存
			request.setAttribute("Msg", Msg);
		
		//フォワード用
		String forwardPath;
		
		//入力内容によって遷移画面を分岐
		if (judge != null && judge.equals("OK")) {
			//入力値チェックがパスしたら、データベースに接続
			DataBaseDAO dataBaseDAO = new DataBaseDAO();
			
			//ログイン処理
			LoginLogic loginLogic = new LoginLogic();
			List<User> userList = new ArrayList<User>();
			userList = dataBaseDAO.userRead();
			boolean isLogin = loginLogic.execute(signInId, password, userList);
					
			//ユーザー登録がされているかチェック
			if (isLogin) {
				//登録されていたらセッションスコープに保存
				for (User user : userList) {
					if (user.getSignInId().equals(signInId)) {
						session.setAttribute("loginUser", user);
					}
				}
				
				//ログインしていなければ、ログイン後Top画面へ遷移
				List<Shisetu> shisetuInfoFromTop = (List<Shisetu>)session.getAttribute("shisetuInfo");
				if (shisetuInfoFromTop == null) {
					//Top画面にフォワード
					forwardPath = "index.html";
				} else {
					//予約対象の施設を指定
					Search search = new Search();
					List<Shisetu> shisetuInfo = search.searchShisetu(dataBaseDAO.shisetuRead(), ShisetuField.shisetuId, shisetuInfoFromTop.get(0).getShisetuId());
					session.setAttribute("shisetuInfo", shisetuInfo);
					
					//予約画面にフォワード
					forwardPath = "WEB-INF/jsp/reserve.jsp";
				}
			} else {
				//アカウントの登録が無い場合、エラーメッセージを表示
				request.setAttribute("Msg", "ID、またはパスワードが違います。<br>アカウント作成がお済でない場合は、<br>新規アカウント登録をしてください。");
				
				//フォワード(エラーメッセージを表示し同ログイン画面へ)
				forwardPath = "WEB-INF/jsp/login.jsp";	
			}	

		} else {
			//パスしなかった場合再入力させる
			//フォワード(エラーメッセージを表示し同ログイン画面へ)
			forwardPath = "WEB-INF/jsp/login.jsp";
		}
		
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(forwardPath);
		dispatcher.forward(request, response);
	}
}