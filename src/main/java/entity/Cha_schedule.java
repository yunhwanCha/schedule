package entity;
//model 패키지에 속하는 클래스를 선언하기 위한 선언부

public class Cha_schedule {
	private int schedule_id;
	private String id;
	private String schedule_name;
	private String schedule_date;
	private String schedule_start;
	private String schedule_end;
	//정보 은닉과 캡슐화를 위해 private를 사용하여 외부에서 접근할 수 없는 변수 선언
	
	public int getSchedule_id() {
		return schedule_id;
	}//schedule_id의 값을 외부로 반환하는 메서드
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}//schedule_id의 값을 외부에서 설정하기 위한 메서드
	public String getId() {
		return id;
	}//id의 값을 외부로 반환하는 메서드
	public void setId(String id) {
		this.id = id;
	}//id의 값을 외부에서 설정하기 위한 메서드
	public String getSchedule_name() {
		return schedule_name;
	}//schedule_name의 값을 외부로 반환하는 메서드
	public void setSchedule_name(String schedule_name) {
		this.schedule_name = schedule_name;
	}//schedule_name의 값을 외부에서 설정하기 위한 메서드
	public String getSchedule_date() {
		return schedule_date;
	}//schedule_date의 값을 외부로 반환하는 메서드
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}//schedule_date의 값을 외부에서 설정하기 위한 메서드
	public String getSchedule_start() {
		return schedule_start;
	}//schedule_start의 값을 외부로 반환하는 메서드
	public void setSchedule_start(String schedule_start) {
		this.schedule_start = schedule_start;
	}//schedule_start의 값을 외부에서 설정하기 위한 메서드
	public String getSchedule_end() {
		return schedule_end;
	}//schedule_end의 값을 외부로 반환하는 메서드
	public void setSchedule_end(String schedule_end) {
		this.schedule_end = schedule_end;
	}//schedule_end의 값을 외부에서 설정하기 위한 메서드
	
	public class DateToday {//スケジュールクラスの内部クラスとして宣言される
		private String startOfWeek;
		private String endOfWeek;
		private String id;
		//정보 은닉과 캡슐화를 위해 private를 사용하여 외부에서 접근할 수 없는 변수 선언
		
		public String getStartOfWeek() {
			return startOfWeek;
		}//startOfWeekの値を外部に返還
		public void setStartOfWeek(String startOfWeek) {
			this.startOfWeek = startOfWeek;
		}//startOfWeekの値を外部から設定するためのメソッド
		public String getEndOfWeek() {
			return endOfWeek;
		}//EndOfWeekの値を外部に返還
		public void setEndOfWeek(String endOfWeek) {
			this.endOfWeek = endOfWeek;
		}//endOfWeekの値を外部から設定するためのメソッド
		public String getId() {
			return id;
		}//id의 값の値を外部に返還
		public void setId(String id) {
			this.id = id;
		}//idの値を外部から設定するためのメソッド
		
	}
	
	
	
}
