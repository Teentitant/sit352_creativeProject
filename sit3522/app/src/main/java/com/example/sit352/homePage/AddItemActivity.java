package com.example.sit352.homePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sit352.R;
import com.example.sit352.database.UserDBHelper;
import com.example.sit352.database.model.Item;

public class AddItemActivity extends AppCompatActivity {
    private EditText et_title, et_email, et_password, et_description;
    private Button bt_add;

    private UserDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        initActivity();

        initAddOnClickListener();
    }

    private void initActivity() {
        et_title = findViewById(R.id.et_add_title);
        et_email = findViewById(R.id.et_add_email);
        et_password = findViewById(R.id.et_add_password);
        et_description = findViewById(R.id.et_add_description);

        bt_add = findViewById(R.id.bt_add_item);

        db = new UserDBHelper(this);
    }

    private void initAddOnClickListener() {
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String description = et_description.getText().toString().trim();

                if (title.length() == 0) {
                    et_title.setError("title error");
                    return;
                } else if (email.length() == 0) {
                    et_email.setError("email error");
                    return;
                } else if (password.length() == 0) {
                    et_password.setError("password error");
                    return;
                } else {
                    if (db.insertItemModel(new Item(title, email, password, description))) {
                        Toast.makeText(AddItemActivity.this, "insert successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddItemActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });
    }
}