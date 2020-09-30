package com.example.storage.loginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.storage.R;
import com.example.storage.database.UserDBHelper;
import com.example.storage.homePage.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText et_email, et_password;
    private TextView tv_create;
    private Button bt_login;

    private UserDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initActivity();

        initRegisterListener();
        initLoginListener();
    }

    private void initActivity() {
        //edit text
        et_email = findViewById(R.id.registerEmail);
        et_password = findViewById(R.id.et_password);

        //text view
        tv_create = findViewById(R.id.create_account);

        //button
        bt_login = findViewById(R.id.bt_login);

        //database
        db = new UserDBHelper(this);

        String email = getIntent().getStringExtra("email");
        String password = getIntent().getStringExtra("password");
        if (email != null && password != null) {
            et_email.setText(email);
            et_password.setText(password);

            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void initRegisterListener() {
        //text view
        tv_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initLoginListener() {
        //button
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();

                if (email.length() == 0) {
                    et_email.setError("email error");
                    return;
                } else if (password.length() == 0) {
                    et_password.setError("password error");
                    return;
                }

                if (db.checkUserModel(email, password)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    et_password.setError("email or password wrong");
                }
            }
        });
    }
}