package com.domain.activity;

import com.domain.Cell;
import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class DeathActivity extends AbstractActivity {

    public DeathActivity(Cell[][] field, Queue<String> messageQueue) {
        super(field, messageQueue);
    }

    @Override
    protected void runActivity(Cell cell, int row, int col) {
        Set<Organism> toRemove = new HashSet<>();
        for (Organism resident : cell.getResidents()) {
            if (resident.isEaten()) {
                toRemove.add(resident);
            }
            if (resident.isStarving() && resident instanceof Animal) {
                toRemove.add(resident);
                messageQueue.add(resident.getIcon() + " has died because of starving");
            }
        }
        cell.removeResidents(toRemove);
    }
}
