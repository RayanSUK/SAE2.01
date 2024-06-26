package modele;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La classe LectureScenario permet de lire les scénarios et de créer la liste des temples et des cristaux
 */
public class LectureScenario implements ConstantesCanvas{

    /**
     * La méthode de lecture prend en paramètre un fichier contenant un scénario
     * et retourne une paire de donnée contenant les temples à réaligner ainsi que les cristaux
     * @param fichierScenario : le fichier lu
     * @return une paire de list de cristaux et de cristaux
     */
    public static Pair<List<Temple>, List<Cristal>> lecture(File fichierScenario){
        List <Temple> templesDuScenario = new ArrayList<>();
        List <Cristal> cristaux = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(fichierScenario);
            while (scanner.hasNext()){
                //Largeur_CANVAS = 31 HAUTEUR_CANVAS = 31
                // permet de traiter tous les scénarios proposés

                int posX = scanner.nextInt() + LARGEUR_CANVAS/(2*CARRE);


                int posY = scanner.nextInt() + HAUTEUR_CANVAS/(2*CARRE);

                int couleur = scanner.nextInt();
                int couleur_cristal = scanner.nextInt();
                Temple temple = new Temple(new Position(posX,posY),couleur);
                templesDuScenario.add(temple);
                Cristal cristal = new Cristal(new Position(posX, posY), couleur_cristal);
                cristaux.add(cristal);

            }
            scanner.close();

        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return new Pair<List<Temple>, List<Cristal>>(templesDuScenario, cristaux);
    }
}