package src.metier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Joueur 
{
    private String          nom;
    private int             score, nbPion;
    private Color           couleur;
    private boolean         premierTour;

    private ArrayList<Carte>                    main;
    private ArrayList<Arete>                    tabArete            = new ArrayList<Arete>                  ();
    private ArrayList<CarteObjectif>            tabCarteObjectif    = new ArrayList<CarteObjectif>          ();
    private HashMap<String, ArrayList<Carte>>   cartes              = new HashMap<String, ArrayList<Carte>> ();


    public Joueur(String nom, Color couleur) 
    {
        this.nom                = nom;
        this.couleur            = couleur;
        this.score              = 0;
        this.main               = new ArrayList<Carte>                  ();
        this.tabArete           = new ArrayList<Arete>                  ();
        this.cartes             = new HashMap<String, ArrayList<Carte>> ();
        this.tabCarteObjectif   = new ArrayList<CarteObjectif>          ();
        this.nbPion             = 0;
        this.premierTour        = true;
    }

    public String getNom() 
    {
        return nom;
    }

    public Color getCouleur()
    {
        return couleur;
    }

    public int getScore() 
    {
        return score;
    }

    public ArrayList<Arete> getTabArete() 
    {
        return tabArete;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }

    public void addArete(Arete arete) 
    {
        this.tabArete.add(arete);
    }

    public void addScore(int score) 
    {
        this.score += score;
    }

    public void removeCarte(Carte c)
    {
        this.main.remove(c);
        this.cartes.get(c.getNomCarte()).remove(c);
    }

    public void removeScore(int score) 
    {
        this.score -= score;
    }

    public ArrayList<Carte> getMain() 
    {
        return main;
    }

    // get couleur carte
    public int nbCouleur(String couleur) 
    {
        //obtenir le nombre de carte de la couleur dans la hashmap si elle existe
        if(this.cartes.containsKey(couleur)) 
        {
            return this.cartes.get(couleur).size();
        } else 
        {
            return 0;
        }
        
    }

    public void removeMain(Color carte) 
    {
        this.main.remove(carte);
    }

    public void addMain(Carte carte) 
    {
        //ajouter la carte dans la hashmap
        if(this.cartes.containsKey(carte.getNomCarte())) 
        {
            this.cartes.get(carte.getNomCarte()).add(carte);
            this.main.add(carte);
        } else 
        {
            ArrayList<Carte> list = new ArrayList<Carte>();
            list.add(carte);
            this.cartes.put(carte.getNomCarte(), list);
            this.main.add(carte);
        }
        
    }


    public HashMap<String, ArrayList<Carte>> getCartes() 
    {
        return cartes;
    }

    public void removeMain(int index) 
    {
        this.main.remove(index);
    }

    public String toString() 
    {
        //afficher toute les cartes du joueur en parcourant la hashmap
        String str = "";
        for(String key : this.cartes.keySet()) 
        {
            str += key + " : " + this.cartes.get(key)+"\n";
        }
        return str;
        
    }

    public List<Arete> getAretes() 
    {
        return this.tabArete;
    }

    public void addCarteObjectif(CarteObjectif carteObjectif) 
    {
        this.tabCarteObjectif.add(carteObjectif);
    }

    public ArrayList<CarteObjectif> getTabCarteObjectif() 
    {
        return tabCarteObjectif;
    }

    public void setNbPion(int nb)
    {
        this.nbPion = nb;
    }

    public void decrementeNbPion(int nb)
    {
        this.nbPion -= nb;
    }

    public int getNbPion()
    {
        return this.nbPion;
    }

    public boolean getPremierTour()
    {
        return this.premierTour;
    }

    public void setPremierTour(boolean etat)
    {
        this.premierTour = etat;
    }

}
