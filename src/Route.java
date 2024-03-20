public class Route {

  private Ville depart;
  private Ville arrivee;

  public Route(Ville depart, Ville arrivee) {
    this.depart = depart;
    this.arrivee = arrivee;
  }

  public Ville getDepart() {
    return depart;
  }

  public Ville getArrivee() {
    return arrivee;
  }

    public String toString() {
        return depart.getNom() + " -> " + arrivee.getNom();
    }
}



