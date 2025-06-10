package entrega4;

import java.util.ArrayList;
import java.util.List;
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
                    if (genero.equals("masculino") || genero.equals("femenino")) {
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

    }
}
