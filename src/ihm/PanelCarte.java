package src.ihm;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



import src.Controleur;
import src.metier.Arete;
import src.metier.Noeud;
public class PanelCarte extends JPanel {
    private Controleur ctrl;
    private Graphics2D  g2d;

    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;
    private Image      image;



    public PanelCarte(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.setBackground(new Color(35, 31, 32));
        this.image          = new ImageIcon ("images/carte.png").getImage();
        this.allTrajets     = new ArrayList<Arete>();
        this.allNoeud       = new ArrayList<Noeud>();
        this.setBackground(new Color(35, 31, 32));
    }

    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.drawImage(image, 0, 0, this);
        this.ctrl.resizeFrame(this.image.getWidth(this), this.image.getHeight(this));

        this.allTrajets = this.ctrl.getAllTrajets();
        this.allNoeud   = this.ctrl.getAllNoeuds();

        if (this.allTrajets != null) 
        {
            for (Arete arete : this.allTrajets) 
            {

                int x1 = arete.getNoeudDepart().getX();
                int y1 = arete.getNoeudDepart().getY();
                int x2 = arete.getNoeudArrive().getX();
                int y2 = arete.getNoeudArrive().getY();

                // Calcul l'angle entre les deux noeuds du premier trajet
                double angle = Math.atan2(y2 - y1, x2 - x1);
                double angle2;
                int distanceAuBord;

                // Décale l'angle de 90 degrés pour obtenir l'angle de décalage sur l'axe y
                if (arete.getSensUnique()) 
                {
                    angle2 = angle;
                    distanceAuBord = 0;
                } 
                else 
                {
                    angle2 = angle + Math.PI / 2;
                    distanceAuBord = 15;

                }

                // Décale les coordonnées des noeuds du second trajet en utilisant l'angle
                // calculé précédemment
                int x1_2 = x1 + (int) (distanceAuBord * Math.cos(angle2));
                int y1_2 = y1 + (int) (distanceAuBord * Math.sin(angle2));
                int x2_2 = x2 + (int) (distanceAuBord * Math.cos(angle2));
                int y2_2 = y2 + (int) (distanceAuBord * Math.sin(angle2));

                // Dessine le second trajet en utilisant les coordonnées décalées

                if (arete.getCouleur().equals(Color.BLACK))
                    g2d.setColor(Color.WHITE);
                else
                    g2d.setColor(Color.BLACK);

                g2d.setStroke(new BasicStroke(15));
                g2d.drawLine(x1_2 + 15, y1_2 + 15, x2_2 + 15, y2_2 + 15);

                // Calcule la distance entre les noeuds du second trajet
                int distance = (int) Math.sqrt(Math.pow(x1_2 - x2_2, 2) + Math.pow(y1_2 - y2_2, 2));
                int distanceEntreVoiture = distance / (arete.getNbVoiture());

                // Place les voitures sur le second trajet
                for (int i = 1; i < arete.getNbVoiture() + 1; i++) 
                {
                    int xVoiture = (int) (x1_2 + ((i - 1) * distanceEntreVoiture + 10) * Math.cos(angle));
                    int yVoiture = (int) (y1_2 + ((i - 1) * distanceEntreVoiture + 10) * Math.sin(angle));

                    int xVoiture2 = (int) (x1_2 + (i * distanceEntreVoiture - 7) * Math.cos(angle));
                    int yVoiture2 = (int) (y1_2 + (i * distanceEntreVoiture - 7) * Math.sin(angle));
                    g2d.setColor(arete.getCouleur());
                    g2d.setStroke(new BasicStroke(10));
                    g2d.drawLine(xVoiture + 15, yVoiture + 15, xVoiture2 + 15, yVoiture2 + 15);

                }
            }

            // dessine les nom des noeuds
            for (Noeud noeud : this.allNoeud) 
            {
                    g2d.setStroke(new BasicStroke(4));
                    // compter le nombre de lettre dans le nom du noeud
                    int nbLettre = noeud.getNom().length();
                    // dessiner un carre blanc pour surligner le nom du noeud
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(noeud.getXNom(), noeud.getYNom(), nbLettre * 12, 20);
                    g2d.setColor(Color.WHITE);
                    
                    

                    //dessiner les noeuds
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(noeud.getX(), noeud.getY(), 35, 35);

                    //dessiner un cercke noir autour du noeud
                    g2d.setColor(Color.BLACK);
                    g2d.drawOval(noeud.getX(), noeud.getY(), 35, 35);
                    
                    //agrandir le texte du nom du noeud 
                    g2d.setFont(new Font("Arial", Font.BOLD, 20));

                    g2d.drawString(noeud.getNom(), noeud.getXNom(), noeud.getY()-13);

            }

            if(!this.ctrl.getJoueur().getAretes().isEmpty())
            {
                for(Arete a : this.ctrl.getJoueur().getAretes())
                {
                    int x1 = a.getNoeudDepart().getX();
                    int y1 = a.getNoeudDepart().getY();
                    int x2 = a.getNoeudArrive().getX();
                    int y2 = a.getNoeudArrive().getY();

                    //calcul des coordonnées du centre du trajet
                    int xCentre = (x1 + x2) / 2;
                    int yCentre = (y1 + y2) / 2;


                    g2d.setColor(Color.black);
                    //dessiner un cercle noir autour du trajet
                    g2d.drawOval(xCentre, yCentre, 40, 40);

                }
            }   
        }
    }

    

}
