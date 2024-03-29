package src;

import java.io.File;

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
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JOptionPane;

import java.awt.Color;

public class Controleur 
{
    private FrameAccueil              frameAcceuil;
    private Gui                       gui;
    private String[]                  couleurCarte = {"red","blue","green","yellow","black","pink","orange","white","gray"};
    private Color[]                   tabColors    = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.BLACK,Color.PINK,Color.ORANGE,Color.WHITE,Color.GRAY};
    private List<Noeud>               allNoeuds;
    private List<Arete>               allAretes;
    private List<CarteObjectif>       allCartesObjectifs;
    private List<String>              allImages;
    private List<Carte>               pioche;
    private List<Carte>               allCartes;
    private Document                  document;
    private static org.jdom2.Element  racine;
    private int                       nbJoueurMin;
    private int                       nbJoueurMax;
    private int                       nbPionMax;
    private int                       nbWagonFin;
    private int                       nbPoint1;
    private int                       nbPoint2;
    private int                       nbPoint3;
    private int                       nbPoint4;
    private int                       nbPoint5;
    private int                       nbPoint6;
    private int                       nbCarteJoueur;
    private int                       nbJoueurDoublesVoies;
    private int                       nbWagonCouleur;
    private int                       nbJoker;

    private Color                     couleurNoeud;
    private boolean                   verifFinPartie = false;
    private boolean                   verifVide = false;
    private int                       tour = 1;

    private int                       nbJoueur;
    private int                       idJoueur = 0;

    private ArrayList<Carte>          defausse;
    private ArrayList<Carte>          carteTable;
    private Joueur[]                  tabJoueur;
    private Joueur                    joueurSelect;

    private ArrayList<CarteObjectif>  allCarteObjectifRandom;

    public Controleur()  
    {
        this.frameAcceuil            = new FrameAccueil             (this);
        this.allNoeuds               = new ArrayList<Noeud>             ();
        this.allAretes               = new ArrayList<Arete>             ();
        this.allImages               = new ArrayList<String>            ();
        this.allCartes               = new ArrayList<Carte>             ();
        this.allCartesObjectifs      = new ArrayList<CarteObjectif>     ();
        this.allCarteObjectifRandom  = new ArrayList<CarteObjectif>     ();
        this.document                = new org.jdom2.Document           ();
        this.racine                  = new org.jdom2.Element("racine"   );
        this.joueurSelect            = new Joueur("", Color.BLACK       );    
    }

    public void initJeux()
    {
        this.initPioche();
        this.initPiocheObjectif();

        for(Joueur j : this.tabJoueur)
        {
            j.setNbPion(nbPionMax);
        }
    }
    public void initPioche()
    {
        this.pioche     = new ArrayList<Carte>      ();
        this.defausse   = new ArrayList<Carte>      ();
        this.carteTable = new ArrayList<Carte>      ();
        this.tabJoueur  = new Joueur[this.nbJoueur   ];

         // Initialisation des joueurs
        for(int i = 0 ; i < this.nbJoueur ; i++)
        {
            this.tabJoueur[i] = new Joueur("Joueur "+(i+1), this.tabColors[i]);
        }
        
        joueurSelect = tabJoueur[0];
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
        //distribuer 4 cartes de la pioche au joueur et les enlever de la pioche
        for(Joueur j: this.tabJoueur)
        {
            for(int i =0 ; i<this.nbCarteJoueur ;i++)
            {
                j.addMain(this.pioche.get(i));
                this.pioche.remove(i);
            }
        }

        // mettres  5 cartes sur la table et les enlever de la pioche
        for(int i =0 ; i<5 ;i++)
        {
            this.carteTable.add(this.pioche.get(i));
            this.pioche.remove(i);
        }
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
    
    public boolean detectDouble(Arete arete)
    {
        int cpt = 0;
        boolean possedeAreteDouble=false;
        for(Arete a : this.allAretes)
        {
            if(arete.getNoeudDepart().equals(a.getNoeudDepart()) && arete.getNoeudArrive().equals(a.getNoeudArrive()) ||  arete.getNoeudDepart().equals(a.getNoeudArrive()) && arete.getNoeudArrive().equals(a.getNoeudDepart()))
            {
                for(Joueur j : this.tabJoueur)
                {
                    for(Arete areteJ : j.getTabArete())
                    {
                        if(areteJ.getNoeudDepart().equals(a.getNoeudDepart()) && areteJ.getNoeudArrive().equals(a.getNoeudArrive()) ||  areteJ.getNoeudDepart().equals(a.getNoeudArrive()) && areteJ.getNoeudArrive().equals(a.getNoeudDepart()))
                        {
                            possedeAreteDouble = true;
                        }
                    }
                }
                cpt++;
            }
            if(cpt == 2 && possedeAreteDouble)
            {
                return true;
            }
        }
        
        return false;
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

        if(this.pioche.isEmpty() )
        {
            this.remelanger();
            this.placerCarte();
        }
        else
        {
            this.verifVide =  false ;
        }
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

        for(Joueur joueur : this.tabJoueur)
        {
            for(Arete arete : joueur.getTabArete())
        {
            somme += tabScore[arete.getNbVoiture()-1];
        }

            //parcourir les cartes objectif du joueur, si il possède la ville de départ et arrivée de la carte objectif on ajoute des points
            for(CarteObjectif carte : joueur.getTabCarteObjectif())
        {
            Set<Noeud> noeudsVisites = new HashSet<Noeud>();
            boolean possedeRoute = possedeRoute(carte.getNoeud1(), carte.getNoeud2(), this.joueurSelect.getNom(), this.joueurSelect.getTabArete(), noeudsVisites);
            
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
        
            joueur.setScore(somme-soustraction);
            System.out.println("Score "+joueur.getNom()+" "+(somme-soustraction));
            System.out.println("nb arete joueur = "+ this.joueurSelect.getAretes().size());
        }
    }

    public static boolean possedeRoute(Noeud depart, Noeud arrive, String nomJoueur, List<Arete> aretes, Set<Noeud> noeudsVisites) 
    {
        // Si le noeud de départ est le même que le noeud d'arrivée, alors le joueur possède une route
        if (depart == arrive) 
        {
          return true;
        }

        System.out.println("dans le 0 "+depart.getNom()+" "+arrive.getNom());
        // Ajout du noeud de départ aux noeuds visités
        noeudsVisites.add(depart);
      
        // Parcours de la liste d'arêtes
        for (Arete arete : aretes) {
          // Vérification si l'arête est possédée par le joueur
          if (arete.getJoueur().getNom().equals(nomJoueur)) 
          {
            System.out.println("dans le 1");
            // Vérification si l'un des noeuds de l'arête est le noeud de départ
            System.out.println("arete.getNoeudDepart() = "+arete.getNoeudDepart().getNom() + " | " +depart.getNom());
            System.out.println("arete.getNoeudArrive() = "+arete.getNoeudArrive().getNom() + " | " +depart.getNom());
            if (arete.getNoeudDepart().equals(depart) || arete.getNoeudArrive().equals(depart)) 
            {
                System.out.println("dans le 2");
                // Récupération du noeud qui n'est pas le noeud de départ
                Noeud noeudSuivant = (arete.getNoeudDepart() == depart) ? arete.getNoeudArrive() : arete.getNoeudDepart();
                // Vérification si le noeud n'a pas déjà été visité
                if (!noeudsVisites.contains(noeudSuivant)) 
                {
                    // Appel récursif sur le noeud qui n'est pas le noeud de départ
                    if (possedeRoute(noeudSuivant, arrive, nomJoueur, aretes, noeudsVisites)) 
                    {
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
        //this.gui.notification("C'est la dernière manche");
        calculScore();
        //System.out.println(trouverCheminLong());
        this.gui.afficherPanelFinPartie(true);
    }

    // methode pour remélanger la defausse et la mettre dans la pioche si la pioche est vide
    public void remelanger()
    {
            if(!this.verifVide)
            {
                this.gui.notification("La pioche est vide, on remélange la défausse");
                this.verifVide = true ;
            }
            this.pioche.addAll(this.defausse);
            this.defausse.clear();
            Collections.shuffle(this.pioche);
    }

    public void poserCarteSurTable(int i)
    {
        this.carteTable.set(i, this.pioche.get(0));
        System.out.println("1111111111111111111111  "+this.carteTable.get(0));
        this.pioche.remove(0);
    }

    // Afficher les données du fichier XML
    public void AfficherDonnees()
    {
        Color color;
        try {
            java.lang.reflect.Field field = Class.forName("java.awt.Color").getField("gray");
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
    }

    // Lire le fichier XML qu'on rentre en paramètre et assigner les valeurs dans le controleur
    public void lireFichierXML(File fichierXML, Controleur controleur, boolean verif)
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
        
        // On crée une Liste regroupant toutes les balises <arete> contenu dans la racine
        List<Element> listAretes = racine.getChild("listeArete").getChildren("arete");
        for (Element arete : listAretes) {
            Noeud noeudDepart = null;
            Noeud noeudArrive = null;

            for (Noeud noeud : this.allNoeuds) 
            {
                if (noeud.getNom().equals(arete.getChild("noeudDepart").getAttributeValue("nom"))) {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(arete.getChild("noeudArrive").getAttributeValue("nom"))) {
                    noeudArrive = noeud;
                }
            }
            
            int nbWagon = Integer.parseInt(arete.getChild("nbWagon").getAttributeValue("nb"));
            // Récupération de la couleur de l'arête
            Color couleur = new Color(Integer.parseInt(arete.getChild("couleur").getAttributeValue("couleur")));
            
            boolean sensUnique = Boolean.parseBoolean(arete.getChild("sens").getAttributeValue("sens"));
            // On crée l'arête avec les valeurs récupérées
            this.allAretes.add(new Arete(noeudDepart, noeudArrive, nbWagon, couleur, sensUnique));
        }

        List<Element> parametres = racine.getChild("listeParametres").getChildren("parametre");
        for(Element parametre : parametres)
        {
            nbJoueurMin             = Integer.parseInt(parametre.getChild("nbJoueurMin").getAttributeValue("nb"));
            nbJoueurMax             = Integer.parseInt(parametre.getChild("nbJoueurMax").getAttributeValue("nb"));
            nbPionMax               = Integer.parseInt(parametre.getChild("nbPion").getAttributeValue("nb"));
            nbWagonFin              = Integer.parseInt(parametre.getChild("nbWagonFin").getAttributeValue("nb"));
            nbPoint1                = Integer.parseInt(parametre.getChild("nbPoint1").getAttributeValue("nb"));
            nbPoint2                = Integer.parseInt(parametre.getChild("nbPoint2").getAttributeValue("nb"));
            nbPoint3                = Integer.parseInt(parametre.getChild("nbPoint3").getAttributeValue("nb"));
            nbPoint4                = Integer.parseInt(parametre.getChild("nbPoint4").getAttributeValue("nb"));
            nbPoint5                = Integer.parseInt(parametre.getChild("nbPoint5").getAttributeValue("nb"));
            nbPoint6                = Integer.parseInt(parametre.getChild("nbPoint6").getAttributeValue("nb"));
            nbCarteJoueur           = Integer.parseInt(parametre.getChild("nbCarteJoueur").getAttributeValue("nb"));
            nbJoueurDoublesVoies    = Integer.parseInt(parametre.getChild("nbJoueurDoublesVoies").getAttributeValue("nb"));
            nbWagonCouleur          = Integer.parseInt(parametre.getChild("nbWagonCouleur").getAttributeValue("nb"));
            nbJoker                 = Integer.parseInt(parametre.getChild("nbJoker").getAttributeValue("nb"));
        }

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

            for (Noeud noeud : this.allNoeuds) {
                if (noeud.getNom().equals(carteObjectif.getChild("noeudDepart").getAttributeValue("nom"))) {
                    noeudDepart = noeud;
                }
                if (noeud.getNom().equals(carteObjectif.getChild("noeudArrive").getAttributeValue("nom"))) {
                    noeudArrive = noeud;
                }
            };
            int points = Integer.parseInt(carteObjectif.getChild("point").getAttributeValue("pts"));
            this.allCartesObjectifs.add(new CarteObjectif(noeudDepart,noeudArrive,points));
        }

        couleurNoeud = new Color(Integer.parseInt(racine.getChild("couleurNoeud").getChild("couleur").getAttributeValue("couleur")));

    }
    
    public Gui getGui()
    {
        return this.gui;
    }

    public void afficherJeux() 
    {
        this.gui = new Gui(this);
        this.initPiocheObjectif();
        this.gui.premierTourCarteObjectif();
        this.gui.refreshTableCarteObjectif();
        this.tabJoueur[0].setPremierTour(false);
        this.initPiocheObjectif();
        this.verifLocoTable();
    }

    public void resizeFrame(int width, int height) 
    {
        this.gui.resizeFrame(width, height);
    }

    public Joueur[] getTabJoueur() 
    {
        return this.tabJoueur;
    }

    public Joueur getJoueur() 
    {
        return this.joueurSelect;
    }

    public Color getCouleurNoeud()
    {
        return this.couleurNoeud;
    }

    public boolean derniereManche()
    {
        if(this.getJoueur().getNbPion() < this.nbWagonFin)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
   
    public void joueurSuivant()
    {    
        System.out.println("CCCCCCCCCCCCCCCC"+ this.joueurSelect.nbCouleur("pink"));
        if(tabJoueur.length != 1)
        {
            if(joueurSelect == this.tabJoueur[this.nbJoueur-1] )
            {
                if(verifFinPartie )
                {
                    finDePartie();
                    return;
                }
                idJoueur = 0;
                joueurSelect = this.tabJoueur[idJoueur];
                this.tour++;
                this.gui.refreshTablePioche         ();
                this.gui.refreshMain                ();
                this.gui.refreshTableTrajets        ();
                this.gui.refreshCarte               ();
                this.gui.refreshPanelPion           ();
                this.gui.refreshTableCarteObjectif  ();
            }
            else
            {
                idJoueur++;
                joueurSelect = tabJoueur[idJoueur];
                this.gui.refreshTablePioche         ();
                this.gui.refreshMain                ();
                this.gui.refreshTableTrajets        ();
                this.gui.refreshCarte               ();
                this.gui.refreshPanelPion           ();
                this.gui.refreshTableCarteObjectif  ();
            }
        }

        if(tabJoueur.length == 1)
        {
            if(verifFinPartie )
            {
                finDePartie();
                return;
            }
        }
        if(this.getTabJoueur().length != 1)
        {
        this.gui.notification("C'est au tour de " + this.joueurSelect.getNom());
        }
        if (this.joueurSelect.getPremierTour() == true) 
        {
            this.joueurSelect.setPremierTour(false);
            initPiocheObjectif();
            this.gui.premierTourCarteObjectif();
        }
        
        if(derniereManche())
        {
            verifFinPartie = true;
        }
        initPiocheObjectif();

    }

    public void initPiocheObjectif()
    {
         this.allCarteObjectifRandom.clear();
         
        // Nouvelle pioche de carte objectif
        Random random = new Random();

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
            if(carte == null) 
            {
                if(!this.pioche.isEmpty())
                {
                    this.poserCarteSurTable(cpt);  
                }    
            }
            cpt++;
        }
    }

    public void verifLocoTable()
    {
        int nbLocoTable = 0;
        for(Carte carte : this.carteTable)
        {
            //si la carte est null on return 
            if(carte == null)
            {
                this.gui.refreshMain();
                return;
            }
            if(carte.getNomCarte() == "gray")
            {
                nbLocoTable++;
            }

        }

        if(nbLocoTable >= 3)
        {
            //notification lorsque il y a 3 locomotives sur la table
            JOptionPane.showMessageDialog(null, "Il y a 3 locomotives sur la table, vous devez défausser toutes les cartes de la table");
            this.defausse.addAll(this.carteTable);
            this.carteTable.clear();
            
            for(int i =0 ; i < 5 ; i++)
            {
                if(i<this.pioche.size())
                {
                    this.carteTable.add(this.pioche.get(0));
                    this.pioche.remove(0);
                }
                
            }
            this.placerCarte();
            this.gui.refreshMain(); 
            verifLocoTable();
        }
    }

    public int prendrePossession(Arete arete, Color couleur) 
    {
        if(couleur == null)
        {
            couleur = arete.getCouleur(); 
        }
        System.out.println("couleur : " + couleur);
        System.out.println("AllCartes : " + this.allCartes);
        if(arete.getJoueur() == null)
        {
            for(Carte c : this.allCartes)
            {
                System.out.println("couleur : " + c.getCouleur());
                if(couleur.equals(c.getCouleur()))
                {
                    if(this.joueurSelect.nbCouleur(c.getNomCarte()) + this.joueurSelect.nbCouleur("gray") >= arete.getNbVoiture())
                    {  
                        this.joueurSelect.addArete(arete);

                            int nbCartesALaBase = this.joueurSelect.nbCouleur(c.getNomCarte());
                            int cpt = 0;
                            for(int i = 0; i < arete.getNbVoiture(); i++)
                            {
                                if(i +1 > nbCartesALaBase )
                                {
                                    this.defausse.add(this.joueurSelect.getCartes().get("gray").remove(i-cpt - nbCartesALaBase));
                                    cpt ++;
                                }
                                else 
                                {
                                    this.joueurSelect.removeCarte(c);
                                    System.out.println("carte : " + c);
                                    this.defausse.add(c);
                                }
                            }
                            if(this.pioche.isEmpty())
                            {
                                this.remelanger();
                                this.placerCarte();
                            }
                            arete.setJoueur(this.joueurSelect);
                            this.gui.refreshTablePioche();
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

    public int getTour() 
    {
        return this.tour;
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

    public int getNbPionMax() {
        return nbPionMax;
    }

    public int getNbWagonFin() {
        return nbWagonFin;
    }

    public String[] getCouleurCarte()
    {
        return couleurCarte;
    }

    public ArrayList<Carte> getCarteTable() {
        return this.carteTable;
    }

    public List<String> getAllImages() 
    {
        return this.allImages;
    }

    public List<Carte> getAllCartes() 
    {
        return this.allCartes;
    }

    public int getNbJoueurDoublesVoies()
    {
        return this.nbJoueurDoublesVoies;
    }

    public void supprimerCarteObjectif(CarteObjectif carteObjectif) 
    {
        this.allCartesObjectifs.remove(carteObjectif);
    }

    public void etatConfig(boolean etat)
    {
        this.frameAcceuil.etatConfig(etat);
    }

    public void enleverPanelFin(boolean etat)
    {
        this.frameAcceuil.enleverPanelFin(etat);
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
