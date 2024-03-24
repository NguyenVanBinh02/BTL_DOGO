package com.btl.btl_dogo.base;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import com.btl.btl_dogo.R;
import com.btl.btl_dogo.model.CardProduct;
import com.btl.btl_dogo.model.Product;
import com.btl.btl_dogo.model.User;
import com.bumptech.glide.Glide;
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
        arrayList.add(new Product("product_1", "Ghế 1", "ghe", "9.500",
                "Italy", "Ghế ngồi êm ái với đệm lót bằng mút cao cấp và vải bọc mềm mại, thích hợp cho gia đình hoặc văn phòng.",
                "50*60*80 cm", "Gỗ xoan",""));

//  2
        arrayList.add(new Product("product_2", "Ghế 2", "ghe", "8.000",
                "France", "Ghế ngồi thoải mái với chất liệu gỗ tự nhiên và đệm mút mềm mại.",
                "45*55*75 cm", "Gỗ tự nhiên",""));

//  1
        arrayList.add(new Product("product_3", "Bàn 1", "ban", "15.000",
                "Germany", "Bàn làm việc hiện đại với thiết kế đơn giản nhưng tiện ích.",
                "120*70*75 cm", "Nhựa cao cấp",""));

//  2
        arrayList.add(new Product("product_4", "Bàn 2", "ban", "12.500",
                "Sweden", "Bàn làm việc thông minh với nhiều ngăn kéo và kệ để sách.",
                "100*60*80 cm", "Gỗ công nghiệp",""));

//  1
        arrayList.add(new Product("product_5", "Bàn ăn 1", "ban", "20.000",
                "Italy", "Bàn ăn sang trọng với chất liệu gỗ và kính cao cấp.",
                "150*90*75 cm", "Gỗ và kính",""));

//  2
        arrayList.add(new Product("product_6", "Bàn ăn 2", "ban", "18.500",
                "Spain", "Bàn ăn đẹp mắt với thiết kế độc đáo và chất liệu nhôm bền bỉ.",
                "140*80*75 cm", "Nhôm",""));

//  1
        arrayList.add(new Product("product_7", "Kệ sách 1", "ke", "7.500",
                "France", "Kệ sách đa năng với nhiều tầng để sắp xếp sách và đồ trang trí.",
                "80*30*180 cm", "Gỗ công nghiệp",""));

//  2
        arrayList.add(new Product("product_8", "Kệ sách 2", "ke", "6.000",
                "Vietnam", "Kệ sách đơn giản nhưng tiện lợi, phù hợp với mọi không gian.",
                "60*25*150 cm", "MDF",""));

//  3
        arrayList.add(new Product("product_9", "Ghế 3", "ghe", "10.200",
                "USA", "Ghế phòng khách đẹp mắt với chất liệu da cao cấp và khung gỗ chắc chắn.",
                "55*65*85 cm", "Da và gỗ",""));

//  4
        arrayList.add(new Product("product_10", "Ghế 4", "ghe", "7.800",
                "Japan", "Ghế làm việc thoải mái với thiết kế hiện đại và đệm lót êm ái.",
                "48*58*80 cm", "Kim loại và vải",""));

//  3
        arrayList.add(new Product("product_11", "Bàn 3", "ban", "13.750",
                "Denmark", "Bàn làm việc nhỏ gọn với mặt bàn gỗ và khung thép chắc chắn.",
                "90*50*75 cm", "Gỗ và thép",""));

//  4
        arrayList.add(new Product("product_12", "Bàn 4", "ban", "16.300",
                "Australia", "Bàn làm việc thông minh có thể điều chỉnh độ cao phù hợp với người sử dụng.",
                "110*70*80 cm", "Gỗ và nhựa",""));

//  3
        arrayList.add(new Product("product_13", "Bàn ăn 3", "ban", "22.500",
                "China", "Bàn ăn hiện đại với chất liệu gỗ tự nhiên và thiết kế sang trọng.",
                "160*100*75 cm", "Gỗ tự nhiên",""));

//  4
        arrayList.add(new Product("product_14", "Bàn ăn 4", "ban", "19.750",
                "Canada", "Bàn ăn đa năng với khung kim loại và mặt đá cao cấp.",
                "150*90*75 cm", "Kim loại và đá",""));

//  3
        arrayList.add(new Product("product_15", "Kệ sách 3", "ke", "8.950",
                "Germany", "Kệ sách treo tường với thiết kế đơn giản và tiện lợi.",
                "100*20*180 cm", "Gỗ composite",""));

//  4
        arrayList.add(new Product("product_16", "Kệ sách 4", "ke", "5.600",
                "Brazil", "Kệ sách hiện đại với nhiều ngăn đựng đồ và chất liệu gỗ tự nhiên.",
                "80*25*150 cm", "Gỗ tự nhiên",""));

