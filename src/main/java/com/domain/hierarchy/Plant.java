package com.domain.hierarchy;

import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public abstract class Plant extends Organism {

    public Organism reproduce() {

        if(reproduced) { // already reproduced
            reproduced = false; // reset for next round
            return null;
        }

        double chance = Math.random() * 100;
        if (chance >= (100 - getReproduceChance())) {
            reproduced = true;
            return getNewOrganism();
        }

        return null;
    };

    protected Plant(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }
}
