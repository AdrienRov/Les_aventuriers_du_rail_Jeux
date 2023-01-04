package src.ihm;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import src.Controleur;

public class Gui extends JFrame
{
    private Controleur controleur;
    private PanelAccueil panelAccueil;
    
    public Gui(Controleur controleur) 
    {
        this.controleur = controleur;
        this.panelAccueil = new PanelAccueil(this.controleur);

        this.setTitle("Jeu : Les aventuriers du rail");

        this.setSize(800, 600);

        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ajout des panels
        this.add(this.panelAccueil, BorderLayout.CENTER);

        this.pack();
        this.setLocationRelativeTo(null);
        // rendre la fenetre visible
        this.setVisible(true);
    }
}
