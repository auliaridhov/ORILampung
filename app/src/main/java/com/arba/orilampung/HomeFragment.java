package com.arba.orilampung;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


//test first commit

public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1 ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CardView card0, card1, card2, card3, card4, card5;
    private View myMainView;
    private DatabaseReference userRef;
    private DatabaseReference dbRef;
    ImageView artikel;
    Animation fade_in, fade_out;
    ViewFlipper viewFlipper;
    ImageView flipper1, flipper2, flipper3, highligh1, highligh2, highligh3, highligh4;
    Dialog dialog;
    RelativeLayout alamat;
    private String gambar1, gambar2, gambar3, link1, link2, link3;

    private OnFragmentInteractionListener mListener;
    private Context getAppicationContext;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final FirebaseAuth mAuth = FirebaseAuth.getInstance();

        myMainView = inflater.inflate(R.layout.fragment_home, container, false);
        card0 = myMainView.findViewById(R.id.card0);
        card1 = myMainView.findViewById(R.id.card1);
        card2 = myMainView.findViewById(R.id.card2);
        card3 = myMainView.findViewById(R.id.card3);
        card4 = myMainView.findViewById(R.id.card4);
        card5 = myMainView.findViewById(R.id.card5);
        highligh1 = myMainView.findViewById(R.id.img1);
        highligh2 = myMainView.findViewById(R.id.img2);
        highligh3 = myMainView.findViewById(R.id.img3);
        highligh4 = myMainView.findViewById(R.id.img4);
        //viewFlipper = (ViewFlipper) myMainView.findViewById(R.id.backvf);
        userRef = FirebaseDatabase.getInstance().getReference().child("User");
        dbRef = FirebaseDatabase.getInstance().getReference().child("artikel");
        alamat = myMainView.findViewById(R.id.layoutAlamat);
        artikel = myMainView.findViewById(R.id.linkArtikel);

        dialog = new Dialog(getContext());

        //flipper
//        int images [] = {R.drawable.fliper1, R.drawable.fliper2, R.drawable.fliper3};
//        for (int image:images){
//            flipperImage(image);
//        }

//        fade_in = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_in);
//        fade_out = AnimationUtils.loadAnimation(getActivity(), android.R.anim.fade_out);
//        viewFlipper.setInAnimation(fade_in);
//        viewFlipper.setOutAnimation(fade_out);
//        viewFlipper.setAutoStart(true);
//        viewFlipper.setFlipInterval(4000);
//        viewFlipper.startFlipping();

        highligh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoEdukasiActivity.class);
                intent.putExtra("URL","VYg6DQGkrFc");
                intent.putExtra("judul","Apa itu Ombudsman?");
                startActivity(intent);
            }
        });

        highligh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoEdukasiActivity.class);
                intent.putExtra("URL","H40S6DeIeXY");
                intent.putExtra("judul","Apa itu Maladministrasi?");
                startActivity(intent);
            }
        });

        highligh3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoEdukasiActivity.class);
                intent.putExtra("URL","6Yc9HG_f9FE");
                intent.putExtra("judul","Logo Ombudsman?");
                startActivity(intent);
            }
        });

        highligh4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), VideoEdukasiActivity.class);
                intent.putExtra("URL","2uNeipRky");
                intent.putExtra("judul","Tata Cara Penerimaan dan Verifikasi Laporan.");
                startActivity(intent);
            }
        });
//
//        flipper2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), ActivityFAQ.class);
//                startActivity(intent);
//            }
//        });
//
//        flipper3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), profilmmbudsman.class);
//                startActivity(intent);
//            }
//        });

        alamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/maps/place/Kantor+Ombudsman+RI+Perwakilan+" +
                                "Provinsi+Lampung/@-5.4273385,105.2718822,17z/data=!3m1!4b1!4m5!3m4!" +
                                "1s0x2e40dbcaf6b32379:0xa1ec813945bdd0b8!8m2!3d-5.4273385!4d105.2740709"));
                startActivity(intent);
            }
        });

        artikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://ombudsman.go.id/perwakilan/perwakilan/profil/19-00-OP"));
                startActivity(intent);
            }
        });


//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    gambar1 = dataSnapshot.child("gambar1").getValue().toString();
//                    gambar2 = dataSnapshot.child("gambar2").getValue().toString();
//                    gambar3 = dataSnapshot.child("gambar3").getValue().toString();
//                    link1 = dataSnapshot.child("link1").getValue().toString();
//                    link2 = dataSnapshot.child("link2").getValue().toString();
//                    link3 = dataSnapshot.child("link3").getValue().toString();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        Picasso.get()
//                .load(gambar1).placeholder(R.drawable.placeholderwait).error(R.drawable.placeholder_error)
//                .into(flipper1);
//        Picasso.get()
//                .load(gambar2).placeholder(R.drawable.placeholderwait).error(R.drawable.placeholder_error)
//                .into(flipper2);
//        Picasso.get()
//                .load(gambar3).placeholder(R.drawable.placeholderwait).error(R.drawable.placeholder_error)
//                .into(flipper3);
//
//        flipper1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isConnected()) {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                    intent.setData(Uri.parse(link1));
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getContext(), "Terjadi kesalahan, cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        flipper2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isConnected()) {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                    intent.setData(Uri.parse(link2));
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getContext(), "Terjadi kesalahan, cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        flipper3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isConnected()) {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                    intent.setData(Uri.parse(link3));
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getContext(), "Terjadi kesalahan, cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        card0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PanduanActivity.class);
                startActivity(intent);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup();
            }

        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                        Intent intent = new Intent(getContext(), LacakAduanActivity.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(getContext(), "Tunggu verifikasi akun anda untuk mengakses layanan ini", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                    else {
                        Toast.makeText(getContext(), "Silahkan masuk atau registrasi untuk akses layanan ini!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Terjadi kesalahan, cek koneksi internet anda!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card3Intent = new Intent(getContext(), ActivityMedia.class);
                startActivity(card3Intent);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent card4Intent = new Intent(getContext(), ProfilOmbudsman.class);
                startActivity(card4Intent);
            }
        });

        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), CatatanActivity.class);
                startActivity(intent);

            }
        });

        // Inflate the layout for this fragment
        return myMainView;
    }

    //animatioan flipper
//    public void flipperImage (int image){
//        ImageView imageView = new ImageView(getActivity());
//        imageView.setBackgroundResource(image);
//
//        viewFlipper.addView(imageView);
//        viewFlipper.setFlipInterval(4000);
//        viewFlipper.setAutoStart(true);
//
//        viewFlipper.setInAnimation(getActivity(), android.R.anim.fade_in);
//        viewFlipper.setOutAnimation(getActivity(), android.R.anim.fade_out);
//
//    }

    private void ShowPopup() {
        ImageView btnClose;
        CardView card1, card2;
        dialog.setContentView(R.layout.customepopup);
        btnClose = (ImageView) dialog.findViewById(R.id.btnClose);
        card1 = (CardView) dialog.findViewById(R.id.card1);
        card2 = (CardView) dialog.findViewById(R.id.card2);

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0721251373"));
                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.CALL_PHONE},
                            MY_PERMISSIONS_REQUEST_CALL_PHONE);

                    // MY_PERMISSIONS_REQUEST_CALL_PHONE is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                } else {
                    //You already have permission
                    try {
                        startActivity(intent);
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:081373899900"));
                startActivity(intent);
//                Uri uri = Uri.parse("smsto:" + "081373899900");
//                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_TEXT, "Lapor Ombudsman!!!");
//                i.setPackage("com.whatsapp");
//                startActivity(Intent.createChooser(i, " Pilih WhatsApp"));
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
