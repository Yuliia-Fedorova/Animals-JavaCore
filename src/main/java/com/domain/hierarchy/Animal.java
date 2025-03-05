package com.domain.hierarchy;

import com.domain.util.Direction;
import com.domain.util.EatingMap;
import com.domain.util.Move;
import com.domain.util.OrganismProps;

import java.util.Map;

public abstract class Animal extends Organism {

    protected boolean moved = false;


    public Animal(OrganismProps props, Map<String, EatingMap> eatingMap) {
        super(props, eatingMap);
    }

    public Organism reproduce(Organism partner) {

        if (reproduced) { // already reproduced
            reproduced = false; // reset for next round
            partner.reproduced = false;
            return null;
        }

        if (partner.reproduced || this == partner || saturation < props.getFullSaturation()*0.7) {
            return null;
        }

        double chance = Math.random() * 100;
        if (chance >= (100 - getReproduceChance())) {
            reproduced = true;
            partner.reproduced = true;
            return getNewOrganism();
        } else {
            // also count if they tried
            reproduced = true;
            partner.reproduced = true;
        }

        return null;
    }

    public boolean eat(Organism organism) {
        if(organism.eaten || saturation >= props.getFullSaturation() * 0.8 || organism == this) {
            return false; // eaten or not hungry
        }
        EatingMap map = eatingMap.get(organism.getClass().getSimpleName());
        if(map == null) {
            return false; // cant eat it
        }

        double chance = Math.random() * 100;
        if(chance >= (100 - map.getChance())) {
            organism.eaten = true;
            saturation = saturation + map.getWeight();
            if(saturation >= props.getFullSaturation()) {
                saturation = props.getFullSaturation();
            }
            return true;
            //System.out.println(this.getClass().getSimpleName() + " eaten " + organism.getClass().getSimpleName());
        }
        return false;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public Move getMove() {
        int length = (int) (Math.random() * (props.getMaxMoveLength() + 1));
        return new Move(getDirection(), length);
    }

    private Direction getDirection() {
        int r = (int) (Math.random() * 4);
        switch (r) {
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.LEFT;
            case 3:
                return Direction.RIGHT;
            default:
                return Direction.UP;
        }
    }
}
