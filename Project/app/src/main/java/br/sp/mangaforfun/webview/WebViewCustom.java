package br.sp.mangaforfun.webview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class WebViewCustom extends WebView {

    public WebViewCustom(Context context) {

        super(context);
    }

    public WebViewCustom(Context context, AttributeSet attributeSet) {

        super(context, attributeSet);

        WebSettings settings = getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(settings.LOAD_NO_CACHE);

        setInitialScale(1);
        setSaveEnabled(true);
        setDrawingCacheEnabled(true);
        setFocusable(false);

        setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);

        if (Build.VERSION.SDK_INT >= 17)
            settings.setMediaPlaybackRequiresUserGesture(false);

        if (Build.VERSION.SDK_INT >= 19)
            WebView.setWebContentsDebuggingEnabled(true);

        setLayerType(View.LAYER_TYPE_HARDWARE, null);
    }

    public void configureForMain(){

        if(Build.VERSION.SDK_INT >= 17)
            addJavascriptInterface(new WebViewInterface(getContext()), "WebViewInterface");
    }

    public void configureForDownload(){

        getSettings().setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");

        setWebViewClient(new WebViewClientDownloader());
        setWebChromeClient(new WebChromeClient());
    }

    private void scroll() {

        int temp_ScrollX = getScrollX();
        int temp_ScrollY = getScrollY();

        scrollTo(temp_ScrollX - 1, getScrollY() + 1);
        scrollTo(temp_ScrollX, temp_ScrollY);
    }

    @Override
    public boolean performLongClick() {

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN: {

                scroll();
            }
        }

        return super.onTouchEvent(event);
    }
}
