package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Inventario inventario = new Inventario();
        ArchivoPersistencia persistencia = new ArchivoPersistencia();

        // Cargar datos al inicio
        try {
            inventario = (Inventario) persistencia.cargarDatos();
        } catch (Exception e) {
            System.out.println("No se pudieron cargar los datos.");
        }

        int opcion;
        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Modificar Producto");
            System.out.println("3. Eliminar Producto");
            System.out.println("4. Consultar Productos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir nueva línea

            switch (opcion) {
                case 1:
                    inventario.agregarProducto();
                    break;
                case 2:
                    inventario.modificarProducto();
                    break;
                case 3:
                    inventario.eliminarProducto();
                    break;
                case 4:
                    inventario.listarProductos();
                    break;
                case 5:
                    // Guardar datos y salir
                    try {
                        persistencia.guardarDatos(inventario.getProductos());
                    } catch (Exception e) {
                        System.out.println("Error al guardar los datos.");
                    }
                    System.exit(0);
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
