package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Tour extends Piece {

    public Tour(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        super(x, y, pieceType, color, plateau);
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getCoordX();
        int y = this.getCoordY();
        int color = this.getColor();
        Plateau plateau = this.getPlateau();
        List<Pair<Integer, Integer>> movements = new ArrayList<>();

        System.out.println("Position initiale de la Tour: (" + x + ", " + y + ")");

        // Mouvement vertical vers le haut
        for (int i = y - 1; i >= 0; i--) {
            System.out.println("Vérification de la case: (" + x + ", " + i + ")");
            if (plateau.isEmpty(x, i)) {
                movements.add(new Pair<>(x, i));
            } else {
                if (plateau.isEnemyPiece(x, i, color)) {
                    movements.add(new Pair<>(x, i));
                }
                break;
            }
        }

        // Mouvement vertical vers le bas
        for (int i = y + 1; i < 8; i++) {
            System.out.println("Vérification de la case: (" + x + ", " + i + ")");
            if (plateau.isEmpty(x, i)) {
                movements.add(new Pair<>(x, i));
            } else {
                if (plateau.isEnemyPiece(x, i, color)) {
                    movements.add(new Pair<>(x, i));
                }
                break;
            }
        }

        // Mouvement horizontal vers la gauche
        for (int i = x - 1; i >= 0; i--) {
            System.out.println("Vérification de la case: (" + i + ", " + y + ")");
            if (plateau.isEmpty(i, y)) {
                movements.add(new Pair<>(i, y));
            } else {
                if (plateau.isEnemyPiece(i, y, color)) {
                    movements.add(new Pair<>(i, y));
                }
                break;
            }
        }

        // Mouvement horizontal vers la droite
        for (int i = x + 1; i < 8; i++) {
            System.out.println("Vérification de la case: (" + i + ", " + y + ")");
            if (plateau.isEmpty(i, y)) {
                movements.add(new Pair<>(i, y));
            } else {
                if (plateau.isEnemyPiece(i, y, color)) {
                    movements.add(new Pair<>(i, y));
                }
                break;
            }
        }

        return movements;
    }
}