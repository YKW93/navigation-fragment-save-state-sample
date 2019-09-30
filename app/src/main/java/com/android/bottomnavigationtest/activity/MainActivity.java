package com.android.bottomnavigationtest.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.bottomnavigationtest.R;
import com.android.bottomnavigationtest.fragment.HomeFragment;
import com.android.bottomnavigationtest.fragment.SearchFragment;
import com.android.bottomnavigationtest.fragment.SettingFragment;
import com.android.bottomnavigationtest.view.CustomViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private CustomViewPager viewPager;


    private HomeFragment homeFragment = HomeFragment.newInstance();
    private SearchFragment searchFragment = SearchFragment.newInstance();
    private SettingFragment settingFragment = SettingFragment.newInstance();

    private Fragment active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_bottomnavigation);
        viewPager = findViewById(R.id.view_pager);
        setBottomNavigationView();
        setViewPager();
    }

    private void setViewPager() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        fragmentAdapter.add(homeFragment);
        fragmentAdapter.add(searchFragment);
        fragmentAdapter.add(settingFragment);
        viewPager.setAdapter(fragmentAdapter);
        viewPager.disableScroll(true);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
    }

    private void setBottomNavigationView() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                viewPager.setCurrentItem(0,false);
                active = homeFragment;
                return true;
            case R.id.search:
                viewPager.setCurrentItem(1, false);
                active = searchFragment;
                return true;
            case R.id.setting:
                viewPager.setCurrentItem(2, false);
                active = settingFragment;
                return true;

        }
        return false;
    }

    @Override
    public void onBackPressed() {

        if (active.getChildFragmentManager().getBackStackEntryCount() > 0) {
            active.getChildFragmentManager().popBackStack();
            return;
        }

        else if (!(active instanceof HomeFragment)) {
            bottomNavigationView.setSelectedItemId(R.id.home);
            return;
        }

        super.onBackPressed();
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        public void add(Fragment fragment) {
            fragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

}
