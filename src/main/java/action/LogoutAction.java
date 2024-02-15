package action;
//actionパッケージのクラスを宣言する
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言
@WebServlet("/logout.do")//サーブレットを logout.doにマッピングするアノテーション
//アノテーションがないとJSPからサーブレットを探せない
public class LogoutAction extends HttpServlet {
	//HttpServletを承継していて外部からアクセスできるLogoutActionクラス
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTPのGET要請を処理するメソッド
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("main.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
