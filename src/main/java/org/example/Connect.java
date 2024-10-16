package org.example;

import java.sql.*;
import java.util.Scanner;

public class Connect {
    private static final String url ="jdbc:mysql://localhost:3306/spark";
    private static final String user = "root";
    private static final String password = "amaansalik2004";

    public static Connection connection;
    private static ResultSet resultSet;

    public static void connect(int choice){
        try{
            connection = DriverManager.getConnection(url,user,password);
            Scanner sc = new Scanner(System.in);

            switch (choice){
                case 1:
                    System.out.println();
                    System.out.println("Enter name of table");
                    String s = sc.next();
                    operations.createEmployeeTable(connection,s);
                    break;
                case 2:
                    System.out.println();
                    operations.insertDataIntoDb(connection);
                    break;
                case 3:
                    System.out.println();
                    operations.deleteEmployeeData(connection);
                    break;
                case 4:
                    System.out.println();
                    System.out.println();
                    System.out.println("WHAT YOU WANT TO UPDATE? \n1. NAME \n2. EMAIL \n3. AGE \n4. ALL");
                    System.out.println();
                    int key = sc.nextInt();
                    System.out.println();
                    operations.update(connection,key);
                    break;

                case 5:
                    System.out.println();
                    System.out.println("\n1 FETCH DATA OF ONE EMPLOYEE \n2. FETCH DATA OF ALL EMPLOYEES");
                    System.out.println();
                    int fetch = sc.nextInt();
                    System.out.println();
                    operations.fetchData(connection,fetch);
                    break;
                default:
                    System.out.println("..............................");
                    System.out.println("PLZZ ENTER CORRECT OPTIONS");
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
