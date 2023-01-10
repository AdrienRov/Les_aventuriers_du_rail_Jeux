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
    private PanelJeux panelJeux;
    private PanelCarte panelCarte;
    private PanelPioche panelPioche;
    private PanelConfig panelConfig;
    
    public Gui(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.panelJeux = new PanelJeux(this.ctrl);
        this.panelCarte = new PanelCarte(this.ctrl);
        this.panelPioche = new PanelPioche(this.ctrl);

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
        //Ajuster la taille de la fenetre
        this.setSize(width+400, height+190);
        this.panelPioche.setPreferredSize(new Dimension(400,  height+190));
        this.panelCarte.setSize(width, height);
        this.panelJeux.setPreferredSize(new Dimension(width+400, 150));
        //centrer la fenetre
        this.setLocationRelativeTo(null);  
        this.setBackground(Color.RED);  
    }


    public void refreshTableTrajets()
    {
        this.panelPioche.refreshTableTrajets();
        this.revalidate();
        this.repaint();
    }

    public void refreshMain()
    {
        this.panelJeux.refreshMain();
        this.revalidate();
        this.repaint();
    }

    public void refreshCarte()
    {
        this.panelCarte.repaint();
        this.revalidate();
        this.repaint();
    }

    public void notification(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.ERROR_MESSAGE);
    }

    public void piocherCarteObjectif()
    {
        this.panelPioche.piocherCarteObjectif();
    }

}
