package passionfive.welcometravel.parser;

import passionfive.welcometravel.R;
import passionfive.welcometravel.application.ApplicationSample;
import passionfive.welcometravel.listener.OnTaskCompletedListener;
import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncBase extends AsyncTask<String, Integer, Integer> {
	final protected int NO_ERROR = 1;
	final protected int ERROR = 2;
	
	final protected int TIME = 5000;

	protected ApplicationSample mApplicationSample = null;
	
	protected OnTaskCompletedListener mTaskCompleted = null;
	
	protected Dialog mDialog = null;
	
	private Context mContext = null;
	private Spanned spanned = null;
	
	protected String strUrl = null;
	protected String imgUrl = null;
	
	public AsyncBase(Context context) {
		this.mContext=context;
		this.mApplicationSample = (ApplicationSample) mContext.getApplicationContext();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		
		if(mDialog == null) {
			mDialog = createProgressDialog();
		}

		mDialog.setCancelable(false);
		mDialog.show();
	}

	@Override
	protected Integer doInBackground(String... params) {
		return ERROR;
	}
	
	@Override
	protected void onPostExecute(Integer result) {
		if (mDialog!=null) {
			mDialog.dismiss();
			mDialog=null;
		}

		if(result==ERROR) {
			mDialog=createCheckNetWorkDialog();
			mDialog.show();
		}
		else if(result == NO_ERROR) {
			if(mTaskCompleted != null) {
				mTaskCompleted.onTaskCompleted();
			}
		}
	}
	
	public void setOnTaskCompletedListener(OnTaskCompletedListener mTaskCompleted) {
		this.mTaskCompleted = mTaskCompleted;
		
	}
	
	protected Dialog createProgressDialog() {
		final Dialog dialog = new Dialog(mContext, R.style.Dialog);
		dialog.setContentView(R.layout.dialog_progress);
		
		return dialog;
	}
	
	public Dialog createCheckNetWorkDialog() {
		final Dialog dialog = new Dialog(mContext, R.style.Dialog);
		dialog.setContentView(R.layout.dailog_sample);
		
		TextView textView = (TextView)dialog.findViewById(R.id.tv_contents);
		textView.setText(mContext.getResources().getString(R.string.check_network));
		
		Button button = (Button)dialog.findViewById(R.id.btn_done);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		
		return dialog;
	}
	
	protected String setString(String str) {
		if(str!=null) {
			spanned=Html.fromHtml(str);
			return spanned.toString();
		}
		else {
			return "";
		}
	}
}
