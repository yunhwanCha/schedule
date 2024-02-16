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
		//schedule_idで検索してその結果をもらうためのメソッド
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		Cha_schedule schedule = new Cha_schedule();//呼び出したスケジュールを入れるCha_scheduleインスタンス生成
		try {
			schedule = ss.selectOne(MAPPER_NAME+"findSchedule",schedule_id);
			//findScheduleクエリを実行してその結果を一つだけをscheduleに入れる
		}finally {
			ss.close();//DBとの接続を切る
		}
		return schedule;//scheduleを返還
	}
	
	public void InputSchedule(Cha_schedule schedule) {
		//scheduleをDBに入力するメソッド
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		try {
			ss.insert(MAPPER_NAME+"inputSchedule",schedule);
			//inputScheduleクエリを実行してscheduleを入力する
			ss.commit();//変更事項を適用するため
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
		//DBから特定な日付が含まれている週のスケジュールを検索するメソッド
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		ArrayList<Cha_schedule> scheduleList = new ArrayList<Cha_schedule>();
		//スケジュールを貯蔵するリストを宣言
		try {
			scheduleList = (ArrayList)ss.selectList(MAPPER_NAME+"getSchedule",date);
			//dateを入れてgetScheduleクエリを実行してその結果をscheduleListに入れる
		}finally {
			ss.close();//DBとの接続を切る
		}
		return scheduleList;
		//scheduleListのスケジュールを返還
	}
	
	private SqlSession getSession() {//mybatisと相互作用するためのメソッド
		String config = "mybatisConfig.xml";
		//mybatisConfig.xmlから設定を持ってきてconfigに入れる
		
		InputStream is = null;
		//設定を読むためのInputStreamを初期化
		//nullで初期化しないと 
		//SqlSessionFactory factory = builder.build(is);この行で
		//The local variable is may not have been initializedっていうエラーが発生する
		//Javaでローカル変数を使うとき、初期値を設定しなければならない
		
		
		try {//例外が発生する可能性がある
			is = Resources.getResourceAsStream(config);//configファイルの経路を読でisにいれる
		}catch(Exception e) {//全ての例外をeっていう変数に割り当てる
			e.printStackTrace();//例外を出力
		}
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		//MyBatisのSqlSessionFactoryBuilderを使ってSqlSessionFactoryを生成
		SqlSessionFactory factory = builder.build(is);
		//SqlSessionFactoryBuilderでisの住所から設定ファイルを呼び出してSqlSessionFactoryを生成
		SqlSession ss = factory.openSession();
		//SqlSessionFactoryのopenSession()でSqlSession を生成
		//SqlSessionデータベース演算をするためのインターフェース
		
		
		return ss;//ssを返還
}
	
	
}
