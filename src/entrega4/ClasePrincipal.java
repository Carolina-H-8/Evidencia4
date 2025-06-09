
package entrega4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ClasePrincipal {
    private Map<String, List<Animal>>clasificar;
    private List<Animal>animales;
    
    public  ClasePrincipal(){
        this.clasificar=new HashMap<>();
        this.animales = new ArrayList<>();
        
    }
    public void agregarAnimales(){
        Scanner sc = new Scanner(System.in);
        String opcion;
        
        
        do{
            System.out.println("Ingrese el nombre del animal");
            String nombre = sc.nextLine();
            
            String tipo;
            
            do{
                System.out.println("Ingrese que tipo de animal es: Terrestre, Aereo o Acuatico");
                tipo = sc.nextLine().toLowerCase();
                if(!tipo.equals("Terrestre")&& !tipo.equals("Aereo")&& !tipo.equals("Acuatico")){
                    System.out.println("Tipo de animal no valido, por favor ingrese uno valido"); 
                }
            } 
            while (!tipo.equals("Terrestre") && !tipo.equals("Aereo") && !tipo.equals("Acuatico"));
            
            String genero;
            
            do{
                System.out.println("Ingrese el genero del animal:->Femenino o Masculino");
                
                genero = sc.nextLine().toLowerCase();
                if(!genero.equals("Femenino") && !genero.equals("Masculino")){
                    System.out.println("Genero no valido, por favor ingrese un genero valido");
                    
                }
            }
            while (!genero.equals("Femenino") && !genero.equals("Masculino"));
            
            Animal animal = new Animal(nombre,tipo,genero);
            animal.add(animal);
          
            
        }
    }
}
