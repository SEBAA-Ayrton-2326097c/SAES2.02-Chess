package com.chessapp.chessapp.controller;

import com.chessapp.chessapp.model.Plateau;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameController {

    @FXML
    private GridPane grid;

    @FXML
    public void initialize() throws Exception {
        Plateau plt = new Plateau();
        plt.addPawn(0, 0, 'P', 1);

        plt.showGrid();

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                VBox vbox = new VBox();
                vbox.setStyle(String.format("-fx-background-color: #FF00000"));
                Label label = new Label(i + "/" + j);
                vbox.setAlignment(Pos.CENTER);

                vbox.getChildren().add(label);
                grid.add(vbox, i, j);
            }
        }
    }
}
