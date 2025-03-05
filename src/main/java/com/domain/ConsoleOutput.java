package com.domain;

import com.domain.hierarchy.Organism;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleOutput {
    private final Cell[][] field;
    private final int cellWidth;
    private final int cellHeight;
    private char[][] grid;
    private String empty = "\u3030\ufe0f";
    private Integer iteration = 0;
    private Map<String, String> iconMap = new HashMap<>();

    public ConsoleOutput(int cellWidth, int cellHeight, Cell[][] field) {
        this.field = field;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        grid = new char[field.length * cellHeight][field[0].length * cellWidth];

        // collect all icons
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                Cell cell = field[i][j];
                cell.getAllResidents().forEach(resident -> {
                   iconMap.put(resident.getClass().getSimpleName(), resident.getIcon());
                });
            }
        }
    }

    public void output() {
        System.out.println("Iteration: " + iteration);
        iteration++;
        resetGrid();
        updateGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

    }

    private void resetGrid() {
        int row = 0, col = 0;
        for (int i = 0; i < grid.length; i++) {
            row++;
            for (int j = 0; j < grid[0].length; j++) {
                col++;
                grid[i][j] = ' ';
                if (row == cellHeight) {
                    grid[i][j] = '-';
                    if(col==2) {
                        grid[i][j] = empty.charAt(1);
                        grid[i][j-1] = empty.charAt(0);
                    }
                }
                if (col == cellWidth) {
                    grid[i][j] = '|';
                    col = 0;
                }
            }
            if (row == cellHeight) {
                row = 0;
            }
            col = 0;
        }
    }

    private void updateGrid() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                updateCell(i*cellHeight, j*cellWidth, field[i][j]);
            }
        }
    }

    private void updateCell(int r, int c, Cell cell) {
        List<String> types = new ArrayList<>(cell.getTypes()).stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < cellHeight-1; i++) {
            String info = empty;
            if(i < types.size()) {
                String type = types.get(i);
                Set<Organism> organisms = cell.getResidents(type);
                info = iconMap.get(type) + " " + organisms.size();
            }

            for(int j = 0; j < cellWidth-1 && j < info.length(); j++) {
                grid[r+i][c+j] = info.charAt(j);
            }
        }
    }
}
