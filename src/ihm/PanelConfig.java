package src.ihm;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import src.Controleur;

public class PanelConfig extends JPanel implements ActionListener
{
    private JLabel lblImportXML;
    private JLabel lblNbJoueur;

    private JTextField txtNbJoueur;

    private JButton btnImportXML;
    private JButton btnValider;

    private JFileChooser fc = new JFileChooser();

    public PanelConfig(Controleur controleur)
    {
        // Paramètre du panel
        this.setLayout(new GridLayout(2, 2, 10, 10));
        this.setBackground(new Color(35, 31, 32));

        // Création des composants
        this.lblImportXML = new JLabel("Importer un fichier XML");
        this.btnImportXML = new JButton("Importer");
        this.btnValider = new JButton("Valider");

        // Ajout des composants
        this.add(this.lblImportXML);
        this.add(this.btnImportXML);
        this.add(this.btnValider);

        // Ajout des listeners
        this.btnImportXML.addActionListener(this);
        this.btnValider.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnImportXML) 
        {
            System.out.println("Import XML");
            this.fc.setDialogType(JFileChooser.OPEN_DIALOG);
                int valeurFC = this.fc.showDialog(this, "Ouvrir une image de fond");

                if (valeurFC == JFileChooser.APPROVE_OPTION) 
                {

                    File fichier = fc.getSelectedFile();
                    String nomFichier = fichier.getName();
                    String extension = nomFichier.substring(nomFichier.lastIndexOf("."));

                    if (extension.equals(".xml")) 
                    {
                        // Stocker le fichier XML dans le controleur avec methode lirefichierxml
                        // mettre une popup : le fichier à bien été importé

                    }
                    else
                    {
                        // Mettre une boite de dialog en disant que ce n'est pas un fichier XML, et rouvrir le JFileChooser


                    }
                }
        } 
        else if (e.getSource() == this.btnValider) 
        {
            System.out.println("Valider");
            // Lire le fichier XML, pour savoir le nb de joueur min et max, et ouvrir une JOptionPane pour demander le nombre de joueur pour la partie
            // Avec une JComboBox


        }
        
    }    
}
