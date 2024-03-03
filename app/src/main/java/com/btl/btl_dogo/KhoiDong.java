package com.btl.btl_dogo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.btl.btl_dogo.base.SharedPreference;
import com.btl.btl_dogo.databinding.ActivityKhoiDongBinding;

public class KhoiDong extends AppCompatActivity {
    SharedPreference preference;
ActivityKhoiDongBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityKhoiDongBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preference= new SharedPreference(getApplicationContext());
        if (preference.getBoolean("khoidong",false)){
            startActivity(new Intent(getApplicationContext(),Login.class));
        }
        binding.txtFooter.setOnClickListener(v->
        {
            startActivity(new Intent(getApplicationContext(), Register.class));
        });
        binding.btnDangnhap.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),Login.class));
        });
    }
}