


package entrega4;


public class Animal {
    private String nombre;
    private String tipo;
    private String genero;
    
public Animal(String nombre, String tipo, String genero){
        this.nombre = nombre;
        this.tipo = tipo;
        this.genero = genero;
    }
public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    void add(Animal animal) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}