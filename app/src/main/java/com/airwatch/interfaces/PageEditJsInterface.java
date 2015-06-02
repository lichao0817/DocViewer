package com.airwatch.interfaces;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Chao on 5/26/15.
 */
public class PageEditJsInterface {
    private TextView mTextView;
    private int currentPageNumber;
    private int pageCount;

    public PageEditJsInterface(TextView textView, int pageCount) {
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
