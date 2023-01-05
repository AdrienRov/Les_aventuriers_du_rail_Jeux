package src.ihm;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import java.awt.Color;

import src.Controleur;

public class Gui extends JFrame
{
    private Controleur ctrl;
    private PanelAccueil panelAccueil;
    private PanelJeux panelJeux;
    
    public Gui(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.panelAccueil = new PanelAccueil(this.ctrl);
        this.panelJeux = new PanelJeux(this.ctrl);


        // Mettre une taille par defaut

        this.setSize(800, 600);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ajout des panels
        this.add(this.panelAccueil, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        // rendre la fenetre visible
        this.setVisible(true);
    }

    public void afficherPanelJeu()
    {
        this.remove(this.panelAccueil);
        this.add(this.panelJeux, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
        
    }


}
