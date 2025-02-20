import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cejemplo01 {

    static Ejemplo01 ejemplo01;
    static Lista lista;

    public static void main(String[] args) {
        lista = new Lista();
        ejemplo01 = new Ejemplo01();
        ejemplo01.setVisible(true);

       
        ejemplo01.bsalir().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejemplo01.dispose();
            }
        });

      
        ejemplo01.bagregar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cpersona nodo = new Cpersona(ejemplo01.getT1(), ejemplo01.getT2());
                lista.insertar(nodo);
                ejemplo01.limparText();
                ejemplo01.setTr(lista.info() + "\n...............\n"); 
            }
        });
    }
}