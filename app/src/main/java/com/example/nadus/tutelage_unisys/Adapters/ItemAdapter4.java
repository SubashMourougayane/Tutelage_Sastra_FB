package com.example.nadus.tutelage_unisys.Adapters;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nadus.tutelage_unisys.R;

import java.util.ArrayList;
import java.util.Arrays;

import me.gujun.android.taggroup.TagGroup;

/**
 * Created by HP on 12/29/2017.
 */

public class ItemAdapter4 extends RecyclerView.Adapter<ItemAdapter4.ViewHolder>
{
    private int listItemLayout;
    private ArrayList<ClassAdapter> list1;
    public MyClickListener myClickListener;
    public ItemAdapter4(int listlayout, ArrayList<ClassAdapter> tl)
    {
        this.listItemLayout=listlayout;
        this.list1=tl;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        final ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
            String[] subjects=new String[list1.get(position).getSubjects().size()];
        list1.get(position).getSubjects().toArray(subjects);
        holder.mTagGroup.setTags(subjects);
//        Typeface typeFace = Typeface.createFromAsset(getAssets(), "GlacialIndifference-Regular.ttf");
//        holder.textView.setTypeface(typeFace);
        holder.textView.setText(list1.get(position).getClassname());
    }

    @Override
    public int getItemCount()
    {
        return list1==null?0:list1.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView;
        TagGroup mTagGroup;
        public ViewHolder(View view)
        {
            super(view);

            mTagGroup = (TagGroup) view.findViewById(R.id.tag_group);
            textView=(TextView)view.findViewById(R.id.classname);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    myClickListener.onItemClick(getAdapterPosition(), view);
                }
            });


        }

    }
   public interface MyClickListener {
        void onItemClick(int position, View v);
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }





}