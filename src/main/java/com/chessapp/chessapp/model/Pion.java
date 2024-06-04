package com.chessapp.chessapp.model;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class Pion extends Piece {

    public Pion(int x, int y, char pieceType, int color) throws Exception {
        super(x, y, pieceType, color);
    }

    @Override
    public List<Pair<Integer, Integer>> calculateMovements() {
        int x = this.getX();
        int y = this.getY();
        int color = this.getColor();

        List<Pair<Integer, Integer>> movements = new ArrayList<>();
        int direction = color == 1 ? 1 : -1; // Direction du mouvement: 1 pour les blancs, -1 pour les noirs

        movements.add(new Pair<Integer, Integer>(x, y + 1));
        return movements;
    }




    private boolean isInBounds(int x, int y) {
        // Vérifier si les coordonnées (x, y) sont dans les limites du plateau d'échecs
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    public static void main(String[] args) throws Exception {
        Plateau plateau1 = new Plateau();

        plateau1.addPawn(0,3,'P', 1);
        plateau1.showGrid();

    }

}
