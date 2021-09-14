package com.funnyboyroks.TerminalChess;

public class Piece {

    private static final boolean EMOJI = false;

    private final Type type;
    private final boolean white;

    public Piece(Type type, boolean white) {

        this.type = type;
        this.white = white;
    }

    public String toString(Color shadedColor) {
        return (white ? Color.RED_BOLD_BRIGHT : Color.BLACK_BOLD_BRIGHT) + "" + shadedColor + type.rep;
    }

    @Override
    public String toString() {
        return (white ? Color.RED_BOLD_BRIGHT : Color.BLACK_BOLD_BRIGHT) + "" + type.rep + Color.RESET;
    }

    public enum Type {
        PAWN('p', '♟'),
        KNIGHT('N', '♞', 1, 6),
        ROOK('R', '♜', 0, 7),
        BISHOP('B', '♝', 2, 5),
        KING('K', '♚', 4),
        QUEEN('Q', '♛', 3),
        NONE(' ', ' '),
        ;

        private final char rep;
        private final int[] files;

        Type(char rep, char emoji) {
            this.rep = EMOJI ? emoji : rep;
            this.files = null;
        }

        Type(char rep, char emoji, int... files) {
            this.rep = EMOJI ? emoji : rep;
            this.files = files;
        }

        public int[] getFiles() {
            return files;
        }
    }

}
