package com.twentytwoseven.android.bitxandroidsampleapp;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import com.twentytwoseven.android.bitx.BitXClient;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String PREF_API_KEY_ID = "apiKeyId";
    public static final String PREF_API_KEY_SECRET = "apiKeySecret";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private BitXClient mBitX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
            R.id.navigation_drawer,
            (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position, String item) {
        String cName = item.replaceAll(" ", "");
        String viewClassName = "com.twentytwoseven.android.bitxandroidsampleapp." + cName + "Fragment";
        Fragment fragment = null;
        try {
            fragment = (Fragment) Class.forName(viewClassName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit();
        mTitle = item;
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_set_api_credentials) {
            new SetApiCredentialsDialog()
                .show(getFragmentManager(), SetApiCredentialsDialog.TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public BitXClient getBitXClient() {
        String keyId = getPreferences(MODE_PRIVATE).getString(PREF_API_KEY_ID, null);
        String keySecret = getPreferences(MODE_PRIVATE).getString(PREF_API_KEY_SECRET, null);
        if (TextUtils.isEmpty(keyId) || TextUtils.isEmpty(keySecret)) {
            mBitX = new BitXClient();
        } else {
            mBitX = new BitXClient(keyId, keySecret);
        }
        return mBitX;
    }
}
