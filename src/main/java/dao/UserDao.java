package dao;
//データベースにアクセスするDAOパッケージ

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
//クラス外部のクラス、パッケージ、ライブラリを使うためにimportで宣言

public class UserDao {//Userに関するDBの処理をするメソッド
	//private,protectにするとサーブレットから呼び出せない
	private final String MAPPER_NAME = "mapper.userMapper.";
	//スケジュールマッパを常數で宣言
	
	public String getPw(String id) {//User DBでidで検索してそのpwの値をもらう
		SqlSession ss = getSession();//MyBatisのSqlSessionを生成してDBに連結
		String pw;//pw値を入れる変数宣言
		try {
			pw = ss.selectOne(MAPPER_NAME+"getPw",id);
			//idを入れてgetPwクエリを実行しれその結果をpwに入れる
		}finally {
			ss.close();//DBとの接続を切る
		}
		return pw;//pwの値をリターン
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
