package passionfive.welcometravel.fragment;

import java.util.Calendar;

import passionfive.welcometravel.R;
import passionfive.welcometravel.data.MoneyDB;
import passionfive.welcometravel.util.Zeller;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentAccountBookList extends ListFragment {
	private Button insertButton, beforButton, nextButton, allButton;
	private TextView _idText, signText, contentText, moneyText, plusSumMoney, minusSumMoney, 
			 allSumMoney, yearSelectText, monthSelectText, monthViewText, allViewText, 
			 yearText, monthText, dayText;
	private EditText editContent, editMoney;
	private RadioGroup rg;
	private RadioButton rbMinus;
	private ListView lv;
	private MoneyDB moneyDB;
	SimpleCursorAdapter adapter;
	
	//현재 날짜 등 관리 필드
	static int settingYear;
	static int settingMonth;
	static int settingDay;
	
	//다이얼로그 용 날짜 관리 필드
	int dialogYear;
	int dialogMonth;
	int dialogDay;
	boolean dayindex = false;
	
	private static int fragment_index = 1;
	private int addOrUpdate_index = 1;
	
	private Dialog mDialog;
	
	//데이터 주고받는 필드
	private int _id;
	private String sign;
	private int year, month, day;
	private String content;
	private int money;
	
	public static FragmentAccountBookList newInstance(int index) {
		FragmentAccountBookList fragment = new FragmentAccountBookList();
		fragment_index = index;
		return fragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_accountbook_list, null);
		
		init(view);
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void init(View view) {
		moneyDB = new MoneyDB(getActivity());
		lv = (ListView) view.findViewById(android.R.id.list);
		plusSumMoney = (TextView) view.findViewById(R.id.plusSumMoney);
		minusSumMoney = (TextView) view.findViewById(R.id.minusSumMoney);
		allSumMoney = (TextView) view.findViewById(R.id.allSumMoney);
		insertButton = (Button) view.findViewById(R.id.insertButton);
		beforButton = (Button) view.findViewById(R.id.beforButton);
		nextButton = (Button) view.findViewById(R.id.nextButton);
		yearSelectText = (TextView) view.findViewById(R.id.yearSelectText);
		monthSelectText = (TextView) view.findViewById(R.id.monthSelectText);
		allButton = (Button) view.findViewById(R.id.allButton);
		monthViewText = (TextView) view.findViewById(R.id.monthViewText);
		allViewText = (TextView) view.findViewById(R.id.allViewText);
		allButton.setText("전체보기");
		
		
      //현재 날짜 가져오기(어플 실행후 한번만 실행하기 위해서 if문)
		if(Zeller.one ==1){
			Calendar now = Calendar.getInstance();
			settingYear = now.get(Calendar.YEAR);
			settingMonth = now.get(Calendar.MONTH)+1;
			settingDay = now.get(Calendar.DAY_OF_MONTH);
			Zeller.one = 2;
		}
		
		//리스트뷰 롱클릭 이벤트
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View v,
					int arg2, long arg3) {
				TextView _idText = (TextView) v.findViewById(R.id._idText);
				TextView signText = (TextView) v.findViewById(R.id.signText);
				TextView yearText = (TextView) v.findViewById(R.id.yearText);
				TextView monthText = (TextView) v.findViewById(R.id.monthText);
				TextView dayText = (TextView) v.findViewById(R.id.dayText);
				TextView contentText = (TextView) v.findViewById(R.id.contentText);
				TextView moneyText = (TextView) v.findViewById(R.id.moneyText);
				
				_id = Integer.parseInt(_idText.getText().toString());
				sign = signText.getText().toString();
				year = Integer.parseInt(yearText.getText().toString());
				month = Integer.parseInt(monthText.getText().toString());
				day = Integer.parseInt(dayText.getText().toString());
				content = contentText.getText().toString();
				money = Integer.parseInt(moneyText.getText().toString());
				
				createLognDialog(_id).show();
				return true;
			}
		});
		
	}
	
	@Override
	public void onStart() {
		super.onStart();
		action();
		//월 선택
		beforButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				settingMonth -= 1;
				if(settingMonth == 0){
					settingYear -= 1;
					settingMonth = 12;
					yearSelectText.setText(String.valueOf(settingYear)+".");
				}
				if(settingMonth >= 10){
					monthSelectText.setText(String.valueOf(settingMonth));
				} else {
					monthSelectText.setText("0"+String.valueOf(settingMonth));
				}
				action();
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				settingMonth += 1;
				if(settingMonth == 13){
					settingYear += 1;
					settingMonth = 1;
					yearSelectText.setText(String.valueOf(settingYear)+".");
				}
				if(settingMonth >= 10){
					monthSelectText.setText(String.valueOf(settingMonth));
				} else {
					monthSelectText.setText("0"+String.valueOf(settingMonth));
				}
				action();
			}
		});
		
		//추가하기 버튼
		insertButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				PlusWriteActivity.value = "저장하기";
