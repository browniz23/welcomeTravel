package passionfive.welcometravel.parser;

import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import passionfive.welcometravel.data.SearchData;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class AsyncSearch extends AsyncBase {
	boolean inItem = false, inTitle = false, inAddress = false, inMapx = false,
			inMapy = false,indescription =false,inlink=false,intelephone=false,inTotal=false;
	String title = null, address = null, mapx = null, mapy = null,
			PreviousMapx = null, PreviousMapy = null,description =null,link=null,telephone=null;
	double resMapX = 0, resMapY = 0;
	String items;
	Handler mHandler;
	String total = "5";
	public AsyncSearch(Context context, Handler mHandler) {
		super(context);
		Log.e("확인", "확인");
		this.mHandler=mHandler;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Integer doInBackground(String... params) {

		String str = params[0];
		
		System.setProperty("http.keepAlive", "false");
		int count=0;
		try {
			URL url = new URL(str);
			Log.e("확인", url.toString());
			XmlPullParserFactory parserCreator = XmlPullParserFactory
					.newInstance();
			XmlPullParser parser = parserCreator.newPullParser();
			Log.e("parser", parser.toString());

			parser.setInput(url.openStream(), null);
			Log.e("input", parser.toString());
			int parserEvent = parser.getEventType();
			count=0;
			while (parserEvent != XmlPullParser.END_DOCUMENT || count<10) {
				switch (parserEvent) {
				case XmlPullParser.START_TAG: // parser가 시작 태그를 만나면 실행
					
					if (parser.getName().equals("total")) {
						inTotal = true;
					}
					if (parser.getName().equals("item")) {
						inItem = true;
					}
					if (parser.getName().equals("title")) { // title 만나면 내용을 받을수
						// 있게 하자
						inTitle = true;
					}
					if (parser.getName().equals("link")) { // mapy 만나면 내용을 받을수
						// 있게 하자
						inlink = true;
					}

					if (parser.getName().equals("description")) { // mapy 만나면 내용을 받을수
						// 있게 하자
						indescription = true;
					}
					if (parser.getName().equals("telephone")) { // mapy 만나면 내용을 받을수
						// 있게 하자
						intelephone = true;
					}
					if (parser.getName().equals("address")) { // address 만나면 내용을
						// 받을수 있게 하자
						inAddress = true;
					}
					if (parser.getName().equals("mapx")) { // mapx 만나면 내용을 받을수
						// 있게 하자
						inMapx = true;
					}
					if (parser.getName().equals("mapy")) { // mapy 만나면 내용을 받을수
						// 있게 하자
						inMapy = true;
					}
					if (parser.getName().equals("message")) { // message 태그를 만나면
						// 에러 출력

					}
					break;

				case XmlPullParser.TEXT:// parser가 내용에 접근했을때
					count++;
				
					if (inTitle) { // isTitle이 true일 때 태그의 내용을 저장.
						String bTagTitle = parser.getText();
						//String removalTag = bTagTitle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
						String removeTitle=setString(bTagTitle);
						title = removeTitle;
						inTitle = false;
					}
					if (inlink) { // inlink이 true일 때 태그의 내용을 저장.
						link = parser.getText();
						inlink = false;
					}
					if (indescription) { // indescription이 true일 때 태그의 내용을 저장.
						String bTagDescription = parser.getText();
						//String removalTag = bTagTitle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
						String removeDescription=setString(bTagDescription);
						description = removeDescription;
						indescription = false;
					}
					if (intelephone) { // intelephone이 true일 때 태그의 내용을 저장.
						telephone = parser.getText();
						intelephone = false;
					}
					if (inAddress) { // isAddress이 true일 때 태그의 내용을 저장.
						address = parser.getText();
						inAddress = false;
					}
					if (inMapx) { // isMapx이 true일 때 태그의 내용을 저장.
						PreviousMapx = parser.getText();
						inMapx = false;
					}
					if (inMapy) { // isMapy이 true일 때 태그의 내용을 저장.
						PreviousMapy = parser.getText();
						inMapy = false;
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")) {
						// 좌표변환
						String transStrUrl = "http://apis.daum.net/local/geo/transcoord?apikey=52fb86a9ff1be6b9369779d94af5c8933d23cd13&x="
								+ PreviousMapx
								+ "&y="
								+ PreviousMapy
								+ "&fromCoord=KTM&toCoord=WGS84&output=xml";
						URL transUrl = new URL(transStrUrl);
						XmlPullParserFactory transParserCreator = XmlPullParserFactory
								.newInstance();
						XmlPullParser transParser = transParserCreator
								.newPullParser();
						transParser.setInput(transUrl.openStream(), null);
						int transParserEvent = transParser.getEventType();
						while (transParserEvent != XmlPullParser.END_DOCUMENT) {
							switch (transParserEvent) {
							case XmlPullParser.START_TAG:
								String tag = transParser.getName();
								if (tag.compareTo("result") == 0) { // 파서가
																	// result
																	// 태그를 만나면
																	// x의 y의 속성
																	// 값을 각각
																	// longitude,latitude에
																	// 넣음.
									mapy = transParser.getAttributeValue(null,
											"x");
									mapx = transParser.getAttributeValue(null,
											"y");
								}
								break;
							}
							transParserEvent = transParser.next();
						}

						SearchData searchData = new SearchData();
						searchData.setTitle(title);
						searchData.setLink(link);
						searchData.setDescription(description);
						searchData.setTelephone(telephone);
						searchData.setAddress(address);
						searchData.setMapx(Double.parseDouble(mapx));
						searchData.setMapy(Double.parseDouble(mapy));
						mApplicationSample.aSearchDatas.add(searchData);
	
						 Log.e("doinbackground", searchData.getLink());
						 Log.e("doinbackground", searchData.getDescription());
						 Log.e("doinbackground", searchData.getTelephone());
	
						inItem = false;
						
					}
					break;
				}
				parserEvent = parser.next();
			}
		} catch (Exception e) {
			Log.e("확인", "에러");
		}

		return NO_ERROR;
	}

	@Override
	protected void onPostExecute(Integer result) {
		// 추가로 해야할 부분 이쪽에
		if (result == ERROR) {

			mDialog.dismiss();
		} else if (result == NO_ERROR) {
				mHandler.sendEmptyMessage(0);
		}

		super.onPostExecute(result);
	}
	
	
}
