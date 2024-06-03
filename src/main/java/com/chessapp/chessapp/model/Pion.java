package com.chessapp.chessapp.model;
import javafx.util.Pair;

/*
    Classe pion
    Le pion de base sur un plateau d'échecs
 */
public class Pion extends Piece {

    public Pion(int x, int y, char pieceType, int color) throws Exception {
        super(x, y, pieceType, color);
    }

    /*
        Fonction calculateMovements()
        Calcule les mouvements possible du pion
     */
    @Override
    public Pair<Integer, Integer>[] calculateMovements(){
        // TODO

        return new Pair[1];
    }
}
