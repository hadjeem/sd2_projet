import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Graph {

  //map of cities

  public Graph(File cities, File roads) {
    readFile(cities);
  }

  public void calculerItineraireMinimisantNombreRoutes(String depart, String arrivee) {

  }

  public void calculerItineraireMinimisantKm(String depart, String arrivee) {

  }

  //function for reading the file
  public void readFile(File file) {
    try {
      // Création d'un BufferedReader pour lire le fichier
      BufferedReader lecteur = new BufferedReader(new FileReader(file));

      // Lecture ligne par ligne
      String ligne;
      while ((ligne = lecteur.readLine()) != null) {
        String[] mots = ligne.split(",");

        // Parcourir et afficher chaque mot
        for (String mot : mots) {
          System.out.println(mot.trim()); // trim() pour enlever les espaces blancs autour du mot
        }
      }
      // Fermeture du BufferedReader
      lecteur.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
