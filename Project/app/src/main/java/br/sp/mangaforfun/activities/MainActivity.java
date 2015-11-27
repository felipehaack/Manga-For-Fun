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
import android.widget.LinearLayout;

import br.sp.mangaforfun.R;
import br.sp.mangaforfun.gestures.SlideLayerMangaWebView;
import br.sp.mangaforfun.webview.WebViewCustom;

public class MainActivity extends AppCompatActivity {

    private Button addManga;
    private WebViewCustom mangaWebView;
    private WebViewCustom serverWebView;
    private ProgressDialog progressDialog;
    private SlideLayerMangaWebView slideLayerMangaWebView;
    private LinearLayout layoutMangaWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        layoutMangaWebView = (LinearLayout) findViewById(R.id.layout_manga_webview);
        mangaWebView = (WebViewCustom) findViewById(R.id.manga_webview);
        serverWebView = (WebViewCustom) findViewById(R.id.server_webview);

        mangaWebView.loadUrl("file:///android_asset/Manga/index.html");
        serverWebView.loadUrl("file:///android_asset/Server/index.html");

        mangaWebView.configureForMain();
        serverWebView.configureForMain();

        slideLayerMangaWebView = new SlideLayerMangaWebView(layoutMangaWebView, this);

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

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        slideLayerMangaWebView.motionEvent(ev);

        if(slideLayerMangaWebView.translate)
            return true;

        return super.dispatchTouchEvent(ev);
    }
}
