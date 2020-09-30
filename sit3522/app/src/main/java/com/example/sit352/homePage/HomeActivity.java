package com.example.sit352.homePage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sit352.R;
import com.example.sit352.database.UserDBHelper;
import com.example.sit352.database.model.Item;
import com.example.sit352.homePage.recyclerView.ItemViewAdapter;
import com.example.sit352.homePage.settingActivity.SettingActivity;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class HomeActivity extends AppCompatActivity {
    private Context context;

    private Button bt_setting;
    private ImageView iv_add;
    private TextView tv_add;

    private RecyclerView recyclerView;

    private UserDBHelper db;
    private List<Item> itemList;

    private ItemViewAdapter itemViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initActivity();

        initRecyclerView();
        setSwipeFunction();

        initAddActivity();
        initSettingActivity();
    }

    private void initActivity() {
        context = this;

        iv_add = findViewById(R.id.iv_add_item);
        tv_add = findViewById(R.id.tv_add_item);
        bt_setting = findViewById(R.id.bt_setting);

        recyclerView = findViewById(R.id.recyclerView);
        itemViewAdapter = new ItemViewAdapter(this);

        db = new UserDBHelper(this);
        itemList = db.getAllItemModel();
    }

    private void initRecyclerView() {
        recyclerView.setAdapter(itemViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initAddActivity() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddItemActivity.class);
                startActivity(intent);
                finish();
            }
        };
        iv_add.setOnClickListener(onClickListener);
        tv_add.setOnClickListener(onClickListener);
    }

    private void initSettingActivity() {
        bt_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setSwipeFunction() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT) {
                    db.deleteItem(itemList.get(position).getId());
                    itemViewAdapter.updateData();
                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                new RecyclerViewSwipeDecorator.Builder(HomeActivity.this, c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        .addSwipeLeftBackgroundColor(ContextCompat.getColor(context, R.color.red))
                        .addSwipeLeftActionIcon(R.drawable.ic_delete)
                        .create()
                        .decorate();

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}