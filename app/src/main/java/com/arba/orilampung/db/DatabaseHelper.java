package com.arba.orilampung.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbpengaduan";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_PENGADUAN = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_PENGADUAN,
            DatabaseContract.PengaduanColumns._ID,

            DatabaseContract.PengaduanColumns.TGLSKRG,

            DatabaseContract.PengaduanColumns.NAMAPELAPOR,
            DatabaseContract.PengaduanColumns.JENISKELAMIN,
            DatabaseContract.PengaduanColumns.KEPENDUDUKAN,
            DatabaseContract.PengaduanColumns.NOMORIDENTITAS,
            DatabaseContract.PengaduanColumns.EMAIL,
            DatabaseContract.PengaduanColumns.NOTLP,
            DatabaseContract.PengaduanColumns.STATUS,
            DatabaseContract.PengaduanColumns.ALAMAT,
            DatabaseContract.PengaduanColumns.KOTA,
            DatabaseContract.PengaduanColumns.KLASIFIKASI,
            DatabaseContract.PengaduanColumns.NAMAINSTANSI,
            DatabaseContract.PengaduanColumns.SUDAHMELAPOR,
            DatabaseContract.PengaduanColumns.TGLUPAYALAPOR,
            DatabaseContract.PengaduanColumns.LAPORMELALUI,
            DatabaseContract.PengaduanColumns.MELAPORKEPADA,
            DatabaseContract.PengaduanColumns.ALAMATTERLAPOR,
            DatabaseContract.PengaduanColumns.KOTAMELAPOR,
            DatabaseContract.PengaduanColumns.HARAPANPELAPOR

            );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PENGADUAN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_PENGADUAN);
        onCreate(db);
    }

}
