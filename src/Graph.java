import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph {

    private Map<Ville, Set<Route>> trajets = new HashMap<>();
    private Map<Integer, Ville> idsVilles = new HashMap<>();
    private Map<String, Ville> nomsVilles = new HashMap<>();
    private LinkedList<Ville> bfsListe = new LinkedList<>();

    //map of cities

    public Graph(File cities, File roads) {
        readFile(cities, roads);
    }

    public void calculerItineraireMinimisantNombreRoutes(String depart, String arrivee) {

        //get the city of departure and arrival
        Ville villeDepart = nomsVilles.get(depart);
        Ville villeArrive = nomsVilles.get(arrivee);

        HashSet<Ville> villesVisite = new HashSet<>();
        HashMap<Ville, Route> chemin = new HashMap<>();
        HashMap<Ville, Double> distances = new HashMap<>();

        bfsListe.add(villeDepart);
        villesVisite.add(villeDepart);
        distances.put(villeDepart, 0.0);

        while (chemin.get(villeArrive) == null && !bfsListe.isEmpty()) {
            Ville v = bfsListe.pop();

            for (Route r : trajets.get(v)) {
                if (!villesVisite.contains(r.getArrivee())) {
                    villesVisite.add(r.getArrivee());
                    bfsListe.add(r.getArrivee());
                    chemin.put(r.getArrivee(), r);

                    double newDistance = distances.get(v) + r.getDistance();
                    distances.put(r.getArrivee(), newDistance);

                    if (r.getArrivee().equals(villeArrive)) {
                        break;
                    }
                }
            }
        }

        if (chemin.get(villeArrive) == null) {
            throw new RuntimeException("No route found from " + depart + " to " + arrivee);
        }

        int nbRoutes = 0;
        double distance = distances.get(villeArrive) != null ? distances.get(villeArrive) : 0;

        LinkedList<Route> routesList = new LinkedList<>();

        do {
            nbRoutes++;
            routesList.addFirst(chemin.get(villeArrive));
            villeArrive = chemin.get(villeArrive).getDepart();
        } while (chemin.get(villeArrive).getDepart() != villeDepart);

        nbRoutes++;
        routesList.addFirst(chemin.get(villeArrive));

        System.out.println("Itinéraire de " + depart + " à " + arrivee + ":" + nbRoutes + " routes" + " et " + distance + " km");
        while (!routesList.isEmpty()) {
            Route r = routesList.removeFirst();
            System.out.println(r.getDepart() + " -> " + r.getArrivee() + " (" + Util.distance(r.getArrivee(),r.getDepart()) + " km)");
        }

    }

    public void calculerItineraireMinimisantKm(String depart, String arrivee) {

        Ville villeDepart = nomsVilles.get(depart);
        Ville villeArrive = nomsVilles.get(arrivee);

        HashMap<Ville, Route> chemin = new HashMap<>();
        HashMap<Ville, Double> distances = new HashMap<>();
        TreeMap<Double, Ville> treeMap = new TreeMap<>();
        HashSet<Ville> villesVisite = new HashSet<>();

        for (Ville v : nomsVilles.values()){
            distances.put(v, Double.MAX_VALUE);
        }

        distances.put(villeDepart, 0.0);
        treeMap.put(0.0, villeDepart);

        while (!treeMap.isEmpty()) {
            Map.Entry<Double, Ville> entry = treeMap.pollFirstEntry();
            Ville v = entry.getValue();
            villesVisite.add(v);

            if (v.equals(villeArrive)) {
                break;
            }

            for (Route r : trajets.get(v)) {
                Ville ville = r.getArrivee();
                if (!villesVisite.contains(ville)) {
                    double newDistance = distances.get(v) + r.getDistance();
                    if (newDistance < distances.get(r.getArrivee())) {
                        distances.put(r.getArrivee(), newDistance);
                        chemin.put(r.getArrivee(), r);
                        treeMap.put(newDistance, r.getArrivee());
                    }
                }
            }
        }

        if (chemin.get(villeArrive) == null) {
            throw new RuntimeException("No route found from " + depart + " to " + arrivee);
        }

        int nbRoutes = 0;
        double distance = 0;

        Deque<Route> itineraire = new LinkedList<>();
        Ville v = villeArrive;
        while (v != villeDepart) {
            Route r = chemin.get(v);
            itineraire.addFirst(r);
            distance += r.getDistance();
            v = r.getDepart();
            nbRoutes++;
        }

        System.out.println("Itinéraire de " + depart + " à " + arrivee + ":" + nbRoutes + " routes" + " et " + distance + " km");
        while(!itineraire.isEmpty()){
            Route r = itineraire.poll();
            System.out.println(r.getDepart() + " -> " + r.getArrivee() + " (" + r.getDistance() + " km)");
        }

    }

    //function for reading the file
    public void readFile(File file, File file2) {
        try {
            BufferedReader lecteur = new BufferedReader(new FileReader(file));
            BufferedReader lecteur2 = new BufferedReader(new FileReader(file2));

            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String[] mots = ligne.split(",");
                Ville v = new Ville(Integer.parseInt(mots[0]),
                        mots[1],
                        Double.parseDouble(mots[2]),
                        Double.parseDouble(mots[3]));
                trajets.put(v, new HashSet<>());
                idsVilles.put(v.getId(), v);
                nomsVilles.put(v.getNom(), v);
            }

            ArrayList<Route> routes = new ArrayList<>();
            String ligne2;

            while ((ligne2 = lecteur2.readLine()) != null) {
                String[] mots = ligne2.split(",");
                Integer d1 = Integer.parseInt(mots[0]);
                Integer d2 = Integer.parseInt(mots[1]);
                Route r = new Route(idsVilles.get(d2), idsVilles.get(d1), Util.distance(idsVilles.get(d2), idsVilles.get(d1)));
                Route r2 = new Route(idsVilles.get(d1), idsVilles.get(d2), Util.distance(idsVilles.get(d1), idsVilles.get(d2)));
                routes.add(r);
                routes.add(r2);

            }

            for (Route r : routes) {
                trajets.get(r.getDepart()).add(r);
            }

            lecteur.close();
            lecteur2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
