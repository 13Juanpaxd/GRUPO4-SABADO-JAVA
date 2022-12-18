package modelo;

import java.util.Random;
import javax.swing.JTextField;


public class Sudoku {
//Para iniciar creamos un atributo de tipo int el cual se llama sudoku, creamos otra matriz que será de 9x9, al atributo que se llama sudoku 
 //le pedimos que sea igual a la matriz que ya habiamos creado.
 //llamamos al metodo limpiar sudoku.
 
    
    
    private int sudoku[][];

    public Sudoku() {
        sudoku = new int[9][9];
        limpiarSudoku();
    }

    //Creamos un metodo para resolver el sudoku, dentro de este metodo creamos dos for para recorrer las filas y las columnas de la matriz
    //para el segundo for verificamos si la posición en la que esta el usuario es igual a cero, para probar con cada numero creamos otro for, en el interior
    //de este hicimos una variable de tipo int para verificar que el numero ingresado no se encuentre en una fila. columna o cuadrante.
    //Usamos un if para validar las filas y las columnas , por parametros pasamos las filas  y columas que queremos recorrer y el valor que se quiere ingresa, verificamos con el if
    //si el valor que queremos ingresar ya esta retorna falso, de lo contrario retorna verdadero.
    
    //cuando el sudoku esta resuelto y todas las casillas esten llenas y diferentes de cero, al finalizar los dos for creados retornaria  verdadero
    //cuando al probar con los 9 numeros ninguno de ellos puede ir en esa posicion, es decir todos los numeros ya se encuentran en la fila,columna o cuadrante
    //retorna a falso.
    
    public boolean resolverSudoku() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                if (sudoku[i][j] == 0) {
                    for (int valor = 1; valor <= 9; valor++) {
                        if (validarFila(i, valor) && validarColumna(j, valor) && validarCuadrante(i, j, valor)) {
                            sudoku[i][j] = valor;
                            if (resolverSudoku()) {
                                return true;
                            }
                            sudoku[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    
//Creamos un metodo llamado comprobarsudoku, con dos for recorremos la matriz del sudoku, creamos una variable en donde se guarda el numero de la posicion
// que el usuario se encuentra y esa posicion se coloca en 0, validamos fila, columna y cuadrante estos validan con todo y el numero que el usuario quiere 
 //comprobar y si no es colocado en cero siempre va retornar a falso ya que siempre se encontrara a el mismo.
    
    public boolean comprobarSudoku() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                int aux = sudoku[i][j];
                sudoku[i][j] = 0;
                
 //verificamos si algunos de estos 3 metodos nos retorna a falso, si es asi al sudoku en esa posicion le colocamos el numero que tenia y retornamos falso
 //si estos tres metodos no se cumplen y está todo bien, al sudoku le agregamos el numero que tenia, que se encuenra en la variable aux, si se recorre 
 //toda la matriz del sudoku y no retorna a falso entonces esta correcto y retorna verdadero.
                if (!validarFila(i, aux) || !validarColumna(j, aux) || !validarCuadrante(i, j, aux)) {
                    sudoku[i][j]=aux;
                    return false;

                }
                sudoku[i][j]=aux;
            }
        }
        return true;
    }
//Creamos un metodo que nos retonrna un booleano, llamado validar cuadrante, para validra este cuadrante necesitamos saber en cual cuadrante 
//estamos, para esto creamos otro metodo que nos retorna un entero, llamado subcuadrante actual
    //por parametros pasamos la fila y la columna en la que el usuario se encuentra, ademas del valor que se quiere ingresar, creamos un entero
    //llamado posI y con el metodo de subcuadrante obtenemos la ultima posicion + 1, por parametro le pasamos la fila de igual manera lo hacemos con 
    //la posicion j y llamamos al metodo subcuadrante actual y le pasamos la columna, de esta manera ya estarian las posiciones finales +1
    
    public boolean validarCuadrante(int i, int j, int valor) {
        int posI = subCuadranteActual(i);
        int posJ = subCuadranteActual(j);
        
//Creamos dos for para recorrer las filas columnas de este cuadrante.
//para recorrer las filas iniciamos desde la posicion I que tendria la posicion final +1 a esto se le resta  3, esto nos daria la posicion inicial
//termina hasta que la variable k sea menor a la posicion I.
        for (int k = posI - 3; k < posI; k++) {
            for (int l = posJ - 3; l < posJ; l++) {
                if (sudoku[k][l] == valor) {
                    return false;
                }
            }
        }
        return true;
    }
    
//En este metodo colocamos todas las posiciones del sudoku en cero, volvemos al constructor instanseamos la matriz y llamamos al metodo limpiar sudoku 
    public void limpiarSudoku() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                sudoku[i][j] = 0;
            }
        }
    }
    
//Creamos un metodo que nos retorna un entero llamado subcuadrante actual, por parametros le pasamos  la posicion ya que este servira para filas y columnas
 //verificamos si la posicion es menor o igual a 2, 2 es la posicion final del primer cuadrante, si esto se cumple retorna  a la posicion final + 1 que 
    //seria 3 , si no se cumple esta  condicion verificamos si la posicion es menor o igual a 5, 5 es la posicion final del segundo cuadrante, entonces retornamos 
    //esa posicion + 1, que seria 6, el cuadrante 3 retorna a 9.
    public int subCuadranteActual(int pos) {
        if (pos <= 2) {
            return 3;
        } else if (pos <= 5) {
            return 6;
        } else {
            return 9;
        }
    }
    
