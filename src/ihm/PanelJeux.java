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
    private Controleur    ctrl;
    
    private JPanel        panelMain;

    private List<JLabel>  cartesJoueur ;

    public PanelJeux(Controleur ctrl){

        this.ctrl = ctrl;
        
        this.panelMain = new JPanel();
        this.setLayout              (new BorderLayout());
        this.panelMain.setLayout    (new GridLayout(1, 8, 10, 10));
        this.panelMain.setBackground(new Color(35, 31, 32       ));

        this.cartesJoueur = new ArrayList<JLabel>();

        refreshMain();
        this.add(this.panelMain);
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
                    this.cartesJoueur.add(new JLabel());
                    try {
                        ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(i)+".png");
                        //changer la taille de l'image
                        Image image = img.getImage(); // transform it
                        Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                        this.cartesJoueur.get(cpt).setIcon(new ImageIcon(newimg)); 
                        this.cartesJoueur.get(cpt).setOpaque(false);
                        //afficher un texte sur le bouton par dessus l'image
                        //mettre le texte en blanc
                        this.cartesJoueur.get(cpt).setForeground(Color.WHITE);
                        //changer la police du texte
                        this.cartesJoueur.get(cpt).setFont(new Font("Arial", Font.BOLD, 10));
                        this.cartesJoueur.get(cpt).setText(" x" +this.ctrl.getJoueur().nbCouleur(this.ctrl.getCouleurCarte()[i]));
                        cpt++;
                        
                    } catch (Exception e) {}   
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
}
