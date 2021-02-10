package com.javan.tugas4.service;

import com.javan.tugas4.data.DataCompany;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public List<DataCompany> companyList = new ArrayList<>();

    public void getData(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "select * from company";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            companyList.clear();
            while(rs.next()){
                DataCompany dataCompany = new DataCompany(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3)
                );
                companyList.add(dataCompany);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void insertData(String nama, String alamat){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");

            // the mysql insert statement
            String query = " insert into company (nama, alamat)"
                    + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, nama);
            preparedStmt.setString(2, alamat);

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
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");

            // the mysql insert statement
            String query = "delete from company where id = %d";
            query = String.format(query, id);

            String query2 = "ALTER TABLE `company` AUTO_INCREMENT = 1";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            preparedStmt2.execute();
            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void updateData(String nama, String alamat, int id){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");

            // the mysql insert statement
            String query = "update company set nama = ?, alamat = ? where id = ?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, nama);
            preparedStmt.setString(2, alamat);
            preparedStmt.setInt(3, id);

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
