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
        int mouvement;

        // mouvement droite
        mouvement = 1;
        while (isInBounds(x + mouvement, y)) {
            if (plateau.isEmpty(x + mouvement, y)) {
                movements.add(new Pair<>(x + mouvement, y));
            } else if (plateau.isEnemyPiece(x + mouvement, y, color)) {
                movements.add(new Pair<>(x + mouvement, y));
                break;
            } else if (plateau.isTeamPiece(x + mouvement, y, color)) {
                break;
            }
            mouvement += 1;
        }

        // mouvement gauche
        mouvement = 1;
        while (isInBounds(x - mouvement, y)) {
            if (plateau.isEmpty(x - mouvement, y)) {
                movements.add(new Pair<>(x - mouvement, y));
            } else if (plateau.isEnemyPiece(x - mouvement, y, color)) {
                movements.add(new Pair<>(x - mouvement, y));
                break;
            } else if (plateau.isTeamPiece(x - mouvement, y, color)) {
                break;
            }
            mouvement += 1;
        }

        // mouvement haut
        mouvement = 1;
        while (isInBounds(x, y + mouvement)) {
            if (plateau.isEmpty(x, y + mouvement)) {
                movements.add(new Pair<>(x, y + mouvement));
            } else if (plateau.isEnemyPiece(x, y + mouvement, color)) {
                movements.add(new Pair<>(x, y + mouvement));
                break;
            } else if (plateau.isTeamPiece(x, y + mouvement, color)) {
                break;
            }
            mouvement += 1;
        }

        // mouvement bas
        mouvement = 1;
        while (isInBounds(x, y - mouvement)) {
            if (plateau.isEmpty(x, y - mouvement)) {
                movements.add(new Pair<>(x, y - mouvement));
            } else if (plateau.isEnemyPiece(x, y - mouvement, color)) {
                movements.add(new Pair<>(x, y - mouvement));
                break;
            } else if (plateau.isTeamPiece(x, y - mouvement, color)) {
                break;
            }
            mouvement += 1;
        }

        return movements;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
