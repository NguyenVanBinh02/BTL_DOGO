package com.btl.btl_dogo;

import android.annotation.SuppressLint;

import androidx.fragment.app.Fragment;

import com.btl.btl_dogo.base.BaseActivity;
import com.btl.btl_dogo.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.btl.btl_dogo.R;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void createView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(OnItemSelectedBottomBar);
    }


    public void openFragment(Fragment fragment, boolean value) {
        replaceFragment(fragment, R.id.layoutMain, true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener OnItemSelectedBottomBar = item -> {
        switch (item.getItemId()) {
            case R.id.iconhome:
                hideKeyboard();


                return true;
            case R.id.ic_notification:
                hideKeyboard();

                return true;
            case R.id.ic_favorite:
                hideKeyboard();
                return true;
            case R.id.ic_person:
                hideKeyboard();
                return true;
        }
        return false;
    };

}