package me.grnboy.blade_pt2;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;


public class BladeInterstitialView extends Activity {

    private int frameColor;
    private int frameWidth;
    private int frameHeight;
    private int adWidth;
    private int adHeight;
    private int closeBtnSize;
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
            this.closeBtnSize = ((int)(36.0F * density));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                this.adWidth = ((int)(300 * density));
                this.adHeight = ((int)(250 * density));
            } else {
                disp.getSize(adView);
                this.adWidth = ((int)(300 * density));
                this.adHeight = ((int)(250 * density));
            }
            Context context = getApplicationContext();
            WebView adWebView = new WebView(this);
//            adWebView.setLayoutParams((new ViewGroup.LayoutParams(300, 250)));
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int)convertDpToPixel(300f,context), (int)convertDpToPixel(250f,context));
            params.gravity = 17;
            adWebView.setLayoutParams(params);
            adWebView.loadUrl("http://www.google.co.jp/");
            FrameLayout frameView = setFrameView(context, density);
            Button closeBtn = setCloseBtn(context, density);
            DrawCross drawCross = new DrawCross(this);
//            drawCross.setLayoutParams();

            this.adLayout.addView(frameView);
            this.adLayout.addView(closeBtn);

            this.adLayout.addView(adWebView);
            this.adLayout.addView(drawCross);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


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

    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * (metrics.densityDpi /160f);
        return px;
    }

    private FrameLayout setFrameView(Context context,float density) {
        FrameLayout layout = new FrameLayout(context);
        this.frameWidth = ((int)(this.adWidth + 40.0F * density));
        this.frameHeight = ((int)(this.adHeight + 40.0F * density));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(this.frameWidth, this.frameHeight);

        params.gravity = 17;
        layout.setLayoutParams(params);
        layout.setBackgroundColor(this.frameColor);
        return layout;
    }

    private Button setCloseBtn(Context context, float density) {
        Button closeBtn = new Button(context);
        GradientDrawable shape = new GradientDrawable();
        shape.setStroke(4, Color.parseColor("#FF0000FF"));
//        shape.setCornerRadius(40);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            closeBtn.setBackgroundDrawable(shape);
        } else {
            closeBtn.setBackground(shape);
        }

//        closeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//        StateListDrawable sld = (StateListDrawable)closeBtn.getBackground();
//        GradientDrawable shape = (GradientDrawable)closeBtn.getBackground();
//        shape.setColor(Color.WHITE);
//        shape.setStroke(3, Color.parseColor("#9acd32"));
//        adLayout.addView(closeBtn);

        int rectX = (this.adView.x - this.frameWidth) / 2 +
                this.frameWidth - (this.closeBtnSize - this.closeBtnSize / 4);
        int rectY = (this.adView.y - this.frameHeight) / 2 - this.closeBtnSize / 2;
        if (this.frameHeight + this.closeBtnSize * 2 > this.adView.y) {
            rectY = (this.adView.y - this.adHeight) / 2;
            rectX = this.adWidth + (this.adView.x - this.adWidth) / 2;
        }
        else if (this.adWidth + this.closeBtnSize * 2 > this.adView.x) {
            rectX = this.adView.x - this. closeBtnSize;
        }
        shape.setCornerRadius(rectX/2);
        FrameLayout.LayoutParams closeParames = new FrameLayout.LayoutParams(this.closeBtnSize, this.closeBtnSize);
        closeParames.setMargins(rectX - 50, rectY, 0, 0);
        closeParames.gravity = 48;


        closeBtn.setOnClickListener(this.closeBtn);
        closeBtn.setLayoutParams(closeParames);

//        byte[] data = FluctDbAccess.getCloseButtonImage(context);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
//        closeBtn.setImageBitmap(bitmap);
//        closeBtn.setOnClickListener(this.mCloseButton);
//        this.mBitmapArray.add(bitmap);
//        this.mImageArray.add(closeBtn);
        return closeBtn;
    }

    public class DrawCross extends View {
        public DrawCross(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.argb(255, 255, 0, 255));
            int rectX = (adView.x - frameWidth) / 2 +
                    frameWidth - (closeBtnSize - closeBtnSize / 4);
            int rectY = (adView.y - frameHeight) / 2 - closeBtnSize / 2;
            if (frameHeight + closeBtnSize * 2 > adView.y) {
                rectY = (adView.y - adHeight) / 2;
                rectX = adWidth + (adView.x - adWidth) / 2;
            }
            else if (adWidth + closeBtnSize * 2 > adView.x) {
                rectX = adView.x - closeBtnSize;
            }

            Rect rect1 = new Rect(rectX,rectY + 30,rectX + 20,rectY + 100);

            canvas.translate(rectX+10,rectY+65);
            canvas.rotate(45);
            canvas.translate(-rectX-10,-rectY-65);
            canvas.drawRect(rect1, paint);

            Rect rect2 = new Rect(rectX,rectY + 30,rectX + 20,rectY + 100);

            canvas.translate(rectX+10,rectY+65);
            canvas.rotate(-90);
            canvas.translate(-rectX-10,-rectY-65);

//            canvas.translate(rectX+10,rectY+65);
//            canvas.rotate(90);
//            canvas.translate(-rectX-10,-rectY-65);
            canvas.drawRect(rect2, paint);

//            paint.setStyle(Paint.Style.STROKE);
//            paint.setStrokeWidth(12);
//            canvas.drawRect(10, 50, 30, 80, paint);
        }
    }

    Button.OnClickListener closeBtn = new Button.OnClickListener() {
        public void onClick(View view) {
            BladeInterstitialView.this.finish();
        }
    };

}
