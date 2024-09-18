package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class ArchivoPersistencia {
    private static final String FILE_PATH = "inventario.json";
    private Gson gson = new Gson();

    public void guardarDatos(List<Producto> productos) throws Exception {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(productos, writer);
        }
    }

    public List<Producto> cargarDatos() throws Exception {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type tipoListaProductos = new TypeToken<List<Producto>>() {}.getType();
            return gson.fromJson(reader, tipoListaProductos);
        }
    }
}

