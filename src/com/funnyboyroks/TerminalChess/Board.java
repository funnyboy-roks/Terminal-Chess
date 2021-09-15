package com.funnyboyroks.TerminalChess;

public class Board {

    public static final char[] ROW_MARKS = "¹²³⁴⁵⁶⁷⁸".toCharArray();
    public static final char[] FILE_MARKS = "ᵃᵇᶜᵈᵉᶠᵍʰ".toCharArray();
    public static final Color[] BACKGROUND = new Color[]{
        Color.GREEN_BACKGROUND,
        Color.BLUE_BACKGROUND,
        };
    private final Piece[][] board = new Piece[8][8];
    private boolean whiteDown = true;

    public Board() {
        for (int y = 0; y < board.length; y++) {
            Piece[] row = board[y];
            for (int x = 0; x < row.length; x++) {
                if (y == 0 || y == 7) {
                    for (Piece.Type type : Piece.Type.values()) {
                        if (type.getFiles() != null && TerminalChess.contains(type.getFiles(), x)) {
                            board[y][x] = new Piece(type, y == 0);
                        }
                    }
                } else {
                    board[y][x] = new Piece(Piece.Type.NONE, false);
                }
            }
        }
        for (int i = 0; i < 8; ++i) {
            board[1][i] = new Piece(Piece.Type.PAWN, true);
            board[6][i] = new Piece(Piece.Type.PAWN, false);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Piece[][] rot = whiteDown ? Util.rotateMat(board) : Util.cloneMat(board);
        for (int y = 0; y < rot.length; y++) {
            Piece[] row = rot[y];
            sb
                .append(
                    (whiteDown ?
                        Util.mirrorArr(ROW_MARKS) :
                        ROW_MARKS)
                        [y]
                );
            for (int x = 0; x < row.length; x++) {
                Piece p = row[x];
                Color shadedColor = BACKGROUND[x % 2 == 0 != (y % 2 == 0) ? 1 : 0];
                sb
                    .append(shadedColor)
                    .append(' ')
                    .append(
                        p == null ?
                            "-" :
                            p.toString(shadedColor)
                    ).append(' ');
            }
            sb.append(Color.RESET).append('\n');
        }
        sb
            .append("  ")
            .append(
                String.join(
                    "  ",
                    Util.charArrToString(
                        whiteDown ?
                            FILE_MARKS :
                            Util.mirrorArr(FILE_MARKS)
                    )
                )
            );
        return sb.toString() + Color.RESET;
    }

    public void print() {
        System.out.println(this);
    }

    public void flip() {
        this.whiteDown = !this.whiteDown;
    }

    public void setWhiteDown(boolean whiteDown) {
        this.whiteDown = whiteDown;
    }

    public Piece getPiece(Space space) {
        return board[space.y][space.x];
    }

    public void setPiece(Space space, Piece p) {
        board[space.y][space.x] = p;
    }
}
