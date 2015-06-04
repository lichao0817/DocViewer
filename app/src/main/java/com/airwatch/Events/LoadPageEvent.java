package com.airwatch.Events;

/**
 * Created by Chao on 6/3/15.
 */
public class LoadPageEvent {
    public final int ON_ERROR = 1;
    public final int ON_PWD = 2;
    public final int ON_PAGE = 3;

    private int type;
    private String image;

    public LoadPageEvent(int type, String image){
        this.type = type;
        this.image = image;
    }

    public LoadPageEvent(int type){
        this.type = type;
        this.image = "";
    }

    public int getType(){
        return this.type;
    }

    public String getImage(){
        return this.image;
    }
}
