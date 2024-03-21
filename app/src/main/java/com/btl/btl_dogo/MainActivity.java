package com.btl.btl_dogo;

import androidx.fragment.app.Fragment;

import com.btl.btl_dogo.base.BaseActivity;
import com.btl.btl_dogo.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    protected ActivityMainBinding getViewBinding() {
        return ActivityMainBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void createView() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener(OnItemSelectedBottomBar);
        openFragment(new HomeFragment(viewModel));

    }


    public void openFragment(Fragment fragment) {
        replaceFragment(fragment, R.id.layoutMain, true);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener OnItemSelectedBottomBar = item -> {
        switch (item.getItemId()) {
            case R.id.iconhome:
                hideKeyboard();
                openFragment(new HomeFragment(viewModel));
                return true;
            case R.id.ic_notification:
                openFragment(new NotifileFragment());
                hideKeyboard();

                return true;
            case R.id.ic_favorite:
                hideKeyboard();
                return true;
            case R.id.ic_person:
                hideKeyboard();
                openFragment(new ProfileFragment());
                return true;
        }
        return false;
    };

}