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
