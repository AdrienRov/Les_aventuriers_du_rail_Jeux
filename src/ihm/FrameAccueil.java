package src.ihm;

import src.Controleur;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;



public class FrameAccueil extends JFrame implements ActionListener
{
    private Controleur ctrl;
    
    // Ajout des composants
    private JLabel          lblTitre, lblImage;
    private JButton         btnJouer;
    private JPanel          panelBoutons,panelTitre,panelImage;;
    private PanelConfig     panelConfig;
    private PanelFinPartie  panelFinPartie;
    private PanelCarte      panelCarte;
    private PanelPioche     panelPioche;
    private FrameAccueil    frameAccueil;


    public FrameAccueil(Controleur ctrl) 
    {
        this.ctrl           = ctrl;
        this.setLayout(null);
        this.setSize(800, 600);

        this.panelBoutons   = new JPanel();
        this.panelImage     = new JPanel();
        this.panelTitre     = new JPanel();
        this.panelConfig    = new PanelConfig(ctrl);
        this.lblTitre       = new JLabel("Les aventuriers du rail");
        this.lblImage       = new JLabel(new ImageIcon("images/Image_accueil.png"));


        this.panelConfig    = new PanelConfig(ctrl);
        //this.panelFinPartie = new PanelFinPartie(ctrl);
        
        this.btnJouer       = new JButton("Jouer");
        //centrer le texte du bouton
        this.btnJouer.setHorizontalAlignment        (JButton.CENTER);
        this.btnJouer.setVerticalAlignment          (JButton.CENTER);

        //Configurations panel boutons
        this.panelBoutons.setLayout     (new GridLayout());
        this.panelBoutons.setBounds     (0, 500, 800, 100);
        this.panelBoutons.setBackground (new java.awt.Color(35, 31, 32));

        //Configurations panel image
        this.panelImage.setLayout       (new GridLayout());
        this.panelImage.setBounds       (0, 50, 800, 450);
        this.panelImage.setBackground   (new java.awt.Color(35, 31, 32));

        //Configurations panel titre
        this.panelTitre.setLayout       (new GridLayout());
        this.panelTitre.setBounds       (0, 0, 800, 50);
        this.panelTitre.setBackground   (new java.awt.Color(35, 31, 32));

        // Configuration des composants
        this.lblTitre.setBounds             (0, 0, 800, 50);
        this.lblTitre.setHorizontalAlignment(JLabel.CENTER);
        this.lblTitre.setVerticalAlignment  (JLabel.CENTER);
        this.lblTitre.setFont               (new java.awt.Font("Arial", 1, 30));
        this.lblTitre.setForeground         (new java.awt.Color(255, 255, 255));

        this.btnJouer.addActionListener(this);

        // Ajout des composants
        this.panelImage  .add(this.lblImage     );
        this.panelBoutons.add(this.btnJouer     );
        this.panelTitre  .add(this.lblTitre     );

        this.add(this.lblTitre      , BorderLayout.NORTH    );
        this.add(this.panelImage    , BorderLayout.CENTER   );
        this.add(this.panelBoutons  , BorderLayout.SOUTH    );
        this.add(this.panelTitre    , BorderLayout.NORTH    );

        this.setVisible(true);
        this.setLocationRelativeTo(null);     

        
    }


    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == this.btnJouer)
        {
            //fermer la frame
            this.ctrl.etatConfig(true);
        }  
    }

    public void etatConfig(boolean etat) 
    {
        if (etat == true) 
        {
            this.remove(this.lblTitre       );
            this.remove(this.panelImage     );
            this.remove(this.panelBoutons   );
            this.remove(this.panelTitre     );

            this.panelConfig = new PanelConfig(this.ctrl    );
            this.add(this.panelConfig, BorderLayout.CENTER  );
        } 
        if (etat == false)
        {
            this.remove (this.panelConfig);
            this.add    (this.lblTitre, BorderLayout.NORTH      );
            this.add    (this.panelImage, BorderLayout.CENTER   );
            this.add    (this.panelBoutons, BorderLayout.SOUTH  );
            this.add    (this.panelTitre, BorderLayout.NORTH    );

            this.setVisible(true);
            this.setLocationRelativeTo(null);
        }
        repaint();
        revalidate();
    }

    public void enleverPanelFin(boolean etat)
    {
        if(etat == true)
        {
            this.dispose();
            this.ctrl.getGui().dispose();
            this.frameAccueil = new FrameAccueil(ctrl);
        }
        repaint();
        revalidate();
    }
}
