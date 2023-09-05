package com.example.practice12;


import java.util.ArrayList;
import java.util.List;

public class BotLogic
{
    public static void move()
    {
        if(MainActivity.getMovesDone() == 9) return;

        // for defend or win
        for (WinCombination winCombination : MainActivity.winCombinations)
        {
            CharSequence firstCellText =
                    MainActivity.field[winCombination.cells[0].yIdx][winCombination.cells[0].xIdx]
                    .getText();

            CharSequence secondCellText =
                    MainActivity.field[winCombination.cells[1].yIdx][winCombination.cells[1].xIdx]
                            .getText();

            if ((firstCellText.equals(MainActivity.PLAYER_CHAR) && secondCellText.equals(MainActivity.PLAYER_CHAR)) ||
                    (firstCellText.equals(MainActivity.BOT_CHAR) && secondCellText.equals(MainActivity.BOT_CHAR)))
            {
                if (MainActivity.field[winCombination.cells[2].yIdx][winCombination.cells[2].xIdx]
                        .getText().equals(""))
                {
                    MainActivity.field[winCombination.cells[2].yIdx][winCombination.cells[2].xIdx]
                            .setText(MainActivity.BOT_CHAR);
                    return;
                }
            }
        }

        // attack
        int randX = MainActivity.random.nextInt(MainActivity.field.length);
        int randY = MainActivity.random.nextInt(MainActivity.field[0].length);

        while (true)
        {
            if (MainActivity.field[randX][randY].getText().equals(""))
            {
                MainActivity.field[randX][randY].setText(MainActivity.BOT_CHAR);
                break;
            }
            else
            {
                randX = MainActivity.random.nextInt(MainActivity.field.length);
                randY = MainActivity.random.nextInt(MainActivity.field[0].length);
            }
        }
    }
}
