package com.javan.tugas4.data;

import javax.persistence.*;

public class DataEmployee {

    public Integer id;
    public String nama;
    public Integer atasan_id;
    public Integer company_id;

    public DataEmployee(int id, String nama, int atasan_id, int company_id){
        this.id = id;
        this.nama = nama;
        this.atasan_id = atasan_id;
        this.company_id = company_id;

    }
}
