package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Pion extends Piece {

    public Pion(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        super(x, y, pieceType, color, plateau);
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getCoordX();
        int y = this.getCoordY();
        int color = this.getColor();
        Plateau plateau = this.getPlateau();
        List<Pair<Integer, Integer>> movements = new ArrayList<>();
        int direction = color == 1 ? 1 : -1; // Direction du mouvement: 1 pour les blancs, -1 pour les noirs

        // 1. Mouvement d'une case en avant
        if (isInBounds(x, y + direction) && plateau.isEmpty(x, y + direction)) {
            movements.add(new Pair<>(x, y + direction));

            // 2. Mouvement de deux cases en avant depuis la position initiale
            if ((color == 1 && y == 1) || (color == -1 && y == 6)) {
                if (plateau.isEmpty(x, y + 2 * direction)) {
                    movements.add(new Pair<>(x, y + 2 * direction));
                }
            }
        }

        // 3. Capture en diagonale gauche
        if (isInBounds(x - 1, y + direction) && plateau.isEnemyPiece(x - 1, y + direction, color)) {
            movements.add(new Pair<>(x - 1, y + direction));
        }

        // 4. Capture en diagonale droite
        if (isInBounds(x + 1, y + direction) && plateau.isEnemyPiece(x + 1, y + direction, color)) {
            movements.add(new Pair<>(x + 1, y + direction));
        }

        return movements;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
