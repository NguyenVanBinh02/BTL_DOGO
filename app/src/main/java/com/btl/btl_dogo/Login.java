package com.btl.btl_dogo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.btl.btl_dogo.databinding.ActivityKhoiDongBinding;
import com.btl.btl_dogo.databinding.ActivityLoginBinding;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtFooter.setOnClickListener(v->
        {
            startActivity(new Intent(getApplicationContext(), Register.class));
        });
        binding.btnLogin.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        });

    }
}