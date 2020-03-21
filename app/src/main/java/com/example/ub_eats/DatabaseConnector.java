package com.example.ub_eats.dummy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnector {
        //executeQuery() reads, executeUpdate() writes
        private String database_name = "cse442_542_2020_spring_teamq_db";
        private Connection con;
        public DatabaseConnector(){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("bruh");
                con= DriverManager.getConnection(
                        "jdbc:mysql://tethys.cse.buffalo.edu:3306/" + database_name,"chimaobi","50179050");
            }catch(Exception e){ System.out.println(e);}
        }

        public String pushOrder(String order_num, String u_id, String d_id, String stat){

            try {
                Statement stmt = con.createStatement();
                int result =stmt.executeUpdate("INSERT INTO Orders(order_number,user_id,deliverer_id,status)"+
                        "VALUES (" + order_num + "," + u_id + "," + d_id + "," + stat + ")");
                con.close();
                return Integer.toString(result) + " records effected";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Error";
            }
        }
        public List<String[]> get_dining_menu(String dining_location_name){
            List<String[]> all_items = new ArrayList<String[]>();

            try{
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("select * from " + dining_location_name);
                while(rs.next()) {
                    String[] new_item = new String[2];
                    new_item[0] = rs.getString(1);
                    new_item[1] = Float.toString(rs.getFloat(2));
                    all_items.add(new_item);
                }
                con.close();

            }
            catch(Exception e){ System.out.println(e);}
            return all_items;}
}
