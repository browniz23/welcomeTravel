package passionfive.welcometravel.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ScheduleItemDB { //������ �׸��� ������ ��Ƶδ� db
	//�����ͺ��̽��� �ٷ�� ���ؼ� ���ؽ��� ��;���
		Context context;
		SQLiteDatabase db;
		
	public ScheduleItemDB(Context context){
		super();
		this.context = context;
		db = context.openOrCreateDatabase("schedule_item.db", context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists schedule_item(" +
				"_id integer primary key  autoincrement, " +
				"title text, year integer, month integer, day integer, week text, hour integer, minute integer, content text, " +
				"place_name text, address text, phone text, link text, mapx float, mapy float, bitmap text)");
	}
	
	//��� ������ ��ȸ�ϴ� �޼ҵ�
	public Cursor selectAllNote(){
		return db.rawQuery("select * from schedule_item order by year, month, day, hour, minute, _id", null);
	}
	
	//�׸� �߰��ϴ� �޼ҵ�
	public void insertNote(String title, int year, int month, int day, String week, int hour, int minute, String content, String place_name, String address, String phone, String link, double mapx, double mapy, String bitmap) {
		db.execSQL("insert into schedule_item(title, year, month, day, week, hour, minute, content, place_name, address, phone, link, mapx, mapy, bitmap)" +
				" values('"+title+"','"+year+"','"+month+"','"+day+"','"+week+"','"+hour+"','"+minute+"','"+content+"','"+place_name+"','"+address+"','"+phone+"','"+link+"','"+mapx+"','"+mapy+"','"+bitmap+"')");
	}
	
	//�׸� �����ϴ� �޼ҵ�
	public void updateNote(int _id, String title, int year, int month, int day, String week, int hour, int minute, String content, String place_name, String address, String phone, String link, double mapx, double mapy, String bitmap){
		db.execSQL("update schedule_item set title = '"+title+"', year = '"+year+"', month = '"+month+"', day = '"+day+"', week = '"+week+"', hour = '"+hour+"', minute = '"+minute+"', content = '"+content+"', " +
				"place_name = '"+place_name+"', address = '"+address+"', phone = '"+phone+"', link = '"+link+"', mapx = '"+mapx+"', mapy = '"+mapy+"', bitmap = '"+bitmap+"' where _id = "+_id);
	}
	
	//�׸� �����ϴ� �޼ҵ�
	public void deleteNote(int _id){
		db.execSQL("delete from schedule_item where _id = "+_id);
	}
	
	public void drop(){
		db.execSQL("drop table schedule_item");
	}


}
