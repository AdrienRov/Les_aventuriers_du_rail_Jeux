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
import src.metier.Noeud;

public class PanelJeux extends JPanel{
    private Controleur ctrl;
    
    private JPanel panelMain;

    private JButton[] cartesJoueur ;

    private PanelCarte panelCarte1;
    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;
    private List<String> allImages;


    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.panelMain = new JPanel();
        this.panelMain.setLayout(new GridLayout(1, 8, 10, 10));
        this.panelMain.setBackground(new Color(35, 31, 32));

        this.allImages = new ArrayList<String>();



        this.cartesJoueur = new JButton[8];

        this.allImages = this.ctrl.getAllImages();
        //création des boutons correspondant aux cartes du joueur
        for (int i = 0; i < 8; i++) 
        {
            //ajouter une image carte wagons dans les boutons
            this.cartesJoueur[i] = new JButton();
            try {
                this.cartesJoueur[i].setIcon(new ImageIcon( "./images/"+this.allImages.get(i)+".png")); 
                this.cartesJoueur[i].setBorderPainted(false);
                this.cartesJoueur[i].setContentAreaFilled(false);
                this.cartesJoueur[i].setFocusPainted(false);
                this.cartesJoueur[i].setOpaque(false);
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
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
}
