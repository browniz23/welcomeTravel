package passionfive.welcometravel.adapter;

import passionfive.welcometravel.R;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class HistoryListAdapter extends CursorAdapter{
	public HistoryListAdapter(Context context, Cursor cursor){
		super(context, cursor);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final TextView searchId = (TextView) view.findViewById(R.id.searchId);
		final TextView searchSign = (TextView) view.findViewById(R.id.searchSign);
		final TextView searchTitle = (TextView) view.findViewById(R.id.searchTitle);
		final TextView searchAddress = (TextView) view.findViewById(R.id.searchAddress);
		final TextView searchTelephone = (TextView) view.findViewById(R.id.searchTelephone);
		final TextView searchDescription = (TextView) view.findViewById(R.id.searchDescription);
		final TextView searchLink = (TextView) view.findViewById(R.id.searchLink);
		final TextView searchX = (TextView) view.findViewById(R.id.searchX);
		final TextView searchY = (TextView) view.findViewById(R.id.searchY);
		
		searchId.setText(cursor.getString(cursor.getColumnIndex("_id"))); 
		searchSign.setText(cursor.getString(cursor.getColumnIndex("sign")));
		searchTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
		searchAddress.setText(cursor.getString(cursor.getColumnIndex("address")));
		searchTelephone.setText(cursor.getString(cursor.getColumnIndex("telephone")));
		searchDescription.setText(cursor.getString(cursor.getColumnIndex("description")));
		searchLink.setText(cursor.getString(cursor.getColumnIndex("link")));
		searchX.setText(cursor.getString(cursor.getColumnIndex("mapx")));
		searchY.setText(cursor.getString(cursor.getColumnIndex("mapy")));
		
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.history_list_item, parent, false);
		return v;
	}
}
