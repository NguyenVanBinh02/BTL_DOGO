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


        arrayList.add(new Product("", "Ghe 1", "ghe", "12.500.000"));
        arrayList.add(new Product("", "Ghe 2", "ghe", "10.000.000"));
        arrayList.add(new Product("", "Ban 1", "ban", "8.000.000"));
        arrayList.add(new Product("", "Ban 2", "ban", "9.500.000"));
        arrayList.add(new Product("", "Tu 1", "tu", "15.000.000"));
        arrayList.add(new Product("", "Tu 2", "tu", "18.000.000"));
        arrayList.add(new Product("", "Ke 2", "ke", "18.500.000"));
        arrayList.add(new Product("", "Ke 3", "ke", "22.000.000"));
        arrayList.add(new Product("", "Ke 4", "ke", "15.800.000"));

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