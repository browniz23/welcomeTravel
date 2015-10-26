package passionfive.welcometravel.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MoneyDB {	//가게부 데이터베이스 다루는 클래스
	//데이터베이스를 다루기 위해서 컨텍스를 얻와야함
	Context context;
	SQLiteDatabase db;
	
	public MoneyDB(Context context){
		super();
		this.context = context;
		db = context.openOrCreateDatabase("money.db", context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists moneydb(" +
				"_id integer primary key  autoincrement, " +
				"sign text, year integer, month integer, day integer, week text, content text, " +
				"money integer)");
	}
	
		//모든 데이터 조회하는 메소드
		public Cursor selectAllNote(){
			return db.rawQuery("select * from moneydb order by year desc, month desc, day desc, _id desc", null);
		}
		
		//월별로 데이터 조회하는 메소드
		public Cursor selectMonthNote(int year, int month){
			return db.rawQuery("select * from moneydb where year = '"+year+"' and month = '"+month+"' order by year desc, month desc, day desc, _id desc", null);
		}
		
		//월별 수입 항목만 조회하는 메소드
		public Cursor selectPlusNote(int year, int month){
			return db.rawQuery("select * from moneydb where sign = '+' and year = '"+year+"' and month = '"+month+"' order by year desc, month desc, day desc, _id desc", null);
		}
		
		//월별 지출 항목만 조회하는 메소드
		public Cursor selectMinusNote(int year, int month){
			return db.rawQuery("select * from moneydb where sign = '-' and year = '"+year+"' and month = '"+month+"' order by year desc, month desc, day desc, _id desc", null);
		}
		
		//모든 수입 항목만 조회하는 메소드
		public Cursor selectAllPlusNote(){
			return db.rawQuery("select * from moneydb where sign = '+' order by year desc, month desc, day desc, _id desc", null);
		}
		
		//모든 지출 항목만 조회하는 메소드
		public Cursor selectAllMinusNote(){
			return db.rawQuery("select * from moneydb where sign = '-' order by year desc, month desc, day desc, _id desc", null);
		}
		
		//항목 추가하는 메소드
		public void insertNote(String sign, int year, int month, int day, String week, String content, int money) {
			db.execSQL("insert into moneydb(sign, year, month, day, week, content, money) values('"+sign+"','"+year+"','"+month+"','"+day+"','"+week+"','"+content+"','"+money+"')");
		}
		
		//항목 수정하는 메소드
		public void updateNote(int _id, String sign, int year, int month, int day, String week, String content, int money){
			db.execSQL("update moneydb set sign = '"+sign+"', year = '"+year+"', month = '"+month+"', day = '"+day+"', week = '"+week+"', content = '"+content+"', money = "+money+" where _id = "+_id);
		}
		
		//항목 삭제하는 메소드
		public void deleteNote(int _id){
			db.execSQL("delete from moneydb where _id = "+_id);
		}
		
		//수입 총액 조회하는 메소드
		public Cursor selectPlusSumNote(){
			return db.rawQuery("select sum(money) from moneydb where sign = '+'",null);
		}
		
		//지출 총액 조회하는 메소드
		public Cursor selectMinusSumNote(){
			return db.rawQuery("select sum(money) from moneydb where sign = '-'",null);
		}
		
		//월별 수입 총액 조회하는 메소드
		public Cursor selectMonthPlusSumNote(int year, int month){
			return db.rawQuery("select sum(money) from moneydb where sign = '+' and year = '"+year+"' and month = '"+month+"'",null);
		}
		
		//월별 지출 총액 조회하는 메소드
		public Cursor selectMonthMinusSumNote(int year, int month){
			return db.rawQuery("select sum(money) from moneydb where sign = '-' and year = '"+year+"' and month = '"+month+"'",null);
		}

}
