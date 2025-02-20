package Aproyecto;

import java.util.List;
import java.io.*;

public class PersistenciaDatos<T> {
    public void guardarCSV(List<T> items, String archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (T item : items) {
                writer.write(item.toString());
                writer.newLine();
            }
        }
    }

    public void guardarJSON(List<T> items, String archivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            writer.write("[\n");
            for (int i = 0; i < items.size(); i++) {
                writer.write("  " + items.get(i).toString());
                if (i < items.size() - 1) {
                    writer.write(",");
                }
                writer.newLine();
            }
            writer.write("]");
        }
    }

    public List<T> cargarCSV(String archivo) throws IOException {
        throw new UnsupportedOperationException("Método cargarCSV no implementado.");
    }

    public List<T> cargarJSON(String archivo) throws IOException {
        throw new UnsupportedOperationException("Método cargarJSON no implementado.");
    }
}

