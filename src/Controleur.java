package src;

import java.io.File;
import java.text.DateFormat.Field;

import src.ihm.FrameAccueil;
import src.ihm.Gui;
import src.metier.Arete;
<<<<<<< HEAD
import src.metier.Carte;
import src.metier.CarteObjectif;
import src.metier.Joueur;
=======
import src.metier.CarteObjectif;
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
import src.metier.Noeud;

import java.io.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
<<<<<<< HEAD
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
=======
import java.util.List;

import javax.imageio.ImageIO;
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Color;
import java.awt.image.BufferedImage;
<<<<<<< HEAD
import java.awt.Image;
=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
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
<<<<<<< HEAD
    private List<Carte> pioche;
    private List<Carte> allCartes;
=======
    private List<Color> pioche;

>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
    // Pour lire le fichier XML
    private Document document;
    private static org.jdom2.Element racine;
    private int nbJoueur;
    private int nbWagon;
    private int nbWagonFin;
    private int nbPoint1;
    private int nbPoint2;
    private int nbPoint3;
    private int nbPoint4;
    private int nbPoint5;
    private int nbPoint6;
<<<<<<< HEAD
    private int nbCarteJoueur;
    private int nbJoueurDoublesVoies;
    private int nbWagonCouleur;
    private int nbJoker;

    private int cpt ; // compteur pour le nombre de carte sur la table

    private ArrayList<Carte> defausse;
    private ArrayList<Carte> carteTable ; //carte sur la table
    private Arete choixArete;
    private Joueur joueur1;

    private ArrayList<CarteObjectif> allCarteObjectifRandom;
=======
    private int nbJoueurDoublesVoies;

    private int cpt ; // compteur pour le nombre de carte sur la table

    private ArrayList<Color> defausse;
    private ArrayList<Color> mainJoueur;
    private ArrayList<Color> carteTable ; //carte sur la table

    private boolean verif ; // verifie si le joeur à joué ou non




>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a

    public Controleur()  
    {
        this.frameAcceuil = new FrameAccueil(this);
<<<<<<< HEAD
        this.joueur1 = new Joueur("Joueur 1");
=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
        this.allNoeuds = new ArrayList<Noeud>();
        this.allAretes = new ArrayList<Arete>();
        this.allParametres = new ArrayList<Integer>();
        this.allImages = new ArrayList<String>();
<<<<<<< HEAD
        this.allCartes = new ArrayList<Carte>();
        this.allCartesObjectifs = new ArrayList<CarteObjectif>();
        this.allCarteObjectifRandom = new ArrayList<CarteObjectif>();
        this.document  = new org.jdom2.Document();
        this.racine    = new org.jdom2.Element("racine");
        this.lireFichierXML(new File("src/FichierSortie.xml"), this);
        initPioche();
        initPiocheObjectif();
        this.AfficherDonnees();
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
=======
        this.allCartesObjectifs = new ArrayList<CarteObjectif>();
        this.document  = new org.jdom2.Document();
        this.racine    = new org.jdom2.Element("racine");
        this.lireFichierXML(new File("src/FichierSortie.xml"), this);
        this.AfficherDonnees();
        initPioche();
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
    }

    public void initPioche()
    {
<<<<<<< HEAD
        this.pioche = new ArrayList<Carte>();
        this.defausse = new ArrayList<Carte>();
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
        
        System.out.println(this.pioche);
        Collections.shuffle(this.pioche);
        
        //distribuer 4 cartes de la pioche au joueur et les enlever de la pioche et les mettre dans la main du joueur
        for(int i =0 ; i<this.nbCarteJoueur ;i++)
        {
            this.joueur1.addMain(this.pioche.get(i));
=======
        this.pioche = new ArrayList<Color>();
        this.defausse = new ArrayList<Color>();
        this.mainJoueur = new ArrayList<Color>();
        this.carteTable = new ArrayList<Color>();

        
        for(int i =0 ; i<12 ;i++)
        {
                this.pioche.add(Color.BLUE);
                this.pioche.add(Color.GREEN);
                this.pioche.add(Color.YELLOW);
                this.pioche.add(Color.RED);
                this.pioche.add(Color.ORANGE);
                this.pioche.add(Color.PINK);
                this.pioche.add(Color.BLACK);
                this.pioche.add(Color.WHITE);
        }

        for(int i =0 ; i<14 ;i++){ this.pioche.add(Color.GRAY);} // locomotive

        //distribuer 4 cartes de la pioche au joueur et les enlever de la pioche et les mettre dans la main du joueur
        for(int i =0 ; i<4 ;i++)
        {
            this.mainJoueur.add(this.pioche.get(i));
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
            this.pioche.remove(i);
        }

        // mettres  5 cartes sur la table et les enlever de la pioche
        for(int i =0 ; i<5 ;i++)
        {
            this.carteTable.add(this.pioche.get(i));
            this.pioche.remove(i);
<<<<<<< HEAD
        }     
        
        //refreshTabTrajets();
    }
        
    // action du joueur : piocher une carte 

    public void pioche(Joueur joueur)
    {
        if(!this.pioche.isEmpty())
        {
            joueur1.addMain(this.pioche.get(0));
            this.pioche.remove(0);
            this.gui.refreshMain();
            return;
        }
        this.gui.notification("Il n'y a plus de carte dans la pioche");      
        System.out.println("Taille de la pioche = "+this.pioche.size());
        
    }
    
=======
        }
    
        System.out.println(this.pioche);
        Collections.shuffle(this.pioche);
    
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(this.pioche);
        System.out.println();
        System.out.println();
        System.out.println("le joueur a la pioche suivante :" + this.mainJoueur);
        System.out.println("les cartes sur la table sont :" + this.carteTable);
    }
   

    // action du joueur : piocher une carte 

    public void pioche()
    {
        if(!verif)
        {
            this.mainJoueur.add(this.pioche.get(0));
            this.pioche.remove(0);
            cpt++;
        }
        if(cpt == 2)
        {
            cpt = 0;
            verif = true;
        }

    }
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a

    // action du joueur :prendre une carte de la table
    public void carteTable(int i)
    {
<<<<<<< HEAD
        this.joueur1.addMain(this.carteTable.get(i));
        this.carteTable.remove(i);
        this.carteTable.add(this.pioche.get(0));
        this.pioche.remove(0);
           
=======

        if(!verif)
        {
            this.mainJoueur.add(this.carteTable.get(i));
            this.carteTable.remove(i);
            this.carteTable.add(this.pioche.get(0));
            this.pioche.remove(0);
            this.verif = true; 
        }
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
    }

    public void verifTourDejeux()
    {
         
<<<<<<< HEAD
        System.out.println("fin de tour");
=======
        if(this.verif == true)
        {
            System.out.println("fin de tour");
            this.verif = false;
        }
        else
        {
            System.out.println("vous devez faire une et une seule des trois actions");
        }
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a

    }

    // methode pour remelanger la defausse et la mettre dans la pioche si la pioche est vide
    public void remelanger()
    {
        if(this.pioche.isEmpty())
        {
            this.pioche.addAll(this.defausse);
            this.defausse.clear();
            Collections.shuffle(this.pioche);
        }
    }

