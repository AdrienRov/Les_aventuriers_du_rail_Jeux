package src.ihm;

import javax.swing.table.AbstractTableModel;
import javax.swing.JRadioButton;
public class ModelTableau extends AbstractTableModel{

    private Object[][] donneesTrajets = new Object[0][0];
    private String[] entetes = {"", "", "", ""};

    public ModelTableau(Object[][] donneesTrajets, String[] entetes) {
        // TODO Auto-generated constructor stub
        this.donneesTrajets = donneesTrajets;
        this.entetes = entetes;
    }
    public void setValueAt(Object value, int row, int col) {
        //On interdit la modification sur certaines colonnes !
        this.donneesTrajets[row][col] = value;
      }

    public String getColumnName(int col) {
        // TODO Auto-generated method stub
        return this.entetes[col];
    }

    public boolean isCellEditable(int row, int col){
        return true;
      }
    
    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return this.entetes.length;
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return this.donneesTrajets.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if (col == 3) {
            return ((JRadioButton)this.donneesTrajets[row][col]).getText();
        } else {
            return this.donneesTrajets[row][col];
        }
    }
    public Class getColumnClass(int col){
        //On retourne le type de la cellule à la colonne demandée
        //On se moque de la ligne puisque les types de données sont les mêmes quelle que soit la ligne
        //On choisit donc la première ligne
        return this.donneesTrajets[0][col].getClass();
      }
    
}