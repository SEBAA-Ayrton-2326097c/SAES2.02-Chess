package com.chessapp.chessapp;

import com.chessapp.chessapp.model.Plateau;
import com.chessapp.chessapp.model.Piece;
import com.chessapp.chessapp.model.Pion;
import javafx.application.Platform;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MouvementBlancTest {

    private Plateau plateau;

    @BeforeAll
    public static void initJfx(){
        Platform.startup(() -> {});
    }

    @BeforeEach
    public void setUp() {
        plateau = new Plateau();
        try {
            // Initialisation des pièces sur le plateau
            plateau.addPiece(0, 3, 'P', 1); // Pion en A4
            plateau.addPiece(1, 1, 'P', 1); // Pion en B2
            plateau.addPiece(2, 5, 'R', 1); // Tour en C6
            plateau.addPiece(2, 2, 'K', 1); // Roi en C3
            plateau.addPiece(3, 1, 'P', 1); // Pion en D2
            plateau.addPiece(3, 3, 'B', 1); // Fou en D4
            plateau.addPiece(4, 6, 'Q', 1); // Reine en E7
            plateau.addPiece(7, 5, 'N', 1); // Cavalier en H6
            plateau.addPiece(7, 3, 'P', 1); // Pion en H4

            // Initialisation de quelques pièces noires
            plateau.addPiece(0, 4, 'P', -1); // Pion en D5
            plateau.addPiece(1, 6, 'P', -1); // Pion en B7
            plateau.addPiece(3, 5, 'P', -1); // Pion en D6
            plateau.addPiece(6, 4, 'P', -1); // Pion en G5
            plateau.addPiece(6, 6, 'P', -1); // Pion en G7
            plateau.addPiece(7, 7, 'N', -1); // Cavalier en H8
            plateau.addPiece(4, 0, 'P', -1); // Pion en A5*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test des mouvements du pion blanc en D4 bloqué par un pion noir")
    public void test1() throws Exception {
        try {
            Piece piece = plateau.getPiece(0, 3);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            assertTrue(moves.isEmpty());
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du pion blanc en position initiale (B2)")
    public void test2() {
        try {
            Piece piece = plateau.getPiece(1, 1);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(1, 2), new Pair<>(1, 3)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements de la Tour blanche en C6")
    public void test3() {
        try {
            Piece piece = plateau.getPiece(2, 5);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(2, 4), new Pair<>(2, 3), new Pair<>(2, 6), new Pair<>(2, 7), // Verticalement
                    new Pair<>(1, 5), new Pair<>(0, 5), new Pair<>(3, 5) // Horizontalement
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du Roi blanc en C3")
    public void test4() {
        try {
            Piece piece = plateau.getPiece(2, 2);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(2, 1), new Pair<>(2, 3),
                    new Pair<>(1, 2), new Pair<>(3, 2),
                    new Pair<>(1, 3)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du Pion en position initiale bloque sur l'avance de 2 (D2)")
    public void test5() {
        try {
            Piece piece = plateau.getPiece(3, 1);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(3, 2)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du Fou blanc en D4")
    public void test6() {
        try {
            Piece piece = plateau.getPiece(3, 3);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(4, 4), new Pair<>(5, 5), new Pair<>(6, 6),
                    new Pair<>(2, 4), new Pair<>(1, 5), new Pair<>(0, 6),
                    new Pair<>(4, 2), new Pair<>(5, 1), new Pair<>(6, 0)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements de la Reine blanche en E7")
    public void test7() {
        try {
            Piece piece = plateau.getPiece(4, 6);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(4, 5), new Pair<>(4, 4), new Pair<>(4, 3), new Pair<>(4, 2), new Pair<>(4, 1), new Pair<>(4, 0),
                    new Pair<>(4, 7),
                    new Pair<>(3, 6), new Pair<>(2, 6), new Pair<>(1, 6),
                    new Pair<>(5, 6), new Pair<>(6, 6),
                    new Pair<>(3, 5),
                    new Pair<>(5, 7),
                    new Pair<>(3, 7),
                    new Pair<>(5, 5), new Pair<>(6, 4)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du Cavalier blanc en H6")
    public void test8() {
        try {
            Piece piece = plateau.getPiece(7, 5);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(5, 4), new Pair<>(5, 6), // Saute en G4 et G6
                    new Pair<>(6, 3), new Pair<>(6, 7) // Saute en F3 et F7
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }


    @Test
    @DisplayName("Test des mouvements du pion blanc en H4 en diagonale")
    public void test9() {
        try {
            Piece piece = plateau.getPiece(7, 3);
            List<Pair<Integer, Integer>> moves = piece.calculateMovements();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(7, 4), new Pair<>(6, 4)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }



}