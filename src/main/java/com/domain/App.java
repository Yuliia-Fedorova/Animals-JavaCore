package com.domain;

import com.domain.activity.DeathActivity;
import com.domain.activity.EatActivity;
import com.domain.activity.MovingActivity;
import com.domain.activity.ReproductionActivity;
import com.domain.hierarchy.Organism;
import com.domain.util.OrganismFactory;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);
    //private Cell[][] field = new Cell[4][15];
    private static final Long ITERATION_DURATION = 1000l;
    private Cell[][] field = new Cell[3][3];
    private ConsoleOutput console;
    private ReproductionActivity reproductionActivity;
    private MovingActivity movingActivity;
    private EatActivity eatActivity;
    private DeathActivity deathActivity;
    private OrganismFactory organismFactory;

    private Queue<String> messageQueue = new LinkedList<>();

    public App(String propsPath, String eatingPropsPath) throws Exception {
        organismFactory = new OrganismFactory(propsPath, eatingPropsPath);
    }


    public void run() {
        reproductionActivity = new ReproductionActivity(field, messageQueue);
        movingActivity = new MovingActivity(field, messageQueue);
        eatActivity = new EatActivity(field, messageQueue);
        deathActivity = new DeathActivity(field, messageQueue);
        setField();

        console = new ConsoleOutput(11, 6, field);

        executor.scheduleAtFixedRate(() -> {
            console.output();
            Map<String, Integer> groupedMap = new HashMap<>();
            while (!messageQueue.isEmpty()) {
                String msg = messageQueue.poll();
                groupedMap.put(msg, groupedMap.getOrDefault(msg, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : groupedMap.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue() + " times");
            }


        }, 0, ITERATION_DURATION, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(() -> {
            reproductionActivity.run();
        }, 0, ITERATION_DURATION, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(() -> {
            movingActivity.run();
        }, 0, ITERATION_DURATION, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(() -> {
            eatActivity.run();
        }, 0, ITERATION_DURATION, TimeUnit.MILLISECONDS);

        executor.scheduleAtFixedRate(() -> {
            deathActivity.run();
        }, 0, ITERATION_DURATION, TimeUnit.MILLISECONDS);
    }


    private void setField() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Cell c = new Cell();
                Set<Organism> organisms = new HashSet<>();
                organisms.add(organismFactory.createGrass());
                organisms.add(organismFactory.createGrass());
                organisms.add(organismFactory.createGrass());
                organisms.add(organismFactory.createGrass());
                if (i == 1 && j == 1) {
                    organisms.add(organismFactory.createRabbit());
                    organisms.add(organismFactory.createRabbit());
                    organisms.add(organismFactory.createRabbit());
                    organisms.add(organismFactory.createRabbit());
                    organisms.add(organismFactory.createRabbit());
                    organisms.add(organismFactory.createRabbit());
                }
                if (i == 2 && j == 2) {
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                    organisms.add(organismFactory.createWolf());
                }
                if (i == 1 && j == 1) {
                    //organisms.add(organismFactory.createFox());
                    //organisms.add(organismFactory.createFox());
                }
                c.addResidents(organisms);
                field[i][j] = c;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        System.out.println("Start simulation...");
        new App("C:\\Users\\Yuliia\\Desktop\\Java Syntax\\Java-Core\\Animals-JavaCore\\src\\organism.csv",
                "C:\\Users\\Yuliia\\Desktop\\Java Syntax\\Java-Core\\Animals-JavaCore\\src\\eating-map.csv").run();
    }
}
