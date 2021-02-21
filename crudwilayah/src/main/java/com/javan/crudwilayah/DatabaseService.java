package com.javan.crudwilayah;

import com.javan.crudwilayah.data.DataDesa;
import com.javan.crudwilayah.data.DataKabupaten;
import com.javan.crudwilayah.data.DataKecamatan;
import com.javan.crudwilayah.data.DataProvinsi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public List<DataProvinsi> provinsiList = new ArrayList<>();
    public List<DataKabupaten> kabupatenList = new ArrayList<>();
    public List<DataKecamatan> kecamatanList = new ArrayList<>();
    public List<DataDesa> desaList = new ArrayList<>();

    public void getDataProvinsi(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");
            //here sonoo is database name, root is username and password

            String query = "select * from provinsi order by provinsiid";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            provinsiList.clear();
            while(rs.next()){
                DataProvinsi dataProvinsi = new DataProvinsi(
                        rs.getInt(1),
                        rs.getString(2)
                );
                provinsiList.add(dataProvinsi);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void getDataKabupaten(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");
            //here sonoo is database name, root is username and password

            String query = "select k.*, p.nama from kabupaten k join provinsi p on k.provinsiid = p.provinsiid order by k.kabupatenid";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            kabupatenList.clear();
            while(rs.next()){
                DataKabupaten dataKabupaten = new DataKabupaten(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                );
                kabupatenList.add(dataKabupaten);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void getDataKecamatan(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");
            //here sonoo is database name, root is username and password

            String query = "select ke.*, ka.nama, p.provinsiid, p.nama from kecamatan ke, kabupaten ka, provinsi p\n" +
                    "where ka.provinsiid = p.provinsiid and ke.kabupatenid = ka.kabupatenid order by ke.kecamatanid;";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            kecamatanList.clear();
            while(rs.next()){
                DataKecamatan dataKecamatan = new DataKecamatan(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)
                );
                kecamatanList.add(dataKecamatan);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void getDataDesa(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");
            //here sonoo is database name, root is username and password

            String query = "select d.*, ke.nama, ka.kabupatenid, ka.nama, p.provinsiid, p.nama from desa d, kecamatan ke, kabupaten ka, provinsi p\n" +
                    "where d.kecamatanid = ke.kecamatanid and ka.provinsiid = p.provinsiid and ke.kabupatenid = ka.kabupatenid order by d.desaid";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            desaList.clear();
            while(rs.next()){
                DataDesa dataDesa = new DataDesa(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8)
                );
                desaList.add(dataDesa);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void insertData(String query){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }

    public void deleteData(String query, String query2){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            preparedStmt2.execute();
            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void updateData(String query){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }
}
