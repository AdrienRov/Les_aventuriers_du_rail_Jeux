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
    
<<<<<<< HEAD
    private JPanel panelMain;

    private List<JButton> cartesJoueur ;

    private PanelCarte panelCarte1;
    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;
    private List<String> allImages;
    private Joueur joueur;

    private Graphics2D  g2d;

=======
    private JPanel panel1, panel2, panel3 ,Paneltext, PanelCarte, PanelInfo, Panel1Nord, PanelTable;
    private JButton suivant , ajouterWagons ;

    private JButton[] cartesJoueur ;
    private JButton[] cartesTable ;
>>>>>>> de9ec02 (Paneljeux)

    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.panelMain = new JPanel();
        this.panelMain.setLayout(new GridLayout(1, 8, 10, 10));
        this.panelMain.setBackground(new Color(35, 31, 32));

<<<<<<< HEAD
        this.allImages = new ArrayList<String>();

        this.joueur = this.ctrl.getJoueur();
        this.cartesJoueur = new ArrayList<JButton>();
        System.out.println(this.ctrl.getJoueur().getCartes().size());
        this.allImages = this.ctrl.getAllImages();
        //création des boutons correspondant aux cartes du joueur
        refreshMain();
        
        this.add(this.panelMain);

        // initialisation des List
        this.allTrajets = new ArrayList<Arete>();
        this.allNoeud = new ArrayList<Noeud>();      
    }  

    public void refreshMain()
    {
        this.panelMain.removeAll();
        this.cartesJoueur.clear();
        int cpt = 0;
        for (int i = 0; i < this.ctrl.getCouleurCarte().length; i++) 
        {
            //ajouter une image carte wagons dans les boutons
            if(this.ctrl.getJoueur().getCartes().containsKey(this.ctrl.getCouleurCarte()[i]))
            {
                if(this.ctrl.getJoueur().nbCouleur(this.ctrl.getCouleurCarte()[i]) != 0)
                {
                    this.cartesJoueur.add(new JButton());
                    try {
                        ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(i)+".png");
                        //changer la taille de l'image
                        Image image = img.getImage(); // transform it
                        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        this.cartesJoueur.get(cpt).setIcon(new ImageIcon(newimg)); 
                        this.cartesJoueur.get(cpt).setBorderPainted(false);
                        this.cartesJoueur.get(cpt).setContentAreaFilled(false);
                        this.cartesJoueur.get(cpt).setFocusPainted(false);
                        this.cartesJoueur.get(cpt).setOpaque(false);
                        //afficher un texte sur le bouton par dessus l'image
                        //mettre le texte en blanc
                        this.cartesJoueur.get(cpt).setForeground(Color.WHITE);
                        //changer la police du texte
                        this.cartesJoueur.get(cpt).setFont(new Font("Arial", Font.BOLD, 15));
                        this.cartesJoueur.get(cpt).setText(" x" +this.joueur.nbCouleur(this.ctrl.getCouleurCarte()[i]));
                        cpt++;
                        
                    } catch (Exception e) {
                        // TODO: handle exception
                    }   
                }
                  
            }
        }
        for (int i = 0; i < this.cartesJoueur.size(); i++) {
            // this.cartesJoueur[i] = new JButton(" " +this.ctrl.compterCarteCouleur(color[i]));
            this.panelMain.add(this.cartesJoueur.get(i));
        }
        this.revalidate();
        this.repaint();
    }
=======
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

>>>>>>> de9ec02 (Paneljeux)
}
