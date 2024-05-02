package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/*
    Read file from scanner
    Lis tout simplement le document, parfait, juste a régler le probleme des deux cases l'une a cote de l'autre
     */
public class LecteurFichier {
    public static ArrayList<String> lireFichier() {
        ArrayList<String> valeurs = new ArrayList<>();
        try {
            // Chemin vers le fichier
            File position = new File("C:\\Users\\sukka\\Desktop\\COURS\\S2\\JAVA\\DEV_J\\IntelliJ_projets_IHM\\IHM\\SAE2.01\\saeBis\\scenarios\\scenario0.txt");

            // Création d'un scanner pour lire le fichier
            Scanner scanner = new Scanner(position);

            // Lecture du fichier ligne par ligne
            while (scanner.hasNextLine()) {
                // Lecture de la ligne courante
                String line = scanner.nextLine();

                // Séparation des valeurs en utilisant la virgule comme séparateur
                String[] values = line.split(" ");

                // Extraction des valeurs x et y
                String x = values[0];
                String y = values[1];

                String couleurTemple = values[2];
                String couleurCristal = values[3];
                valeurs.add(x);
                valeurs.add(y);
                valeurs.add(couleurTemple);
                valeurs.add(couleurCristal);




            }

            // Fermeture du scannerscr
            scanner.close();
        } catch (Exception e) {
            // Gestion des exceptions
            e.printStackTrace();
        }

        return valeurs;

    };
}

