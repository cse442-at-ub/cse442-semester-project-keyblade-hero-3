package com.example.ub_eats.Menu;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ub_eats.MainActivity;
import com.example.ub_eats.R;
import com.example.ub_eats.Registration;

//import static android.provider.AlarmClock.EXTRA_MESSAGE;
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final String EXTRA_MESSAGE="com.example.ub_eats.Menu.MESSAGE";


    String data1[], data2[];
    int images[];
    Context context;


    public MyAdapter(Context ct, String s1[], String s2[], int img[]){

        context=ct;
        data1=s1;
        data2=s2;
        images=img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.mytext1.setText(data1[position]);
        holder.mytext2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);

        //holder.button1.setOnClickListener( {

        holder.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent( v.getContext(), secondActivity.class);
                String Pos=String.valueOf(position);
                intent.putExtra(EXTRA_MESSAGE, Pos);
                v.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {

        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mytext1, mytext2;
        ImageView myImage;
        Button button1;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            mytext1=itemView.findViewById(R.id.myText1);
            mytext2=itemView.findViewById(R.id.myText2);
            myImage=itemView.findViewById(R.id.myImageView);
            button1=itemView.findViewById(R.id.button);
        }
    }
}
