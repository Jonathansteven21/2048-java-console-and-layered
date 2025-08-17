package com.example.mvc.entities;

import java.util.Objects;

public class Box {
// This class represents a box in a grid with coordinates (x, y) and an optional value.
    int x;
    int y;
    Integer value;

    public Box(int x, int y, Integer value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Box)) return false;
        Box box = (Box) o;
        return Objects.equals(value, box.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
