package com.chessapp.chessapp.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.io.IOException;

public class NewGameController {

    private GameController gameController;

    @FXML
    private TextField textFieldPlayerOne;
    @FXML
    private TextField textFieldPlayerTwo;
    @FXML
    private Button playButton;

    @FXML
    private void initialize() throws IOException {
        System.out.println("init GameController");

        createBindings();
    }

    /**
     * si le bouton de lancement est appuyé, on appelle la fonction dans le GameController
     */
    @FXML
    public void startGame() throws Exception {
        String playerOneName = textFieldPlayerOne.getText();
        String playerTwoName = textFieldPlayerTwo.getText();

        gameController.startGame(playerOneName, playerTwoName);
    }

    /**
     * crée les bindings nécessaire au bon fonctionnement de l'application
     */
    public void createBindings(){

        BooleanProperty playersNamesFilled = new SimpleBooleanProperty(false);

        BooleanBinding checkPlayerNamesFilled = new BooleanBinding() {
            {
                this.bind(textFieldPlayerOne.textProperty(), textFieldPlayerTwo.textProperty());
            }
            @Override
            protected boolean computeValue() {

                return !(textFieldPlayerOne.getText().isEmpty() || textFieldPlayerTwo.getText().isEmpty());

            }
        };

        playersNamesFilled.bind(checkPlayerNamesFilled);
        playButton.disableProperty().bind(playersNamesFilled.not());
    }


    public void gameEnded() {
        playButton.setDisable(false);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        System.out.println(gameController);
    }

    public GameController getGameController() {
        return gameController;
    }
}
