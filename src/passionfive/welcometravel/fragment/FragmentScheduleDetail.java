package passionfive.welcometravel.fragment;

import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import passionfive.welcometravel.adapter.HistoryListAdapter;
import passionfive.welcometravel.data.ScheduleItemDB;
import passionfive.welcometravel.data.SearchDB;
import android.app.Dialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class FragmentScheduleDetail extends Fragment {
	private FragmentTransaction ft;
	private Fragment newFragment;
	
	public static String strId, strTitle, strPlaceName, strYear, strMonth, strDay, strWeek, strHour, strMinute,
					strAddress, strContent, strPhone, strLink, strMapx, strMapy, strImageSrc;

	private TextView detail_txtTitle, detail_txtDate, detail_txtTime, detail_txtPlaceName, detail_txtAddress,
				detail_txtPhone, detail_txtLink, detail_txtContent;
	
	private ImageView detail_image;
	
	private Button btn_detail_update, btn_detail_trash, sche_detail_makeMarker;

	private int height;
	
	private Dialog mDialog;
		
	public static FragmentScheduleDetail newInstance(String _id, String title, String placeName, String year, String month, String day, String week, String hour, String minute, String address, String content, String phone, String link, String mapx, String mapy, String imageSrc) {
		FragmentScheduleDetail fragment = new FragmentScheduleDetail();
		strId = _id;
		strTitle = title;
		strPlaceName = placeName;
		strYear = year;
		strMonth = month;
		strDay = day;
		strWeek = week;
		strHour = hour;
		strMinute = minute;
		strAddress = address;
		strContent = content;
		strPhone = phone;
		strLink = link;
		strMapx = mapx;
		strMapy = mapy;
		strImageSrc = imageSrc;
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_schedule_detail, null);
		ActivityMain.backIndex = 2;
		
		init(view);
		return view;
	}

	
	private void init(View view) {
		detail_txtTitle = (TextView) view.findViewById(R.id.detail_txtTitle);
		detail_txtDate = (TextView) view.findViewById(R.id.detail_txtDate);
		detail_txtTime = (TextView) view.findViewById(R.id.detail_txtTime);
		detail_txtPlaceName = (TextView) view.findViewById(R.id.detail_txtPlaceName);
		detail_txtAddress = (TextView) view.findViewById(R.id.detail_txtAddress);
		detail_txtPhone = (TextView) view.findViewById(R.id.detail_txtPhone);
		detail_txtLink = (TextView) view.findViewById(R.id.detail_txtLink);
		detail_txtContent = (TextView) view.findViewById(R.id.detail_txtContent);
		detail_image = (ImageView) view.findViewById(R.id.detail_image);

		detail_txtTitle.setText(strTitle);
		detail_txtDate.setText(strYear+". "+pad(Integer.parseInt(strMonth))+". "+pad(Integer.parseInt(strDay))+" "+strWeek+"요일");
		detail_txtTime.setText(strHour+" : "+strMinute);
		detail_txtPlaceName.setText(strPlaceName);
		detail_txtAddress.setText(strAddress);
		detail_txtPhone.setText(strPhone);
		detail_txtLink.setText(strLink);
		detail_txtContent.setText(strContent);
		
		if(strImageSrc.equals("null")){
			detail_image.setVisibility(view.GONE);
		}else{
			Uri pictureUrl = Uri.parse(strImageSrc);
			
			Bitmap bitmap;
			
			try {
				bitmap = Images.Media.getBitmap(getActivity().getContentResolver(), pictureUrl);
				height = bitmap.getHeight();
				detail_image.setImageBitmap(bitmap);
				detail_image.setScaleType(ScaleType.CENTER_CROP);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		btn_detail_update = (Button) view.findViewById(R.id.btn_detail_update);
		btn_detail_update.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				replaceUpdateFragment();
			}
		});
		
		btn_detail_trash = (Button) view.findViewById(R.id.btn_detail_trash);
		btn_detail_trash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				createHistoryDialog().show();
			}
		});
		
		sche_detail_makeMarker = (Button) view.findViewById(R.id.sche_detail_makeMarker);
		sche_detail_makeMarker.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double mapx = Double.parseDouble(strMapx);
				double mapy = Double.parseDouble(strMapy);
				ft = getActivity().getSupportFragmentManager().beginTransaction();
				newFragment = FragmentNMap.newMakrMarkerInstance(mapx, mapy, strTitle);

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
		
		detail_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//이미지 눌렀을때 확대
				ft = getActivity().getSupportFragmentManager().beginTransaction();
				ActivityMain.touchragment = FragmentTouchImage.newInstance(strImageSrc);

				ft.add(R.id.layout_fragment, ActivityMain.touchragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
	}
	
	private String pad(int c) {
		if(c >= 10){
			return String.valueOf(c);
		}else{
			return "0" + String.valueOf(c);
		}
	}
	
	private void replaceUpdateFragment(){
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		newFragment = FragmentScheduleUpdate.newUpdateInstance(strId, strTitle, strPlaceName, strYear, strMonth, strDay,
				strWeek, strHour, strMinute, strAddress, strContent, strPhone, strLink, strMapx, strMapy, strImageSrc);

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		
		super.onActivityCreated(savedInstanceState);
	}
	
	private Dialog createHistoryDialog() {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_detail_detele_check);
		
		Button btn_dialog_detail_delete = (Button) dialog.findViewById(R.id.btn_dialog_detail_delete);
		btn_dialog_detail_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ScheduleItemDB db = new ScheduleItemDB(getActivity());
				db.deleteNote(Integer.parseInt(strId));
				dialog.dismiss();
				
				ft = getActivity().getSupportFragmentManager().beginTransaction();
				newFragment = FragmentSchedule.newInstance();

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
		
		Button btn_dialog_detail_cancel = (Button) dialog.findViewById(R.id.btn_dialog_detail_cancel);
		btn_dialog_detail_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		mDialog = dialog;
		return mDialog;
	}
}
