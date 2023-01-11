package src.ihm;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import src.Controleur;
import src.metier.Arete;
import src.metier.Joueur;
import src.metier.Noeud;

public class PanelCarte extends JPanel 
{
    private Controleur  ctrl;
    private Graphics2D  g2d;

    private List<Arete> allTrajets;
    private List<Noeud> allNoeud;
    private Image       image;



    public PanelCarte(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        
        this.image          = new ImageIcon ("images/"+this.ctrl.getAllImages().get(this.ctrl.getAllImages().size()-1)+".png").getImage();
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

        this.allTrajets = this.ctrl.getAllTrajets   ();
        this.allNoeud   = this.ctrl.getAllNoeuds    ();

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
                    angle2          = angle;
                    distanceAuBord  = 0;
                } 
                else
                {
                    angle2          = angle + Math.PI / 2;
                    distanceAuBord  = 15;

                }

                // Décale les coordonnées des noeuds du second trajet en utilisant l'angle calculé précédemment
                int x1_2 = x1 + (int) (distanceAuBord * Math.cos(angle2));
                int y1_2 = y1 + (int) (distanceAuBord * Math.sin(angle2));
                int x2_2 = x2 + (int) (distanceAuBord * Math.cos(angle2));
                int y2_2 = y2 + (int) (distanceAuBord * Math.sin(angle2));

                // Dessine le second trajet en utilisant les coordonnées décalées
                if (arete.getCouleur().equals(Color.BLACK))
                {
                    g2d.setColor(Color.WHITE);
                }
                else
                {
                    g2d.setColor(Color.BLACK);
                }

                g2d.setStroke(new BasicStroke(15));
                g2d.drawLine(x1_2 + 15, y1_2 + 15, x2_2 + 15, y2_2 + 15);

                // Calcule la distance entre les noeuds du second trajet
                int distance                = (int) Math.sqrt(Math.pow(x1_2 - x2_2, 2) + Math.pow(y1_2 - y2_2, 2));
                int distanceEntreVoiture    = distance / (arete.getNbVoiture());

                // Place les voitures sur le second trajet
                for (int i = 1; i < arete.getNbVoiture() + 1; i++) 
                {
    
                    int xVoiture = (int) (x1_2 + ((i - 1) * distanceEntreVoiture + 10 ) * Math.cos(angle));
                    int yVoiture = (int) (y1_2 + ((i - 1) * distanceEntreVoiture + 10 ) * Math.sin(angle));

                    int xVoiture2 = (int) (x1_2 + (i * distanceEntreVoiture - 7) * Math.cos(angle));
                    int yVoiture2 = (int) (y1_2 + (i * distanceEntreVoiture - 7) * Math.sin(angle));
                    
                    g2d.setColor(arete.getCouleur());
                    g2d.setStroke(new BasicStroke(10));
                    g2d.drawLine(xVoiture + 15, yVoiture + 15, xVoiture2 + 15, yVoiture2 + 15);


                }
                
            }

            for (Arete areteP : this.allTrajets) 
            {

                int x1P = areteP.getNoeudDepart().getX();
                int y1P = areteP.getNoeudDepart().getY();
                int x2P = areteP.getNoeudArrive().getX();
                int y2P = areteP.getNoeudArrive().getY();

                // Calcul l'angle entre les deux noeuds du premier trajet
                double angleP = Math.atan2(y2P - y1P, x2P - x1P);
                double angle2P;
                int distanceAuBordP;

                // Décale l'angle de 90 degrés pour obtenir l'angle de décalage sur l'axe y
                if (areteP.getSensUnique()) 
                {
                    angle2P          = angleP;
                    distanceAuBordP  = 0;
                } 
                else
                {
                    angle2P          = angleP + Math.PI / 2;
                    distanceAuBordP  = 15;

                }

                // Décale les coordonnées des noeuds du second trajet en utilisant l'angle calculé précédemment
                int x1_2P = x1P + (int) (distanceAuBordP * Math.cos(angle2P));
                int y1_2P = y1P + (int) (distanceAuBordP * Math.sin(angle2P));
                int x2_2P = x2P + (int) (distanceAuBordP * Math.cos(angle2P));
                int y2_2P = y2P + (int) (distanceAuBordP * Math.sin(angle2P));

                // Calcule la distance entre les noeuds du second trajet
                int distanceP                =  (int) Math.sqrt(Math.pow(x1_2P - x2_2P, 2) + Math.pow(y1_2P - y2_2P, 2));
                int distanceEntreVoitureP    =  distanceP / (areteP.getNbVoiture());

                // Place les voitures sur le second trajet
                for (int i = 1; i < areteP.getNbVoiture() + 1; i++) 
                {
                    int pion  = (int) ((x1_2P +  ((distanceEntreVoitureP / 2)   * Math.cos(angle2P)))  + ((i - 1) * distanceEntreVoitureP ) * Math.cos(angleP));
                    int pion2 = (int) ((y1_2P +  ((distanceEntreVoitureP / 2)   * Math.sin(angle2P)))  + ((i - 1) * distanceEntreVoitureP ) * Math.sin(angleP));

                    System.out.println(""+ distanceEntreVoitureP / 2);
                    
                    for(Joueur j : this.ctrl.getTabJoueur())
                    {
                        for(Arete ar : j.getTabArete())
                        {
                            if(ar.equals(areteP))
                            {
                                g2d.setColor(j.getCouleur());
                                g2d.drawOval(pion + 15, pion2 + 15, 5, 5);
                            }
                        }
                    }
                }
            }
            
            // dessine les noms des noeuds
            for (Noeud noeud : this.allNoeud) 
            {
                    g2d.setStroke(new BasicStroke(4));
                    // compter le nombre de lettres dans le nom du noeud
                    int nbLettre = noeud.getNom().length();
                    // dessiner un carre blanc pour surligner le nom du noeud
                    g2d.setColor(Color.WHITE);
                    g2d.fillRect(noeud.getXNom(), noeud.getYNom(), nbLettre * 12, 20);
                    g2d.setColor(Color.WHITE);
                    
                    

                    //dessiner les noeuds
                    g2d.setColor(this.ctrl.getCouleurNoeud());
                    g2d.fillOval(noeud.getX(), noeud.getY(), 35, 35);

                    //dessiner un cercle noir autour du noeud
                    g2d.setColor(this.ctrl.getCouleurNoeud());
                    g2d.drawOval(noeud.getX(), noeud.getY(), 35, 35);
                    
                    //agrandir le texte du nom du noeud 
                    g2d.setFont(new Font("Arial", Font.BOLD, 20));

                    g2d.drawString(noeud.getNom(), noeud.getXNom(), noeud.getYNom()+15);

            }
                
        }
    }

}