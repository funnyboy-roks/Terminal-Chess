package com.funnyboyroks.TerminalChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalChess {

    private static final List<String> moves = new ArrayList<>();
    private static final Board board = new Board();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(Color.YELLOW + "Commands: ");
        System.out.println("-m => Show the previous moves");
        System.out.println("-f => Flip the board");
        System.out.println("-b => Show the board" + Color.RESET);

        board.print();

        boolean whiteTurn = true;
        System.out.print((whiteTurn ? "White" : "Black") + " to move: ");
        String line = scanner.nextLine();
        while (line != null && !line.isBlank()) {
            boolean moved = false;
            switch (line.toLowerCase()) {
                case "-f" -> {
                    board.flip();
                    System.out.println(Color.YELLOW + "Board flipped" + Color.RESET);
                    board.print();
                }
                case "-b" -> board.print();
                case "-m" -> {
                    StringBuilder sb = new StringBuilder("Moves:\n");
                    for (int i = 0; i < moves.size(); i++) {
                        if (i % 2 == 0) {
                            sb.append((i / 2) + 1).append(' ');
                        }
                        sb.append(moves.get(i)).append(i % 2 == 1 ? '\n' : ' ');
                    }
                    System.out.println(sb);
                }
                default -> {
                    if (isValidMove(line)) {
                        String[] parts = line.split(" ");
                        Space from = new Space(parts[0]);
                        Space to = new Space(parts[1]);
                        moves.add(from + "" + to);
                        System.out.println(Color.GREEN + "" + from + " -> " + to + Color.RESET);
                    } else {
                        System.out.println(Color.RED + "Invalid move, format: 'from to'" + Color.RESET);
                    }


                }
            }

            if (moved) {
                whiteTurn = !whiteTurn;
            }
            System.out.print((whiteTurn ? "White" : "Black") + " to move: ");
            line = scanner.nextLine();
        }
    }

    private static boolean isValidMove(String line) {
        String[] parts = line.split(" ");
        if (parts.length != 2) {
            return false;
        }
        for (String part : parts) {
            int file = part.charAt(0);
            if (file < 'a' || file > 'h') {
                return false;
            }
            try {
                Integer.parseInt(part.substring(1));
            } catch (NumberFormatException e) {
                return false;
            }

        }
        return true;
    }

    public static boolean contains(int[] arr, int v) {
        for (int i : arr) {
            if (i == v) return true;
        }
        return false;
    }

}
