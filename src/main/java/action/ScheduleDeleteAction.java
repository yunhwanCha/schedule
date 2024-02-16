package action;
//actionパッケージのクラスを宣言する

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScheduleDao;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言

@WebServlet("/scheduleDelete.do")//サーブレットを scheduleDelete.doにマッピングするアノテーション
//アノテーションがないとJSPからサーブレットを探せない
public class ScheduleDeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//HTTPのGET要請を処理するメソッド	
		ScheduleDao ScheduleDatabase = new ScheduleDao();
		//ScheduleDaoクラスをインスタンス化してScheduleDatabaseというインスタンスを生成
		int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
		//HttpServletRequestでschedule_idというキーの値を定数に変換しschedule_idに入れる
		ScheduleDatabase.deleteSchedule(schedule_id);
		// schedule_idの値を入れてdeleteScheduleメソッドを実行
		//検索結果を削除
		response.sendRedirect("schedule.do");
		//schedule.doにリダイレクト
		//スケジュール画面で戻る
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//HTTPのPOST要請を処理するメソッド
	}

}
