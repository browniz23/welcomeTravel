package passionfive.welcometravel.fragment;


import passionfive.welcometravel.R;
import passionfive.welcometravel.util.SampleLog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class FragmentTwo extends Fragment {
	final static private String KEY_VALUE = "VALUE";
	
	private TextView tv_value;
	
	public static FragmentTwo newInstance(String value) {
		FragmentTwo fragment = new FragmentTwo();
		
		Bundle bundle = new Bundle();
		bundle.putString(KEY_VALUE, value);
		fragment.setArguments(bundle);
		
		return fragment;
	}
	
	public String getValue() {
		String value = getArguments().getString(KEY_VALUE);
		return value;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_two, null);
		
		init(view);
		
		return view;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void init(View view) {
		tv_value = (TextView) view.findViewById(R.id.tv_value);
		
		String value = getValue();
		if(value != null) {
			tv_value.setText(getValue());
		}
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		SampleLog.LOG_D("FragMentTwo - onCreate");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		SampleLog.LOG_D("FragMentTwo - onDestroy");
	}

	@Override
	public void onPause() {
		super.onPause();
		SampleLog.LOG_D("FragMentTwo - onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		SampleLog.LOG_D("FragMentTwo - onResume");
	}

	@Override
	public void onStart() {
		super.onStart();
		SampleLog.LOG_D("FragMentTwo - onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		SampleLog.LOG_D("FragMentTwo - onStop");
	}
}
