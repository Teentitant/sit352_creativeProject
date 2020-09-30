package com.example.storage.homePage;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ItemDetailDialog extends Dialog {

    public ItemDetailDialog(@NonNull Context context, int layoutId) {
        super(context);
        setContentView(layoutId);

        Window window = getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
    }

    private void initDialog() {

    }
}
