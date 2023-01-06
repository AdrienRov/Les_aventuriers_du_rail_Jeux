package src.ihm;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import src.Controleur;

public class PanelPioche extends JPanel
{
    private Controleur ctrl;

    private JPanel panelBoutonPioche, panel1Nord, panelSud;
    private JButton[] cartesTable ;
    private JButton btnRemplirSection;

    public PanelPioche(Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.setLayout(new BorderLayout());
        this.panelBoutonPioche = new JPanel();
        this.panelBoutonPioche.setLayout(new GridLayout(3, 2, 10, 10));
        //modifier la taille des boutons dans un panel avec un gridlayout 

        this.panel1Nord = new JPanel();
        
        this.panel1Nord.setLayout(new GridLayout(2, 1, 10, 10));

        this.cartesTable  = new JButton[6];
        this.btnRemplirSection = new JButton("Remplir une section");

        
        for (int i = 0; i < this.cartesTable.length; i++) 
        {
            if(i == 0) 
            { 
                this.cartesTable[i] = new JButton("Pioche : nombre de carte restante");
            }
            else 
            { 
                //gérer la taille des boutons
                this.cartesTable[i] = new JButton("CarteTable" + i);
            }
        }

        for (int i = 0; i < this.cartesTable.length; i++) 
        {
            this.panelBoutonPioche.add(this.cartesTable[i]);
        }
        
        this.panel1Nord.add(new JLabel("Table :"));

        this.add(this.panel1Nord, BorderLayout.NORTH);
        this.add(this.panelBoutonPioche, BorderLayout.CENTER);
        this.add(this.btnRemplirSection, BorderLayout.SOUTH);
    }
}