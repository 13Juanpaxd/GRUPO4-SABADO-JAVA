package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import modelo.Sudoku;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TableroSudoku extends JPanel {

    private JTextField[][] listaTxt;
    private int txtAncho;
    private int txtAltura;
    private int txtMargen;
    private int txtTamañoLetra;
    private Color panelBackground;
    private Color txtBackground1;
    private Color txtForeground1;
    private Color txtBackground2;
    private Color txtForeground2;
    private Color txtBackground3;
    private Color txtForeground3;
    private Color txtBackground4;
    private Color txtForeground4;

    private Sudoku sudoku;
    private ArrayList<JTextField> listaTxtAux;
    private ArrayList<JTextField> listaTxtGenerados;
    public JTextField txtSelected;

    public TableroSudoku() {
        iniciarComponentes();
    }

    public void iniciarComponentes() {
        listaTxt = new JTextField[9][9];
        txtAncho = 35;
        txtAltura = 36;
        txtMargen = 4;
        txtTamañoLetra = 27;
        panelBackground = Color.BLACK;
        txtBackground1 = Color.WHITE;
        txtForeground1 = Color.BLACK;
        txtBackground2 = Color.WHITE;
        txtForeground2 = Color.BLACK;
        txtBackground3 = Color.WHITE;
        txtForeground3 = Color.BLACK;
        txtBackground4 = Color.RED;
        txtForeground4 = Color.WHITE;
        sudoku = new Sudoku();
        listaTxtAux = new ArrayList<>();
        listaTxtGenerados = new ArrayList<>();
        txtSelected = new JTextField();
    }
//Creamos un metodo llamado crear sudoku, el layout lo colocamos en null y con el metodo Size le asignamos el ancho y la altura del panel
    //el ancho del panel sera el ancho de cada uno de los j txt multiplicado por 9,tenemos 4 margenes entonces le sumamos txt Margen x4
    //multiplicamos la altura de cada j txt por 9, le sumamos txt Margen x4, le assignamos un color al panel con el metodo setBackground.
    public void crearSudoku() {
        this.setLayout(null);
        this.setSize(txtAncho * 9 + (txtMargen * 4), txtAltura * 9 + (txtMargen * 4));
        this.setBackground(panelBackground);
        crearCamposTxt();
    }
//Creamos un metodo llamado crearCampos en el interior creamos  dos variables para las posiciones x y y, estas toman el valor del atributo txt margen,
    //ya que ese sera el punto inicial
    //con dos for recorremos las filas y columnas de la lista txt en el interior creamos un objeto de la clase j txt llamado txt.
    public void crearCamposTxt() {
        int x = txtMargen;
        int y = txtMargen;

        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                JTextField txt = new JTextField();
                this.add(txt);
                
    //con el metodo setBounds le asignamos una posicion estas posiciones las tenemos en las varibales x y y tambien un ancho y una altura 
    //usamos background y foreground para los colres de las letras y el fondo
    //creamos un objeto de la clse llamado font y por prametros pasamos el nombre, estilo y tamaño de la letra.
    //hacemos que cada vez que se cree un txt a la variable x le sumamos el ancho de cada uno de los txt.
    //decimos que la variable x sea igual a txtMargen y al  y le sumamos la altura de los txt.
    // a los txt les decimos que no sean editables
    //asignamos borde a los txt
    ///agregamos margenes cada 3 casillas.
                txt.setBounds(x, y, txtAncho, txtAltura);
                txt.setBackground(txtBackground1);
                txt.setForeground(txtForeground1);
                txt.setFont(new Font("Montserrat", Font.BOLD, txtTamañoLetra));
                txt.setEditable(false);
                txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                txt.setBorder(BorderFactory.createLineBorder(panelBackground, 1));
                txt.setVisible(true);
                x += txtAncho;
                if ((j + 1) % 3 == 0) {
                    x += txtMargen;
                }
                listaTxt[i][j] = txt;
                generarEventos(txt);
            }
            x = txtMargen;
            y += txtAltura;
            if ((i + 1) % 3 == 0) {
                y += txtMargen;
            }
        }

    }

    public boolean txtGenerado(JTextField txt) {
        for (JTextField jTxt : listaTxtGenerados) {
            if (txt == jTxt) {
                return true;
            }
        }
        return false;
    } 

    public void generarEventos(JTextField txt) {
        MouseListener evento = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed(txt);
                txtSelected = txt;

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        KeyListener eventoTecla = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (txtGenerado(txt)) {
                    return;
                } else {
                    if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        txt.setText("");
                    }

                    if (e.getKeyChar() >= 49 && e.getKeyChar() <= 57) {
                        txt.setText(String.valueOf(e.getKeyChar()));
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        };

        txt.addMouseListener(evento);
        txt.addKeyListener(eventoTecla);
    }
