package passionfive.welcometravel.util;

import android.content.Context;

public class DpToPx {
	
	public static int convert(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
