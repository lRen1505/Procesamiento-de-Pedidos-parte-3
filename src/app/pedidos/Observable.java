
package app.pedidos;

public interface Observable {
    void agregarObservador(Observer observador);
    void eliminarObservador(Observer observador);
    void notificarObservadores(String evento, String datos);
}
