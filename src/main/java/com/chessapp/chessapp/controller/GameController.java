package com.chessapp.chessapp.controller;

import com.chessapp.chessapp.model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

/**
 * Controlleur principal du jeu, gère la boucle du jeu, l'initialisation de la grille, ...
 *
 */
public class GameController {


    // TODO : Couleurs de l'interface de chess.com
    @FXML
    private GridPane grid;
    private Piece movingPiece;

    private StackPane[][] cases;
    private Plateau plateau;

    private int clickNumber;
    private StackPane firstClickedPane;

    int sourceX, sourceY, destX, destY;

    /**
     * Initialisation de la matrice de stackPanes, du plateau de jeu, et des events pour chaque case
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

                String squareColor = ((i + j) % 2 == 0) ? "beige" : "lightgreen";
                stackPane.setStyle("-fx-background-color: " + squareColor + "; -fx-border-color: black");

                Piece piece = null;

                if (j == 1) piece = new Pion(i, j, -1);
                else if (j == 6) piece = new Pion(i, j, 1);


                // TODO : chaque classe pour chaque pièce
                /*if (j == 0 || j == 7) {
                    int color = (j == 0) ? 1 : 0; // 1 pour noir, 0 pour blanc

                    piece = switch (i) {
                        case 0, 7 -> new Tour(i, j, color);
                        case 1, 6 -> new Cavalier(i, j, color);
                        case 2, 5 -> new Fou(i, j, color);
                        case 3 -> (color == 1) ? new Reine(i, j, color) : new Roi(i, j, color);
                        case 4 -> (color == 1) ? new Roi(i, j, color) : new Reine(i, j, color);
                        default -> piece;
                    };*/

                if(piece != null) {
                    plateau.addPiece(i, j, piece);
                    stackPane.getChildren().add(piece);
                }

                stackPane.setOnMouseClicked(e -> {
                    onMouseClicked(e, stackPane);
                });

                cases[i][j] = stackPane;
                grid.add(stackPane, i, j);
            }
        }

    }

    /**
     * Appelle cette fonction lorsqu'on clique sur une case, elle permet de distinguer les deux phases de clics, la pièce bougée, l'appel de l'affichage. Gère aussi le mouvement graphique et technique entre deux cases et sa validité
     * @param e L'event du clic
     * @param stackPane Le stackpane concerné
     */
    public void onMouseClicked(Event e, StackPane stackPane) {

        List<Tuple> availableMoves;

        if (clickNumber == 0 && !stackPane.getChildren().isEmpty()) {
            movingPiece = (Piece) stackPane.getChildren().get(0);
            stackPane.setStyle("-fx-background-color: green; -fx-border-color: black");
            firstClickedPane = stackPane;
            clickNumber = 1;
            sourceX = GridPane.getColumnIndex((Node) e.getSource());
            sourceY = GridPane.getRowIndex((Node) e.getSource());

            availableMoves = plateau.getPiece(sourceX, sourceY).calculateMovements(plateau);

            showAvailableMoves(availableMoves);
            // System.out.println(availableMoves);

        }
        else if (clickNumber == 1) {
            destX = GridPane.getColumnIndex((Node) e.getSource());
            destY = GridPane.getRowIndex((Node) e.getSource());

            availableMoves = plateau.getPiece(sourceX, sourceY).calculateMovements(plateau);
            // System.out.println(sourceX + " " + sourceY + " " + destX + " " + destY);

            boolean moveIsValid = isMoveValid(availableMoves, sourceX, sourceY, destX, destY);

            if(moveIsValid) {

                stackPane.getChildren().setAll(movingPiece); // mouvement de la pièce visuellement

                try {
                    plateau.movement(sourceX, sourceY, destX, destY);
                    // plateau.showGrid();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }

            // dans tous les cas on remet les couleurs comme avant
            String sourceColor = ((sourceX + sourceY) % 2 == 0) ? "beige" : "lightgreen";
            firstClickedPane.setStyle("-fx-background-color: " + sourceColor + "; -fx-border-color: black");

            firstClickedPane = null;
            stopShowingAvailableMoves(availableMoves);
            availableMoves.clear();

            clickNumber = 0;

        }
    }

    /**
     * Vérifie si le mouvement fourni est valide
     * @param availableMoves liste des mouvements possible de la pièce
     * @param oldX X source
     * @param oldY Y source
     * @param newX X destination
     * @param newY Y destination
     * @return Booléen
     */
    public boolean isMoveValid(List<Tuple> availableMoves, int oldX, int oldY, int newX, int newY) {
        Piece destinationPiece = plateau.getPiece(newX, newY);
        Piece sourcePiece = plateau.getPiece(oldX, oldY);

        if (destinationPiece != null && destinationPiece.getColor() == sourcePiece.getColor()) return false;

        for (Tuple tuple : availableMoves) {
            Tuple newCoords = new Tuple(newX, newY);
            if (tuple.equals(newCoords)) return true;
        }
        return false;
    }

    /**
     * Commence l'affichage des mouvements possibles
     * @param availableMoves liste des mouvements possibles
     */
    public void showAvailableMoves(List<Tuple> availableMoves) {
        int x, y;
        for (Tuple coords : availableMoves) {
            x = (int) coords.getFirst();
            y = (int) coords.getSecond();
            cases[x][y].setStyle("-fx-background-color: red; -fx-border-color: black");
        }
    }

    /**
     * Arrête l'affichage des mouvements possibles
     * @param availableMoves liste des mouvements possibles
     */
    public void stopShowingAvailableMoves(List<Tuple> availableMoves) {
        int x, y;
        String color;
        for (Tuple coords : availableMoves) {
            x = (int) coords.getFirst();
            y = (int) coords.getSecond();
            color = ((x + y) % 2 == 0) ? "beige" : "lightgreen";
            cases[x][y].setStyle("-fx-background-color: " + color + "; -fx-border-color: black");
        }
    }
}
