package action;
//actionパッケージのクラスを宣言する
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import entity.Cha_schedule;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言



@WebServlet("/scheduleModify.do")//サーブレットを scheduleModify.doにマッピングするアノテーション
//アノテーションがないとJSPからサーブレットを探せない
public class ScheduleModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生

	ScheduleDao ScheduleDatabase = new ScheduleDao();
	//ScheduleDatabaseをインスタンス化してScheduleDatabaseというインスタンス生成
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTPのGET要請を処理するメソッド
		
		int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
		//HttpServletRequestで schedule_idの値を整数で変換してschedule_idに代入
		HttpSession session = request.getSession();
		//セッションを使うためのコード
		Cha_schedule schedule = new Cha_schedule();
		//Scheduleクラスのインスタンス生成
		schedule = ScheduleDatabase.findSchedule(schedule_id);
		//ScheduleDatabaseのfindScheduleメソッドにschedule_idの値を挿入して戻り値をscheduleに代入
		String id = (String)session.getAttribute("USER");
		//sessionのUSERキーの値をStringに変換してidに代入
		
		String schedule_name=schedule.getSchedule_name();
		String schedule_date=schedule.getSchedule_date();
		String schedule_start=schedule.getSchedule_start();
		String schedule_end=schedule.getSchedule_end();
		//scheduleクラスにSchedule_name,schedule_date,schedule_start,getSchedule_endを代入
		
		System.out.println(id);
		System.out.println(schedule_id);
		System.out.println(schedule_name);
		System.out.println(schedule_date);
		System.out.println(schedule_start);
		System.out.println(schedule_end);
		//コンソールにid,schedule_id,schedule_name,schedule_date,schedule_start,schedule_endを示す
		
		request.setAttribute("schedule", schedule);
		//http要請にscheduleキーの値でscheduleを挿入
		
		request.getRequestDispatcher("scheduleModify.jsp").forward(request, response);
		//サーブレットからscheduleModify.jspに要請を伝達
		//request, responseの値も一緒に移る
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTPのPOST要請を処理するメソッド
			HttpSession session = request.getSession();
			//세션을 사용하기 위한 코드
			Cha_schedule schedule = new Cha_schedule();
			//Cha_schedule 클래스의 인스턴스 생성
			String id = (String)session.getAttribute("USER");
			//세션으로부터 유저 아이디 값을 불러와 id에 대입
			
			int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
			String schedule_name=request.getParameter("schedule_name");
			String schedule_date=request.getParameter("schedule_date");
			String schedule_start=request.getParameter("schedule_start");
			String schedule_end=request.getParameter("schedule_end");
			//HttpServletRequest에서 schedule_end의 파라미터 값을 불러와 schedule_end에 대입
			//리퀘스트에 있는 스케쥴의 값을 서블릿에서 쓰기 위해 작성된 코드
			
			
			schedule.setId(id);
			schedule.setSchedule_id(schedule_id);
			schedule.setSchedule_name(schedule_name);
			schedule.setSchedule_date(schedule_date);
			schedule.setSchedule_start(schedule_start);
			schedule.setSchedule_end(schedule_end);
			//setter를 이용해 스케쥴 인스턴스의 schedule_end에 schedule_end값을 삽입
			
			
			ScheduleDatabase.modifySchedule(schedule);
			//ScheduleDatabase 클래스의 modifySchedule메서드에 schedule를 입력하여 실행
			
			response.sendRedirect("schedule.do");
			//schedule.do 로 리다이렉트 시킴
			//변경이 끝나면 스케쥴 화면으로 되돌아가기
	}

}
