package com.chessapp.chessapp.model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

public abstract class Piece extends ImageView {

    private int xTab;
    private int yTab;
    private String pieceType;
    private int color;


    /**
     * Constructeur d'une pièce, initialise les paramètres de base
     * @param xTab coordonnée d'apparition en X
     * @param yTab coordonnée d'apparition en Y
     * @param pieceType String du type de pièce (pawn, knight, ..)
     * @param color couleur de la pièce (-1 / 1)
     * @throws Exception Erreur si couleur non valide
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

    public void setxTab(int xTab) {
        this.xTab = xTab;
    }

    public void setyTab(int yTab) {
        this.yTab = yTab;
    }

    /**
     * Calcule tous les mouvements possible de la pièce
     * @param plateau plateau sur lequel donner les mouvements possibles
     * @return La liste de tous les mouvements possible de la pièce
     */
    public abstract List<Tuple> calculateMovements(Plateau plateau);


    /*
        Fonction abstraite calculateMovements()
        Renvoie une liste de coordonnées où la pièce peut se déplacer
     */
    abstract Pair<Integer, Integer>[] calculateMovements();

}
