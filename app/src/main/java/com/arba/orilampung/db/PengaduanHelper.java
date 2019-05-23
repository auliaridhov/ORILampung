package com.arba.orilampung.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.arba.orilampung.entity.Pengaduan;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.ALAMAT;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.ALAMATTERLAPOR;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.EMAIL;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.HARAPANPELAPOR;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.JENISKELAMIN;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.KEPENDUDUKAN;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.KLASIFIKASI;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.KOTA;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.KOTAMELAPOR;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.LAPORMELALUI;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.MELAPORKEPADA;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.NAMAINSTANSI;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.NAMAPELAPOR;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.NOMORIDENTITAS;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.NOTLP;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.STATUS;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.SUDAHMELAPOR;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.TGLSKRG;
import static com.arba.orilampung.db.DatabaseContract.PengaduanColumns.TGLUPAYALAPOR;
import static com.arba.orilampung.db.DatabaseContract.TABLE_PENGADUAN;

public class PengaduanHelper {
    private static final String DATABASE_TABLE = TABLE_PENGADUAN;
    private static DatabaseHelper dataBaseHelper;
    private static PengaduanHelper INSTANCE;

    private static SQLiteDatabase database;

    private PengaduanHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public static PengaduanHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PengaduanHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        dataBaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Pengaduan> getAllNotes() {
        ArrayList<Pengaduan> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();
        Pengaduan pengaduan;
        if (cursor.getCount() > 0) {
            do {
                pengaduan = new Pengaduan();
                pengaduan.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));

                pengaduan.setTglSkrg(cursor.getString(cursor.getColumnIndexOrThrow(TGLSKRG)));
                pengaduan.setNamapelapor(cursor.getString(cursor.getColumnIndexOrThrow(NAMAPELAPOR)));
                pengaduan.setJeniskelamin(cursor.getString(cursor.getColumnIndexOrThrow(JENISKELAMIN)));
                pengaduan.setKependudukan(cursor.getString(cursor.getColumnIndexOrThrow(KEPENDUDUKAN)));
                pengaduan.setNomoridentitas(cursor.getString(cursor.getColumnIndexOrThrow(NOMORIDENTITAS)));
                pengaduan.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(EMAIL)));
                pengaduan.setNotlp(cursor.getString(cursor.getColumnIndexOrThrow(NOTLP)));
                pengaduan.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(STATUS)));
                pengaduan.setAlamat(cursor.getString(cursor.getColumnIndexOrThrow(ALAMAT)));
                pengaduan.setKota(cursor.getString(cursor.getColumnIndexOrThrow(KOTA)));
                pengaduan.setKlasifikasi(cursor.getString(cursor.getColumnIndexOrThrow(KLASIFIKASI)));
                pengaduan.setNamaInstansiTerlapor(cursor.getString(cursor.getColumnIndexOrThrow(NAMAINSTANSI)));
                pengaduan.setSudahMelaporkan(cursor.getString(cursor.getColumnIndexOrThrow(SUDAHMELAPOR)));
                pengaduan.setTglUpayaLapor(cursor.getString(cursor.getColumnIndexOrThrow(TGLUPAYALAPOR)));
                pengaduan.setLaporMelalui(cursor.getString(cursor.getColumnIndexOrThrow(LAPORMELALUI)));
                pengaduan.setMelaporKepada(cursor.getString(cursor.getColumnIndexOrThrow(MELAPORKEPADA)));
                pengaduan.setAlamatTerlapor(cursor.getString(cursor.getColumnIndexOrThrow(ALAMATTERLAPOR)));
                pengaduan.setKotaMelapor(cursor.getString(cursor.getColumnIndexOrThrow(KOTAMELAPOR)));
                pengaduan.setHarapanPelapor(cursor.getString(cursor.getColumnIndexOrThrow(HARAPANPELAPOR)));

                arrayList.add(pengaduan);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertPengaduan(Pengaduan note) {
        ContentValues args = new ContentValues();

        args.put(TGLSKRG, note.getTglSkrg());
        args.put(NAMAPELAPOR, note.getNamapelapor());
        args.put(JENISKELAMIN, note.getJeniskelamin());
        args.put(KEPENDUDUKAN, note.getKependudukan());
        args.put(NOMORIDENTITAS, note.getNomoridentitas());
        args.put(EMAIL, note.getEmail());
        args.put(NOTLP, note.getNotlp());
        args.put(STATUS, note.getStatus());
        args.put(ALAMAT, note.getAlamat());
        args.put(KOTA, note.getKota());
        args.put(KLASIFIKASI, note.getKlasifikasi());
        args.put(NAMAINSTANSI, note.getNamaInstansiTerlapor());
        args.put(SUDAHMELAPOR, note.getSudahMelaporkan());
        args.put(TGLUPAYALAPOR, note.getTglUpayaLapor());
        args.put(LAPORMELALUI, note.getLaporMelalui());
        args.put(MELAPORKEPADA, note.getMelaporKepada());
        args.put(ALAMATTERLAPOR, note.getAlamatTerlapor());
        args.put(KOTAMELAPOR, note.getKotaMelapor());
        args.put(HARAPANPELAPOR, note.getHarapanPelapor());
        return database.insert(DATABASE_TABLE, null, args);
    }

//    public int updateNote(Note note) {
//        ContentValues args = new ContentValues();
//        args.put(TITLE, note.getTitle());
//        args.put(DESCRIPTION, note.getDescription());
//        args.put(DATE, note.getDate());
//        return database.update(DATABASE_TABLE, args, _ID + "= '" + note.getId() + "'", null);
//    }
//
//    public int deleteNote(int id) {
//        return database.delete(TABLE_NAME, _ID + " = '" + id + "'", null);
//    }

}
