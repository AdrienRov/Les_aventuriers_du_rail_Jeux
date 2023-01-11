package src.metier;

public class Noeud 
{
    private String      nom                 ;
    private int         x, y, xNom, yNom    ;

    public Noeud(String nom, int x, int y, int xNom, int yNom)
    {
        this.nom    = nom   ;
        this.x      = x     ;
        this.y      = y     ;
        this.xNom   = xNom  ;
        this.yNom   = yNom  ;
    }    

    public String getNom() 
    {
        return nom;
    }

    public int getX() 
    {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXNom() 
    {
        return xNom;
    }

    public int getYNom() 
    {
        return yNom;
    }

    //to string
    @Override
    public String toString() 
    {
        return "Noeud [nom=" + nom + ", x=" + x + ", y=" + y + ", xNom=" + xNom + ", yNom=" + yNom + "]";
    }
}
