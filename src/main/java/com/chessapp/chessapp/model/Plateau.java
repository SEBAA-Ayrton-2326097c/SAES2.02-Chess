package com.chessapp.chessapp.model;

import javafx.util.Pair;

import java.util.List;

public class Plateau {
    private Piece[][] plateau;

    public Plateau() {
        plateau = new Piece[8][8];
    }

    public boolean movement(int oldX, int oldY, int newX, int newY) throws Exception {
        if (plateau[oldY][oldX] == null) {
            throw new Exception("ERREUR Plateau.movement() : case vide");
        }

        Pair<Integer, Integer> newCoordones = new Pair<>(newX, newY);
        List<Pair<Integer, Integer>> coupPossibles = plateau[oldX][oldY].calculateMovements();

        if(coupPossibles.contains(newCoordones)){
            plateau[newY][newX] = plateau[oldY][oldX];
            plateau[oldY][oldX] = null;
            plateau[newY][newX].setCoordX(newX);
            plateau[newY][newX].setCoordY(newY);
            return true;
        }
        else{
            return false;
        }
    }

    public void addPiece(int x, int y, char pieceType, int color) throws Exception {
        if (!isInBounds(x, y)) {
            throw new Exception("ERREUR Plateau.addPiece() : coordonnées hors limites");
        }
        if (plateau[y][x] != null) {
            throw new Exception("ERREUR Plateau.addPiece() : case non vide");
        }

        try {
            Piece piece;
            switch (pieceType) {
                case 'P':
                    piece = new Pion(x, y, pieceType, color, this);
                    break;
                case 'R':
                    piece = new Tour(x, y, pieceType, color, this);
                    break;
                case 'B':
                    piece = new Fou(x, y, pieceType, color, this);
                    break;
                case 'K':
                    piece = new Roi(x, y, pieceType, color, this);
                    break;
                case 'Q':
                    piece = new Reine(x, y, pieceType, color, this);
                    break;
                case 'N':
                    piece = new Cavalier(x, y, pieceType, color, this);
                    break;
                default:
                    throw new Exception("Type de pièce non valide");
            }
            plateau[y][x] = piece;
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

    public boolean isTeamPiece(int x, int y, int color) {
        Piece piece = plateau[y][x];
        return piece != null && piece.getColor() == color;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
