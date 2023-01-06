package src.metier;
import java.awt.Color;
import java.util.ArrayList;

public class Joueur {
    private String nom;
    private int score;

    private ArrayList<Color> main;



    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.main = new ArrayList<Color>();
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void removeScore(int score) {
        this.score -= score;
    }

    public ArrayList<Color> getMain() {
        return main;
    }

    public void setMain(Color carte) {
        this.main.add(carte);
    }

    // get couleur carte
    

    public void removeMain(Color carte) {
        this.main.remove(carte);
    }

    public void addMain(Color carte) {
        this.main.add(carte);
    }

    public void removeMain(int index) {
        this.main.remove(index);
    }

}
