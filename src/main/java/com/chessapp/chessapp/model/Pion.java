package com.chessapp.chessapp.model;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

/*
    Classe pion
    Le pion de base sur un plateau d'échecs
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

    /**
     * Calcule les mouvements possibles de la pièce
     * @return Liste de tuple de mouvements possibles
     */
    @Override
    public List<Tuple> calculateMovements(){
        ArrayList<Tuple> availableMovements = new ArrayList<>();

        if(super.getColor() == 1) {
            availableMovements.add(new Tuple(super.getxTab() + 1, super.getyTab() + 1));
            availableMovements.add(new Tuple(super.getxTab() + 2, super.getyTab() + 2));
        }

        return availableMovements;
    }

    @Override
    public boolean isValidMovement(int x, int y) {
        Tuple destinationCoords = new Tuple(x, y);
        for(Tuple coords : this.calculateMovements()) {
            if (destinationCoords.equals(coords)) return true;
        }
        return false;
    }
}
