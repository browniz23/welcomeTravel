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
		Log.e("Ȯ��", "Ȯ��");
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
			Log.e("Ȯ��", url.toString());
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
				case XmlPullParser.START_TAG: // parser�� ���� �±׸� ������ ����
					
					if (parser.getName().equals("total")) {
						inTotal = true;
					}
					if (parser.getName().equals("item")) {
						inItem = true;
					}
					if (parser.getName().equals("title")) { // title ������ ������ ������
						// �ְ� ����
						inTitle = true;
					}
					if (parser.getName().equals("link")) { // mapy ������ ������ ������
						// �ְ� ����
						inlink = true;
					}

					if (parser.getName().equals("description")) { // mapy ������ ������ ������
						// �ְ� ����
						indescription = true;
					}
					if (parser.getName().equals("telephone")) { // mapy ������ ������ ������
						// �ְ� ����
						intelephone = true;
					}
					if (parser.getName().equals("address")) { // address ������ ������
						// ������ �ְ� ����
						inAddress = true;
					}
					if (parser.getName().equals("mapx")) { // mapx ������ ������ ������
						// �ְ� ����
						inMapx = true;
					}
					if (parser.getName().equals("mapy")) { // mapy ������ ������ ������
						// �ְ� ����
						inMapy = true;
					}
					if (parser.getName().equals("message")) { // message �±׸� ������
						// ���� ���

					}
					break;

				case XmlPullParser.TEXT:// parser�� ���뿡 ����������
					count++;
				
					if (inTitle) { // isTitle�� true�� �� �±��� ������ ����.
						String bTagTitle = parser.getText();
						//String removalTag = bTagTitle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
						String removeTitle=setString(bTagTitle);
						title = removeTitle;
						inTitle = false;
					}
					if (inlink) { // inlink�� true�� �� �±��� ������ ����.
						link = parser.getText();
						inlink = false;
					}
					if (indescription) { // indescription�� true�� �� �±��� ������ ����.
						String bTagDescription = parser.getText();
						//String removalTag = bTagTitle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
						String removeDescription=setString(bTagDescription);
						description = removeDescription;
						indescription = false;
					}
					if (intelephone) { // intelephone�� true�� �� �±��� ������ ����.
						telephone = parser.getText();
						intelephone = false;
					}
					if (inAddress) { // isAddress�� true�� �� �±��� ������ ����.
						address = parser.getText();
						inAddress = false;
					}
					if (inMapx) { // isMapx�� true�� �� �±��� ������ ����.
						PreviousMapx = parser.getText();
						inMapx = false;
					}
					if (inMapy) { // isMapy�� true�� �� �±��� ������ ����.
						PreviousMapy = parser.getText();
						inMapy = false;
					}
					break;
				case XmlPullParser.END_TAG:
					if (parser.getName().equals("item")) {
						// ��ǥ��ȯ
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
								if (tag.compareTo("result") == 0) { // �ļ���
																	// result
																	// �±׸� ������
																	// x�� y�� �Ӽ�
																	// ���� ����
																	// longitude,latitude��
																	// ����.
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
			Log.e("Ȯ��", "����");
		}

		return NO_ERROR;
	}

	@Override
	protected void onPostExecute(Integer result) {
		// �߰��� �ؾ��� �κ� ���ʿ�
		if (result == ERROR) {

			mDialog.dismiss();
		} else if (result == NO_ERROR) {
				mHandler.sendEmptyMessage(0);
		}

		super.onPostExecute(result);
	}
	
	
}
