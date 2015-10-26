package passionfive.welcometravel.activity;

import passionfive.welcometravel.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

	public class ggariSplash extends Activity {
		 
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.ggari_splash);
	         
	        initialize();
	    }

		private void initialize()
	    {
	        Handler handler =    new Handler()
	                                     {
	                                         @Override
	                                         public void handleMessage(Message msg)
	                                         {
	                                             finish();    // 액티비티 종료
	                                         }
	                                     };

	        handler.sendEmptyMessageDelayed(0, 2700);    // ms, 3초후 종료시킴
	    }
	}

