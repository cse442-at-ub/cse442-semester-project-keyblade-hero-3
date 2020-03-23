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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //AsyncDataPull pull = new AsyncDataPull();
            //pull.execute(database_name, "Champa_Sushi");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }


    public String pushOrder(String order_num, String u_id, String d_id, String stat) {

        try {
            Statement stmt = con.createStatement();
            int result = stmt.executeUpdate("INSERT INTO Orders(order_number,user_id,deliverer_id,status)" +
                    "VALUES (" + order_num + "," + u_id + "," + d_id + "," + stat + ")");
            con.close();
            return Integer.toString(result) + " records effected";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public List<ArrayList<String>> get_dining_menu(String dining_location_name) {
        List<ArrayList<String>> q;
        AsyncDataPull pull = new AsyncDataPull();
        try {
            q = pull.execute(database_name, dining_location_name).get();
            all_items_pulled = q;

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return all_items_pulled;
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