package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Fou extends Piece {

    public Fou(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
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

        // mouvement haut-gauche
        mouvement = 1;
        while (isInBounds(x - mouvement, y + mouvement)) {
            if (plateau.isEmpty(x - mouvement, y + mouvement)) {
                movements.add(new Pair<>(x - mouvement, y + mouvement));
            } else if (plateau.isEnemyPiece(x - mouvement, y + mouvement, color)) {
                movements.add(new Pair<>(x - mouvement, y + mouvement));
                break;
            } else if (plateau.isTeamPiece(x - mouvement, y + mouvement, color)) {
                break;
            }
            mouvement += 1;
        }

        // mouvement haut-droit
        mouvement = 1;
        while (isInBounds(x + mouvement, y + mouvement)) {
            if (plateau.isEmpty(x + mouvement, y + mouvement)) {
                movements.add(new Pair<>(x + mouvement, y + mouvement));
            } else if (plateau.isEnemyPiece(x + mouvement, y + mouvement, color)) {
                movements.add(new Pair<>(x + mouvement, y + mouvement));
                break;
            } else if (plateau.isTeamPiece(x + mouvement, y + mouvement, color)) {
                break;
            }
            mouvement += 1;
        }

        // mouvement bas-gauche
        mouvement = 1;
        while (isInBounds(x - mouvement, y - mouvement)) {
            if (plateau.isEmpty(x - mouvement, y - mouvement)) {
                movements.add(new Pair<>(x - mouvement, y - mouvement));
            } else if (plateau.isEnemyPiece(x - mouvement, y - mouvement, color)) {
                movements.add(new Pair<>(x - mouvement, y - mouvement));
                break;
            } else if (plateau.isTeamPiece(x - mouvement, y - mouvement, color)) {
                break;
            }
            mouvement += 1;
        }

        // mouvement bas-droit
        mouvement = 1;
        while (isInBounds(x + mouvement, y - mouvement)) {
            if (plateau.isEmpty(x + mouvement, y - mouvement)) {
                movements.add(new Pair<>(x + mouvement, y - mouvement));
            } else if (plateau.isEnemyPiece(x + mouvement, y - mouvement, color)) {
                movements.add(new Pair<>(x + mouvement, y - mouvement));
                break;
            } else if (plateau.isTeamPiece(x + mouvement, y - mouvement, color)) {
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