//Creamos un metodo llamado pressed , y por parametros recibimos un objeto de la clse j txt
    //recorremos la lista por sus filas y sus columnas, verificamos si la posicion en la que nos encontramos es igual al
    //txt que pasamos por parametro, si es asi cambiamos el color de fondo y letra, con los txt foreground  y background 
    //le agregamos un borde al txt seleccionado, finalmemte retornamos.
    //creamos un for para recorrer todos los txt de las columnas en las que nos encontramos
    //creamos un for para recorrer todos los txt de las filas en las que nos encontramos 
    public void pressed(JTextField txt) {

        for (JTextField jTxt : listaTxtAux) {
            jTxt.setBackground(txtBackground1);
            jTxt.setForeground(txtForeground1);
            jTxt.setBorder(BorderFactory.createLineBorder(panelBackground, 1));
        }
        listaTxtAux.clear();

        for (JTextField jTxt : listaTxtGenerados) {
            jTxt.setBackground(txtBackground4);
            jTxt.setForeground(txtForeground4);
        }
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                if (listaTxt[i][j] == txt) {
                    for (int k = 0; k < listaTxt.length; k++) {
                        listaTxt[k][j].setBackground(txtBackground2); 
                        listaTxtAux.add(listaTxt[k][j]);
                    }
                    for (int k = 0; k < listaTxt[0].length; k++) {
                        listaTxt[i][k].setBackground(txtBackground2);  
                        listaTxtAux.add(listaTxt[i][k]);

                    }
        //creamos dos variables para obtener la ultima posicion del cudrante en el que nos encontramos +1
        //con dos for recorremos el cudrante, las columnas van de la posicion i - 3 hasta que la variable k 
        //sea menor que la posicion i 
        //las filas van desde la posicion j - 3 hasta que la variable L sea menor a la posicion j.
        //
                    int posI = sudoku.subCuadranteActual(i);
                    int posJ = sudoku.subCuadranteActual(j);
                    for (int k = posI - 3; k < posI; k++) {
                        for (int l = posJ - 3; l < posJ; l++) {
                            listaTxt[k][l].setBackground(txtBackground2);
                            // listaTxt[k][l].setForeground(txtForeground2);
                            listaTxtAux.add(listaTxt[k][l]);
                        }
                    }

                    listaTxt[i][j].setBackground(txtBackground3);
                    listaTxt[i][j].setForeground(txtForeground3);
                    listaTxt[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                    return;
                }

            }
        }

    }
//Creamos un metodo llamado generar sudoku
    //creamos una matriz llamada sudoku generado en donde vamos a recibir el sudoku que previamente se genero
    //con dos for recorremos las filas y las columnas de lista
    //verificamos si el sudoku generado en esa posicion es deiferene de cero, si es asi al txt de la lista en esa posicion le agregamos el valor que
    //contiene el sudoku generado en esa posicion
    
    public void generarSudoku(int nivel) {
        limpiarTxt();
        sudoku.generarSudoku(nivel);
        int[][] sudokuGenerado = sudoku.getSudoku();
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                if (sudokuGenerado[i][j] != 0) {
                    listaTxt[i][j].setText(String.valueOf(sudokuGenerado[i][j]));
                    listaTxt[i][j].setBackground(txtBackground4);
                    listaTxt[i][j].setForeground(txtForeground4);
                    listaTxtGenerados.add(listaTxt[i][j]);
                }
            }
        }
    }

