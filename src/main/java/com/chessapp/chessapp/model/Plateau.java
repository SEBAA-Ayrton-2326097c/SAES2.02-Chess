package com.chessapp.chessapp.model;

import javafx.util.Pair;

import java.util.List;

public class Plateau {
    private Piece[][] plateau;

    public Plateau() {
        plateau = new Piece[8][8];
    }

    /**
     * Si les nouvelles coordonés sont dans la liste de coup possiible :
     * Faire le mouvement et return True sinon False
     * @param oldX
     * @param oldY
     * @param newX
     * @param newY
     * @return boolean
     * @throws Exception
     */
    public boolean movement(int oldX, int oldY, int newX, int newY) throws Exception {
        if (plateau[oldY][oldX] == null) {
            throw new Exception("ERREUR Plateau.movement() : case vide");
        }

        Pair <Integer, Integer> newCoordones = new Pair<>(newX, newY);
        List<Pair<Integer, Integer>> coupPossibles = plateau[oldX][oldY].calculateMovements();
//
        if(coupPossibles.contains(newCoordones)){
            plateau[newY][newX] = plateau[oldY][oldX];
            plateau[oldY][oldX] = null;
            return true;
        }
        else{
            return false;
        }

    }

    /**
     * Ajoute une piece aux coordonées (x,y)
     * @param x
     * @param y
     * @param pawnType de type char ('P' Pions, 'R' Tours, 'K' Rois,
     * @param color 1 pour les blancs et -1 pour les noirs
     * @throws Exception
     */
    public void addPawn(int x, int y, char pawnType, int color) throws Exception {
        if (plateau[y][x] != null) {
            throw new Exception("ERREUR Plateau.addPawn() : case non vide");
        }

        try {
            Piece pion = new Pion(x, y, pawnType, color);
            plateau[y][x] = pion;
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

    }

    public void showGrid() {
        for (Piece[] liste : plateau) {
            for (Piece p : liste) {
                System.out.print((p == null) ? "0" : "1");
            }
            System.out.println();
        }
    }

    public void setGrid(Piece[][] newPlateau) {
        this.plateau = newPlateau;
    }

    public Piece getPiece(int x, int y) {
        return plateau[y][x];
    }



    public boolean isEmpty(int x, int y) {
        return plateau[y][x] == null;
    }

    public boolean isEnemyPiece(int x, int y, int color) {
        Piece piece = plateau[y][x];
        return piece != null && piece.getColor() != color;
    }
}
