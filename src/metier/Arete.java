package src.metier;

import java.awt.Color;
import java.util.function.BooleanSupplier;

public class Arete {
    // Attributs
    private int n1y;
    private int n1x;
    private int n2y;
    private int n2x;

    private Noeud noeudDepart;

    private Noeud noeudarrive;

    private boolean sensUnique;

    private Color couleur;

    private int nbVoiture;

    private Joueur joueur;

    public Arete(Noeud noeudDepart, Noeud noeudarrive, int nbVoiture, Color couleur, Boolean sensUnique ) 
    {
        this.noeudDepart = noeudDepart;
        this.noeudarrive = noeudarrive;
        this.nbVoiture = nbVoiture;
        this.sensUnique = sensUnique;
        this.couleur = couleur;
        this.joueur = null;

    }

    public int getNbVoiture() 
    {
        return nbVoiture;
    }

    public boolean getSensUnique() 
    {
        return sensUnique;
    }

    public void setNbVoiture(int nbVoiture) 
    {
        this.nbVoiture = nbVoiture;
    }

    public Noeud getNoeudDepart() 
    {
        return noeudDepart;
    }

    public Noeud getNoeudArrive() 
    {
        return noeudarrive;
    }

    public void setNoeudDepart(Noeud noeudDepart) 
    {
        this.noeudDepart = noeudDepart;
    }

    public void setNoeudarrive(Noeud noeudarrive) 
    {
        this.noeudarrive = noeudarrive;
    }

    public void setN1x(int n1x) 
    {
        this.n1x = n1x;
    }

    public void setN1y(int n1y) 
    {
        this.n1y = n1y;
    }

    public void setN2x(int n2x) 
    {
        this.n2x = n2x;
    }

    public void setN2y(int n2y) 
    {
        this.n2y = n2y;
    }

    public Color getCouleur() 
    {
        return couleur;
    }
    
    public void setJoueur(Joueur joueurPossedantArete)
    {
        this.joueur = joueurPossedantArete;
    }

    public Joueur getJoueur()
    {
        return joueur;
    }



    public String toString() 
    {
        return "Arete [n1x=" + n1x + ", n1y=" + n1y + ", n2x=" + n2x + ", n2y=" + n2y + ", nbVoiture=" + nbVoiture
                + "]";
    }

}
