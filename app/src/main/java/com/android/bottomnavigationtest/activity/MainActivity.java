package com.android.bottomnavigationtest.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.bottomnavigationtest.R;
import com.android.bottomnavigationtest.fragment.HomeFragment;
import com.android.bottomnavigationtest.fragment.SearchFragment;
import com.android.bottomnavigationtest.fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment = HomeFragment.newInstance();
    private SearchFragment searchFragment = SearchFragment.newInstance();
    private SettingFragment settingFragment = SettingFragment.newInstance();

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private Fragment active = homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.nav_bottomnavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fragmentManager.beginTransaction().add(R.id.container, homeFragment).commit();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                showFragment(homeFragment);
                return true;
            case R.id.search:
                showFragment(searchFragment);
                return true;
            case R.id.setting:
                showFragment(settingFragment);
                return true;

        }
        return false;
    }


    private void showFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

        if (fragmentManager.getFragments().contains(fragment)) {
            fragmentTransaction.hide(active).show(fragment).commit();
        }
        else {
            fragmentTransaction.add(R.id.container, fragment).hide(active).commit();
        }
        active = fragment;
    }

    @Override
    public void onBackPressed() {

        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null && fragment.isVisible()) {
                if (fragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
                    fragment.getChildFragmentManager().popBackStack();
                    return;
                }
                else if (!(fragment instanceof HomeFragment)) {
                    bottomNavigationView.setSelectedItemId(R.id.home);
                    return;
                }
            }
        }
        super.onBackPressed();
    }

}
