package com.chessapp.chessapp.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController{
    public void initButtons(GridPane grid, Button nouvelle_partie, Button parties, Button joueurs) {
        grid = new GridPane();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                VBox vbox = new VBox();
                Label label = new Label(i + "/" + j);
                vbox.setAlignment(Pos.CENTER);

                vbox.getChildren().add(label);
                grid.add(vbox, i, j);
            }
        }
        nouvelle_partie = new Button();
        Button finalNouvelle_partie = nouvelle_partie;
        nouvelle_partie.setOnMouseClicked(event -> {
            System.out.println("-");
            changeScene("views/ChessApp.fxml", finalNouvelle_partie);
        });

        parties = new Button();
        Button finalParties = parties;
        parties.setOnMouseClicked(event -> {
            System.out.println("--");
            changeScene("views/parties.fxml", finalParties);
        });

        joueurs = new Button();
        joueurs.setOnMouseClicked(event -> {

        });
    }

    /**
     * Méthode qui sert à changer de scene
     * @param sceneName Nom de la scene à charger
     * @param button VBox qui sert à charger une scène
     */
    public void changeScene(String sceneName, Button button) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(sceneName));
            Stage stage = (Stage) button.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
