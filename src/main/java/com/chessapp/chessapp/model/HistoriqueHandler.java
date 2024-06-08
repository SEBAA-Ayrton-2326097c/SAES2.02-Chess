package com.chessapp.chessapp.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoriqueHandler {
    public static void ecritureHistorique(String fileName, Tuple sourceCoords, Tuple destCoords) throws IOException {
        String directoryPath = "Data/Historique";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + "/" + fileName;
        File file = new File(filePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (file.createNewFile()) { // si le fichier vient de se créer
                writer.write("oldx,oldy,newx,newy\n");
            }
            writer.write(sourceCoords.getFirst() + "," + sourceCoords.getSecond() + "," + destCoords.getFirst() + "," + destCoords.getSecond() + "\n"); // source X, Y, destination X, Y
        } catch (IOException e) {
            throw new IOException("Problème durant l'écriture du fichier");
        }
    }

    public static String createName(String pseudo1, String pseudo2) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm");
        String formattedDate = now.format(formatter);

        return pseudo1 + "-" + pseudo2 + "-" + formattedDate + ".csv";
    }

    public static void lectureHistorique(String file) throws IOException {
        //initialisation des pions sauf si deja fais

    }


    public static void main(String[] args) {

        String pseudo1 = "Player1";
        String pseudo2 = "Player2";
        String fileName = createName(pseudo1, pseudo2);

        Tuple move1 = new Tuple(1, 2);
        Tuple move2 = new Tuple(2, 3);

        Tuple move3 = new Tuple(3, 4);
        Tuple move4 = new Tuple(4, 5);


        try {
            HistoriqueHandler.ecritureHistorique(fileName, move1, move2);
            HistoriqueHandler.ecritureHistorique(fileName, move3, move4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
