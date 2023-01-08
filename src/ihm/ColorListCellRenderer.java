package src.ihm;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import java.awt.Graphics;

public class ColorListCellRenderer extends DefaultListCellRenderer 
{

    private Color color;

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.setBackground((Color) value);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setOpaque(true);

        JLabel c = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        c.setText("  zdadzd  ");  
        c.setBackground((Color) value);
        c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        c.setForeground(Color.BLACK);
        c.setOpaque(true);
        color = (Color) value;
        return c;
    }
    @Override
    public void paint(Graphics g) {
        setBackground(this.color);
        setForeground(this.color);
        super.paint(g);
    }
  }
