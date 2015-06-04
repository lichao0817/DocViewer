package com.airwatch.docviewer;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airwatch.Buttons.ControlImageButton;
import com.airwatch.Filter.PageInputFilter;
import com.airwatch.View.PageViewer;
import com.airwatch.JsInterfaces.PageTextJsInterface;
import com.airwatch.Utils.ButtonUtils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DocViewerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DocViewerFragment} factory method to
 * create an instance of this fragment.
 */
public class DocViewerFragment extends Fragment {

    private PageViewer mPageViewer;
    private ControlImageButton mZoomInButton;
    private ControlImageButton mZoomOutButton;
    private ControlImageButton mFitToWidthButton;
    private ControlImageButton mFitToPageButton;
    private ControlImageButton mPrevPageButton;
    private ControlImageButton mNextPageButton;
    private TextView mPageText;
    private Toolbar mToolbar;
    private PageTextJsInterface mPageTextJsInterface;
    private int pageCount = 8;
    private int lastVisitPage = 0;
    private final String LAST_VISIT_PAGE = "last_visit_page";

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
        mPageViewer = (PageViewer) view.findViewById(R.id.main_page_viewer);
        setUpButtons(view);
    }

    public void setUpButtons(View view){
        // Refrence to all the buttons
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mZoomInButton = (ControlImageButton) view.findViewById(R.id.zoom_in_button);
        mZoomOutButton = (ControlImageButton) view.findViewById(R.id.zoom_out_button);
        mFitToWidthButton = (ControlImageButton) view.findViewById(R.id.fit_to_width_button);
        mFitToPageButton = (ControlImageButton) view.findViewById(R.id.fit_to_page_button);
        mPrevPageButton = (ControlImageButton) view.findViewById(R.id.prev_page_button);
        mNextPageButton = (ControlImageButton) view.findViewById(R.id.next_page_button);
        mPageText = (TextView) view.findViewById(R.id.page_number);
        // Set up buttons and tool bar
        ButtonUtils.setupZoomInButton(mZoomInButton, mPageViewer);
        ButtonUtils.setupZoomOutButton(mZoomOutButton, mPageViewer);
        ButtonUtils.setupFitToWidthButton(mFitToWidthButton, mFitToPageButton, mPageViewer);
        ButtonUtils.setupFitToPageButton(mFitToPageButton, mFitToWidthButton, mPageViewer);
        ButtonUtils.setupPrevPageButton(mPrevPageButton, mPageViewer);
        ButtonUtils.setupNextPageButton(mNextPageButton, mPageViewer);
        ButtonUtils.setupPageText(mPageText, mPageViewer, getActivity(), new PageInputFilter(pageCount));
        ButtonUtils.setupToolbar(mToolbar, mPageViewer);
        mPageTextJsInterface = new PageTextJsInterface(mPageText, pageCount);
        mPageViewer.addJavascriptInterface(mPageTextJsInterface, "Android");

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAST_VISIT_PAGE, mPageTextJsInterface.getCurrentPageNumber());
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