//Creamos un metodo llamado crear sudoku personalizado que nos retorne un booleano 
    //creamos una variable de tipo int en donde se recibe el numero que contenga el txt en el que nos encontramos 
    //con if verificamos si ese numero ya esta en la fila, columa o cuadrante 
    //limpiamos el sudoku y recorremos las filas y las columnas de la lista
    //verficiamos si el txt en el que nos encontramos no esta vacio, si no lo esta entonces agregamos ese numero 
    //a la matriz del sudoku 
    //le agregamos el borde al txt ya generados 
    
    public boolean crearSudokuPersonalizado() {
        sudoku.limpiarSudoku();
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                if (!(listaTxt[i][j].getText().isEmpty())) {

                    int num = Integer.valueOf(listaTxt[i][j].getText());
                    if (sudoku.validarColumna(j, num) && sudoku.validarFila(i, num) && sudoku.validarCuadrante(i, j, num)) {
                        sudoku.getSudoku()[i][j] = num;
                        listaTxt[i][j].setBackground(txtBackground4);
                        listaTxt[i][j].setForeground(txtForeground4);
                        listaTxt[i][j].setBorder(BorderFactory.createLineBorder(panelBackground, 1));
                        listaTxtGenerados.add(listaTxt[i][j]);
                    } else {
                        listaTxtGenerados.clear();
                        JOptionPane.showMessageDialog(null, "Sudoku Incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }

                } else {
                    listaTxt[i][j].setBackground(txtBackground1);
                    listaTxt[i][j].setForeground(txtForeground1);
                    listaTxt[i][j].setBorder(BorderFactory.createLineBorder(panelBackground, 1));
                }
            }
        }
        return true;

    }
//Creamos un metodo limpiartxt recorremos las filas y las columnas de la lista txt 
    //a todos los txt los colocamos vacios.
    public void limpiarTxt() {
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                listaTxt[i][j].setText("");
                listaTxt[i][j].setBackground(txtBackground1);
                listaTxt[i][j].setForeground(txtForeground1);
        //pedimos que cuando limpie los botones los regrese a su borde inicial.   
                listaTxt[i][j].setBorder(BorderFactory.createLineBorder(panelBackground, 1));

            }
        }
        listaTxtGenerados.clear();
    }
