package passionfive.welcometravel.fragment;

import passionfive.welcometravel.R;
import passionfive.welcometravel.application.ApplicationSample;
import passionfive.welcometravel.config.SharedPreferencesInfo;
import passionfive.welcometravel.listener.OnTaskCompletedListener;
import passionfive.welcometravel.parser.AsyncWeather;
import passionfive.welcometravel.util.SampleLog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class FragmentOne extends Fragment {
	private ApplicationSample mApplicationSample;
	private SharedPreferencesInfo mSharedPreferencesInfo;
	
	private String weather;
	
	private AsyncWeather asyncSample;
	
	private TextView tv_weather;
	
	private Button btn_async;
	
	public static FragmentOne newInstance() {
		FragmentOne fragment = new FragmentOne();
		
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_one, null);
		
		init(view);
		
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
	}
	
	private void init(View view) {
		mApplicationSample = (ApplicationSample) getActivity().getApplication();
		mSharedPreferencesInfo = new SharedPreferencesInfo(getActivity());
		
		tv_weather = (TextView) view.findViewById(R.id.tv_weather);
		weather = mSharedPreferencesInfo.getWeather();
		tv_weather.setText(String.format("저장된 온도 : %s℃", weather));
		
		btn_async = (Button) view.findViewById(R.id.btn_async);

		btnEvent();
	}
	
	private void btnEvent() {
		btn_async.setOnClickListener(mClick);
	}
	
	private OnClickListener mClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_async:
				asyncSample = new AsyncWeather(getActivity());
				asyncSample.setOnTaskCompletedListener(mTaskCompleted);
				asyncSample.execute(getString(R.string.weather_url));
				break;
			}
		}
	};
	
	private OnTaskCompletedListener mTaskCompleted = new OnTaskCompletedListener() {
		@Override
		public void onTaskCompleted() {
			weather = mApplicationSample.getWeather().get("curTemp");
			tv_weather.setText(String.format("현재 온도 : %s℃", weather));
			mSharedPreferencesInfo.setWeather(weather);
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SampleLog.LOG_D("FragMentOne - onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SampleLog.LOG_D("FragMentOne - onDestroy");
	}

	@Override
	public void onPause() {
		super.onPause();
		SampleLog.LOG_D("FragMentOne - onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		SampleLog.LOG_D("FragMentOne - onResume");
	}

	@Override
	public void onStart() {
		super.onStart();
		SampleLog.LOG_D("FragMentOne - onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		SampleLog.LOG_D("FragMentOne - onStop");
	}
}
