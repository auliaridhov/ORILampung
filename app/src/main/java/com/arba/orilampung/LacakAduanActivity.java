package com.arba.orilampung;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class LacakAduanActivity extends AppCompatActivity {

    private RecyclerView riwayatList;
    private DatabaseReference riwayatRef, userRef;
    String currentUserId;
    private FirebaseAuth mAuth;
    private String id, jenisKey;
    private LacakAdapter lacakAdapter;
    String uid;

    private FirebaseRecyclerAdapter<ModulLacak, LacakAduanActivity.ReviewViewHolder> firebaseRecyclerAdapter;


    private final List<ModulLacak> reviewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lacak_aduan);


        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        lacakAdapter = new LacakAdapter(reviewList);

        riwayatRef = FirebaseDatabase.getInstance().getReference().child("Pengaduan").child(uid);

        riwayatList = findViewById(R.id.riwayat_RV);

        riwayatList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LacakAduanActivity.this);

        riwayatList.setLayoutManager(linearLayoutManager);

        TampilkanRiwayat();
    }

    private void TampilkanRiwayat() {

//        Query query = FirebaseDatabase.getInstance()
//                .getReference()
//                .child("Pengaduan")
//                .child(uid);

        FirebaseRecyclerOptions<ModulLacak> options = new FirebaseRecyclerOptions.Builder<ModulLacak>()
                .setQuery(riwayatRef, ModulLacak.class)
                .build();
        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ModulLacak, ReviewViewHolder>(
                options
        ) {
            @NonNull
            @Override
            public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.lacak_layout, viewGroup, false);
                return new  ReviewViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ReviewViewHolder holder, int position, @NonNull ModulLacak model) {

//                ModulReview modulReview = reviewList.get(position);

                holder.setInstansi_terlapor(model.getInstansi_terlapor());
                holder.setNama_pelapor(model.getNama_pelapor());
                holder.setStatus(model.getStatus());
            }

        };

        riwayatList.setAdapter(firebaseRecyclerAdapter);

    }
    public static class ReviewViewHolder extends RecyclerView.ViewHolder{

        View mView;
        public ReviewViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }


        public void setInstansi_terlapor(String instansi_terlapor) {
            TextView instansi = mView.findViewById(R.id.instansiTerlapor);
            instansi.setText(instansi_terlapor);
        }

        public void setNama_pelapor(String nama_pelapor) {
            TextView nama = mView.findViewById(R.id.namaPelapor);
            nama.setText(nama_pelapor);
        }

        public void setStatus(String status) {
            TextView statustv = mView.findViewById(R.id.statusaduan);
            statustv.setText(status);
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }



    @Override
    public void onStop() {
        super.onStop();
//        firebaseRecyclerAdapter.stopListening();
    }

}
