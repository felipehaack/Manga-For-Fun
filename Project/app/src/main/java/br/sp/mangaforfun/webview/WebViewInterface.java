package br.sp.mangaforfun.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebViewInterface {

    private Context context;

    WebViewInterface(Context context){

        this.context = context;
    }

    @JavascriptInterface
    public void enableSelectAll(){


    }

    @JavascriptInterface
    public void disableSelectAll(){


    }
}
