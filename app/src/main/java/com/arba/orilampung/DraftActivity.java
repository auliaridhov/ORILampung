package com.arba.orilampung;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.arba.orilampung.adapter.PengaduanAdapter;
import com.arba.orilampung.db.PengaduanHelper;
import com.arba.orilampung.entity.Pengaduan;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.arba.orilampung.BuatAjuanActivity.REQUEST_UPDATE;

public class DraftActivity extends AppCompatActivity implements View.OnClickListener, LoadPengaduanCallback {
    private RecyclerView rvNotes;
    private ProgressBar progressBar;
    private FloatingActionButton fabAdd;
    private static final String EXTRA_STATE = "EXTRA_STATE";
    private PengaduanAdapter adapter;
    private PengaduanHelper pengaduanHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Notes");
        rvNotes = findViewById(R.id.rv_notes);
        rvNotes.setLayoutManager(new LinearLayoutManager(this));
        rvNotes.setHasFixedSize(true);
        pengaduanHelper = PengaduanHelper.getInstance(getApplicationContext());
        pengaduanHelper.open();
        progressBar = findViewById(R.id.progressbar);
//        fabAdd = findViewById(R.id.fab_add);
//        fabAdd.setOnClickListener(this);
        adapter = new PengaduanAdapter(this);
        rvNotes.setAdapter(adapter);
        if (savedInstanceState == null) {
            new LoadNotesAsync(pengaduanHelper, this).execute();
        } else {
            ArrayList<Pengaduan> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                adapter.setListNotes(list);
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getListPengaduan());
    }
    @Override
    public void onClick(View view) {
//        if (view.getId() == R.id.fab_add) {
//            Intent intent = new Intent(MainActivity.this, NoteAddUpdateActivity.class);
//            startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD);
//        }
    }

    @Override
    public void preExecute() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
    @Override
    public void postExecute(ArrayList<Pengaduan> pengaduans) {
        progressBar.setVisibility(View.INVISIBLE);
        adapter.setListNotes(pengaduans);
    }

    private static class LoadNotesAsync extends AsyncTask<Void, Void, ArrayList<Pengaduan>> {

        private final WeakReference<PengaduanHelper> weakNoteHelper;
        private final WeakReference<LoadPengaduanCallback> weakCallback;

        private LoadNotesAsync(PengaduanHelper noteHelper, LoadPengaduanCallback callback) {
            weakNoteHelper = new WeakReference<>(noteHelper);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            weakCallback.get().preExecute();
        }

        @Override
        protected ArrayList<Pengaduan> doInBackground(Void... voids) {

            return weakNoteHelper.get().getAllNotes();
        }

        @Override
        protected void onPostExecute(ArrayList<Pengaduan> notes) {
            super.onPostExecute(notes);

            weakCallback.get().postExecute(notes);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            if (requestCode == BuatAjuanActivity.REQUEST_ADD) {
                if (resultCode == BuatAjuanActivity.RESULT_ADD) {
                    Pengaduan note = data.getParcelableExtra(BuatAjuanActivity.EXTRA_NOTE);
                    adapter.addItem(note);
                    rvNotes.smoothScrollToPosition(adapter.getItemCount() - 1);
                    showSnackbarMessage("Satu item berhasil ditambahkan");
                }
            }
            else if (requestCode == REQUEST_UPDATE) {
                if (resultCode == BuatAjuanActivity.RESULT_UPDATE) {
                    Pengaduan note = data.getParcelableExtra(BuatAjuanActivity.EXTRA_NOTE);
                    int position = data.getIntExtra(BuatAjuanActivity.EXTRA_POSITION, 0);
                    adapter.updateItem(position, note);
                    rvNotes.smoothScrollToPosition(position);
                    showSnackbarMessage("Satu item berhasil diubah");
                }
                else if (resultCode == BuatAjuanActivity.RESULT_DELETE) {
                    int position = data.getIntExtra(BuatAjuanActivity.EXTRA_POSITION, 0);
                    adapter.removeItem(position);
                    showSnackbarMessage("Satu item berhasil dihapus");
                }
            }

        }
    }

    private void showSnackbarMessage(String message) {
        Snackbar.make(rvNotes, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pengaduanHelper.close();
    }
}
