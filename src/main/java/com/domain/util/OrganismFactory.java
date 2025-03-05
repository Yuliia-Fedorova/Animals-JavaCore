package com.domain.util;

import com.domain.hierarchy.animals.Fox;
import com.domain.hierarchy.animals.Rabbit;
import com.domain.hierarchy.animals.Wolf;
import com.domain.hierarchy.plants.Grass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrganismFactory {

    Map<String, OrganismProps> propsMap = new HashMap<>();
    List<EatingMap> eatingMaps = new ArrayList<>();

    public OrganismFactory(String propsPath, String eatingMapPath) throws IOException {
        setProps(propsPath);
        setEatingMaps(eatingMapPath);
    }

    private void setProps(String propsPath) throws IOException {
        for (String line : Files.readAllLines(Paths.get(propsPath))) {
            if(line.startsWith("name")) {
                continue; // skip first line
            }
            String[] split = line.split(",");
            OrganismProps organismProps = new OrganismProps();
            organismProps.setName(split[0]);
            organismProps.setWeight(Double.parseDouble(split[1]));
            organismProps.setMaxCellCount(Integer.parseInt(split[2]));
            organismProps.setMaxMoveLength(Integer.parseInt(split[3]));
            organismProps.setFullSaturation(Double.parseDouble(split[4]));
            propsMap.put(organismProps.getName(), organismProps);
        }
    }

    private void setEatingMaps(String eatingMapPath) throws IOException {
        for (String line : Files.readAllLines(Paths.get(eatingMapPath))) {
            if(line.startsWith("name")) {
                continue; // skip first line
            }
            String[] split = line.split(",");
            EatingMap eatingMap = new EatingMap();
            eatingMap.setHunter(split[0]);
            eatingMap.setTarget(split[1]);
            eatingMap.setChance(Integer.parseInt(split[2]));
            eatingMaps.add(eatingMap);
        }
    }

    public Grass createGrass() {
        return new Grass(propsMap.get("Grass"), prepareEatingMap("Grass"));
    }

    public Wolf createWolf() {
        return new Wolf(propsMap.get("Wolf"), prepareEatingMap("Wolf"));
    }

    public Fox createFox() {
        return new Fox(propsMap.get("Fox"), prepareEatingMap("Fox"));
    }

    public Rabbit createRabbit() {
        return new Rabbit(propsMap.get("Rabbit"), prepareEatingMap("Rabbit"));
    }

    private Map<String, EatingMap> prepareEatingMap(String organism) {
        Map<String, EatingMap> eatingMap = new HashMap<>();
        for(EatingMap map: eatingMaps) {
            if(map.getHunter().equals(organism)) {
                map.setWeight(propsMap.get(organism).getWeight());
                eatingMap.put(map.getTarget(), map);
            }
        }
        return eatingMap;
    }
}
