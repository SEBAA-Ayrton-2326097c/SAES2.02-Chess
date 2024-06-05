package com.chessapp.chessapp.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe Pion, extension de Piece, qui symbolise le pion de base des échecs
 */
public class Pion extends Piece {

    public static Image imgPionBlanc = new Image("file:src/main/resources/com/chessapp/chessapp/img/pawn_w.png");
    public static Image imgPionNoir = new Image("file:src/main/resources/com/chessapp/chessapp/img/pawn_b.png");


    public Pion(int xTab, int yTab, int color) throws Exception {
        super(xTab, yTab, "pawn", color);

        if (color == -1) {
            super.setImage(imgPionNoir);
        } else if (color == 1) {
            super.setImage(imgPionBlanc);
        }
    }

    @Override
    public List<Tuple> calculateMovements(Plateau plateau){
        ArrayList<Tuple> availableMovements = new ArrayList<>();
        
        int x, y, color;
        
        x = super.getxTab();
        y = super.getyTab();
        color = super.getColor();
        
        int direction = color * -1;

        if (color == -1 && y == 1 || color == 1 && y == 6 && plateau.getPiece(x, y + 2 * direction) == null) { // déplacement initial double
            availableMovements.add(new Tuple(x, y + 2*direction));
        }
        if (y + direction >= 0 && y + direction < 8 && plateau.getPiece(x, y + direction) == null) { // déplacement tout droit
            availableMovements.add(new Tuple(x, y + direction));
        }

        if (x - 1 >= 0 && y + direction >= 0 && y + direction < 8 // si la diagonale avant gauche ne dépasse pas le tableau
                && plateau.getPiece(x - 1, y + direction) != null // et qu'il y a une pièce à l'avant gauche
                && plateau.getPiece(x - 1, y + direction).getColor() != color) { // et que sa couleur est différente
            availableMovements.add(new Tuple(x - 1, y + direction));
        }

        if (x + 1 < 8 && y + direction >= 0 && y + direction < 8 // si la diagonale avant droite ne dépasse pas
                && plateau.getPiece(x + 1, y + direction) != null // et qu'il y a une pièce
                && plateau.getPiece(x + 1, y + direction).getColor() != super.getColor()) { // et que sa couleur est différente
            availableMovements.add(new Tuple(x + 1, y + direction));
        }

        return availableMovements;
    }


    @Override
    public boolean isValidMovement(int x, int y, Plateau plateau) {
        Tuple destinationCoords = new Tuple(x, y);

        for(Tuple coords : this.calculateMovements(plateau)) {
            if (destinationCoords.equals(coords)) return true;
        }

        return false;
    }
}
