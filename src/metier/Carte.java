package src.metier;

import java.awt.Color;


public class Carte 
{

    private String nomCarte;
    private String nomImage;
    private Color color;

    public Carte(String nomCarte, String nomImage)
    {
        this.nomCarte = nomCarte;
        this.nomImage = nomImage;
        this.color = setColor(nomCarte);
    }

    private Color setColor(String nomCarte)
    {
        Color color;
        try {
            java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(nomCarte);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }
        return color;
    }
    public Color getCouleur()
    {
        return color;
    }

    public String getNomCarte() 
    {
        return nomCarte;
    }

    public String getNomImage() 
    {
        return nomImage;
    }

    public String toString() 
    {
        return "\nCarte [nomCarte=" + nomCarte + ", nomImage=" + nomImage + "]\n";
    }
}
