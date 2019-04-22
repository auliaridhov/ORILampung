package com.arba.orilampung;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AboutLoginFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutLoginFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View myMainView;
    private Button logout, upload, ambilGambar;
    private TextView nama, noIdentitas, textView, tvUnggah, tvIdentitas;
    private DatabaseReference userRef;
    private static final int Gallery_Pick = 1;
    private DatabaseReference dbRef;
    private String ref;
    private ProgressDialog loadingBar;
    private Uri ImageUri;
    private String gambarStr;
    private ImageView gambarIdentitas;
    private StorageReference PostsImageReference;
    private String saveCurrentDate, saveCurrentTime, postRandomName;
    private FirebaseAuth mAuth;

    private OnFragmentInteractionListener mListener;

    public AboutLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutLoginFragment newInstance(String param1, String param2) {
        AboutLoginFragment fragment = new AboutLoginFragment();
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
        // Inflate the layout for this fragment
        myMainView = inflater.inflate(R.layout.fragment_about_login, container, false);
        loadingBar = new ProgressDialog(getContext());
        mAuth = FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        PostsImageReference = FirebaseStorage.getInstance().getReference();
        dbRef = FirebaseDatabase.getInstance().getReference().child("User").child(userID);
        logout = myMainView.findViewById(R.id.logout);
        upload = myMainView.findViewById(R.id.upload);
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String userId = mAuth.getCurrentUser().getUid();
        nama = myMainView.findViewById(R.id.nama);
        noIdentitas = myMainView.findViewById(R.id.noIdentitas);
        textView = myMainView.findViewById(R.id.verif);
        gambarIdentitas = myMainView.findViewById(R.id.gambarIdentitas);
        ambilGambar = myMainView.findViewById(R.id.ambilgambar);
        tvUnggah = myMainView.findViewById(R.id.tvUnggah);
        tvIdentitas = myMainView.findViewById(R.id.tvIdentitas);
        upload.setVisibility(View.INVISIBLE);
        tvUnggah.setVisibility(View.INVISIBLE);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoringImageToFirebaseStorage();
            }
        });


        userRef = FirebaseDatabase.getInstance().getReference().child("User");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setCancelable(true);
                builder.setTitle("Keluar dari akun");
                builder.setMessage("Dengan anda keluar, anda tidak bisa melakukan pengaduan. Apakah anda yakin ingin keluar?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        loadingBar.setTitle("Keluar Akun");
                        loadingBar.setMessage("Mohon tunggu sebentar...");
                        loadingBar.show();
                        mAuth.signOut();
                        SendUserToLoginActivity();
                    }
                })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.create().show();
            }
        });

        ambilGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        userRef.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String username = dataSnapshot.child("fullname").getValue().toString();
                    nama.setText(username);
                    String identitas = dataSnapshot.child("identitas").getValue().toString();
                    noIdentitas.setText(identitas);
                    String verif = dataSnapshot.child("verif").getValue().toString();
                    String bukti = dataSnapshot.child("buktiidentitas").getValue().toString();
                    if(bukti.equals("belum")){
                        ambilGambar.setVisibility(View.VISIBLE);
                        gambarIdentitas.setVisibility(View.VISIBLE);
                        textView.setText("Akun anda belum terverifikasi, silahkan upload bukti identitas sesuai dengan nomor identitas saat melakukan registrasi.");
                    }

                    else if(bukti.equals("ubah")){
                        ambilGambar.setVisibility(View.VISIBLE);
                        gambarIdentitas.setVisibility(View.VISIBLE);
                        textView.setText("Maaf bukti identitas anda tidak sesuai atau gambar rusak, silahkan unggah ulang!");
                    }
                    else {
                        upload.setVisibility(View.INVISIBLE);
                        ambilGambar.setVisibility(View.INVISIBLE);
                        gambarIdentitas.setVisibility(View.INVISIBLE);
                        tvUnggah.setVisibility(View.INVISIBLE);
                        tvIdentitas.setVisibility(View.INVISIBLE);
                        textView.setText("Bukti identitas anda sudah di unggah, Silahkan tunggu. Apabila akun anda belum terverifikasi selama 2x24 jam, hubungi kami pada menu beranda.");
                    }
                    if(verif.equals("sudah")) {
                        textView.setVisibility(View.INVISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return myMainView;
    }

    private void OpenGallery() {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, Gallery_Pick);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Gallery_Pick && resultCode == RESULT_OK && data!=null){
            ImageUri = data.getData();
            gambarIdentitas.setImageURI(ImageUri);
            upload.setVisibility(View.VISIBLE);
            tvUnggah.setVisibility(View.VISIBLE);
        }
    }

    private void StoringImageToFirebaseStorage() {
        loadingBar.setTitle("Upload Foto Ke Database");
        loadingBar.setMessage("Mohon tunggu sebentar...");
        loadingBar.show();
        Calendar calFordDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
        saveCurrentDate = currentDate.format(calFordDate.getTime());
        Calendar calFordTime = Calendar.getInstance();
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calFordTime.getTime());
        postRandomName = saveCurrentDate + saveCurrentTime;
        final StorageReference filepath = PostsImageReference.child("Bukti Identitas").child(ImageUri.getLastPathSegment() + postRandomName + ".jpg");
        filepath.putFile(ImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            gambarStr = uri.toString();
                            Toast.makeText(getContext(), "Berhasil Menggunggah!", Toast.LENGTH_SHORT).show();
                            SavingPostInformationToDatabase();
                            loadingBar.dismiss();
                        }
                    });
                } else {
                    String message = task.getException().getMessage();
                    Toast.makeText(getContext(), "Error occured: " + message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void SavingPostInformationToDatabase() {
        dbRef.child("buktiidentitas").setValue(gambarStr).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getContext(), "Berhasil Memperbaharui", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(getContext(), NavActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
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
