package com.productions.jalebi;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ListItemFragment.OnFragmentInteractionListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private ActionBarDrawerToggle mActionBarDrawerToggle;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        // Used to set our hamburger icon
        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    }

    // Supporting hamburger icon
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mActionBarDrawerToggle.syncState();
    }

    // Supporting hamburger icon
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, FragmentGenerator.newInstance(position, getApplicationContext()))
                .commit();
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    public void updateTitle(String title) {
            mTitle = title;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // Implementing ListItemFragment.OnFragmentInteractionListener methods
    public void onListItemClick(String id) {
        // TODO: Redirect to another ListItemFragment containing sub menu
        // See http://developer.android.com/training/basics/fragments/communicating.html
        // for fragment interaction with activities
    }

    /**
     * FragmentGenerator used to create fragments corresponding to the Navigation Drawer List
     */
    public static class FragmentGenerator {

        /**
         * Returns the fragment corresponding to the listing in the navigation drawer list
         * 0 - Featured Sweets
         * 1 - Bakery Items
         * 2 - Checkout
         */
        public static Fragment newInstance(int navDrawerEntryNumber, Context context) {
            Fragment result;
            ArrayList<SweetShop> list = new ArrayList();
            Resources resources = context.getResources();
            switch(navDrawerEntryNumber) {
                case 0:
                    list.add(new SweetShop("Mantha Sweets", "1 Mi"));
                    list.add(new SweetShop("Gabbar Singh Bakery", "3 Mi"));
                    list.add(new SweetShop("Genelia Joint", "7 Mi"));
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section1), list);
                break;
                case 1:
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section2), null);
                break;
                case 2:
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section3), null);
                break;
                case 3:
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section4), null);
                break;
                case 4:
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section5), null);
                break;
                case 5:
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section6), null);
                break;
                case 6:
                    result = ListItemFragment.newInstance(resources.getString(R.string.title_section7), null);
                break;
                default:
                    // Default placeholder fragment that shows nothing
                    result = new PlaceholderFragment();
            }
            return result;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            return rootView;
        }
    }

}
