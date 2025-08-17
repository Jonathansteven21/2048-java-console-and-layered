package com.example.mvc.entities;

public class Table {

    int[][] grid;
    Box size;
    int score;
    int bigger;

    public Table(int[][] grid, Box size, int score, int bigger) {
        this.grid = grid;
        this.size = size;
        this.score = score;
        this.bigger = bigger;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public Box getSize() {
        return size;
    }

    public void setSize(Box size) {
        this.size = size;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBigger() {
        return bigger;
    }

    public void setBigger(int bigger) {
        this.bigger = bigger;
    }
}
