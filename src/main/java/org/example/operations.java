package org.example;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.Comparator;
import java.util.Scanner;

public class operations {
    private static  PreparedStatement preparedStatement;
    static Scanner sc = new Scanner(System.in);
    private  static String s;
    static   boolean check(Connection connection) throws SQLException{
        boolean flag = false;
        DatabaseMetaData databaseMetaData = connection.getMetaData();
        String table[]={"TABLE"};
        ResultSet rs=databaseMetaData.getTables(null,null,null,table);

        while(rs.next()){
            if(s.equalsIgnoreCase(rs.getString(3)))
                flag = true;
           // System.out.println(rs.getString(3));
        }
        //System.out.println(flag);
        return flag;
    }
    static void createEmployeeTable(Connection connection,String tablename) throws SQLException {
        s = tablename;
        boolean flag = check(connection);
        if(flag == true){
            System.out.println("TABLE IS ALREADY PRESENT");
            System.out.println();
        }
        else {
            String createTable = "CREATE TABLE "+s +
                    " ( id INT AUTO_INCREMENT PRIMARY KEY,"
                    +" name VARCHAR(100),"
                    +"age INT,"
                    +"email VARCHAR(100)"
                    +")";
            preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();
            System.out.println("TABLE CREATED SUCCESSFULLY");
            System.out.println();
        }
    }
     static void insertDataIntoDb(Connection connection) throws SQLException {
         System.out.println("ENTER NAME OF EMPLOYEE : ");
         String name = sc.next();
         System.out.println("ENTER AGE OF EMPLOYEE : ");
         int age = sc.nextInt();
         System.out.println("ENTER EMAIL OF EMPLOYEE :");
         String mail = sc.next();
         String insertSql = "INSERT INTO "+s+"(name,age,email) VALUES(?,?,?)";
         preparedStatement = connection.prepareStatement(insertSql);
         preparedStatement.setString(1,name);
         preparedStatement.setInt(2,age);
         preparedStatement.setString(3,mail);
         preparedStatement.executeUpdate();
         System.out.println();
         System.out.println("DATA INSERTED.....");
    }

    static void deleteEmployeeData(Connection connection) throws SQLException{
        System.out.println("ENTER ID OF EMPLOYEE WHICH YOU WANT TO DELETE");
        int i = sc.nextInt();
        String del = "DELETE FROM "+s+" WHERE id = ?";
        preparedStatement = connection.prepareStatement(del);
        preparedStatement.setInt(1,i);
        preparedStatement.executeUpdate();
        System.out.println();
        System.out.println("DATA DELETED");
    }

    static void update(Connection connection,int key) throws SQLException{
        System.out.println("ENTER ID OF EMPLOYEE WHICH YOU WANT TO DELETE");
        int i = sc.nextInt();
        switch (key){
            case 1:
                System.out.println("ENTER NEW NAME");
                String new_name = sc.next();
                String sql = "UPDATE "+s+" SET name = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,new_name);
                preparedStatement.setInt(2,i);
                break;

            case 2:
                System.out.println("ENTER NEW EMAIL");
                String e = sc.next();
                String sql1 = "UPDATE "+s+" SET email = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1,e);
                preparedStatement.setInt(2,i);
                break;

            case 3:
                System.out.println("ENTER NEW AGE");
                int a = sc.nextInt();
                String sql2 = "UPDATE "+s+" SET age = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1,a);
                preparedStatement.setInt(2,i);
                break;

            case 4:
                System.out.println("ENTER NEW NAME");
                String new_name1 = sc.next();
                System.out.println("ENTER NEW EMAIL");
                String new_email = sc.next();
                System.out.println("ENTER NEW AGE");
                int new_age = sc.nextInt();
                String sql3 = "UPDATE "+s+" SET name = ?, email = ? , age = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql3);
                preparedStatement.setString(1,new_name1);
                preparedStatement.setString(2,new_email);
                preparedStatement.setInt(3,new_age);
                preparedStatement.setInt(4,i);
                break;

            default:
                System.out.println("PLZZ ENTER CORRECT OPTION");
        }
        preparedStatement.executeUpdate();
        System.out.println("DATA UPDATED.........");
    }


    static void fetchData(Connection connection,int key) throws SQLException{
        switch (key){
            case 1:
                System.out.println("ENTER EMPLOYEE ID");
                int i =sc.nextInt();
                String sql = "SELECT * FROM "+s+" WHERE id =?";
                preparedStatement =  connection.prepareStatement(sql);
                preparedStatement.setInt(1,i);
                break;
            case 2:
                String sql1 = "SELECT * FROM "+s;
                preparedStatement = connection.prepareStatement(sql1);
                break;
            default:
                System.out.println("PLZZ ENTER CORRECT OPTION");
                break;
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println("ID OF EMPLOYEE    : "+resultSet.getString("id"));
            System.out.println("NAME OF EMPLOYEE  : "+resultSet.getString("name"));
            System.out.println("EMAIL OF EMPLOYEE : "+resultSet.getString("email"));
            System.out.println("AGE OF EMPLOYEE   : "+resultSet.getString("age"));
            System.out.println();
        }
    }
}
