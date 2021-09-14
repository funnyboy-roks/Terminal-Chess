package com.funnyboyroks.TerminalChess;

import java.util.Arrays;

public class Util {

    public static Piece[][] rotateMat(Piece[][] arr) {
        Piece[][] out = new Piece[8][8]; // hard coded bc I'm lazy
        for (int y = 0; y < arr.length; y++) {
            Piece[] row = arr[y];
            for (int x = 0; x < row.length; x++) {
                out[7 - y][7 - x] = arr[y][x];
            }
        }
        return out;
    }

    public static <T> T[][] cloneMat(T[][] mat) {
        T[][] out = mat.clone();
        for (int i = 0; i < out.length; i++) {
            out[i] = out[i].clone();
        }
        return out;
    }

    public static String[] charArrToString(char[] arr) {
        String[] out = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            out[i] = "" + arr[i];
        }
        return out;
    }


}
