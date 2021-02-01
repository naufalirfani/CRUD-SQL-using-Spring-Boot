package com.javan.tugas4.service;

import com.javan.tugas4.data.DataEmployee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public ArrayList<DataEmployee> employeeList = new ArrayList<>();

    public void getData(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "select * from employee";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()){
                DataEmployee dataEmployee = new DataEmployee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
                employeeList.add(dataEmployee);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void insertData(){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Perpustakaan","root","freetopl4y");

            // the mysql insert statement
            String query = " insert into anggota (id_anggota, nm_anggota, alamat, ttl_anggota, status_anggota)"
                    + " values (?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setInt(1, 3);
            preparedStmt.setString(2, "Siwi Widayanti");
            preparedStmt.setString(3, "Gondang, Margoagung");
            preparedStmt.setString(4, "23 Januari 1973");
            preparedStmt.setInt(5, 1);

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

    public void deleteData(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Perpustakaan","root","freetopl4y");

            // the mysql insert statement
            String query = "delete from anggota where id_anggota = 3";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
