package passionfive.welcometravel.activity;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import passionfive.welcometravel.R;
import passionfive.welcometravel.fragment.FragmentAccountBook;
import passionfive.welcometravel.fragment.FragmentHistory;
import passionfive.welcometravel.fragment.FragmentNMap;
import passionfive.welcometravel.fragment.FragmentSchedule;
import passionfive.welcometravel.fragment.FragmentScheduleDetail;
import passionfive.welcometravel.fragment.FragmentTouchImage;
import passionfive.welcometravel.util.DpToPx;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ActivityMain extends FragmentActivity {
	final static private int DELAY_TIME = 2000;
	final static private int APP_FINISH = 100;

	private boolean mFinishFlag;
	private String mApp_Finish;

	private DisplayMetrics metrics;

	private int displayWidth;

	private MenuDrawer mMenuDrawer;

	private Dialog mDialog;

	public Button btn_menu;
	private Button btn_search;

	public static int backIndex = 0;

	private TextView tv_title;
	private EditText ed_title;

	private FragmentTransaction ft;
	private Fragment newFragment;
	public static Fragment touchragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // XML레이아웃 연결
		 startActivity(new Intent(this, ggariSplash.class));
		// StrictMode.enableDefaults();
		init();
	}

	private void init() {
		/*
		 * SearchDB searchDB = new SearchDB(this); searchDB.dropNote();
		 */

		// 화면사이즈 설정
//		SearchDB searchDB = new SearchDB(getApplication());
//		searchDB.dropNote();
		metrics = new DisplayMetrics(); // 가로세로 폭을 알고싶을때
		getWindow().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		displayWidth = metrics.widthPixels; // 디스플레이화면 가로는 메트릭스에서 구해진 픽셀값과 같게한다

		mMenuDrawer = MenuDrawer.attach(this, MenuDrawer.Type.OVERLAY,
				Position.LEFT, MenuDrawer.MENU_DRAG_WINDOW); // MenuDrawer.Type.BEHIND
																// 메뉴를 밑으로 숨기
		mMenuDrawer.setMenuView(R.layout.menudrawer_menu);
		mMenuDrawer.setMenuSize(displayWidth
				- DpToPx.convert(getApplicationContext(), 140)); // "-" 이거뭐여 o_O
		
		// mMenuDrawer.setDropShadow(R.drawable.img_shadow_left);
		// mMenuDrawer.setDropShadowSize(DpToPx.convert(getApplicationContext(),
		// 15));// 그림자 사이즈 설정

		mApp_Finish = getResources().getString(R.string.app_finish);

		btn_menu = (Button) findViewById(R.id.btn_menu);
		btn_search = (Button) findViewById(R.id.btn_search);
		
		// 타이틀 필드초기화
		tv_title = (TextView) findViewById(R.id.tv_title);
		ed_title = (EditText) findViewById(R.id.ed_title);
		btnEvent();
		replaceFragmentNMap();
	}

	private void btnEvent() {
		btn_menu.setOnClickListener(mClick);
		btn_search.setOnClickListener(mClick);
		for (int i = 0; i < 4; i++) {
			findViewById(R.id.btn_menu_01 + i).setOnClickListener(mMenuClick);
		}
	}

	// 홈 nmap replace
	private void replaceFragmentNMap() {
		ft = getSupportFragmentManager().beginTransaction();
		newFragment = FragmentNMap.newInstance();
		tv_title.setText("홈");
		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	// 가계부 replace
	private void replaceAccountBookFragment() {
		ft = getSupportFragmentManager().beginTransaction();
		newFragment = FragmentAccountBook.newInstance();

		tv_title.setText("가계부");

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	// 스케쥴 replace
	private void replaceScheduleFragment() {
		ft = getSupportFragmentManager().beginTransaction();
		newFragment = FragmentSchedule.newInstance();

		tv_title.setText("스케줄러");

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	// 히스토리 replace
	private void replaceHistoryFragment() {
		ft = getSupportFragmentManager().beginTransaction();
		newFragment = FragmentHistory.newInstance();

		tv_title.setText("즐겨찾기");

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	// FragmentNMap Search replace
	private void replaceNmapSearchFragment(String value) {
		ft = getSupportFragmentManager().beginTransaction();
		newFragment = FragmentNMap.searchInstance(value);

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}

	// test 프래그먼트

	private OnClickListener mClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_menu:
				mMenuDrawer.toggleMenu();
				break;
			case R.id.btn_search:
				if(ed_title.getVisibility()==View.GONE){
					ed_title.setVisibility(View.VISIBLE);
					tv_title.setVisibility(View.GONE);
					ed_title.setText("");
					break;
				}
				String value = ed_title.getText().toString();
				replaceNmapSearchFragment(value);
				break;
			}
		}
	};

	private OnClickListener mMenuClick = new OnClickListener() {
		@Override
		public void onClick(View view) {
			String value = ((Button) view).getText().toString();

			switch (view.getId()) {
			case R.id.btn_menu_01:
				replaceFragmentNMap();
				break;

			case R.id.btn_menu_02:
				replaceAccountBookFragment();
				break;

			case R.id.btn_menu_03:
				replaceScheduleFragment();
				break;

			case R.id.btn_menu_04:
				replaceHistoryFragment();
				break;

			}

			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}

			// 메뉴에서 버튼 클릭시 다이얼로그 뛰우기
			// createSampleDialog(((Button)view).getText().toString()).show();

			mMenuDrawer.closeMenu();
		}
	};

	private Dialog createSampleDialog(String str) {
		final Dialog dialog = new Dialog(ActivityMain.this, R.style.Dialog);
		dialog.setContentView(R.layout.dailog_sample);

		TextView textView = (TextView) dialog.findViewById(R.id.tv_contents);
		textView.setText(str);

		Button button = (Button) dialog.findViewById(R.id.btn_done);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		mDialog = dialog;
		return mDialog;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			mMenuDrawer.toggleMenu();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private Handler mFinishHandler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {
			if (msg.what == APP_FINISH) {
				mFinishFlag = false;
			}
		};
	};

	@Override
	public void onBackPressed() {
		final int drawerState = mMenuDrawer.getDrawerState();
		if (drawerState == MenuDrawer.STATE_OPEN
				|| drawerState == MenuDrawer.STATE_OPENING) {
			mMenuDrawer.closeMenu();
			return;
		}
		
		if(tv_title.getVisibility() == View.GONE){
			tv_title.setVisibility(View.VISIBLE);
			ed_title.setVisibility(View.GONE);
			return;
		}
		
		if(backIndex>=1){
			switch (backIndex) {
			case 1:
				ft = getSupportFragmentManager().beginTransaction();

				ft.remove(touchragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				backIndex = 2;
				break;
			case 2:
				ft = getSupportFragmentManager().beginTransaction();
				newFragment = FragmentSchedule.newInstance();

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				break;
			case 3 :
				ft = getSupportFragmentManager().beginTransaction();
				newFragment = FragmentSchedule.newInstance();

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				break;
			case 4 :
				ft = getSupportFragmentManager().beginTransaction();
				newFragment = FragmentSchedule.newInstance();

				ft.replace(R.id.layout_fragment, newFragment);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
				break;
			}
			return;
		}
		

		final int drawerSearchState = FragmentNMap.mSearchListDrawer
				.getDrawerState();
		if (drawerSearchState == FragmentNMap.mSearchListDrawer.STATE_OPEN
				|| drawerSearchState == FragmentNMap.mSearchListDrawer.STATE_OPENING) {
			FragmentNMap.mSearchListDrawer.closeMenu();
			return;
		}

		if (!mFinishFlag) {
			Toast.makeText(getApplicationContext(), mApp_Finish,
					Toast.LENGTH_SHORT).show();
			mFinishHandler.sendEmptyMessageDelayed(APP_FINISH, DELAY_TIME);
			mFinishFlag = true;
		} else {
			finish();
		}
	}
}
