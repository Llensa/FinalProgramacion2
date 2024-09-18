package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void agregarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoría del producto: ");
        String categoria = scanner.nextLine();

        System.out.print("Cantidad del producto: ");
        int cantidad = scanner.nextInt();

        System.out.print("Precio del producto: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Consumir la nueva línea

        Producto nuevoProducto = new Producto(nombre, categoria, cantidad, precio);
        productos.add(nuevoProducto);

        System.out.println("Producto agregado exitosamente.");
    }

    public void modificarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto a modificar: ");
        String nombre = scanner.nextLine();

        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = scanner.nextLine();

            System.out.print("Nueva categoría: ");
            String nuevaCategoria = scanner.nextLine();

            System.out.print("Nueva cantidad: ");
            int nuevaCantidad = scanner.nextInt();

            System.out.print("Nuevo precio: ");
            double nuevoPrecio = scanner.nextDouble();
            scanner.nextLine(); // Consumir nueva línea

            producto.setNombre(nuevoNombre);
            producto.setCategoria(nuevaCategoria);
            producto.setCantidad(nuevaCantidad);
            producto.setPrecio(nuevoPrecio);

            System.out.println("Producto modificado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombre = scanner.nextLine();

        Producto producto = buscarProducto(nombre);
        if (producto != null) {
            productos.remove(producto);
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("El inventario está vacío.");
        } else {
            productos.forEach(System.out::println);
        }
    }

    private Producto buscarProducto(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }
}
