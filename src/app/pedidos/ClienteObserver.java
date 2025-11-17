
package app.pedidos;


public class ClienteObserver implements Observer{
    private String nombre;
    public ClienteObserver(String nombre) {
        this.nombre = nombre;
    }
}
