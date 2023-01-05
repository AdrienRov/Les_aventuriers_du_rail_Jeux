package src.ihm;

import javax.swing.JFrame;
import java.awt.BorderLayout;

import java.awt.Color;

import src.Controleur;

public class Gui extends JFrame
{
    private Controleur ctrl;
    private PanelJeux panelJeux;
    private PanelCarte panelCarte;
    
    public Gui(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.panelJeux = new PanelJeux(this.ctrl);
        this.panelCarte = new PanelCarte(this.ctrl);

        // Mettre une taille par defaut


        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ajout des panels

        this.setLocationRelativeTo(null);
        this.add(this.panelJeux, BorderLayout.CENTER);
        // rendre la fenetre visible
        
        this.setVisible(true);
        this.pack();
    }

    
}
