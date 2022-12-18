package principal;

import java.awt.TextArea;

    public class clsCliente {
    private int fechaNacimiento = 0 ; 
    private String identificacion = "";
    private String nombreCompleto = "";
    private char sexo = ' '; //I intersex, M Mujer, H hombre
    private String telefono = "";
    private String  Residencia = "" ; 
   
  

    public clsCliente(String identificacion, String nombreCompleto, char sexo, String telefono, String Residencia, int fechanacimiento) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.sexo = sexo;
        this.telefono = telefono;
        this.Residencia = Residencia ; 
        this. fechaNacimiento =  fechanacimiento ; 
        
    }

    public clsCliente() {
    }

    public clsCliente(String identificacion, String nombreCompleto, char sexo, String telefono, String Residencia) {
        this.identificacion = identificacion;
        this.nombreCompleto = nombreCompleto;
        this.sexo = sexo;
        this.telefono = telefono;
        
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public char getSexo() {
        return sexo;
    }

    public String getSexoString() {
        switch (sexo) {
            case 'I':
                return "Intersexo";
            case 'H':
                return "Hombre";
            case 'M':
                return "Mujer";
            default:
                return "";
        }
    }

    public void setSexo(char sexo) {
        if (sexo != 'H' && sexo != 'M' && sexo != 'I') {
            this.sexo = ' ';
        } else {
            this.sexo = sexo;
        }
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    @Override
    public String toString() {
        return identificacion + "\t" + nombreCompleto + "\t" + getSexoString() + "\t" + telefono; 
    }
public String getResidencia () {
    return Residencia ; 
}
     
public void setResidencia (String Residencia) {
    this.Residencia = Residencia ; 
        
}

public int getfechaNacimiento () {
    return fechaNacimiento ; 
}
        
 public void setfechaNacimiento () { 
     this.fechaNacimiento = fechaNacimiento ; 
 }       
        
        
    public clsCliente[] crearVector() {
        clsCliente vectCliente[] = new clsCliente[10];
        return vectCliente;
    }

    public int agregarCliente(clsCliente vectCliente[], int cantClientes) {
        Atajos A = new Atajos();

        String identificacion = A.recibeString("Digite la identificación del cliente");
        String fechaNacimiento = A.recibeString ("Digite su fecha de nacimiento"); 
        String Residencia = A.recibeString ("Digite su residencia") ; 
        String nombreCompleto = A.recibeString("Digite el nombre completo del cliente");
        char sexo = ' '; //I intersex, M Mujer, H hombre
        boolean primera = true;
        do {
            if (!primera) {
                A.imprimeMensaje("Anteriormente digitó un sexo incorrecto, intente de nuevo");
            }
            sexo = A.RecibeChar("Seleccione el sexo: \nM. Mujer\nH. Hombre\nI. Intersexo");
            primera = false;
        } while (sexo != 'I' && sexo != 'M' && sexo != 'H');

        String telefono = A.recibeString("Digite el teléfono del cliente");
        return 0;
    }


    public void listarCliente(clsCliente vectCliente[], int cantClientes) {
        String impresion = "Identificacion\tNombre\tSexo\tTeléfono\tCantMascota\n";
        Atajos A = new Atajos();

        for (int i = 0; i < cantClientes; i++) {
            impresion += vectCliente[i].toString() + "\n";
        }
        A.imprimeMensaje(new TextArea(impresion));
    }
    
    public void obtenerInfoCliente(clsCliente vectCliente[], int cantClientes){
        Atajos A = new Atajos();
        String identificacionCambiar = A.recibeString("Digite la identificacion del cliente a consultar");
        int posCliente = -1;
        for (int i = 0; i < cantClientes; i++) {
            if (vectCliente[i].getIdentificacion().equalsIgnoreCase(identificacionCambiar)) {
                posCliente = i;
                break;
            }
        }
        if (posCliente == -1) {
            A.imprimeMensaje("No se encontraron coincidencias con la identificacion");
        }else{
            String impresion = "Identificacion\tNombre\tSexo\tTeléfono\n";
            impresion+=vectCliente[posCliente].toString();
            A.imprimeMensaje(new TextArea(impresion));
        }
    }
    public int obtenerCliente(clsCliente vectCliente[], int cantClientes){
        Atajos A = new Atajos();
        String identificacionCambiar = A.recibeString("Digite la identificacion del cliente a consultar");
        int posCliente = -1;
        for (int i = 0; i < cantClientes; i++) {
            if (vectCliente[i].getIdentificacion().equalsIgnoreCase(identificacionCambiar)) {
                return i;
            }
        }
        return -1;
    }
}

