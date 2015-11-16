package br.sp.mangaforfun.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import br.sp.mangaforfun.R;

public class MainActivity extends AppCompatActivity {

    private Button addManga;
    private WebView webView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview_main);

        webView.setVisibility(View.GONE);

        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        if (Build.VERSION.SDK_INT >= 19)
            webView.setWebContentsDebuggingEnabled(true);

        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressDialog = new ProgressDialog(MainActivity.this);

                progressDialog.setCancelable(false);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Carregando...");
                progressDialog.setTitle(null);

                progressDialog.show();

                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                webView.setVisibility(View.VISIBLE);

                view.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        progressDialog.dismiss();
                    }
                }, 1500);

                super.onPageFinished(view, url);
            }
        });

        webView.loadUrl("file:///android_asset/Html/MainScreen/main.html");



        addManga = (Button) findViewById(R.id.add_manga_button);

        addManga.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                switch (motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN: {

                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.add_manga_button);

                        addManga.startAnimation(animation);

                        break;
                    }

                    case MotionEvent.ACTION_UP: {

                        addManga.clearAnimation();

                        Intent i = new Intent(MainActivity.this, DownloadActivity.class);
                        startActivity(i);

                        break;
                    }
                }

                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
