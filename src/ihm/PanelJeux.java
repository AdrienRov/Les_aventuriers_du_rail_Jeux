package src.ihm;

// je veux un panel qui contien un espace vide au milieu, une barre qui prend 1/5 de la hauteur en bas et une bar qui commence en haut qui prend 1/5 de largeur 

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import src.Controleur;
import src.metier.Arete;
import src.metier.Noeud;

public class PanelJeux extends JPanel{
    private Controleur ctrl;
    
    private JPanel panelMain, paneltext, panelCarte;

    private JButton[] cartesJoueur ;

    private PanelCarte panelCarte1;
    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;


    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.panelMain = new JPanel();
        this.panelMain.setLayout(new GridLayout(2, 1, 10, 10));
        this.panelMain.setBackground(Color.WHITE);


        this.paneltext = new JPanel();
        this.paneltext.setLayout(new GridLayout(1, 8, 10, 10));

        this.panelCarte = new JPanel();
        this.panelCarte.setLayout(new GridLayout(1, 8, 10, 10));

        this.cartesJoueur = new JButton[8];

        //création des boutons correspondant aux cartes du joueur
        for (int i = 0; i < cartesJoueur.length; i++) 
        {
            //ajouter une image carte wagons dans les boutons
            this.cartesJoueur[i] = new JButton(new ImageIcon("src/images/carteWagon.png"));
        }


        this.paneltext.add(  new JLabel("<html>votre main: <html>"));

        //création de Label qui correspondent aux nombres de cartes que possèdent le joueur
        for (int i = 0; i < cartesJoueur.length-1; i++) 
        {
            this.paneltext.add(new JLabel(""));
        }

        
        for (int i = 0; i < cartesJoueur.length; i++) {
            this.cartesJoueur[i] = new JButton("Carte " + i);
            this.panelCarte.add(cartesJoueur[i]);
        }

        this.panelMain.add(this.paneltext);
        this.panelMain.add(this.panelCarte);
        this.add(this.panelMain);

        // initialisation des List
        this.allTrajets = new ArrayList<Arete>();
        this.allNoeud = new ArrayList<Noeud>();      
    }  
}
