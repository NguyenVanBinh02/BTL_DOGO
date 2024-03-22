package com.btl.btl_dogo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.btl.btl_dogo.model.Product;
import com.btl.btl_dogo.model.User;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public abstract class BaseFragment<T extends ViewBinding> extends Fragment {

    protected T binding;
    private OnBackPressedCallback callback;

    public void handlerBackPressed() {
    }
public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public void showToast(String mess) {
        Toast.makeText(this.requireContext(), mess, Toast.LENGTH_LONG).show();
    }

    public User userLogin= null;
    public void getUserData(BaseActivity.OnGetUser event) {
        String userID = preference.getString("UserID", "");

        FirebaseDatabase.getInstance().getReference("users")
                .child(userID)
                .get()
                .addOnSuccessListener(dataSnapshot -> {
                    User user = dataSnapshot.getValue(User.class);

                    if (user != null) {
                        userLogin= user;
                        event.onGetSuccess(user);
                    } else {
                        // Handle the case where user is null
                        showToast("User data is null");
                    }
                })
                .addOnFailureListener(e -> showToast("Error: " + e.getMessage()));
    }
    public ArrayList<Product> getListProduct(){
        ArrayList<Product> arrayList= new ArrayList<>();
        //String id, String ten, String tenLoai, String gia, String noiSX, String motaSP, String kichThuoc, String chatLieu, String img

//  1
        arrayList.add(new Product("", "Ghế 1", "ghe", "9.500.000",
                "Italy", "Ghế ngồi êm ái với đệm lót bằng mút cao cấp và vải bọc mềm mại, thích hợp cho gia đình hoặc văn phòng.",
                "50*60*80 cm", "Gỗ xoan",""));

//  2
        arrayList.add(new Product("", "Ghế 2", "ghe", "8.000.000",
                "France", "Ghế ngồi thoải mái với chất liệu gỗ tự nhiên và đệm mút mềm mại.",
                "45*55*75 cm", "Gỗ tự nhiên",""));

//  1
        arrayList.add(new Product("", "Bàn 1", "ban", "15.000.000",
                "Germany", "Bàn làm việc hiện đại với thiết kế đơn giản nhưng tiện ích.",
                "120*70*75 cm", "Nhựa cao cấp",""));

//  2
        arrayList.add(new Product("", "Bàn 2", "ban", "12.500.000",
                "Sweden", "Bàn làm việc thông minh với nhiều ngăn kéo và kệ để sách.",
                "100*60*80 cm", "Gỗ công nghiệp",""));

//  1
        arrayList.add(new Product("", "Bàn ăn 1", "ban", "20.000.000",
                "Italy", "Bàn ăn sang trọng với chất liệu gỗ và kính cao cấp.",
                "150*90*75 cm", "Gỗ và kính",""));

//  2
        arrayList.add(new Product("", "Bàn ăn 2", "ban", "18.500.000",
                "Spain", "Bàn ăn đẹp mắt với thiết kế độc đáo và chất liệu nhôm bền bỉ.",
                "140*80*75 cm", "Nhôm",""));

//  1
        arrayList.add(new Product("", "Kệ sách 1", "ke", "7.500.000",
                "France", "Kệ sách đa năng với nhiều tầng để sắp xếp sách và đồ trang trí.",
                "80*30*180 cm", "Gỗ công nghiệp",""));

//  2
        arrayList.add(new Product("", "Kệ sách 2", "ke", "6.000.000",
                "Vietnam", "Kệ sách đơn giản nhưng tiện lợi, phù hợp với mọi không gian.",
                "60*25*150 cm", "MDF",""));

//  3
        arrayList.add(new Product("", "Ghế 3", "ghe", "10.200.000",
                "USA", "Ghế phòng khách đẹp mắt với chất liệu da cao cấp và khung gỗ chắc chắn.",
                "55*65*85 cm", "Da và gỗ",""));

//  4
        arrayList.add(new Product("", "Ghế 4", "ghe", "7.800.000",
                "Japan", "Ghế làm việc thoải mái với thiết kế hiện đại và đệm lót êm ái.",
                "48*58*80 cm", "Kim loại và vải",""));

//  3
        arrayList.add(new Product("", "Bàn 3", "ban", "13.750.000",
                "Denmark", "Bàn làm việc nhỏ gọn với mặt bàn gỗ và khung thép chắc chắn.",
                "90*50*75 cm", "Gỗ và thép",""));

//  4
        arrayList.add(new Product("", "Bàn 4", "ban", "16.300.000",
                "Australia", "Bàn làm việc thông minh có thể điều chỉnh độ cao phù hợp với người sử dụng.",
                "110*70*80 cm", "Gỗ và nhựa",""));

//  3
        arrayList.add(new Product("", "Bàn ăn 3", "ban", "22.500.000",
                "China", "Bàn ăn hiện đại với chất liệu gỗ tự nhiên và thiết kế sang trọng.",
                "160*100*75 cm", "Gỗ tự nhiên",""));

//  4
        arrayList.add(new Product("", "Bàn ăn 4", "ban", "19.750.000",
                "Canada", "Bàn ăn đa năng với khung kim loại và mặt đá cao cấp.",
                "150*90*75 cm", "Kim loại và đá",""));

//  3
        arrayList.add(new Product("", "Kệ sách 3", "ke", "8.950.000",
                "Germany", "Kệ sách treo tường với thiết kế đơn giản và tiện lợi.",
                "100*20*180 cm", "Gỗ composite",""));

//  4
        arrayList.add(new Product("", "Kệ sách 4", "ke", "5.600.000",
                "Brazil", "Kệ sách hiện đại với nhiều ngăn đựng đồ và chất liệu gỗ tự nhiên.",
                "80*25*150 cm", "Gỗ tự nhiên",""));

//  1
        arrayList.add(new Product("", "Tủ 1", "tu", "14.500.000",
                "Italy", "Tủ đựng đồ phòng khách với kiểu dáng sang trọng và nhiều ngăn tiện ích.",
                "120*40*120 cm", "Gỗ và kính",""));

//  2
        arrayList.add(new Product("", "Tủ 2", "tu", "11.800.000",
                "France", "Tủ để đồ trang trí phòng khách với chất liệu gỗ tự nhiên và thiết kế đẹp mắt.",
                "100*30*150 cm", "Gỗ tự nhiên",""));

//  3
        arrayList.add(new Product("", "Tủ 3", "tu", "18.200.000",
                "Germany", "Tủ đựng quần áo phòng ngủ với nhiều ngăn kéo và kệ đựng rộng rãi.",
                "180*60*200 cm", "Gỗ và kim loại",""));

//  4
        arrayList.add(new Product("", "Tủ 4", "tu", "9.750.000",
                "Spain", "Tủ đựng đồ phòng khách với chất liệu gỗ công nghiệp và kiểu dáng hiện đại.",
                "90*40*120 cm", "Gỗ công nghiệp",""));

//  5
        arrayList.add(new Product("", "Tủ 5", "tu", "16.000.000",
                "Japan", "Tủ để sách và đồ trang trí với chất liệu gỗ và mặt kính trong suốt.",
                "120*35*180 cm", "Gỗ và kính",""));

// Add more arrayList similarly...






        return arrayList;
    }

    public void loadImg(Object img, ImageView view){
        Glide.with(view.getContext()).load(img).into(view);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                handlerBackPressed();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }
    public String formatToCurrency(float value) {
        Locale locale = new Locale("vi", "VN"); // Set the Vietnamese locale
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(value);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        callback.remove();
    }

    public SharedPreference preference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = getBinding(inflater, container);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preference = new SharedPreference(requireContext());
        initView();
    }



    public void addToCart(Product product) {
        if(userLogin!=null){
            database.getReference("Card")
                    .child(userLogin.getId())
                    .push()
                    .setValue(product)
                    .addOnSuccessListener(unused -> {
                        showToast("Đã thêm vào giỏ hàng thành công");
                    }).addOnFailureListener(e -> {
                        showToast("Error: "+e.getMessage());
                    });
        }
        else {
            showToast("User loggin is null");
        }
    }

    public interface OnEventListener{
        void onSuccess();
    }
    public interface FectchCartByUser{
        void onSuccess(ArrayList<Product> products);
    }
    public void removeCard(Product product, OnEventListener ev) {
        if(userLogin!=null){
            database.getReference("Card")
                    .child(userLogin.getId())
                    .child(product.Id)
                    .removeValue()
                    .addOnSuccessListener(unused -> {
                        showToast("Đã xóa thành công");
                        ev.onSuccess();
                    }).addOnFailureListener(e -> {
                        showToast("Error: "+e.getMessage());
                    });
        }
        else {
            showToast("User loggin is null");
        }
    }

    public void getListCard(User user,FectchCartByUser arr){
        if(user!=null){
            database.getReference("Card")
                    .child(user.getId())
                    .get()
                    .addOnSuccessListener(dataSnapshot -> {
                        ArrayList<Product> fetchPr= new ArrayList<>();
                        for (DataSnapshot doc:dataSnapshot.getChildren()){
                            fetchPr.add(doc.getValue(Product.class));
                        }
                        arr.onSuccess(fetchPr);
                    })
                    .addOnFailureListener(e -> showToast("Err: "+e.getMessage()));
        }
        else {
            showToast("User loggin is null");
        }
    }

    protected abstract T getBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void initView();

    public void addFragment(Fragment fragment, int viewId, boolean addtoBackStack) {
        if (viewId == 0) {
            viewId = android.R.id.content;
        }
        ((BaseActivity<?>) requireActivity()).addFragment(fragment, viewId, addtoBackStack);
    }

    public void replaceFullViewFragment(Fragment fragment, int viewId, boolean addToBackStack) {
        if (viewId == 0) {
            viewId = android.R.id.content;
        }
        ((BaseActivity<?>) requireActivity()).replaceFragment(fragment, viewId, addToBackStack);
    }

    public String getTimeNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    public static String formatDateString(String inputDate) {
        try {
            SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss 'GMT'Z yyyy", Locale.US);
            Date date = inputDateFormat.parse(inputDate);

            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
            return outputDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void replaceFragment(Fragment fragment, int viewId, boolean addtoBackStack) {
        if (viewId == 0) {
            viewId = android.R.id.content;
        }
        ((BaseActivity<?>) requireActivity()).replaceFragment(fragment, viewId, addtoBackStack);
    }

    public void closeFragment(Fragment fragment) {
        ((BaseActivity<?>) requireActivity()).handleBackpress();
    }

    public void addAndRemoveCurrentFragment(Fragment currentFragment, Fragment newFragment, boolean addToBackStack) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.remove(currentFragment);
        transaction.add(android.R.id.content, newFragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected void hideKeyboard() {
        if (getActivity() != null) {
            ((BaseActivity<?>) getActivity()).hideKeyboard();
        }
    }

    protected void showKeyboard(View view) {
        ((BaseActivity<?>) requireActivity()).showKeyboard(view);
    }

    protected void setColorStatusBar(int idColor) {
        if (getActivity() != null) {
            getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(requireContext(), idColor));
        }
    }

    protected void getResultListener(String requestKey, ResultListener callback) {
        getParentFragmentManager().setFragmentResultListener(requestKey, this, (key, result) -> callback.onResult(key, result));
    }

    protected void setFragmentResult(String requestKey, Bundle resultBundle) {
        requireActivity().getSupportFragmentManager().setFragmentResult(requestKey, resultBundle);
    }

    public static boolean isGoToSetting = false;
    public static boolean isAdsRewardShowing = false;

    public static <F extends Fragment> F newInstance(Class<F> fragment, Bundle args) throws IllegalAccessException, java.lang.InstantiationException {
        F f = fragment.newInstance();
        if (args != null) {
            f.setArguments(args);
        }
        return f;
    }

    interface ResultListener {
        void onResult(String requestKey, Bundle bundle);
    }
}
