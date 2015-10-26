package passionfive.welcometravel.fragment;


import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentAccountBook extends Fragment{
	private Button btn_all, btn_plus, btn_minus;
	private FragmentTransaction ft;
	private Fragment newFragment;
	private final int ALLINDEX = 1;
	private final int PlUSINDEX = 2;
	private final int MINUSINDEX = 3;

	
	public static FragmentAccountBook newInstance() {
		FragmentAccountBook fragment = new FragmentAccountBook();
		return fragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_accountbook, null);
		ActivityMain.backIndex = 0;
		init(view);
		replaceAllFragment(ALLINDEX);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}
	
	private void init(View view) {
		btn_all = (Button) view.findViewById(R.id.btn_all);
		btn_plus = (Button) view.findViewById(R.id.btn_plus);
		btn_minus = (Button) view.findViewById(R.id.btn_minus);
		btnEvent();
	}
	
	private void btnEvent() {
		btn_all.setOnClickListener(mClick);
		btn_plus.setOnClickListener(mClick);
		btn_minus.setOnClickListener(mClick);
	}
	
	private OnClickListener mClick = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_all:
				replaceAllFragment(ALLINDEX);
				break;
				
			case R.id.btn_plus:
				replaceAllFragment(PlUSINDEX);
				break;
				
			case R.id.btn_minus:
				replaceAllFragment(MINUSINDEX);
				break;
			}
		}
	};
	
	private void replaceAllFragment(int index)
	{
		ft = ((FragmentActivity) getActivity()).getSupportFragmentManager().beginTransaction();
		newFragment = FragmentAccountBookList.newInstance(index);

		ft.replace(R.id.accountbook_fragment, newFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
		ft.commit();
	}
	
}
