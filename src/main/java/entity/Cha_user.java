package entity;
//entityパッケージ
//entityはテーブルのカラムと1対1に対応するように作る

public class Cha_user {
private String id;
private String pw;
private String name;

//カプセル化のためにprivateを使って外部からアクセスできない変数を宣言

public String getId() {
	return id;
}//idの値を外部に返還
public void setId(String id) {
	this.id = id;
}//idの値を外部から設定するためのメソッド
public String getPw() {
	return pw;
}//pwの値を外部に返還
public void setPw(String pw) {
	this.pw = pw;
}//pwの値を外部から設定するためのメソッド
public String getName() {
	return name;
}//nameの値を外部に返還
public void setName(String name) {
	this.name = name;
}//nameの値を外部から設定するためのメソッド


}
