package com.chessapp.chessapp.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Controlleur principal du jeu, gère la boucle du jeu, l'initialisation de la grille, ...
 *
 */
public class GameController {

    @FXML
    private ImageView draggedIv;
    @FXML
    private GridPane grid;
    private static Image bishopB = new Image("file:src/main/resources/com/chessapp/chessapp/img/bishop_b.png");
    private static Image bishopW = new Image("file:src/main/resources/com/chessapp/chessapp/img/bishop_w.png");
    private static Image empty = new Image("file:src/main/resources/com/chessapp/chessapp/img/empty.png");
    private VBox[][] vBoxes;

    /**
     * Initialisation
     *
     */
    @FXML
    public void initialize() {
        ImageView iv = new ImageView();
        iv.setImage(bishopB);
        vBoxes = new VBox[8][8];

        for(int j = 0; j < 8; j++) {
            for(int i = 0; i < 8; i++) {
                VBox vbox = new VBox();
                vbox.setStyle("-fx-border-style: solid; -fx-background-color: beige");
                ImageView imageView = new ImageView((i == 0 && j % 2 == 0) ? bishopB : empty);

                imageView.setOnDragDetected(event -> {
                    Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);

                    draggedIv = imageView;

                    ClipboardContent content = new ClipboardContent();
                    content.putImage(imageView.getImage());
                    db.setContent(content);

                    event.consume();
                });

                imageView.setOnDragOver(event -> {
                    if(event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        imageView.getParent().setStyle("-fx-border-color: red; -fx-background-color: beige");
                    }

                    event.consume();
                });

                imageView.setOnDragExited(event -> {
                    if(event.getGestureSource() != imageView && event.getDragboard().hasImage()) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                        imageView.getParent().setStyle("-fx-border-color: black; -fx-background-color: beige");
                    }

                    event.consume();

                });

                imageView.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();

                    imageView.setImage(db.getImage());
                    draggedIv.setImage(empty);

                    event.consume();
                });

                imageView.setOnDragDone(event -> {
                    draggedIv = null;
                    event.consume();
                });

                vbox.setAlignment(Pos.CENTER);

                vbox.getChildren().add(imageView);
                vBoxes[i][j] = vbox;
                grid.add(vbox, i, j);
            }
        }
    }

    public void onClick(int x, int y){
        System.out.println("clic sur " + x + ", " + y);
    }

}
