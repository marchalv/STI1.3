public class Ville {
    private String nom;
    private int x;
    private int y;

    public Ville(String nom, int x, int y) {
        this.nom = nom;
        this.x = x;
        this.y = y;
    }

    public Ville() {

    }

    public double getDistance(Ville v) {
        return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
    }

    public String getNom() {
        return nom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
