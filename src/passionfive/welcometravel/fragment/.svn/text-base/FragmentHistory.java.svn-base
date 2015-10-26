package passionfive.welcometravel.fragment;

import passionfive.welcometravel.R;
import passionfive.welcometravel.adapter.HistoryListAdapter;
import passionfive.welcometravel.data.SearchDB;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentHistory extends ListFragment {

	
	private ListView lv;
	private SearchDB searchDB;
	private Cursor cursor;
	private Button btn_bookmark, btn_history;
	private int index = 0;
	private String title, address, telephone, description;
	private TextView textTitle, textAddress, textTelephone, textDescription;
	private Dialog hDialog;
	
	
	public static FragmentHistory newInstance() {
		FragmentHistory fragment = new FragmentHistory();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_history_main, null);
		init(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	

	private void init(View view) {
		lv = (ListView) view.findViewById(android.R.id.list);
		btn_bookmark = (Button) view.findViewById(R.id.btn_bookmark);
		btn_history = (Button) view.findViewById(R.id.btn_history);
		searchDB = new SearchDB(getActivity());
		cursor = searchDB.selectHistoryNote();
		selectBookMarkDB();
		
		lv.setOnItemLongClickListener(new OnItemLongClickListener(){
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				TextView history_item_id = (TextView) view.findViewById(R.id.searchId);
				int _id = Integer.parseInt(history_item_id.getText().toString());
				createLongDialog(_id).show();
				return true;
			}
		});
		
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		btnEvent();
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		TextView titleText = (TextView)v.findViewById(R.id.searchTitle);
		title = titleText.getText().toString();
		
		TextView addressText = (TextView)v.findViewById(R.id.searchAddress);
		address = addressText.getText().toString();
		
		TextView telephoneText = (TextView)v.findViewById(R.id.searchTelephone);
		telephone = telephoneText.getText().toString();
		
		TextView descriptionText = (TextView)v.findViewById(R.id.searchDescription);
		description = descriptionText.getText().toString();
		
		createHistoryDialog().show();
		
	}
	
	private Dialog createHistoryDialog() {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_history_list);
		textTitle = (TextView)dialog.findViewById(R.id.textTitle);
		textAddress = (TextView)dialog.findViewById(R.id.textAddress);
		textTelephone = (TextView)dialog.findViewById(R.id.textPhone);
		textDescription = (TextView)dialog.findViewById(R.id.textDescription);
		textTitle.setText(title);
		textAddress.setText(address);
		textTelephone.setText(telephone);
		textDescription.setText(description);
		
		Button hisCheck = (Button) dialog.findViewById(R.id.history_check);
		hisCheck.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});
		
		hDialog = dialog;
		return hDialog;
	}

	//히스토리 다이어로그 (삭제, 취소)
	private Dialog createLongDialog(final int _id) {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_history_long);
		
		//전체삭제
		Button btn_history_allDelete = (Button) dialog.findViewById(R.id.btn_history_allDelete);
		btn_history_allDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(index == 0){
					searchDB.deleteAllHistory();
					selectHistoryDB();
					dialog.dismiss();
				} else if (index == 1){
					searchDB.deleteAllBookMark();
					selectBookMarkDB();
					dialog.dismiss();
				}
			}
		});
		
		//삭제
		Button btnHistoryDelete = (Button)dialog.findViewById(R.id.btn_history_delete);
		btnHistoryDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				searchDB.deleteNote(_id);
				dialog.dismiss();
				if(index == 0){
					selectHistoryDB();
				} else if (index == 1){
					selectBookMarkDB();
				}
			}
		});
		
		Button btnHistoryCancel = (Button)dialog.findViewById(R.id.btn_history_cancel);
		btnHistoryCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		return dialog;
	}
		

	private void selectHistoryDB(){
		cursor = searchDB.selectHistoryNote();
		//if(cursor.getCount() > 0){
			getActivity().startManagingCursor(cursor);
			HistoryListAdapter historyListAdapter = new HistoryListAdapter(getActivity(), cursor);
			lv.setAdapter(historyListAdapter);			
		
	}

	
	private void selectBookMarkDB(){
		cursor = searchDB.selectBookMarkNote();
		getActivity().startManagingCursor(cursor);
		HistoryListAdapter historyListAdapter = new HistoryListAdapter(getActivity(), cursor);
		lv.setAdapter(historyListAdapter);
	}
	
		
		
		private void btnEvent() {
			btn_history.setOnClickListener(mClick);
			btn_bookmark.setOnClickListener(mClick);
	}
		
		private OnClickListener mClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.btn_history:
					cursor = searchDB.selectHistoryNote();
					selectHistoryDB();
					index = 0;
					break;
				
				case R.id.btn_bookmark:
					cursor = searchDB.selectBookMarkNote();
					selectBookMarkDB();
					index = 1;
					break;
				}
			}
		};
		
		
		
}
