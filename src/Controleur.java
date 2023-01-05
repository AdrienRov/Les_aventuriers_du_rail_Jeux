package src;

import src.ihm.Gui;

public class Controleur 
{
    private Gui gui;

    public Controleur() 
    {
        this.gui = new Gui(this);
    }

    public void afficherPanelJeu()
    {
        this.gui.afficherPanelJeu();
    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
    
}
