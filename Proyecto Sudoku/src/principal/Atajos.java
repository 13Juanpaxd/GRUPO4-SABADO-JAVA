package principal;

import java.awt.TextArea;
import javax.swing.JOptionPane;

public class Atajos {
    public int RecibeInt(String mensaje){
        return Integer.parseInt(JOptionPane.showInputDialog(mensaje));
    }
    public float RecibeFloat(String mensaje){
        return Float.parseFloat(JOptionPane.showInputDialog(mensaje));
    }
    public char RecibeChar (String mensaje){
        return JOptionPane.showInputDialog(mensaje).toUpperCase().charAt(0);
    }
    public String recibeString(String mensaje){
        return JOptionPane.showInputDialog(mensaje);
    }
    public void imprimeMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public void imprimeMensaje(TextArea mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }
}
