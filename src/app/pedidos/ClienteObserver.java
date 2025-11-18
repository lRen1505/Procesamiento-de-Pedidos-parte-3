
package app.pedidos;


public class ClienteObserver implements Observer{
    private String nombre;
    public ClienteObserver(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public void actualizar(String evento, String datos) {
        System.out.println("CLIENTE: " + nombre + " Notificacion recibida");
    switch (evento) {
            case "PEDIDO_CREADO":
                System.out.println("Su pedido ha sido registrado exitosamente");
                System.out.println("Detalles: " + datos);
                break;
                
            case "FACTURA_GENERADA":
                System.out.println("Su factura electronica ha sido generada");
                System.out.println("  " + datos);
                break;
                
            case "PEDIDO_PROCESADO":
                System.out.println("Su pedido esta siendo procesado");
                System.out.println(" " + datos);
                break;
                
            default:
                System.out.println("Evento: " + evento + " - " + datos);
        }   
     
    }
  public String getNombre() {
        return nombre;
    }  
}
