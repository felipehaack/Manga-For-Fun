package br.sp.mangaforfun.webview;

import android.content.Context;
import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class CustomWebView extends WebView {

    public CustomWebView(Context context) {

        super(context);

        WebSettings settings = getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        settings.setSupportZoom(true);

        settings.setBlockNetworkImage(true);
        settings.setLoadsImagesAutomatically(false);

        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(false);

        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);

        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(false);

        settings.setUserAgentString("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            WebView.setWebContentsDebuggingEnabled(true);

        setWebViewClient(new DownloadWebViewClient());
        setWebChromeClient(new WebChromeClient());
    }
}
