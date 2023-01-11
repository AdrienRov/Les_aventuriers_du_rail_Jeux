package src.ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import src.Controleur;
import src.metier.Joueur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.Font;

public class PanelFinPartie extends JPanel implements ActionListener
{
    private Controleur   ctrl;

    private JPanel       panelGagnant;
    private JPanel       panelListeJoueurs;

    private JLabel       lblTitrePanel;
    private JLabel       lblGagnant;
    private JLabel       lblScoreGagnant;

    private JLabel       lblListeJoueurs;

    private Joueur       joueurGagnant;

    private JButton      btnRetourAccueil;

    public PanelFinPartie(Controleur ctrl)
    {
        this.ctrl = ctrl;
        // Paramètre du panel
        this.setSize(800, 600);
        this.setLayout(new GridLayout(5, 1, 10, 10));
        this.setBackground(new Color(35, 31, 32));
        // Création des composants
        this.joueurGagnant          = this.ctrl.getTabJoueur()[0];
        this.panelGagnant           = new JPanel(new GridLayout(2, 1, 10, 10));
        this.panelListeJoueurs      = new JPanel(new GridLayout(this.ctrl.getNbJoueur(), 1, 10, 10));

        for(Joueur joueur : this.ctrl.getTabJoueur())
        {
            if(joueur.getScore() > this.joueurGagnant.getScore())
            {
                this.joueurGagnant  = joueur;
            }
        }

        this.lblTitrePanel          = new JLabel("Fin de la partie");
        this.lblGagnant             = new JLabel("Le gagnant est : " + this.joueurGagnant.getNom() );
        this.lblScoreGagnant        = new JLabel("Score du gagnant : " + this.joueurGagnant.getScore() + " points");

        this.lblListeJoueurs        = new JLabel("Liste des joueurs : ");
        
        this.btnRetourAccueil       = new JButton("Retour à l'accueil");
        // Configuration des composants
        this.btnRetourAccueil.addActionListener(this);
        this.btnRetourAccueil.setPreferredSize(new Dimension(100,30));

        this.panelGagnant.setBackground(new Color(35, 31, 32));
        this.panelListeJoueurs.setBackground(new Color(35, 31, 32));

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

        for(int i = 0; i<this.ctrl.getNbJoueur(); i++)
        {
            JLabel lblNomJoueur = new JLabel(this.ctrl.getTabJoueur()[i].getNom() + " | Score : " + this.ctrl.getTabJoueur()[i].getScore());
            lblNomJoueur.setHorizontalAlignment(SwingConstants.CENTER);
            lblNomJoueur.setForeground(Color.WHITE);
            this.panelListeJoueurs.add(lblNomJoueur);
        }

        this.add(this.lblTitrePanel);
        this.add(this.panelGagnant);
        this.add(this.lblListeJoueurs);
        this.add(this.panelListeJoueurs);
        this.add(this.btnRetourAccueil);
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnRetourAccueil)
        {
            // On affiche à nouveau le menu accueil de l'application en enlevant le tableau de fin de partie
            this.ctrl.enleverPanelFin(true);
            this.setVisible(false);
        }
        
    }
    
}
