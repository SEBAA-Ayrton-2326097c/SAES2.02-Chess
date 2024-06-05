package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Cavalier extends Piece {

    public Cavalier(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        super(x, y, pieceType, color, plateau);
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getCoordX();
        int y = this.getCoordY();
        int color = this.getColor();
        Plateau plateau = this.getPlateau();
        List<Pair<Integer, Integer>> movements = new ArrayList<>();

        int[][] moves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] move : moves) {
            int newX = x + move[0];
            int newY = y + move[1];

            if (isInBounds(newX, newY)) {
                if (plateau.isEmpty(newX, newY) || plateau.isEnemyPiece(newX, newY, color)) {
                    movements.add(new Pair<>(newX, newY));
                }
            }
        }

        return movements;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
