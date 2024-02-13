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
	//HttpServlet을 상속받고있고 외부에서 접근 가능한 LoginServlet 클래스
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
				session.setAttribute("USER", id);
				response.sendRedirect("main.jsp");
			}else {
				login="not ok";
				response.sendRedirect("login.jsp?R="+login);
				return;
			}//else
		}//else

	}//if
	

}
