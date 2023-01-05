package src.ihm;

// je veux un panel qui contien un espace vide au milieu, une barre qui prend 1/5 de la hauteur en bas et une bar qui commence en haut qui prend 1/5 de largeur 

import java.awt.*;
import javax.swing.*;

import src.Controleur;

public class PanelJeux extends JPanel{
    private Controleur ctrl;
    
    private JPanel panel1, panel2, panel3 ,Paneltext, PanelCarte;
    // bouton suivant, piocher, jouer, passer, quitter
    private JButton suivant, piocher, jouer, passer, quitter;
    // label pour le nom du joueur, le nombre de carte, le nombre de carte dans la pioche, le nombre de carte dans la defausse
    private JLabel nomJoueur, nbCarte, nbCartePioche, nbCarteDefausse;

    // 8 boutons qui vont representer les cartes du joueur
    private JButton[] cartesJoueur ;


    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;

        // creation des boutons


        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        Paneltext = new JPanel();
        PanelCarte = new JPanel();

        cartesJoueur = new JButton[8];

        suivant  = new JButton("Suivant");
        piocher  = new JButton("Piocher");
        jouer    = new JButton("Jouer");
        passer   = new JButton("Passer");
        quitter  = new JButton("Quitter");

        nomJoueur       = new JLabel("Nom du joueur");
        nbCarte         = new JLabel("Nombre de carte");
        nbCartePioche   = new JLabel("Nombre de carte dans la pioche");
        nbCarteDefausse = new JLabel("Nombre de carte dans la defausse");

        // creation des 8 boutons qui vont representer les cartes du joueur avec boucle 
        for (int i = 0; i < cartesJoueur.length; i++) {
            cartesJoueur[i] = new JButton("Carte");
        }
        
        // liste des boutons
        JButton[] boutons = {suivant, piocher, jouer, passer, quitter};
        //boucle pour changer les boutons, bouton arrondi  avec un fond gris, police blanche, taille 80%
        for (int i = 0; i < boutons.length; i++) {
            boutons[i].setBackground(Color.GRAY);
            boutons[i].setForeground(Color.WHITE);
            boutons[i].setFont(new Font("Arial", Font.BOLD, 20));
            boutons[i].setBorderPainted(false);
            boutons[i].setFocusPainted(false);
            boutons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            boutons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
         
        }
        // liste des labels
        JLabel[] labels = {nomJoueur, nbCarte, nbCartePioche, nbCarteDefausse};
        //boucle pour changer les labels, fond noir, police blanche, taille 80%
        for (int i = 0; i < labels.length; i++) {
            labels[i].setBackground(Color.BLACK);
            labels[i].setForeground(Color.WHITE);
            labels[i].setFont(new Font("Arial", Font.BOLD, 20));
        }
        //panel 1 marron tres foncé
        panel1.setBackground(new Color(0x1E1E1E));
        panel2.setBackground(Color.BLACK);
        panel3.setBackground(Color.WHITE);

        panel1.add(suivant);
        panel1.add(piocher);
        panel1.add(jouer);
        panel1.add(passer);
        panel1.add(quitter);

        Paneltext.setLayout(new GridLayout(1, 8, 10, 10));
        PanelCarte.setLayout(new GridLayout(1, 8, 10, 10));

        // le panel 1 prend 1/5 de la largeur est sera en colone 
        panel1.setPreferredSize(new Dimension(100, 0));
        panel1.setLayout(new GridLayout(5, 1, 10, 10));
        // le panel sera vide
        panel2.setLayout(new BorderLayout());
        // le panel 3 prend 1/5 de la hauteur et sera en ligne  
        panel3.setPreferredSize(new Dimension(0, 150));
        panel3.setLayout(new GridLayout(2, 1, 10, 10));
        // le panel 3 contient 2 panel, un pour les textes et un pour les cartes
        panel3.add(Paneltext);
        panel3.add(PanelCarte);
        // ajouter bouton et label au panel 3
        Paneltext.add(  new JLabel("Rouge: "));
        Paneltext.add(  new JLabel("Bleu: "));
        Paneltext.add(  new JLabel("Jaune: "));
        Paneltext.add(  new JLabel("Vert: "));
        Paneltext.add(  new JLabel("Noir: "));
        Paneltext.add(  new JLabel("Blanc: "));
        Paneltext.add(  new JLabel("Orange: "));
        Paneltext.add(  new JLabel("Violet: "));

        // creation des 8 boutons qui vont representer les cartes du joueur
        for (int i = 0; i < cartesJoueur.length; i++) {
            cartesJoueur[i] = new JButton("Carte " + i);
            PanelCarte.add(cartesJoueur[i]);
        }
        setLayout(new BorderLayout());
        add(panel1, BorderLayout.EAST);
        add(panel2, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
    }  
}
