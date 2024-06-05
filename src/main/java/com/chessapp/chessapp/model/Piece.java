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


    /**
     * Vérifie si les coordonnées du mouvement fourni est un mouvement valide pour la pièce
     * @param x X cible
     * @param y Y cible
     * @param plateau Plateau sur lequel vérifier le mouvement
     * @return booléen, si le mouvement est valide ou non
     */
    public abstract boolean isValidMovement(int x, int y, Plateau plateau);
}
