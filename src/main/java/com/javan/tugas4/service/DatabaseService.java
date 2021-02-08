package com.javan.tugas4.service;

import com.javan.tugas4.data.DataEmployee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    public List<DataEmployee> employeeList = new ArrayList<>();

    public void getData(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");
            //here sonoo is database name, root is username and password

            // the mysql insert statement
            String query = "select * from employee";

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            employeeList.clear();
            while(rs.next()){
                String company;
                if(rs.getInt(4) == 1)
                    company = "PT JAVAN";
                else
                    company = "PT Dicoding";
                DataEmployee dataEmployee = new DataEmployee(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        company
                );
                employeeList.add(dataEmployee);
            }

            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public void insertData(String nama, int atasanId, int companyId){
        try{
            // create a mysql database connection
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/sdm","root","freetopl4y");

            // the mysql insert statement
            String query = " insert into employee (nama, atasan_id, company_id)"
                    + " values (?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, nama);
            preparedStmt.setInt(2, atasanId);
            preparedStmt.setInt(3, companyId);

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
            String query = "delete from employee where id = %d";
            query = String.format(query, id);

            String query2 = "ALTER TABLE `employee` AUTO_INCREMENT = 1";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.execute();
            PreparedStatement preparedStmt2 = conn.prepareStatement(query2);
            preparedStmt2.execute();
            conn.close();
        }catch(Exception e){ System.out.println(e);}
    }
}
