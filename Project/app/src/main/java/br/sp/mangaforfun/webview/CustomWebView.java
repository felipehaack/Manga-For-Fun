package br.sp.mangaforfun.webview;

import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CustomWebView extends WebView {

    public CustomWebView(Context context) {

        super(context);

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

        setOverScrollMode(OVER_SCROLL_NEVER);
        setScrollBarStyle(SCROLLBARS_OUTSIDE_OVERLAY);
        setDrawingCacheQuality(DRAWING_CACHE_QUALITY_HIGH);

        if (Build.VERSION.SDK_INT >= 17)
            settings.setMediaPlaybackRequiresUserGesture(false);

        if (Build.VERSION.SDK_INT >= 19)
            WebView.setWebContentsDebuggingEnabled(true);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        settings.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");

        setWebViewClient(new DownloadWebViewClient());
        setWebChromeClient(new WebChromeClient());
    }

    private void scroll() {

        int temp_ScrollX = getScrollX();
        int temp_ScrollY = getScrollY();

        scrollTo(temp_ScrollX - 1, getScrollY() + 1);
        scrollTo(temp_ScrollX, temp_ScrollY);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return super.onInterceptTouchEvent(ev);
    }

    /**
     * Where: WebViewAdmagInterface and ViewParent
     * Because: some ADs use drag element, and, if it doesn't was implemented then the page will be swipe...
     * ...WebViewAdmagInterface disable swipe to the ViewParent after detect preventDefault and release it when detect any finger on the surface
     *
     * @param event
     * @return
     */
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
