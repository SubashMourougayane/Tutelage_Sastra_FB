package com.example.nadus.tutelage_unisys.Adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nadus.tutelage_unisys.R;

import java.util.ArrayList;

/**
 * Created by nadus on 02-01-2018.
 */

public class AssessmentResultItemAdapter extends RecyclerView.Adapter<AssessmentResultItemAdapter.ViewHolder>{
    private int listItemLayout;
    public ArrayList<AssessmentResultCardAdapter> itemList;
    public static RecyclerView.RecyclerListener re;
    int pos;

    private static MyClickListener myClickListener;
    public AssessmentResultItemAdapter(int layoutId, ArrayList<AssessmentResultCardAdapter> itemList) {
        listItemLayout = layoutId;
        this.itemList = itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        final ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        TextView t_resultname=holder.result_name;
        TextView t_resultreg=holder.result_reg;
        TextView t_tot=holder.result_tot;
        LinearLayout t_card=holder.card;

        t_resultname.setText(itemList.get(position).getResult_name());
        t_resultreg.setText(itemList.get(position).getResult_reg());
        t_tot.setText(itemList.get(position).getResult_tot());

//        item1.setText(itemList.get(position).getTestCata());
//        item2.setText((itemList.get(position).getTestdura())+" minutes");
//        item3.setText(itemList.get(position).getTestdate());
        pos=position;
        t_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(v.getContext(), "Clicked "+position+" "+itemList.get(position).getTestname(), Toast.LENGTH_SHORT).show();
                myClickListener.onItemClick(position, v);
            }
        });
        //String n=String.valueOf(itemList.get(position).getNq());

    }

    @Override
    public int getItemCount()
    {
        return itemList==null?0:itemList.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView result_name,result_reg,result_tot;
        public LinearLayout card;
        public ViewHolder(final View itemView)
        {
            super(itemView);
            card=(LinearLayout) itemView.findViewById(R.id.card);
            result_name=(TextView)itemView.findViewById(R.id.result_name);
            result_reg=(TextView)itemView.findViewById(R.id.result_reg);
            result_tot=(TextView)itemView.findViewById(R.id.result_tot);

        }

        @Override
        public void onClick(View v) {


        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
