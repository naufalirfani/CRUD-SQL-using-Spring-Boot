package com.javan.crudwilayah.data;

public class DataDesa {

    public Integer desaid;
    public String namaDesa;
    public Integer kecamatanid;
    public String namaKecamatan;
    public Integer kabupatenid;
    public String namaKabupaten;
    public Integer provinsiid;
    public String namaProvinsi;

    public DataDesa(int desaid, String namaDesa,
                    int kecamatanid, String namaKecamatan,
                    int kabupatenid, String namaKabupaten,
                    int provinsiid, String namaProvinsi){
        this.desaid = desaid;
        this.namaDesa = namaDesa;
        this.kecamatanid = kecamatanid;
        this.namaKecamatan = namaKecamatan;
        this.kabupatenid = kabupatenid;
        this.namaKabupaten = namaKabupaten;
        this.provinsiid = provinsiid;
        this.namaProvinsi = namaProvinsi;
    }
}
