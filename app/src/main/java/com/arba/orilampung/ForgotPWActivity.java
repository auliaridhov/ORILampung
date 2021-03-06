package com.arba.orilampung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPWActivity extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);

        passwordEmail = findViewById(R.id.editTextpw);
        resetPassword = findViewById(R.id.buttonpwreset);
        firebaseAuth = FirebaseAuth.getInstance();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String useremail = passwordEmail.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(ForgotPWActivity.this,"Masukkan email anda",Toast.
                            LENGTH_SHORT).show();
                }
                else {
                    firebaseAuth.sendPasswordResetEmail(useremail).
                            addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(ForgotPWActivity.this,
                                                "Cek email anda!",Toast.LENGTH_SHORT).show();
                                        finish();
                                        startActivity(new Intent(
                                                ForgotPWActivity.this, LoginActivity.class));
                                    }
                                    else {
                                        Toast.makeText(ForgotPWActivity.this,
                                                "Terjadi Kesalahan!",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }

            }
        });
    }
}