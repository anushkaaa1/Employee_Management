package org.example;

import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.Scanner;

public class operations {
    private static  PreparedStatement preparedStatement;
    static Scanner sc = new Scanner(System.in);
    static void createEmployeeTable(Connection connection) throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS employee1 ( id INT AUTO_INCREMENT PRIMARY KEY,"
                +" name VARCHAR(100),"
                +"age INT,"
                +"email VARCHAR(100)"
                +")";
        preparedStatement = connection.prepareStatement(createTable);
        preparedStatement.execute();
        System.out.println("TABLE CREATED SUCCESSFULLY");
    }
     static void insertDataIntoDb(Connection connection) throws SQLException {
         System.out.println("ENTER NAME OF EMPLOYEE : ");
         String name = sc.next();
         System.out.println("ENTER AGE OF EMPLOYEE : ");
         int age = sc.nextInt();
         System.out.println("ENTER EMAIL OF EMPLOYEE :");
         String mail = sc.next();
         String insertSql = "INSERT INTO employee1(name,age,email) VALUES(?,?,?)";
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
        String del = "DELETE FROM employee1 WHERE id = ?";
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
                String s = sc.next();
                String sql = "UPDATE employee1 SET name = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1,s);
                preparedStatement.setInt(2,i);
                break;

            case 2:
                System.out.println("ENTER NEW EMAIL");
                String e = sc.next();
                String sql1 = "UPDATE employee1 SET email = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1,e);
                preparedStatement.setInt(2,i);
                break;

            case 3:
                System.out.println("ENTER NEW AGE");
                int a = sc.nextInt();
                String sql2 = "UPDATE employee1 SET age = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql2);
                preparedStatement.setInt(1,a);
                preparedStatement.setInt(2,i);
                break;

            case 4:
                System.out.println("ENTER NEW NAME");
                String new_name = sc.next();
                System.out.println("ENTER NEW EMAIL");
                String new_email = sc.next();
                System.out.println("ENTER NEW AGE");
                int new_age = sc.nextInt();
                String sql3 = "UPDATE employee1 SET name = ?, email = ? , age = ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(sql3);
                preparedStatement.setString(1,new_name);
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
                String sql = "SELECT * FROM employee1 WHERE id =?";
                preparedStatement =  connection.prepareStatement(sql);
                preparedStatement.setInt(1,i);
                break;
            case 2:
                String sql1 = "SELECT * FROM employee1";
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
