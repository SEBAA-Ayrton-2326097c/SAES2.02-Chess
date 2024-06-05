package com.chessapp.chessapp.model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.List;

public abstract class Piece extends ImageView {

    private int xTab;
    private int yTab;
    private String pieceType;
    private int color;


    /*
        Constructeur Pion()
        Prend des coordonnées de base, et une couleur
     */
    public Piece(int xTab, int yTab, String pieceType, int color) throws Exception{
        if (color != 1 && color != -1) {
            throw new Exception("ERREUR Pion : couleur non valide");
        }

        this.pieceType = pieceType;
        this.xTab = xTab;
        this.yTab = yTab;
        this.color = color;

        super.setFitHeight(50);
        super.setPreserveRatio(true);
    }

    public String getPieceType() {
        return pieceType;
    }

    public int getColor() {
        return color;
    }

    public int getxTab() {
        return xTab;
    }

    public int getyTab() {
        return yTab;
    }

    /*
    Fonction abstraite calculateMovements()
    Renvoie une liste de coordonnées où la pièce peut se déplacer
     */
    public abstract List<Tuple> calculateMovements();

    public abstract boolean isValidMovement(int x, int y);
}
