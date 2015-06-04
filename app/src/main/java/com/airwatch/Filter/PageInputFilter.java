package com.airwatch.Filter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Chao on 5/28/15.
 */
public class PageInputFilter implements InputFilter{

    private int max;

    public PageInputFilter(int max){
        this.max = max;
    }

    public PageInputFilter(String min, String max) {
        this.max = Integer.parseInt(max);
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(1, max, input))
                return null;
        } catch (NumberFormatException nfe) {

        }
        return "";
    }
}
