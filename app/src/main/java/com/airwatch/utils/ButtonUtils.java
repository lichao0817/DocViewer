package com.airwatch.Utils;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.airwatch.Buttons.ControlImageButton;
import com.airwatch.Filter.PageInputFilter;
import com.airwatch.View.PageViewer;

/**
 * Created by Chao on 5/26/15.
 */
public class ButtonUtils {
    public static void setupZoomInButton(final ControlImageButton zoomInButton, final PageViewer pageViewer){
        zoomInButton.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    zoomInButton.onClick();
                    pageViewer.zoomIn();
                }
                else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    zoomInButton.onRelease();
                }
                return false;
            }
        });
    }

    public static void setupZoomOutButton(final ControlImageButton zoomOutButton, final PageViewer pageViewer){
        zoomOutButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    zoomOutButton.onClick();
                    pageViewer.zoomOut();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    zoomOutButton.onRelease();
                }
                return false;
            }
        });
    }
    public static void setupFitToWidthButton(final ControlImageButton fitToWidthButton, final ControlImageButton fitToPageButton, final PageViewer pageViewer){
        fitToWidthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageViewer.fitToWidth();
                fitToWidthButton.onClick();
                fitToPageButton.onRelease();
                for (int i = 0; i < 10; i++) {
                    pageViewer.zoomOut();
                }
            }
        });
    }
    public static void setupFitToPageButton(final ControlImageButton fitToPageButton, final ControlImageButton fitToWidthButton, final PageViewer pageViewer){
        fitToPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageViewer.fitToPage();
                fitToPageButton.onClick();
                fitToWidthButton.onRelease();
            }
        });
    }
    public static void setupPrevPageButton(final ControlImageButton prevPageButton, final PageViewer pageViewer){
        prevPageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    prevPageButton.onClick();
                    pageViewer.prevPage();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    prevPageButton.onRelease();
                }
                return false;
            }
        });
    }
    public static void setupNextPageButton(final ControlImageButton nextPageButton, final PageViewer pageViewer){
        nextPageButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    nextPageButton.onClick();
                    pageViewer.nextPage();
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    nextPageButton.onRelease();
                }
                return false;
            }
        });
    }
    public static void setupPageText(final TextView pageText, final PageViewer pageViewer, final Context context, final PageInputFilter inputFilter){
        pageText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Go To Page");
                final EditText input = new EditText(context);
                input.setFilters(new PageInputFilter[]{inputFilter});
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pageViewer.goToPage(input.getText().toString());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });
    }
    public static void setupToolbar(final Toolbar toolbar, final PageViewer pageViewer){
        pageViewer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int visibility = toolbar.getVisibility();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (visibility == View.VISIBLE ||
                            motionEvent.getAction() == MotionEvent.ACTION_SCROLL) {
                        toolbar.setVisibility(View.GONE);
                    } else {
                        toolbar.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
    }
}
