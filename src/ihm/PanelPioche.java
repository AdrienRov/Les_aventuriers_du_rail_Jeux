package src.ihm;

import javax.swing.*;
import java.awt.*;
import src.Controleur;

public class PanelPioche extends JPanel
{
    private Controleur ctrl;

    private JPanel panelTable;
    private JPanel panel1Nord;
    private JButton[] cartesTable ;
    

    private JButton suivant, ajouterWagons;

    public PanelPioche(Controleur ctrl)
    {

        this.ctrl = ctrl;
        this.panelTable = new JPanel();

        this.panel1Nord.setLayout(new GridLayout(2, 1, 10, 10));

        this.cartesTable  = new JButton[6];

        JButton[] boutons = {suivant, ajouterWagons};
        for (int i = 0; i < boutons.length; i++) {
            boutons[i].setBackground(Color.GRAY);
            boutons[i].setForeground(Color.WHITE);
            boutons[i].setFont(new Font("Arial", Font.BOLD, 12));
            boutons[i].setBorderPainted(false);
            boutons[i].setFocusPainted(false);
            boutons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            boutons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
         
        }

        for (int i = 0; i < cartesTable.length; i++) 
        {
            this.panelTable.add(cartesTable[i]);
        }
        this.panel1Nord.add(boutons[0]);
        this.panel1Nord.add(new JLabel("Table :"));

        this.add(panel1Nord, BorderLayout.NORTH);
        this.add(panelTable, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PanelPioche");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new PanelPioche(null));
        frame.pack();
        frame.setVisible(true);
    }
}