public class Route {

  private Ville depart;
  private Ville arrivee;
  private double distance;

  public Route(Ville depart, Ville arrivee, double distance) {
    this.depart = depart;
    this.arrivee = arrivee;
    this.distance=distance;
  }

  public Ville getDepart() {
    return depart;
  }

  public Ville getArrivee() {
    return arrivee;
  }

  public double getDistance() {
    return distance;
  }

    public String toString() {
        return depart.getNom() + " -> " + arrivee.getNom();
    }
}



