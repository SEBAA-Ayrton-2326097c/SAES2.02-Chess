package com.chessapp.chessapp.model;

import java.io.*;
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
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line == null) {
                throw new IOException("Le fichier est vide");
            }

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 4) {
                    throw new IOException("Ligne de format incorrect: " + line);
                }

                int oldx = Integer.parseInt(values[0]);
                int oldy = Integer.parseInt(values[1]);
                int newx = Integer.parseInt(values[2]);
                int newy = Integer.parseInt(values[3]);

                System.out.println("oldx: " + oldx + ", oldy: " + oldy + ", newx: " + newx + ", newy: " + newy);


            }
        } catch ( IOException e) {
            e.printStackTrace();
            throw new IOException("Problème durant la lecture du fichier", e);
        }
    }


    public static void main(String[] args) {

        String pseudo1 = "p1";
        String pseudo2 = "p2";
        String fileName = createName(pseudo1, pseudo2);

        Tuple move1 = new Tuple(1, 2);
        Tuple move2 = new Tuple(2, 3);

        Tuple move3 = new Tuple(3, 4);
        Tuple move4 = new Tuple(4, 5);


        try {
            HistoriqueHandler.ecritureHistorique(fileName, move1, move2);
            HistoriqueHandler.ecritureHistorique(fileName, move3, move4);
            HistoriqueHandler.lectureHistorique(fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}