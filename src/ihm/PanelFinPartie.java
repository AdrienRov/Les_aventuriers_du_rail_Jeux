package src.ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.Font;

public class PanelFinPartie extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JPanel panelGagnant;
    private JPanel panelListeJoueurs;

    private JLabel lblTitrePanel;
    private JLabel lblGagnant;
    private JLabel lblScoreGagnant;

    private JLabel lblListeJoueurs;


    private JButton btnRetourAccueil;

    public PanelFinPartie(Controleur controleur)
    {
        this.ctrl = controleur;
        // Paramètre du panel
        this.setSize(800, 600);
        this.setLayout(new GridLayout(4, 1, 10, 10));
        this.setBackground(new Color(35, 31, 32));
        // Création des composants
        this.panelGagnant = new JPanel(new GridLayout(2, 1, 10, 10));
        this.panelListeJoueurs = new JPanel(new GridLayout(this.ctrl.getNbJoueur(), 1, 10, 10));

        this.lblTitrePanel = new JLabel("Fin de la partie");
        this.lblGagnant = new JLabel("Le gagnant est : ");
        this.lblScoreGagnant = new JLabel("Score du gagnant : ");

        this.lblListeJoueurs = new JLabel("Liste des joueurs : ");
        
        this.btnRetourAccueil = new JButton("Retour à l'accueil");
        // Configuration des composants
        this.lblTitrePanel.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblTitrePanel.setFont(new Font("Arial", Font.BOLD, 30));
        this.lblTitrePanel.setForeground(Color.WHITE);

        this.lblGagnant.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblGagnant.setFont(new Font("Arial", Font.BOLD, 20));
        this.lblGagnant.setForeground(Color.WHITE);

        this.lblScoreGagnant.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblScoreGagnant.setFont(new Font("Arial", Font.BOLD, 20));
        this.lblScoreGagnant.setForeground(Color.WHITE);

        this.lblListeJoueurs.setHorizontalAlignment(SwingConstants.CENTER);
        this.lblListeJoueurs.setFont(new Font("Arial", Font.BOLD, 20));
        this.lblListeJoueurs.setForeground(Color.WHITE);

        // Ajout des composants

        this.panelGagnant.add(this.lblGagnant);
        this.panelGagnant.add(this.lblScoreGagnant);

        this.panelListeJoueurs.add(this.lblListeJoueurs);
        for(int i = 0; i<this.ctrl.getNbJoueur(); i++)
        {
            this.panelListeJoueurs.add(new JLabel("Score du joueur " + i + " : le score mageul"));
        }

        this.add(this.lblTitrePanel);
        this.add(this.panelGagnant);
        this.add(this.panelListeJoueurs);
        this.add(this.btnRetourAccueil);


    }




    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnRetourAccueil)
        {
            // On re affiche le menu accueil de l'application
            this.ctrl.finPartie();
        }
        
    }
    
}
