package entrega4;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Scanner;

public class PrincipalPersona {

    public static void main(String[] args) {
        List<Persona> personas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Ingreso de Personas ---");
        System.out.println("Ingresa 'fin' en el nombre para terminar.");

        String nombrePersona;
        do {
            System.out.print("\nNombre de la persona (o 'fin' para terminar): ");
            nombrePersona = scanner.nextLine();

            if (!nombrePersona.equalsIgnoreCase("fin")) {
                System.out.print("Apellido: ");
                String apellido = scanner.nextLine();

                int edad = 0;
                while (true) {
                    try {
                        System.out.print("Edad: ");
                        edad = Integer.parseInt(scanner.nextLine());
                        if (edad > 0) {
                            break;
                        } else {
                            System.out.println("La edad debe ser un número positivo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada inválida. Por favor, ingresa un número para la edad.");
                    }
                }

                String genero;
                while (true) {
                    System.out.print("Género masculino o femenino: ");
                    genero = scanner.nextLine().toUpperCase();
                    if (genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("femenino")) {
                        break;
                    } else {
                        System.out.println("Género inválido. Por favor, ingresa 'masculino' o 'femenino'.");
                    }
                }

                double sueldoHora = 0;
                while (true) {
                    try {
                        System.out.print("Sueldo por hora: ");
                        sueldoHora = Double.parseDouble(scanner.nextLine());
                        if (sueldoHora >= 0) {
                            break;
                        } else {
                            System.out.println("El sueldo por hora no puede ser valores negativo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Valor no valido. Por favor, ingresa un valor correcto para el sueldo.");
                    }
                }

                System.out.print("Cargo: ");
                String cargo = scanner.nextLine();

                personas.add(new Persona(nombrePersona, apellido, edad, genero, sueldoHora, cargo));
            }
        } while (!nombrePersona.equalsIgnoreCase("fin"));

        scanner.close();

        long cantidadPersonas = personas.stream().count();
        System.out.println("\na. Cantidad de personas almacenadas: " + cantidadPersonas);

        OptionalDouble promedioEdades = personas.stream()
                .mapToInt(Persona::getEdad)
                .average();
        System.out.print("b. Promedio de edades: ");
        promedioEdades.ifPresentOrElse(
                avg -> System.out.printf("%.2f%n", avg),
                () -> System.out.println("No hay personas para calcular el promedio.")
        );

        long mayoresDeEdad = personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .count();
        System.out.println("c. Cantidad de personas mayores de edad: " + mayoresDeEdad);

        System.out.println("d. Personas cuyos nombres empiezan con 'A':");
        personas.stream()
                .filter(p -> p.getNombre().startsWith("A") || p.getNombre().startsWith("a"))
                .forEach(p -> System.out.println("   - " + p.getNombre() + " " + p.getApellido()));

        System.out.println("e. Personas cuyos apellidos contienen la letra 'M':");
        personas.stream()
                .filter(p -> p.getApellido().toLowerCase().contains("m"))
                .forEach(p -> System.out.println("   - " + p.getNombre() + " " + p.getApellido()));

        System.out.println("\n--- Tarea 3 ---");
        System.out.println("a. Sueldo de 8 horas para directores masculinos:");
        personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Director") && p.getGenero().equalsIgnoreCase("M"))
                .map(p -> {
                    double sueldo8Horas = p.getSueldoHora() * 8;
                    // Extra: Usando peek para mostrar información adicional antes de la operación terminal
                    System.out.print("   Nombre: " + p.getNombre() + " " + p.getApellido());
                    return sueldo8Horas;
                })
                .forEach(sueldo -> System.out.printf(" Sueldo por 8 horas: $%.2f%n", sueldo));

        // b. Usando findFirst, filter, muestra la primera persona que sea “desarrollador” y sea de género femenino.
        System.out.println("\nb. Primera persona desarrolladora femenina:");
        Optional<Persona> primeraDesarrolladoraFemenina = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("desarrollador") && p.getGenero().equalsIgnoreCase("F"))
                .findFirst();

        primeraDesarrolladoraFemenina.ifPresentOrElse(
                p -> System.out.println("   " + p),
                () -> System.out.println("   No se encontró ninguna desarrolladora femenina.")
        );

        // c. Muestre la persona con cargo de desarrollador que más gana por hora.
        System.out.println("\nc. Desarrollador(a) que más gana por hora:");
        Optional<Persona> masGana = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("desarrollador"))
                .max((p1, p2) -> Double.compare(p1.getSueldoHora(), p2.getSueldoHora()));

        if (masGana.isPresent()) {
            System.out.println("   " + masGana.get());
        } else {
            System.out.println("   No se encontraron desarrolladores.");
        }

        // d. Usa sorted para mostrar todas las mujeres ordenadas por su nombre.
        System.out.println("\nd. Mujeres ordenadas por nombre:");
        personas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase("F"))
                .sorted((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre())) // O usando Comparator.comparing
                // .sorted(Comparator.comparing(Persona::getNombre)) // Opción usando Comparator.comparing
                       .forEach(p -> System.out.println("   - " + p.getNombre() + " " + p.getApellido()));
    }
}

