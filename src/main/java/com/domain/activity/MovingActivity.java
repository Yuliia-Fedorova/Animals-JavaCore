package com.domain.activity;

import com.domain.Cell;
import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;
import com.domain.util.Move;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class MovingActivity extends AbstractActivity {

    public MovingActivity(Cell[][] field, Queue<String> messageQueue) {
        super(field, messageQueue);
    }

    @Override
    public void runActivity() {
        for (Cell[] cells : field) {
            for (Cell cell : cells) {
                cell.getTypes().forEach(type -> {
                    cell.getResidents(type).forEach(resident -> {
                        if (resident instanceof Animal) {
                            ((Animal) resident).setMoved(false);
                        }
                    });
                });
            }
        }
        super.runActivity();
    }

    @Override
    protected void runActivity(Cell cell, int row, int col) {
        Set<Organism> toRemove = new HashSet<>();
        for (Organism resident : cell.getResidents()) {
            if (resident instanceof Animal) {
                Animal animal = (Animal) resident;
                if (animal.isMoved()) {
                    continue;
                }
                Move move = animal.getMove();
                if (moveAnimal(animal, move, row, col)) {
                    toRemove.add(resident);
                }
            }
        }
        cell.removeResidents(toRemove);
    }

    private boolean moveAnimal(Animal animal, Move move, int row, int col) {
        int newRow = row, newCol = col;
        if (move.getMoveLength() == 0) {
            return false;
        }
        switch (move.getDirection()) {
            case UP:
                newRow -= move.getMoveLength();
                break;
            case DOWN:
                newRow += move.getMoveLength();
                break;
            case LEFT:
                newCol -= move.getMoveLength();
                break;
            case RIGHT:
                newCol += move.getMoveLength();
                break;
        }

        newRow = Math.max(newRow, 0);
        newCol = Math.max(newCol, 0);
        newRow = Math.min(newRow, field.length - 1);
        newCol = Math.min(newCol, field[0].length - 1);
        //System.out.println("Moving " + organism. + " at " + row + ", " + col);
        if (field[newRow][newCol].addResident(animal)) {
            animal.setMoved(true);
            return row != newRow || col != newCol;
        }
        return false;
    }
}
