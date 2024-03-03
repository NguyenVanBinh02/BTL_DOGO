package com.btl.btl_dogo.base;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class BaseActivity<V extends ViewBinding> extends AppCompatActivity {

    public SharedPreference preference;
public AppViewModel  viewModel;
    private static final String TAG = BaseActivity.class.getName();
    protected V binding;
    private boolean onFullscreen = false;
    private View decorView;
    private User users;

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewBinding();

        setContentView(binding.getRoot());
        viewModel= new ViewModelProvider(this).get(AppViewModel.class);
        preference= new SharedPreference(getApplicationContext());
        decorView = getWindow().getDecorView();
        createView();
    }

    public void getUserData(OnGetUser event) {
        String userID = preference.getString("UserID", "");

        FirebaseDatabase.getInstance().getReference("users")
                .child(userID)
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    User user = dataSnapshot.getValue(User.class);

                    if (user != null) {
                        // User data retrieved successfully
                        event.onGetSuccess(user);
                    } else {
                        // Handle the case where user is null
                        showToast("User data is null");
                    }
                })
                .addOnFailureListener(e -> showToast("Error: " + e.getMessage()));
    }


    public    interface  OnGetUser{
        void onGetSuccess(User us);
    }
    public String formatToCurrency(float value) {
        Locale locale = new Locale("vi", "VN"); // Set the Vietnamese locale
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(value);
    }

    public String formatTimes(Object timestamp) {
        if (timestamp instanceof Long) {
            long timestampLong = (Long) timestamp;
            Date date = new Date(timestampLong);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            return sdf.format(date);
        }
        return "";
    }

    public String getTimeNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public void showToast(String mess) {
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show();
    }

    protected abstract V getViewBinding();

    protected abstract void createView();

    protected int hideSystemBars() {
        return (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    protected void setFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController windowInsetsController = getWindow().getInsetsController();
            if (windowInsetsController != null) {
                windowInsetsController.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                windowInsetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    protected void showKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    protected void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getRootView().getWindowToken(), 0);
        }
    }

    protected void handleBackpress() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStackImmediate();
        }

        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }

    protected void addFragment(Fragment fragment, int viewId, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        transaction.add(viewId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected void addFragment(Fragment fragment, int viewId, boolean addToBackStack, boolean hideBottomBar) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        if (hideBottomBar) {
            findViewById(viewId).setVisibility(View.GONE);
        }
        transaction.add(viewId, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
    private boolean isShowPass = false;

    public void togglePasswordVisibility(EditText tv) {
        if (!isShowPass) {
            // Show password
            tv.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            isShowPass = true;
        } else {
            // Hide password
            tv.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            isShowPass = false;
        }

        tv.setSelection(tv.getText().length());
    }
    protected void replaceFragment(Fragment fragment, int viewId, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(viewId, fragment);
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.commit();
    }

    protected void showFullscreen(boolean on) {
        onFullscreen = on;
        if (on) {
            setFullscreen();
        }
    }
}
