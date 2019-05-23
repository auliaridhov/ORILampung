package com.arba.orilampung;

import com.arba.orilampung.entity.Pengaduan;

import java.util.ArrayList;

public interface LoadPengaduanCallback {
    void preExecute();
    void postExecute(ArrayList<Pengaduan> pengaduan);
}
