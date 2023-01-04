package src.metier;

public class Arete 
{
    private String noeudDepart;
    private String noeudArrivee;
    private int xDepart;
    private int yDepart;
    private int xArrivee;
    private int yArrivee;
    private int nbWagon;
    private String couleur;

    public Arete(String noeudDepart, String noeudArrivee, int xDepart, int yDepart, int xArrivee, int yArrivee, int nbWagon, String couleur) 
    {
        this.noeudDepart = noeudDepart;
        this.noeudArrivee = noeudArrivee;
        this.xDepart = xDepart;
        this.yDepart = yDepart;
        this.xArrivee = xArrivee;
        this.yArrivee = yArrivee;
        this.nbWagon = nbWagon;
        this.couleur = couleur;
    }

    public String getNoeudDepart() {
        return noeudDepart;
    }

    public String getNoeudArrivee() {
        return noeudArrivee;
    }

    public int getXDepart() {
        return xDepart;
    }

    public int getYDepart() {
        return yDepart;
    }

    public int getXArrivee() {
        return xArrivee;
    }

    public int getYArrivee() {
        return yArrivee;
    }

    public int getNbWagon() {
        return nbWagon;
    }

    public String getCouleur() {
        return couleur;
    }
}
