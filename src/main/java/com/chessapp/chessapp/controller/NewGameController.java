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
    private Button createPlayerOneButton;
    @FXML
    private Button createPlayerTwoButton;
    @FXML
    private Button importPlayerOneButton;
    @FXML
    private Button importPlayerTwoButton;

    private BooleanProperty playButtonAvailable;
    private BooleanProperty gameRunning;
    private BooleanProperty playerOneNameFilled;
    private BooleanProperty playerTwoNameFilled;

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

        gameRunning.set(true);
        gameController.startGame(playerOneName, playerTwoName);

    }

    @FXML
    public void importPlayerOne() {

    }

    @FXML
    public void importPlayerTwo() {

    }

    @FXML
    public void createPlayerOne() {

    }

    @FXML
    public void createPlayerTwo() {

    }

    /**
     * crée les bindings nécessaire au bon fonctionnement de l'application
     */
    public void createBindings(){

        gameRunning = new SimpleBooleanProperty(false);
        playButtonAvailable = new SimpleBooleanProperty(false);
        playerOneNameFilled = new SimpleBooleanProperty(false);
        playerTwoNameFilled = new SimpleBooleanProperty(false);

        BooleanBinding checkNamePlyOne = new BooleanBinding() {
            {
                this.bind(textFieldPlayerOne.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return !textFieldPlayerOne.getText().isEmpty();
            }
        };

        playerOneNameFilled.bind(checkNamePlyOne);

        BooleanBinding checkNamePlyTwo = new BooleanBinding() {
            {
                this.bind(textFieldPlayerTwo.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return !textFieldPlayerTwo.getText().isEmpty();
            }
        };

        playerTwoNameFilled.bind(checkNamePlyTwo);

        BooleanBinding playButtonAvailableCheck = new BooleanBinding() {
            {
                this.bind(textFieldPlayerTwo.textProperty(), textFieldPlayerOne.textProperty(), gameRunning);
            }
            @Override
            protected boolean computeValue() {

                return checkNamePlyOne.get() && checkNamePlyTwo.get() && !gameRunning.get();

            }
        };

        playButtonAvailable.bind(playButtonAvailableCheck);
        playButton.disableProperty().bind(playButtonAvailable.not());
    }


    public void gameEnded(int winner) {
        gameRunning.set(false);

    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        System.out.println(gameController);
    }

    public GameController getGameController() {
        return gameController;
    }
}
