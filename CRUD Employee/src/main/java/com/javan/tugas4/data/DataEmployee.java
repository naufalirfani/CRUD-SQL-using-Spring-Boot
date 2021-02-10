package com.javan.tugas4.data;

public class DataEmployee {

    public Integer id;
    public String nama;
    public Integer atasanId;
    public String companyId;

    public DataEmployee(int id, String nama, int atasanId, String companyId){
        this.id = id;
        this.nama = nama;
        this.atasanId = atasanId;
        this.companyId = companyId;

    }
}
