package com.example.sit352.homePage.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sit352.R;
import com.example.sit352.database.UserDBHelper;
import com.example.sit352.database.model.Item;
import com.example.sit352.homePage.ItemDetailDialog;

import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> {

    private List<Item> itemList;
    private Context context;

    private UserDBHelper db;

    public ItemViewAdapter(Context context) {
        this.context = context;

        db = new UserDBHelper(context);
        itemList = db.getAllItemModel();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_item, parent, false);
        return new ItemViewAdapter.ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        holder.tv_title.setText(itemList.get(position).getTitle());
        holder.tv_description.setText(itemList.get(position).getDescription());

        holder.bt_more_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDetailDialog itemDetailDialog = new ItemDetailDialog(context, R.layout.dialog_item_detail);
                itemDetailDialog.setTitle(itemList.get(position).getTitle());
                itemDetailDialog.setEmail(itemList.get(position).getEmail());
                itemDetailDialog.setPassword(itemList.get(position).getPassword());
                itemDetailDialog.setDescription(itemList.get(position).getDescription());
                itemDetailDialog.show();
            }
        });
    }

    public void updateData(){
        itemList = db.getAllItemModel();
        notifyDataSetChanged();
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
