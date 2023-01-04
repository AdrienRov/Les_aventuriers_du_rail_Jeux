package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Image;

import src.Controleur;
//TEST BRanche
public class PanelAccueil extends JPanel
{
    private Controleur controleur;

    // Ajout des composants
    private Image carte;
    private JButton btnAjouterCarte;
    private JLabel lblTitre;


    public PanelAccueil(Controleur controleur) 
    {
        this.controleur = controleur;
        this.setLayout(null);

        // Configuration des composants
        this.btnAjouterCarte = new JButton("Ajouter une carte de jeu");

        this.lblTitre = new JLabel("Les aventuriers du rail");

        // Configuration du panel
        this.setBackground(new java.awt.Color(0, 0, 255));

        this.add(lblTitre, BorderLayout.NORTH);
        this.add(btnAjouterCarte, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void ajouterCarte()
    {
        
    }
}
