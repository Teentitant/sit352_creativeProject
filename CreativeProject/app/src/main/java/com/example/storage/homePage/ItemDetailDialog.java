package com.example.storage.homePage;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.storage.R;

public class ItemDetailDialog extends Dialog {
    private TextView tv_title, tv_email, tv_password, tv_description;
    private Button bt_email_copy, bt_password_copy;
    private Button bt_confirm;

    public ItemDetailDialog(@NonNull Context context, int layoutId) {
        super(context);
        setContentView(layoutId);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);

        initDialog();

        setConfirmListener();
        setCopyListener();
    }

    private void initDialog() {
        tv_title = findViewById(R.id.tv_dialog_title);
        tv_email = findViewById(R.id.tv_dialog_email);
        tv_password = findViewById(R.id.tv_dialog_password);
        tv_description = findViewById(R.id.tv_dialog_description);

        bt_email_copy = findViewById(R.id.bt_email_copy);
        bt_password_copy = findViewById(R.id.bt_password_copy);

        bt_confirm = findViewById(R.id.bt_dialog_confirm);
    }

    private void setConfirmListener() {
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void setCopyListener() {
        final ClipboardManager cm = (ClipboardManager) getContext().getSystemService(getContext().CLIPBOARD_SERVICE);

        bt_email_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("email", tv_email.getText().toString().trim());
                cm.setPrimaryClip(clipData);
            }
        });

        bt_password_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clipData = ClipData.newPlainText("password", tv_password.getText().toString().trim());
                cm.setPrimaryClip(clipData);
            }
        });
    }

    public void setTitle(String title) {
        tv_title.setText(title);
    }

    public void setEmail(String email) {
        tv_email.setText(email);
    }

    public void setPassword(String password) {
        tv_password.setText(password);
    }

    public void setDescription(String description) {
        tv_description.setText(description);
    }
}

