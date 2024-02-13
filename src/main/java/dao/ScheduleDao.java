package dao;
//データベースにアクセスするDAOパッケージ
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import entity.DateToday;
import entity.Schedule;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言
public class ScheduleDao {//Scheduleに関するDBの処理をするメソッド
	private final String MAPPER_NAME = "mapper.scheduleMapper.";
	//スケジュールマッパを常數で宣言
	
	
	public void modifySchedule(Schedule schedule) {//スケジュールを修正するメソッド
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.update(MAPPER_NAME+"modifySchedule",schedule);
			//schedule를 삽입해 modifySchedule를 실행함
			ss.commit();//변경사항을 적용하는 커밋 쿼리를 실행
		}finally {
			ss.close();//DB와의 연결을 끊음
		}
	}
	
	public void deleteSchedule(int schedule_id) {
		//schedule_id로 검색해서 나온 스케쥴을 삭제하는 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.delete(MAPPER_NAME+"deleteSchedule",schedule_id);
			//schedule_id를 삽입해 deleteSchedule를 실행함
			ss.commit();//변경사항을 적용하는 커밋 쿼리를 실행
		}finally {
			ss.close();//DB와의 연결을 끊음
		}
	}
	
	public Schedule findSchedule(int schedule_id) {
		//schedule_id로 검색해서 나온 스케쥴을 불러오는 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		Schedule schedule = new Schedule();//불러온 스케쥴을 넣을 스케쥴 인스턴스 생성
		try {
			schedule = ss.selectOne(MAPPER_NAME+"findSchedule",schedule_id);
			//findSchedule쿼리를 실행하여 나온 결과를 하나만 schedule에 대입
		}finally {
			ss.close();//DB와의 연결을 끊음
		}
		return schedule;//스케쥴을 반환함
	}
	
	public void InputSchedule(Schedule schedule) {
		//schedule를 DB에 입력하기 위한 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.insert(MAPPER_NAME+"inputSchedule",schedule);
			//inputSchedule쿼리를 실행하여 schedule의 값을 입력함
			ss.commit();//변경사항을 적용하는 커밋 쿼리를 실행
		}finally {
			ss.close();// db와의 연결을 끊음
		}
	}
	
	public Integer GetScheduleNum() {//DB에서 schedule_id가 가장 큰 값을 불러와 1을 더한 값을 반환하는 메서드
		Integer num;//가장 큰 schedule_id를 불러오기 위한 변수 선언
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			num = ss.selectOne(MAPPER_NAME+"getScheduleNum");
			//getScheduleNum쿼리를 실행하고 결과값을 하나만 불러와 num에 대입
		}finally {
			ss.close(); // db와의 연결을 끊음
		}
		return num+1;//num보다 1 큰 수를 반환함
	}
	

	public ArrayList<Schedule> getSchedule(DateToday date) {//오늘 날짜 클래스인 DateToday를 입력
		//DB에서 날짜를 기준으로 스케쥴을 검색하여 받는 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		ArrayList<Schedule> scheduleList = new ArrayList<Schedule>();
		//스케쥴들을 저장하기 위한 ArrayList 생성
		try {
			scheduleList = (ArrayList)ss.selectList(MAPPER_NAME+"getSchedule",date);
			//date를 삽입하여 매퍼의 getSchedule 쿼리를 실행 후 결과를 리스트로 받음
		}finally {
			ss.close();//데이터베이스와의 연결을 종료
		}
		return scheduleList;
		//scheduleList의 스케쥴들을 반환함
	}
	
	private SqlSession getSession() {//mybatis와 상호작용하기 위한 메서드
		String config = "mybatisConfig.xml";
		//mybatisConfig.xml 에서 설정을 가져와 config에 대입
		
		InputStream is = null;
		//설정 파일을 읽어오기 위한 InputStream을 초기화
		
		try {//예외가 발생할 수도 있는 코드
			is = Resources.getResourceAsStream(config);//config의 파일 경로를 읽어 is에 대입
		}catch(Exception e) {}//예외를 처리하지 않음
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//MyBatis의 SqlSessionFactoryBuilder를 사용하여 SqlSessionFactory를 생성
		SqlSessionFactory factory = builder.build(is);
		//SqlSessionFactoryBuilder에서 is 의 주소에서 설정파일을 불러와 SqlSessionFactory생성
		SqlSession ss = factory.openSession();
		//SqlSessionFactory의 openSession()으로 SqlSession 생성
		//SqlSession 데이터베이스 연산을 수행하기 위한 인터페이스
		
		
		return ss;//SqlSession ss 를 반환
}
	
	
}
