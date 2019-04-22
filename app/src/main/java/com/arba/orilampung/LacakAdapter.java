package com.arba.orilampung;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class LacakAdapter extends RecyclerView.Adapter<LacakAdapter.ReviewViewHolder>{

    private List<ModulLacak> reviewList;
    private FirebaseAuth mAuth;
    private DatabaseReference usersDatabaseReference;


    public LacakAdapter(List<ModulLacak> reviewList){
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.lacak_layout, viewGroup, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder reviewViewHolder, int i) {


        ModulLacak modulReview = reviewList.get(i);

        reviewViewHolder.setInstansi_terlapor(modulReview.getInstansi_terlapor());
        reviewViewHolder.setNama_pelapor(modulReview.getNama_pelapor());
        reviewViewHolder.setStatus(modulReview.getStatus());


    }

    @Override
    public int getItemCount() {
        return 0;
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
}
