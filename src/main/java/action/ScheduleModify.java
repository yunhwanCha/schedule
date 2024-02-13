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
@WebServlet("/scheduleModify.do")//サーブレットを scheduleModify.doにマッピングするアノテーション
public class ScheduleModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//クラスと逆直列化しようとするオブジェクトのserialVersionUIDを比べて一致しないとInvalidClassExceptionが発生

	ScheduleDao ScheduleDatabase = new ScheduleDao();
	//ScheduleDatabaseをインスタンス化してScheduleDatabaseというインスタンス生成
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http의 GET요청을 처리하는 메서드
		
		int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
		//HttpServletRequest에서 schedule_id라는 키의 파라미터값을 정수로 변환하여 schedule_id에 대입
		HttpSession session = request.getSession();
		//세션을 사용하기 위한 코드
		Schedule schedule = new Schedule();
		//스케쥴 클래스의 인스턴스 생성
		schedule = ScheduleDatabase.findSchedule(schedule_id);
		//ScheduleDatabase에서 findSchedule메서드에 schedule_id의 값을 대입하고 리턴값을 schedule에 대입
		String id = (String)session.getAttribute("USER");
		//session에서 USER 키의 밸류값을 String으로 변환하여 id에 대입
		//세션으로부터 유저 아이디 값을 불러와 사용
		
		String schedule_name=schedule.getSchedule_name();
		//schedule 클래스의 Schedule_name 값을 getter 로 불러와 schedule_name에 대입
		String schedule_date=schedule.getSchedule_date();
		//schedule 클래스의 schedule_date 값을 getter 로 불러와 schedule_date에 대입
		String schedule_start=schedule.getSchedule_start();
		//schedule 클래스의 schedule_start 값을 getter 로 불러와 schedule_start에 대입
		String schedule_end=schedule.getSchedule_end();
		//schedule 클래스의 schedule_end 값을 getter 로 불러와 schedule_end에 대입
		
		System.out.println(id);
		//콘솔창에 id 값을 표시하여 id값을 확인
		System.out.println(schedule_id);
		//콘솔창에 schedule_id 값을 표시하여 schedule_id값을 확인
		System.out.println(schedule_name);
		//콘솔창에 schedule_name 값을 표시하여 schedule_name값을 확인
		System.out.println(schedule_date);
		//콘솔창에 schedule_date 값을 표시하여 schedule_date값을 확인
		System.out.println(schedule_start);
		//콘솔창에 schedule_start 값을 표시하여 schedule_start값을 확인
		System.out.println(schedule_end);
		//콘솔창에 schedule_end 값을 표시하여 schedule_end값을 확인
		
		request.setAttribute("schedule", schedule);
		//http요청에 schedule키의 값으로 schedule을 대입
		request.getRequestDispatcher("scheduleModify.jsp").forward(request, response);
		//서블릿에서 scheduleModify.jsp로 요청을 전달
		//request, response의 값과 함께 이동시킴
	}

	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//http의 POST 요청을 처리하는 메서드
			HttpSession session = request.getSession();
			//세션을 사용하기 위한 코드
			Schedule schedule = new Schedule();
			//스케쥴 클래스의 인스턴스 생성
			String id = (String)session.getAttribute("USER");
			//세션으로부터 유저 아이디 값을 불러와 id에 대입
			
			int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
			//HttpServletRequest에서 schedule_id의 파라미터 값을 불러와 schedule_id에 대입
			String schedule_name=request.getParameter("schedule_name");
			//HttpServletRequest에서 schedule_name의 파라미터 값을 불러와 schedule_name에 대입
			String schedule_date=request.getParameter("schedule_date");
			//HttpServletRequest에서 schedule_date의 파라미터 값을 불러와 schedule_date에 대입
			String schedule_start=request.getParameter("schedule_start");
			//HttpServletRequest에서 schedule_start의 파라미터 값을 불러와 schedule_start에 대입
			String schedule_end=request.getParameter("schedule_end");
			//HttpServletRequest에서 schedule_end의 파라미터 값을 불러와 schedule_end에 대입
			//리퀘스트에 있는 스케쥴의 값을 서블릿에서 쓰기 위해 작성된 코드
			
			
			schedule.setId(id);
			//setter를 이용해 스케쥴 인스턴스의 id에 id값을 삽입
			schedule.setSchedule_id(schedule_id);
			//setter를 이용해 스케쥴 인스턴스의 schedule_id에 schedule_id값을 삽입
			schedule.setSchedule_name(schedule_name);
			//setter를 이용해 스케쥴 인스턴스의 schedule_name에 schedule_name값을 삽입
			schedule.setSchedule_date(schedule_date);
			//setter를 이용해 스케쥴 인스턴스의 schedule_date에 schedule_date값을 삽입
			schedule.setSchedule_start(schedule_start);
			//setter를 이용해 스케쥴 인스턴스의 schedule_start에 schedule_start값을 삽입
			schedule.setSchedule_end(schedule_end);
			//setter를 이용해 스케쥴 인스턴스의 schedule_end에 schedule_end값을 삽입
			
			System.out.println(id);
			System.out.println(schedule_id);
			System.out.println(schedule_name);
			System.out.println(schedule_date);
			System.out.println(schedule_start);
			System.out.println(schedule_end);
//콘솔창에 id,schedule_id,schedule_name,schedule_date,schedule_start,schedule_end 값을 표시
			
			ScheduleDatabase.modifySchedule(schedule);
			//ScheduleDatabase 클래스의 modifySchedule메서드에 schedule를 입력하여 실행
			
			response.sendRedirect("schedule.do");
			//schedule.do 로 리다이렉트 시킴
			//변경이 끝나면 스케쥴 화면으로 되돌아가기
	}

}