//				Intent intent  = new Intent(AllLayout.this, WriteTab.class);
//				intent.putExtra("_id", 0);
//				intent.putExtra("sign", "+");
//				intent.putExtra("year", year);
//				intent.putExtra("month", month);
//				intent.putExtra("day", day);
//				intent.putExtra("content", "insert");
//				intent.putExtra("money","0");
//				startActivity(intent);
				addOrUpdate_index = 1;
				createAccountBookDialog(addOrUpdate_index).show();

			}
		});
		
		//전체보기 버튼
		allButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String str = allButton.getText().toString();
				if(str == "전체보기"){
					allButton.setText("월별보기");
					allView();
				} else if (str == "월별보기"){
					allButton.setText("전체보기");
					action();
				}
				
			}

		});
	}
		//전체보기 메소드
		private void allView() {
			Cursor cursor = null;
			switch (fragment_index) {
			case 1:
				//전체
				cursor = moneyDB.selectAllNote();
				break;
				
			case 2:
				//수입전체
				cursor = moneyDB.selectAllPlusNote();
				break;
		
			case 3:
				//지출전체
				cursor = moneyDB.selectAllMinusNote();
				break;
				
			default:
				break;
			}
			String []from = {"_id","sign","year","month","day","week","content","money"};
			int []to = {R.id._idText, R.id.signText, R.id.yearText, R.id.monthText, R.id.dayText, R.id.weekText, R.id.contentText, R.id.moneyText};
			adapter = new SimpleCursorAdapter(getActivity(), R.layout.all_layout_item, cursor, from, to);
			setListAdapter(adapter);	
			
			//수입총액
			String plusSum = "0";
			Cursor sumCursor = moneyDB.selectPlusSumNote();
			if(sumCursor.moveToFirst()){
				plusSum = sumCursor.getString(0);
			}
			if(plusSum == null)
				plusSum = "0";
			plusSumMoney.setText(plusSum+" 원");
			
			//지출총액
			String minusSum = "0";
			sumCursor = moneyDB.selectMinusSumNote();
			if(sumCursor.moveToFirst()){
				minusSum = sumCursor.getString(0);
			}
			if(minusSum == null)
				minusSum = "0";
			minusSumMoney.setText(minusSum+" 원");
			
			//잔액
			int allSum = 0;
			if (plusSum != null){
				if(minusSum != null){
					allSum = Integer.parseInt(plusSum)-Integer.parseInt(minusSum);
				}
			}
			allSumMoney.setText(String.valueOf(allSum)+" 원");
			
			//수입총액,지출총액,잔액 옆 전체라고 출력
			monthViewText.setText("전체");
			monthViewText.setTextSize(23);
			allViewText.setVisibility(allViewText.GONE);
			
			//날짜 선택하는 부분 숨기기
			beforButton.setVisibility(beforButton.INVISIBLE);
			nextButton.setVisibility(nextButton.INVISIBLE);
			yearSelectText.setVisibility(yearSelectText.INVISIBLE);
			monthSelectText.setVisibility(monthSelectText.INVISIBLE);
		}
	
	private void action(){
		Cursor cursor = null;
		switch (fragment_index) {
		case 1:
			//월별
			cursor = moneyDB.selectMonthNote(settingYear, settingMonth);
			break;
		case 2:
			//수입
			cursor = moneyDB.selectPlusNote(settingYear, settingMonth);
			break;
		case 3:
			//지출
			cursor = moneyDB.selectMinusNote(settingYear, settingMonth);
			break;

		default:
			break;
		}
		String []from = {"_id","sign","year","month","day","week","content","money"};
		int []to = {R.id._idText, R.id.signText, R.id.yearText, R.id.monthText, R.id.dayText, R.id.weekText, R.id.contentText, R.id.moneyText};
		adapter = new SimpleCursorAdapter(getActivity(), R.layout.all_layout_item, cursor, from, to);
		Log.e("dd", adapter.toString());
		setListAdapter(adapter);		
	
		//수입총액
		String plusSum = "0";
		Cursor sumCursor = moneyDB.selectMonthPlusSumNote(settingYear, settingMonth);
		if(sumCursor.moveToFirst()){
			plusSum = sumCursor.getString(0);
		}
		if(plusSum == null)
			plusSum = "0";
		plusSumMoney.setText(plusSum+" 원");
		
		//지출총액
		String minusSum = "0";
		sumCursor = moneyDB.selectMonthMinusSumNote(settingYear, settingMonth);
		if(sumCursor.moveToFirst()){
			minusSum = sumCursor.getString(0);
		}
		if(minusSum == null)
			minusSum = "0";
		minusSumMoney.setText(minusSum+" 원");
		
		//잔액
		int allSum = 0;
		if (plusSum != null){
			if(minusSum != null){
				allSum = Integer.parseInt(plusSum)-Integer.parseInt(minusSum);
			}
		}
		allSumMoney.setText(String.valueOf(allSum)+" 원");
		
		//수입총액,지출총액,잔액 옆 월 출력
		monthViewText.setText(String.valueOf(settingMonth));
		monthViewText.setTextSize(40);
		allViewText.setVisibility(allViewText.VISIBLE);
		
		//날짜 선택하는 부분 숨기기
		beforButton.setVisibility(beforButton.VISIBLE);
		nextButton.setVisibility(nextButton.VISIBLE);
		yearSelectText.setVisibility(yearSelectText.VISIBLE);
		monthSelectText.setVisibility(monthSelectText.VISIBLE);
		
		//년 월 출력
		if(settingMonth >= 10){
			monthSelectText.setText(String.valueOf(settingMonth));
		} else {
			monthSelectText.setText("0"+String.valueOf(settingMonth));
		}
		yearSelectText.setText(String.valueOf(settingYear)+".");
		
	}
	
	//리스트 한번 클릭으로 수정하기
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		TextView _idText = (TextView) v.findViewById(R.id._idText);
		_id = Integer.parseInt(_idText.getText().toString());
		
		TextView signText = (TextView) v.findViewById(R.id.signText);
		sign = signText.getText().toString();
		
		TextView yearText = (TextView) v.findViewById(R.id.yearText);
		year = Integer.parseInt(yearText.getText().toString());
		
		TextView monthText = (TextView) v.findViewById(R.id.monthText);
		month = Integer.parseInt(monthText.getText().toString());
		
		TextView dayText = (TextView) v.findViewById(R.id.dayText);
		day = Integer.parseInt(dayText.getText().toString());
		
		TextView contentText = (TextView) v.findViewById(R.id.contentText);
		content = contentText.getText().toString();
		
		TextView moneyText = (TextView) v.findViewById(R.id.moneyText);
		money = Integer.parseInt(moneyText.getText().toString());
		
		addOrUpdate_index = 2; //수정 다이얼로그
		createAccountBookDialog(addOrUpdate_index).show();
	}
	
	

	//추가또는 수정 다이얼로그
	private Dialog createAccountBookDialog(final int index) {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_accountbook_add);
		
		editContent = (EditText) dialog.findViewById(R.id.editContent);
		editMoney = (EditText) dialog.findViewById(R.id.editMoney);
		yearText = (TextView) dialog.findViewById(R.id.addyearText);
		monthText = (TextView) dialog.findViewById(R.id.addmonthText);
		dayText = (TextView) dialog.findViewById(R.id.adddayText);
		rbMinus = (RadioButton) dialog.findViewById(R.id.radioMinus);
		Button btnSave = (Button) dialog.findViewById(R.id.btn_save);
		Button btnCancel = (Button) dialog.findViewById(R.id.btn_cancel);
		
		Calendar now = Calendar.getInstance();
		dialogYear = now.get(Calendar.YEAR);
		dialogMonth = now.get(Calendar.MONTH)+1;
		dialogDay = now.get(Calendar.DAY_OF_MONTH);
		
		switch (index) {
		case 1://추가일경우
			yearText.setText(String.valueOf(dialogYear));
			monthText.setText(String.valueOf(dialogMonth));
			dayText.setText(String.valueOf(dialogDay));
			sign = "수입";
			break;

		case 2://수정일경우
			yearText.setText(String.valueOf(year));
			monthText.setText(String.valueOf(month));
			dayText.setText(String.valueOf(day));
			editContent.setText(content);
			editMoney.setText(String.valueOf(money));
			btnSave.setText("수정");
			dayindex = true;
			if(sign.equals("+")){
				sign = "수입";
			}else if(sign.equals("-")){
				sign = "지출";
				rbMinus.setChecked(true);
			}
			break;
		}
		
		
		//datepicker dialog
		final DatePickerDialog.OnDateSetListener dateListener = 
		new DatePickerDialog.OnDateSetListener() {
											
			@Override
			public void onDateSet(DatePicker view, int yearof, int monthOfYear,
					int dayOfMonth) {
				dialogYear = yearof;
				dialogMonth = monthOfYear+1;
				dialogDay = dayOfMonth;
				yearText.setText(String.valueOf(dialogYear));
				monthText.setText(String.valueOf(dialogMonth));
				dayText.setText(String.valueOf(dialogDay));
				dayindex = false;
			}
		};
		
		//데이터피커 다이얼로그 뛰우기
		Button btnDay = (Button) dialog.findViewById(R.id.btn_day);
		btnDay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(index == 1){
					new DatePickerDialog(getActivity(), dateListener, dialogYear, dialogMonth-1, dialogDay).show();
				} else {
					if(dayindex){
						new DatePickerDialog(getActivity(), dateListener, year, month-1, day).show();
					}else{
						new DatePickerDialog(getActivity(), dateListener, dialogYear, dialogMonth-1, dialogDay).show();
					}
				}
				
			}
		});
		
		rg = (RadioGroup) dialog.findViewById(R.id.rgAdd);
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				sign = ((RadioButton) dialog.findViewById(checkedId)).getText().toString();
			}
		});
		
		//추가 또는 수정 버튼
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (index) {
				case 1: //추가일경우 추가
					content = editContent.getText().toString();
					money = Integer.parseInt(editMoney.getText().toString());
					Zeller zeller1 = new Zeller();
					String week1 = zeller1.calculateWeek(dialogYear, dialogMonth, dialogDay);
					if(sign.equals("수입")){
						sign = "+";
					}else{
						sign = "-";
					}
					moneyDB.insertNote(sign, dialogYear, dialogMonth, dialogDay, week1, content, money);
					Toast.makeText(getActivity(), "추가되었습니다.", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
					action();
					break;

				case 2: //수정일경우 수정
					year = Integer.parseInt(yearText.getText().toString());
					month = Integer.parseInt(monthText.getText().toString());
					day = Integer.parseInt(dayText.getText().toString());
					content = editContent.getText().toString();
					money = Integer.parseInt(editMoney.getText().toString());
					Zeller zeller2 = new Zeller();
					String week2 = zeller2.calculateWeek(dialogYear, dialogMonth, dialogDay);
					if(sign.equals("수입")){
						sign = "+";
					}else{
						sign = "-";
					}
					moneyDB.updateNote(_id, sign, year, month, day, week2, content, money);
					Toast.makeText(getActivity(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
					dialog.dismiss();
					action();
					break;
				}
				
			}
		});

		//취소버튼
		btnCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		mDialog = dialog;
		return mDialog;
	}
	
	//리스트뷰 롱클릭 다이얼로그
	private Dialog createLognDialog(final int _id) {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_accountbook_long);
		//수정
		Button btnLongUpdate = (Button) dialog.findViewById(R.id.btn_long_update);
		btnLongUpdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addOrUpdate_index = 2;
				createAccountBookDialog(addOrUpdate_index).show();
				dialog.dismiss();
			}
		});
		
		//삭제
		Button btnLongDelete = (Button) dialog.findViewById(R.id.btn_long_delete);
		btnLongDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				moneyDB.deleteNote(_id);
				dialog.dismiss();
				action();
			}
		});
		
		//취소
		Button btnLongCancel = (Button) dialog.findViewById(R.id.btn_long_cancel);
		btnLongCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		mDialog = dialog;
		return mDialog;
	}
	
}
