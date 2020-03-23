package com.example.ub_eats.Menu;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.Cart.DatabaseHelper;
import com.example.ub_eats.Cart.cart;
import com.example.ub_eats.R;

public class myAdapterr extends RecyclerView.Adapter<myAdapterr.MyViewHolderr> {
    public static final String EXTRA_MESSAGE2="com.example.ub_eats.Menu.MESSAGE";
    public static final String EXTRA_MESSAGE3="com.example.ub_eats.Menu.MESSAGE";
    DatabaseHelper myDB;


    String data1[], data2[],data3[];
    int images[];
    Context context;

    public myAdapterr(Context ct, String s1[], String s2[],  String s3[],int img[]){
        context=ct;
        data1=s1;
        data2=s2;
        data3=s3;
        images=img;
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
        holder.myImage.setImageResource(images[position]);

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
        return images.length;
    }

    public class MyViewHolderr extends RecyclerView.ViewHolder {


        TextView mytext1, mytext2, mytext3;
        ImageView myImage;
        Button bt;

        public MyViewHolderr(@NonNull View itemView) {
            super(itemView);

            mytext1=itemView.findViewById(R.id.myText1);
            mytext2=itemView.findViewById(R.id.myText2);
            mytext3=itemView.findViewById(R.id.myText3);
            myImage=itemView.findViewById(R.id.myImageView);
            bt=itemView.findViewById(R.id.button);

            myImage.setVisibility(View.GONE);
        }
    }


}
