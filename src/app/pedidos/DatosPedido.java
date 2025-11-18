
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
}
