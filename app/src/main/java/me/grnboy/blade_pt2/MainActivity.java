package me.grnboy.blade_pt2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //

        setContentView(R.layout.activity_main);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BladeInterstitialView.class);

                startActivity(intent);
            }
        });

        WebView bannerWebView = new WebView(this);
        bannerWebView.setVerticalScrollbarOverlay(true);
        bannerWebView.setBackgroundColor(Color.BLUE);
        bannerWebView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        bannerWebView.setWebViewClient(new ImageWebViewClient());
        WebSettings ws = bannerWebView.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setSupportZoom(true);
        ws.setJavaScriptEnabled(true);
//        bannerWebView.addJavascriptInterface(new forJs(), "android");
        bannerWebView.loadUrl("http://i.microad.net/images/9384/1859018_1.png");
        LinearLayout ll = new LinearLayout(this);
        ll.addView(bannerWebView);
        setContentView(ll);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

}
