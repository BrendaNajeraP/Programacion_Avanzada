package proyectoclases;

import java.util.ArrayList;

public class Lista<T> {
    private ArrayList<T> lista;

    public Lista() {
        lista = new ArrayList<>();
    }

    public void insertar(T elemento) {
        lista.add(elemento);
    }

    public String MostrarLista() {
        StringBuilder sb = new StringBuilder();
        for (T elemento : lista) {
            sb.append(elemento.toString()).append("\n");
        }
        return sb.toString();
    }
}