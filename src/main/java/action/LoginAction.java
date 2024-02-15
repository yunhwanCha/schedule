package action;
//actionパッケージのクラスを宣言する
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言
@WebServlet("/login.do")//サーブレットを login.doにマッピングするアノテーション
public class LoginAction extends HttpServlet {
	//HttpServletを承継していて外部からアクセスできるLoginServletクラス
	//アノテーションがないとJSPからサーブレットを探せない
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTP의 GET 요청을 처리하는 메서드
	response.sendRedirect("login.jsp");
	//login.jsp로 리다이렉트시킴
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String login="";
		UserDao userDatabase = new UserDao(); 
		String pwDB = userDatabase.getPw(id);
		if(pwDB == null) {
			login="not ok";
			response.sendRedirect("login.jsp?R="+login);
		}else {
			if(pw.equals(pwDB)) {
				HttpSession session = request.getSession();
				//HTTP要請でセッションを返還するメソッドrequest.getSession();
				session.setAttribute("USER", id);
				//idの値をセッションの"USER"キーに入れる
				response.sendRedirect("main.jsp");
				//"main.jsp"にリダイレクト
				//メーン画面に戻る
			}else {
				login="not ok";//ログイン失敗を表す
				response.sendRedirect("login.jsp?R="+login);
				//loginの値をRという名前で"main.jsp"にリダイレクトするとき一緒に送る
				
			}//else
		}//else

	}//if
	

}
