package com.chessapp.chessapp.controller;

import com.chessapp.chessapp.model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Controlleur principal du jeu, gère la boucle du jeu, l'initialisation de la grille, ...
 *
 */
public class GameController {

    private Piece movingPiece;
    @FXML
    private GridPane grid;

    private StackPane[][] cases;
    private Plateau plateau;

    private int clickNumber;
    private StackPane firstClickedPane;

    private int sourceX, sourceY;
    private int destX, destY;

    /**
     * Initialisation
     *
     */
    @FXML
    public void initialize() throws Exception {
        cases = new StackPane[8][8];
        plateau = new Plateau();

        clickNumber = 0;

        for(int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; i++) {

                StackPane stackPane = new StackPane();

                stackPane.setStyle("-fx-background-color: beige; -fx-border-color: black");

                Piece piece = ((j + i) % 2 == 1 && j < 2) ? new Pion(i, j, -1) : null;

                if(piece != null) {
                    plateau.addPiece(i, j, piece);
                    stackPane.getChildren().add(piece);
                }

                stackPane.setOnMouseClicked(e -> {
                    if (clickNumber == 0 && stackPane.getChildren().size() > 0) {
                        movingPiece = (Piece) stackPane.getChildren().get(0);
                        stackPane.setStyle("-fx-background-color: green; -fx-border-color: black");
                        firstClickedPane = stackPane;
                        clickNumber = 1;
                        sourceX = GridPane.getColumnIndex((Node) e.getSource());
                        sourceY = GridPane.getRowIndex((Node) e.getSource());
                    }
                    else if (clickNumber == 1) {
                        destX = GridPane.getColumnIndex((Node) e.getSource());
                        destY = GridPane.getRowIndex((Node) e.getSource());

                        if(plateau.getPiece(destX, destY) == null
                                || plateau.getPiece(destX, destY).getColor() != movingPiece.getColor()) {


                            stackPane.getChildren().setAll(movingPiece);
                            firstClickedPane.setStyle("-fx-background-color: beige; -fx-border-color: black");
                            try {
                                System.out.println(sourceX + sourceY + destX + destY + "/");
                                plateau.movement(sourceX, sourceY, destX, destY);
                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                            clickNumber = 0;
                        }


                    }
                });

                grid.add(stackPane, i, j);
            }
        }

    }

    public void onMouseClicked(StackPane stackPane, int x, int y) {

    }
}
