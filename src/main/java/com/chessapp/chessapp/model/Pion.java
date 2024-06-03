package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pion extends Piece {
    private Plateau plateau;

    public Pion(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        super(x, y, pieceType, color);
        this.plateau = plateau;
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getX();
        int y = this.getY();
        int color = this.getColor();

        List<Pair<Integer, Integer>> movements = new ArrayList<>();
        int direction = color == 1 ? 1 : -1; // Direction du mouvement: 1 pour les blancs, -1 pour les noirs

        movements.add(new Pair(x, y - direction));
        return movements;
    }



    private boolean isValidMove(int x, int y) {
        // Vérifier si la case (x, y) est dans les limites du plateau et est vide
        return isInBounds(x, y) && plateau.isEmpty(x, y);
    }

    private boolean isValidCapture(int x, int y) {
        // Vérifier si la case (x, y) est dans les limites du plateau et contient une pièce ennemie
        return isInBounds(x, y) && plateau.isEnemyPiece(x, y, this.getColor());
    }

    private boolean isInBounds(int x, int y) {
        // Vérifier si les coordonnées (x, y) sont dans les limites du plateau d'échecs
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
