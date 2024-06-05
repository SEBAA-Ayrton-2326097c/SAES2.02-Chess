package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Roi extends Piece {

    public Roi(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        super(x, y, pieceType, color, plateau);
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getCoordX();
        int y = this.getCoordY();
        int color = this.getColor();
        Plateau plateau = this.getPlateau();
        List<Pair<Integer, Integer>> movements = new ArrayList<>();

        // Déplacements possibles pour le roi
        int[][] directions = {
                {1, 0},  // droite
                {-1, 0}, // gauche
                {0, 1},  // haut
                {0, -1}, // bas
                {1, 1},  // haut-droite
                {-1, 1}, // haut-gauche
                {1, -1}, // bas-droite
                {-1, -1} // bas-gauche
        };

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

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
