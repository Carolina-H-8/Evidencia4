package entrega4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClasePrincipal {

    private Map<String, List<Animal>> clasificar;
    private List<Animal> animales;

    public ClasePrincipal() {
        this.clasificar = new HashMap<>();
        this.animales = new ArrayList<>();

    }

    public void agregarAnimales() {
        Scanner sc = new Scanner(System.in);
        String opcion;

        do {
            System.out.println("Ingrese el nombre del animal");
            String nombre = sc.nextLine();

            String tipo;

            do {
                System.out.println("Ingrese que tipo de animal es: Terrestre, Aereo o Acuatico");
                tipo = sc.nextLine().toLowerCase();
                if (!tipo.equalsIgnoreCase("Terrestre") && !tipo.equalsIgnoreCase("Aereo") && !tipo.equalsIgnoreCase("Acuatico")) {
                    System.out.println("Tipo de animal no valido, por favor ingrese uno valido");
                }
            } while (!tipo.equalsIgnoreCase("Terrestre") && !tipo.equalsIgnoreCase("Aereo") && !tipo.equalsIgnoreCase("Acuatico"));

            String genero;

            do {
                System.out.println("Ingrese el genero del animal:->Femenino o Masculino");

                genero = sc.nextLine().toLowerCase();
                if (!genero.equalsIgnoreCase("Femenino") && !genero.equalsIgnoreCase("Masculino")) {
                    System.out.println("Genero no valido, por favor ingrese un genero valido");

                }
            } while (!genero.equalsIgnoreCase("Femenino") && !genero.equalsIgnoreCase("Masculino"));

            Animal animal = new Animal(nombre, tipo, genero);
            this.animales.add(animal);

            clasificar.computeIfAbsent(tipo, k -> new ArrayList<>()).add(animal);

            System.out.print("¿Desea ingresar otro animal? (s/n): ");
            opcion = sc.nextLine();
        } while (opcion.equalsIgnoreCase("s"));

        sc.close();
    }

    public void mostrarClasificacion() {
        System.out.println("\n--- Clasificación de Animales ---");
        for (Map.Entry<String, List<Animal>> entry : clasificar.entrySet()) {
            String tipo = entry.getKey();
            List<Animal> listaAnimales = entry.getValue();

            System.out.println(tipo.substring(0, 1).toUpperCase() + tipo.substring(1) + ":");

            for (Animal animal : listaAnimales) {
                System.out.println("    " + animal.getNombre());
            }

        }
    }

}
