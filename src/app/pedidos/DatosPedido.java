
package app.pedidos;


public class DatosPedido {
    private String cliente;
    private String producto;
    private int cantidad;
    
    public DatosPedido(String cliente, String producto, int cantidad) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    public String getCliente() {
        return cliente;
    }
    
    public String getProducto() {
        return producto;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    @Override
    public String toString() {
        return String.format("Cliente: %s, Producto: %s, Cantidad: %d", 
                           cliente, producto, cantidad);
    }
}
