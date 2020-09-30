package com.example.sit352.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sit352.R;
import com.example.sit352.database.UserDBHelper;
import com.example.sit352.database.model.UserModel;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_email, et_password, et_confirm_password;
    private Button bt_register;

    private UserDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initActivity();

        initRegisterButton();
    }

    private void initActivity() {
        //edit text
        et_email = findViewById(R.id.registerEmail);
        et_password = findViewById(R.id.registerPassword);
        et_confirm_password = findViewById(R.id.registerConPassword);

        //button
        bt_register = findViewById(R.id.bt_register);

        //database
        db = new UserDBHelper(this);
    }

    private void initRegisterButton() {
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String cPassword = et_confirm_password.getText().toString().trim();

                if (email.length() == 0) {
                    et_email.setError("email error");
                    return;
                } else if (password.length() == 0) {
                    et_password.setError("password error");
                    return;
                }

                if (password.equals(cPassword)) {
                    //insert data
                    try {
                        if (db.insertUserModel(new UserModel(email, password))) {
                            Toast.makeText(RegisterActivity.this, "inserted successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("password", password);
                            startActivity(intent);
                            finish();
                        } else {
                            et_confirm_password.setError("password wrong");
                            return;
                        }
                    } catch (Exception e) {

                    }
                }
            }
        });
    }
}