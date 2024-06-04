package com.chessapp.chessapp.model;

import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.List;

public abstract class Piece extends ImageView {

    private int x;
    private int y;
    private char pieceType;
    private int color;
    protected Plateau plateau; // Référence au plateau

    public Piece(int x, int y, char pieceType, int color, Plateau plateau) throws Exception {
        if (color != 1 && color != -1) {
            throw new Exception("ERREUR Pion : couleur non valide");
        }

        this.pieceType = pieceType;
        this.x = x;
        this.y = y;
        this.color = color;
        this.plateau = plateau; // Initialisation du plateau
    }

    public char getName() {
        return pieceType;
    }
    public int getColor() {return  color;}
    public int getCoordX() {return x;}
    public int getCoordY() {return y;}

    public void setCoordX(int x) { this.x = x; }
    public void setCoordY(int y) { this.y = y; }

    public Plateau getPlateau() { return plateau; }

    public void setPlateau(Plateau plateau) { this.plateau = plateau; }

    public abstract List<Pair<Integer, Integer>> calculateMovements();
}
