package com.chessapp.chessapp.model;

import javafx.scene.control.Label;
import javafx.util.Pair;

public abstract class Piece extends Label {

    private int x;
    private int y;
    private char pieceType;
    private int color;


    /*
        Constructeur Pion()
        Prend des coordonnées de base, et une couleur
     */
    public Piece(int x, int y, char pieceType, int color) throws Exception{
        if (color != 1 && color != -1) {
            throw new Exception("ERREUR Pion : couleur non valide");
        }

        this.pieceType = pieceType;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public char getName() {
        return pieceType;
    }


    /*
        Fonction abstraite calculateMovements()
        Renvoie une liste de coordonnées où la pièce peut se déplacer
     */
    abstract Pair<Integer, Integer>[] calculateMovements();

}
