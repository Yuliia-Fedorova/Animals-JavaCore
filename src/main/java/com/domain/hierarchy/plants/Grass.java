package com.domain.hierarchy.plants;

import com.domain.hierarchy.Organism;
import com.domain.hierarchy.Plant;
import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public class Grass extends Plant {

    public Grass(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }

    @Override
    public String getIcon() {
        return "\uD83C\uDF3F";
    }

    @Override
    protected int getReproduceChance() {
        return 50;
    }

    @Override
    protected Organism getNewOrganism() {
        return new Grass(props, eatingMap);
    }
}
