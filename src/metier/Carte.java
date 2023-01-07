package src.metier;

public class Carte 
{

    private String nomCarte;
    private String nomImage;

    public Carte(String nomCarte, String nomImage)
    {
        this.nomCarte = nomCarte;
        this.nomImage = nomImage;
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
