package com.airwatch.docviewer;

import android.app.Activity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.airwatch.Filter.PageInputFilter;
import com.airwatch.interfaces.PageEditJsInterface;
import com.airwatch.interfaces.ToolbarJsInterface;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DocViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DocViewerFragment} factory method to
 * create an instance of this fragment.
 */
public class DocViewerFragment extends Fragment {

    private WebView mWebView;
    private ImageButton mZoomInButton;
    private ImageButton mZoomOutButton;
    private ImageButton mFitToWidthButton;
    private ImageButton mFitToPageButton;
    private ImageButton mPrevPageButton;
    private ImageButton mNextPageButton;
    private TextView mPageEditText;
    private Toolbar mToolbar;
    private int pageCount;

    private OnFragmentInteractionListener mListener;

    public DocViewerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_doc_viewer, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        mWebView = (WebView) view.findViewById(R.id.main_webview);
        mWebView.setVerticalScrollBarEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);

        mWebView.loadUrl("file:///android_asset/base/base.html");
        setUpButtons(view);
    }

    public void setUpButtons(View view){
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mWebView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int visible = mToolbar.getVisibility();
                if (visible == View.VISIBLE){
                    mToolbar.setVisibility(View.GONE);
                }
                else{
                    mToolbar.setVisibility(View.VISIBLE);
                }
            }
        });
        mZoomInButton = (ImageButton) view.findViewById(R.id.zoom_in_button);
        mZoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.zoomIn();
            }
        });
        mZoomOutButton = (ImageButton) view.findViewById(R.id.zoom_out_button);
        mZoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWebView.zoomOut();
            }
        });
        mFitToWidthButton = (ImageButton) view.findViewById(R.id.fit_to_width_button);
        mFitToWidthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mWebView.loadUrl("javascript:fitToWidth()");
                for (int i = 0; i < 10; i ++){
                    mWebView.zoomOut();
                }
            }
        });
        mFitToPageButton = (ImageButton) view.findViewById(R.id.fit_to_page_button);
        mFitToPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                mWebView.loadUrl("javascript:fitToPage()");
            }
        });
        mPrevPageButton = (ImageButton) view.findViewById(R.id.prev_page_button);
        mPrevPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("javascript:prevPage()");
            }
        });
        mNextPageButton = (ImageButton) view.findViewById(R.id.next_page_button);
        mNextPageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mWebView.loadUrl("javascript:nextPage()");
            }
        });
        mPageEditText = (TextView) view.findViewById(R.id.page_number);
        mPageEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Go To Page");
                final EditText input = new EditText(getActivity());
                input.setFilters(new PageInputFilter[]{new PageInputFilter(1, 8)});
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mWebView.loadUrl("javascript:goToPage(" + input.getText().toString() + ")");
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
        //TODO: Should read the total page number here
        //mPageEditText.setFilters(new InputFilter[]{new PageInputFilter("1", "8")});
        mWebView.addJavascriptInterface(new PageEditJsInterface(mPageEditText, 8), "Android");
        mWebView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int visibility = mToolbar.getVisibility();
                if ( motionEvent.getAction() == MotionEvent.ACTION_UP ){
                    if (visibility == View.VISIBLE ||
                            motionEvent.getAction() == MotionEvent.ACTION_SCROLL){
                        mToolbar.setVisibility(View.GONE);
                    }
                    else {
                        mToolbar.setVisibility(View.VISIBLE);
                    }
                }
                return false;
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
