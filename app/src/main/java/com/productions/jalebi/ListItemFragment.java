package com.productions.jalebi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
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

import java.util.ArrayList;


/**
 * A fragment representing a list of Items.
 * <p/>
 * Large screen devices (such as tablets) are supported by replacing the ListView
 * with a GridView.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class ListItemFragment extends Fragment implements AbsListView.OnItemClickListener {
    private static final String ARG_TITLE = "title";
    private static final String ARG_DATA = "data";

    private String mTitle;
    private ArrayList<Store> mData;

    private OnFragmentInteractionListener mListener;

    /**
     * The fragment's ListView/GridView.
     */
    private AbsListView mListView;

    /**
     * The Adapter which will be used to populate the ListView/GridView with
     * Views.
     */
    private ListAdapter mAdapter;

    // TODO: Rename and change types of parameters
    public Fragment newInstance(int navDrawerEntryNumber, Context context) {
        Resources resources = context.getResources();
        Fragment fragment;
        Bundle args = new Bundle();

        switch(navDrawerEntryNumber) {
            case 0:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section1));
                args.putParcelableArrayList(ARG_DATA, new ArrayList<Store>());
                break;
            case 1:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section2));
                args.putParcelableArrayList(ARG_DATA, null);
                break;
            case 2:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section3));
                args.putParcelableArrayList(ARG_DATA, null);
                break;
            case 3:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section4));
                args.putParcelableArrayList(ARG_DATA, null);
                break;
            case 4:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section5));
                args.putParcelableArrayList(ARG_DATA, null);
                break;
            case 5:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section6));
                args.putParcelableArrayList(ARG_DATA, null);
                break;
            case 6:
                fragment = new ListItemFragment();
                args.putString(ARG_TITLE, resources.getString(R.string.title_section7));
                args.putParcelableArrayList(ARG_DATA, null);
                break;
            default:
                fragment = new HomeActivity.PlaceholderFragment();
        }

        fragment.setArguments(args);
        return fragment;
    }

    private void handleData(String url, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseJSONResponse(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // TODO: Proper error messaging
                error.printStackTrace();
            }
        });
        queue.add(jsonRequest);
    }

    private void parseJSONResponse(JSONArray response) {
        try {
            if (response.length() != 0) {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject obj = (JSONObject) response.get(i);
                    // TODO: Update location information
                    Store store = new Store(obj.getInt("id"), obj.getString("name"), "1MI");
                    mData.add(store);
                    ((BaseAdapter) mListView.getAdapter()).notifyDataSetChanged();
                }
            } else {
                setEmptyText(getResources().getText(R.string.stores_list_empty));
            }
        } catch (JSONException e) {
            // TODO: Proper exception handling + error messaging
            e.printStackTrace();
        }
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ListItemFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            mData = getArguments().getParcelableArrayList(ARG_DATA);
            ((HomeActivity) getActivity()).updateTitle(mTitle);
        } else {
            mTitle = "";
            mData = new ArrayList();
        }

        if (mData != null) {
            mAdapter = new ArrayAdapter(getActivity(),
                    android.R.layout.simple_list_item_2, android.R.id.text1, mData) {

                public View getView(int position, View view, ViewGroup parent) {
                    View v = super.getView(position, view, parent);
                    TextView text1 = (TextView) v.findViewById(android.R.id.text1);
                    TextView text2 = (TextView) v.findViewById(android.R.id.text2);
                    text1.setText(mData.get(position).getStoreName());
                    text2.setText(mData.get(position).getLocation());
                    text2.setAllCaps(true);
                    text2.setTypeface(null, Typeface.ITALIC);
                    return v;
                }
            };

            handleData(HomeActivity.BASE_URL + "stores", getActivity().getApplicationContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listitem, container, false);

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);

        // Set listview empty view
        mListView.setEmptyView(view.findViewById(android.R.id.empty));

        return view;
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


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Pass the next URL
            mListener.onListItemClick(HomeActivity.BASE_URL + "stores/" + mData.get(position).getStoreId() + "/menu_sections");
        }
    }

    /**
     * The default content for this Fragment has a TextView that is shown when
     * the list is empty. If you would like to change the text, call this method
     * to supply the text it should use.
     */
    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
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
        public void onListItemClick(String requestUrl);
    }


}