//  1
        arrayList.add(new Product("product_17", "Tủ 1", "tu", "14.500",
                "Italy", "Tủ đựng đồ phòng khách với kiểu dáng sang trọng và nhiều ngăn tiện ích.",
                "120*40*120 cm", "Gỗ và kính",""));

//  2
        arrayList.add(new Product("product_18", "Tủ 2", "tu", "11.800",
                "France", "Tủ để đồ trang trí phòng khách với chất liệu gỗ tự nhiên và thiết kế đẹp mắt.",
                "100*30*150 cm", "Gỗ tự nhiên",""));

//  3
        arrayList.add(new Product("product_19", "Tủ 3", "tu", "18.200",
                "Germany", "Tủ đựng quần áo phòng ngủ với nhiều ngăn kéo và kệ đựng rộng rãi.",
                "180*60*200 cm", "Gỗ và kim loại",""));

//  4
        arrayList.add(new Product("product_20", "Tủ 4", "tu", "9.750",
                "Spain", "Tủ đựng đồ phòng khách với chất liệu gỗ công nghiệp và kiểu dáng hiện đại.",
                "90*40*120 cm", "Gỗ công nghiệp",""));

//  5
        arrayList.add(new Product("product_21", "Tủ 5", "tu", "16.000",
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
        CardProduct cardProduct= new CardProduct();
        cardProduct.Count=1;
        cardProduct.Id=product.Id;
        cardProduct.Price=product.Gia;
        cardProduct.Product=product;
        if(userLogin!=null){
            database.getReference("Card")
                    .child(userLogin.getId())
                    .child(cardProduct.Id)
                    .setValue(cardProduct)
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
        void onSuccess(ArrayList<CardProduct> products);
    }
    public void removeCard(CardProduct product, OnEventListener ev, boolean isPayment) {
        String usID= preference.getString("UserID","");
        if(!usID.isEmpty()){
            database.getReference("Card")
                    .child(usID)
                    .child(product.Id)
                    .removeValue()
                    .addOnSuccessListener(unused -> {
                        if(!isPayment){
                            showToast("Đã xóa thành công");
                        }
                        ev.onSuccess();
                    }).addOnFailureListener(e -> {
                        showToast("Error: "+e.getMessage());
                    });
        }
        else {
            showToast("User loggin is null");
        }
    }

    public void getListCard(FectchCartByUser arr){
        String usID= preference.getString("UserID","");
        if(!usID.isEmpty()){
            database.getReference("Card")
                    .child(usID)
                    .get()
                    .addOnSuccessListener(dataSnapshot -> {
                        ArrayList<CardProduct> fetchPr= new ArrayList<>();
                        for (DataSnapshot doc:dataSnapshot.getChildren()){
                            fetchPr.add(doc.getValue(CardProduct.class));
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

    public void getLove(Consumer<ArrayList<Product>> data){
        String usID= preference.getString("UserID","");
        if(!usID.isEmpty()) {
            database.getReference("Love")
                    .child(usID)
                    .get()
                    .addOnSuccessListener(dataSnapshot -> {
                        ArrayList<Product> arrProduct= new ArrayList<>();
                        for (DataSnapshot ignored :dataSnapshot.getChildren()){
                            arrProduct.add(ignored.getValue(Product.class));
                        }
                        data.accept(arrProduct);
                    }).addOnFailureListener(e -> {
                        data.accept(null);
                    });

        }
    }

    public void showNotification(String header,String content){
        NotificationManager notificationManager = (NotificationManager) this.requireContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String s = header.equals("") ? header = "Thông báo" : header;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channelId", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(requireContext(), "channelId")
                .setSmallIcon(R.drawable.img)
                .setContentTitle(s)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        // Show the notification
        notificationManager.notify(0, builder.build());
    }



    public void showYesNoDialog(Context context, String title, String message,Consumer<Void> event) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.img);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Xác nhận", (dialog, which) -> {
         event.accept(null);
            dialog.dismiss();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            dialog.dismiss();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void onLoveSp(Product pr,Consumer<Boolean> status){
        String usID= preference.getString("UserID","");
        if(!usID.isEmpty()) {
            database.getReference("Love")
                    .child(usID)
                    .child(pr.Id)
                    .setValue(pr)
                    .addOnSuccessListener(task->{
                        status.accept(true);
                    }).addOnFailureListener(e->{
                        status.accept(false);
                    });
        }
    }
    public void onUnLove(Product pr,Consumer<Boolean> status){
        String usID= preference.getString("UserID","");
        if(!usID.isEmpty()) {
            database.getReference("Love")
                    .child(usID)
                    .child(pr.Id)
                    .removeValue()
                    .addOnSuccessListener(task->{
                        status.accept(true);
                    }).addOnFailureListener(e->{
                        status.accept(false);
                    });
        }
    }



}
