import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

  private Map<Ville, Set<Route>> trajets = new HashMap<>();
  private Map <String, Ville> nomsVilles = new HashMap<>();

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
        Ville v = new Ville(Integer.parseInt(mots[0]), mots[1], Double.parseDouble(mots[3]),
            Double.parseDouble(mots[4]));
      trajets.put(v,new HashSet<>());
      }


      // Fermeture du BufferedReader
      lecteur.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
