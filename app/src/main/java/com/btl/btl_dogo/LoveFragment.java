package com.btl.btl_dogo;

import android.os.Bundle;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.btl.btl_dogo.adapter.LoveAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentLoveBinding;
import com.btl.btl_dogo.model.CardProduct;
import com.btl.btl_dogo.model.Product;

import java.util.ArrayList;


public class LoveFragment extends BaseFragment<FragmentLoveBinding> {

    @Override
    protected FragmentLoveBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentLoveBinding.inflate(getLayoutInflater());
    }

    private LoveAdapter adapter;
    @Override
    protected void initView() {
        binding.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CartFragment(),android.R.id.content,true);
            }
        });
        adapter= new LoveAdapter(new LoveAdapter.ProductEvent() {
            @Override
            public void onDelete(Product pr) {
                showYesNoDialog(LoveFragment.this.requireContext(), "Cảnh báo", "Bạn có muốn xóa sản phẩm này khỏi danh sách yêu thích không?",
                        unused -> onUnLove(pr, aBoolean -> {
                    if(aBoolean){
                        showToast("Đã xóa khỏi danh sách yêu thích!");
                        adapter.removeItem(pr);
                    }
                }));
            }
            @Override
            public void onDetail(Product product) {
                replaceFragment(new DetailsFragment(product),android.R.id.content,true);
            }

            @Override
            public void onAddCart(Product product) {
                addToCart( new CardProduct(product.Id, product,1,product.Gia,false));
            }
        });
        getLove(products -> {
            adapter.setItems(products);
        });

        binding.listSP.setAdapter(adapter);
    }

}