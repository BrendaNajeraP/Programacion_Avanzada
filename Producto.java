package Aproyecto;
public class Producto extends ItemVenta implements ImpuestoAplicable {
    private int cantidad;

    public Producto(String id, String nombre, double precio, int cantidad) {
        super(id, nombre, precio);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public double calcularIVA() {
        return getPrecio() * 0.16;
    }

    @Override
    public double calcularIEPS() {
        return getPrecio() * 0.08;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", \"Cantidad\": " + cantidad + "}");
    }
}