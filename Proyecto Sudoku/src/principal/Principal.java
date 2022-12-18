package principal;

import javax.swing.JOptionPane;
import modelo.Sudoku;
import vista.FormSudoku;


public class Principal {

    public static void main(String[] args) {
        FormSudoku sudoku = new FormSudoku();
        char opcion = ' ';
        Atajos A = new Atajos();
        clsCliente menu = new clsCliente();
        clsCliente[] vectCliente = new clsCliente[opcion];
          
        String[][] matriz = new String[0][0];
        
        do {
            opcion = A.RecibeChar("Seleccione una opcion"
                    + "\nA. Abrir Sudoku"
                    + "\nB. Agregar Usuario"
                    + "\nC. Buscar Usuario"
                    + "\nS. Salir"
                    + "");
            switch (opcion) {
                case 'A':
                    sudoku.setVisible(true);
                    break;
                case 'B':
                    menu.crearVector();
                    menu.agregarCliente (vectCliente, 0);              
                    break;

                case 'C':
                    menu.obtenerInfoCliente(vectCliente, 0);
                    break;
                case 'S':
                    break;
                default:
                    break;
            }
        } while (opcion != 'S' & opcion != 'A');
        
        
    }
}