<<<<<<< HEAD
=======
    // action du jouer : prendre possession d'une arete
    public void prendrePossession(Arete a)
    {
        //verif si le joueurs dans sa main a assez de carte de la couleur de l'arete 
        // les locomotives sont pas comptabilisée
        //si oui : prendre possession de l'arete et enlever les cartes de la main du joueur et le mettre dans la defausse
        //si non : afficher un message d'erreur

        
        if(!verif)
        {
            if(cpt != 2)
            {
                int nbCarte = 0;
                for(int i =0 ; i<this.mainJoueur.size() ; i++)
                {
                    if(this.mainJoueur.get(i).equals(a.getCouleur()))
                    {
                        nbCarte++;
                    }
                
                }
                if(nbCarte >= a.getNbVoiture())
                {
                    for(int i =0 ; i<a.getNbVoiture() ;i++)
                    {
                        this.mainJoueur.remove(a.getCouleur());
                        this.defausse.add(a.getCouleur());
                    }
                    a.setPossession(true);
                    this.cpt++;
                    if(cpt == 2)
                    {
                        cpt = 0;
                        verif = true;
                    }
                }
                else
                {
                    System.out.println("vous n'avez pas assez de carte de la couleur de l'arete");
                }
            }
            
        }
        else 
        {
            System.out.println("vous avez deja fait une action");
        }


    }






    //créer une pioche de carte voiture avec les infos du

>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
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
        System.out.println("\nNombre de joueurs : " + this.nbJoueur);
        System.out.println("\nNombre de wagons : " + this.nbWagon);
        System.out.println("\nNombre de wagons pour finir : " + this.nbWagonFin);
        System.out.println("\nNombre de points pour 1 : " + this.nbPoint1);
        System.out.println("\nNombre de points pour 2 : " + this.nbPoint2);
        System.out.println("\nNombre de points pour 3 : " + this.nbPoint3);
        System.out.println("\nNombre de points pour 4 : " + this.nbPoint4);
        System.out.println("\nNombre de points pour 5 : " + this.nbPoint5);
        System.out.println("\nNombre de points pour 6 : " + this.nbPoint6);
        System.out.println("\nNombre de joueurs avec double voies : " + this.nbJoueurDoublesVoies);
<<<<<<< HEAD
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
    

