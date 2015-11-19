package com.productions.jalebi;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ItemViewFragment extends Fragment {

    private static Item selectedItem;
    private OnFragmentInteractionListener mListener;
    private static final String ARG_SELECTED_ITEM = "selectedItem";

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param selectedItem
     *      Item that was selected in the previous Items list.
     * @return
     *      A new instance of fragment ItemViewFragment.
     */
    public static ItemViewFragment newInstance(Item selectedItem) {
        ItemViewFragment fragment = new ItemViewFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_SELECTED_ITEM, selectedItem);
        fragment.setArguments(args);
        return fragment;
    }

    public ItemViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedItem = getArguments().getParcelable(ARG_SELECTED_ITEM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_item_view, container, false);
        if (selectedItem != null) {
            TextView nameTextView = (TextView) v.findViewById(R.id.item_view_name);
            nameTextView.setText(selectedItem.getName());

            TextView priceTextView = (TextView) v.findViewById(R.id.item_view_price);
            priceTextView.setText("Rs-/ " + selectedItem.getPrice() + "/" + selectedItem.getPriceUnit());

            TextView descTextView = (TextView) v.findViewById(R.id.item_view_description);
            descTextView.setText(selectedItem.getDescription());
        }

        return v;
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
        public void onItemViewInteraction(Uri uri);
    }

}
