package com.airwatch.JsInterfaces;

import android.webkit.JavascriptInterface;
import android.widget.TextView;

/**
 * Created by Chao on 5/26/15.
 */
public class PageTextJsInterface {
    private TextView mTextView;
    private int currentPageNumber;
    private int pageCount;

    public PageTextJsInterface(TextView textView, int pageCount) {
        mTextView = textView;
        this.pageCount = pageCount;
    }

    @JavascriptInterface
    public void updatePageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
        mTextView.setText("" + currentPageNumber + " / " + pageCount);
    }

    public int getCurrentPageNumber(){
        return currentPageNumber;
    }
}
