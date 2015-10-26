package passionfive.welcometravel.adapter;

import passionfive.welcometravel.R;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class ScheduleDetailDBAdapter extends CursorAdapter {

	public ScheduleDetailDBAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		final ImageView sche_detail_item_image = (ImageView) view.findViewById(R.id.sche_detail_item_image);
		final TextView sche_detail_item_id = (TextView) view.findViewById(R.id.sche_detail_item_id);
		final TextView sche_detail_item_txtTitle = (TextView) view.findViewById(R.id.sche_detail_item_txtTitle);
		final TextView sche_detail_item_txtPlaceName = (TextView) view.findViewById(R.id.sche_detail_item_txtPlaceName);
		final TextView sche_detail_item_txtYear = (TextView) view.findViewById(R.id.sche_detail_item_txtYear);
		final TextView sche_detail_item_txtMonth = (TextView) view.findViewById(R.id.sche_detail_item_txtMonth);
		final TextView sche_detail_item_txtDay = (TextView) view.findViewById(R.id.sche_detail_item_txtDay);
		final TextView sche_detail_item_txtWeek = (TextView) view.findViewById(R.id.sche_detail_item_txtWeek);
		final TextView sche_detail_item_txtHour = (TextView) view.findViewById(R.id.sche_detail_item_txtHourd);
		final TextView sche_detail_item_txtMinute = (TextView) view.findViewById(R.id.sche_detail_item_txtMinute);
		final TextView sche_detail_item_txtAddress = (TextView) view.findViewById(R.id.sche_detail_item_txtAddress);
		final TextView sche_detail_item_txtContent = (TextView) view.findViewById(R.id.sche_detail_item_txtContent);
		final TextView sche_detail_item_txtPhone = (TextView) view.findViewById(R.id.sche_detail_item_txtPhone);
		final TextView sche_detail_item_txtLink = (TextView) view.findViewById(R.id.sche_detail_item_txtLink);
		final TextView sche_detail_item_txtMapx = (TextView) view.findViewById(R.id.sche_detail_item_txtMapxd);
		final TextView sche_detail_item_txtMapy = (TextView) view.findViewById(R.id.sche_detail_item_txtMapyd);
		final TextView sche_detail_item_imageSrc = (TextView) view.findViewById(R.id.sche_detail_item_imageSrc);				
		
		try {			 
			
			Uri pictureUrl = Uri.parse(cursor.getString(cursor.getColumnIndex("bitmap")));
			Bitmap bitmap=Images.Media.getBitmap(context.getContentResolver(), pictureUrl);
			
			sche_detail_item_image.setImageBitmap(bitmap);
			sche_detail_item_image.setScaleType(ScaleType.CENTER_CROP);
			
		}catch (Exception e) {
			e.printStackTrace();
			Log.e("bitmap", e.toString());		
		} 
		
		sche_detail_item_imageSrc.setText(cursor.getString(cursor.getColumnIndex("bitmap")));
		sche_detail_item_id.setText(cursor.getString(cursor.getColumnIndex("_id")));
		sche_detail_item_txtTitle.setText(cursor.getString(cursor.getColumnIndex("title")));
		sche_detail_item_txtPlaceName.setText(cursor.getString(cursor.getColumnIndex("place_name")));
		sche_detail_item_txtYear.setText(cursor.getString(cursor.getColumnIndex("year")));
		sche_detail_item_txtMonth.setText(cursor.getString(cursor.getColumnIndex("month")));
		sche_detail_item_txtDay.setText(cursor.getString(cursor.getColumnIndex("day")));
		sche_detail_item_txtWeek.setText(cursor.getString(cursor.getColumnIndex("week")));
		sche_detail_item_txtHour.setText(cursor.getString(cursor.getColumnIndex("hour")));
		sche_detail_item_txtMinute.setText(cursor.getString(cursor.getColumnIndex("minute")));
		sche_detail_item_txtAddress.setText(cursor.getString(cursor.getColumnIndex("address")));
		sche_detail_item_txtContent.setText(cursor.getString(cursor.getColumnIndex("content")));
		sche_detail_item_txtPhone.setText(cursor.getString(cursor.getColumnIndex("phone")));
		sche_detail_item_txtLink.setText(cursor.getString(cursor.getColumnIndex("link")));
		sche_detail_item_txtMapx.setText(cursor.getString(cursor.getColumnIndex("mapx")));
		sche_detail_item_txtMapy.setText(cursor.getString(cursor.getColumnIndex("mapy")));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.schedule_detail_item, parent, false);
		return v;
	}

}
