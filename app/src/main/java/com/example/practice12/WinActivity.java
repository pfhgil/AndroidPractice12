package com.example.practice12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinActivity extends AppCompatActivity
{
    private TextView whoWinTextView;

    private Button restartGameBtn;

    @Override
    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(R.layout.win_activity);

        whoWinTextView = findViewById(R.id.winnerTextView);

        restartGameBtn = findViewById(R.id.restartGameButton);

        if(MainActivity.winnerChar.equals(MainActivity.BOT_CHAR))
        {
            whoWinTextView.setText("Bot won!");
            MainActivity.stats.addBotWon();
        }
        else if(MainActivity.winnerChar.equals(MainActivity.PLAYER_CHAR))
        {
            whoWinTextView.setText("You won!");
            MainActivity.stats.addPlayerWon();
        }
        else
        {
            whoWinTextView.setText("Draw!");
            MainActivity.stats.addDraw();
        }

        restartGameBtn.setOnClickListener(view -> {
            MainActivity.restart();

            Intent mainIntent = new Intent(this, MainActivity.class);
            startActivity(mainIntent);
        });
    }
}
