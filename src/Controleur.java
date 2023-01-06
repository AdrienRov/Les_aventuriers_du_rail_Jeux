package src;

import java.io.File;

import java.io.File;

import src.ihm.Gui;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.Noeud;

import java.io.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.text.AttributeSet.ColorAttribute;

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
import java.awt.image.BufferedImage;

public class Controleur 
{
    private Gui gui;

    private List<Noeud> allNoeuds;
    private List<Arete> allAretes;
    private List<CarteObjectif> allCartesObjectifs;
    private List<Integer> allParametres;
    private List<String> allImages;
    private List<Color> pioche;

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
    private int nbJoueurDoublesVoies;

    private int cpt ; // compteur pour le nombre de carte sur la table

    private ArrayList<Color> defausse;
    private ArrayList<Color> mainJoueur;
    private ArrayList<Color> carteTable ; //carte sur la table

    private boolean verif ; // verifie si le joeur à joué ou non
    
    public Controleur()  
    {
        this.gui = new Gui(this);
        this.allNoeuds = new ArrayList<Noeud>();
        this.allAretes = new ArrayList<Arete>();
        this.allParametres = new ArrayList<Integer>();
        this.allImages = new ArrayList<String>();
        this.allCartesObjectifs = new ArrayList<CarteObjectif>();
        this.document  = new org.jdom2.Document();
        this.racine    = new org.jdom2.Element("racine");
        this.lireFichierXML(new File("src/FichierSortie.xml"), this);
        this.AfficherDonnees();
        initPioche();
    }

    public void initPioche()
    {
        this.pioche = new ArrayList<Color>();
        this.defausse = new ArrayList<Color>();
        this.mainJoueur = new ArrayList<Color>();
        this.carteTable = new ArrayList<Color>();

        
        for(int i =0 ; i<12 ;i++)
        {
                this.pioche.add(Color.BLUE  );
                this.pioche.add(Color.GREEN );
                this.pioche.add(Color.YELLOW);
                this.pioche.add(Color.RED   );
                this.pioche.add(Color.ORANGE);
                this.pioche.add(Color.PINK  );
                this.pioche.add(Color.BLACK );
                this.pioche.add(Color.WHITE );
        }

        for(int i =0 ; i<14 ;i++){ this.pioche.add(Color.GRAY);} // locomotive

        //distribuer 4 cartes de la pioche au joueur et les enlever de la pioche et les mettre dans la main du joueur
        for(int i =0 ; i<4 ;i++)
        {
            this.mainJoueur.add(this.pioche.get(i));
            this.pioche.remove(i);
        }

        // mettres  5 cartes sur la table et les enlever de la pioche
        for(int i =0 ; i<5 ;i++)
        {
            this.carteTable.add(this.pioche.get(i));
            this.pioche.remove(i);
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

    // action du joueur :prendre une carte de la table
    public void carteTable(int i)
    {

        if(!verif)
        {
            this.mainJoueur.add(this.carteTable.get(i));
            this.carteTable.remove(i);
            this.carteTable.add(this.pioche.get(0));
            this.pioche.remove(0);
            this.verif = true; 
        }
    }

    public void verifTourDejeux()
    {
         
        if(this.verif == true)
        {
            System.out.println("fin de tour");
            this.verif = false;
        }
        else
        {
            System.out.println("vous devez faire une et une seule des trois actions");
        }

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
                if (noeud.getNom().equals(arete.getChild("noeudDepart").getAttributeValue("nom"))) {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(arete.getChild("noeudArrive").getAttributeValue("nom"))) {
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
            nbJoueurDoublesVoies    = Integer.parseInt(parametre.getChild("nbJoueurDoublesVoies").getAttributeValue("nb"));
        }

        // On s'occupe des cartes objectifs

        // Pour les images présentent dans le XML
        try {
            List<Element> listImages = racine.getChild("listeImage").getChildren("image");
            for (Element image : listImages) 
            {
                String idImage = image.getAttributeValue("idImage");
                String nom = image.getAttributeValue("nom");
                byte[] decodedBytes = Base64.getDecoder().decode(idImage);
                FileOutputStream fos = new FileOutputStream("images/"+ nom + ".jpg");
                fos.write(decodedBytes);
                fos.close();
            }    
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

    //si le joueur a moins de nbWagonFin, alors la fin de partie est déclanché
    public boolean finPartie()
    {
        if(mainJoueur.size() < nbWagonFin)
        {
            return true;
        }
        return false;
    }

    


    public List<Arete> getAllTrajets() 
    {
        return this.allAretes;
    }

    public void afficherPanelJeu()
    {
        this.gui.afficherPanelJeu();
    }

    public List<Noeud> getAllNoeuds() 
    {
        return this.allNoeuds;
    }

    //vérifier si la main possède moins de 3 carte wagon

    

    public static void main(String[] args) 
    {
        new Controleur();
    }

}
