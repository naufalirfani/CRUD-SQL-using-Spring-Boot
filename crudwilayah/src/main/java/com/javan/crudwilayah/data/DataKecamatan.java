package com.javan.crudwilayah.data;

public class DataKecamatan {

    public Integer kecamatanid;
    public String namaKecamatan;
    public Integer kabupatenid;
    public String namaKabupaten;
    public Integer provinsiid;
    public String namaProvinsi;

    public DataKecamatan(int kecamatanid, String namaKecamatan, int kabupatenid, String namaKabupaten, int provinsiid, String namaProvinsi){
        this.kecamatanid = kecamatanid;
        this.namaKecamatan = namaKecamatan;
        this.kabupatenid = kabupatenid;
        this.namaKabupaten = namaKabupaten;
        this.provinsiid = provinsiid;
        this.namaProvinsi = namaProvinsi;
    }
}
