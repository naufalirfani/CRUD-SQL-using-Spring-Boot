package com.javan.crudwilayah.data;

public class DataKabupaten {

    public Integer kabupatenid;
    public String namaKabupaten;
    public Integer provinsiid;
    public String namaProvinsi;

    public DataKabupaten(int kabupatenid, String namaKabupaten, int provinsiid, String namaProvinsi){
        this.kabupatenid = kabupatenid;
        this.namaKabupaten = namaKabupaten;
        this.provinsiid = provinsiid;
        this.namaProvinsi = namaProvinsi;
    }
}
