package com.chessapp.chessapp.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PlayerController {

    /**
     * Méthode pour vérifier l'existence d'un joueur et créer le fichier si nécessaire.
     * Si le fichier (csv) est créé, les données à l'intérieur sont initialisées à 0.
     * @param pseudo Nom du joueur
     * @throws IOException En cas d'erreur lors de la création ou de l'écriture dans le fichier
     */
    public void verficationJoueur(String pseudo) throws IOException {
        String directoryPath = "Data";
        String filePath = directoryPath + "/" + pseudo + ".csv";

        // Vérifier si le répertoire existe, sinon le créer
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Vérifier si le fichier existe
        if (Files.exists(Paths.get(filePath))) {
            return;
        } else {
            // Créer le fichier et l'initialiser si il n'existe pas
            File file = new File(filePath);
            if (file.createNewFile()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("\"pseudo\",nb_parties,nb_victoires,nb_egalite,nb_defaite\n");
                    writer.write(pseudo + ",0,0,0,0\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new IOException("Erreur lors de l'écriture dans le fichier " + pseudo + ".csv", e);
                }
            } else {
                throw new IOException("Erreur lors de la création du fichier " + pseudo + ".csv");
            }
        }
    }

    /**
     * Méthode pour démarrer une partie entre deux joueurs qui va ajouter 1 aux nb_parties et nb_egalite pour les 2 joueurs
     * @param pseudo1 Nom du premier joueur
     * @param pseudo2 Nom du deuxième joueur
     * @throws IOException En cas d'erreur lors de la lecture ou de l'écriture dans le fichier
     */
    public void debutPartie(String pseudo1, String pseudo2) throws IOException {
        modifierStats(pseudo1, 1, 0, 1, 0);
        modifierStats(pseudo2, 1, 0, 1, 0);
    }

    /**
     * Méthode pour finir une partie entre deux joueurs qui va enlever 1 a nb_egalite pour les 2 joueurs
     * et ajouter 1 a nb_vicoire au  gagnant et ajouter 1 a nb_defaite au perdant
     * @param gagnant Nom du joueur gagnant
     * @param perdant Nom du joueur perdant
     * @throws IOException En cas d'erreur lors de la lecture ou de l'écriture dans le fichier
     */
    public void finPartie(String gagnant, String perdant) throws IOException {
        modifierStats(gagnant, 0, 1, -1, 0);
        modifierStats(perdant, 0, 0, -1, 1);
    }

    /**
     * Méthode pour modifier les statistiques d'un joueur.
     * @param pseudo Nom du joueur
     * @param nbParties Nombre de parties à ajouter
     * @param nbVictoires Nombre de victoires à ajouter
     * @param nbEgalite Nombre d'égalités à ajouter
     * @param nbDefaites Nombre de défaites à ajouter
     * @throws IOException En cas d'erreur lors de la lecture ou de l'écriture dans le fichier
     */
    private void modifierStats(String pseudo, int nbParties, int nbVictoires, int nbEgalite, int nbDefaites) throws IOException {
        String filePath = "Data/" + pseudo + ".csv";
        String[] stats = lireStats(filePath);
        if (stats != null) {
            stats[1] = Integer.toString(Integer.parseInt(stats[1]) + nbParties);
            stats[2] = Integer.toString(Integer.parseInt(stats[2]) + nbVictoires);
            stats[3] = Integer.toString(Integer.parseInt(stats[3]) + nbEgalite);
            stats[4] = Integer.toString(Integer.parseInt(stats[4]) + nbDefaites);
            ecrireStats(filePath, stats);
        }
    }

    // Méthode pour lire les statistiques d'un fichier CSV
    private String[] lireStats(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // Lire la ligne d'en-tête
            String line = reader.readLine();
            if (line != null) {
                return line.split(",");
            }
        }
        return null;
    }

    // Méthode pour écrire les statistiques dans un fichier CSV
    private void ecrireStats(String filePath, String[] stats) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("\"pseudo\",nb_parties,nb_victoires,nb_egalite,nb_defaite\n");
            writer.write(String.join(",", stats) + "\n");
        }
    }

    public static void main(String[] args) {
        PlayerController pc = new PlayerController();
        try {
            pc.verficationJoueur("joueur1");
            pc.verficationJoueur("joueur2");

            pc.debutPartie("joueur1", "joueur2");
            pc.finPartie("joueur1", "joueur2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
