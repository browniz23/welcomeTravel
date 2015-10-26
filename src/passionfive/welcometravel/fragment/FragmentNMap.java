package passionfive.welcometravel.fragment;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;
import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import passionfive.welcometravel.adapter.SearchListAdapter;
import passionfive.welcometravel.application.ApplicationSample;
import passionfive.welcometravel.config.SharedPreferencesInfo;
import passionfive.welcometravel.data.SearchDB;
import passionfive.welcometravel.data.SearchData;
import passionfive.welcometravel.nmap.NMapCalloutCustomOldOverlay;
import passionfive.welcometravel.nmap.NMapCalloutCustomOverlayView;
import passionfive.welcometravel.nmap.NMapPOIflagType;
import passionfive.welcometravel.nmap.NMapViewerResourceProvider;
import passionfive.welcometravel.parser.AsyncSearch;
import passionfive.welcometravel.util.DpToPx;
import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhn.android.maps.NMapContext;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapProjection;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.mapviewer.overlay.NMapCalloutCustomOverlay;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;

public class FragmentNMap extends Fragment {
	// 메뉴바
	private DisplayMetrics metrics;
	private int displayHeight;
	public static MenuDrawer mSearchListDrawer;
	private ImageView searchDrawer;

	ArrayAdapter<SearchData> adapter;

	// 지도
	final static private String API_KEY = "816b03890688dc2a89e184f65eef0610";
	private NMapContext mMapContext;
	private NMapView mMapView;
	private NMapController mMapController;
	private NMapOverlayManager mMspOverlayManager;
	private NMapViewerResourceProvider mMapViewerResourceProvider;
	private NMapController nMapController;
	private NGeoPoint nGeoPoint;

	// 어싱크테스크
	private ApplicationSample mApplicationSample;
	private SharedPreferencesInfo mSharedPreferencesInfo;
	private String weather;
	private AsyncSearch asyncSample;
	private NMapProjection mapProjection;
	private ListView searchListView;

	private static String strValue;
	private static int SEND_FLAG = 0;

	private static double detail_mapx = 0.0;
	private static double detail_mapy = 0.0;
	private static String detail_title = "";

	public static FragmentNMap newInstance() {
		FragmentNMap fragment = new FragmentNMap();
		SEND_FLAG = 0;
		return fragment;
	}

	public static FragmentNMap searchInstance(String value) {
		FragmentNMap fragment = new FragmentNMap();
		strValue = value;
		SEND_FLAG = 1;
		return fragment;
	}

	public static FragmentNMap newMakrMarkerInstance(double mapx, double mapy,
			String title) {
		FragmentNMap fragment = new FragmentNMap();
		detail_mapx = mapx;
		detail_mapy = mapy;
		detail_title = title;
		SEND_FLAG = 3;
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mMapContext = new NMapContext(super.getActivity());

		mMapContext.onCreate();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_nmap, container, false);
		ActivityMain.backIndex = 0;

