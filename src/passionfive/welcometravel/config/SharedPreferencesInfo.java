package passionfive.welcometravel.config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesInfo
{
	final private static String INFO = "mInfo";
	final private static String WEATHER = "mWeather";
	
	private Context mContext = null;
	
	private SharedPreferences pref;
	private SharedPreferences.Editor edit;
	
	public SharedPreferencesInfo(Context context) {
		mContext = context;
		pref = mContext.getSharedPreferences(INFO, Activity.MODE_PRIVATE);
	}
	
	public String getWeather() {
		return pref.getString(WEATHER, "NULL");
	}

	public void setWeather(String Weather) {
		edit = pref.edit();
		edit.putString(WEATHER, Weather);
		edit.commit();
	}
}
