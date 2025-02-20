
import java.util.ArrayList;

public class Lista {
    private ArrayList<Cpersona> personas;

    public Lista() {
        personas = new ArrayList<>();
    }

    public void insertar(Cpersona persona) {
        personas.add(persona);
    }

    public String info() {
        StringBuilder resultado = new StringBuilder();
        for (Cpersona p : personas) {
            resultado.append(p.toString()).append("\n");
        }
        return resultado.toString();
    }
}