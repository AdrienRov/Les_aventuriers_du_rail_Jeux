package src.ihm;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.*;
import src.Controleur;
import src.metier.Arete;
import src.metier.CarteObjectif;
import src.metier.Joueur;



public class PanelPioche extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JPanel panelBoutonPioche, panelGlobal, panelObjectif, panelPion;
    private JButton[] cartesTable ;
    private JButton btnPiocheObjectif;
    private JButton btnRemplirSection;
    private JLabel  lblTrajets[] = new JLabel[3];
    private List<Color>  lstColor ;
    private JComboBox<Color> comboColor;
    private JRadioButton rdTrajets[];
    private JLabel lblTable, lblObjectif;
    private JLabel lblNomJoueur;
    private JTable table;
    private Object[][] donneesTrajets = {{"", "", ""}};
    private String[] entetesTrajets = {"Départ", "Voiture", "Arrivée", "Validation"};

    private Object[][] donneesObjectif = {{"", "", ""}};
    private String[] entetesObjectif;
    private JTable tableObjectif;
    private TableColumn[] tabColObjectif;
    private TableColumn tabColTrajet      = new TableColumn();
    private JComboBox<String> comboVille1, comboVille2;
    private boolean verif =  true ; 
    private int action = 0;

    private JCheckBox checkObjectif1, checkObjectif2, checkObjectif3;

    public PanelPioche(Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.table = new JTable(this.donneesTrajets, this.entetesTrajets);
        this.setLayout(new BorderLayout());
        this.panelBoutonPioche = new JPanel();
        this.lstColor = new ArrayList<Color>();
        this.panelBoutonPioche.setLayout(new GridLayout(3, 3, 10, 10));
        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new GridLayout(5, 1, 1, 1));
        this.panelObjectif = new JPanel();
        this.panelObjectif.setLayout(new GridLayout(1, 2, 1, 1));
        this.tabColObjectif = new TableColumn[3];
        this.rdTrajets = new JRadioButton[this.ctrl.getAllTrajets().size()];
        this.comboVille1 = new JComboBox<String>();
        this.comboVille2 = new JComboBox<String>();
        // Création des labels
        this.lblTable = new JLabel("Cartes sur la table : ");
        this.lblObjectif = new JLabel("Liste des objectifs :");
        this.lblNomJoueur = new JLabel(this.ctrl.getJoueur().getNom());

        this.lblNomJoueur.setForeground(this.ctrl.getJoueur().getCouleur());
        this.lblNomJoueur.setFont(new Font("Arial", Font.BOLD, 20));
        this.lblNomJoueur.setHorizontalAlignment(SwingConstants.CENTER);

        this.lblTable.setForeground(Color.WHITE);
        this.lblTable.setFont(new Font("Arial", Font.BOLD, 20));
        //centrer le texte lbl table au centre
        this.lblTable.setHorizontalAlignment(SwingConstants.CENTER);

        this.lblObjectif.setForeground(Color.WHITE);
        this.lblObjectif.setFont(new Font("Arial", Font.BOLD, 20));
        this.lblObjectif.setHorizontalAlignment(SwingConstants.CENTER);
        // Création des boutons
        this.btnPiocheObjectif = new JButton();
        this.cartesTable  = new JButton[6];
        this.btnRemplirSection = new JButton("Remplir une section");
        this.btnRemplirSection.addActionListener(this);
        this.btnPiocheObjectif.addActionListener(this);
        //////////////////////////////
        for(Color c: this.ctrl.getTabColor())
        {
            this.lstColor.add(c);
        }

        this.comboColor = new JComboBox<Color>(this.lstColor.toArray(new Color[this.lstColor.size()]));
        comboColor.setRenderer(new ColorListCellRenderer());
        this.comboColor.addActionListener(this);
        

        for (int i = 0; i < this.cartesTable.length; i++) 
        {
            
            if(i == 0) 
            { 
                this.cartesTable[i] = new JButton("");
                this.cartesTable[i].addActionListener(this);
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(9)+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesTable[i].setIcon(new ImageIcon(newimg)); 
                    //this.cartesTable[i].setBorderPainted(false);
                    this.cartesTable[i].setContentAreaFilled(false);
                    //this.cartesTable[i].setFocusPainted(false);
                    //this.cartesTable[i].setOpaque(false);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                } 
            }
            else  
            { 
                //gérer la taille des boutons
                this.cartesTable[i] = new JButton();
                this.cartesTable[i].addActionListener(this);

                
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getCarteTable().get(i-1).getNomImage()+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesTable[i].setIcon(new ImageIcon(newimg)); 
                    //this.cartesTable[i].setBorderPainted(false);
                    this.cartesTable[i].setContentAreaFilled(false);
                    //this.cartesTable[i].setFocusPainted(false);
                    //this.cartesTable[i].setOpaque(false);
                } catch (Exception e) {
                    // TODO: handle exception
                } 
            }
        }

        
        try {
            ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(9)+".png");
            //changer la taille de l'image
            Image image = img.getImage(); // transform it
            Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
            this.btnPiocheObjectif.setIcon(new ImageIcon(newimg)); 
            //this.btnPiocheObjectif.setBorderPainted(false);
            this.btnPiocheObjectif.setContentAreaFilled(false);
            //this.btnPiocheObjectif.setFocusPainted(false);
            //this.btnPiocheObjectif.setOpaque(false);
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        } 
        // Création de la table
        for(int i = 0; i < this.ctrl.getJoueur().getTabCarteObjectif().size(); i++)
        {
            this.donneesObjectif[i][0] = this.ctrl.getJoueur().getTabCarteObjectif().get(i).getNoeud1();
            this.donneesObjectif[i][1] = this.ctrl.getJoueur().getTabCarteObjectif().get(i).getNoeud2();
            this.donneesObjectif[i][2] = this.ctrl.getJoueur().getTabCarteObjectif().get(i).getScore();        
        }
  

        this.entetesObjectif = new String[] {"Départ", "Arrivé", "Points"};
        // Mise en place des panels

        for (int i = 0; i < this.cartesTable.length; i++) 
        {
            this.panelBoutonPioche.add(this.cartesTable[i]);
        }

        JScrollPane scrollPane = new JScrollPane(this.tableObjectif);
        this.panelBoutonPioche.setBackground(new Color(35, 31, 32));
        this.panelGlobal.setBackground(new Color(35, 31, 32));
        this.panelObjectif.setBackground(new Color(35, 31, 32));
        this.btnRemplirSection.setPreferredSize(new Dimension(100,60));
        this.btnRemplirSection.setFont(new Font("Arial", Font.BOLD, 20));

        this.panelObjectif.add(this.btnPiocheObjectif);
        this.panelObjectif.add(scrollPane);

        this.panelGlobal.add(this.lblTable);
        this.panelGlobal.add(this.panelBoutonPioche);
        
        // Panel des pions restants du joueur
        this.panelPion = new JPanel(new GridLayout(2, 2));
        this.panelPion.setBackground(new Color(35, 31, 32));
        JLabel lblTextPion = new JLabel("Nombre de pion : ");
        JLabel lblNbPion = new JLabel(String.valueOf(this.ctrl.getJoueur().getNbPion() + " / " + this.ctrl.getNbPionMax()));
        lblTextPion.setForeground(Color.WHITE);
        lblTextPion.setFont(new Font("Arial", Font.BOLD, 20));
        lblNbPion.setForeground(Color.WHITE);
        lblNbPion.setFont(new Font("Arial", Font.BOLD, 20));
        this.panelPion.add(lblNomJoueur);
        this.panelPion.add(new JLabel());
        this.panelPion.add(lblTextPion);
        this.panelPion.add(lblNbPion);
        lblNbPion.setHorizontalAlignment(SwingConstants.CENTER);
        lblTextPion.setHorizontalAlignment(SwingConstants.CENTER);
        this.panelGlobal.add(this.panelPion);
        this.panelGlobal.add(this.lblObjectif);
        this.panelGlobal.add(this.panelObjectif);
        
        this.add(this.panelGlobal, BorderLayout.CENTER);
        this.add(this.btnRemplirSection, BorderLayout.SOUTH);
    }

    public void refreshTableTrajets()
    {

        //remplr les combobox avec les trajets disponibles 
        this.comboVille1.removeAllItems();
        this.comboVille2.removeAllItems();

        for(int i = 0; i < this.ctrl.getAllNoeuds().size(); i++)
        {
            this.comboVille1.addItem(this.ctrl.getAllNoeuds().get(i).getNom());
            this.comboVille2.addItem(this.ctrl.getAllNoeuds().get(i).getNom());
        }

        this.comboVille1.addActionListener(this);
        this.comboVille2.addActionListener(this);
    }

    public void refreshTableObjectifs()
    {
        this.panelGlobal.remove(this.panelObjectif);
        this.donneesObjectif = new Object[this.ctrl.getJoueur().getTabCarteObjectif().size()][3];
        System.out.println("Objectif : "+this.ctrl.getJoueur().getTabCarteObjectif().size());
        for(int i = 0; i < this.ctrl.getJoueur().getTabCarteObjectif().size(); i++)
        {
            System.out.println("refresh Table Objectif");
            this.donneesObjectif[i][0] = this.ctrl.getJoueur().getTabCarteObjectif().get(i).getNoeud1().getNom();
            this.donneesObjectif[i][1] = this.ctrl.getJoueur().getTabCarteObjectif().get(i).getNoeud2().getNom();
            this.donneesObjectif[i][2] = this.ctrl.getJoueur().getTabCarteObjectif().get(i).getScore();       
            System.out.println(this.donneesObjectif[i][0]); 
        }

        //rend la table objectif non editable
        this.tableObjectif = new JTable(this.donneesObjectif, this.entetesObjectif){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        JScrollPane scrollPane = new JScrollPane(this.tableObjectif);
        this.panelObjectif.removeAll();
        this.panelObjectif.add(this.btnPiocheObjectif);
        this.panelObjectif.add(scrollPane);
        this.panelGlobal.add(this.panelObjectif);
        this.revalidate();
        this.repaint();
    }

    // refresh le panel du nbPion
    public void refreshPanelPion()
    {
        this.panelGlobal.remove(this.panelPion);
        this.panelPion = new JPanel(new GridLayout(2, 2));
        this.panelPion.setBackground(new Color(35, 31, 32));
        JLabel lblTextPion = new JLabel("Nombre de</br> pion : ");
        JLabel lblNbPion = new JLabel(String.valueOf(this.ctrl.getJoueur().getNbPion() + " / " + this.ctrl.getNbPionMax()));

        this.lblNomJoueur.setText(this.ctrl.getJoueur().getNom());
        this.lblNomJoueur.setForeground(this.ctrl.getJoueur().getCouleur());
        this.lblNomJoueur.setFont(new Font("Arial", Font.BOLD, 20));
        this.lblNomJoueur.setHorizontalAlignment(SwingConstants.CENTER);

        lblTextPion.setForeground(Color.WHITE);
        lblTextPion.setFont(new Font("Arial", Font.BOLD, 20));
        lblNbPion.setForeground(Color.WHITE);
        lblNbPion.setFont(new Font("Arial", Font.BOLD, 20));
        this.panelPion.add(this.lblNomJoueur);
        this.panelPion.add(new JLabel());
        this.panelPion.add(lblTextPion);
        this.panelPion.add(lblNbPion);
        lblNbPion.setHorizontalAlignment(SwingConstants.CENTER);
        lblTextPion.setHorizontalAlignment(SwingConstants.CENTER);
        this.panelGlobal.add(this.panelPion, 2);
        this.revalidate();
        this.repaint();
    }

    public void piocherCarteObjectif()
    {
        // On vérifie si la liste des cartes est vide
        if(this.ctrl.getAllCartesObjectifRandom().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Il ne reste plus de cartes objectifs", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(this.action > 0 && this.ctrl.getTabJoueur().length > 1)
        {
            JOptionPane.showMessageDialog(null, "Vous avez déjà effectué une action", "Information", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel(new GridLayout(this.ctrl.getAllCartesObjectifRandom().size(),1));

        // Création des checkbox nécéssaires avec les infos des cartes objectif random
        for(int i = 0; i < this.ctrl.getAllCartesObjectifRandom().size(); i++)
        {
            CarteObjectif ca = this.ctrl.getAllCartesObjectifRandom().get(i);
            JCheckBox cb = new JCheckBox("Ville de départ : " + ca.getNoeud1().getNom() + " - Ville d'arrivée : " + ca.getNoeud2().getNom() + " - Score : " + ca.getScore());
            panel.add(cb);
        }

        // Check if selected 
        boolean select = false;
        int option = JOptionPane.showConfirmDialog(null, panel, "Sélectionner au moins 1 carte objectif", JOptionPane.PLAIN_MESSAGE);
        if (option == JOptionPane.OK_OPTION) 
        {
            // Récupérez les valeurs des JCheckBox sélectionnées dans les components du panel
            Component[] components = panel.getComponents();
            for(int i = 0; i<components.length; i++)
            {
                // Si le component est une CheckBox, je vérifie si elle est selectionné
                if(components[i] instanceof JCheckBox)
                {
                    JCheckBox checkbox = (JCheckBox) components[i];
                    if(checkbox.isSelected())
                    {
                        // Si selectionner, alors je l'ajoute au joueur et je la supprime de toutes les cartes objectifs
                        select = true;
                        this.ctrl.getJoueur().addCarteObjectif(this.ctrl.getAllCartesObjectifRandom().get(i));
                        this.ctrl.getAllCartesObjectifs().remove(this.ctrl.getAllCartesObjectifRandom().get(i));
                    }
                }
            }
        }
        // Si rien n'est selectionné ou qu'on ferme la fenetre, on re affiche
        if(!select)piocherCarteObjectif();
        refreshTableObjectifs();
        
    }

    public void premTourCarteObjectif()
    {
        JPanel panel = new JPanel(new GridLayout(this.ctrl.getAllCartesObjectifRandom().size(),1));

        // Création des checkbox nécéssaires avec les infos des cartes objectif random
        for(int i = 0; i < this.ctrl.getAllCartesObjectifRandom().size(); i++)
        {
            CarteObjectif ca = this.ctrl.getAllCartesObjectifRandom().get(i);
            JCheckBox cb = new JCheckBox("Ville de départ : " + ca.getNoeud1().getNom() + " - Ville d'arrivée : " + ca.getNoeud2().getNom() + " - Score : " + ca.getScore());
            panel.add(cb);
        }

        int nbSelect = 0;
        int option = JOptionPane.showConfirmDialog(null, panel, "Sélectionner au moins 2 carte objectif", JOptionPane.PLAIN_MESSAGE);
        // Check si 2 checkbox uniquement sont selectionner dans le panel quand il clique sur OK, on les ajoute au joueur et on les supprime de la liste des cartes objectifs sinon on re affiche. Si on a plus de 2 checkbox selectionner, on affiche une popup
        if(option == JOptionPane.OK_OPTION)
        {
            Component[] components = panel.getComponents();
            for(int i = 0; i<components.length; i++)
            {
                if(components[i] instanceof JCheckBox)
                {
                    JCheckBox checkbox = (JCheckBox) components[i];
                    if(checkbox.isSelected())
                    {
                        nbSelect++;
                        if(nbSelect > 2)
                        {
                            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas selectionner plus de 2 cartes objectifs", "Erreur", JOptionPane.ERROR_MESSAGE);
                            premTourCarteObjectif();
                            return;
                        }
                        this.ctrl.getJoueur().addCarteObjectif(this.ctrl.getAllCartesObjectifRandom().get(i));
                        this.ctrl.getAllCartesObjectifs().remove(this.ctrl.getAllCartesObjectifRandom().get(i));
                    }
                }
            }
            if(nbSelect < 2)
            {
                JOptionPane.showMessageDialog(null, "Vous devez selectionner au moins 2 cartes objectifs", "Erreur", JOptionPane.ERROR_MESSAGE);
                premTourCarteObjectif();
            }
        }
        else
        {
            premTourCarteObjectif();
        }
        
    }

    public void refreshTablePioche()
    {
        if(this.ctrl.getCartePioche().isEmpty())
        {
            this.cartesTable[0].setVisible(false);
            for(int i = 1; i<this.cartesTable.length; i++)
            {
                if(this.ctrl.getCarteTable().get(i-1) == null)
                {
                    this.cartesTable[i].setVisible(false);
                }
                else
                {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getCarteTable().get(i-1).getNomImage()+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesTable[i].setIcon(new ImageIcon(newimg));
                    this.cartesTable[i].setVisible(true);
                }
            }
            return ;
        }
        if(!this.ctrl.getCarteTable().isEmpty())
        {
            this.cartesTable[0].setVisible(true);

            for (int i = 1; i < this.cartesTable.length; i++) 
            {
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getCarteTable().get(i-1).getNomImage()+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesTable[i].setIcon(new ImageIcon(newimg)); 
                    //this.cartesTable[i].setBorderPainted(false);
                    this.cartesTable[i].setContentAreaFilled(false);
                    //this.cartesTable[i].setFocusPainted(false);
                    //this.cartesTable[i].setOpaque(false);
                    
                    this.cartesTable[i].setVisible(true);
                    
                    
                } catch (Exception e) {
                    // TODO: handle exception
                } 

            }
        }
        refreshTableObjectifs(); 
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        for(int i = 1; i < this.cartesTable.length; i++)
        {
            if(e.getSource() == this.cartesTable[i])
            {
                if(this.ctrl.getTabJoueur().length == 1 )
                {
                    this.ctrl.piocheCarteTable(i, this.ctrl.getJoueur());
                    this.refreshTablePioche();
                    this.ctrl.verifLocoTable();
                    this.refreshTablePioche();
                    return;
                }
                
                if(this.ctrl.getCarteTable().get(i-1).getNomImage().equals("Locomotive") && this.action <1)
                {
                    this.ctrl.piocheCarteTable(i, this.ctrl.getJoueur());
                    this.ctrl.joueurSuivant();
                    this.refreshTablePioche();
                    this.action = 0;
                }
                else
                {  
                    if(this.action == 0)
                    {
                        this.ctrl.piocheCarteTable(i, this.ctrl.getJoueur());
                        this.refreshTablePioche();
                        this.action++;
                    }
                    else
                    {   
                        if(this.ctrl.getCarteTable().get(i-1).getNomImage().equals("Locomotive"))
                        {
                            //faire apparaitre une erreur car une carte locomotive ne peut pas etre pioché
                            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas choisir une locomotive aprés une carte wagon", "Information", JOptionPane.ERROR_MESSAGE);
                        }
                        else if (! this.ctrl.getCarteTable().get(i-1).getNomImage().equals("Locomotive"))
                        {
                            this.ctrl.piocheCarteTable(i, this.ctrl.getJoueur());
                            this.ctrl.joueurSuivant();
                            this.refreshTablePioche();
                            this.action = 0 ;
                        }
                        
                    }    
                }    
                this.ctrl.verifLocoTable();
                this.refreshTablePioche();
            }
        }
        
        if(e.getSource() == this.cartesTable[0])
        {
            if(this.ctrl.getTabJoueur().length == 1 )
            {
                    this.ctrl.piocherCarte(this.ctrl.getJoueur());
                    this.ctrl.verifLocoTable();
                    this.refreshTablePioche();
                    return;
            }
            
            if(action < 1)
           {
                this.ctrl.piocherCarte(this.ctrl.getJoueur());
                this.refreshTablePioche();
                this.action++;
            }
            else
            {
                this.ctrl.piocherCarte(this.ctrl.getJoueur());
                this.ctrl.joueurSuivant();
                this.refreshTablePioche();
                this.action = 0;
           }
        }

        if(e.getSource() == this.btnRemplirSection)
        {
            //afficher une popup pour choisir la section à remplir avec une liste déroulante 
            System.out.println("Remplir section");
            JPanel panel = new JPanel();
            this.ctrl.refreshTabTrajets();
            
            panel.add(this.comboVille1);
            panel.add(this.comboVille2);
            panel.add(this.comboColor);
            //mettre une condition si on clique sur ok ou annuler
            //si on clique sur ok, on récupère les deux villes choisies et on les envoie au controleur
            if(this.action > 0 && this.ctrl.getTabJoueur().length > 1)
            {
                JOptionPane.showMessageDialog(null, "Vous avez déjà effectué une action","Information", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            int result = JOptionPane.showConfirmDialog(null, panel, "Trajet", JOptionPane.OK_CANCEL_OPTION);
            ArrayList<Arete> trajets = (ArrayList<Arete>) this.ctrl.getAllTrajets();
            if (result == JOptionPane.OK_OPTION) {
                // L'utilisateur a appuyé sur "OK"
                // Exécutez le code voulu ici
                for(int i = 0; i <this.ctrl.getAllTrajets().size(); i++)
                { 
                    if(trajets.get(i).getNoeudDepart().getNom().equals(this.comboVille1.getSelectedItem()) && trajets.get(i).getNoeudArrive().getNom().equals(this.comboVille2.getSelectedItem()) || trajets.get(i).getNoeudDepart().getNom().equals(this.comboVille2.getSelectedItem()) && trajets.get(i).getNoeudArrive().getNom().equals(this.comboVille1.getSelectedItem()))
                    {
                        Arete arete = this.ctrl.getAllTrajets().get(i);
                        if(this.ctrl.prendrePossession(arete) == 1)
                        {
                            this.ctrl.getJoueur().decrementeNbPion(arete.getNbVoiture());
                            this.ctrl.joueurSuivant();
                            this.refreshPanelPion();
                            refreshTablePioche();
                            return ;
                        }
                        else if(this.ctrl.prendrePossession(arete) == 2)
                        {
                            JOptionPane.showMessageDialog(null, "L'arrete est déjà prise ", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return ;
                        }
                        else if(this.ctrl.prendrePossession(arete) == 3)
                        {
                            JOptionPane.showMessageDialog(null, "Vous n'avez pas assez de carte de même couleur pour prendre possession de cette arete", "Erreur", JOptionPane.ERROR_MESSAGE);
                            return ;
                        }
                       
                    }
                }
            }
            else {
                // L'utilisateur a appuyé sur "Annuler"
                // Exécutez le code voulu ici
                return;
            }
        }
        if(e.getSource() == this.btnPiocheObjectif)
        {
            if(this.action > 0 && this.ctrl.getTabJoueur().length > 1)
            {
                JOptionPane.showMessageDialog(null, "Vous avez déjà effectué une action","Information", JOptionPane.ERROR_MESSAGE);
                return ;
            }
            if(this.ctrl.getAllCartesObjectifs().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Il n'y a plus de carte objectif dans la pioche","Information", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else
            {
                this.ctrl.getGui().piocherCarteObjectif();
                this.ctrl.joueurSuivant();
            }
        
        }
    }
   
}