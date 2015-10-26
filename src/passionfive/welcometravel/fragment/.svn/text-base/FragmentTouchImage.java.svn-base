package passionfive.welcometravel.fragment;

import passionfive.welcometravel.R;
import passionfive.welcometravel.activity.ActivityMain;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.util.FloatMath;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class FragmentTouchImage extends Fragment implements OnTouchListener{
	private static final String TAG = "FragmentTouch";
	Matrix matrix = new Matrix();
	Matrix savedMatrix = new Matrix();
	
	static final int NONE = 0;
	static final int DRAG = 1;
	static final int ZOOM = 2;
	int mode = NONE;
	
	PointF start = new PointF();
	PointF mid = new PointF();
	float oldDist = 1f;
	
	private static String imageSrc;
	
	public static FragmentTouchImage newInstance(String strImageSrc) {
		FragmentTouchImage fragment = new FragmentTouchImage();
		imageSrc = strImageSrc;
		return fragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_touch_image, null);
		ActivityMain.backIndex = 1;
		init(view);
		return view;
	}
	
	private void init(View view){
		ImageView iamgeView = (ImageView) view.findViewById(R.id.touchImage);
		
		Uri pictureUrl = Uri.parse(imageSrc);
		
		Bitmap bitmap;
		
		try {
			bitmap = Images.Media.getBitmap(getActivity().getContentResolver(), pictureUrl);
			iamgeView.setImageBitmap(bitmap);
			iamgeView.setScaleType(ScaleType.CENTER_CROP);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		iamgeView.setOnTouchListener(this);
	}
	
	public boolean onTouch(View v,MotionEvent event){
		ImageView view = (ImageView)v;
		dumpEvent(event);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
	      case MotionEvent.ACTION_DOWN:
	         savedMatrix.set(matrix);
	         start.set(event.getX(), event.getY());
	         Log.d(TAG, "mode=DRAG");
	         mode = DRAG;
	         break;
	      case MotionEvent.ACTION_POINTER_DOWN:
	         oldDist = spacing(event);
	         Log.d(TAG, "oldDist=" + oldDist);
	         if (oldDist > 10f) {
	            savedMatrix.set(matrix);
	            midPoint(mid, event);
	            mode = ZOOM;
	            Log.d(TAG, "mode=ZOOM");
	         }
	         break;
	      case MotionEvent.ACTION_UP:
	      case MotionEvent.ACTION_POINTER_UP:
	         mode = NONE;
	         Log.d(TAG, "mode=NONE");
	         break;
	      case MotionEvent.ACTION_MOVE:
	         if (mode == DRAG) {
	            // ...
	            matrix.set(savedMatrix);
	            matrix.postTranslate(event.getX() - start.x,
	                  event.getY() - start.y);
	         }
	         else if (mode == ZOOM) {
	            float newDist = spacing(event);
	            Log.d(TAG, "newDist=" + newDist);
	            if (newDist > 10f) {
	               matrix.set(savedMatrix);
	               float scale = newDist / oldDist;
	               matrix.postScale(scale, scale, mid.x, mid.y);
	            }
	         }
	         break;
	      }

	      view.setImageMatrix(matrix);
	      return true; 
	}

	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
	    float y = event.getY(0) + event.getY(1);
	    point.set(x / 2, y / 2);
		
	}

	private float spacing(MotionEvent event) {
		 float x = event.getX(0) - event.getX(1);
	     float y = event.getY(0) - event.getY(1);
	     return FloatMath.sqrt(x * x + y * y);
	}

	private void dumpEvent(MotionEvent event) {
		 String names[] = { "DOWN", "UP", "MOVE", "CANCEL", "OUTSIDE",
		            "POINTER_DOWN", "POINTER_UP", "7?", "8?", "9?" };
		      StringBuilder sb = new StringBuilder();
		      int action = event.getAction();
		      int actionCode = action & MotionEvent.ACTION_MASK;
		      sb.append("event ACTION_").append(names[actionCode]);
		      if (actionCode == MotionEvent.ACTION_POINTER_DOWN
		            || actionCode == MotionEvent.ACTION_POINTER_UP) {
		         sb.append("(pid ").append(
		               action >> MotionEvent.ACTION_POINTER_ID_SHIFT);
		         sb.append(")");
		      }
		      sb.append("[");
		      for (int i = 0; i < event.getPointerCount(); i++) {
		         sb.append("#").append(i);
		         sb.append("(pid ").append(event.getPointerId(i));
		         sb.append(")=").append((int) event.getX(i));
		         sb.append(",").append((int) event.getY(i));
		         if (i + 1 < event.getPointerCount())
		            sb.append(";");
		      }
		      sb.append("]");
		      Log.d(TAG, sb.toString());
		   }
		}

