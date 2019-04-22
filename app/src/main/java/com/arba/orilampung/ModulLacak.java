package com.arba.orilampung;

public class ModulLacak {
    public String instansi_terlapor, nama_pelapor, status;



    public ModulLacak(){

    }

    public ModulLacak(String instansi_terlapor, String nama_pelapor, String status) {
        this.instansi_terlapor = instansi_terlapor;
        this.nama_pelapor = nama_pelapor;
        this.status = status;
    }

    public String getInstansi_terlapor() {
        return instansi_terlapor;
    }

    public void setInstansi_terlapor(String instansi_terlapor) {
        this.instansi_terlapor = instansi_terlapor;
    }

    public String getNama_pelapor() {
        return nama_pelapor;
    }

    public void setNama_pelapor(String nama_pelapor) {
        this.nama_pelapor = nama_pelapor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}