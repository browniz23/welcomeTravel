package passionfive.welcometravel.application;

import java.util.ArrayList;
import java.util.HashMap;

import passionfive.welcometravel.data.SearchData;

import android.app.Application;

public class ApplicationSample extends Application {
	public HashMap<String, String> hashmapWeather = new HashMap<String, String>();
	public ArrayList<SearchData> aSearchDatas = new ArrayList<SearchData>();
	public HashMap<String, String> getWeather() {
		return hashmapWeather;
	}
	public ArrayList<SearchData> getSearch(){
		return aSearchDatas;
	}
	
	public void clearWeatherData() {
		hashmapWeather.clear();
	}
	
	public void clearSearchData() {
		aSearchDatas.clear();
	}
}
