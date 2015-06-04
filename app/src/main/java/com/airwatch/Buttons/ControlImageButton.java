package com.airwatch.Buttons;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.airwatch.docviewer.R;

/**
 * Created by Chao on 6/2/15.
 */
public class ControlImageButton extends ImageButton {
    public ControlImageButton(Context context) {
        super(context);
    }
    public ControlImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ControlImageButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onClick(){
        super.setBackgroundResource(R.color.AWDarkBlue);
    }

    public void onRelease(){
        super.setBackgroundResource(R.color.AWBlue);
    }
}
