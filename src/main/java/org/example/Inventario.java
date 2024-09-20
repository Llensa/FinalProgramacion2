package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventario {
    private List<Producto> productos = new ArrayList<>();

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
        scanner.nextLine(); // Consumir nueva línea

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setCategoria(categoria);
        producto.setCantidad(cantidad);
        producto.setPrecio(precio);

        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO productos (nombre, categoria, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getCategoria());
            statement.setInt(3, producto.getCantidad());
            statement.setDouble(4, producto.getPrecio());

            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Producto agregado exitosamente.");
                productos.add(producto); // Añadimos el producto a la lista
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar producto: " + e.getMessage());
        }
    }

    public void listarProductos() {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "SELECT * FROM productos";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCategoria(resultSet.getString("categoria"));
                producto.setCantidad(resultSet.getInt("cantidad"));
                producto.setPrecio(resultSet.getDouble("precio"));

                System.out.println(producto.toString());
            }
        } catch (SQLException e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        }
    }

    public void eliminarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del producto a eliminar: ");
        int id = scanner.nextInt();

        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "DELETE FROM productos WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);

            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    public void modificarProducto() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el ID del producto a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nueva línea

        System.out.print("Nuevo nombre del producto: ");
        String nuevoNombre = scanner.nextLine();

        System.out.print("Nueva categoría del producto: ");
        String nuevaCategoria = scanner.nextLine();

        System.out.print("Nueva cantidad del producto: ");
        int nuevaCantidad = scanner.nextInt();

        System.out.print("Nuevo precio del producto: ");
        double nuevoPrecio = scanner.nextDouble();
        scanner.nextLine(); // Consumir nueva línea

        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "UPDATE productos SET nombre = ?, categoria = ?, cantidad = ?, precio = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nuevoNombre);
            statement.setString(2, nuevaCategoria);
            statement.setInt(3, nuevaCantidad);
            statement.setDouble(4, nuevoPrecio);
            statement.setInt(5, id);

            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Producto modificado exitosamente.");
            } else {
                System.out.println("No se encontró un producto con ese ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar producto: " + e.getMessage());
        }
    }

    public void agregarProductoDesdeJson(List<Producto> productosDesdeJson) {
        try (Connection conn = ConexionDB.getConnection()) {
            String sql = "INSERT INTO productos (nombre, categoria, cantidad, precio) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);

            for (Producto producto : productosDesdeJson) {
                statement.setString(1, producto.getNombre());
                statement.setString(2, producto.getCategoria());
                statement.setInt(3, producto.getCantidad());
                statement.setDouble(4, producto.getPrecio());

                int filasInsertadas = statement.executeUpdate();
                if (filasInsertadas > 0) {
                    System.out.println("Producto " + producto.getNombre() + " agregado exitosamente.");
                    productos.add(producto); // Añadir a la lista en memoria
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar productos desde JSON: " + e.getMessage());
        }
    }
}