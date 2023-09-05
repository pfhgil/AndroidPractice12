package com.example.practice12;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    public static final Random random = new Random();

    public static final Button[][] field = new Button[3][3];

    public static final WinCombination[] winCombinations = new WinCombination[24];

    public TextView yourScoreText;
    public TextView botScoreText;
    public TextView drawsText;

    private Button nightModeBtn;

    // ---------------------------

    public static final String BOT_CHAR = "O";
    public static final String PLAYER_CHAR = "X";
    public static final String DRAW_CHAR = "-";

    public static String winnerChar = "";

    private View.OnClickListener buttonsClickListener;

    private static int movesDone = 0;

    // ----------------------------

    public static final Stats stats = new Stats();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        winCombinations[0] = new WinCombination(new Cell(0, 0), new Cell(0, 1), new Cell(0, 2));
        winCombinations[1] = new WinCombination(new Cell(0, 0), new Cell(0, 2), new Cell(0, 1));
        winCombinations[2] = new WinCombination(new Cell(0, 1), new Cell(0, 2), new Cell(0, 0));

        winCombinations[3] = new WinCombination(new Cell(1, 0), new Cell(1, 1), new Cell(1, 2));
        winCombinations[4] = new WinCombination(new Cell(1, 0), new Cell(1, 2), new Cell(1, 1));
        winCombinations[5] = new WinCombination(new Cell(1, 1), new Cell(1, 2), new Cell(1, 0));

        winCombinations[6] = new WinCombination(new Cell(2, 0), new Cell(2, 1), new Cell(2, 2));
        winCombinations[7] = new WinCombination(new Cell(2, 0), new Cell(2, 2), new Cell(2, 1));
        winCombinations[8] = new WinCombination(new Cell(2, 1), new Cell(2, 2), new Cell(2, 0));

        // -------

        winCombinations[9] = new WinCombination(new Cell(0, 0), new Cell(1, 0), new Cell(2, 0));
        winCombinations[10] = new WinCombination(new Cell(0, 0), new Cell(2, 0), new Cell(1, 0));
        winCombinations[11] = new WinCombination(new Cell(1, 0), new Cell(2, 0), new Cell(0, 0));

        winCombinations[12] = new WinCombination(new Cell(0, 1), new Cell(1, 1), new Cell(2, 1));
        winCombinations[13] = new WinCombination(new Cell(0, 1), new Cell(2, 1), new Cell(1, 1));
        winCombinations[14] = new WinCombination(new Cell(1, 1), new Cell(2, 1), new Cell(0, 1));

        winCombinations[15] = new WinCombination(new Cell(0, 2), new Cell(1, 2), new Cell(2, 2));
        winCombinations[16] = new WinCombination(new Cell(0, 2), new Cell(2, 2), new Cell(1, 2));
        winCombinations[17] = new WinCombination(new Cell(1, 2), new Cell(2, 2), new Cell(0, 2));

        // -------

        winCombinations[18] = new WinCombination(new Cell(0, 0), new Cell(1, 1), new Cell(2, 2));
        winCombinations[19] = new WinCombination(new Cell(0, 0), new Cell(2, 2), new Cell(1, 1));
        winCombinations[20] = new WinCombination(new Cell(1, 1), new Cell(2, 2), new Cell(0, 0));

        winCombinations[21] = new WinCombination(new Cell(0, 2), new Cell(1, 1), new Cell(2, 0));
        winCombinations[22] = new WinCombination(new Cell(0, 2), new Cell(2, 0), new Cell(1, 1));
        winCombinations[23] = new WinCombination(new Cell(1, 1), new Cell(2, 0), new Cell(0, 2));

        // --------

        nightModeBtn = findViewById(R.id.nightModeButton);
        nightModeBtn.setOnClickListener((view) ->
        {
                Settings.switchThemeMode();
        });

        field[0][0] = findViewById(R.id.button1);
        field[0][1] = findViewById(R.id.button2);
        field[0][2] = findViewById(R.id.button3);

        field[1][0] = findViewById(R.id.button4);
        field[1][1] = findViewById(R.id.button5);
        field[1][2] = findViewById(R.id.button6);

        field[2][0] = findViewById(R.id.button7);
        field[2][1] = findViewById(R.id.button8);
        field[2][2] = findViewById(R.id.button9);

        yourScoreText = findViewById(R.id.yourScoreText);
        botScoreText = findViewById(R.id.botScoreText);
        drawsText = findViewById(R.id.drawsText);

        Settings.init(this);
        stats.init(this);

        buttonsClickListener = view -> {
            // bad practice but i dont care
            Button btn = (Button) view;
            if(btn.getText() == "")
            {
                btn.setText(PLAYER_CHAR);
                movesDone++;

                checkForWinner();

                BotLogic.move();
                movesDone++;

                checkForWinner();
            }
        };

        for (Button[] buttons : field)
        {
            for (Button btn : buttons)
            {
                btn.setOnClickListener(buttonsClickListener);
            }
        }
    }

    private void checkForWinner()
    {
        if(!winnerChar.equals("")) return;

        for(WinCombination winCombination : winCombinations)
        {
            if(field[winCombination.cells[0].yIdx][winCombination.cells[0].xIdx].getText().equals(BOT_CHAR) &&
                    field[winCombination.cells[1].yIdx][winCombination.cells[1].xIdx].getText().equals(BOT_CHAR) &&
                    field[winCombination.cells[2].yIdx][winCombination.cells[2].xIdx].getText().equals(BOT_CHAR))
            {
                winnerChar = BOT_CHAR;

                break;
            }
            else if(field[winCombination.cells[0].yIdx][winCombination.cells[0].xIdx].getText().equals(PLAYER_CHAR) &&
                    field[winCombination.cells[1].yIdx][winCombination.cells[1].xIdx].getText().equals(PLAYER_CHAR) &&
                    field[winCombination.cells[2].yIdx][winCombination.cells[2].xIdx].getText().equals(PLAYER_CHAR))
            {
                winnerChar = PLAYER_CHAR;

                Log.d("player winner",
                        "" + winCombination.cells[0].yIdx + ", " + winCombination.cells[0].xIdx + ", " +
                                field[winCombination.cells[0].yIdx][winCombination.cells[0].xIdx].getText() + "\n" +
                                winCombination.cells[1].yIdx + ", " + winCombination.cells[1].xIdx + ", " +
                                field[winCombination.cells[1].yIdx][winCombination.cells[1].xIdx].getText() + "\n" +
                                winCombination.cells[2].yIdx + ", " + winCombination.cells[2].xIdx + ", " +
                                field[winCombination.cells[2].yIdx][winCombination.cells[2].xIdx].getText() + "\n");

                break;
            }
        }

        if(winnerChar.equals("") && movesDone == 9)
        {
            winnerChar = DRAW_CHAR;
        }

        if(!winnerChar.equals(""))
        {
            Intent winIntent = new Intent(this, WinActivity.class);
            startActivity(winIntent);
        }
    }

    public static void restart()
    {
        winnerChar = "";

        for(Button[] buttons : field)
        {
            for(Button btn : buttons)
            {
                btn.setText("");
            }
        }

        movesDone = 0;
    }

    public static int getMovesDone() { return movesDone; }
}
