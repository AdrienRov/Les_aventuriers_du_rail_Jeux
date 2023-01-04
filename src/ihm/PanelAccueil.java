package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Image;

import src.Controleur;

public class PanelAccueil extends JPanel
{
    private Controleur ctrl;

    // Ajout des composants
    private Image carte;
    private JButton btnAjouterCarte;
    private JLabel lblTitre;


    public PanelAccueil(Controleur ctrl) 
    {
        this.ctrl = ctrl;
        this.setLayout(null);

        this.lblTitre = new JLabel("Les aventuriers du rail");

        // Configuration du panel
        this.setBackground(new java.awt.Color(0, 0, 255));

        this.add(lblTitre, BorderLayout.NORTH);
        this.add(btnAjouterCarte, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
