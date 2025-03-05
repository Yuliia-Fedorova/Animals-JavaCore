package com.domain.hierarchy.animals;

import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;
import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public class Rabbit extends Animal {
    public Rabbit(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }

    @Override
    public String getIcon() {
        return "\uD83D\uDC07";
    }

    @Override
    protected int getReproduceChance() {
        return 50;
    }

    @Override
    protected Organism getNewOrganism() {
        return new Rabbit(props, eatingMap);
    }
}
