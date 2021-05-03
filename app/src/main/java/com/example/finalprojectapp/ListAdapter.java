package com.example.finalprojectapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

/***
 * The Adapter
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.listItemHolder>{

    private final LinkedList<String> list;
    private LayoutInflater inflater;

    public ListAdapter(Context context, LinkedList<String> list){
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    @NonNull
    @Override
    public listItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("oCVH", "Inside this method");
        View mItemView = inflater.inflate(R.layout.list_item, parent, false);
        Log.d("oCVH", "Leaving this method");
        return new listItemHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull listItemHolder holder, int position) {
        Log.d("oBVH", "Inside this method");
        String current = list.get(position);
        holder.listItemView.setText(current);
        Log.d("oBVH", "Leaving this method");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /***
     * The Holder
     */
    class listItemHolder extends RecyclerView.ViewHolder{

        public final TextView listItemView;

        public listItemHolder(@NonNull View itemView) {
            super(itemView);
            listItemView = itemView.findViewById(R.id.title);
        }

    }

}
