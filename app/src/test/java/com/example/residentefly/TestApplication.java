package com.example.residentefly;

import android.app.Application;
import android.content.res.Resources;

public class TestApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Any setup needed for your tests
    }

    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        theme.applyStyle(R.style.Theme_ResidenteFly, true);
        // Return the theme
        return theme;
    }
}