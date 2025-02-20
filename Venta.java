package Aproyecto;

public class Venta extends ItemVenta {
    private int cantidadVendida;

    public Venta(String id, String nombre, double precio, int cantidadVendida) {
        super(id, nombre, precio);
        this.cantidadVendida = cantidadVendida;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    @Override
    public String toString() {
        return super.toString().replace("}", ", \"Cantidad Vendida\": " + cantidadVendida + "}");
    }
}