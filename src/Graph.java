import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {

  private Map<Ville, Set<Route>> trajets = new HashMap<>();
  private Map<Integer,Ville> idsVilles = new HashMap<>();
  private Map <String, Ville> nomsVilles = new HashMap<>();
  private List<Ville> bfsListe =new LinkedList<>();

  //map of cities

  public Graph(File cities, File roads) {
    readFile(cities, roads);
  }

  public void calculerItineraireMinimisantNombreRoutes(String depart, String arrivee) {
    Ville villeDepart = nomsVilles.get(depart);
    Ville villeArrive = nomsVilles.get(arrivee);
    Set<Route> routesDepart = new HashSet<>();
    routesDepart = trajets.get(villeDepart);
    for (Route route : routesDepart) {

    }

  }

  public void calculerItineraireMinimisantKm(String depart, String arrivee) {

  }

  //function for reading the file
  public void readFile(File file,File file2) {
    try {
      // Création d'un BufferedReader pour lire le fichier
      BufferedReader lecteur = new BufferedReader(new FileReader(file));
      BufferedReader lecteur2 = new BufferedReader(new FileReader(file2));

      // Lecture ligne par ligne
      String ligne;
      while ((ligne = lecteur.readLine()) != null) {
        String[] mots = ligne.split(",");
        Ville v = new Ville(Integer.parseInt(mots[0]), mots[1], Double.parseDouble(mots[2]),
            Double.parseDouble(mots[3]));
        trajets.put(v,new HashSet<>());
        idsVilles.put(v.getId(),v);
        nomsVilles.put(v.getNom(),v);
      }

      String ligne2;
      while((ligne2 = lecteur2.readLine())!=null) {
        String[] mots = ligne.split(",");
        Route r = new Route(idsVilles.get(mots[0]),idsVilles.get(mots[1]));
        Route r2 = new Route(idsVilles.get(mots[1]),idsVilles.get(mots[0]));
      }

      // Fermeture du BufferedReader
      lecteur.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
