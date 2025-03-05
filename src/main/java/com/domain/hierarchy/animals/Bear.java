package com.domain.hierarchy.animals;

import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;
import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public class Bear extends Animal {

    public Bear(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }

    @Override
    public String getIcon() {
        return "\0uD83D\0uDC3B";
    }

    @Override
    protected int getReproduceChance() {
        return 15;
    }

    @Override
    protected Organism getNewOrganism() {
        return new Bear(props, eatingMap);
    }
}
