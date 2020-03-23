package com.example.ub_eats;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;

public class DatabaseConnector {
    //executeQuery() reads, executeUpdate() writes
    private String database_name = "cse442_542_2020_spring_teamq_db";
    private Connection con;
    static private List<ArrayList<String>> all_items_pulled;

    public DatabaseConnector() {
        Log.d("Checkpoint 2", "Reached Here");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //AsyncDataPull pull = new AsyncDataPull();
            //pull.execute(database_name, "Champa_Sushi");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public String pushOrder(String order_num, String u_id, String d_id, String stat, String price, String items) {
        AsyncDataPush push = new AsyncDataPush();
        try {
            Integer result = push.execute(database_name,order_num, u_id, d_id, stat, price, items).get();
            System.out.println(result);
            return "yuh";
        } catch (ExecutionException e) {
            e.printStackTrace();
            return "Push failed - did not execute";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "Push failed - Interupted";
        }


    }

    public List<ArrayList<String>> get_dining_menu(String dining_location_name) {
        List<ArrayList<String>> q;
        AsyncDataPull pull = new AsyncDataPull();
        try {
            q = pull.execute(database_name, dining_location_name).get();
            all_items_pulled = q;
            Log.d("Size", Integer.toString(all_items_pulled.size()));

            for(int i = 0; i < all_items_pulled.size(); i++){
                Log.e("Names", all_items_pulled.get(i).get(0));

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return all_items_pulled;
    }

    static private class AsyncDataPush extends AsyncTask<String, Void, Integer>{
        Connection connection;

        @Override
        protected Integer doInBackground(String... strings) {

            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://tethys.cse.buffalo.edu:3306/" + strings[0], "chimaobi", "50179050");
                Statement stmt = connection.createStatement();
                String order_num = strings[1];
                String u_id = strings[2];
                String d_id = strings[3];
                String stat = strings[4];
                String price = strings[5];
                String items = strings[6];

                System.out.println("Total price: " + price);
                System.out.println("Items: " + items);

                int result = stmt.executeUpdate("INSERT INTO Orders(order_number,user_id,deliverer_id,status,price,items)" +
                        "VALUES (" + order_num + "," + u_id + "," + d_id + "," + "'" + stat + "'" + "," + price + "," + "'" + items + "'" + ")");
                connection.close();
                return result;

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 300;
        }
    }
    static private class AsyncDataPull extends AsyncTask<String, Void, List<ArrayList<String>>> {
        ResultSet rs;
        Connection connection;

        @Override
        protected List<ArrayList<String>>  doInBackground(String... strings) {
            List<ArrayList<String>> items_pulled = new ArrayList<>();
            ArrayList<String> item_names = new ArrayList<>();
            ArrayList<String> item_prices = new ArrayList<>();

            String db_name = strings[0];
            String table_name = strings[1];
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://tethys.cse.buffalo.edu:3306/" + db_name, "chimaobi", "50179050");
                Statement stmt = connection.createStatement();
                rs = stmt.executeQuery("select * from " + table_name);

                while (rs.next()) {
                    item_names.add(rs.getString(1));
                    item_prices.add(Float.toString(rs.getFloat(2)));
                }
                items_pulled.add(item_names);
                items_pulled.add(item_prices);

                connection.close();
                return items_pulled;
            } catch (SQLException e) {
                e.printStackTrace();
                return items_pulled;
            }

        }

        @Override
        protected void onPostExecute(List<ArrayList<String>> arrayLists) {
            all_items_pulled = arrayLists;
        }

    }
}