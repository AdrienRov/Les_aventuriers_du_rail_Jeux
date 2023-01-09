package src.ihm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;

import java.awt.Color;
<<<<<<< HEAD
import java.awt.Dimension;
=======
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a

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
<<<<<<< HEAD
=======

>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
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
<<<<<<< HEAD
        this.setSize(width+(width/3)+50, height+190);
        System.out.println("width: "+width+" height: "+height);
        this.panelPioche.setPreferredSize(new Dimension((width/3)+30,  height+190));
        this.panelCarte.setSize(width, height);
        this.panelJeux.setPreferredSize(new Dimension(width+650, 150));
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
=======
        this.setSize(width+500, height+100);
        this.panelCarte.setSize(width, height);
        this.panelJeux.setSize(width+500, 200);
        this.panelPioche.setSize(600, height +200);  

        //centrer la fenetre
        this.setLocationRelativeTo(null);     
>>>>>>> ef39959aad131b9c45a2af7a5ea3e9d82626069a
    }

    
}
