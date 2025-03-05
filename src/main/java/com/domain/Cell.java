package com.domain;

import com.domain.hierarchy.Organism;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


public class Cell {

    private final Set<Organism> residents = ConcurrentHashMap.newKeySet();

    public Set<Organism> getResidents() {
        return residents;
    }

    public Set<Organism> getResidents(String type) {
        return residents.stream()
                .filter(r->r.getClass().getSimpleName().equals(type))
                .collect(Collectors.toSet());
    }

    public List<Organism> getAllResidents() {
        return new ArrayList<>(residents);
    }

    public void addResidents(Set<Organism> residentsToAdd) {
        for (Organism organism : residentsToAdd) {
            addResident(organism);
        }
    }

    public boolean addResident(Organism organism) {
        long count = residents.stream().filter(o->o.getClass().equals(organism.getClass())).count();
        if(count < organism.getMaxCellCount()) {
            residents.add(organism);
            return true;
        }
        return false;
    }

    public void removeResidents(Set<Organism> residentsToRemove) {
        residents.removeAll(residentsToRemove);
    }

    public Set<String> getTypes() {
        return residents.stream().map(r->r.getClass().getSimpleName()).collect(Collectors.toSet());
    }

//    public List<String> getIcons() {
//        return residents.stream().map(Organism::getIcon).collect(Collectors.toList());
//    }
}
