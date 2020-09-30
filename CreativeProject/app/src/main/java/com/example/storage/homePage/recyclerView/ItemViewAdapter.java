package com.example.storage.homePage.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storage.R;
import com.example.storage.database.model.Item;
import com.example.storage.homePage.ItemDetailDialog;

import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {

    private List<Item> itemList;
    private Context context;

    public ItemViewAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ItemViewAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.tv_title.setText(itemList.get(position).getTitle());
        holder.tv_description.setText(itemList.get(position).getDescription());

        holder.bt_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDetailDialog itemDetailDialog = new ItemDetailDialog(context, R.layout.dialog_item_detail);
                itemDetailDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title, tv_description;
        private Button bt_more_info;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_item_title);
            tv_description = itemView.findViewById(R.id.tv_item_description);

            bt_more_info = itemView.findViewById(R.id.bt_item_more_info);
        }
    }
}
