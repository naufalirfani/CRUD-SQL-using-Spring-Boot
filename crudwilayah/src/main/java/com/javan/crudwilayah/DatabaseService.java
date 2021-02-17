package com.javan.crudwilayah;

import com.javan.crudwilayah.data.DataProvinsi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public List<DataProvinsi> provinsiList = new ArrayList<>();

    public void getDataProvinsi(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/wilayah","root","freetopl4y");
            //here sonoo is database name, root is username and password

            String query = "select * from provinsi";
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
