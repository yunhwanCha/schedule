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
import entity.Schedule;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言　　
@WebServlet("/scheduleInput.do")//サーブレットを scheduleInput.doにマッピングするアノテーション
public class ScheduleInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTPのGET要請を処理するメソッド
		response.sendRedirect("scheduleInput.jsp");
		//HttpServletResponse의 sendRedirect("scheduleInput.jsp") scheduleInput.jsp로 리다이렉트
		//ページを移る
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTPのPOST要請を処理するメソッド
		ScheduleDao ScheduleDatabase = new ScheduleDao();
		//ScheduleDatabaseクラスをインスタンス化してScheduleDatabaseというインスタンスを生成
		HttpSession session = request.getSession();
		//セッションを使うためにHttpServletRequestのgetSession()メソッドを呼び出してsessionに代入
		Schedule schedule = new Schedule();
		//scheduleクラスのインスタンス生成
		String id = (String)session.getAttribute("USER");
		//セッションからIDを呼び出してidに代入
		int schedule_id=ScheduleDatabase.GetScheduleNum();
		//ScheduleDatabaseのGetScheduleNum();を実行して戻り値をschedule_idに代入
		
		String schedule_name=request.getParameter("schedule_name");
		String schedule_date=request.getParameter("schedule_date");;
		String schedule_start=request.getParameter("schedule_start");;
		String schedule_end=request.getParameter("schedule_end");;
		//HttpServletRequestのschedule_name,schedule_date,schedule_start,schedule_endキーの
		//値を呼び出してschedule_name,schedule_date,schedule_start,schedule_endに代入
		
		schedule.setId(id);
		schedule.setSchedule_id(schedule_id);
		schedule.setSchedule_name(schedule_name);
		schedule.setSchedule_date(schedule_date);
		schedule.setSchedule_start(schedule_start);
		schedule.setSchedule_end(schedule_end);
		//スケジュールにid,schedule_id,schedule_name,schedule_date,schedule_start,schedule_endを入力
		
		System.out.println(id);
		System.out.println(schedule_id);
		System.out.println(schedule_name);
		System.out.println(schedule_date);
		System.out.println(schedule_start);
		System.out.println(schedule_end);
		//コンソールにid,schedule_id,schedule_name,schedule_date,schedule_start,schedule_endを示す
		
		ScheduleDatabase.InputSchedule(schedule);
		//ScheduleDatabaseのInputSchedule(schedule);メソッドを実行して
		//scheduleの値でデータベースに入力
		
		response.sendRedirect("schedule.do");
		//クライアントをschedule.doにリダイレクト
		//スケジュール画面に戻る
	}

}