//Creamos un metodo llamado limpiar, con dos for recooremos las filas y columnas de la lista txt creamos una variable de tipo boolean
    // y lo inicializamos en falso
    //con un for recorremos la lista de los txt generados
    //con un condicional verificamos si el txt en el que  nos encontramos forma parte de la lista de los txt generados, si es asi la variable
    //b es verdadero y terminamos con un break
    public void limpiar() {
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {

                boolean b = false;
                for (JTextField txt : listaTxtGenerados) {
                    if (listaTxt[i][j] == txt) {
                        b = true;
                        break;
                    }
                }
//verificamos si la variable b sigue en falso.
                if (!b) {
                    listaTxt[i][j].setText("");
                }

            }
        }
    }
    //Creamos un metodo llamado comprobar
    //creamos una matriz de tipo int de 9x9 
    //con dos for recorremos las filas y las columnas  de la lista txt 
    //verificamos si hay algun txt que se encuentra vacio 
    //si es asi se muestra un mensaje 
    //si esta completo se muestra otro mensaje
    public void comprobar() {
        int sudo[][] = new int[9][9];
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                if (listaTxt[i][j].getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Sudoku incompleto", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    sudo[i][j] = Integer.parseInt(listaTxt[i][j].getText());
                }
            }
        }
        sudoku.setSudoku(sudo);
        if (sudoku.comprobarSudoku()) {
            JOptionPane.showMessageDialog(null, "Sudoku correcto", "Sudoku", JOptionPane.INFORMATION_MESSAGE);
            this.horaInicial();
        } else {
            JOptionPane.showMessageDialog(null, "No hay solución", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    public void resolver(){
        sudoku.limpiarSudoku();
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                for (JTextField txt:listaTxtGenerados) {
                    if(txt==listaTxt[i][j]){
                        sudoku.getSudoku()[i][j]=Integer.parseInt(txt.getText());
                    }
                }
            }
        }
        
        if(sudoku.resolverSudoku()){
            for (int i = 0; i < listaTxt.length; i++) {
                for (int j = 0; j < listaTxt[0].length; j++) {
                    listaTxt[i][j].setText(String.valueOf(sudoku.getSudoku()[i][j]));
                }
            }
        }else{
            JOptionPane.showMessageDialog(null,"No hay solución","Error",JOptionPane.ERROR_MESSAGE);
        }
        
    }

    
    public void horaInicial(){
    DateFormat dateFormat = new SimpleDateFormat("HH"); //Para obtener la hora del sistema
    DateFormat dateFormato = new SimpleDateFormat("mm");//Para obtener los minutos del sistema
 
        String hora = dateFormat.format(new Date());    //asignamos la hora a la variable hora
        String minuto = dateFormato.format(new Date()); //asignamos los minutos a la variable minuto
        
        int horainicio = Integer.parseInt(hora);    //Convertimos la hora de inicio a int
        int minutoinicio = Integer.parseInt(minuto);//convertimos los minutos de inicio a int

        horaFinal(horainicio, minutoinicio);
       
    }
    public String horaFinal(int horainicio, int minutoinicio){
        int tiempoh = 0;
        int tiempom = 0;
        String total = "";
        DateFormat dateFormat = new SimpleDateFormat("HH"); //Para obtener la hora del sistema
        DateFormat dateFormato = new SimpleDateFormat("mm");//Para obtener los minutos del sistema
        
        String horaf = dateFormat.format(new Date());   //asignamos la hora de final a la variable horaf
        String minutof = dateFormato.format(new Date());//asignamos los minutos de final a la variable minutof
        
        int horafinal = Integer.parseInt(horaf);    //Convertimos la hora de final a int
        int minutofinal = Integer.parseInt(minutof);//convertimos los minutos de final a int
        
        tiempoh=horainicio-horafinal;
        tiempom=minutoinicio-minutofinal;
        
        total="Duracion total: "+tiempoh+":"+tiempom+"";
        System.out.println(total);
        return total;
    }
    
    

    public Color getTxtBackground4() {
        return txtBackground4;
    }

    public void setTxtBackground4(Color txtBackground4) {
        this.txtBackground4 = txtBackground4;
    }

    public Color getTxtForeground4() {
        return txtForeground4;
    }

    public void setTxtForeground4(Color txtForeground4) {
        this.txtForeground4 = txtForeground4;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public ArrayList<JTextField> getListaTxtAux() {
        return listaTxtAux;
    }

    public void setListaTxtAux(ArrayList<JTextField> listaTxtAux) {
        this.listaTxtAux = listaTxtAux;
    }

    public JTextField[][] getListaTxt() {
        return listaTxt;
    }

    public void setListaTxt(JTextField[][] listaTxt) {
        this.listaTxt = listaTxt;
    }

    public int getTxtAncho() {
        return txtAncho;
    }

    public void setTxtAncho(int txtAncho) {
        this.txtAncho = txtAncho;
    }

    public int getTxtAltura() {
        return txtAltura;
    }

    public void setTxtAltura(int txtAltura) {
        this.txtAltura = txtAltura;
    }

    public int getTxtMargen() {
        return txtMargen;
    }

    public void setTxtMargen(int txtMargen) {
        this.txtMargen = txtMargen;
    }

    public int getTxtTamañoLetra() {
        return txtTamañoLetra;
    }

    public void setTxtTamañoLetra(int txtTamañoLetra) {
        this.txtTamañoLetra = txtTamañoLetra;
    }

    public Color getPanelBackground() {
        return panelBackground;
    }

    public void setPanelBackground(Color panelBackground) {
        this.panelBackground = panelBackground;
    }

    public Color getTxtBackground1() {
        return txtBackground1;
    }

    public void setTxtBackground1(Color txtBackground1) {
        this.txtBackground1 = txtBackground1;
    }

    public Color getTxtForeground1() {
        return txtForeground1;
    }

    public void setTxtForeground1(Color txtForeground1) {
        this.txtForeground1 = txtForeground1;
    }

    public Color getTxtBackground2() {
        return txtBackground2;
    }

    public void setTxtBackground2(Color txtBackground2) {
        this.txtBackground2 = txtBackground2;
    }

    public Color getTxtForeground2() {
        return txtForeground2;
    }

    public void setTxtForeground2(Color txtForeground2) {
        this.txtForeground2 = txtForeground2;
    }

    public Color getTxtBackground3() {
        return txtBackground3;
    }

    public void setTxtBackground3(Color txtBackground3) {
        this.txtBackground3 = txtBackground3;
    }

    public Color getTxtForeground3() {
        return txtForeground3;
    }

    public void setTxtForeground3(Color txtForeground3) {
        this.txtForeground3 = txtForeground3;
    }

}