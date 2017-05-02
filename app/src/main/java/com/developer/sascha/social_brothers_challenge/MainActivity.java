package com.developer.sascha.social_brothers_challenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.developer.sascha.social_brothers_challenge.fragments.CardFragment;
import com.developer.sascha.social_brothers_challenge.fragments.FreeFragment;
import com.developer.sascha.social_brothers_challenge.fragments.ListFragment;

public class MainActivity extends AppCompatActivity {
    private CardFragment mCardFragment = null;
    private ListFragment mListFragment = null;
    private FreeFragment mFreeFragment = null;
    private TextView mTextMessage;
    private Toolbar  mToolbar;
    private FragmentManager mFragmentManager;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_card:
                    if(mCardFragment == null)
                        mCardFragment = new CardFragment();
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container,mCardFragment).commit();
                    mToolbar.setTitle(R.string.title_card);
                    return true;
                case R.id.navigation_list:
                    if(mListFragment==null)
                        mListFragment = new ListFragment();
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container,mListFragment).commit();
                    mToolbar.setTitle(R.string.title_list);
                    return true;
                case R.id.navigation_free:
                    if(mFreeFragment == null)
                        mFreeFragment = new FreeFragment();
                    mFragmentManager.beginTransaction().replace(R.id.fragment_container,mFreeFragment).commit();
                    mToolbar.setTitle(R.string.title_free);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar     = (Toolbar)  findViewById(R.id.main_toolbar);
             setSupportActionBar(mToolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fragment_container,new CardFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
