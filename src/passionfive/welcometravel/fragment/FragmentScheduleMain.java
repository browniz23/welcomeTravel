package passionfive.welcometravel.fragment;

import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentScheduleMain extends Fragment {
	private FragmentTransaction ft;
	private Fragment newFragment;
	
	private Button btn_sche_add;
	
	public static FragmentScheduleMain newInstance() {
		FragmentScheduleMain fragment = new FragmentScheduleMain();
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_schedule_main, null);
		ActivityMain.backIndex = 0;
		
		init(view);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void init(View view) {
		btn_sche_add = (Button) view.findViewById(R.id.btn_sche_add);
		btn_sche_add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				replaceFragment();
			}
		});
	}
	
	private void replaceFragment()
	{
		ft = getActivity().getSupportFragmentManager().beginTransaction();
		newFragment = FragmentSchedule.newInstance();

		ft.replace(R.id.layout_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
}
