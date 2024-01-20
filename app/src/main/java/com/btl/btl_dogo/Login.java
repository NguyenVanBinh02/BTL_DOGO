package com.btl.btl_dogo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.btl.btl_dogo.databinding.ActivityLoginBinding;
import com.btl.btl_dogo.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    ActivityLoginBinding binding;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtFooter.setOnClickListener(v->
        {
            startActivity(new Intent(getApplicationContext(), Register.class));
        });
        binding.backLogin.setOnClickListener(v->{
            startActivity(new Intent(getApplicationContext(), KhoiDong.class));
        });

        mDatabase= FirebaseDatabase.getInstance().getReference("users");
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.txtEmail.getText().toString().trim();
                String password = binding.txtPassword.getText().toString().trim();
                if(!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){
                    mDatabase.orderByChild(email).equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                for (DataSnapshot userSnapshot: snapshot.getChildren()){
                                    User user = userSnapshot.getValue(User.class);

                                    // Kiểm tra mật khẩu
                                    if (user != null && user.getPassword().equals(password)) {
                                        // Đăng nhập thành công
                                        Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                        // Thực hiện các hành động cần thiết sau khi đăng nhập thành công
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));

                                    } else {
                                        // Mật khẩu không chính xác
                                        Toast.makeText(Login.this, "Mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }else {
                                Toast.makeText(Login.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Xử lý khi có lỗi xảy ra trong quá trình đọc dữ liệu từ Firebase
                            Toast.makeText(Login.this, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    // Hiển thị thông báo nếu có ô nhập liệu trống
                    Toast.makeText(Login.this, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}