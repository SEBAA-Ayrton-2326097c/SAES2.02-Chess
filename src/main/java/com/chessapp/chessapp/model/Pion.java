package com.chessapp.chessapp.model;

public class Pion {

    private int x;
    private int y;
    private int pawnType;

    private int color;

    public Pion(int x, int y, int pawnType, int color) throws Exception{
        if (color != 1 && color != -1) {
            throw new Exception("ERREUR Pion : couleur non valide");
        }

        this.pawnType = pawnType;
        this.x = x;
        this.y = y;
        this.color = color;
    }

}
