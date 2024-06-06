package com.chessapp.chessapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private GridPane grid;
    @FXML
    private Button nouvelle_partie;
    @FXML
    private Button parties;
    @FXML
    private Button joueurs;

    public void initialize(URL arg0, ResourceBundle arg1){
        GameController gameController = new GameController();
        gameController.initButtons(grid, nouvelle_partie,parties,joueurs);
    }
}
