package com.chessapp.chessapp.controller;

import com.chessapp.chessapp.model.PlayerHandler;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Button importPlayerOneButton;
    @FXML
    private Button importPlayerTwoButton;
    @FXML
    private Label infoLabel;

    private String playerOneName, playerTwoName;
    private BooleanProperty gameRunning;
    private BooleanProperty playerOneImported;
    private BooleanProperty playerTwoImported;

    @FXML
    private void initialize() throws IOException {
        System.out.println("init GameController");
        infoLabel.setText("Merci de importer vos pseudos (3 chars min.)");

        createBindings();
    }

    /**
     * si le bouton de lancement est appuyé, on appelle la fonction dans le GameController
     */
    @FXML
    public void startGame() throws Exception {

        gameRunning.set(true);
        gameController.startGame(playerOneName, playerTwoName, true);
        infoLabel.setText("Partie commencée, bonne chance !");
    }

    @FXML
    public void importPlayerOne() {
        playerOneName = textFieldPlayerOne.getText();
        try {
            PlayerHandler.verficationJoueur(playerOneName.toLowerCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        infoLabel.setText(String.format("Joueur 1 (%s) importé avec succès.", playerOneName));

        playerOneImported.set(true);
    }

    @FXML
    public void importPlayerTwo() {
        playerTwoName = textFieldPlayerTwo.getText();
        try {
            PlayerHandler.verficationJoueur(playerTwoName.toLowerCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        infoLabel.setText(String.format("Joueur 2 (%s) importé avec succès.", playerTwoName));

        playerTwoImported.set(true);
    }

    /**
     * crée les bindings nécessaire au bon fonctionnement de l'application
     */
    public void createBindings(){

        gameRunning = new SimpleBooleanProperty(false);
        playerOneImported = new SimpleBooleanProperty(false);
        playerTwoImported = new SimpleBooleanProperty(false);

        BooleanBinding checkNamePlyOne = new BooleanBinding() {
            {
                this.bind(textFieldPlayerOne.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return textFieldPlayerOne.getText().length() > 2;
            }
        };

        BooleanBinding checkNamePlyTwo = new BooleanBinding() {
            {
                this.bind(textFieldPlayerTwo.textProperty());
            }
            @Override
            protected boolean computeValue() {
                return textFieldPlayerTwo.getText().length() > 2;
            }
        };

        importPlayerOneButton.disableProperty().bind(checkNamePlyOne.not());
        importPlayerTwoButton.disableProperty().bind(checkNamePlyTwo.not());

        BooleanBinding checkBothPlayersImported = new BooleanBinding() {
            {
                this.bind(playerOneImported, playerTwoImported);
            }

            @Override
            protected boolean computeValue() {
                return playerOneImported.get() && playerTwoImported.get() && !gameRunning.get();
            }
        };

        playButton.disableProperty().bind(checkBothPlayersImported.not());
    }


    /**
     * est appelée lorsque la partie se termine, modifie les fichiers des joueurs et permet au joueur de relancer la partie
     * @param winner -1 / 1
     * @throws IOException si erreur lors de la lecture des fichiers
     */
    public void gameEnded(int winner) throws IOException {
        gameRunning.set(false);

        if (winner == 1) {
            PlayerHandler.finPartie(playerOneName, playerTwoName);
            infoLabel.setText(String.format("Victoire de %s! Vous pouvez à présent relancer une partie.", playerOneName));
        } else {
            PlayerHandler.finPartie(playerTwoName, playerOneName);
            infoLabel.setText(String.format("Victoire de %s! Vous pouvez à présent relancer une partie.", playerTwoName));
        }

    }

    /**
     * importe le controlleur principal du jeu
     * @param gameController
     */
    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        System.out.println(gameController);
    }

    public GameController getGameController() {
        return gameController;
    }
}
