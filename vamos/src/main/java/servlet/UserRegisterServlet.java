//制作者：渡邊奈津子
package servlet;
//利用者登録画面サーブレット
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

import enumType.UserField;
import model.DataBaseDAO;
import model.Search;
import model.User;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//userRegister.jspへフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/userRegister.jsp");
		dispatcher.forward(request, response);
	}

	//doPost
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//リクエストパラメータより利用者登録情報を取得
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("userName");
		String phoneNumber = request.getParameter("phoneNumber");
		String signInId = request.getParameter("signInId");
		String password = request.getParameter("password");
		
		//正規表現チェック
		String errorMsg = "";
		Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{5}$");
		//名前
		if(userName == null || userName.length() == 0) {
			errorMsg += "名前が入力されていません<br>";
			}else if(userName.length() > 10) {
			errorMsg += "10文字以下の名前を入力してください<br>";
		}
		//電話番号
		if(phoneNumber == null || phoneNumber.length() == 0) {
			errorMsg += "電話番号が入力されていません<br>";
			}else if(phoneNumber.length() > 20) {
				errorMsg += "20文字以下の電話番号を入力してください<br>";
		}
		//ID
		if(signInId == null || signInId.length() == 0) {
			errorMsg += "IDが入力されていません<br>";
			}else if(signInId.length() != 5) {
				errorMsg += "5文字のIDを入力してください<br>";
		}
		//ID正規表現
		if(pattern.matcher(signInId).matches() || signInId.equals("")) {
			//半角英数字または空白の場合は正規表現のエラーメッセージを表示しない
			}else {
				errorMsg += "IDは半角英数字で入力してください<br>";
		}
		//パスワード
		if(password == null || password.length() == 0) {
			errorMsg += "パスワードが入力されていません<br>";
			}else if(password.length() != 5) {
				errorMsg += "5文字のパスワードを入力してください<br>";
		}
		//パスワード正規表現
		if(pattern.matcher(password).matches() || password.equals("")) {
			//半角英数字または空白の場合は正規表現のエラーメッセージを表示しない
			}else {
				errorMsg += "パスワードは半角英数字で入力してください<br>";
		}

		//重複チェック
		DataBaseDAO dbDAO = new DataBaseDAO();
		List<User> userList = new ArrayList<User>();
		userList = dbDAO.userRead();
		
		
		Search search = new Search();
		//IDの重複チェック
		List<User> userIdcheckList = new ArrayList<User>();
		userIdcheckList = search.searchUser(userList, UserField.signInId, signInId);
		if(userIdcheckList.size() == 0) {
			//重複なし
		}else {
			errorMsg += "ご希望のIDは既に利用されています<br>";
		}
		
		//名前＆電話番号の重複チェック
		List<User> userNamecheckList = new ArrayList<User>();
		userNamecheckList=search.searchUser(userList, UserField.userName, userName);
		if(userNamecheckList.size() == 0) {
			for(User user : userNamecheckList){
				System.out.println("名前が重複した人"+user.getUserName());
			}
			//重複なし
		}else {
			List<User> userTelcheckList = new ArrayList<User>();
			userTelcheckList=search.searchUser(userNamecheckList, UserField.phoneNumber, phoneNumber);
			if(userTelcheckList.size() == 0) {
				//重複なし
			}else {
				for(User user : userTelcheckList){
					System.out.println("名前が重複した人"+user.getUserName());
				}
				errorMsg += "そのユーザーは既に登録されています<br>";
			}
		}
		
		//エラーメッセージをリクエストスコープに保存
		request.setAttribute("errorMsg", errorMsg);
		
		//フォワード先変更用変数
		String fowardPath;
		
		if(errorMsg == "") {
			//User(JavaBeans)インスタンス生成
			User user = new User(signInId, password, userName, phoneNumber);
		
			//入力値をセッションスコープに値を渡す
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			//UserConfirm.jspへフォワード
			fowardPath = "WEB-INF/jsp/userConfirm.jsp";
		}else {
		
		//UserRegister,jspへフォワード
		fowardPath = "WEB-INF/jsp/userRegister.jsp";
		}
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardPath);
		dispatcher.forward(request, response);
	}

}
