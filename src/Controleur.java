package src;

import java.io.File;

import src.ihm.Gui;
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
    private List<Integer> allParametres;

    // Pour lire le fichier XML
    private Document document;
    private static org.jdom2.Element racine;

    public Controleur() 
    {
        this.gui = null;
        this.allNoeuds = new ArrayList<Noeud>();
        this.allAretes = new ArrayList<Arete>();
        this.document  = new org.jdom2.Document();
        this.racine    = new org.jdom2.Element("racine");
        this.lireFichierXML(new File("src/FichierSortie.xml"), this);
        //456
    }
//TEEEEESTTT
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
            controleur.creerNoeud(nom, x, y, xNom, yNom);
        }

        // String nom = noeud.getChild("nom").getText();

        // On crée une Liste regroupant toutes les balises <arete> contenu dans la racine
        List<Element> listAretes = racine.getChild("listeArete").getChildren("arete");
        for (Element arete : listAretes) 
        {
            String noeudDepart = arete.getChild("noeudDepart").getAttributeValue("nom");
            String noeudArrive = arete.getChild("noeudArrive").getAttributeValue("nom");
            int xDepart = Integer.parseInt(arete.getChild("coordonneesDepart").getAttributeValue("x"));
            int yDepart = Integer.parseInt(arete.getChild("coordonneesDepart").getAttributeValue("y"));
            int xArrive = Integer.parseInt(arete.getChild("coordonneesArrive").getAttributeValue("x"));
            int yArrive = Integer.parseInt(arete.getChild("coordonneesArrive").getAttributeValue("y"));
            int nbWagon = Integer.parseInt(arete.getChild("nbWagon").getAttributeValue("nb"));
            String couleur = arete.getChild("couleur").getAttributeValue("couleur");
            // On créer l'arete avec les valeurs récupérées
            controleur.creerArete(noeudDepart, noeudArrive, xDepart, xArrive, yDepart, yArrive, nbWagon, couleur);
        }

        // On crée une Liste regroupant toutes les balises <arete> contenu dans la
        // racine

        List<Element> parametres = racine.getChild("listeParametres").getChildren("parametre");
        for(Element parametre : parametres)
        {
            int nbJoueur = Integer.parseInt(parametre.getChild("nbJoueur").getAttributeValue("nb"));
            int nbWagon = Integer.parseInt(parametre.getChild("nbWagon").getAttributeValue("nb"));
            int nbWagonFin = Integer.parseInt(parametre.getChild("nbWagonFin").getAttributeValue("nb"));
            int nbPoint1 = Integer.parseInt(parametre.getChild("nbPoint1").getAttributeValue("nb"));
            int nbPoint2 = Integer.parseInt(parametre.getChild("nbPoint2").getAttributeValue("nb"));
            int nbPoint3 = Integer.parseInt(parametre.getChild("nbPoint3").getAttributeValue("nb"));
            int nbPoint4 = Integer.parseInt(parametre.getChild("nbPoint4").getAttributeValue("nb"));
            int nbPoint5 = Integer.parseInt(parametre.getChild("nbPoint5").getAttributeValue("nb"));
            int nbPoint6 = Integer.parseInt(parametre.getChild("nbPoint6").getAttributeValue("nb"));
            int nbJoueurDoublesVoies = Integer.parseInt(parametre.getChild("nbJoueurDoublesVoies").getAttributeValue("nb"));

            // print tous les parametres pour tester si ça marche
            System.out.println("nbJoueur : " + nbJoueur);
            System.out.println("nbWagon : " + nbWagon);
            System.out.println("nbWagonFin : " + nbWagonFin);
            System.out.println("nbPoint1 : " + nbPoint1);
            System.out.println("nbPoint2 : " + nbPoint2);
            System.out.println("nbPoint3 : " + nbPoint3);
            System.out.println("nbPoint4 : " + nbPoint4);
            System.out.println("nbPoint5 : " + nbPoint5);
            System.out.println("nbPoint6 : " + nbPoint6);
            System.out.println("nbJoueurDoublesVoies : " + nbJoueurDoublesVoies);

        }

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
    }

    public void creerNoeud(String nom, int x, int y, int xNom, int yNom)
    {
        this.allNoeuds.add(new Noeud(nom, x, y, xNom, yNom));
    }

    public void creerArete(String noeudDepart, String noeudArrive, int xDepart, int xArrive, int yDepart, int yArrive, int nbWagon, String couleur)
    {
        this.allAretes.add(new Arete(noeudDepart, noeudArrive, xDepart, xArrive, yDepart, yArrive, nbWagon, couleur));
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
    
}
