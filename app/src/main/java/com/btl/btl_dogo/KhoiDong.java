package com.btl.btl_dogo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.btl.btl_dogo.databinding.ActivityKhoiDongBinding;

public class KhoiDong extends AppCompatActivity {
ActivityKhoiDongBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityKhoiDongBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtFooter.setOnClickListener(v->
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });
    }
}