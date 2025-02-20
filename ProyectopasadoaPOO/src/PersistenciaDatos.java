package ProyectopasadoaPOO;

import java.util.List;

public interface PersistenciaDatos<T> {
    void guardar(List<T> datos, String archivo);
    List<T> cargar(String archivo);
}