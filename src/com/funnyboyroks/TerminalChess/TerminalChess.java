package com.funnyboyroks.TerminalChess;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalChess {

    private static final List<String> moves = new ArrayList<>();
    private static final Board board = new Board();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        info("Commands: ");
        info("-m => Show the previous moves");
        info("-f => Flip the board");
        info("-b => Show the board");

        board.print();

        boolean whiteTurn = true;
        System.out.print((whiteTurn ? "White" : "Black") + " to move: ");
        String line = scanner.nextLine();
        while (line != null && !line.isBlank()) {
            boolean moved = false;
            switch (line.toLowerCase()) {
                case "-f" -> {
                    board.flip();
                    info("Board flipped.");
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
                case "-h" -> {
                    // Highlight the possible moves
                    // '-' for empty spot
                    // 'x' for spot with enemy
                }
                default -> {
                    if (!isValidMove(line)) {
                        error("Invalid Format.  Format: 'from to'");
                        break;
                    }
                    String[] parts = line.split(" ");
                    Space from = new Space(parts[0]);
                    Space to = new Space(parts[1]);
                    moves.add(from + "" + to);
                    moved = move(from, to);
                    if (moved) {

                        success("%s -> %s", from, to);
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

    public static boolean move(Space from, Space to) {
        if (from.equals(to)) {
            error("Invalid Move");
            return false;
        }

        Piece fromPiece = board.getPiece(from);
        Piece toPiece = board.getPiece(to);

        return true;
    }

    public static boolean contains(int[] arr, int v) {
        for (int i : arr) {
            if (i == v) return true;
        }
        return false;
    }

    public static void error(String str, Object... subs) {
        System.out.printf(Color.RED + str + Color.RESET + "\n", subs);
    }

    public static void success(String str, Object... subs) {
        System.out.printf(Color.GREEN + str + Color.RESET + "\n", subs);
    }

    public static void info(String str, Object... subs) {
        System.out.printf(Color.YELLOW + str + Color.RESET + "\n", subs);
    }

}
