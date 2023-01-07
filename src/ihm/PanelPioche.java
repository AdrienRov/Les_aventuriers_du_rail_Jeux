package src.ihm;

import javax.swing.*;
import javax.swing.table.TableColumn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.GridLayout;
import java.awt.*;
import src.Controleur;
import src.metier.CarteObjectif;

public class PanelPioche extends JPanel implements ActionListener
{
    private Controleur ctrl;

    private JPanel panelBoutonPioche, panelGlobal, panelObjectif;
    private JButton[] cartesTable ;
    private JButton btnPiocheObjectif;
    private JButton btnRemplirSection;
    private JLabel lblTable, lblObjectif;
    private JTable table;
    private Object[][] donneesTrajets = {{"", "", ""}};
    private String[] entetes = {"Départ", "Voiture", "Arrivée", "Validation"};

    private JTable tableObjectif;
    private Object[][] donneesObjectif = {{"", "", ""}};
    private String[] entetesObjectif;
    private TableColumn[] tabColObjectif;

    private JCheckBox checkObjectif1, checkObjectif2, checkObjectif3;

    public PanelPioche(Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.table = new JTable(this.donneesTrajets, this.entetes);
        this.setLayout(new BorderLayout());
        this.panelBoutonPioche = new JPanel();
        this.panelBoutonPioche.setLayout(new GridLayout(3, 3, 10, 10));
        this.panelGlobal = new JPanel();
        this.panelGlobal.setLayout(new GridLayout(4, 1, 1, 1));
        this.panelObjectif = new JPanel();
        this.panelObjectif.setLayout(new GridLayout(1, 2, 1, 1));
        this.tabColObjectif = new TableColumn[3];
        // Création des labels
        this.lblTable = new JLabel("Cartes sur la table : ");
        this.lblObjectif = new JLabel("Liste des objectifs :");

        this.lblTable.setForeground(Color.WHITE);
        this.lblTable.setFont(new Font("Arial", Font.BOLD, 15));
        //centrer le texte lbl table au centre
        this.lblTable.setHorizontalAlignment(SwingConstants.CENTER);

        this.lblObjectif.setForeground(Color.WHITE);
        this.lblObjectif.setFont(new Font("Arial", Font.BOLD, 15));
        this.lblObjectif.setHorizontalAlignment(SwingConstants.CENTER);
        // Création des boutons
        this.btnPiocheObjectif = new JButton();
        this.cartesTable  = new JButton[6];
        this.btnRemplirSection = new JButton("Remplir une section");
        this.btnRemplirSection.addActionListener(this);
        this.btnPiocheObjectif.addActionListener(this);
        
        
        
        for (int i = 0; i < this.cartesTable.length; i++) 
        {
            
            if(i == 0) 
            { 
                this.cartesTable[i] = new JButton("");
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(9)+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesTable[i].setIcon(new ImageIcon(newimg)); 
                    this.cartesTable[i].setBorderPainted(false);
                    this.cartesTable[i].setContentAreaFilled(false);
                    this.cartesTable[i].setFocusPainted(false);
                    this.cartesTable[i].setOpaque(false);
                    
                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e.getMessage());
                } 
            }
            else  
            { 
                //gérer la taille des boutons
                this.cartesTable[i] = new JButton();
                try {
                    ImageIcon img= new ImageIcon( "./images/"+this.ctrl.getAllImages().get(i)+".png");
                    //changer la taille de l'image
                    Image image = img.getImage(); // transform it
                    Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
                    this.cartesTable[i].setIcon(new ImageIcon(newimg)); 
                    this.cartesTable[i].setBorderPainted(false);
                    this.cartesTable[i].setContentAreaFilled(false);
                    this.cartesTable[i].setFocusPainted(false);
                    this.cartesTable[i].setOpaque(false);
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
            this.btnPiocheObjectif.setBorderPainted(false);
            this.btnPiocheObjectif.setContentAreaFilled(false);
            this.btnPiocheObjectif.setFocusPainted(false);
            this.btnPiocheObjectif.setOpaque(false);
            
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
        this.tableObjectif = new JTable(this.donneesObjectif, this.entetesObjectif);
        
        // Mise en place des panels

        for (int i = 0; i < this.cartesTable.length; i++) 
        {
            this.panelBoutonPioche.add(this.cartesTable[i]);
        }
        JScrollPane scrollPane = new JScrollPane(this.tableObjectif);
        this.panelBoutonPioche.setBackground(new Color(35, 31, 32));
        this.panelGlobal.setBackground(new Color(35, 31, 32));
        this.panelObjectif.setBackground(new Color(35, 31, 32));

        this.panelObjectif.add(this.btnPiocheObjectif);
        this.panelObjectif.add(scrollPane);

        this.panelGlobal.add(this.lblTable);
        this.panelGlobal.add(this.panelBoutonPioche);
        this.panelGlobal.add(this.lblObjectif);
        this.panelGlobal.add(this.panelObjectif);
        
        this.add(this.panelGlobal, BorderLayout.CENTER);
        this.add(this.btnRemplirSection, BorderLayout.SOUTH);
    }

    public void refreshTableTrajets()
    {
        this.donneesTrajets = new Object[this.ctrl.getAllTrajets().size()][4];
        for(int i = 0; i < this.ctrl.getAllTrajets().size(); i++)
        {
            this.donneesTrajets[i][0] = this.ctrl.getAllTrajets().get(i).getNoeudDepart().getNom();
            this.donneesTrajets[i][1] = this.ctrl.getAllTrajets().get(i).getNbVoiture();
            this.donneesTrajets[i][2] = this.ctrl.getAllTrajets().get(i).getNoeudArrive().getNom();
        }
        this.table = new JTable(this.donneesTrajets, this.entetes);
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
        this.tableObjectif = new JTable(this.donneesObjectif, this.entetesObjectif);   
        JScrollPane scrollPane = new JScrollPane(this.tableObjectif);
        this.panelObjectif.removeAll();
        this.panelObjectif.add(this.btnPiocheObjectif);
        this.panelObjectif.add(scrollPane);
        this.panelGlobal.add(this.panelObjectif);
        this.revalidate();
        this.repaint();
    }

    public void piocherCarteObjectif()
    {
        Random random = new Random();
        
        int index1 = random.nextInt(this.ctrl.getAllCartesObjectifs().size());
        int index2 = random.nextInt(this.ctrl.getAllCartesObjectifs().size());
        int index3 = random.nextInt(this.ctrl.getAllCartesObjectifs().size());

        // Vérifier si il n'y a pas deux index identiques, on recommence en tirant un nouveau nombre 
        while(index1 == index2 || index1 == index3 || index2 == index3)
        {
            index1 = random.nextInt(this.ctrl.getAllCartesObjectifs().size());
            index2 = random.nextInt(this.ctrl.getAllCartesObjectifs().size());
            index3 = random.nextInt(this.ctrl.getAllCartesObjectifs().size());
        }

        // On récupère les cartes objectifs
        CarteObjectif carteRandom1 = this.ctrl.getAllCartesObjectifs().get(index1);
        CarteObjectif carteRandom2 = this.ctrl.getAllCartesObjectifs().get(index2);
        CarteObjectif carteRandom3 = this.ctrl.getAllCartesObjectifs().get(index3);
        
        // On créer les JCheckBox
        this.checkObjectif1 = new JCheckBox("Ville de départ : " + carteRandom1.getNoeud1().getNom() + " - Ville d'arrivée : " + carteRandom1.getNoeud2().getNom() + " - Score : " + carteRandom1.getScore());
        this.checkObjectif2 = new JCheckBox("Ville de départ : " + carteRandom2.getNoeud1().getNom() + " - Ville d'arrivée : " + carteRandom2.getNoeud2().getNom() + " - Score : " + carteRandom2.getScore());
        this.checkObjectif3 = new JCheckBox("Ville de départ : " + carteRandom3.getNoeud1().getNom() + " - Ville d'arrivée : " + carteRandom3.getNoeud2().getNom() + " - Score : " + carteRandom3.getScore());

        // Créez un JPanel et ajoutez les JCheckBox
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(this.checkObjectif1);
        panel.add(this.checkObjectif2);
        panel.add(this.checkObjectif3);

        // Afficher la boîte de dialogue
        int option = JOptionPane.showConfirmDialog(null, panel, "Sélectionner au moins 1 carte objectif", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) 
        {
            // Récupérez les valeurs des JCheckBox sélectionnées
            if(this.checkObjectif1.isSelected())
            {
                this.ctrl.getJoueur().addCarteObjectif(carteRandom1);
                this.ctrl.getAllCartesObjectifs().remove(carteRandom1);
            }
            if(this.checkObjectif2.isSelected())
            {
                this.ctrl.getJoueur().addCarteObjectif(carteRandom2);
                this.ctrl.getAllCartesObjectifs().remove(carteRandom2);
            }
            if(this.checkObjectif3.isSelected())
            {
                this.ctrl.getJoueur().addCarteObjectif(carteRandom3);
                this.ctrl.getAllCartesObjectifs().remove(carteRandom3);
            }
        }

        this.refreshTableObjectifs();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // TODO Auto-generated method stub
        if(e.getSource() == this.cartesTable[0])
        {
            this.ctrl.jouerManche(0);
        }
        if(e.getSource() == this.btnPiocheObjectif)
        {
            this.ctrl.jouerManche(1);
        }

        if(e.getSource() == this.btnRemplirSection)
        {
            //afficher une popup pour choisir la section à remplir avec une liste déroulante 
            System.out.println("Remplir section");
            JPanel panel = new JPanel();
            this.ctrl.refreshTabTrajets();
            JScrollPane scrollPane = new JScrollPane(this.table);
            panel.add(scrollPane);
            JOptionPane.showMessageDialog(null, panel, "Trajet", JOptionPane.PLAIN_MESSAGE);
        }
    }
}