package src.ihm;

// je veux un panel qui contien un espace vide au milieu, une barre qui prend 1/5 de la hauteur en bas et une bar qui commence en haut qui prend 1/5 de largeur 

import java.awt.*;
import javax.swing.*;

import src.Controleur;

public class PanelJeux extends JPanel{
    private Controleur ctrl;
    
    private JPanel panel1, panel2, panel3 ,Paneltext, PanelCarte, PanelInfo, Panel1Nord, PanelTable;
    private JButton suivant , ajouterWagons ;

    private JButton[] cartesJoueur ;
    private JButton[] cartesTable ;

    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;

       /*--------------------------------*/
	   /* Création des composants        */
	   /*--------------------------------*/


        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        Paneltext    = new JPanel();
        PanelCarte   = new JPanel();
        PanelInfo    = new JPanel();
        Panel1Nord   = new JPanel();
        PanelTable   = new JPanel();

        cartesJoueur = new JButton[8];
        cartesTable  = new JButton[6];

        suivant         = new JButton( "<html>Suivant<html>");
        ajouterWagons   = new JButton( "<html>Remplire une section<html>");
      

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
        JButton[] boutons = {suivant, ajouterWagons,};
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
     
        panel1.setBackground   (new Color(0x1E1E1E));
        panel1.setPreferredSize(new Dimension(300, 0));
        panel1.setLayout       (new BorderLayout());
        
        panel2.setBackground(Color.BLACK);
        panel2.setLayout    (new BorderLayout());
        
        panel3.setBackground   (Color.WHITE);
        panel3.setPreferredSize(new Dimension(0, 150));
        panel3.setLayout       (new GridLayout(2, 1, 10, 10));

        PanelTable.setLayout(new GridLayout(2, 3, 10, 10));
        PanelInfo.setLayout (new BorderLayout());

        Paneltext.setLayout (new GridLayout(1, 8, 10, 10));
        PanelCarte.setLayout(new GridLayout(1, 8, 10, 10));

       
        Panel1Nord.setLayout(new GridLayout(2, 1, 10, 10));

        /*--------------------------------*/
		/* Positionnement des composants  */
		/*--------------------------------*/

        //-------------------------------- Panel 1(table + bouton suivant et remplire section) --------------------------------
        
        Panel1Nord.add(boutons[0]);
        Panel1Nord.add(new JLabel("Table :"));

        for (int i = 0; i < cartesTable.length; i++) 
        {
            PanelTable.add(cartesTable[i]);
        }

        PanelInfo.add(PanelTable);
       
        panel1.add(Panel1Nord, BorderLayout.NORTH);
        panel1.add(PanelInfo, BorderLayout.CENTER);
        panel1.add(boutons[1], BorderLayout.SOUTH);
        
        
        //-------------------------------- Panel 2 (carte du joueur) -----------------------------------------------------------------
        
        Paneltext.add(  new JLabel("<html>votre main: <html>"));
        for (int i = 0; i < cartesJoueur.length-1; i++) {
            Paneltext.add(new JLabel(""));
        }
       
        // creation des 8 boutons qui vont representer les cartes du joueur
        for (int i = 0; i < cartesJoueur.length; i++) {
            cartesJoueur[i] = new JButton("Carte " + i);
            PanelCarte.add(cartesJoueur[i]);
        }
        
        panel3.add(Paneltext);
        panel3.add(PanelCarte);

        //-------------------------------- Panel de base --------------------------------------------
       
        this.add(panel1, BorderLayout.EAST);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.SOUTH);
    }  

}
