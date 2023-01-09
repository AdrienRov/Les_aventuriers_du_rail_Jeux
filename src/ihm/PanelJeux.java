package src.ihm;

// je veux un panel qui contien un espace vide au milieu, une barre qui prend 1/5 de la hauteur en bas et une bar qui commence en haut qui prend 1/5 de largeur 

import java.awt.*;
<<<<<<< HEAD
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
=======
import java.util.ArrayList;
import java.util.List;

>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
import javax.swing.*;

import src.Controleur;
import src.metier.Arete;
<<<<<<< HEAD
import src.metier.Joueur;
=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
import src.metier.Noeud;

public class PanelJeux extends JPanel{
    private Controleur ctrl;
    
<<<<<<< HEAD
    private JPanel panelMain;

    private List<JButton> cartesJoueur ;
=======
    private JPanel panelMain, paneltext, panelCarte;

    private JButton[] cartesJoueur ;
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a

    private PanelCarte panelCarte1;
    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;
<<<<<<< HEAD
    private List<String> allImages;
    private Joueur joueur;
=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a


    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.panelMain = new JPanel();
<<<<<<< HEAD
        this.panelMain.setLayout(new GridLayout(1, 8, 10, 10));
        this.panelMain.setBackground(new Color(35, 31, 32));

        this.allImages = new ArrayList<String>();

        this.joueur = this.ctrl.getJoueur();
        this.cartesJoueur = new ArrayList<JButton>();
        System.out.println(this.ctrl.getJoueur().getCartes().size());
        this.allImages = this.ctrl.getAllImages();
        //création des boutons correspondant aux cartes du joueur
        refreshMain();
        
=======
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
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
        this.add(this.panelMain);

        // initialisation des List
        this.allTrajets = new ArrayList<Arete>();
        this.allNoeud = new ArrayList<Noeud>();      
    }  
<<<<<<< HEAD

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
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
}
