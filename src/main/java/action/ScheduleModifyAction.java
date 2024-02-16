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
			//セッションを使うためにHttpServletRequestのgetSession()メソッドを呼び出してsessionに代入
			Cha_schedule schedule = new Cha_schedule();
			//Cha_scheduleクラスのインスタンス生成
			String id = (String)session.getAttribute("USER");
			//セッションから"USER"キーの値を呼び出してidに入れる
			
			int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
			String schedule_name=request.getParameter("schedule_name");
			String schedule_date=request.getParameter("schedule_date");
			String schedule_start=request.getParameter("schedule_start");
			String schedule_end=request.getParameter("schedule_end");
			//HttpServletRequestでschedule_id,schedule_name,schedule_date,schedule_start,schedule_endの値を呼び出して
			//変数schedule_id,schedule_name,schedule_date,schedule_start,schedule_endに入れる
			//JSPからもらったスケジュールの値を使うためのコード
			
			
			schedule.setId(id);
			schedule.setSchedule_id(schedule_id);
			schedule.setSchedule_name(schedule_name);
			schedule.setSchedule_date(schedule_date);
			schedule.setSchedule_start(schedule_start);
			schedule.setSchedule_end(schedule_end);
			//setterを利用してscheduleに値を入れる
			
			
			ScheduleDatabase.modifySchedule(schedule);
			//scheduleを入れてmodifyScheduleメソッドを実行
			
			response.sendRedirect("schedule.do");
			//schedule.doにリダイレクト
			//変更が終わったらスケジュール画面に戻る
	}

}
