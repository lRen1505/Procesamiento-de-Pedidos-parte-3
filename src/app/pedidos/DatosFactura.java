
package app.pedidos;


public class DatosFactura {
    private String cliente;
    private double total;
  
    public DatosFactura(String cliente, double total) {
        this.cliente = cliente;
        this.total = total;
    }
    public String getCliente() {
        return cliente;
    }
    
    public double getTotal() {
        return total;
    }
    
    @Override
    public String toString() {
        return String.format("Cliente: %s, Total: S/. %.2f", cliente, total);
    }
}
