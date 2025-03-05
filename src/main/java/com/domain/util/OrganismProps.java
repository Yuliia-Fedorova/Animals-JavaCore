package com.domain.util;

public class OrganismProps {
    private String name;
    private double weight;
    private int maxCellCount;
    private int maxMoveLength;
    private double fullSaturation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMaxCellCount() {
        return maxCellCount;
    }

    public void setMaxCellCount(int maxCellCount) {
        this.maxCellCount = maxCellCount;
    }

    public int getMaxMoveLength() {
        return maxMoveLength;
    }

    public void setMaxMoveLength(int maxMoveLength) {
        this.maxMoveLength = maxMoveLength;
    }

    public double getFullSaturation() {
        return fullSaturation;
    }

    public void setFullSaturation(double fullSaturation) {
        this.fullSaturation = fullSaturation;
    }
}
