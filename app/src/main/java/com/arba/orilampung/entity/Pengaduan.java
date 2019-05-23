package com.arba.orilampung.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Pengaduan implements Parcelable {
    private int id;
    private String namapelapor, jeniskelamin, kependudukan, nomoridentitas, email, notlp, status,
            alamat, kota, klasifikasi, namaInstansiTerlapor, sudahMelaporkan, tglUpayaLapor, laporMelalui;
    private String melaporKepada, alamatTerlapor, kotaMelapor, harapanPelapor;
    private String tglSkrg;



    public Pengaduan() {
    }

    private Pengaduan(Parcel in) {
        id = in.readInt();
        namapelapor = in.readString();
        jeniskelamin = in.readString();
        kependudukan = in.readString();
        nomoridentitas = in.readString();
        email = in.readString();
        notlp = in.readString();
        status = in.readString();
        alamat = in.readString();
        kota = in.readString();
        klasifikasi = in.readString();
        namaInstansiTerlapor = in.readString();
        sudahMelaporkan = in.readString();
        tglUpayaLapor = in.readString();
        laporMelalui = in.readString();
        melaporKepada = in.readString();
        alamatTerlapor = in.readString();
        kotaMelapor = in.readString();
        harapanPelapor = in.readString();
        tglSkrg = in.readString();
    }

    public static final Creator<Pengaduan> CREATOR = new Creator<Pengaduan>() {
        @Override
        public Pengaduan createFromParcel(Parcel in) {
            return new Pengaduan(in);
        }

        @Override
        public Pengaduan[] newArray(int size) {
            return new Pengaduan[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamapelapor() {
        return namapelapor;
    }
    public String getTglSkrg() {
        return tglSkrg;
    }

    public void setTglSkrg(String tglSkrg) {
        this.tglSkrg = tglSkrg;
    }

    public void setNamapelapor(String namapelapor) {
        this.namapelapor = namapelapor;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getKependudukan() {
        return kependudukan;
    }

    public void setKependudukan(String kependudukan) {
        this.kependudukan = kependudukan;
    }

    public String getNomoridentitas() {
        return nomoridentitas;
    }

    public void setNomoridentitas(String nomoridentitas) {
        this.nomoridentitas = nomoridentitas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotlp() {
        return notlp;
    }

    public void setNotlp(String notlp) {
        this.notlp = notlp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKlasifikasi() {
        return klasifikasi;
    }

    public void setKlasifikasi(String klasifikasi) {
        this.klasifikasi = klasifikasi;
    }

    public String getNamaInstansiTerlapor() {
        return namaInstansiTerlapor;
    }

    public void setNamaInstansiTerlapor(String namaInstansiTerlapor) {
        this.namaInstansiTerlapor = namaInstansiTerlapor;
    }

    public String getSudahMelaporkan() {
        return sudahMelaporkan;
    }

    public void setSudahMelaporkan(String sudahMelaporkan) {
        this.sudahMelaporkan = sudahMelaporkan;
    }

    public String getTglUpayaLapor() {
        return tglUpayaLapor;
    }

    public void setTglUpayaLapor(String tglUpayaLapor) {
        this.tglUpayaLapor = tglUpayaLapor;
    }

    public String getLaporMelalui() {
        return laporMelalui;
    }

    public void setLaporMelalui(String laporMelalui) {
        this.laporMelalui = laporMelalui;
    }

    public String getMelaporKepada() {
        return melaporKepada;
    }

    public void setMelaporKepada(String melaporKepada) {
        this.melaporKepada = melaporKepada;
    }

    public String getAlamatTerlapor() {
        return alamatTerlapor;
    }

    public void setAlamatTerlapor(String alamatTerlapor) {
        this.alamatTerlapor = alamatTerlapor;
    }

    public String getKotaMelapor() {
        return kotaMelapor;
    }

    public void setKotaMelapor(String kotaMelapor) {
        this.kotaMelapor = kotaMelapor;
    }

    public String getHarapanPelapor() {
        return harapanPelapor;
    }

    public void setHarapanPelapor(String harapanPelapor) {
        this.harapanPelapor = harapanPelapor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(namapelapor);
        parcel.writeString(jeniskelamin);
        parcel.writeString(kependudukan);
        parcel.writeString(nomoridentitas);
        parcel.writeString(email);
        parcel.writeString(notlp);
        parcel.writeString(status);
        parcel.writeString(alamat);
        parcel.writeString(kota);
        parcel.writeString(klasifikasi);
        parcel.writeString(namaInstansiTerlapor);
        parcel.writeString(sudahMelaporkan);
        parcel.writeString(tglUpayaLapor);
        parcel.writeString(laporMelalui);
        parcel.writeString(melaporKepada);
        parcel.writeString(alamatTerlapor);
        parcel.writeString(kotaMelapor);
        parcel.writeString(harapanPelapor);
        parcel.writeString(tglSkrg);
    }
}
