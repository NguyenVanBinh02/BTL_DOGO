package com.btl.btl_dogo;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.util.Consumer;

import com.btl.btl_dogo.adapter.CommentAdapter;
import com.btl.btl_dogo.adapter.SPLQAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentDetailsBinding;
import com.btl.btl_dogo.model.CardProduct;
import com.btl.btl_dogo.model.Comment;
import com.btl.btl_dogo.model.Product;

import java.util.ArrayList;


public class DetailsFragment extends BaseFragment<FragmentDetailsBinding> {
    private Product product;
    CommentAdapter commentAdapter;
    SPLQAdapter splqAdapter;
    public DetailsFragment(Product product) {
        this.product = product;
    }

    @Override
    protected FragmentDetailsBinding getBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentDetailsBinding.inflate(getLayoutInflater());
    }
    CardProduct cart;
    @Override
    protected void initView() {


        binding.btnThemSP.setOnClickListener(v->{
            addToCart(cart);
        });
        binding.btnChiTiet.setOnClickListener(v->{
            addFragment( new WriteCommentFragment(comment -> {
                commentAdapter.addItem(comment,0);
            }, product),android.R.id.content,false);
        });
        cart = new CardProduct(product.Id, product,1,product.Gia,false);
        binding.txtSL.setText(""+ cart.Count);
        splqAdapter = new SPLQAdapter(new SPLQAdapter.ProductEvent() {
            @Override
            public void onDetail(Product product) {
                replaceFragment(new DetailsFragment(product),android.R.id.content,true);
            }
        });
        binding.relatedProducts.setAdapter(splqAdapter);
        splqAdapter.setItems(getSPLQ());
        commentAdapter =new CommentAdapter();
        binding.ingBack.setOnClickListener(v -> {
            closeFragment(DetailsFragment.this);
        });
        loadImg(product.img==""?R.drawable.img_1:product.img,binding.imgSp);
        binding.txtSp.setText(product.Ten);
        binding.txtGia.setText(String.valueOf(product.Gia));
        binding.txtMota.setText(product.MotaSP);
        binding.txtChatLieu.setText("Chất liệu: "+product.ChatLieu);
        binding.txtXuatSu.setText("Nơi sản xuất: "+product.NoiSX);

        binding.listComment.setAdapter(commentAdapter);
        getComment(product, comments -> {
            commentAdapter.setItems(comments);
        });

        binding.txtAdd.setOnClickListener(v -> {
           cart.Count++;
            binding.txtSL.setText(""+ cart.Count);
        });
        binding.txtRemove.setOnClickListener(v -> {
            cart.Count--;

            if (cart.Count <=1){
                cart.Count =1;
            }
            binding.txtSL.setText(""+ cart.Count);
        });
    }
    public ArrayList<Product> getSPLQ (){
        ArrayList<Product> SPLQ = new ArrayList<>();
        for (Product pr:getListProduct()){
            if (pr.TenLoai.equals(product.TenLoai)){
                SPLQ.add(pr);
            }
        }
        return SPLQ;
    }


}