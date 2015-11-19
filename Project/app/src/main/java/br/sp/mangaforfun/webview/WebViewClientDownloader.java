package br.sp.mangaforfun.webview;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewClientDownloader extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        if(url.contains("MangaForFun")){

            return true;
        }

        return false;
    }

    public void onPageFinished(WebView view, String url) {

        view.loadUrl("javascript:window.setTimeout(function(){var e='',t='',a='',n='',m=new Array,g=new Array;try{e=document.getElementById('mangaproperties').getElementsByTagName('h1')[0].innerHTML.indexOf('Manga')>-1?!0:!1}catch(r){e=!1}if(e){t=document.getElementById('mangaproperties').getElementsByTagName('h1')[0].innerHTML;try{a=document.getElementById('mangaimg').getElementsByTagName('img')[0].src}catch(r){}try{n=document.getElementById('readmangasum').getElementsByTagName('p')[0].innerHTML}catch(r){}try{for(var y=document.getElementById('chapterlist').getElementsByTagName('table')[0].getElementsByTagName('tbody')[0].getElementsByTagName('tr'),c=1;c<y.length;++c)_chapter=y[c].getElementsByTagName('td')[0].getElementsByTagName('a')[0],m.push(_chapter.innerHTML),g.push(_chapter.href)}catch(r){}}MangaForFunInterface.setManga(e.toString(),t,a,n,m.join(' , '),g.join(' , '))},10);");
    }
}