=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
    }

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
<<<<<<< HEAD
                if (noeud.getNom().equals(arete.getChild("noeudDepart").getAttributeValue("nom"))) 
                {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(arete.getChild("noeudArrive").getAttributeValue("nom"))) 
                {
=======
                if (noeud.getNom().equals(arete.getChild("noeudDepart").getAttributeValue("nom"))) {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(arete.getChild("noeudArrive").getAttributeValue("nom"))) {
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
                    noeudArrive = noeud;
                }
            };
            int nbWagon = Integer.parseInt(arete.getChild("nbWagon").getAttributeValue("nb"));
            // Récupération de la couleur de l'arête
            Color couleur = new Color (Integer.parseInt(arete.getChild("couleur").getAttributeValue("couleur")));
            // On créer l'arete avec les valeurs récupérées
            this.allAretes.add(new Arete(noeudDepart, noeudArrive, nbWagon, couleur, true));
        }
        // On crée une Liste regroupant toutes les balises <arete> contenu dans la
        // racine

        List<Element> parametres = racine.getChild("listeParametres").getChildren("parametre");
        for(Element parametre : parametres)
        {
            nbJoueur                = Integer.parseInt(parametre.getChild("nbJoueur").getAttributeValue("nb"));
            nbWagon                 = Integer.parseInt(parametre.getChild("nbWagon").getAttributeValue("nb"));
            nbWagonFin              = Integer.parseInt(parametre.getChild("nbWagonFin").getAttributeValue("nb"));
            nbPoint1                = Integer.parseInt(parametre.getChild("nbPoint1").getAttributeValue("nb"));
            nbPoint2                = Integer.parseInt(parametre.getChild("nbPoint2").getAttributeValue("nb"));
            nbPoint3                = Integer.parseInt(parametre.getChild("nbPoint3").getAttributeValue("nb"));
            nbPoint4                = Integer.parseInt(parametre.getChild("nbPoint4").getAttributeValue("nb"));
            nbPoint5                = Integer.parseInt(parametre.getChild("nbPoint5").getAttributeValue("nb"));
            nbPoint6                = Integer.parseInt(parametre.getChild("nbPoint6").getAttributeValue("nb"));
<<<<<<< HEAD
            nbCarteJoueur            = Integer.parseInt(parametre.getChild("nbCarteJoueur").getAttributeValue("nb"));
            nbJoueurDoublesVoies    = Integer.parseInt(parametre.getChild("nbJoueurDoublesVoies").getAttributeValue("nb"));
            nbWagonCouleur          = Integer.parseInt(parametre.getChild("nbWagonCouleur").getAttributeValue("nb"));
            nbJoker                 = Integer.parseInt(parametre.getChild("nbJoker").getAttributeValue("nb"));
=======
            nbJoueurDoublesVoies    = Integer.parseInt(parametre.getChild("nbJoueurDoublesVoies").getAttributeValue("nb"));
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
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

<<<<<<< HEAD
        for(int i = 0; i < couleurCarte.length; i++)
        {
            this.allCartes.add(new Carte(couleurCarte[i],this.allImages.get(i)));
        }

=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
        // Pour les cartes objectifs
        List<Element> listCartesObjectifs = racine.getChild("listeCarteObjectif").getChildren("CarteObjectif");
        for (Element carteObjectif : listCartesObjectifs) 
        {
            String NomNoeudDepart = carteObjectif.getAttributeValue("nom");
            String NomNoeudArrive = carteObjectif.getAttributeValue("nom");
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
<<<<<<< HEAD
    }

    public Joueur getJoueur() 
    {
        return this.joueur1;
    }
    
    public void joueurSuivant()
    {
        System.out.println("Joueur suivant");
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

    public boolean prendrePossession(Arete arete) 
    {
        for(Carte c : this.allCartes)
        {
            System.out.println(this.joueur1.nbCouleur(c.getNomCarte()));
            if(arete.getCouleur().equals(c.getCouleur()))
            {
                if(this.joueur1.nbCouleur(c.getNomCarte()) >= arete.getNbVoiture())
                {
                    
                    this.joueur1.addArete(arete);
                    for(int i = 0; i < arete.getNbVoiture(); i++)
                    {
                        this.joueur1.removeCarte(c);
                    }
                    this.gui.refreshMain();
                    this.gui.refreshCarte();
                    return true;
                }
            }
        }
        return false;
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

    public int getNbJoueur() {
        return nbJoueur;
    }

    public int getNbWagon() {
        return nbWagon;
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
=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
    }

    // public static File stringToFile(String encodedString, File file) {
    //     try {
    //         byte[] bytes = Base64.getDecoder().decode(encodedString);
    //         file.createNewFile();
    //         FileOutputStream w = new FileOutputStream(file);
    //         w.write(bytes);
    //         w.close();
    //         return file;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }


    

    public List<Arete> getAllTrajets() {
        return this.allAretes;
    }

    public List<Noeud> getAllNoeuds() {
        return this.allNoeuds;
    }
    

    public static void main(String[] args) 
    {
        new Controleur();
    }

}
