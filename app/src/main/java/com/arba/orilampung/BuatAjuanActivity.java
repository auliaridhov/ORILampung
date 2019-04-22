package com.arba.orilampung;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class BuatAjuanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference dbref;
    private FirebaseAuth mAuth;
    private Button button1;
    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView11;
    private TextView textView12;
    private Spinner spinner;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private Spinner spinner6;
    private CheckBox checkBox;
    private RadioGroup radioGroup;
    private RadioGroup radioGroup2;
    private RadioButton radioButton;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button batal;
    private ImageView imageView;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private TextView tvDateResult;
    private ImageButton btDatePicker;
    String temp_key;
    String[] bankNames={"Korban Langsung","Kuasa Korban","Badan Hukum/Organisasi"};
    String[] bankNames2={"Tidak Menikah", "Menikah", "Duda/Janda"};
    String[] bankNames3={"Kejaksaan", "Lembaga Peradilan", "Komisi Negara/Lembaga Negara Non Struktual", "BUMN/BUMD",
                            "Perguruan Tinggi Negeri", "Dewan Perwakilan Rakyat", "Tentara Nasional Indonesia",
                            "Perbankan", "Lembaga Pemerintah Non Kementrian", "Pemerintah Daerah", "Badan Pertanahan Nasional",
                            "Kepolisian", "Instansi Pemerintah/Kementrian", "Badan Pemerilksa Keuangan", "Badan Swasta/Perorangan",
                            "Perorangan", "Lembaga Pendidikan Negeri", "Rumah Sakit Pemerintah", "Rumah Sakit Swasta",
                            "Lembaga Pendidikan Swasta", "Perguruan Tinggi Swasta"};
    String[] bankNames4={"Surat", "Datang Langsung", "Email", "Telepon"};
    String[] bankNames5={"Lampung Selatan", "Lampung Tengah", "Lampung Utara", "Lampung Barat", "Tulang Bawang", "Tanggamus",
            "Lampung Timur", "Way Kanan", "Pesawaran", "Pringsewu", "Mesuji", "Tulang Bawang Barat", "Pesisir Barat", "Bandar Lampung", "Metro"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_ajuan);

        Button button1 = (Button) findViewById(R.id.button1);
        Button batal = (Button) findViewById(R.id.btnBatal);
        textView = (TextView) findViewById(R.id.et_nama);
        final TextView textView2 = (TextView) findViewById(R.id.et_identitas);
        final TextView textView3 = (TextView) findViewById(R.id.et_email);
        final TextView textView4 = (TextView) findViewById(R.id.et_noHp);
        final TextView textView5 = (TextView) findViewById(R.id.et_alamat);
         textView7 = (TextView) findViewById(R.id.et_namaInstansi);
        final TextView textView9 = (TextView) findViewById(R.id.et_laporKpd);
        final TextView textView10 = (TextView) findViewById(R.id.et_alamatTerlapor);
        final TextView textView12 = (TextView) findViewById(R.id.et_harapan);
        final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        final Spinner spiner = (Spinner) findViewById(R.id.simpleSpinner);
        final Spinner spiner2 = (Spinner) findViewById(R.id.simpleSpinner2);
        final Spinner spiner3 = (Spinner) findViewById(R.id.simpleSpinner3);
        final Spinner spiner4 = (Spinner) findViewById(R.id.simpleSpinner4);
        final Spinner spiner5 = (Spinner) findViewById(R.id.simpleSpinner5);
        final Spinner spiner6 = (Spinner) findViewById(R.id.simpleSpinner6);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        final RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.myRadioGroup2);
        final RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.myRadioGroup3);

        final ImageView imageView = (ImageView) findViewById(R.id.image);
        tvDateResult = (TextView) findViewById(R.id.tv_dateresult);
        btDatePicker = (ImageButton) findViewById(R.id.bt_datepicker);

        mAuth = FirebaseAuth.getInstance();

        //spinner
        spiner.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner.setAdapter(aa);

        spiner2.setOnItemSelectedListener(this);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames2);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner2.setAdapter(aa2);

        spiner3.setOnItemSelectedListener(this);
        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames3);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner3.setAdapter(aa3);

        spiner4.setOnItemSelectedListener(this);
        ArrayAdapter aa4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames4);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner4.setAdapter(aa4);

        spiner5.setOnItemSelectedListener(this);
        ArrayAdapter aa5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames5);
        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner5.setAdapter(aa5);

        spiner6.setOnItemSelectedListener(this);
        ArrayAdapter aa6 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,bankNames5);
        aa6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner6.setAdapter(aa6);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/ombudsman-ri-lampung." +
                "appspot.com/o/tatacara.png?alt=media&token=ea185a0f-b5dd-4bda-beae-5ec6296d7d82")
                .placeholder(R.drawable.placeholderwait2).error(R.drawable.placeholder_error)
                .into(imageView);

        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuatAjuanActivity.this, NavActivity.class);
                startActivity(intent);
            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(BuatAjuanActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Kirim Form Aduan");
                builder.setMessage("Apakah anda yakin dengan form yang sudah diisi?");
                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // get selected radio button from radioGroup
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        int selectedId2 = radioGroup2.getCheckedRadioButtonId();
                        int selectedId3 = radioGroup3.getCheckedRadioButtonId();

                        // find the radiobutton by returned id
                        radioButton = (RadioButton) findViewById(selectedId);
                        radioButton2 = (RadioButton) findViewById(selectedId2);
                        radioButton3 = (RadioButton) findViewById(selectedId3);



                        if (TextUtils.isEmpty(textView.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Nama anda kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView2.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Nomor identitas kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView3.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Email kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView4.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Nomor telepon kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView5.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Alamat kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView7.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Nama instansi terlapor kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if ((tvDateResult.getText().equals("Tanggal dipilih :"))) {
                            Toast.makeText(BuatAjuanActivity.this, "Tanggal upaya terakhir kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView9.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Nama terlapor kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else if (TextUtils.isEmpty(textView10.getText().toString())) {
                            Toast.makeText(BuatAjuanActivity.this, "Alamat terlapor kosong",
                                    Toast.LENGTH_SHORT).show();
                        } else {

                            String value =
                                    "Data Pelapor" + "\n\n" +
                                            "Nama : " + textView.getText() + "\n" +
                                            "Kategori Pelapor : " + spiner.getSelectedItem() + "\n" +
                                            "Jenis Kelamin : " + radioButton.getText() + "\n" +
                                            "Pelapor adalah : " + radioButton2.getText() + "\n" +
                                            "Nomor Identitas KTP/Pasport/SIM: " + textView2.getText() + "\n" +
                                            "Email : " + textView3.getText() + "\n" +
                                            "Nomor Telepon : " + textView4.getText() + "\n" +
                                            "Status Perkawinan : " + spiner2.getSelectedItem() + "\n" +
                                            "Alamat : " + textView5.getText() + "\n" +
                                            "Kabupaten/Kota : " + spiner5.getSelectedItem() + "\n\n" +
                                            "Data Pengaduan" + "\n\n" +
                                            "Klarifikasi Instansi Terlapor : " + spiner3.getSelectedItem() + "\n" +
                                            "Nama Instansi Terlapor : " + textView7.getText() + "\n" +
                                            "sudah melaporkan keluhan Anda kepada Instansi Terlapor : " + radioButton3.getText() + "\n" +
                                            "Kapan upaya terakhir yang disampaikan kepada Terlapor: " + tvDateResult.getText() + "\n" +
                                            "Melapor melalui : " + spiner4.getSelectedItem() + "\n" +
                                            "Melpor Kepada : " + textView9.getText() + "\n" +
                                            "Alamat Terlapor : " + textView10.getText() + "\n" +
                                            "Kabupaten/Kota Terlapor : " + spiner6.getSelectedItem() + "\n" +
                                            "Rahasiakan Data Pelapor : " + checkBox.isChecked() + "\n" +
                                            "Harapan Pelapor : " + textView12.getText();

                            Intent email = new Intent(Intent.ACTION_SEND);
                            email.putExtra(Intent.EXTRA_EMAIL, new String[]{"rifkiarba@yahoo.co.id"});
                            email.putExtra(Intent.EXTRA_SUBJECT, "Form Pengaduan");
                            email.putExtra(Intent.EXTRA_TEXT, value);

                            //need this to prompts email client only
                            email.setType("message/rfc822");
                            try {
                                // the user can choose the email client
                                SimpankeDataBase();
                                startActivity(Intent.createChooser(email, "Choose an email client"));

                            } catch (android.content.ActivityNotFoundException ex) {
                                Toast.makeText(BuatAjuanActivity.this, "No email client installed.",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
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

    }

    private void SimpankeDataBase() {
        final String uid = mAuth.getCurrentUser().getUid();
        dbref = FirebaseDatabase.getInstance().getReference().child("Pengaduan");
        temp_key = dbref.push().getKey();

        final HashMap PengaduanMap = new HashMap();
        PengaduanMap.put("nama_pelapor", textView.getText().toString());
        PengaduanMap.put("instansi_terlapor", textView7.getText().toString());
        PengaduanMap.put("status", "Pengaduan diterima");


        dbref.child(uid).child(temp_key).updateChildren(PengaduanMap)
                .addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(BuatAjuanActivity.this, "Berhasil simpan", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tvDateResult.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
