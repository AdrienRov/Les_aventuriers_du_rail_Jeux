package src;

import java.io.File;
import java.text.DateFormat.Field;

import src.ihm.FrameAccueil;
import src.ihm.Gui;
import src.metier.Arete;
import src.metier.Carte;
import src.metier.CarteObjectif;
import src.metier.Joueur;
import src.metier.Noeud;

import java.io.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import src.metier.Arete;
import src.metier.Noeud;

import java.io.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import src.metier.Arete;
import src.metier.Noeud;

import java.io.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.Color;

public class Controleur 
{
    private FrameAccueil frameAcceuil;
    private Gui gui;
    private String[] couleurCarte = {"red","blue","green","yellow","black","purple","orange","white","grey"};
    private Color[] tabColors = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.BLACK,Color.MAGENTA,Color.ORANGE,Color.WHITE,Color.GRAY};
    private List<Noeud> allNoeuds;
    private List<Arete> allAretes;
    private List<CarteObjectif> allCartesObjectifs;
    private List<Integer> allParametres;
    private List<String> allImages;
    private List<Carte> pioche;
    private List<Carte> allCartes;
    // Pour lire le fichier XML
    private Document document;
    private static org.jdom2.Element racine;
    private int nbJoueurMin;
    private int nbJoueurMax;
    private int nbPionMax;
    private int nbWagonFin;
    private int nbPoint1;
    private int nbPoint2;
    private int nbPoint3;
    private int nbPoint4;
    private int nbPoint5;
    private int nbPoint6;
    private int nbCarteJoueur;
    private int nbJoueurDoublesVoies;
    private int nbWagonCouleur;
    private int nbJoker;

    private int tour = 1;

    private int nbJoueur;

    private int cpt ; // compteur pour le nombre de carte sur la table

    private ArrayList<Carte> defausse;
    private ArrayList<Carte> carteTable ; //carte sur la table
    private Arete choixArete;
    private Joueur joueur1;

    private ArrayList<CarteObjectif> allCarteObjectifRandom;

    public Controleur()  
    {
        this.frameAcceuil = new FrameAccueil(this);
        this.joueur1 = new Joueur("Joueur 1");
        this.allNoeuds = new ArrayList<Noeud>();
        this.allAretes = new ArrayList<Arete>();
        this.allParametres = new ArrayList<Integer>();
        this.allImages = new ArrayList<String>();
        this.allCartes = new ArrayList<Carte>();
        this.allCartesObjectifs = new ArrayList<CarteObjectif>();
        this.allCarteObjectifRandom = new ArrayList<CarteObjectif>();
        this.document  = new org.jdom2.Document();
        this.racine    = new org.jdom2.Element("racine");
        this.lireFichierXML(new File("src/FichierSortie.xml"), this);
        initPioche();
        initPiocheObjectif();
        //this.AfficherDonnees();
        this.setPionMax();
    }

    public void jouerManche(int numAction)
    {
        if(numAction == 0)
        {

        }

        if(numAction == 1)
        {
            this.gui.piocherCarteObjectif();
        }

        if(numAction == 2)
        {
            
        }
    }

    public void initPioche()
    {
        this.pioche     = new ArrayList<Carte>();
        this.defausse   = new ArrayList<Carte>();
        this.carteTable = new ArrayList<Carte>();

        
        for(int i =0 ; i<this.nbWagonCouleur ;i++)
        {
                this.pioche.add(this.allCartes.get(0));
                this.pioche.add(this.allCartes.get(1));
                this.pioche.add(this.allCartes.get(2));
                this.pioche.add(this.allCartes.get(3));
                this.pioche.add(this.allCartes.get(4));
                this.pioche.add(this.allCartes.get(5));
                this.pioche.add(this.allCartes.get(6));
                this.pioche.add(this.allCartes.get(7));
        }

        for(int i =0 ; i<this.nbJoker ;i++){ this.pioche.add(this.allCartes.get(8));} // locomotive
        
        
        Collections.shuffle(this.pioche);
        //distribuer 4 cartes de la pioche au joueur et les enlever de la pioche et les mettre dans la main du joueur
        for(int i =0 ; i<this.nbCarteJoueur ;i++)
        {
            this.joueur1.addMain(this.pioche.get(i));
            this.pioche.remove(i);
        }

        // mettres  5 cartes sur la table et les enlever de la pioche
        
        for(int i =0 ; i<5 ;i++)
        {
            System.out.print(i);
            this.carteTable.add(this.pioche.get(i));
            this.pioche.remove(i);
        }     

        
        
        //refreshTabTrajets();
    }

    public List<Carte> getPioche()
    {
        return this.pioche;
    }
        
    // action du joueur : piocher une carte 

    public Carte pioche()
    {
        Carte carte = null;

        if(!this.pioche.isEmpty())
        {
            carte = this.pioche.get(0);
            this.pioche.remove(0);
            return carte;
        }

        return carte;
    }

    public void piocherCarte(Joueur joueur)
    {
        if(!this.pioche.isEmpty())
        {
            joueur.addMain(this.pioche());
            this.gui.refreshMain();
            return;
        }
        this.gui.notification("La pioche est vide !");
    }

    // action du joueur :prendre une carte de la table
    public void piocheCarteTable(int i, Joueur joueur)
    {
        
        // verifie si la pioche est vide 
        System.out.println("piocheCarteTable" + this.carteTable.get(i-1));
        if(this.carteTable.get(i-1) == null)
        {
            return;
        }
        joueur.addMain(this.carteTable.get(i-1));
        this.carteTable.set(i-1, this.pioche());
        this.gui.refreshMain();    
    }

    public void verifTourDejeux()
    {    
        System.out.println("fin de tour");
    }

    private void calculScore()
    {
        int somme        = 0;
        int soustraction = 0;  

        int[] tabScore = {nbPoint1,nbPoint2,nbPoint3,nbPoint4,nbPoint5,nbPoint6};

        for(Arete arete : this.joueur1.getTabArete())
        {
            somme += tabScore[arete.getNbVoiture()-1];
        }

        //parcourir les cartes objectif du joueur , si il possède la ville de départ et arriver de la carte objectif on ajoute des points
        for(CarteObjectif carte : this.joueur1.getTabCarteObjectif())
        {
            Set<Noeud> noeudsVisites = new HashSet<>();
            boolean possedeRoute = possedeRoute(carte.getNoeud1(), carte.getNoeud2(), "nomJoueur", this.joueur1.getTabArete(), noeudsVisites);
            

            if(possedeRoute)
            {
                somme += carte.getScore();
                System.out.println("il possède = "+possedeRoute + " "+carte.getNoeud1().getNom()+" "+carte.getNoeud2().getNom());
            }
            else
            {
                soustraction += carte.getScore();
                System.out.println("il possède pas = "+possedeRoute + " "+carte.getNoeud1().getNom()+" "+carte.getNoeud2().getNom());
            }
        }
        

        System.out.println("Score du Joueurs 1 = "+(somme-soustraction));
        
    }
    public static boolean possedeRoute(Noeud depart, Noeud arrive, String nomJoueur, List<Arete> aretes, Set<Noeud> noeudsVisites) {
        // Si le noeud de départ est le même que le noeud d'arrivée, alors le joueur possède une route
        if (depart == arrive) {
          return true;
        }
      
        // Ajout du noeud de départ aux noeuds visités
        noeudsVisites.add(depart);
      
        // Parcours de la liste d'arêtes
        for (Arete arete : aretes) {
          // Vérification si l'arête est possédée par le joueur
          if (arete.getJoueur().getNom().equals(nomJoueur)) {
            // Vérification si l'un des noeuds de l'arête est le noeud de départ
            if (arete.getNoeudDepart() == depart || arete.getNoeudArrive() == depart) {
              // Récupération du noeud qui n'est pas le noeud de départ
              Noeud noeudSuivant = (arete.getNoeudDepart() == depart) ? arete.getNoeudArrive() : arete.getNoeudDepart();
              // Vérification si le noeud n'a pas déjà été visité
              if (!noeudsVisites.contains(noeudSuivant)) {
                // Appel récursif sur le noeud qui n'est pas le noeud de départ
                if (possedeRoute(noeudSuivant, arrive, nomJoueur, aretes, noeudsVisites)) {
                  return true;
                }
              }
            }
          }
        }
      
        // Si aucune route n'a été trouvée, alors le joueur ne possède pas de route
        return false;
      }
    public boolean recTrajet(Noeud depart, Noeud arrivee, Noeud actuel)
    {
        
        return false;
    }

    public void finDePartie()
    {
        System.out.println("Fin de la partie");

        if(this.joueur1.getMain().size() < this.nbWagonFin)
        {
            //this.gui.notification("C'est la dernière manche");
            return;
        }
        calculScore();
    }

    // methode pour remelanger la defausse et la mettre dans la pioche si la pioche est vide
    public void remelanger()
    {
            this.pioche.addAll(this.defausse);
            this.defausse.clear();
            Collections.shuffle(this.pioche);
    }

    public void poserCarteSurTable(int i)
    {
        this.carteTable.set(i-1, this.pioche.get(0));
        this.pioche.remove(0);
    }

    // Afficher les données du fichier XML
    public void AfficherDonnees()
    {
        System.out.println("\n----------------- Les Noeuds -----------------\n");
        for(Noeud n : this.allNoeuds)
        {
            System.out.println(n.getNom());
        }   
        System.out.println("\n----------------- Les Aretes -----------------\n");
        for(Arete a : this.allAretes)
        {
            System.out.println(a.getNoeudDepart() + " - " + a.getNoeudArrive() +  " : " + a.getNbVoiture() + " " + a.getCouleur());
        }   
        System.out.println("\n----------------- Les Paramètres -----------------\n");
        System.out.println("\nNombre de joueurs minimium : " + this.nbJoueurMin);
        System.out.println("\nNombre de joueurs minimium : " + this.nbJoueurMax);
        System.out.println("\nNombre de pions : " + this.nbPionMax);
        System.out.println("\nNombre de wagons pour finir : " + this.nbWagonFin);
        System.out.println("\nNombre de points pour 1 : " + this.nbPoint1);
        System.out.println("\nNombre de points pour 2 : " + this.nbPoint2);
        System.out.println("\nNombre de points pour 3 : " + this.nbPoint3);
        System.out.println("\nNombre de points pour 4 : " + this.nbPoint4);
        System.out.println("\nNombre de points pour 5 : " + this.nbPoint5);
        System.out.println("\nNombre de points pour 6 : " + this.nbPoint6);
        System.out.println("\nNombre de joueurs avec double voies : " + this.nbJoueurDoublesVoies);
        System.out.println("\nNombre Wagon par couleur : " + this.nbWagonCouleur);
        System.out.println("\nNombre de carte joker : " + this.nbJoker);

        System.out.println("\n----------------- Les Images -----------------\n");
        for(String ch : this.allImages)
        {
            System.out.println(ch);
        }

        System.out.println("\n----------------- Main du joueur -----------------\n");

        System.out.println("Joueur 1 : " + joueur1);
        System.out.println("NB carte : " + joueur1.getMain().size());
        System.out.println("NB carte HashMap: " + joueur1.getCartes().size());

        System.out.println("\n----------------- Verif nb carte par couleur -----------------\n");
        System.out.println("Bleu: " + joueur1.nbCouleur("Bleu"));
        System.out.println("Rouge : " + joueur1.nbCouleur("Rouge"));
        System.out.println("Vert : " + joueur1.nbCouleur("Vert"));
        System.out.println("Jaune : " + joueur1.nbCouleur("Jaune"));
        System.out.println("Noir : " + joueur1.nbCouleur("Noir"));
        System.out.println("Blanc : " + joueur1.nbCouleur("Blanc"));
        System.out.println("Orange : " + joueur1.nbCouleur("Orange"));
        System.out.println("Violet : " + joueur1.nbCouleur("Violet"));
        System.out.println("rien : " + joueur1.nbCouleur("fergreg"));
        System.out.println("Marron : " + joueur1.nbCouleur("Marron"));
        System.out.println("Gris : " + joueur1.nbCouleur("Gris"));

        Color color;
        try {
            java.lang.reflect.Field field = Class.forName("java.awt.Color").getField("grey");
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }

        System.out.println("Couleur : " + color);
    }
    //
    // Lire le fichier XML qu'on rentre en paramètre et assigner les valeurs dans le controleur
    public void lireFichierXML(File fichierXML, Controleur controleur)
    {
        try {
            // On cree un nouveau document JDOM avec en argument le fichier XML
            // Le parsing est termine
            SAXBuilder sxb = new SAXBuilder();
            document = sxb.build(fichierXML);

            racine = document.getRootElement();
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On crée une Liste regroupant toutes les balises <noeud> contenu dans la racine
        List<Element> listNoeuds = racine.getChild("listeNoeuds").getChildren("noeud");
        for (Element noeud : listNoeuds) 
        {
            String nom = noeud.getAttributeValue("nom");
            int x = Integer.parseInt(noeud.getChild("coordonnees").getAttributeValue("x"));
            int y = Integer.parseInt(noeud.getChild("coordonnees").getAttributeValue("y"));
            int xNom = Integer.parseInt(noeud.getChild("coordonneesTexte").getAttributeValue("x"));
            int yNom = Integer.parseInt(noeud.getChild("coordonneesTexte").getAttributeValue("y"));
            // On créer le noeud avec les valeurs récupérées
            this.allNoeuds.add(new Noeud(nom, x, y, xNom, yNom));
        }

        // String nom = noeud.getChild("nom").getText();

        // On crée une Liste regroupant toutes les balises <arete> contenu dans la racine
        List<Element> listAretes = racine.getChild("listeArete").getChildren("arete");
        for (Element arete : listAretes) 
        {
            Noeud noeudDepart = null;
            Noeud noeudArrive = null;

            for (Noeud noeud : this.allNoeuds) {
                if (noeud.getNom().equals(arete.getChild("noeudDepart").getAttributeValue("nom"))) 
                {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(arete.getChild("noeudArrive").getAttributeValue("nom"))) 
                {
                    noeudArrive = noeud;
                }
            };
            int nbWagon = Integer.parseInt(arete.getChild("nbWagon").getAttributeValue("nb"));
            // Récupération de la couleur de l'arête
            Color couleur = new Color (Integer.parseInt(arete.getChild("couleur").getAttributeValue("couleur")));
            boolean sensUnique = Boolean.parseBoolean(arete.getChild("sens").getAttributeValue("sens"));
            // On créer l'arete avec les valeurs récupérées
            this.allAretes.add(new Arete(noeudDepart, noeudArrive, nbWagon, couleur, sensUnique));
        }
        // On crée une Liste regroupant toutes les balises <arete> contenu dans la
        // racine

        List<Element> parametres = racine.getChild("listeParametres").getChildren("parametre");
        for(Element parametre : parametres)
        {
            nbJoueurMin                = Integer.parseInt(parametre.getChild("nbJoueurMin").getAttributeValue("nb"));
            nbJoueurMax                 = Integer.parseInt(parametre.getChild("nbJoueurMax").getAttributeValue("nb"));
            nbPionMax               = Integer.parseInt(parametre.getChild("nbPion").getAttributeValue("nb"));
            nbWagonFin              = Integer.parseInt(parametre.getChild("nbWagonFin").getAttributeValue("nb"));
            nbPoint1                = Integer.parseInt(parametre.getChild("nbPoint1").getAttributeValue("nb"));
            nbPoint2                = Integer.parseInt(parametre.getChild("nbPoint2").getAttributeValue("nb"));
            nbPoint3                = Integer.parseInt(parametre.getChild("nbPoint3").getAttributeValue("nb"));
            nbPoint4                = Integer.parseInt(parametre.getChild("nbPoint4").getAttributeValue("nb"));
            nbPoint5                = Integer.parseInt(parametre.getChild("nbPoint5").getAttributeValue("nb"));
            nbPoint6                = Integer.parseInt(parametre.getChild("nbPoint6").getAttributeValue("nb"));
            nbCarteJoueur            = Integer.parseInt(parametre.getChild("nbCarteJoueur").getAttributeValue("nb"));
            nbJoueurDoublesVoies    = Integer.parseInt(parametre.getChild("nbJoueurDoublesVoies").getAttributeValue("nb"));
            nbWagonCouleur          = Integer.parseInt(parametre.getChild("nbWagonCouleur").getAttributeValue("nb"));
            nbJoker                 = Integer.parseInt(parametre.getChild("nbJoker").getAttributeValue("nb"));
        }

        // On s'occupe des cartes objectifs

        // Pour les images présentent dans le XML
        
        
        try {
            List<Element> listImages = racine.getChild("listeImage").getChildren("image");
            for (int i = 0; i < listImages.size(); i++) 
            {
                String idImage = listImages.get(i).getAttributeValue("idImage");
                String nom = listImages.get(i).getAttributeValue("nom");
                this.allImages.add(nom);
                
                byte[] decodedBytes = Base64.getDecoder().decode(idImage);
                FileOutputStream fos = new FileOutputStream("images/"+ nom + ".png");
                fos.write(decodedBytes);
                fos.close();
            }    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for(int i = 0; i < couleurCarte.length; i++)
        {
            this.allCartes.add(new Carte(couleurCarte[i],this.allImages.get(i)));
        }

        // Pour les cartes objectifs
        List<Element> listCartesObjectifs = racine.getChild("listeCarteObjectif").getChildren("CarteObjectif");
        for (Element carteObjectif : listCartesObjectifs) 
        {
            Noeud noeudDepart = null;
            Noeud noeudArrive = null;
            // Trouver les noeuds correspondant dans la liste des noeuds
            for (Noeud noeud : this.allNoeuds) {
                if (noeud.getNom().equals(carteObjectif.getChild("noeudDepart").getAttributeValue("nom"))) {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(carteObjectif.getChild("noeudArrive").getAttributeValue("nom"))) {
                    noeudArrive = noeud;
                }
            };
            int points = Integer.parseInt(carteObjectif.getChild("point").getAttributeValue("pts"));
            // On créer la carte avec les valeurs récupérées
            this.allCartesObjectifs.add(new CarteObjectif(noeudDepart,noeudArrive,points));
        }
        
    }

    public void afficherJeux() 
    {
        this.gui = new Gui(this);
    }

    public void resizeFrame(int width, int height) 
    {
        this.gui.resizeFrame(width, height);
    }

    public Joueur getJoueur() 
    {
        return this.joueur1;
    }
    
    public void joueurSuivant()
    {
        System.out.println("Joueur suivant");
        System.out.println("Tour "+ ++this.tour);
        
        if(this.joueur1.getNbPion() < this.nbWagonFin)
        {
            finPartie();
        }

        if(this.joueur1.getNbPion() < this.nbWagonFin)
        {
           // this.gui.notification("C'est la dernière manche");
            // this.afficherScore();

        }
        initPiocheObjectif();
    }

    public void initPiocheObjectif()
    {
         this.allCarteObjectifRandom.clear();
         
        // Nouvelle pioche de carte objectif
        Random random = new Random();
        // Verifier combien il en reste
        if(this.allCartesObjectifs.size() >= 3)
        {
            int index1 = random.nextInt(this.allCartesObjectifs.size());
            int index2 = random.nextInt(this.allCartesObjectifs.size());
            int index3 = random.nextInt(this.allCartesObjectifs.size());

            while(index1 == index2 || index1 == index3 || index2 == index3)
            {
                index1 = random.nextInt(this.allCartesObjectifs.size());
                index2 = random.nextInt(this.allCartesObjectifs.size());
                index3 = random.nextInt(this.allCartesObjectifs.size());
            }

            this.allCarteObjectifRandom.add(this.allCartesObjectifs.get(index1));
            this.allCarteObjectifRandom.add(this.allCartesObjectifs.get(index2));
            this.allCarteObjectifRandom.add(this.allCartesObjectifs.get(index3));
        }
        if(this.allCartesObjectifs.size() == 2)
        {
            int index1 = random.nextInt(this.allCartesObjectifs.size());
            int index2 = random.nextInt(this.allCartesObjectifs.size());

            while(index1 == index2 || index2 == index1)
            {
                index1 = random.nextInt(this.allCartesObjectifs.size());
                index2 = random.nextInt(this.allCartesObjectifs.size());
            }

            this.allCarteObjectifRandom.add(this.allCartesObjectifs.get(index1));
            this.allCarteObjectifRandom.add(this.allCartesObjectifs.get(index2));
        }
        if(this.allCartesObjectifs.size() == 1)
        {
            int index1 = random.nextInt(this.allCartesObjectifs.size());

            this.allCarteObjectifRandom.add(this.allCartesObjectifs.get(index1));
        }
    }

    public ArrayList<CarteObjectif> getAllCartesObjectifRandom()
    {
        return this.allCarteObjectifRandom;
    }

    public void placerCarte()
    {
        int cpt = 0 ;
        for(Carte carte : this.carteTable)
        {
            cpt++;
            if(carte == null) 
            {
                if(!this.pioche.isEmpty())
                {
                    this.poserCarteSurTable(cpt);  
                }
                    
            }
          
        }
    }

    public int prendrePossession(Arete arete) 
    {
        if(arete.getJoueur() == null)
        {
            for(Carte c : this.allCartes)
            {
                //System.out.println(this.joueur1.nbCouleur(c.getNomCarte()));
                if(arete.getCouleur().equals(c.getCouleur()))
                {
                    if(this.joueur1.nbCouleur(c.getNomCarte()) >= arete.getNbVoiture())
                    {  
                        this.joueur1.addArete(arete);
                        for(int i = 0; i < arete.getNbVoiture(); i++)
                        {
                            this.joueur1.removeCarte(c);
                            this.defausse.add(c);

                        }
                        if(this.pioche.isEmpty())
                        {
                            this.placerCarte();
                        }
                        this.remelanger();
                        arete.setJoueur(this.joueur1);
                        this.gui.refreshMain();
                        this.gui.refreshCarte();
                        return 1;
                    }
                }
               
            }
            return 3;        
        }
        return 2; 
    }

    public void refreshTabTrajets()
    {
        this.gui.refreshTableTrajets();
    }

    public Color[] getTabColor() 
    {
        return this.tabColors;
    }

    //si le joueur a moins de nbWagonFin, alors la fin de partie est déclanché
    public boolean finPartie()
    {
        if(this.joueur1.getMain().size() < nbWagonFin)
        {
            // On ouvre une popup disant que c'est la fin de partie, et dès qu'il clique sur ok on met le panel de fin
            JOptionPane.showMessageDialog(null, "Fin de la partie !", "Fin de partie", JOptionPane.INFORMATION_MESSAGE);
            this.frameAcceuil.finPartie();
            return true;
        }
        return false;
    }    

    public List<Noeud> getAllNoeuds() 
    {
        return this.allNoeuds;
    }
    
    public List<Arete> getAllTrajets() 
    {
        return this.allAretes;
    }
    

    public List<CarteObjectif> getAllCartesObjectifs() {
        return this.allCartesObjectifs;
    }

    public int getNbJoueurMin() {
        return nbJoueurMin;
    }

    public List<Carte> getCartePioche()
    {
        return pioche ;
    }

    public int getNbJoueurMax() {
        return nbJoueurMax;
    }

    public void setNbJoueur(int nb)
    {
        this.nbJoueur = nb;
    }

    public int getNbJoueur() {
        return nbJoueur;
    }

    public void setPionMax()
    {
        this.joueur1.setNbPion(this.nbPionMax);
    }

    public int getNbPionMax() {
        return nbPionMax;
    }

    public int getNbWagonFin() {
        return nbWagonFin;
    }

    public String[] getCouleurCarte(){return couleurCarte;}

    public ArrayList<Carte> getCarteTable() {
        return this.carteTable;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public List<String> getAllImages() 
    {
        return this.allImages;
    }

    public List<Carte> getAllCartes() 
    {
        return this.allCartes;
    }

    public void supprimerCarteObjectif(CarteObjectif carteObjectif) 
    {
        this.allCartesObjectifs.remove(carteObjectif);
    }

    public void etatConfig(boolean etat)
    {
        this.frameAcceuil.etatConfig(etat);
    }

    public FrameAccueil getFrameAccueil()
    {
        return this.frameAcceuil;
    }
    

    public static void main(String[] args) 
    {
        new Controleur();
    }

}
