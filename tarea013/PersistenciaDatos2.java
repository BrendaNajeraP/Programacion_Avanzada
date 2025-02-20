package tarea013;

import java.util.List;

public interface PersistenciaDatos2<T> {
    void guardar(List<T> datos, String archivo);
    List<T> cargar(String archivo);
    
}