		init(view);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Log.e("tag", "onActivityCreated");
	}

	private void menuDrawer() {
		if (mSearchListDrawer == null) {
			mSearchListDrawer = MenuDrawer.attach(getActivity(),
					MenuDrawer.Type.OVERLAY, Position.BOTTOM,
					MenuDrawer.MENU_DRAG_WINDOW); // MenuDrawer.Type.BEHIND 메뉴를
													// 밑으로
													// 숨기
			mSearchListDrawer.setTouchMode(MenuDrawer.TOUCH_MODE_NONE);
			mSearchListDrawer.setMenuView(R.layout.nmap_search_list);
			mSearchListDrawer
					.setMenuSize(displayHeight
							- DpToPx.convert(getActivity()
									.getApplicationContext(), 300)); // "-"
																		// 이거뭐여
		}

	}

	private void init(View view) {
		// 리스트메뉴바
		metrics = new DisplayMetrics(); // 가로세로 폭을 알고싶을때
		getActivity().getWindow().getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		displayHeight = metrics.heightPixels;

		menuDrawer();

		searchListView = (ListView) mSearchListDrawer
				.findViewById(R.id.searchListView);

		final ArrayList<Integer> insertHistoryAfter = new ArrayList<Integer>();

		// searchListView 아이템 하나 클릭시 상세정보 보여주고 히스토리에 추가!
		searchListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {

				Toast.makeText(getActivity(), "히스토리에 추가 되었습니다.",
						Toast.LENGTH_SHORT).show();

				SearchDB searchDB = new SearchDB(getActivity());
				String sign = "2";

				TextView tvTitle = (TextView) v
						.findViewById(R.id.searchItemTitle);
				TextView tvAddress = (TextView) v
						.findViewById(R.id.searchItemAddress);
				TextView tvLink = (TextView) v.findViewById(R.id.searchLink);
				TextView tvDescription = (TextView) v
						.findViewById(R.id.searchDescription);
				TextView tvTelephone = (TextView) v
						.findViewById(R.id.searchTelephone);
				TextView tvMapx = (TextView) v.findViewById(R.id.searchItemX);
				TextView tvMapy = (TextView) v.findViewById(R.id.searchItemY);

				String title = tvTitle.getText().toString();
				String link = tvLink.getText().toString();
				String description = tvDescription.getText().toString();
				String telephone = tvTelephone.getText().toString();
				String address = tvAddress.getText().toString();
				double mapx = Double.parseDouble(tvMapx.getText().toString());
				double mapy = Double.parseDouble(tvMapy.getText().toString());

				mMapController.setMapCenter(mapy, mapx - 0.004, 12);
				boolean checkHistory = false;
				for (Integer index : insertHistoryAfter) {
					if (arg2 == index) {
						checkHistory = true;
						return;
					} else if (checkHistory == false) {
						/*
						 * searchDB.insertHistoryNote(sign, title, link,
						 * description, telephone, address, mapx, mapy);
						 */insertHistoryAfter.add(arg2);
					}
				}
				searchDB.insertHistoryNote(sign, title, link, description,
						telephone, address, mapx, mapy);
			}
		});

		searchDrawer = (ImageView) view.findViewById(R.id.searchDrawer);
		searchDrawer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mSearchListDrawer.toggleMenu();
			}
		});

		// 지도
		// mMapView
		mMapView = (NMapView) view.findViewById(R.id.layout_navermapview);
		mMapView.setApiKey(API_KEY);
		mMapView.setClickable(true);
		mMapView.setEnabled(true);
		mMapView.setFocusable(true);
		mMapView.setFocusableInTouchMode(true);
		mMapView.requestFocus();
		mMapContext.setupMapView(mMapView);
		// mapProjection
		// mapProjection = mMapView.getMapProjection();

		mMapController = mMapView.getMapController();
		/*
		 * mMapController.setMapViewMode(NMapView.VIEW_MODE_HYBRID);
		 */mMapView.setLogoImageOffset(1, 1);
		// mMapController.setMapViewTrafficMode(false);//실시간교통보기
		// mMapController.setMapViewBicycleMode(false);//자전거길보기
		// mMapController.setMapViewPanoramaMode(true);//파노라마지도보기
		// mMapController.setZoomLevelConstraint(1, 14);//최대최소줌설정하기
		// nGeoPoint= mMapController.getMapCenter(); //지도 중심 좌표를 경위도 형식으로 반환한다.
		// NMapOverlay
		mMapViewerResourceProvider = new NMapViewerResourceProvider(
				getActivity());
		mMspOverlayManager = new NMapOverlayManager(getActivity(), mMapView,
				mMapViewerResourceProvider);
		mMspOverlayManager
				.setOnCalloutOverlayListener(onCalloutOverlayListener);
		mMspOverlayManager
				.setOnCalloutOverlayViewListener(onCalloutOverlayViewListener);

		// 어싱크
		mApplicationSample = (ApplicationSample) getActivity().getApplication();
		mSharedPreferencesInfo = new SharedPreferencesInfo(getActivity());

		// 다이어리 디테일에서 지도로 왔을때
		Log.e("x", String.valueOf(detail_mapx));
		Log.e("y", String.valueOf(detail_mapy));
		Log.e("title", detail_title);
		// if(SEND_FLAG == 3){
		// NMapPOIdata poiData = new NMapPOIdata(10,
		// mMapViewerResourceProvider);
		// poiData.addPOIitem(detail_mapx, detail_mapy, detail_title,
		// 1, 0).setRightAccessory(true,
		// NMapPOIflagType.CLICKABLE_ARROW);// 마커
		// poiData.endPOIdata();// 마커
		// NMapPOIdataOverlay poiDataOverlay = mMspOverlayManager
		// .createPOIdataOverlay(poiData, null);// 마커
		// poiDataOverlay.removePOIitem(1);
		// poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);//
		// 마커
		// mMapController.setMapCenter(detail_mapy, detail_mapx, 12);// 마커
		// }

		// 검색창에서 검색했을때
		if (SEND_FLAG == 1) {
			search();
		}
	}

	private void search() {

		String encodedK = null;
		try {
			encodedK = URLEncoder.encode(strValue, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (encodedK.trim()!=""){
			StringBuffer buffer = new StringBuffer();
			buffer.delete(0, buffer.capacity());
			buffer.append("http://openapi.naver.com/search?");
			buffer.append("key=1a0ac7793322bdc155c2d15bde1e9133");
			buffer.append("&query=" + encodedK);
			buffer.append("&target=local&start=1&display=10");

			String url = buffer.toString();// URL만들기
			asyncSample = new AsyncSearch(getActivity(), mHandler);// 어싱크테스크
																	// 생성
			asyncSample.execute(url);// 어싱크테스크 시작
			mApplicationSample.clearSearchData();
			
		}else{
			Toast.makeText(getActivity(), "검색어를 입력하세요", Toast.LENGTH_LONG)
			.show();
			
		}
			
		
	}

	private void testPOIdataOverlay() {

		String resultTitle = null, resultAddress = null, testTitle = null; // 파싱
		double resultResMapX = 0, resultResMapY = 0;// 파싱
		int markerId = NMapPOIflagType.PIN; // 마커
		NMapPOIdata poiData = new NMapPOIdata(10, mMapViewerResourceProvider);// 마커
		// 마커
		for (int i = 0; i < mApplicationSample.aSearchDatas.size(); i++) {// 파싱
			poiData.beginPOIdata(i);
			SearchData searchData = mApplicationSample.aSearchDatas.get(i);// 파싱
			resultTitle = searchData.getTitle();// 파싱
			resultAddress = searchData.getAddress();// 파싱
			resultResMapX = searchData.getMapx();// 파싱
			resultResMapY = searchData.getMapy();// 파싱
			poiData.addPOIitem(resultResMapY, resultResMapX, resultTitle,
					markerId, 0).setRightAccessory(true,
					NMapPOIflagType.CLICKABLE_ARROW);// 마커
			// poiData.removePOIitem(i);
		}

		poiData.endPOIdata();// 마커
		NMapPOIdataOverlay poiDataOverlay = mMspOverlayManager
				.createPOIdataOverlay(poiData, null);// 마커
		poiDataOverlay.removePOIitem(1);
		poiDataOverlay.setOnStateChangeListener(onPOIdataStateChangeListener);// 마커
		mMapController.setMapCenter(
				mApplicationSample.aSearchDatas.get(0).mapy,
				mApplicationSample.aSearchDatas.get(0).mapx - 0.004, 12);// 마커
	}

	private final NMapPOIdataOverlay.OnStateChangeListener onPOIdataStateChangeListener = new NMapPOIdataOverlay.OnStateChangeListener() {

		@Override
		public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay,
				NMapPOIitem item) {
		}

		@Override
		public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay,
				NMapPOIitem item) {
		}
	};

	private final NMapOverlayManager.OnCalloutOverlayListener onCalloutOverlayListener = new NMapOverlayManager.OnCalloutOverlayListener() {
		@Override
		public NMapCalloutOverlay onCreateCalloutOverlay(
				NMapOverlay itemOverlay, NMapOverlayItem overlayItem,
				Rect itemBounds) {
			if (itemOverlay instanceof NMapPOIdataOverlay) {
				NMapPOIdataOverlay poiDataOverlay = (NMapPOIdataOverlay) itemOverlay;

				if (!poiDataOverlay.isFocusedBySelectItem()) {
					int countOfOverlappedItems = 1;

					NMapPOIdata poiData = poiDataOverlay.getPOIdata();
					for (int i = 0; i < poiData.count(); i++) {
						NMapPOIitem poiItem = poiData.getPOIitem(i);

						if (poiItem == overlayItem) {
							continue;
						}

						if (Rect.intersects(poiItem.getBoundsInScreen(),
								overlayItem.getBoundsInScreen())) {
							countOfOverlappedItems++;
						}
					}

					if (countOfOverlappedItems > 1) {
						String text = countOfOverlappedItems
								+ " overlapped items for "
								+ overlayItem.getTitle();
						Toast.makeText(getActivity(), text, Toast.LENGTH_LONG)
								.show();
						return null;
					}
				}
			}

			if (overlayItem instanceof NMapPOIitem) {
				NMapPOIitem poiItem = (NMapPOIitem) overlayItem;

				if (poiItem.showRightButton()) {
					return new NMapCalloutCustomOldOverlay(itemOverlay,
							overlayItem, itemBounds, mMapViewerResourceProvider);
				}
			}

			return new NMapCalloutCustomOverlay(itemOverlay, overlayItem,
					itemBounds, mMapViewerResourceProvider);
		}
	};

	private final NMapOverlayManager.OnCalloutOverlayViewListener onCalloutOverlayViewListener = new NMapOverlayManager.OnCalloutOverlayViewListener() {

		@Override
		public View onCreateCalloutOverlayView(NMapOverlay itemOverlay,
				NMapOverlayItem overlayItem, Rect itemBounds) {

			if (overlayItem != null) {
				String title = overlayItem.getTitle();
				if (title != null && title.length() > 5) {
					return new NMapCalloutCustomOverlayView(getActivity(),
							itemOverlay, overlayItem, itemBounds);
				}
			}

			return null;
		}
	};

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			testPOIdataOverlay(); // 마커 찍기

			// 리스트뷰

			SearchListAdapter listAdapter = new SearchListAdapter(
					FragmentNMap.this.getActivity(),
					mApplicationSample.aSearchDatas);

			searchListView.setAdapter(listAdapter);

			mSearchListDrawer.toggleMenu();

		}
	};

	@Override
	public void onStart() {
		super.onStart();
		mMapContext.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();

		mMapContext.onResume();

	}

	@Override
	public void onPause() {
		super.onPause();

		mMapContext.onPause();

	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		mMapContext.onDestroy();
		super.onDestroy();
	}

}
