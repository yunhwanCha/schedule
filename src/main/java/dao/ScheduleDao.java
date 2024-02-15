package dao;
//データベースにアクセスするDAOパッケージ
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import entity.Cha_schedule;
import entity.Cha_schedule.DateToday;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言

public class ScheduleDao {//Scheduleに関するDBの処理をするメソッド
	//private,protectにするとサーブレットから呼び出せない
	
	private final String MAPPER_NAME = "mapper.scheduleMapper.";
	//スケジュールマッパを常數で宣言
	
	
	public void modifySchedule(Cha_schedule schedule) {//スケジュールを修正するメソッド
		//privateにするとサーブレットから呼び出せない
		
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.update(MAPPER_NAME+"modifySchedule",schedule);
			//scheduleを挿入してmodifyScheduleを実行する
			ss.commit();//変更事項を適用するため
		}finally {
			ss.close();//DBとの接続を切る
		}
	}
	
	public void deleteSchedule(int schedule_id) {
		//schedule_idを入れて検索されたスケジュールを消すメソッド
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.delete(MAPPER_NAME+"deleteSchedule",schedule_id);
			//schedule_idを入れてdeleteScheduleクエリを実行
			ss.commit();//変更事項を適用するため
		}finally {
			ss.close();//DBとの接続を切る
		}
	}
	
	public Cha_schedule findSchedule(int schedule_id) {
		//schedule_id로 검색해서 나온 스케쥴을 불러오는 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		Cha_schedule schedule = new Cha_schedule();//불러온 스케쥴을 넣을 스케쥴 인스턴스 생성
		try {
			schedule = ss.selectOne(MAPPER_NAME+"findSchedule",schedule_id);
			//findSchedule쿼리를 실행하여 나온 결과를 하나만 schedule에 대입
		}finally {
			ss.close();//DBとの接続を切る
		}
		return schedule;//스케쥴을 반환함
	}
	
	public void InputSchedule(Cha_schedule schedule) {
		//schedule를 DB에 입력하기 위한 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.insert(MAPPER_NAME+"inputSchedule",schedule);
			//inputSchedule쿼리를 실행하여 schedule의 값을 입력함
			ss.commit();//변경사항을 적용하는 커밋 쿼리를 실행 커밋하지 않으면 DB에 변경사항이 적용되지 않는다.
		}finally {
			ss.close();//DBとの接続を切る
		}
	}
	
	public Integer GetScheduleNum() {//DBから最も大きなschedule_idの値を呼び出し1を足すメソッド
		Integer num;//最も大きいschedule_idを呼び出すための変数宣言
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			num = ss.selectOne(MAPPER_NAME+"getScheduleNum");
			//getScheduleNumクエリを実行して結果の値を一つだけnumに入れる
		}finally {
			ss.close(); //DBとの接続を切る
		}
		return num+1;//numより1大きい数を変換
	}
	

	public ArrayList<Cha_schedule> getSchedule(DateToday date) {//오늘 날짜 클래스인 DateToday를 입력
		//DB에서 날짜를 기준으로 스케쥴을 검색하여 받는 메서드
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		ArrayList<Cha_schedule> scheduleList = new ArrayList<Cha_schedule>();
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
		//null로 초기화 하지 않으면 
		//SqlSessionFactory factory = builder.build(is);에서
		//The local variable is may not have been initialized라는 오류 가 발생한다
		
		try {//예외가 발생할 수도 있는 코드
			is = Resources.getResourceAsStream(config);//config의 파일 경로를 읽어 is에 대입
		}catch(Exception e) {
			e.printStackTrace();//예외를 출력
		}	
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
