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
                onLoveSp(pr, aBoolean -> {
                    if(aBoolean){
                        showToast("Đã thêm vào danh sách yêu thích!");
                    }
                });
            }

            @Override
            public void onDetail(Product product) {
                replaceFragment(new DetailsFragment(product),android.R.id.content,true);

            }

            @Override
            public void onAddCart(Product product) {
                addToCart(product);
            }
        });
        binding.imgCart.setOnClickListener(v -> {
            replaceFragment(new CartFragment(),android.R.id.content,true);
        });


        binding.icSearch.setOnClickListener(v -> {
            ArrayList<Product> search = new ArrayList<>();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getListProduct().forEach(product -> {
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

        binding.listProduct.setAdapter(productAdater);
        productAdater.setItems(getListProduct());
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
                search = getListProduct();
            } else {
                search = new ArrayList<>();
                getListProduct().forEach(product -> {
                    if (product.TenLoai == tag.idTag) {
                        search.add(product);
                    }
                });
            }
            productAdater.setItems(search);
        }
    }

}