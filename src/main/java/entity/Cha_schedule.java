package entity;
//entityパッケージ
//entityはテーブルのカラムと1対1に対応するように作る

public class Cha_schedule {
	private int schedule_id;
	private String id;
	private String schedule_name;
	private String schedule_date;
	private String schedule_start;
	private String schedule_end;
	//カプセル化のためにprivateを使って外部からアクセスできない変数を宣言
	
	public int getSchedule_id() {
		return schedule_id;
	}//schedule_idの値を外部に返還
	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}//schedule_idの値を外部から設定するためのメソッド
	public String getId() {
		return id;
	}//idの値を外部に返還
	public void setId(String id) {
		this.id = id;
	}//idの値を外部から設定するためのメソッド
	public String getSchedule_name() {
		return schedule_name;
	}//schedule_nameの値を外部に返還
	public void setSchedule_name(String schedule_name) {
		this.schedule_name = schedule_name;
	}//schedule_nameの値を外部から設定するためのメソッド
	public String getSchedule_date() {
		return schedule_date;
	}//schedule_dateの値を外部に返還
	public void setSchedule_date(String schedule_date) {
		this.schedule_date = schedule_date;
	}//schedule_dateの値を外部から設定するためのメソッド
	public String getSchedule_start() {
		return schedule_start;
	}//schedule_startの値を外部に返還
	public void setSchedule_start(String schedule_start) {
		this.schedule_start = schedule_start;
	}//schedule_startの値を外部から設定するためのメソッド
	public String getSchedule_end() {
		return schedule_end;
	}//schedule_endの値を外部に返還
	public void setSchedule_end(String schedule_end) {
		this.schedule_end = schedule_end;
	}//schedule_endの値を外部から設定するためのメソッド
	
	public class DateToday {//スケジュールクラスの内部クラスとして宣言される
		private String startOfWeek;
		private String endOfWeek;
		private String id;
		//カプセル化のためにprivateを使って外部からアクセスできない変数を宣言
		
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
