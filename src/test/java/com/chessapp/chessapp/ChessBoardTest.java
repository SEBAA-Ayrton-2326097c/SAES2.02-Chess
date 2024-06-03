package com.chessapp.chessapp;

import com.chessapp.chessapp.model.Plateau;
import com.chessapp.chessapp.model.Piece;
import javafx.util.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChessBoardTest {
    private Plateau plateau;

    @BeforeEach
    public void setUp() {
        plateau = new Plateau();
        try {
            // Initialisation des pièces sur le plateau
            plateau.addPawn(0, 3, 'P', 1); // Pion en D4
            plateau.addPawn(1, 1, 'P', 1); // Pion en B2
            plateau.addPawn(2, 5, 'R', 1); // Tour en C6
            plateau.addPawn(2, 2, 'K', 1); // Roi en C3
            plateau.addPawn(3, 1, 'P', 1); // Pion en D2
            plateau.addPawn(3, 3, 'N', 1); // Cavalier en D4
            plateau.addPawn(4, 6, 'Q', 1); // Reine en E7
            plateau.addPawn(7, 5, 'N', 1); // Cavalier en H6
            plateau.addPawn(7, 3, 'P', 1); // Pion en H4

            // Initialisation de quelques pièces noires
            plateau.addPawn(0, 4, 'P', -1); // Pion en D5
            plateau.addPawn(1, 6, 'P', -1); // Pion en B7
            plateau.addPawn(3, 5, 'P', -1); // Pion en D6
            plateau.addPawn(6, 4, 'P', -1); // Pion en G5
            plateau.addPawn(6, 6, 'P', -1); // Pion en G7
            plateau.addPawn(6, 7, 'P', -1); // Pion en G8

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Test des mouvements du pion blanc en D4 bloqué par un pion noir")
    public void testWhitePawnBlocked() {
        try {
            Piece piece = plateau.getPiece(0, 3);
            List<Pair<Integer, Integer>> moves = piece.calculateMoves();
            assertTrue(moves.isEmpty());
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du pion blanc en B2")
    public void testWhitePawnInitialPosition() {
        try {
            Piece piece = plateau.getPiece(1, 1);
            List<Pair<Integer, Integer>> moves = piece.calculateMoves();
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
    public void testWhiteRookMoves() {
        try {
            Piece piece = plateau.getPiece(2, 5);
            List<Pair<Integer, Integer>> moves = piece.calculateMoves();
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
    public void testWhiteKingMoves() {
        try {
            Piece piece = plateau.getPiece(2, 2);
            List<Pair<Integer, Integer>> moves = piece.calculateMoves();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(2, 1), new Pair<>(2, 3),
                    new Pair<>(1, 2), new Pair<>(3, 2),
                    new Pair<>(1, 1), new Pair<>(1, 3), new Pair<>(3, 1), new Pair<>(3, 3)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    @Test
    @DisplayName("Test des mouvements du Cavalier blanc en D4")
    public void testWhiteKnightMovesD4() {
        try {
            Piece piece = plateau.getPiece(3, 3);
            List<Pair<Integer, Integer>> moves = piece.calculateMoves();
            List<Pair<Integer, Integer>> expectedMoves = Arrays.asList(
                    new Pair<>(2, 1), new Pair<>(2, 5), new Pair<>(4, 1), new Pair<>(4, 5),
                    new Pair<>(1, 2), new Pair<>(1, 4), new Pair<>(5, 2), new Pair<>(5, 4)
            );
            assertTrue(moves.containsAll(expectedMoves) && expectedMoves.containsAll(moves));
        } catch (Exception e) {
            fail("Exception during test execution: " + e.getMessage());
        }
    }

    // Ajoutez d'autres tests pour la Reine, autres Cavaliers, etc., selon le modèle ci-dessus
}
