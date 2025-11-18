
package app.pedidos;


public class InventarioObserver implements Observer {
    @Override
    public void actualizar(String evento, String datos) {
        System.out.println("Notificación recibida");
}
