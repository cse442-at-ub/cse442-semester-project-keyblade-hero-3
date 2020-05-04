package com.example.ub_eats;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DatabaseConnector {
    //executeQuery() reads, executeUpdate() writes
    private String database_name = "cse442_542_2020_spring_teamq_db";
    private Connection con;
    static private List<ArrayList<String>> all_items_pulled;

    public DatabaseConnector() {

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            //AsyncDataPull pull = new AsyncDataPull();
//            //pull.execute(database_name, "Champa_Sushi");
//        } catch (Exception e) {
//            System.out.println(e.toString());
//        }
    }


    public String pushOrder(String order_num, String u_id, String d_id, String stat, String price, String items) {
        AsyncDataPush push = new AsyncDataPush();
        try {
            Integer result = push.execute(database_name, order_num, u_id, d_id, stat, price, items).get();
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

            for (int i = 0; i < all_items_pulled.size(); i++) {
                Log.e("Names", all_items_pulled.get(i).get(0));

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return all_items_pulled;
    }

    public List<ArrayList<String>> httpPullMenu(String dining_location) {
        List<ArrayList<String>> q;
        AsyncHttpDataPull pull = new AsyncHttpDataPull();
        try {
            q = pull.execute(dining_location).get();
            all_items_pulled = q;
            Log.d("Size", Integer.toString(all_items_pulled.size()));

            for (int i = 0; i < all_items_pulled.size(); i++) {
                Log.e("Names", all_items_pulled.get(i).get(0));

            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return all_items_pulled;
    }

    public String httpPushOrder(String u_id, String d_id, String status, String price, String items) {
        AsyncHttpDataPush push = new AsyncHttpDataPush();
        try {
            String result = push.execute(u_id, d_id, status, price, items).get();
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return ("Pushing order failed ");
    }

    public String httpRegisterUser(String user, String pass, String phone, String email) {
        AsyncHttpUserRegister reg = new AsyncHttpUserRegister();
        try {
            String result = reg.execute(user, pass, phone, email).get();
            if (result.equals("Pass")) {
                return ("Passed");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    public String httpLoginUser(String user, String pass) {
        AsyncHttpUserLogin login = new AsyncHttpUserLogin();
        try {
            String result = login.execute(user, pass).get();
            if (Integer.valueOf(result) > 0) {
                System.out.println(result);
                return "Passed";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    public String httpGetUserSalt(String user) {
        AsyncHttpGetUserSalt login = new AsyncHttpGetUserSalt();
        try {
            String result = login.execute(user).get();
            System.out.println("Salt value returned is: " + result);
            return result;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "Failed";
    }

    static private class AsyncDataPush extends AsyncTask<String, Void, Integer> {
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
        protected List<ArrayList<String>> doInBackground(String... strings) {
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

    static private class AsyncHttpDataPull extends AsyncTask<String, Void, List<ArrayList<String>>> {
        @Override
        protected List<ArrayList<String>> doInBackground(String... strings) {
            String url = "http://www-student.cse.buffalo.edu/CSE442-542/2020-Spring/cse-442q/web-api/rest.php";
            URL u = null;
            try {
                u = new URL(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                String r_name = "\"" + strings[0] + "\"";

                String output = String.format("{\"restaurant\":%s}",r_name);
                System.out.println(output);


                byte[] out = output.getBytes("UTF-8");
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json");
                con.connect();
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
                os.write(output);
                os.flush();
                os.close();



                int responseCode = con.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line;
                    ArrayList<String> names = new ArrayList<>();
                    ArrayList<String> prices = new ArrayList<>();

                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                        if (line.contains("item_nm")) {
                            names.add(line.split("=>")[1]);
                        }

                        if (line.contains("price")) {
                            prices.add(line.split("=>")[1]);
                        }
                    }
                    br.close();
                    for (int i = 0; i < names.size(); i++) {
                        System.out.println(names.get(i));
                        System.out.println(prices.get(i));

                    }

                    List<ArrayList<String>> menu = new ArrayList<>();
                    menu.add(names);
                    menu.add(prices);
                    return menu;
                    //Return menu. That is the list we will use to fill up the recyclerview
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ArrayList<String>> arrayLists) {
            all_items_pulled = arrayLists;
        }
    }

    static private class AsyncHttpDataPush extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String url = "http://www-student.cse.buffalo.edu/CSE442-542/2020-Spring/cse-442q/web-api/orders.php/";
            URL u;
            try {
                u = new URL(url);
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                String u_id = "\"" + strings[0] + "\"";
                String d_id = "\"" + strings[1] + "\"";
                String status = "\"" + strings[2] + "\"";
                String price = "\"" + strings[3] + "\"";
                String items = "\"" + strings[4] + "\"";

                String output = String.format("{\"user_id\":%s,\"deliverer_id\":%s,\"status\":%s,\"price\":%s,\"items\":%s}", u_id, d_id, status, price, items);
                System.out.println(output);


                byte[] out = output.getBytes("UTF-8");
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json");
                con.connect();
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
                os.write(output);
                os.flush();
                os.close();


                int responseCode = con.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { //success

                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    con.disconnect();
                    return (sb.toString());
                } else {
                    return ("POST request failed");
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ("POST request failed");
        }
    }

    static private class AsyncHttpUserRegister extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url = "http://www-student.cse.buffalo.edu/CSE442-542/2020-Spring/cse-442q/web-api/new_account.php/";
            URL u;
            try {
                u = new URL(url);
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                String user = "\"" + strings[0] + "\"";
                String email = "\"" + strings[2] + "\"";
                String phone = "\"" + strings[3] + "\"";

                //Encode the password
                Encoder e = new Encoder();
                String[] salt_and_pass = e.encodePassword(strings[1]);

                String salt = "\"" + salt_and_pass[0] + "\"";
                //Salt is static for now
                //String z = "2AxGUi/qgnHOZRuG2RaUMtLhe+Q=";
                //String salt = "\"" + z + "\"";
                String pass = "\"" + salt_and_pass[1] + "\"";


                String output = String.format("{\"username\":%s,\"pass\":%s,\"email\":%s,\"phone\":%s,\"salt_value\":%s}", user, pass, email, phone, salt);
                System.out.println(output);
                System.out.println("The salt created: " + salt);

                byte[] out = output.getBytes("UTF-8");
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json");
                con.connect();
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
                os.write(output);
                os.flush();
                os.close();
                int responseCode = con.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                    return "Pass";
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return ("POST request failed");


        }

    }

    static private class AsyncHttpUserLogin extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url = "http://www-student.cse.buffalo.edu/CSE442-542/2020-Spring/cse-442q/web-api/login.php/";
            URL u;
            try {
                u = new URL(url);
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                String user = "\"" + strings[0] + "\"";
                String password = "\"" + strings[1] + "\"";

                String output = String.format("{\"username\":%s,\"pass\":%s}", user, password);
                System.out.println(output);


                byte[] out = output.getBytes("UTF-8");
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json");
                con.connect();
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
                os.write(output);
                os.flush();
                os.close();
                int responseCode = con.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuffer sb = new StringBuffer();
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    con.disconnect();
                    return (sb.toString());
                } else {
                    return ("POST request failed");
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ("POST request failed");


        }
    }


    static private class AsyncHttpGetUserSalt extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url = "http://www-student.cse.buffalo.edu/CSE442-542/2020-Spring/cse-442q/web-api/usersalt.php/";
            URL u;
            try {
                u = new URL(url);
                HttpURLConnection con = (HttpURLConnection) u.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                String user = "\"" + strings[0] + "\"";

                String output = String.format("{\"username\":%s}", user);
                System.out.println(output);


                byte[] out = output.getBytes("UTF-8");
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json");
                con.connect();
                OutputStreamWriter os = new OutputStreamWriter(con.getOutputStream());
                os.write(output);
                os.flush();
                os.close();
                int responseCode = con.getResponseCode();
                System.out.println(responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { //success
                    StringBuffer sb = new StringBuffer();
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    br.close();
                    return sb.toString().trim();
                }
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ("POST request failed");


        }
    }
}