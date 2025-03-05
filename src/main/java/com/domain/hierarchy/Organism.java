package com.domain.hierarchy;

import com.domain.util.EatingMap;
import com.domain.util.OrganismProps;

import java.util.Map;

public abstract class Organism {

    protected OrganismProps props;
    protected boolean reproduced = false;
    protected double saturation;
    protected Map<String, EatingMap> eatingMap;
    protected boolean eaten = false;

    protected Organism(OrganismProps props, Map<String, EatingMap> eatingMap) {
        this.props = props;
        this.saturation = props.getFullSaturation()*0.3;
        this.eatingMap = eatingMap;
    }

    public abstract String getIcon();

    protected abstract int getReproduceChance();

    protected abstract Organism getNewOrganism();

    public int getMaxCellCount() {
        return props.getMaxCellCount();
    }

    public void reduceSaturation() {
        saturation -= props.getFullSaturation() * 0.1; // -20%
    }

    public boolean isEaten() {
        return eaten;
    }

    public boolean isStarving() {
        return saturation < 0;
    }
}
