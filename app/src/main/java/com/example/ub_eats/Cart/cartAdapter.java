package com.example.ub_eats.Cart;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.telecom.ConnectionService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import com.example.ub_eats.R;


public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {


    String data1[], data2[];
    String data3;
    String data4;
    int images[];

    Context context;
    DatabaseHelper deleteItem;
    DatabaseHelper myDB;


    public cartAdapter(Context ct, String s1[], String s2[], String s3){
        context=ct;
        data1=s1;
        data2=s2;
        data3=s3;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.activity_cart,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]); //item name
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3);
        deleteItem = new DatabaseHelper(context);

        holder.myText4.setText(deleteItem.quanRet(data1[position])); //Gets the quantity of item

        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName=data1[position];
                deleteItem.deleteItem(itemName);

                List<String> list = new ArrayList<String>(Arrays.asList(data1));
                list.remove(position);

                List<String> list2 = new ArrayList<String>(Arrays.asList(data2));
                list2.remove(position);

                data1=list.toArray(new String[0]);
                data2=list2.toArray(new String[0]);


                notifyItemRemoved(position);
                notifyItemChanged(position);
                notifyItemRangeChanged(position,getItemCount()-position);

            }
        });
        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName=data1[position];
                deleteItem.incrementQuantity(itemName);//Increment
                holder.myText4.setText(deleteItem.quanRet(data1[position])); //Gets the quantity of item

            }
        });
        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itemName=data1[position];
                deleteItem.decrementQuantity(itemName);//decrement
                holder.myText4.setText(deleteItem.quanRet(data1[position])); //Gets the quantity of item
            }
        });

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1, myText2, myText3, myText4;
        Button bt;
        ImageButton increment, decrement;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1=itemView.findViewById(R.id.myText11);//item name
            myText2=itemView.findViewById(R.id.myText21);
            myText3=itemView.findViewById(R.id.myText31);
            myText4=itemView.findViewById(R.id.textView51);
            bt=itemView.findViewById(R.id.button41);//Delete item
            increment=itemView.findViewById(R.id.Increase);
            decrement=itemView.findViewById(R.id.Decrease);

        }
    }
}