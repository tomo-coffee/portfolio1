//制作者：髙尾
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

import model.DataBaseDAO;
import model.Reserve;

@WebServlet("/ReserveConfirmCancel")
public class ReserveConfirmCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String reserveId = request.getParameter("b");
				
		//予約IDで予約をキャンセル
		//データベース、セッションスコープのインスタンス作成
		DataBaseDAO dataBaseDAO = new DataBaseDAO();
		HttpSession session = request.getSession();
		
		//キャンセルする予約情報をセッションスコープから取得
		List<Reserve> cancelReserve = (List<Reserve>)session.getAttribute("checkReserve");
		
		//キャンセル処理
		Reserve reserveDel = new Reserve(reserveId, "00001", cancelReserve.get(0).getShisetuId(), cancelReserve.get(0).getNumberOfpeople(), cancelReserve.get(0).getStartDate(), cancelReserve.get(0).getFinishDate(), cancelReserve.get(0).getSumFee());
		dataBaseDAO.reserveDelete(reserveDel);
		
		// レスポンス後に画面に表示する結果メッセージ
		String Msg = "";
		int delCnt = 1;
		
		 //削除結果から結果メッセージを設定
		if (delCnt == 1) {
			Msg = "キャンセルしました";
		}

		// 結果メッセージをリクエストスコープに保存
		request.setAttribute("message", Msg);
		
		//フォワード
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("WEB-INF/jsp/reserveConfirmCancel.jsp");
		dispatcher.forward(request, response);
	}
}