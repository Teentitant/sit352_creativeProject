package com.example.storage.homePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.storage.R;
import com.example.storage.database.UserDBHelper;
import com.example.storage.database.model.Item;
import com.example.storage.homePage.recyclerView.ItemViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ImageView iv_add;
    private TextView tv_add;

    private RecyclerView recyclerView;

    private UserDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initActivity();

        initRecyclerView();

        initAddActivity();
    }

    private void initActivity() {
        iv_add = findViewById(R.id.iv_add_item);
        tv_add = findViewById(R.id.tv_add_item);

        recyclerView = findViewById(R.id.recyclerView);

        db = new UserDBHelper(this);
    }

    private void initRecyclerView() {
        List<Item> itemList = db.getAllItemModel();

        ItemViewAdapter itemViewAdapter = new ItemViewAdapter(itemList, this);
        recyclerView.setAdapter(itemViewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initAddActivity() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AddItemActivity.class);
                startActivity(intent);
                finish();
            }
        };

        iv_add.setOnClickListener(onClickListener);
        tv_add.setOnClickListener(onClickListener);
    }
}