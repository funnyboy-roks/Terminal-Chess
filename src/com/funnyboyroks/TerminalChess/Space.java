package com.funnyboyroks.TerminalChess;

import java.util.Objects;

public class Space {

    public int x, y;

    Space(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Space(String str) {
        this.x = fromChar(str.charAt(0));
        this.y = Integer.parseInt(str.charAt(1) + "");
    }

    public static int fromChar(char c) {
        return c - 'a';
    }

    public static char toChar(int i) {
        return (char) ('a' + i);
    }

    @Override
    public String toString() {
        return toChar(x) + "" + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Space space = (Space) o;
        return x == space.x && y == space.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
