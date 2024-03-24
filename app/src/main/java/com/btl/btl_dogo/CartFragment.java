package com.btl.btl_dogo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;

import com.btl.btl_dogo.adapter.CartAdapter;
import com.btl.btl_dogo.base.BaseActivity;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentCartBinding;
import com.btl.btl_dogo.model.CardProduct;
import com.btl.btl_dogo.model.Notification;
import com.btl.btl_dogo.model.Product;
import com.btl.btl_dogo.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class CartFragment extends BaseFragment<FragmentCartBinding> {
    CartAdapter cartAdapter;

    ArrayList<CardProduct> cardProduct = new ArrayList<>();

    @Override
    protected FragmentCartBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCartBinding.inflate(getLayoutInflater());
    }

    private ArrayList<CardProduct> arrBuy = new ArrayList<>();

    @Override
    protected void initView() {
        getListCard(products -> {
            cardProduct = products;
            cartAdapter.setItems(cardProduct);
        });
        binding.ingBack.setOnClickListener(v -> closeFragment(CartFragment.this));
        cartAdapter = new CartAdapter(new CartAdapter.ProductEvent() {
            @Override
            public void onDetail(CardProduct product) {
                replaceFragment(new DetailsFragment(product.Product), android.R.id.content, true);
            }

            @Override
            public void onAdd(CardProduct product, int position) {
                product.Count += 1;
                cartAdapter.notifyItemChanged(position, product);
                updateValue(cartAdapter.getListItem());
            }

            @Override
            public void onRemove(CardProduct product, int position) {
                product.Count -= 1;
                if (product.Count < 1) {
                    product.Count = 1;
                }
                cartAdapter.notifyItemChanged(position, product);
                updateValue(cartAdapter.getListItem());
            }

            @Override
            public void onSelect(CardProduct product, boolean isSelect, int post) {
                product.IsSelect = isSelect;
                cartAdapter.notifyItemChanged(post, product);
                updateValue(cartAdapter.getListItem());
            }

        });
        binding.listSP.setAdapter(cartAdapter);

        binding.btnMuaHang.setOnClickListener(v -> {
            showYesNoDialog(this.requireContext(), "Xác nhận thanh toán", "Bạn có xác nhận thanh toán " + tongSp + " không?", unused -> {
                ConfirmPayment();
            });
        });
    }


    private void ConfirmPayment() {
        for (CardProduct cardProduct : cartAdapter.getListItem()) {
            if (cardProduct.IsSelect) {
                removeCard(cardProduct, () -> {

                }, true);
            }
        }
        getListCard(products -> {
            cardProduct = products;
            cartAdapter.setItems(cardProduct);
        });
        showNotification("Đặt hàng thành công", "Bạn đã thanh toán " + tongSp + "đ vào lúc " + getTimeNow());
        binding.txtTongGia.setText("0.0");

        pushNotification();
    }

    private void pushNotification() {
        DatabaseReference reference = database.getReference("Notification").child(preference.getString("UserID", "")).push();
        Notification notification = new Notification(reference.getKey(),"Đặt hàng thành công","Đơn hàng của bạn đã được tạo\nTổng hóa đơn: "+tongSp+"đ",getTimeNow(),false);
        reference.setValue(notification)
                .addOnFailureListener(e -> {
                    showToast(e.getMessage());
                });

    }

    float tongSp = 0f;

    private void updateValue(ArrayList<CardProduct> listItem) {

        for (CardProduct cardProduct : listItem) {
            if (cardProduct.IsSelect) {
                String giatt = cardProduct.Price.replace(".", "").replace(" ", "").replace(",", "");
                float gia = cardProduct.Count * Integer.valueOf(giatt);
                tongSp += gia;
            }
        }
        binding.txtTongGia.setText("" + tongSp);
    }


}