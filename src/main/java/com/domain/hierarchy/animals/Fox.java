package com.domain.hierarchy.animals;

import com.domain.hierarchy.Animal;
import com.domain.hierarchy.Organism;
import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public class Fox extends Animal {

    public Fox(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }

    @Override
    public String getIcon() {
        return "\uD83E\uDD8A";
    }

    @Override
    protected int getReproduceChance() {
        return 20;
    }

    @Override
    protected Organism getNewOrganism() {
        return new Fox(props, eatingMap);
    }
}
