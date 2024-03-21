package com.btl.btl_dogo;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.btl.btl_dogo.adapter.CommentAdapter;
import com.btl.btl_dogo.adapter.SPLQAdapter;
import com.btl.btl_dogo.base.BaseAdapter;
import com.btl.btl_dogo.base.BaseFragment;
import com.btl.btl_dogo.databinding.FragmentDetailsBinding;
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

    @Override
    protected void initView() {

        splqAdapter = new SPLQAdapter(new SPLQAdapter.ProductEvent() {
            @Override
            public void onDetail(Product product) {

            }
        });
        binding.relatedProducts.setAdapter(splqAdapter);
        splqAdapter.setItems(getSPLQ());
        commentAdapter =new CommentAdapter();
        binding.ingBack.setOnClickListener(v -> closeFragment(DetailsFragment.this));
        loadImg(product.img==""?R.drawable.img_1:product.img,binding.imgSp);
        binding.txtSp.setText(product.Ten);
        binding.txtGia.setText(String.valueOf(product.Gia));
        binding.txtMota.setText(product.MotaSP);
        binding.txtChatLieu.setText("Chất liệu: "+product.ChatLieu);
        binding.txtXuatSu.setText("Nơi sản xuất: "+product.NoiSX);
        ArrayList<Comment> listComment = new ArrayList<>();
        listComment.add(new Comment("", "Nguyen Van Binh", "", "Kệ làm bằng gỗ mang vẻ đẹp hài hòa, tinh tế tự nhiên và bền đẹp"));
        listComment.add(new Comment("", "Nguyen Thi Minh Anh", "", "Chất liệu gỗ tự nhiên nhẵn mịn, chống ẩm tốt cộng thêm được phủ bóng bên ngoài giúp việc chùi, vệ sinh sản phẩm trở nên dễ dàng"));
        listComment.add(new Comment("", "Nguyen Thi Thanh Lam", "", "kiểu dáng thiết kế lẫn chất liệu được sử dụng để gia công, sản xuất"));
        listComment.add(new Comment("", "Nguyen Van Binh", "", "Kệ làm bằng gỗ mang vẻ đẹp hài hòa, tinh tế tự nhiên và bền đẹp"));
        listComment.add(new Comment("", "Nguyen Thi Minh Anh", "", "Chất liệu gỗ tự nhiên nhẵn mịn, chống ẩm tốt cộng thêm được phủ bóng bên ngoài giúp việc chùi, vệ sinh sản phẩm trở nên dễ dàng"));
        listComment.add(new Comment("", "Nguyen Thi Thanh Lam", "", "kiểu dáng thiết kế lẫn chất liệu được sử dụng để gia công, sản xuất"));

        binding.listComment.setAdapter(commentAdapter);
        commentAdapter.setItems(listComment);
        binding.txtAdd.setOnClickListener(v -> {
            int add = Integer.parseInt(binding.txtSL.getText().toString());
            add ++;
            binding.txtSL.setText(String.valueOf(add));
        });
        binding.txtRemove.setOnClickListener(v -> {
            int remove = Integer.parseInt(binding.txtSL.getText().toString());
            if (remove >1) {
                remove--;
                binding.txtSL.setText(String.valueOf(remove));
            }
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