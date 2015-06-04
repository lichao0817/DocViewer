package com.airwatch.Helpers;

import com.airwatch.View.PageViewer;

/**
 * Created by Chao on 6/3/15.
 */
public class LoadPageHelper {

    private PageViewer pageViewer;

    public LoadPageHelper(PageViewer pageViewer){
        this.pageViewer = pageViewer;
    }

    public void loadDoc(String fileName){

    }

    public boolean onError(){
        return false;
    }
    public String onPage(){
        return null;
    }
}
