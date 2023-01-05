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
    
    private JPanel panel1, panel2, panel3 ,paneltext, panelCarte, panelInfo, panel1Nord, panelTable;
    private JButton suivant , ajouterWagons ;

    private JLabel lblImage;
    private JButton[] cartesTable ;
    private JButton[] cartesJoueur ;

    private PanelCarte panelCarte1;
    private Graphics2D  g2d;

    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;


    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        paneltext    = new JPanel();
        panelCarte   = new JPanel();
        panelInfo    = new JPanel();
        panel1Nord   = new JPanel();
        panelTable   = new JPanel();

        this.panelCarte1 = new PanelCarte(ctrl);

        cartesJoueur = new JButton[8];
        cartesTable  = new JButton[6];

        this.lblImage = new JLabel(new ImageIcon("images/carte.png"));

        // initialisation des List
        this.allTrajets = new ArrayList<Arete>();
        this.allNoeud = new ArrayList<Noeud>();

        this.suivant         = new JButton( "<html>Suivant<html>");
        this.ajouterWagons   = new JButton( "<html>Remplire une section<html>");
      

        // creation des 8 boutons qui vont representer les cartes du joueur 
        for (int i = 0; i < cartesJoueur.length; i++) 
        {
            cartesJoueur[i] = new JButton("Carte");


        }

        // création des des boutons de la table
        for (int i = 0; i < cartesTable.length; i++) 
        {
            if(i == 0) 
            { 
                cartesTable[i] = new JButton("pioche : nombre de carte restante");
            }
            else 
            { 
                cartesTable[i] = new JButton("CarteTable" + i);
            }

          

        }

        
        // liste des boutons
        JButton[] boutons = {suivant, ajouterWagons};
        for (int i = 0; i < boutons.length; i++) {
            boutons[i].setBackground(Color.GRAY);
            boutons[i].setForeground(Color.WHITE);
            boutons[i].setFont(new Font("Arial", Font.BOLD, 12));
            boutons[i].setBorderPainted(false);
            boutons[i].setFocusPainted(false);
            boutons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            boutons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
         
        }

        this.setLayout(new BorderLayout());
     
        panel1.setBackground(new Color(0x1E1E1E));
        panel1.setPreferredSize(new Dimension(300, 0));
        panel1.setLayout(new BorderLayout());
        
        panel2.setBackground(Color.BLACK);
        panel2.setLayout (new BorderLayout());
        
        panel3.setBackground(Color.WHITE);
        panel3.setPreferredSize(new Dimension(0, 150));
        panel3.setLayout(new GridLayout(2, 1, 10, 10));

        panelTable.setLayout(new GridLayout(2, 3, 10, 10));
        panelInfo.setLayout(new BorderLayout());

        paneltext.setLayout(new GridLayout(1, 8, 10, 10));
        panelCarte.setLayout(new GridLayout(1, 8, 10, 10));

       
        panel1Nord.setLayout(new GridLayout(2, 1, 10, 10));

        /*--------------------------------*/
		/* positionnement des composants  */
		/*--------------------------------*/

        //-------------------------------- panel 1(table + bouton suivant et remplire section) --------------------------------
        
        panel1Nord.add(boutons[0]);
        panel1Nord.add(new JLabel("Table :"));

        for (int i = 0; i < cartesTable.length; i++) 
        {
            panelTable.add(cartesTable[i]);
        }

        panelInfo.add(panelTable);
       
        panel1.add(panel1Nord, BorderLayout.NORTH);
        panel1.add(panelInfo, BorderLayout.CENTER);
        panel1.add(boutons[1], BorderLayout.SOUTH);

        //panel2.add(lblImage, BorderLayout.SOUTH);
        panel2.add(panelCarte1, BorderLayout.CENTER);

        
        
        //-------------------------------- panel 2 (carte du joueur) -----------------------------------------------------------------
        
        paneltext.add(  new JLabel("<html>votre main: <html>"));
        for (int i = 0; i < cartesJoueur.length-1; i++) {
            paneltext.add(new JLabel(""));
        }
       
        // creation des 8 boutons qui vont representer les cartes du joueur
        for (int i = 0; i < cartesJoueur.length; i++) {
            cartesJoueur[i] = new JButton("Carte " + i);
            panelCarte.add(cartesJoueur[i]);
        }
        
        panel3.add(paneltext);
        panel3.add(panelCarte);

        //-------------------------------- panel de base --------------------------------------------
       
        this.add(panel1, BorderLayout.EAST);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
    }  
}
