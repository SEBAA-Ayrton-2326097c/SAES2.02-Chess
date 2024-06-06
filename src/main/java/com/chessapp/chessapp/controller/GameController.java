package com.chessapp.chessapp.controller;

import com.chessapp.chessapp.model.*;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;
/**
 * Controlleur principal du jeu, gère la boucle du jeu, l'initialisation de la grille, ...
 *
 */
public class GameController {

    @FXML
    private Label labelPlayerOne;
    @FXML
    private Label labelPlayerTwo;
    @FXML
    private GridPane grid;

    private Piece movingPiece;

    private StackPane[][] cases;
    private Plateau plateau;

    private int clickNumber;
    private StackPane firstClickedPane;

    @FXML
    private NewGameController newGameController;

    private static final String squareOneColor = "#739552";
    private static final String squareTwoColor = "#EBECD0";
    private static final String canMoveSquareColor = "#EB7D6A";
    private static final String clickedSquareColor = "#F5F682";
    
    int sourceX, sourceY, destX, destY;

    @FXML
    public void initialize() {
        newGameController.setGameController(this);

        cases = new StackPane[8][8];
        plateau = new Plateau();

        clickNumber = 0;
    }

    /**
     * Fonction appellée quand le bouton de lancement est cliqué
     * @param playerOneName Nom du joueur 1
     * @param playerTwoName Nom du joueur 2
     */
    public void startGame(String playerOneName, String playerTwoName) throws Exception {
        System.out.println("start game called in GameController");

        labelPlayerOne.setText(playerOneName);
        labelPlayerTwo.setText(playerTwoName);

        for (int j = 0; j < 8; ++j) {
            for (int i = 0; i < 8; i++) {

                StackPane stackPane = new StackPane();

                String squareColor = ((i + j) % 2 == 0) ? squareOneColor : squareTwoColor;
                stackPane.setStyle("-fx-background-color: " + squareColor + "; -fx-border-color: black");

                Piece piece = getPiece(j, i);

                if (piece != null) {
                    plateau.addPiece(i, j, piece);
                    stackPane.getChildren().add(piece);
                }

                stackPane.setOnMouseClicked(e -> onMouseClicked(e, stackPane)
                );

                cases[i][j] = stackPane;
                grid.add(stackPane, i, j);
            }
        }
    }

    /**
     * Donne la pièce correspondante à une case donnée au début de la partie
     * @param x coordonnée X
     * @param y coordonnée Y
     * @return la pièce correspondante à la case
     * @throws Exception si x, y invalides
     */
    private static Piece getPiece(int y, int x) throws Exception {
        Piece piece = null;

        if (y == 1) piece = new Pawn(x, y, -1);
        else if (y == 6) piece = new Pawn(x, y, 1);

        if (y == 0 || y == 7) {
            int color = (y == 0) ? -1 : 1;

            piece = switch (x) {
                case 0, 7 -> new Rook(x, y, color);
                case 1, 6 -> new Knight(x, y, color);
                case 2, 5 -> new Bishop(x, y, color);
                case 3 -> (color == 1) ? new Queen(x, y, color) : new King(x, y, color);
                case 4 -> (color == 1) ? new King(x, y, color) : new Queen(x, y, color);
                default -> piece;
            };
        }
        return piece;
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
            stackPane.setStyle("-fx-background-color: " + clickedSquareColor + "; -fx-border-color: black");
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
            String sourceColor = ((sourceX + sourceY) % 2 == 0) ? squareOneColor : squareTwoColor;
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
            cases[x][y].setStyle("-fx-background-color: " + canMoveSquareColor + "; -fx-border-color: black");
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
            color = ((x + y) % 2 == 0) ? squareOneColor : squareTwoColor;
            cases[x][y].setStyle("-fx-background-color: " + color + "; -fx-border-color: black");
        }
    }

    public void gameEnded(int winner){
        newGameController.gameEnded();
    }
}
