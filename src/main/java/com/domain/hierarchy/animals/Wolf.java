package com.domain.hierarchy.animals;

import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;
import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public class Wolf extends Animal {

    public Wolf(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }

    @Override
    public String getIcon() {
        return "\uD83D\uDC3A";
    }

    @Override
    protected int getReproduceChance() {
        return 15;
    }

    @Override
    protected Organism getNewOrganism() {
        return new Wolf(props, eatingMap);
    }
}
