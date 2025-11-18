
package app.pedidos;


public class InventarioObserver implements Observer {
    @Override
    public void actualizar(String evento, String datos) {
        System.out.println("Notificacion recibida");
        switch (evento) {
            case "PEDIDO_CREADO":
                System.out.println(" Reservando stock para el pedido");
                System.out.println(" Datos: " + datos);
                // Aquí se actualizaría el stock en una BD real
                break;
                
            case "PEDIDO_PROCESADO":
                System.out.println("Descontando productos del inventario");
                System.out.println("  " + datos);
                break;
                
            case "FACTURA_GENERADA":
                System.out.println(" Confirmando movimiento de inventario");
                break;
                
            default:
                System.out.println(" Evento no procesado por inventario: " + evento);
        }
    }
}
