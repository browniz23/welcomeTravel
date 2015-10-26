package passionfive.welcometravel.fragment;

import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import passionfive.welcometravel.adapter.ScheduleDetailDBAdapter;
import passionfive.welcometravel.data.ScheduleItemDB;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentSchedule extends Fragment {
	private FragmentTransaction ft;
	private Fragment newFragment;

	private Button btn_sche_item_add;
	private Button btn_sche_trash;
	

	private ListView sche_detail_list;
	private ScheduleItemDB scheduleItemDB;
	private Cursor cursor;

	private Dialog mDialog;

	public static FragmentSchedule newInstance() {
		FragmentSchedule fragment = new FragmentSchedule();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_schedule, null);
		init(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void init(View view) {
		scheduleItemDB = new ScheduleItemDB(getActivity());
		sche_detail_list = (ListView) view.findViewById(R.id.sche_detail_list);
		/*
		 * scheduleItemDB.drop();
		 */// schedule detail 전체 select
		selectDB();

		sche_detail_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parnet, View view,
					int position, long id) {
				ImageView sche_detail_item_image = (ImageView) view
						.findViewById(R.id.sche_detail_item_image);
				TextView sche_detail_item_id = (TextView) view
						.findViewById(R.id.sche_detail_item_id);
				TextView sche_detail_item_txtTitle = (TextView) view
						.findViewById(R.id.sche_detail_item_txtTitle);
				TextView sche_detail_item_txtPlaceName = (TextView) view
						.findViewById(R.id.sche_detail_item_txtPlaceName);
				TextView sche_detail_item_txtYear = (TextView) view
						.findViewById(R.id.sche_detail_item_txtYear);
				TextView sche_detail_item_txtMonth = (TextView) view
						.findViewById(R.id.sche_detail_item_txtMonth);
				TextView sche_detail_item_txtDay = (TextView) view
						.findViewById(R.id.sche_detail_item_txtDay);
				TextView sche_detail_item_txtWeek = (TextView) view
						.findViewById(R.id.sche_detail_item_txtWeek);
				TextView sche_detail_item_txtHour = (TextView) view
						.findViewById(R.id.sche_detail_item_txtHourd);
				TextView sche_detail_item_txtMinute = (TextView) view
						.findViewById(R.id.sche_detail_item_txtMinute);
				TextView sche_detail_item_txtAddress = (TextView) view
						.findViewById(R.id.sche_detail_item_txtAddress);
				TextView sche_detail_item_txtContent = (TextView) view
						.findViewById(R.id.sche_detail_item_txtContent);
				TextView sche_detail_item_txtPhone = (TextView) view
						.findViewById(R.id.sche_detail_item_txtPhone);
				TextView sche_detail_item_txtLink = (TextView) view
						.findViewById(R.id.sche_detail_item_txtLink);
				TextView sche_detail_item_txtMapx = (TextView) view
						.findViewById(R.id.sche_detail_item_txtMapxd);
				TextView sche_detail_item_txtMapy = (TextView) view
						.findViewById(R.id.sche_detail_item_txtMapyd);
				TextView sche_detail_item_imageSrc = (TextView) view
						.findViewById(R.id.sche_detail_item_imageSrc);

				String strId = sche_detail_item_id.getText().toString();
				String strTitle = sche_detail_item_txtTitle.getText()
						.toString();
				String strPlaceName = sche_detail_item_txtPlaceName.getText()
						.toString();
				String strYear = sche_detail_item_txtYear.getText().toString();
				String strMonth = sche_detail_item_txtMonth.getText()
						.toString();
				String strDay = sche_detail_item_txtDay.getText().toString();
				String strWeek = sche_detail_item_txtWeek.getText().toString();
				String strHour = sche_detail_item_txtHour.getText().toString();
				String strMinute = sche_detail_item_txtMinute.getText()
						.toString();
				String strAddress = sche_detail_item_txtAddress.getText()
						.toString();
				String strContent = sche_detail_item_txtContent.getText()
						.toString();
				String strPhone = sche_detail_item_txtPhone.getText()
						.toString();
				String strLink = sche_detail_item_txtLink.getText().toString();
				String strMapx = sche_detail_item_txtMapx.getText().toString();
				String strMapy = sche_detail_item_txtMapy.getText().toString();
				String strImageSrc = sche_detail_item_imageSrc.getText()
						.toString();

				replaceDetailFragment(strId, strTitle, strPlaceName, strYear,
						strMonth, strDay, strWeek, strHour, strMinute,
						strAddress, strContent, strPhone, strLink, strMapx,
						strMapy, strImageSrc);
			}
		});

		// 리스트뷰 롱클릭 이벤트
		sche_detail_list
				.setOnItemLongClickListener(new OnItemLongClickListener() {

					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View view, int arg2, long arg3) {
						TextView sche_detail_item_id = (TextView) view
								.findViewById(R.id.sche_detail_item_id);
						TextView sche_detail_item_txtTitle = (TextView) view
								.findViewById(R.id.sche_detail_item_txtTitle);
						TextView sche_detail_item_txtPlaceName = (TextView) view
								.findViewById(R.id.sche_detail_item_txtPlaceName);
						TextView sche_detail_item_txtYear = (TextView) view
								.findViewById(R.id.sche_detail_item_txtYear);
						TextView sche_detail_item_txtMonth = (TextView) view
								.findViewById(R.id.sche_detail_item_txtMonth);
						TextView sche_detail_item_txtDay = (TextView) view
								.findViewById(R.id.sche_detail_item_txtDay);
						TextView sche_detail_item_txtWeek = (TextView) view
								.findViewById(R.id.sche_detail_item_txtWeek);
						TextView sche_detail_item_txtHour = (TextView) view
								.findViewById(R.id.sche_detail_item_txtHourd);
						TextView sche_detail_item_txtMinute = (TextView) view
								.findViewById(R.id.sche_detail_item_txtMinute);
						TextView sche_detail_item_txtAddress = (TextView) view
								.findViewById(R.id.sche_detail_item_txtAddress);
						TextView sche_detail_item_txtContent = (TextView) view
								.findViewById(R.id.sche_detail_item_txtContent);
						TextView sche_detail_item_txtPhone = (TextView) view
								.findViewById(R.id.sche_detail_item_txtPhone);
						TextView sche_detail_item_txtLink = (TextView) view
								.findViewById(R.id.sche_detail_item_txtLink);
						TextView sche_detail_item_txtMapx = (TextView) view
								.findViewById(R.id.sche_detail_item_txtMapxd);
						TextView sche_detail_item_txtMapy = (TextView) view
								.findViewById(R.id.sche_detail_item_txtMapyd);

						String _id = sche_detail_item_id.getText().toString();
						String strTitle = sche_detail_item_txtTitle.getText()
								.toString();
						String strPlaceName = sche_detail_item_txtPlaceName
								.getText().toString();
						String strYear = sche_detail_item_txtYear.getText()
								.toString();
						String strMonth = sche_detail_item_txtMonth.getText()
								.toString();
						String strDay = sche_detail_item_txtDay.getText()
								.toString();
						String strWeek = sche_detail_item_txtWeek.getText()
								.toString();
						String strHour = sche_detail_item_txtHour.getText()
								.toString();
						String strMinute = sche_detail_item_txtMinute.getText()
								.toString();
						String strAddress = sche_detail_item_txtAddress
								.getText().toString();
						String strContent = sche_detail_item_txtContent
								.getText().toString();
						String strPhone = sche_detail_item_txtPhone.getText()
								.toString();
						String strLink = sche_detail_item_txtLink.getText()
								.toString();
						String strMapx = sche_detail_item_txtMapx.getText()
								.toString();
						String strMapy = sche_detail_item_txtMapy.getText()
								.toString();
						createLongDialog(_id, strTitle, strPlaceName, strYear,
								strMonth, strDay, strWeek, strHour, strMinute,
								strAddress, strContent, strPhone, strLink,
								strMapx, strMapy).show();
						return true;
					}
				});

		btn_sche_item_add = (Button) view.findViewById(R.id.btn_sche_item_add);
		btn_sche_item_add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				replaceAddFragment();
			}
		});
		btn_sche_trash = (Button) view.findViewById(R.id.btn_sche_trash);
		btn_sche_trash.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				createAllDeleteDialog().show();
			}

			
		});
	}
	private Dialog createAllDeleteDialog() {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_sche_detele_check);
		
		Button btn_dialog_detail_delete = (Button) dialog.findViewById(R.id.btn_dialog_sche_delete);
		btn_dialog_detail_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ScheduleItemDB db = new ScheduleItemDB(getActivity());
				db.drop();
				dialog.dismiss();
				
				ft = getActivity().getSupportFragmentManager().beginTransaction();
				newFragment = FragmentSchedule.newInstance();

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		});
		
		Button btn_dialog_detail_cancel = (Button) dialog.findViewById(R.id.btn_dialog_sche_cancel);
		btn_dialog_detail_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		mDialog = dialog;
		return mDialog;
	}
	private void selectDB() {
		cursor = scheduleItemDB.selectAllNote();
		ScheduleDetailDBAdapter scheduleDBAdapter = new ScheduleDetailDBAdapter(
				getActivity(), cursor);
		sche_detail_list.setAdapter(scheduleDBAdapter);

	}

	private Dialog createLongDialog(final String id, final String title,
			final String placeName, final String year, final String month,
			final String day, final String week, final String hour,
			final String minute, final String address, final String content,
			final String phone, final String link, final String mapx,
			final String mapy) {
		final Dialog dialog = new Dialog(getActivity(), R.style.Dialog);
		dialog.setContentView(R.layout.dialog_list_long);
		// 수정
		Button btnLongUpdate = (Button) dialog
				.findViewById(R.id.btn_long_update);
		btnLongUpdate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				replaceUpdateFragment(id, title, placeName, year, month, day,
						week, hour, minute, address, content, phone, link,
						mapx, mapy);
				dialog.dismiss();
			}
		});

		// 삭제
		Button btnLongDelete = (Button) dialog
				.findViewById(R.id.btn_long_delete);
		btnLongDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int _id = Integer.parseInt(id);
				scheduleItemDB.deleteNote(_id);
				dialog.dismiss();
				selectDB();
			}
		});

		// 취소
		Button btnLongCancel = (Button) dialog
				.findViewById(R.id.btn_long_cancel);
		btnLongCancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		mDialog = dialog;
		return mDialog;
	}

	private void replaceAddFragment() {
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		newFragment = FragmentScheduleUpdate.newInstance();

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	private void replaceUpdateFragment(String id, String title,
			String placeName, String year, String month, String day,
			String week, String hour, String minute, String address,
			String content, String phone, String link, String mapx, String mapy) {
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		newFragment = FragmentScheduleUpdate.newUpdateInstance(id, title,
				placeName, year, month, day, week, hour, minute, address,
				content, phone, link, mapx, mapy, "null");

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	private void replaceDetailFragment(String _id, String title,
			String placeName, String year, String month, String day,
			String week, String hour, String minute, String address,
			String content, String phone, String link, String mapx,
			String mapy, String imageSrc) {
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		newFragment = FragmentScheduleDetail.newInstance(_id, title, placeName,
				year, month, day, week, hour, minute, address, content, phone,
				link, mapx, mapy, imageSrc);

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
}
