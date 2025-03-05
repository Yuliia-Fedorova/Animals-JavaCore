package com.domain.activity;

import com.domain.Cell;

import java.util.Queue;

public abstract class AbstractActivity {

    protected Cell[][] field;
    protected Queue<String> messageQueue;

    public AbstractActivity(Cell[][] field, Queue<String> messageQueue) {
        this.field = field;
        this.messageQueue = messageQueue;
    }

    public void run() {
        try {
            runActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void runActivity() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                runActivity(field[i][j], i, j);
            }
        }
    }

    protected void runActivity(Cell cell, int row, int col) {
        runActivity(cell);
    }

    protected void runActivity(Cell cell) {}
}
