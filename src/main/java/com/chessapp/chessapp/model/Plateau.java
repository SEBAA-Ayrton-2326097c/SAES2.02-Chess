package com.chessapp.chessapp.model;

/*
    Classe Plateau
    Initialise le plateau de 8*8 cases où se déplaceront les pions
 */
public class Plateau {
    private Piece[][] plateau;

    /*
        Constructeur Plateau();
        Initialise la liste de liste
     */
    public Plateau() {
        plateau = new Piece[8][8];
    }

    /*
        Fonction movement()
        Prend en argument des coordonnées de deux cases, déplace le contenu de la première dans la deuxième,
        puis vide la première.
        Si la première case est vide, renvoie une erreur
     */
    public void movement(int oldX, int oldY, int newX, int newY) throws Exception {
        if(plateau[oldY][oldX] == null || plateau[newY][newX] == null) {
            throw new Exception("ERREUR Plateau.movement() : case vide");
        }
        else if (plateau[oldY][oldX].getClass().getSimpleName() != "Pion") {
            System.out.println("WARNING : type of element in " + oldY + ", " + oldX + " not a pawn");
        }

        plateau[newY][newX] = plateau[oldX][oldY];
        plateau[oldY][oldX] = null;
    }

    /*
        Fonction addPawn()
        Prend en argument des coordonnées et un type de pion (nombre) et le place aux coordonnées
        Si la case n'est pas vide, renvoie une erreur
     */
    public void addPawn(int x, int y, char pawnType, int color) throws Exception {
        if (plateau[y][x] == null) {
            throw new Exception("ERREUR Plateau.addPawn() : case non vide");
        }

        Piece pion = new Pion(x, y, pawnType, color);
        plateau[y][x] = pion;

    }

    /*
        Fonction showGrid()
        Montre la grille dans la console, utile pour debug
     */
    public void showGrid() {

        for(Piece[] liste : plateau) {
            for(Piece p : liste) {
                System.out.print((p == null) ? "0" : "1");
            }
            System.out.println();
        }

    }

    /*
        Fonction setGrid()
        Change toute la grille manuellement, utile pour les tests
     */
    public void setGrid(Piece[][] newPlateau) {
        this.plateau = newPlateau;
    }

}