// Creamos un metodo que nos retorna a un booleano, llamado validar fila y por parametros le pasamos la fila queremos correr y el valor que queremos ingresar
//con un for verificamos si el valor se encuentra en dicha fila y si es asi retorna falso porque no se puede ingresar un valor que ya este en la fila, de 
// lo contrario retorna verdadero.
    
 
    public boolean validarFila(int i, int valor) {
        for (int j = 0; j < sudoku[i].length; j++) {
            if (sudoku[i][j] == valor) {
                return false;
            }
        }
        return true;
    }
    
//Creamos un metodo que nos retorna un booleano, llamado validar columna porparametros recibimos la columna que queremos recorrer y el valor que 
//queremos ingresar al recorrer la columa verificamos si el valor que queremos ingresar esta en alguna posicion retorna falso,de lo contrario retorna verdadero
    
    public boolean validarColumna(int j, int valor) {
        for (int i = 0; i < sudoku.length; i++) {
            if (sudoku[i][j] == valor) {
                return false;
            }
        }
        return true;
    }
    
    //Creamos un metodo que nos retorna un vacio, llamado mostrar sudoku, en el interior llamamos al metodo resolverSudoku, luego recorrimos el sudoku 
    //y mostramos los valores de cada una de sus posiciones, para que se viera mas ordenado agregamos un guion entre los numeros, menos a las ultimas 
    //posiciones de cada fila, finalizando el segundo for agregamos un salto de linea.
    public void mostrarSudoku() {
        resolverSudoku();
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                System.out.print(sudoku[i][j]);
                if (!(j == sudoku[0].length - 1)) {
                    System.out.print(" - ");
                }
            }
            System.out.println();
        }
    }
  //Creamos un metodo llamado generar sudoku, en este metodo limpiamos el sudoku y creamos un objeto de la clase random, generamos numeros entre el 1 y 9
 //utilizando la variable int, usamos un for para recorrer las filas y columnas del primer cuadrante, creamos una variable de tipo int , a esta variable
 //le asignamos un numero aleatorio entre 1 y 9, con un if verificamos  si el numero ingresado es valido para ingresar en la primer cuadrante, si el numero 
 //ya se encuentra en una posicion de la cuadrante a la variable j le restamos 1, para que se vuelva a generar hicimos lo mismo para los 3 cuadrantes .
    
    public void generarSudoku(int nivel) {
        limpiarSudoku();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = random.nextInt(9) + 1;
                if (validarCuadrante(i, j, num)) {
                    sudoku[i][j] = num;
                } else {
                    j--;
                }
            }
        }
//Camiamos el recorrido del cuadrante, comenzara en la posicion 3 y terminara cuando i sea menor que 6, en las columnas comenzara en posicion 3 
//y termnara hasta que j sea menor a 6.
        for (int i = 3; i < 6; i++) {
            for (int j = 3; j < 6; j++) {
                int num = random.nextInt(9) + 1;
                if (validarCuadrante(i, j, num)) {
                    sudoku[i][j] = num;
                } else {
                    j--;
                }
            }
        }
//Cambiamos el recorrido del cuadrante, comenzara en la posicion 6 y terminara hasta que i sea menor a 9, en las columnas empezara en posicion 6 y 
// terminara hasta que la variable j sea menor a 9.
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                int num = random.nextInt(9) + 1;
                if (validarCuadrante(i, j, num)) {
                    sudoku[i][j] = num;
                } else {
                    j--;
                }
            }
        }
        
        
       resolverSudoku();
        //Llamamos al metodo resolver sudoku, utilizamos dos for para recorrer las filas y las columnas de la matriz del sudoku, creando una variablle
        //llamada aux en donde se almacena la posicion de la columa, creamos una variable de tipo int llamada rand en donde almacenamos el numero random 
        // que se genere
        
        //recibimos una variable de tipo int llamada nivel, hacemos que se generen nuemros aleatorios entre 0 y el nivel que le indiquemosn+ 1, a j le agregamos el
        // numero random que se genero , con un for recorremos la posicion actual hasta que k sea menor a j ya que esta tiene la posicion actual mas el numero random que se 
        //generó, tambien verificamos que la variable k no sobrepase las filas del sudoku, a todas las posicines que recorremos segun el numero random que se 
        //genere las colocamos  en 0.    
        
        //recibimos una variable de tipo int llamada nivel, hacemos que se generen nuemros aleatorios entre 0 y el nivel que le indiquemosn+ 1, a j le agregamos el
        // numero random que se genero.
        
          for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                int aux = j;
                int rand = random.nextInt(nivel + 1);
                j += rand;
                for (int k = aux; k < j && k < sudoku.length; k++) {
                    sudoku[i][k] = 0;
                }
            }
        }

    }

    public int[][] getSudoku() {
        return sudoku;
    }

    public void setSudoku(int[][] sudoku) {
        this.sudoku = sudoku;
    }

}