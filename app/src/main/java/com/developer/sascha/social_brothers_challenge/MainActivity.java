package com.developer.sascha.social_brothers_challenge;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.developer.sascha.social_brothers_challenge.fragments.CardFragment;
import com.developer.sascha.social_brothers_challenge.fragments.FreeFragment;
import com.developer.sascha.social_brothers_challenge.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {
    private CardFragment mCardFragment = null;
    private ListFragment mListFragment = null;
    private FreeFragment mFreeFragment = null;
    private Toolbar  mToolbar;
    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private String mOldTitle;
    private int mCurrentFragment;

    private void switchFragments(int id) {

        mDrawerLayout.closeDrawer(Gravity.START);
        mCurrentFragment = id;
        switch (id) {
            case R.id.navigation_card:
                if (mCardFragment == null)
                    mCardFragment = new CardFragment();
                mFragmentManager.beginTransaction().replace(R.id.fragment_container, mCardFragment).commit();
                mToolbar.setTitle(R.string.title_card);
                mOldTitle = getString(R.string.title_card);
                break;
            case R.id.navigation_list:
                if (mListFragment == null)
                    mListFragment = new ListFragment();
                mFragmentManager.beginTransaction().replace(R.id.fragment_container, mListFragment).commit();
                mToolbar.setTitle(R.string.title_list);
                mOldTitle = getString(R.string.title_list);
                break;
            case R.id.navigation_free:
                if (mFreeFragment == null)
                    mFreeFragment = new FreeFragment();
                mFragmentManager.beginTransaction().replace(R.id.fragment_container, mFreeFragment).commit();
                    mToolbar.setTitle(R.string.title_free);
                mOldTitle = getString(R.string.title_free);
                break;
        }
        mCurrentFragment = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar     = (Toolbar)  findViewById(R.id.main_toolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_drawer);
        setSupportActionBar(mToolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (getSupportActionBar() != null && getSupportActionBar().getTitle() != null) {
                    mToolbar.setNavigationIcon(R.drawable.ic_back_arrow);
                    mOldTitle = getSupportActionBar().getTitle().toString();
                    getSupportActionBar().setTitle(R.string.drawer_open);
                }
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(mOldTitle);
                    mToolbar.setNavigationIcon(R.drawable.ic_drawer);
                }
            }
        };
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchFragments(item.getItemId());
                return true;
            }
        });
        final NavigationView drawer = (NavigationView) findViewById(R.id.drawer);
        drawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switchFragments(item.getItemId());
                drawer.setCheckedItem(item.getItemId());
                return true;
            }
        });
        mDrawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState != null) {
            switchFragments(savedInstanceState.getInt("currentFragment"));
        } else {
            mFragmentManager.beginTransaction().replace(R.id.fragment_container, new CardFragment()).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("currentFragment", mCurrentFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu,menu);
        return true;
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        mActionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mActionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return mActionBarDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
