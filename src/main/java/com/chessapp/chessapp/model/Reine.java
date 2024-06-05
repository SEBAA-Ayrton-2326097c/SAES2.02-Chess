package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Reine extends Piece {

    public Reine(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        super(x, y, pieceType, color, plateau);
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getCoordX();
        int y = this.getCoordY();
        int color = this.getColor();
        Plateau plateau = this.getPlateau();
        List<Pair<Integer, Integer>> movements = new ArrayList<>();

        // Déplacements dans les 8 directions
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
            int mouvement = 1;
            while (isInBounds(x + direction[0] * mouvement, y + direction[1] * mouvement)) {
                int newX = x + direction[0] * mouvement;
                int newY = y + direction[1] * mouvement;

                if (plateau.isEmpty(newX, newY)) {
                    movements.add(new Pair<>(newX, newY));
                } else if (plateau.isEnemyPiece(newX, newY, color)) {
                    movements.add(new Pair<>(newX, newY));
                    break;
                } else if (plateau.isTeamPiece(newX, newY, color)) {
                    break;
                }
                mouvement += 1;
            }
        }

        return movements;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}

