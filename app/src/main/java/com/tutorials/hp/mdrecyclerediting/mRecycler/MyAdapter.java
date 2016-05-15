package com.tutorials.hp.mdrecyclerediting.mRecycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tutorials.hp.mdrecyclerediting.R;
import com.tutorials.hp.mdrecyclerediting.mData.Spacecraft;
import com.tutorials.hp.mdrecyclerediting.mDetail.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Oclemy on 5/14/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class MyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Spacecraft> spacecrafts;

    public MyAdapter(Context c, ArrayList<Spacecraft> spacecrafts) {
        this.c = c;
        this.spacecrafts = spacecrafts;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final String name=spacecrafts.get(position).getName();
        final String desc=spacecrafts.get(position).getDescription();

        //BIND
        holder.nameTxt.setText(name);
        holder.descTxt.setText(desc);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                openDetailActivity(name,desc,pos);
            }
        });


    }

    @Override
    public int getItemCount() {
        return spacecrafts.size();
    }

    private void openDetailActivity(String name,String desc,int position)
    {
        Intent i=new Intent(c, DetailActivity.class);

        //pack data
        i.putExtra("NAME_KEY",name);
        i.putExtra("DESC_KEY",desc);
        i.putExtra("POS_KEY",position);

        c.startActivity(i);

    }
}















