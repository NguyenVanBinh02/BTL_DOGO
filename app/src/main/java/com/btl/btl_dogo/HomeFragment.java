package com.btl.btl_dogo;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btl.btl_dogo.adapter.ProductAdater;
import com.btl.btl_dogo.adapter.TagAdapter;
import com.btl.btl_dogo.base.AppViewModel;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentHomeBinding;
import com.btl.btl_dogo.model.Product;
import com.btl.btl_dogo.model.Tag;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> implements TagAdapter.TagInterface {

    private AppViewModel viewModel;

    public HomeFragment(AppViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    protected FragmentHomeBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(getLayoutInflater());
    }

    TagAdapter adapterTag;
    ProductAdater productAdater;

    ArrayList<Product> arrayList = new ArrayList<>();


    @Override
    protected void initView() {

        getUserData(us -> {
            binding.txtUser.setText(us.getName());
        });
        adapterTag = new TagAdapter(this);
        productAdater = new ProductAdater(new ProductAdater.ProductEvent() {
            @Override
            public void onLove(Product pr) {
                showToast("Love");
            }

            @Override
            public void onDetail(Product product) {
                replaceFragment(new DetailsFragment(product),android.R.id.content,true);

            }

            @Override
            public void onAddCart(Product product) {
                showToast("Add");
            }
        });


        binding.icSearch.setOnClickListener(v -> {
            ArrayList<Product> search = new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                arrayList.forEach(product -> {
                    if (product.Ten.toLowerCase().trim().replace(" ", "").
                            contains(binding.txtSearch.getText().toString().trim().
                                    replace(" ", "").toLowerCase())) {
                        search.add(product);
                    }
                });
                productAdater.setItems(binding.txtSearch.getText().length() == 0 ? arrayList : search);
                hideKeyboard();
            }
        });

        ArrayList<Tag> listTag = new ArrayList<>();
        listTag.add(new Tag("Ghế", "ghe"));
        listTag.add(new Tag("Bàn", "ban"));
        listTag.add(new Tag("Tủ quần áo", "tu"));
        listTag.add(new Tag("Kệ sách", "ke"));
        listTag.add(new Tag("Tất cả", "All"));
        binding.tagSearch.setAdapter(adapterTag);
        adapterTag.setItems(listTag);


        // Thêm dữ liệu cho ghế
        arrayList.add(new Product("", "Ghe 2", "ghe", "9.500.000",
                "Ghế ngồi cao cấp có thiết kế hiện đại và sang trọng, đem lại sự thoải mái khi sử dụng.",
                "50*60*80 cm", "Gỗ xoan", "Italy"));
        arrayList.add(new Product("", "Ghe 3", "ghe", "15.000.000",
                "Ghế ngồi êm ái với đệm lót bằng mút cao cấp và vải bọc mềm mại, thích hợp cho gia đình hoặc văn phòng.",
                "45*55*75 cm", "Gỗ hiến", "Việt Nam"));
// Thêm dữ liệu cho bàn
        arrayList.add(new Product("", "Ban 1", "ban", "7.800.000",
                "Bàn làm việc đơn giản và tiện lợi, phù hợp cho không gian làm việc nhỏ.",
                "120*60*75 cm", "Gỗ MDF", "Việt Nam"));
        arrayList.add(new Product("", "Ban 2", "ban", "10.200.000",
                "Bàn ăn hiện đại với chất liệu gỗ tự nhiên, mặt bàn chống nước và chống trầy xước.",
                "150*80*75 cm", "Gỗ sồi", "Đức"));
// Thêm dữ liệu cho tủ
        arrayList.add(new Product("", "Tu 1", "tu", "18.500.000",
                "Tủ đựng quần áo sang trọng và tiện lợi, có nhiều ngăn đựng giúp bạn sắp xếp đồ dễ dàng.",
                "120*50*180 cm", "Gỗ thông", "Việt Nam"));
        arrayList.add(new Product("", "Tu 2", "tu", "22.000.000",
                "Tủ giày dép đa năng với nhiều ngăn đựng và chất liệu gỗ cao cấp, giúp tiết kiệm không gian và bảo quản đồ dễ dàng.",
                "80*35*120 cm", "Gỗ cao su", "Hàn Quốc"));
// Thêm dữ liệu cho kệ
        arrayList.add(new Product("", "Ke 1", "ke", "14.800.000",
                "Kệ sách hiện đại với kiểu dáng độc đáo và chất liệu gỗ tự nhiên, phù hợp cho phòng khách hoặc phòng làm việc.",
                "100*30*180 cm", "Gỗ dán", "Việt Nam"));
        arrayList.add(new Product("", "Ke 2", "ke", "17.600.000",
                "Kệ trang trí với các ngăn đan xen, giúp tạo điểm nhấn cho không gian sống của bạn.",
                "120*40*150 cm", "Gỗ bạch đà", "Việt Nam"));

        binding.listProduct.setAdapter(productAdater);
        productAdater.setItems(arrayList);
    }


    @Override
    public void onSelect(Tag tag, int position) {
        adapterTag.setCurrentPos(position);
        serachByTag(tag);
    }

    private void serachByTag(Tag tag) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ArrayList<Product> search;
            if (tag.idTag == "All") {
                search = arrayList;
            } else {
                search = new ArrayList<>();
                arrayList.forEach(product -> {
                    if (product.TenLoai == tag.idTag) {
                        search.add(product);
                    }
                });
            }
            productAdater.setItems(search);
        }
    }

}