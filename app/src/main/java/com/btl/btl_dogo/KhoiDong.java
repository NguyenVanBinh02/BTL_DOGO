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
            if(!preference.getString("UserID","").equals("")){
                startActivity(new Intent(KhoiDong.this,MainActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }

        }
        binding.txtFooter.setOnClickListener(v->
        {
            startActivity(new Intent(getApplicationContext(), Register.class));
            finish();
        });
        binding.btnDangnhap.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),Login.class));
            finish();
        });
    }
}