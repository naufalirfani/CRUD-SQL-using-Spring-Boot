package com.javan.bioskop;

import com.javan.bioskop.data.DataFilm;
import com.javan.bioskop.data.DataPenonton;
import com.javan.bioskop.data.DataPesanan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public List<DataFilm> filmList = new ArrayList<>();
    public List<DataPenonton> penontonList = new ArrayList<>();
    public List<DataPesanan> pesananList = new ArrayList<>();

    public void getDataFilm(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop","root","freetopl4y");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "select * from film";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            filmList.clear();
            while(rs.next()){
                DataFilm dataFilm = new DataFilm(
                        rs.getInt(1),
                        rs.getString(2)
                );
                filmList.add(dataFilm);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void getDataPesanan(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop","root","freetopl4y");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "select * from pesanan";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            pesananList.clear();
            while(rs.next()){
                String film = String.valueOf(rs.getInt(2));
                String penonton = String.valueOf(rs.getInt(3));

                DataPesanan dataPesanan = new DataPesanan(
                        rs.getInt(1),
                        film,
                        penonton
                );
                pesananList.add(dataPesanan);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void getDataPenonton(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop","root","freetopl4y");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "select * from penonton";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            penontonList.clear();
            while(rs.next()){
                DataPenonton dataPenonton = new DataPenonton(
                        rs.getInt(1),
                        rs.getString(2)
                );
                penontonList.add(dataPenonton);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void insertData(int filmid, int penontonid){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop","root","freetopl4y");

            // the mysql insert statement
            String query = " insert into pesanan (filmid, penontonid)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, filmid);
            preparedStmt.setInt(2, penontonid);

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

    public void deleteData(int id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskop","root","freetopl4y");

            // the mysql insert statement
            String query = "delete from pesanan where pesananid = %d";
            query = String.format(query, id);

            String query2 = "ALTER TABLE `pesanan` AUTO_INCREMENT = 1";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            preparedStmt2.execute();
            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void updateData(String nama, int atasanId, int companyId, int id){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");

            // the mysql insert statement
            String query = "update employee set nama = ?, atasan_id = ?, company_id = ? where id = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, nama);
            preparedStmt.setInt(2, atasanId);
            preparedStmt.setInt(3, companyId);
            preparedStmt.setInt(4, id);

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
