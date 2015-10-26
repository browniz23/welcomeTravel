package passionfive.welcometravel.fragment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import passionfive.welcometravel.adapter.HistoryListAdapter;
import passionfive.welcometravel.data.ScheduleItemDB;
import passionfive.welcometravel.data.SearchDB;
import passionfive.welcometravel.util.Zeller;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class FragmentScheduleUpdate extends Fragment {
	private Button btn_sche_day, btn_sche_time, btn_sche_gallery,
			btn_sche_save, btn_sche_history;
	private TextView txt_sche_day, txt_sche_time;
	private EditText txt_sche_update_title, txt_sche_update_content,
			txt_sche_update_place_name, txt_sche_update_address,
			txt_sche_update_phone;
	private ImageButton ImageButton;
	private ImageView sche_imageview;
	private static String pictureSrc = null;
	private ListView lv;
	
	private static int _id;
	private static String title = "";
	private static String placeName = "";
	private static String address = "";
	private static String content = "";
	private static String phone = "";
	private static String link = "";
	private static double mapx = 0.0;
	private static double mapy = 0.0;
	private static int mYear;
	private static int mMonth;
	private static int mDay;
	private static int mHour;
	private static int mMinute;
	private static String week;
	private static String imageSrc;
	
	private String dialogPlaceName, dialogAddress, dialogPhone, dialogMapx, dialogMapy;
	
	static final int DATE_DIALOG_ID = 0;
	static final int TIME_DIALOG_ID = 1;
	private static final int PICK_FROM_CAMERA = 0;
	private static final int PICK_FROM_ALBUM = 1;
	private static final int CROP_FROM_CAMERA = 2;
	FragmentTransaction ft;
	Fragment newFragment;

	ArrayList<String> tems;
	private Uri pictureUrl;

	private Dialog mDialog;

	// 사진불러오기
	private static final String TEMP_PHOTO_FILE = "temp.jpg"; // 임시 저장파일
	private static final int REQ_CODE_PICK_IMAGE = 0;
	
	private static int backIndex = 3;

	public static FragmentScheduleUpdate newInstance() {
		FragmentScheduleUpdate fragment = new FragmentScheduleUpdate();
		backIndex = 3;
		return fragment;
	}

	public static FragmentScheduleUpdate newUpdateInstance(String strId, String strTitle, String strPlaceName, String strYear, String strMonth, String strDay,
			String strWeek, String strHour, String strMinute, String strAddress, String strContent, String strPhone, String strLink, String strMapx, String strMapy, String strImageSrc) {
		FragmentScheduleUpdate fragment = new FragmentScheduleUpdate();
		_id = Integer.parseInt(strId);
		title = strTitle;
		placeName= strPlaceName;
		mYear = Integer.parseInt(strYear);
		mMonth = Integer.parseInt(strMonth);
		mDay = Integer.parseInt(strDay);
		week = strWeek;
		mHour = Integer.parseInt(strHour);
		mMinute = Integer.parseInt(strMinute);
		address = strAddress;
		content = strContent;
		phone = strPhone;
		link = strLink;
		mapx = Double.parseDouble(strMapx);
		mapy = Double.parseDouble(strMapy);
		imageSrc = strImageSrc;
		backIndex = 4;//수정index
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_schedule_update, null);
		if(backIndex == 3){
			ActivityMain.backIndex = 3;
		} else if (backIndex == 4){
			ActivityMain.backIndex = 4;
		}
		
		
		init(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void init(View view) {
		txt_sche_update_title = (EditText) view
				.findViewById(R.id.txt_sche_update_title);
		txt_sche_update_content = (EditText) view
				.findViewById(R.id.txt_sche_update_content);
		txt_sche_update_place_name = (EditText) view
				.findViewById(R.id.txt_sche_update_place_name);
		txt_sche_update_address = (EditText) view
				.findViewById(R.id.txt_sche_update_address);
		txt_sche_update_phone = (EditText) view
				.findViewById(R.id.txt_sche_update_phone);
		sche_imageview = (ImageView) view.findViewById(R.id.sche_imageview);

		txt_sche_day = (TextView) view.findViewById(R.id.txt_sche_day);
		txt_sche_time = (TextView) view.findViewById(R.id.txt_sche_time);
			
		if(backIndex == 3){
			txt_sche_update_title.setText("");
			txt_sche_update_content.setText("");
			txt_sche_update_place_name.setText("");
			txt_sche_update_address.setText("");
			txt_sche_update_phone.setText("");
			pictureSrc="null";
			link="";
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			mHour = c.get(Calendar.HOUR_OF_DAY);
			mMinute = c.get(Calendar.MINUTE);
		} else if (backIndex == 4){
			txt_sche_update_title.setText(title);
			txt_sche_update_content.setText(content);
			txt_sche_update_place_name.setText(placeName);
			txt_sche_update_address.setText(address);
			txt_sche_update_phone.setText(phone);
			
			Uri pictureUrl = Uri.parse(imageSrc);
			Bitmap bitmap;
			try {
				bitmap = Images.Media.getBitmap(getActivity().getContentResolver(), pictureUrl);
				sche_imageview.setImageBitmap(bitmap);
				sche_imageview.setScaleType(ScaleType.CENTER_CROP);
			}catch (Exception e) {
				e.printStackTrace();
			}
			mMonth = mMonth -1;
		}
		
		updateDateDisplay();
		updateTimeDisplay();

		btn_sche_save = (Button) view.findViewById(R.id.btn_sche_save);
		btn_sche_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				title = txt_sche_update_title.getText().toString();
				content = txt_sche_update_content.getText().toString();
				placeName = txt_sche_update_place_name.getText().toString();
				address = txt_sche_update_address.getText().toString();
				phone = txt_sche_update_phone.getText().toString();				

				ScheduleItemDB scheduleItemDB = new ScheduleItemDB(
						getActivity());
				
				if(backIndex == 3){					
					scheduleItemDB.insertNote(title, mYear, mMonth + 1, mDay, week,
						mHour, mMinute, content, placeName, address, phone,
						link, mapx, mapy, pictureSrc);
				} else if (backIndex == 4){
					scheduleItemDB.updateNote(_id, title, mYear, mMonth + 1, mDay, week, mHour, mMinute, content, placeName, address, phone, link, mapx, mapy, pictureSrc);
				}

				ft = getActivity().getSupportFragmentManager()
						.beginTransaction();
				newFragment = FragmentSchedule.newInstance();

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});

		btn_sche_day = (Button) view.findViewById(R.id.btn_sche_day);
		btn_sche_day.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onCreateDialog(DATE_DIALOG_ID).show();
			}
		});

		btn_sche_time = (Button) view.findViewById(R.id.btn_sche_time);
		btn_sche_time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onCreateDialog(TIME_DIALOG_ID).show();
			}
		});
		//사진
		sche_imageview = (ImageView) view.findViewById(R.id.sche_imageview);
		sche_imageview.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createLognDialog().show();
			}
		});
		//사진
		btn_sche_gallery = (Button) view.findViewById(R.id.btn_sche_gallery);
		btn_sche_gallery.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createLognDialog().show();
			}
		});
		
		btn_sche_history = (Button) view.findViewById(R.id.btn_sche_history);
		btn_sche_history.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createHistoryDialog().show();
			}
		});
	}

	private void updateDateDisplay() {
		Zeller zeller = new Zeller();
		week = zeller.calculateWeek(mYear, mMonth + 1, mDay);
		txt_sche_day.setText(new StringBuilder().append(mYear).append(". ")
				.append(pad(mMonth + 1)).append(". ").append(pad(mDay))
				.append(" ").append(week).append("요일"));
	}

	private void updateTimeDisplay() {
		txt_sche_time.setText(new StringBuilder().append(pad(mHour))
				.append(" : ").append(pad(mMinute)));
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			updateDateDisplay();
		}
	};

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			mHour = hourOfDay;
			mMinute = minute;
			updateTimeDisplay();
		}
	};

	private Dialog createLognDialog() {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.schedule_long);
		// 촬영
		Button doTakePhotoAction = (Button) dialog
				.findViewById(R.id.btn_long_update2);
		doTakePhotoAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// addOrUpdate_index = 2;
				// createAccountBookDialog(addOrUpdate_index).show();
				doTakePhotoAction();
				dialog.dismiss();
			}
		});

		// 갤러리
		Button doTakeAlbumAction = (Button) dialog
				.findViewById(R.id.btn_long_delete2);
		doTakeAlbumAction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doTakeAlbumAction();
				dialog.dismiss();
			}
		});

		// 취소
		Button btnLongCancel = (Button) dialog
				.findViewById(R.id.btn_long_cancel2);
		btnLongCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();

			}
		});

		mDialog = dialog;
		return mDialog;
	}
	
	private String pad(int c) {
		if (c >= 10) {
			return String.valueOf(c);
		} else {
			return "0" + String.valueOf(c);
		}
	}
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(getActivity(), mDateSetListener, mYear,
					mMonth, mDay);
		case TIME_DIALOG_ID:
			return new TimePickerDialog(getActivity(), mTimeSetListener, mHour,
					mMinute, true);
		}
		return null;
	}

	private void doTakePhotoAction() {//사진

		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		// 임시로 사용할 파일의 경로를 생성
		String url = "tmp_" + String.valueOf(System.currentTimeMillis())
				+ ".jpg";
		pictureUrl = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));

		intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,pictureUrl);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PICK_FROM_CAMERA);
	}

	private void doTakeAlbumAction() {
		// 앨범 호출
		Intent intent = new Intent(Intent.ACTION_PICK);
		intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
		startActivityForResult(intent, PICK_FROM_ALBUM);
	}
	private String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		Cursor cursor = getActivity().managedQuery(uri, projection, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
	
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != getActivity().RESULT_OK) {
			return;
		}
		if(requestCode==1){
			//이미지 가져옴 
			pictureUrl = data.getData();
			Log.e("bitmap", pictureUrl.getPath());
			try {
				  
				//화면 뿌려주는 것
				Bitmap bitmap= Images.Media.getBitmap(getActivity().getContentResolver(), pictureUrl);
				sche_imageview.setImageBitmap(bitmap);
				sche_imageview.setScaleType(ScaleType.CENTER_CROP);
				 int resizeX = sche_imageview.getWidth();
				int resizeY = sche_imageview.getHeight();
				//이미지 압축
				String 	cropUrlFisrt = "android_tmp_croped_"
						+ String.valueOf(System.currentTimeMillis()) + ".jpg";			
				Bitmap bmp =  Images.Media.getBitmap(getActivity().getContentResolver(), pictureUrl);
			
				Bitmap rebmp = Bitmap.createScaledBitmap(bmp, resizeX, resizeY, true);//사이즈를 설정한 값으로 줄여주고...
			
				File f=new File (Environment.getExternalStorageDirectory(),	cropUrlFisrt);
				
				FileOutputStream fileStream = new FileOutputStream(f);
				rebmp.compress(Bitmap.CompressFormat.JPEG, 90,fileStream);//jpg로 파일 포멧을 하여 생성해줍니다. 
				//bmp.compress(CompressFormat.JPEG, 10, fileStream);
				fileStream.close();				
			
				
				//압출한 경로를 디비에 저장하기 위해서 pictureSrc에 저장둠
				Uri compressUrl= Uri.fromFile(f);				
				
				pictureSrc=String.valueOf(compressUrl);	
				bmp.recycle();
				rebmp.recycle();
//				Log.e("compressUrl ", compressUrl.getPath());
//				Log.e("compressUrl", pictureSrc);
				
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private Dialog createHistoryDialog() {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_schedule_history);
		lv = (ListView) dialog.findViewById(R.id.sche_histroy_list);
		SearchDB searchDB = new SearchDB(getActivity());
		Cursor cursor = searchDB.selectBookMarkNote();
		getActivity().startManagingCursor(cursor);
		HistoryListAdapter historyListAdapter = new HistoryListAdapter(getActivity(), cursor);
		lv.setAdapter(historyListAdapter);
		
		//즐겨찾기
		Button btn_dialog_bookmark = (Button) dialog.findViewById(R.id.btn_dialog_bookmark);
		btn_dialog_bookmark.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SearchDB searchDB = new SearchDB(getActivity());
				Cursor cursor = searchDB.selectBookMarkNote();
				getActivity().startManagingCursor(cursor);
				HistoryListAdapter historyListAdapter = new HistoryListAdapter(getActivity(), cursor);
				lv.setAdapter(historyListAdapter);
			}
		});
		
		//히스토리
		Button btn_dialog_history = (Button) dialog.findViewById(R.id.btn_dialog_history);
		btn_dialog_history.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				SearchDB searchDB = new SearchDB(getActivity());
				Cursor cursor = searchDB.selectHistoryNote();
				getActivity().startManagingCursor(cursor);
				HistoryListAdapter historyListAdapter = new HistoryListAdapter(getActivity(), cursor);
				lv.setAdapter(historyListAdapter);
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parnet, View view, int position,	long id) {
				TextView strPlaceName = (TextView) view.findViewById(R.id.searchTitle);
				TextView strAddress = (TextView) view.findViewById(R.id.searchAddress);
				TextView strPhone = (TextView) view.findViewById(R.id.searchTelephone);
				TextView strMapx = (TextView) view.findViewById(R.id.searchX);
				TextView strMapy = (TextView) view.findViewById(R.id.searchY);
				TextView strLink = (TextView) view.findViewById(R.id.searchLink);
				
				dialogPlaceName = strPlaceName.getText().toString();
				dialogAddress = strAddress.getText().toString();
				dialogPhone = strPhone.getText().toString();
				mapx = Double.parseDouble(strMapx.getText().toString());
				mapy = Double.parseDouble(strMapy.getText().toString());
				link = strLink.getText().toString();
			
				
				TextView dialog_history_placeName = (TextView) dialog.findViewById(R.id.dialog_history_placeName);
				dialog_history_placeName.setText(dialogPlaceName);
				TextView dialog_history_address = (TextView) dialog.findViewById(R.id.dialog_history_address);
				dialog_history_address.setText(dialogAddress);
			}
		});
		
		//확인
		Button btn_dialog_histroy_save = (Button) dialog.findViewById(R.id.btn_dialog_histroy_save);
		btn_dialog_histroy_save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				txt_sche_update_place_name.setText(dialogPlaceName);
				txt_sche_update_address.setText(dialogAddress);
				txt_sche_update_phone.setText(dialogPhone);
				dialog.dismiss();
			}
		});
		
		//취소
		Button btn_dialog_histroy_cancel = (Button) dialog.findViewById(R.id.btn_dialog_histroy_cancel);
		btn_dialog_histroy_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dialog.dismiss();
			}
		});
		
		mDialog = dialog;
		return mDialog;
	}
}