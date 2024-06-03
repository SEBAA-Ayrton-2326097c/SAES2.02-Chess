package com.chessapp.chessapp.controller;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameController {

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                VBox vbox = new VBox();
                Label label = new Label((i == 0) ? "drag" : "ici");


                label.setOnDragDetected(event -> {
                    Dragboard db = label.startDragAndDrop(TransferMode.ANY);

                    ClipboardContent content = new ClipboardContent();
                    content.putString(label.getText());
                    db.setContent(content);

                    event.consume();
                });

                label.setOnDragOver(event -> {
                    if(event.getGestureSource() != label) {
                        event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                    }

                    event.consume();
                });

                label.setOnDragEntered(event -> {
                    if (event.getGestureSource() != label) {
                        label.setStyle("-fx-background-color: blue");
                    }

                    event.consume();
                });

                label.setOnDragExited(event -> {
                    if (event.getGestureSource() != label) {
                        label.setStyle("-fx-background-color: white");
                    }
                });

                label.setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    boolean success = false;

                    if (db.hasString()) {
                        label.setText(db.getString());
                        success = true;
                    }

                    event.setDropCompleted(success);
                    event.consume();
                });

                label.setOnDragDone(event -> {
                    if (event.getTransferMode() == TransferMode.MOVE) {
                        label.setText("");
                    }
                });

                vbox.setAlignment(Pos.CENTER);

                vbox.getChildren().add(label);
                grid.add(vbox, i, j);
            }
        }
    }

    public void onClick(int x, int y){
        System.out.println("clic sur " + x + ", " + y);
    }
}
