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
public class LogoutAction extends HttpServlet {
	//HttpServlet을 상속받고있고 외부에서 접근 가능한 LoginServlet 클래스
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTP의 GET 요청을 처리하는 메서드
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("main.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
