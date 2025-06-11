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

        System.out.println("Ingrese los datos de la persona");
        System.out.println("Ingresa fin en el nombre para terminar la ejecucion.");

        String nombrePersona;
        do {
            System.out.print("\nNombre de la persona: ");
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
                            System.out.println("La edad debe ser un numero positivo.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada invalida. Por favor, ingresa un numero positivo para la edad.");
                    }
                }

                String genero;
                while (true) {
                    System.out.print("Genero masculino o femenino: ");
                    genero = scanner.nextLine().toUpperCase();
                    if (genero.equalsIgnoreCase("masculino") || genero.equalsIgnoreCase("femenino")) {
                        break;
                    } else {
                        System.out.println("Genero invalido. Por favor, ingresa masculino o femenino.");
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
                        System.out.println("Valor no valido. Por favor, ingresa un valor correcto para el sueldo recuerda que debe ser positivo.");
                    }
                }

                System.out.print("Cargo: ");
                String cargo = scanner.nextLine();

                personas.add(new Persona(nombrePersona, apellido, edad, genero, sueldoHora, cargo));
            }
        } while (!nombrePersona.equalsIgnoreCase("fin"));

        scanner.close();

        long cantidadPersonas = personas.stream().count();
        System.out.println("\n  Cantidad de personas almacenadas: " + cantidadPersonas);

        OptionalDouble promedioEdades = personas.stream()
                .mapToInt(Persona::getEdad)
                .average();
        System.out.print("Promedio de edades: ");
        promedioEdades.ifPresentOrElse(
                avg -> System.out.printf("%.2f%n", avg),
                () -> System.out.println("No hay personas para calcular el promedio.")
        );

        long mayoresDeEdad = personas.stream()
                .filter(p -> p.getEdad() >= 18)
                .count();
        System.out.println(" Cantidad de personas mayores de edad: " + mayoresDeEdad);

        System.out.println(" Personas cuyos nombres empiezan con 'A':");
        personas.stream()
                .filter(p -> p.getNombre().startsWith("A") || p.getNombre().startsWith("a"))
                .forEach(p -> System.out.println("   - " + p.getNombre() + " " + p.getApellido()));

        System.out.println(" Personas que su apellidos contienen la letra 'M':");
        personas.stream()
                .filter(p -> p.getApellido().toLowerCase().contains("m"))
                .forEach(p -> System.out.println("   - " + p.getNombre() + " " + p.getApellido()));

        System.out.println(" Sueldo de 8 horas para directores masculinos:");
        personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("Director") && p.getGenero().equalsIgnoreCase("M"))
                .map(p -> {
                    double sueldo8Horas = p.getSueldoHora() * 8;

                    System.out.print("   Nombre: " + p.getNombre() + " " + p.getApellido());
                    return sueldo8Horas;
                })
                .forEach(sueldo -> System.out.printf(" Sueldo por 8 horas: $%.2f%n", sueldo));

        System.out.println("\n Primera persona desarrolladora femenina:");
        Optional<Persona> primeraDesarrolladoraFemenina = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("desarrollador") && p.getGenero().equalsIgnoreCase("femenino"))
                .findFirst();

        primeraDesarrolladoraFemenina.ifPresentOrElse(
                p -> System.out.println("   " + p),
                () -> System.out.println("   No se encontro ninguna desarrolladora femenina.")
        );

        
        System.out.println("\n Desarrollador(a) que mas gana por hora:");
        Optional<Persona> masGana = personas.stream()
                .filter(p -> p.getCargo().equalsIgnoreCase("desarrollador"))
                .max((p1, p2) -> Double.compare(p1.getSueldoHora(), p2.getSueldoHora()));

        if (masGana.isPresent()) {
            System.out.println("   " + masGana.get());
        } else {
            System.out.println("   No se encontraron desarrolladores.");
        }

        System.out.println("\n Mujeres ordenadas por nombre:");
        personas.stream()
                .filter(p -> p.getGenero().equalsIgnoreCase("femenino"))
                .sorted((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()))
                .forEach(p -> System.out.println("   - " + p.getNombre() + " " + p.getApellido()));
    }
}
