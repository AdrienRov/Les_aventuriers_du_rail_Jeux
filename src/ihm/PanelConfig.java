package src.ihm;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Font;

import java.awt.Color;
import java.awt.Dimension;

import src.Controleur;

public class PanelConfig extends JPanel implements ActionListener
{
    private Controleur          ctrl            ;

    private JLabel              lblImportXML    ;
    private JLabel              lblTitre        ;

    private JButton             btnImportXML    ;
    private JButton             btnValider      ;
    private JButton             btnRetour       ;

    private JPanel              panelXML        ;
    private JPanel              panelValidation ;

    private JComboBox<Integer>  comboNbJoueurs  ;

    
    private File                fichier         ;
    private boolean             xmlImporte = false;


    public PanelConfig(Controleur ctrl)
    {
        this.ctrl = ctrl;
        // Paramètre du panel
        this.setSize(800,600);
        this.setLayout      (new GridLayout(3,1, 10, 10 ));
        this.setBackground  (new Color(35, 31, 32       ));

        // Création des composants
        this.panelXML          = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        this.panelValidation   = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        this.lblTitre          = new JLabel("Configuration de la partie");
        this.lblImportXML      = new JLabel("Importer un fichier XML");
        this.btnImportXML      = new JButton("Importer");
        this.btnValider        = new JButton("Valider");
        this.btnRetour         = new JButton("Retour");

        // Configuration des composants
        this.lblImportXML.setForeground         (Color.WHITE                    );
        this.lblImportXML.setFont               (new Font("Arial", Font.BOLD, 20));
        this.lblImportXML.setHorizontalAlignment(SwingConstants.CENTER          );
        this.lblTitre.setForeground             (Color.WHITE                    );
        this.lblTitre.setFont                   (new Font("Arial", Font.BOLD, 20));
        this.lblTitre.setHorizontalAlignment    (SwingConstants.CENTER          );

        this.panelXML.setBackground         (new Color(35, 31, 32));
        this.panelValidation.setBackground  (new Color(35, 31, 32));

        this.btnRetour.setPreferredSize     (new Dimension(100,45));
        this.btnValider.setPreferredSize    (new Dimension(100,45));

        // Ajout des composants
        this.add(lblTitre);
        this.panelXML.add(this.lblImportXML);
        this.panelXML.add(this.btnImportXML);
        this.add(this.panelXML);

        this.panelValidation.add(this.btnValider);
        this.panelValidation.add(this.btnRetour);
        this.add(this.panelValidation);

        // Ajout des listeners
        this.btnImportXML   .addActionListener(this);
        this.btnValider     .addActionListener(this);
        this.btnRetour      .addActionListener(this);
    }
    
    private File getFileDialog() 
    {
        JFileChooser fc = new JFileChooser();
        this.fichier    = new File(System.getProperty("user.dir"));
        fc.setCurrentDirectory(fichier);

        int valeurFC = fc.showOpenDialog(this);

            if (valeurFC == JFileChooser.APPROVE_OPTION) 
            {
                fichier = fc.getSelectedFile();
                String nomFichier   = fichier.getName();
                String extension    = nomFichier.substring(nomFichier.lastIndexOf("."));

                if (extension.equals(".xml")) 
                {
                    this.ctrl.lireFichierXML(fichier, ctrl,false);
                    JOptionPane.showMessageDialog(this, "Fichier XML importé avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    this.xmlImporte = true;
                    return fc.getSelectedFile();
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Le fichier importé n'est pas un fichier XML", "Erreur", JOptionPane.ERROR_MESSAGE);
                    
                }
            }
            return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnImportXML) 
        {
            File f = this.getFileDialog(); 
        } 
        else if (e.getSource() == this.btnValider) 
        {
            if(this.xmlImporte == false)
            {
                JOptionPane.showMessageDialog(this, "Veuillez importer un fichier XML", "Erreur", JOptionPane.ERROR_MESSAGE);
                return;
            }

            System.out.println("Valider");

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            JLabel label = new JLabel("Nombre de joueurs : ");

            this.comboNbJoueurs = new JComboBox<Integer>();
            for(int i = this.ctrl.getNbJoueurMin(); i <= this.ctrl.getNbJoueurMax(); i++)
            {
                comboNbJoueurs.addItem(i);
            }
            panel.add(label);
            panel.add(comboNbJoueurs);

            int result = JOptionPane.showConfirmDialog(null, panel, "Nombre de joueurs", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) 
            {
                this.ctrl.setNbJoueur(comboNbJoueurs.getSelectedIndex() + 1);
                this.ctrl.lireFichierXML(fichier, ctrl,true);
                this.ctrl.getFrameAccueil().dispose ();
                this.ctrl.initJeux                  ();
                this.ctrl.afficherJeux              ();
            } 
        }
        else if (e.getSource() == this.btnRetour) 
        {
            // Retour au menu principal
            this.ctrl.etatConfig(false);
        } 
    }    
}
