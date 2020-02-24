package com.example.ub_eats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myAdapterr extends RecyclerView.Adapter<myAdapterr.MyViewHolderr> {
    String data1[], data2[];
    int images[];
    Context context;

    public myAdapterr(Context ct, String s1[], String s2[], int img[]){
        context=ct;
        data1=s1;
        data2=s2;
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
    public void onBindViewHolder(@NonNull MyViewHolderr holder, int position) {

        holder.mytext1.setText(data1[position]);
        holder.mytext2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolderr extends RecyclerView.ViewHolder {


        TextView mytext1, mytext2;
        ImageView myImage;
        Button bt;

        public MyViewHolderr(@NonNull View itemView) {
            super(itemView);

            mytext1=itemView.findViewById(R.id.myText1);
            mytext2=itemView.findViewById(R.id.myText2);
            myImage=itemView.findViewById(R.id.myImageView);
            bt=itemView.findViewById(R.id.button);
        }
    }
}
