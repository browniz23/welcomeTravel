package passionfive.welcometravel.adapter;

import java.util.ArrayList;

import passionfive.welcometravel.R;
import passionfive.welcometravel.application.ApplicationSample;
import passionfive.welcometravel.data.SearchDB;
import passionfive.welcometravel.data.SearchData;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SearchListAdapter extends BaseAdapter {
	private Context context;
	 private ArrayList<SearchData> arrayList;
	 private LayoutInflater inflater;
	 
	 public SearchListAdapter(Context c, ArrayList<SearchData> arrayList) {
	  this.context = c;
	  this.arrayList = arrayList;
	  inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	  
	 }
	 
	 public int getCount() {
		Log.e("tag", ""+arrayList.size());
	  return arrayList.size();
	 }
	 public String getItem(int position) {
	  return arrayList.get(position).getTitle();
	 }
	 public long getItemId(int position) {
	  return position;
	 }
	 
	 public View getView(final int position, View convertView, ViewGroup parent) {
	  if(convertView == null){
	   convertView = inflater.inflate(R.layout.nmap_search_list_item, parent, false);
	  }
	  
	  final TextView searchItemTitle = (TextView)convertView.findViewById(R.id.searchItemTitle);
	  searchItemTitle.setText(arrayList.get(position).getTitle());
	  
	  final TextView searchItemLink = (TextView)convertView.findViewById(R.id.searchLink);
	  searchItemLink.setText(arrayList.get(position).getLink());
	  
	  final TextView searchItemDescription = (TextView)convertView.findViewById(R.id.searchDescription);
	  searchItemDescription.setText(arrayList.get(position).getDescription());
	  
	  final TextView searchItemTelephone = (TextView)convertView.findViewById(R.id.searchTelephone);
	  searchItemTelephone.setText(arrayList.get(position).getTelephone());
	  
	  final TextView searchItemAddress = (TextView)convertView.findViewById(R.id.searchItemAddress);
	  searchItemAddress.setText(arrayList.get(position).getAddress());
	  
	  final TextView searchItemX = (TextView)convertView.findViewById(R.id.searchItemX);
	  searchItemX.setText(String.valueOf(arrayList.get(position).getMapx()));
	  
	  final TextView searchItemY = (TextView)convertView.findViewById(R.id.searchItemY);
	  searchItemY.setText(String.valueOf(arrayList.get(position).getMapy()));
	  
	  Button btnLikeAdd = (Button)convertView.findViewById(R.id.btnLikeAdd);
	  btnLikeAdd.setFocusable(false);
	  btnLikeAdd.setOnClickListener(new OnClickListener() {   
	   
		  public void onClick(View v) {
			  SearchDB searchdb = new SearchDB(context);
			  String sign = "1";
			  String strtitle = searchItemTitle.getText().toString();
			  String strAddress = searchItemAddress.getText().toString();
			  String strLink = searchItemLink.getText().toString();
			  String strDescription = searchItemDescription.getText().toString();
			  String strTelephone = searchItemTelephone.getText().toString();
			  double strMapX = arrayList.get(position).getMapx();
			  double strMapY = arrayList.get(position).getMapy();
			  searchdb.insertBookMarkNote(sign, strtitle, strLink, strDescription, strTelephone, strAddress, strMapX, strMapY);
			  Toast.makeText(context, "즐겨찾기에 추가 되었습니다.", Toast.LENGTH_SHORT).show();
		  }
	  });

	  
	  return convertView;
	 }
}
