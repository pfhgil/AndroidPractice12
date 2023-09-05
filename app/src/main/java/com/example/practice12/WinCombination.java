package com.example.practice12;

public class WinCombination
{
    public Cell[] cells = new Cell[3];

    WinCombination(Cell firstCell, Cell secondCell, Cell blankCell)
    {
        cells[0] = firstCell;
        cells[1] = secondCell;
        cells[2] = blankCell;
    }
}
