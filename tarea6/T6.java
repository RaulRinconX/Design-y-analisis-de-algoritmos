import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class T6 {
    private static class Persona {
        String nombre;
        Set<String> diasDisponibles;

        Persona(String nombre, Set<String> diasDisponibles) {
            this.nombre = nombre;
            this.diasDisponibles = diasDisponibles;
        }
    }

    public static void main(String[] args) {
        // Crear un conjunto de personas y sus días disponibles
        Set<Persona> personas = new HashSet<>();
        Set<String> dias = new HashSet<>();

        // Leer los datos de un archivo de texto
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] linea = scanner.nextLine().split(",");
                String nombre = linea[0];
                Set<String> diasDisponibles = new HashSet<>(Arrays.asList(linea).subList(1, linea.length));
                personas.add(new Persona(nombre, diasDisponibles));
                dias.addAll(diasDisponibles);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no fue encontrado.");
            e.printStackTrace();
        }

        // Encontrar el conjunto mínimo de días
        Set<String> minDias = encontrarMinDias(personas, dias);
        System.out.println("El conjunto mínimo de días es: " + minDias);
    }

    private static Set<String> encontrarMinDias(Set<Persona> personas, Set<String> dias) {
        Set<String> minDias = new HashSet<>();
        while (!personas.isEmpty()) {
            String diaMax = null;
            Set<Persona> personasMax = new HashSet<>();
            for (String dia : dias) {
                Set<Persona> personasTemp = new HashSet<>();
                for (Persona persona : personas) {
                    if (persona.diasDisponibles.contains(dia)) {
                        personasTemp.add(persona);
                    }
                }
                if (personasTemp.size() > personasMax.size()) {
                    diaMax = dia;
                    personasMax = personasTemp;
                }
            }
            dias.remove(diaMax);
            personas.removeAll(personasMax);
            minDias.add(diaMax);
        }
        return minDias;
    }
}