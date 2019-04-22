package com.arba.orilampung;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NavActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;
    private HomeFragment homeFragment;
    private AboutFragment aboutFragment;
    private AboutLoginFragment aboutLoginFragment;
    private AboutVerifFragment aboutVerifFragment;
    private DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.navigation);
        homeFragment = new HomeFragment();
        aboutFragment = new AboutFragment();
        aboutLoginFragment = new AboutLoginFragment();
        aboutVerifFragment = new AboutVerifFragment();

        userRef = FirebaseDatabase.getInstance().getReference().child("User");

        setFragment(homeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.navigation_home:
                        setFragment (homeFragment);
                        return true;

                    case R.id.navigation_dashboard:
                        if (isConnected()) {
                            FirebaseUser cuurentUser = mAuth.getCurrentUser();
                            if (cuurentUser != null) {
                                String userId = mAuth.getCurrentUser().getUid();
                                userRef.child(userId).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            String verif = dataSnapshot.child("verif").getValue().toString();
                                            if (verif.equals("sudah")){
                                                Intent intent = new Intent(NavActivity.this, BuatAjuanActivity.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(NavActivity.this, "Tunggu verifikasi akun anda untuk buat aduan", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                            else {
                                Toast.makeText(NavActivity.this, "Silahkan masuk atau registrasi untuk buat ajuan!", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan, cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                        }
                        return true;

                    case R.id.navigation_notifications:
                        if (isConnected()) {
                            FirebaseUser cuurentUser1 = mAuth.getCurrentUser();
                            if (cuurentUser1 != null) {
                                String userId = mAuth.getCurrentUser().getUid();
                                userRef.child(userId).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()){
                                            String verif = dataSnapshot.child("verif").getValue().toString();
                                            if (verif.equals("sudah")){
                                                setFragment (aboutVerifFragment);
                                            } else {
                                                setFragment (aboutLoginFragment);

                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });

                            }
                            else {
                                setFragment (aboutFragment);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Terjadi kesalahan, cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                        }

                        return true;


                    default:
                        return false;
                }

            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Keluar");
        builder.setMessage("Anda yakin ingin keluar?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        builder.create().show();
    }

}
