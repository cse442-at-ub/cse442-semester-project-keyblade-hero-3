package com.example.ub_eats.Menu;

import android.content.ContentValues;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

import com.example.ub_eats.Cart.cart;
import com.example.ub_eats.R;

import com.example.ub_eats.Menu.RecyclerAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.R;

import java.util.ArrayList;
import java.util.List;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.MyViewHolder> {


    String data1[], data2[];
    String data3;
    int images[];
    Context context;

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
        return new cartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myText3.setText(data3);
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView myText1, myText2, myText3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1=itemView.findViewById(R.id.myText11);
            myText2=itemView.findViewById(R.id.myText21);
            myText3=itemView.findViewById(R.id.myText31);
        }
    }
}
