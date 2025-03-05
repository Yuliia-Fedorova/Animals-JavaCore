package com.domain.activity;

import com.domain.Cell;
import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class EatActivity extends AbstractActivity {

    public EatActivity(Cell[][] field, Queue<String> messageQueue) {
        super(field, messageQueue);
    }

    @Override
    protected void runActivity(Cell cell) {
        // update saturation
        for (String type : cell.getTypes()) {
            cell.getResidents(type).forEach(Organism::reduceSaturation);
        }

        List<Organism> residents = cell.getAllResidents();
        for (int i = 0; i < residents.size(); i++) {
            for (int j = i+1; j < residents.size(); j++) {
                if(residents.get(i) instanceof Animal) {
                    Animal animal = (Animal) residents.get(i);
                    boolean hasEaten = animal.eat(residents.get(j));
                    if(hasEaten && residents.get(j) instanceof Animal) {
                        messageQueue.add(animal.getIcon() + " has eaten " + residents.get(j).getIcon());
                    }
                }
            }
        }
    }
}
