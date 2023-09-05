package com.example.practice12;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings
{
    private static final String SETTINGS_NAME = "settings";
    private static final String NIGHT_MODE_ON_NAME = "night_mode_on";

    private static SharedPreferences themeSettings;
    private static SharedPreferences.Editor themeSettingsEditor;

    public static void init(final AppCompatActivity activity)
    {
        themeSettings = activity.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
        themeSettingsEditor = themeSettings.edit();

        if(!themeSettings.contains(NIGHT_MODE_ON_NAME))
        {
            themeSettingsEditor.putBoolean(NIGHT_MODE_ON_NAME, false);
            themeSettingsEditor.apply();

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        else
        {
            applyCurrentTheme();
        }
    }

    public static void applyCurrentTheme()
    {
        AppCompatDelegate.setDefaultNightMode(
                themeSettings.getBoolean(NIGHT_MODE_ON_NAME, false) ?
                        AppCompatDelegate.MODE_NIGHT_YES :
                        AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static void switchThemeMode()
    {
        themeSettingsEditor.putBoolean(NIGHT_MODE_ON_NAME,
                !themeSettings.getBoolean(NIGHT_MODE_ON_NAME, false));
        themeSettingsEditor.apply();
        applyCurrentTheme();
    }
}
