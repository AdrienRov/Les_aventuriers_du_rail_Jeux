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
    private PanelPioche panelPioche;
    
    public Gui(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.panelJeux = new PanelJeux(this.ctrl);
        this.panelCarte = new PanelCarte(this.ctrl);
        this.panelPioche = new PanelPioche(this.ctrl);

        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //ajout des panels
        this.add(this.panelCarte, BorderLayout.CENTER);
        this.add(this.panelJeux, BorderLayout.SOUTH);
        this.add(this.panelPioche, BorderLayout.EAST);
        this.setVisible(true);
        this.pack();
    }

    public void resizeFrame(int width, int height)
    {
        //Ajuster la taille de la fenetre
        this.setSize(width+500, height+100);
        this.panelCarte.setSize(width, height);
        this.panelJeux.setSize(width+500, 200);
        this.panelPioche.setSize(600, height +200);  

        //centrer la fenetre
        this.setLocationRelativeTo(null);     
    }

    
}
