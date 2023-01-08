package src.ihm;

// je veux un panel qui contien un espace vide au milieu, une barre qui prend 1/5 de la hauteur en bas et une bar qui commence en haut qui prend 1/5 de largeur 

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.Controleur;
import src.metier.Arete;
import src.metier.Joueur;
import src.metier.Noeud;

public class PanelJeux extends JPanel{
    private Controleur ctrl;
    
    private JPanel panelMain;

    private JButton[] cartesJoueur ;

    private PanelCarte panelCarte1;
    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;
    private List<String> allImages;
    private Joueur joueur;


    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.panelMain = new JPanel();
        this.panelMain.setLayout(new GridLayout(1, 8, 10, 10));
        this.panelMain.setBackground(new Color(35, 31, 32));

        this.allImages = new ArrayList<String>();

        this.joueur = this.ctrl.getJoueur();
        this.cartesJoueur = new JButton[this.ctrl.getJoueur().getCartes().size()];
        System.out.println(this.ctrl.getJoueur().getCartes().size());
        this.allImages = this.ctrl.getAllImages();
        //création des boutons correspondant aux cartes du joueur
        int cpt = 0;
        for (int i = 0; i < this.ctrl.getCouleurCarte().length; i++) 
        {
            //ajouter une image carte wagons dans les boutons
            if(this.ctrl.getJoueur().getCartes().containsKey(this.ctrl.getCouleurCarte()[i]))
            {
                this.cartesJoueur[cpt] = new JButton();
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(i)+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesJoueur[cpt].setIcon(new ImageIcon(newimg)); 
                    this.cartesJoueur[cpt].setBorderPainted(false);
                    this.cartesJoueur[cpt].setContentAreaFilled(false);
                    this.cartesJoueur[cpt].setFocusPainted(false);
                    this.cartesJoueur[cpt].setOpaque(false);
                    //afficher un texte sur le bouton par dessus l'image
                    //mettre le texte en blanc
                    this.cartesJoueur[cpt].setForeground(Color.WHITE);
                    //changer la police du texte
                    this.cartesJoueur[cpt].setFont(new Font("Arial", Font.BOLD, 15));
                    this.cartesJoueur[cpt].setText(" " +this.joueur.nbCouleur(this.ctrl.getCouleurCarte()[i]));
                    cpt++;
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }   
            }
        }

        // liste de color pour les cartes en color 
        Color[] color = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.BLACK, Color.WHITE, Color.ORANGE, Color.PINK};
        

        //création de Label qui correspondent aux nombres de cartes que possèdent le joueur
        for (int i = 0; i < cartesJoueur.length; i++) {
            // this.cartesJoueur[i] = new JButton(" " +this.ctrl.compterCarteCouleur(color[i]));
            this.panelMain.add(cartesJoueur[i]);
        }
        this.add(this.panelMain);

        // initialisation des List
        this.allTrajets = new ArrayList<Arete>();
        this.allNoeud = new ArrayList<Noeud>();      
    }  

    public void refreshMain()
    {
        this.panelMain.removeAll();
        int cpt = 0;
        for (int i = 0; i < this.ctrl.getCouleurCarte().length; i++) 
        {
            //ajouter une image carte wagons dans les boutons
            if(this.ctrl.getJoueur().getCartes().containsKey(this.ctrl.getCouleurCarte()[i]))
            {
                this.cartesJoueur[cpt] = new JButton();
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(i)+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesJoueur[cpt].setIcon(new ImageIcon(newimg)); 
                    this.cartesJoueur[cpt].setBorderPainted(false);
                    this.cartesJoueur[cpt].setContentAreaFilled(false);
                    this.cartesJoueur[cpt].setFocusPainted(false);
                    this.cartesJoueur[cpt].setOpaque(false);
                    //afficher un texte sur le bouton par dessus l'image
                    //mettre le texte en blanc
                    this.cartesJoueur[cpt].setForeground(Color.WHITE);
                    //changer la police du texte
                    this.cartesJoueur[cpt].setFont(new Font("Arial", Font.BOLD, 15));
                    this.cartesJoueur[cpt].setText(" " +this.joueur.nbCouleur(this.ctrl.getCouleurCarte()[i]));
                    cpt++;
                    
                } catch (Exception e) {
                    // TODO: handle exception
                }   
            }
        }

        for (int i = 0; i < cartesJoueur.length; i++) {
            // this.cartesJoueur[i] = new JButton(" " +this.ctrl.compterCarteCouleur(color[i]));
            this.panelMain.add(cartesJoueur[i]);
        }
        this.revalidate();
        this.repaint();
    }
}
