package com.example.finalprojectapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedList;

/***
 * The Adapter
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.listItemHolder> implements Filterable {

    private LinkedList<ListItem> list;
    private LinkedList<ListItem> mList;
    private Context context;
    private ListDBHelper mDatabase;
    private static final String NAME = "LIST_ADAPTER";

    public ListAdapter(Context context, LinkedList<ListItem> mList){
        this.mList = mList;
        this.context = context;
        list = mList;
        mDatabase = new ListDBHelper(context);
    }

    @NonNull
    @Override
    public listItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(NAME, "Inside onCreateViewHolder");
        return new listItemHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull listItemHolder holder, int position) {
        Log.d(NAME, "Inside onBindViewHolder");
        ListItem current = mList.get(position);
        holder.listItemView.setText(current.getName());
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.deleteList(current.getId());
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });
        Log.d(NAME, "Leaving onBindViewHolder");
    }

    @Override
    public Filter getFilter() {
        Log.d(NAME, "Inside getFilter");
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    Log.d(NAME, "Inside getFilter if loop");
                    mList = list;
                }
                else {
                    Log.d(NAME, "Inside getFilter else loop");
                    LinkedList<ListItem> filteredList = new LinkedList<>();
                    for (ListItem listItem : list) {
                        if (listItem.getName().toLowerCase().contains(charString)) {
                            filteredList.add(listItem);
                        }
                    }
                    mList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mList;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Log.d(NAME, "Inside publishResults");
                mList = (LinkedList<ListItem>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() { return mList.size(); }

    /***
     * The Holder
     */
    class listItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView listItemView;
        public ImageView deleteBtn;

        public listItemHolder(@NonNull View itemView) {
            super(itemView);
            listItemView = itemView.findViewById(R.id.title);
            deleteBtn = itemView.findViewById(R.id.delete);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(NAME, "Inside Holder onClick");
            Intent listIntent = new Intent(v.getContext(), ListActivity.class);
            context.startActivity(listIntent);
            Log.d(NAME, "Leaving Holder onClick");
        }

    }

}
