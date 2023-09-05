package com.example.practice12;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Stats
{
    private static final String STATS_NAME = "games_stats";
    private static final String BOT_WON_CNT = "bot_won_cnt";
    private static final String PLAYER_WON_CNT = "player_won_cnt";
    private static final String DRAWS_CNT = "draws_cnt";

    private SharedPreferences statsSharedPrefs;
    private SharedPreferences.Editor statsSharedPrefsEditor;

    private MainActivity mainActivity;

    public void init(final MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;

        statsSharedPrefs = mainActivity.getSharedPreferences(STATS_NAME, Context.MODE_PRIVATE);
        statsSharedPrefsEditor = statsSharedPrefs.edit();

        if(!statsSharedPrefs.contains(BOT_WON_CNT))
        {
            statsSharedPrefsEditor.putInt(BOT_WON_CNT, 0);
            statsSharedPrefsEditor.putInt(PLAYER_WON_CNT, 0);
            statsSharedPrefsEditor.putInt(DRAWS_CNT, 0);
            statsSharedPrefsEditor.apply();
        }
        else
        {
            mainActivity.botScoreText.setText("Bot`s score: " + statsSharedPrefs.getInt(BOT_WON_CNT, 0));
            mainActivity.yourScoreText.setText("Player`s score: " + statsSharedPrefs.getInt(PLAYER_WON_CNT, 0));
            mainActivity.drawsText.setText("Draws: " + statsSharedPrefs.getInt(DRAWS_CNT, 0));
        }
    }

    public void addBotWon()
    {
        statsSharedPrefsEditor.putInt(BOT_WON_CNT, statsSharedPrefs.getInt(BOT_WON_CNT, 0) + 1);
        statsSharedPrefsEditor.apply();

        mainActivity.botScoreText.setText("Bot`s score: " + statsSharedPrefs.getInt(BOT_WON_CNT, 0));
    }

    public void addPlayerWon()
    {
        statsSharedPrefsEditor.putInt(PLAYER_WON_CNT, statsSharedPrefs.getInt(PLAYER_WON_CNT, 0) + 1);
        statsSharedPrefsEditor.apply();

        mainActivity.yourScoreText.setText("Player`s score: " + statsSharedPrefs.getInt(PLAYER_WON_CNT, 0));
    }

    public void addDraw()
    {
        statsSharedPrefsEditor.putInt(DRAWS_CNT, statsSharedPrefs.getInt(DRAWS_CNT, 0) + 1);
        statsSharedPrefsEditor.apply();

        mainActivity.drawsText.setText("Draws: " + statsSharedPrefs.getInt(DRAWS_CNT, 0));
    }
}
