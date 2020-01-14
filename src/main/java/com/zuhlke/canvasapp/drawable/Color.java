package com.zuhlke.canvasapp.drawable;

import java.util.Objects;

public class Color {

    private final char color;

    public Color(char color) {
        this.color = color;
    }

    public Color() {
        color = ' ';
    }

    @Override
    public String toString() {
        return String.valueOf(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Color color1 = (Color) o;
        return color == color1.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
