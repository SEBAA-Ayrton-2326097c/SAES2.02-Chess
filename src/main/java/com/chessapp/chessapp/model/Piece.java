package com.chessapp.chessapp.model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Pair;

import java.util.List;

public abstract class Piece extends ImageView {

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
    public int getColor() {return  color;}


    /*
        Fonction abstraite calculateMovements()
        Renvoie une liste de coordonnées où la pièce peut se déplacer
     */
    public abstract List<Pair<Integer, Integer>> calculateMovements();

}
