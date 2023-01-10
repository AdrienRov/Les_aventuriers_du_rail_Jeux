package src.ihm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;

import src.Controleur;

public class Gui extends JFrame
{
    private Controleur ctrl;
    private FrameAccueil frameAccueil;
    private PanelJeux panelJeux;
    private PanelCarte panelCarte;
    private PanelPioche panelPioche;
    private PanelConfig panelConfig;
    private PanelFinPartie panelFinPartie;
    
    public Gui(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.panelJeux      = new PanelJeux(this.ctrl);
        this.panelCarte     = new PanelCarte(this.ctrl);
        this.panelPioche    = new PanelPioche(this.ctrl);
        this.panelFinPartie = new PanelFinPartie(this.ctrl);
        this.frameAccueil   = new FrameAccueil(this.ctrl);

        this.setResizable(false);
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

        int tailleDebut = this.getWidth();
        //Ajuster la taille de la fenetre
        this.setSize(width+(width/3)+50, height+190);
        this.panelPioche.setPreferredSize(new Dimension((width/3)+30,  height+190));
        this.panelCarte.setSize(width, height);
        this.panelJeux.setPreferredSize(new Dimension(width+650, 150));
        //centrer la fenetre
        // this.setLocationRelativeTo(null); 
        this.setBackground(Color.RED);  
        if(tailleDebut != this.getWidth())
        {
            this.setLocationRelativeTo(null);
        }
    }


    public void refreshTableTrajets()
    {
        this.panelPioche.refreshTableTrajets();
        this.revalidate();
        this.repaint();
    }

    public void refreshPanelPion()
    {
        this.panelPioche.refreshPanelPion();
        this.revalidate();
        this.repaint();
    }

    public void refreshMain()
    {
        this.panelJeux.refreshMain();
        this.revalidate();
        this.repaint();
    }

    public void refreshTablePioche()
    {
        this.panelPioche.refreshTablePioche();
        this.revalidate();
        this.repaint();
    }

    public void refreshCarte()
    {
        this.panelCarte.repaint();
        this.revalidate();
        this.repaint();
    }

    public void refreshTableCarteObjectif()
    {
        this.panelPioche.refreshTableObjectifs();
        this.revalidate();
        this.repaint();
    }

    public void notification(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void piocherCarteObjectif()
    {
        this.panelPioche.piocherCarteObjectif();
    }

    public void premierTourCarteObjectif()
    {
        this.panelPioche.premTourCarteObjectif();
    }

    public void afficherPanelFinPartie(boolean etat)
    {
        if(etat == true)
        {
            this.remove(this.panelCarte);
            this.remove(this.panelJeux);
            this.remove(this.panelPioche);
            this.add(this.panelFinPartie);
        }
        else
        {
            this.remove(this.panelFinPartie);
            this.add(this.frameAccueil);
        }

    }

}
