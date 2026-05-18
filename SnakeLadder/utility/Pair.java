package SnakeLadder.utility;

public class Pair {
    int row;
    int col;

    public Pair(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair pair = (Pair) obj;
        return row == pair.row && col == pair.col;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(row, col);
    }
}
