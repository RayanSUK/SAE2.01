package modele;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class LectureScenario implements ConstantesCanvas{
    /**
     * La méthode de lecture prend en paramètre un fichier contenant un scénario
     * et retourne une collection de donnée contenant les temples à réaligner
     * @param fichierScenario : le fichier lu
     * @return une collection de temples (de la classe temple)
     */

    public static Pair<List<Temple>, List<Cristal>> lecture(File fichierScenario){
        List <Temple> templesDuScenario = new ArrayList<>();
        List <Cristal> cristaux = new ArrayList<>();
        /* à vous de remplacer la liste par un type de collection plus adapté */
        try {
            Scanner scanner = new Scanner(fichierScenario);
            while (scanner.hasNext()){
                //Largeur_CANVAS = 31 HAUTEUR_CANVAS = 31
                // permet de traiter tout les scénarios poposés

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
        return new Pair<List<Temple>, List<Cristal>>((List<Temple>) templesDuScenario, cristaux);
    }
}