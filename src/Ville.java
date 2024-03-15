public class Ville {
  private int id;
  private String nom;
  private double longitude;
  private double latitude;


  public Ville(int id, String nom, double longitude, double latitude) {
    this.id = id;
    this.nom = nom;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public int getId() {
    return id;
  }

  public String getNom() {
    return nom;
  }

  public double getLongitude() {
    return longitude;
  }

  public double getLatitude() {
    return latitude;
  }
}
