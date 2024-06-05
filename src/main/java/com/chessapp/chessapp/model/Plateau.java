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
        if(plateau[oldY][oldX] == null) {
            throw new Exception("ERREUR Plateau.movement() : case source vide");
        }

        plateau[newY][newX] = plateau[oldY][oldX];
        plateau[oldY][oldX] = null;

        plateau[newY][newX].setxTab(newX);
        plateau[newY][newX].setyTab(newY);


    }

    public Piece getPiece(int x, int y){
        return plateau[y][x];
    }

    /*
        Fonction addPawn()
        Prend en argument des coordonnées et un type de pion (nombre) et le place aux coordonnées
        Si la case n'est pas vide, renvoie une erreur
     */
    public void addPiece(int x, int y, Piece piece) throws Exception {
        if (plateau[y][x] != null) {
            throw new Exception("ERREUR Plateau.addPawn() : case non vide");
        }

        plateau[y][x] = piece;
    }

    /*
        Fonction showGrid()
        Montre la grille dans la console, utile pour debug
     */
    public void showGrid() {

        for(Piece[] liste : plateau) {
            for(Piece p : liste) {
                System.out.print((p == null) ? "0" : p.getPieceType().charAt(0));
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
