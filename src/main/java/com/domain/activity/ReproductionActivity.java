package com.domain.activity;

import com.domain.Cell;
import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;
import com.domain.hierarchy.Plant;

import java.util.*;

public class ReproductionActivity extends AbstractActivity {


    public ReproductionActivity(Cell[][] field, Queue<String> messageQueue) {
        super(field, messageQueue);
    }

    @Override
    protected void runActivity(Cell cell) {
        List<Organism> residents = new ArrayList<>(cell.getResidents());
        Set<Organism> children = new HashSet<>();
        for (int i = 0; i < residents.size(); i++) {
            Organism resident = residents.get(i);
            if ((residents.size() + children.size()) >= resident.getMaxCellCount()) {
                break;
            }
            if (resident instanceof Plant) {
                Organism child = ((Plant) resident).reproduce();
                if (child != null) {
                    children.add(child);
                }
            }
            if (resident instanceof Animal) {
                Organism child = reproduceAnimal((Animal) resident, residents, i);
                if (child != null) {
                    children.add(child);
                }
            }
        }
        cell.addResidents(children);
    }

    private Organism reproduceAnimal(Animal organism, List<Organism> partners, int from) {
        for (int i = from + 1; i < partners.size(); i++) {
            Organism child = organism.reproduce(partners.get(i));
            if (child != null) {
                return child;
            }
        }
        return null;
    }
}
