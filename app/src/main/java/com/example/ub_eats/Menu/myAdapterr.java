package com.example.ub_eats.Menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.Cart.DatabaseHelper;
import com.example.ub_eats.Cart.cart;
import com.example.ub_eats.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class myAdapterr extends RecyclerView.Adapter<myAdapterr.MyViewHolderr> implements Filterable {
    public static final String EXTRA_MESSAGE2="com.example.ub_eats.Menu.MESSAGE";
    public static final String EXTRA_MESSAGE3="com.example.ub_eats.Menu.MESSAGE";
    DatabaseHelper myDB;


    String data1[], data2[],data3[];

    Context context;
    private List<String> exampleListFullName;
    private List<String> exampleListFullprice;
    private List<String> exampleListFulls3;
    List<String> listFilteredPrice;
    List<String> itemList;
    List<String> listFilteredName;
    List<String> listFiltereds3;



    public myAdapterr(Context ct, String s1[], String s2[],  String s3[]){
        context=ct;
        data1=s1;
        data2=s2;
        data3=s3;
        exampleListFullName = new ArrayList<>(Arrays.asList(data1));
        exampleListFullprice = new ArrayList<>(Arrays.asList(data2));
        exampleListFulls3 = new ArrayList<>(Arrays.asList(data3));
        itemList  = new ArrayList<String>( Arrays.asList(data1));
        listFiltereds3= new ArrayList<>();

    }
    @Override
    public Filter getFilter() {
        return filter;
    }

    @NonNull
    @Override
    public MyViewHolderr onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row2, parent,false);
        return new MyViewHolderr(view);

        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderr holder, final int position) {
        holder.mytext1.setText(data1[position]);
        holder.mytext2.setText(data2[position]);
        holder.mytext3.setText(data3[position]);

        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( v.getContext(), cart.class);
                String item=data1[position];
                String price=data3[position];
                //  myDB.addData(item,price);
                intent.putExtra("item", item);
                intent.putExtra("price",price);
                v.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return data2.length;
    }


    Filter filter = new Filter() {
        @Override //background thread
        protected FilterResults performFiltering(CharSequence charSequence) {

                        listFilteredName= new ArrayList<>();
                         listFilteredPrice= new ArrayList<>();
                         listFiltereds3=new ArrayList<>();

            //List<String> itemName = new ArrayList<String>(Arrays.asList(data1));

            if( charSequence.toString().isEmpty()){

                listFilteredName.addAll(exampleListFullName);
                listFilteredPrice.addAll(exampleListFullprice);
                listFiltereds3.addAll(exampleListFulls3);


            }else{
                System.out.println("-------------------");
                System.out.println(data2.length);
                for (int i=0; i<data2.length;i++){

                    if (exampleListFullName.get(i).toLowerCase().contains(charSequence.toString().toLowerCase())){
                        listFilteredName.add(exampleListFullName.get(i));
                        listFilteredPrice.add(exampleListFullprice.get(i));
                        listFiltereds3.add(exampleListFulls3.get(i));
                    }
                }
            }
          //  data2=exampleListFullprice.toArray(new String[0]);
            FilterResults filterResults=new FilterResults();
            filterResults.values=listFilteredName;

            System.out.println("---List FIltered size----------------");
            System.out.println(listFilteredName.size());
            return filterResults;
        }

        @Override //UI thread
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            List<String> priceList = new ArrayList<String>(Arrays.asList(data2));
            List<String> s3List = new ArrayList<String>(Arrays.asList(data3));


            //itemList.clear();
            priceList.clear();
            s3List.clear();

           // exampleListFull.clear(); //
            itemList.clear();
            itemList.addAll((Collection<? extends String>) filterResults.values);
            priceList.addAll(listFilteredPrice);
            s3List.addAll(listFiltereds3);
            data1=itemList.toArray(new String[0]);
            data2=priceList.toArray(new String[0]);
            data3=s3List.toArray(new String[0]);

            notifyDataSetChanged();

        }
    };

    public class MyViewHolderr extends RecyclerView.ViewHolder {


        TextView mytext1, mytext2, mytext3;
        Button bt;

        public MyViewHolderr(@NonNull View itemView) {
            super(itemView);

            mytext1=itemView.findViewById(R.id.myText1);
            mytext2=itemView.findViewById(R.id.myText2);
            mytext3=itemView.findViewById(R.id.myText3);
            bt=itemView.findViewById(R.id.button);

        }
    }


}
