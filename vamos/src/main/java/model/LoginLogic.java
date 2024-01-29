//白石
package model;

import java.util.List;

/*
 * ログインの際にアカウント登録されているか確認するモデル
 */
public class LoginLogic {
	public boolean execute(String signInId, String password, List<User> userList) {
		boolean bool = false;
		for (User user : userList) {
			if (user.getSignInId().equals(signInId) && user.getPassword().equals(password)) {
				bool = true;
			}
		}
		return bool;
	}
}