package entity;
//model 패키지에 속하는 클래스를 선언하기 위한 선언부
public class Cha_user {
private String id;
private String pw;

//정보 은닉과 캡슐화를 위해 private를 사용하여 외부에서 접근할 수 없는 변수 선언

public String getId() {
	return id;
}//id의 값을 외부로 반환하는 메서드
public void setId(String id) {
	this.id = id;
}//id의 값을 외부에서 설정하기 위한 메서드
public String getPw() {
	return pw;
}//pw의 값을 외부로 반환하는 메서드
public void setPw(String pw) {
	this.pw = pw;
}//pw의 값을 외부에서 설정하기 위한 메서드


}
