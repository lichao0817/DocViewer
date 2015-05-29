package com.airwatch.interfaces;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Chao on 5/26/15.
 */
public class JsInterface {
    private Context mContext;
    private int currentPageNumber;

    public JsInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void updatePageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public int getCurrentPageNumber(){
        return currentPageNumber;
    }
}
