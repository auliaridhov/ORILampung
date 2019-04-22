package com.arba.orilampung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        final TextInputEditText emailEt = findViewById(R.id.email_ed);
        final TextInputEditText passwordEt = findViewById(R.id.password_ed);
        final Button btnLogin = findViewById(R.id.btnLogin);
        final Button btnRegister = findViewById(R.id.regis);
        final TextView tvlupapw = findViewById(R.id.tvlupapw);

        tvlupapw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPWActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(emailEt.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Email kosong",
                            Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(passwordEt.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Password kosong",
                            Toast.LENGTH_SHORT).show();
                } else {

                    String email = emailEt.getText().toString();
                    String password = passwordEt.getText().toString();

                    LoginAccount(email, password);
                }
            }
        });

    }

    private void LoginAccount(String email, String password) {
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show();
        } else {
            loadingBar.setTitle("Login");
            loadingBar.setMessage("Mohon tunggu sebentar...");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent mainInten = new Intent(LoginActivity.this, NavActivity.class);
                                mainInten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(mainInten);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Gagal login, coba lagi", Toast.LENGTH_SHORT).show();
                            }
                            loadingBar.dismiss();
                        }
                    });
        }
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.regis:
                Intent loginIntent = new Intent(LoginActivity.this,
                        RegisActivity.class);
                startActivity(loginIntent);
                break;
        }
    }

    //Auth User
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cuurentUser = mAuth.getCurrentUser();
        if (cuurentUser != null){
            String currentUserId = mAuth.getCurrentUser().getUid();

            Intent mainInten = new Intent(LoginActivity.this, NavActivity.class);
            mainInten.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainInten);
            finish();


        }
    }
}
