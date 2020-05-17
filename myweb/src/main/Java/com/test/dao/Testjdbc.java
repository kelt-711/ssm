package com.test.dao;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Testjdbc {


        public static void main(String[] args) throws ClassNotFoundException, SQLException {
            try {
                //老版驱动类com.mysql.jdbc.Driver
                //如果忘记了第一个步骤的导包，就会抛出ClassNotFoundException
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("数据库加载成功!");

                Connection c = DriverManager.getConnection("jdbc:mysql://49.235.51.187:3306/web?"
                        + "useUnicode=true&characterEncoding=utf8&","root","123456Web%");
                System.out.println("连接成功，获取连接对象： " + c);
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery("select * from customer");
                while (rs.next()) {
                    for (int i = 1; i <= 4; i++) {
                        System.out.print(rs.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }





}
