package proyectoclases;

import java.util.Objects;

public class Cproducto implements Iproductos {
    private String id, producto, precio, cantidad;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getProducto() {
        return this.producto;
    }

    @Override
    public String getPrecio() {
        return this.precio;
    }

    @Override
    public String getCantidad() {
        return this.cantidad;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setProducto(String producto) {
        this.producto = producto;
    }

    @Override
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        String codigo = String.format("%1$-" + 5 + "s", this.getId());
        String producto = String.format("%1$-" + 30 + "s", this.getProducto());
        String precio = String.format("%1$-" + 10 + "s", this.getPrecio());
        String cantidad = String.format("%1$-" + 10 + "s", this.getCantidad());
        return codigo.concat(producto + precio + cantidad);
    }

    public Cproducto(String id, String producto, String precio, String cantidad) {
        super();
        this.id = id;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, producto);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cproducto other = (Cproducto) obj;
        return Objects.equals(id, other.id) && Objects.equals(producto, other.producto);
    }
}