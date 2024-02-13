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
public class ScheduleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//serialVersionUID 직렬화된 객체의 버전번호로써 1L을 사용
	//버전 충돌을 방지하고 직렬화된 객체의 호환성을 보장하기 위해 사용

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ScheduleDao ScheduleDatabase = new ScheduleDao();
		//ScheduleDatabase클래스를 생성자를 사용하여 ScheduleDatabase라는 이름의 인스턴스 생성
		int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
		//HttpServletRequest에서 schedule_id라는 키의 파라미터값을 정수로 변환하여 schedule_id에 대입
		ScheduleDatabase.deleteSchedule(schedule_id);
		//ScheduleDatabase를 통해 schedule_id의 값을 파라미터로 deleteSchedule메서드를 실행
		//schedule_id에 해당하는 행을 삭제
		response.sendRedirect("schedule.do");
		//schedule.do로 리다이렉트
		//삭제가 끝나면 스케쥴 화면으로 되돌아가기
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HTTP의 POST 요청을 처리하는 메서드
	}

}
