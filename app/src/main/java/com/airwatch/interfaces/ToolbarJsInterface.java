package com.airwatch.interfaces;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JavascriptInterface;

/**
 * Created by Chao on 6/2/15.
 */
public class ToolbarJsInterface {
    private Toolbar toolbar;

    public ToolbarJsInterface(Toolbar toolbar){
        this.toolbar = toolbar;
    }

    @JavascriptInterface
    public void tapView() {
        int visibility = toolbar.getVisibility();
        if (visibility == View.VISIBLE){
            toolbar.setVisibility(View.GONE);
        }
        else{
            toolbar.setVisibility(View.VISIBLE);
        }
    }
}
