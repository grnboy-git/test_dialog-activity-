package me.grnboy.blade_pt2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.FrameLayout;


public class BladeInterstitialView extends Activity {

    private int frameColor;
    private int frameWidth;
    private int frameHeight;
    private int adWidth;
    private int adHeight;
    private int closeBtn;
    private FrameLayout adLayout;
    private Point adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.frameColor = -13421773;
        this.adLayout = new FrameLayout(getApplicationContext());
        this.adLayout.setBackgroundColor(-1724697805);
        this.adLayout.setForegroundGravity(17);
        setContentView(this.adLayout);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            this.adView = new Point();
            this.adView.x = this.adLayout.getWidth();
            this.adView.y = this.adLayout.getHeight();
            float density = getResources().getDisplayMetrics().density;
            WindowManager wm = getWindowManager();
            Display disp = wm.getDefaultDisplay();
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//                this.adWidth = ((int)(disp.getWidth() * density));
//                this.adHeight = ((int)(disp.getHeight() * density));
//            } else {
//                disp.getSize(adView);
//                this.adWidth = ((int)(adView.x * density));
//                this.adHeight = ((int)(adView.y * density));
//            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                this.adWidth = ((int)(320 * density));
                this.adHeight = ((int)(200 * density));
            } else {
                disp.getSize(adView);
                this.adWidth = ((int)(320 * density));
                this.adHeight = ((int)(200 * density));
            }
            Context context = getApplicationContext();
            WebView adWebView = new WebView(this);
//            adWebView.setLayoutParams((new ViewGroup.LayoutParams(320, 200)));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(320, 200);
            params.gravity = 17;
            adWebView.setLayoutParams(params);
            adWebView.loadUrl("http://www.google.co.jp/");
            FrameLayout frameView = setFrameView(context, density);
            this.adLayout.addView(frameView);
            this.adLayout.addView(adWebView);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_blade_interstitial_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private FrameLayout setFrameView(Context context,float density) {
        FrameLayout layout = new FrameLayout(context);
        this.frameWidth = ((int)((this.adWidth + 40.0F) * density));
        this.frameHeight = ((int)((this.adHeight + 40.0F) * density));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(this.frameWidth, this.frameHeight);

        params.gravity = 17;
        layout.setLayoutParams(params);
        layout.setBackgroundColor(this.frameColor);
        return layout;
    }

}
