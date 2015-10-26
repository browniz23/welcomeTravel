package passionfive.welcometravel.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SearchDB {	//검색결과를 저장하기 위한 데이터베이스 클래스 
	Context context;
	SQLiteDatabase db;
	
	public SearchDB(Context context){
		super();
		this.context = context;
		db= context.openOrCreateDatabase("search.db", context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists searchdb(" + 
		" _id integer primary key autoincrement, " + 
		" sign text, title text, address text, telephone text," +
		"  description text, link text, mapx text, mapy text)");
	}
	
		public Cursor selectHistoryNote(){
			return db.rawQuery("select * from searchdb where sign = '2' order by _id desc", null);
		}
		
		public Cursor selectBookMarkNote(){
			return db.rawQuery("select * from searchdb where sign = '1' order by _Id desc", null);
		}
		
		public void insertHistoryNote(String sign, String title, String link, String description, String telephone, String address, double mapx, double mapy){
			String[] selectionArgs = {Double.toString(mapx),Double.toString(mapy)};
			Cursor cursor = db.query("searchdb", null	, "mapx=? and mapy=? and sign = '2'", selectionArgs, null, null, null);
			boolean checkHistory =cursor.moveToNext();
			if(checkHistory == false) {//존재하지 않아야 추가				
				ContentValues values= new ContentValues();
				values.put("sign", "2");
				values.put("title", title);
				values.put("link", link);
				values.put("description", description);
				values.put("telephone", telephone);
				values.put("address", address);
				values.put("mapx", Double.toString(mapx));
				values.put("mapy",  Double.toString(mapy));
				db.insert("searchdb", null, values);
			}
		}
		
			public void insertBookMarkNote(String sign, String title, String link, String description, String telephone, String address, double mapx, double mapy){
				String[] selectionArgs = {Double.toString(mapx), Double.toString(mapy)};
				Cursor cursor = db.query("searchdb", null	,  "sign='1' and mapx=? and mapy=?", selectionArgs, null, null, null);
				
				Log.e("tagdb", Double.toString(mapx));
				Log.e("tagdb", Double.toString(mapy));
				
				boolean checkBookMark =cursor.moveToNext(); 
				
				Log.e("tagdb", Boolean.toString(checkBookMark));
				
				if(checkBookMark == false) {//존재하지 않아야 추가
					ContentValues values= new ContentValues();
					values.put("sign", "1");
					values.put("title", title);
					values.put("link", link);
					values.put("description", description);
					values.put("telephone", telephone);
					values.put("address", address);
					values.put("mapx", Double.toString(mapx));
					values.put("mapy",  Double.toString(mapy));
					db.insert("searchdb", null, values);
				}		
			}
		
		public void deleteNote(int _id){
			db.execSQL("delete from searchdb where _id = " + _id );
		}
		
		public void updateNote(int _id, String sign, String title, String link, String description, String telephone, String address, double mapx, double mapy){
			db.execSQL("update searchdb set sign = '"+sign+"', '"+title+"', '"+link+"', '"+description+"', '"+telephone+"', '"+address+"', '"+mapx+"', '"+mapy+"' where _id = "+_id);
		}
		
		public void dropNote(){
			db.execSQL("drop table searchdb");
		}
		
		public void deleteAllHistory(){
			db.execSQL("delete from searchdb where sign = 2");
		}
		
		public void deleteAllBookMark(){
			db.execSQL("delete from searchdb where sign = 1");
		}
}