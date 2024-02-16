package action;
//actionパッケージのクラスを宣言する

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScheduleDao;
import entity.Cha_schedule;

@WebServlet("/schedule.do")//サーブレットを schedule.doにマッピングするアノテーション
//アノテーションがないとJSPからサーブレットを探せない

public class ScheduleAction extends HttpServlet {
	//HttpServletを承継していて、外部からアクセスできるScheduleDoクラス
	//アノテーションがないとJSPからサーブレットを探せない
	
	//public　全てのクラスでもアクセスできるようにするアクセス修飾子
	//Illegal modifier for the class ScheduleDo; only public, abstract & final are permitted
	//WebアプリケーションでクライアントのHTTP要請を処理するためpublicを使う
	
	//extends承継をする
	//HttpServlet　JAVAでHTTPに対して要請を処理する基本クラス
	//HttpServletの機能をScheduleDoクラスで使うために承継をする
	//extends HttpServletを消すとサーブレットって認識されず、一般的なクラスになる
	
	private static final long serialVersionUID = 1L;
	//private現在のクラスだけでアクセスできる
	//static クラスをインスタンス化しなくてもアクセスできるようにする
	//クラス名.メンバー名のような形式でアクセスできる
	//final メンバーの上書きを禁止
	//long 8バイト(64ビット)の整数
	
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生
	//1Lは最初のバージョンを示す。クラスの構造が変わった時、アップデートすることを勧める
	
	//直列化（シリアライズ）；データの転送、ファイルに貯蔵するためにオブジェクトを
	//連続するバイナリデータやテキストデータに変換するプロセス
	
	//逆直列化（デシリアライズ）；シリアライズされて一つの文字列やバイト列に変換されたデータを
	//元のデータ構造やオブジェクトに復元するプロセス
	
	ScheduleDao ScheduleDatabase = new ScheduleDao();
	//ScheduleDatabaseクラスをインスタンス化してScheduleDatabaseというインスタンス生成
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//protected現在のクラスとサブクラスからアクセスできるアクセス修飾者
		//サーブレットコンテナから呼び出されるメソッドなのでPrivateは使えない
		//Publicの場合、全てのクラスで接近可能になるので、保安のために使わない
		
		//void 戻り値がないメソッド
		//doGet HTTPのGET要請を処理するメソッド
		
		//HttpServletRequest クライアントがサーバーに送るHTTP要請
		//HttpServletResponse サーバーからクライアントに応答を送る

		//throws 例外を宣言
		
		//ServletException サーブレットの処理中発生する例外
		//これを消すとrequest.getRequestDispatcher("schedule.jsp").forward(request, response);で
		//Unhandled exception type ServletExceptiondのエラーが発生
		
		//IOException 入出力中発生する例外
		//これを消すとHttpServletResponseを使ったコードでUnhandled exception type IOExceptionが発生

		
		HttpSession session = request.getSession();
		//HTTP要請でセッションを返還するメソッドrequest.getSession();
		//ユーザーを識別してユーザーに合う情報を貯蔵できる
		
		String id = (String)session.getAttribute("USER");
		//セッションから"USER"キーの値を呼び出してidに入れる
		//ログインしているユーザーのID情報を呼び出すために使う
		
		Integer weeks;
		//Integer 整数を扱うデータ型
		//週を示すために使う変数
		
		String weeksParam=request.getParameter("weeks");
		//httprequestから "weeks"というキーの値を呼び出す
		if(org.apache.commons.lang3.StringUtils.isEmpty(weeksParam)) {//weeksParamがnullや長さが0の場合
			//Apache Commons LangライブラリのStringUtils.isEmpty()メソッド
			//weeksParamがnullや長さが0ならtrueそうではないとfalse
			//org.apache.commons.lang3.StringUtils.isEmpty()は()内の値がnullや長さが0の場合trueをリターン
			weeks=0;
			//weeksParamがnullや長さが0ならweeksの値を0にする
			//weeksの値が0な場合、今週のスケジュールを画面に表示
			
		}else{//weeksParamがnullではなく長さも0ではない場合
			weeks=Integer.parseInt(weeksParam);
			//weeksの値はweeksParamの値をIntegerで変換する値
			//週単位でスケジュールを呼び出すためにweeksを宣言
		}
		 LocalDate today = LocalDate.now().plusDays(weeks);
		 //日付のデータ型LocalDate
		 //now()現在の日付
		 //plusDays() ()中の数字を加える
	     LocalDate startOfWeekDate = today.with(DayOfWeek.MONDAY);
	     //todayを含む週の月曜日の日付
	     LocalDate endOfWeekDate = today.with(DayOfWeek.SUNDAY);
	     //todayを含む週の日曜日の日付
	     //todayを含む週のスケジュールだけ呼び出すために作成
	     
	     String startOfWeek = startOfWeekDate.toString();
	     //LocalDate型のstartOfWeekDateをStringに変換してstartOfWeekに代入
	     String endOfWeek = endOfWeekDate.toString();
	     //LocalDate型のendOfWeekDateをStringに変換してendOfWeekに代入
	     //データベースで使うためにStringに変換
	     
	     //DateToday date = new DateToday();
	     Cha_schedule schedule = new Cha_schedule();
	     Cha_schedule.DateToday date = schedule.new DateToday();
	     //DateTodayというCha_scheduleの内部クラスをインスタンス化してdateというインスタンスを生成
	     //DateTodayクラスにはstartOfWeek,endOfWeek,idが存在
	     //DTO(Data Transfer Object)として使うクラス
	     
	     
	     System.out.println(startOfWeek);
	     System.out.println(endOfWeek);
	     System.out.println(id);
	     //コンソールにid,startOfWeek,endOfWeekの値を示す
	     //コンソールでid,startOfWeek,endOfWeekの値を確認するため作成
	     
	     date.setEndOfWeek(endOfWeek);
	     //DateTodayクラスdateのEndOfWeekにendOfWeekの値を代入
	     date.setStartOfWeek(startOfWeek);
	     //DateTodayクラスdateのstartOfWeekにstartOfWeekの値を代入
	     date.setId(id);
	     //DateTodayクラスdateのidにidの値を代入
	     //privateなので接近ができないのでsetを使って値を入れる
	     
		ArrayList<Cha_schedule> scheduleList = new ArrayList<Cha_schedule>();
		//Scheduleオブジェクトを貯蔵するArrayList生成
		
		if(id==null) {//idの値がnullの場合
			response.sendRedirect("login.jsp");//ログインページに移る
		}else {//idの値がnullではない場合
			scheduleList = ScheduleDatabase.getSchedule(date);
			//ScheduleDatabaseのgetSchedule()メソッドにdateの値を入れて実行する。
			//その戻り値をscheduleListに代入
			
			request.setAttribute("schedule", scheduleList);
			//サーブレットからjspにscheduleListの値をscheduleというキーの値で設定して送る
			request.setAttribute("weeks", weeks);
			//サーブレットからjspにweeksの値をweeksというキーの値で設定して送る
			
			request.getRequestDispatcher("schedule.jsp").forward(request, response);
			//getRequestDispatcher("schedule.jsp") サーブレットからschedule.jspに要請を伝達
			//forward()ページを移る
			//forward(request, response) request, responseの値も移る
		}
		
	}

	//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost HTTPのPOST要請を処理するメソッド
	//}
	//schedule.doへのPOST要請がなくてdoPostを消してもエラーがない

}
