package com.airwatch.View;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * Created by Chao on 6/2/15.
 */
public class PageViewer extends WebView{

    private final String URL = "file:///android_asset/base/base.html";

    public PageViewer(Context context) {
        super(context);
        setup();
    }

    public PageViewer(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public PageViewer(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup(){
        this.setVerticalScrollBarEnabled(true);
        this.getSettings().setJavaScriptEnabled(true);
        this.getSettings().setBuiltInZoomControls(true);
        this.getSettings().setDisplayZoomControls(false);
        this.loadUrl(URL);
    }

    public void goToPage(int pageNumber){
        this.loadUrl("javascript:goToPage("+ pageNumber +")");
    }

    public void goToPage(String pageNumber){
        this.loadUrl("javascript:goToPage("+ pageNumber +")");
    }

    public void prevPage(){
        this.loadUrl("javascript:prevPage()");
    }

    public void nextPage(){
        this.loadUrl("javascript:nextPage()");
    }

    public void fitToPage(){
        this.loadUrl("javascript:fitToPage()");
    }

    public void fitToWidth(){
        this.loadUrl("javascript:fitToWidth()");
    }

}
