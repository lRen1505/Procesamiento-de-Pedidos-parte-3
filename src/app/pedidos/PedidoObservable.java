
package app.pedidos;

import java.util.ArrayList;
import java.util.List;


public class PedidoObservable implements Observable {
    private List<Observer> observadores;
    public PedidoObservable() {
        this.observadores = new ArrayList<>();
    }
    @Override
    public synchronized void agregarObservador(Observer observador) {
        observadores.add(observador);
        System.out.println("Observador agregado: " + observador.getClass().getSimpleName());
    }
    @Override
    public synchronized void eliminarObservador(Observer observador) {
        observadores.remove(observador);
        System.out.println("Observador eliminado: " + observador.getClass().getSimpleName());
    }
    @Override
    public synchronized void notificarObservadores(String evento, String datos) {
        System.out.println("\nNotificando evento: " + evento);
        System.out.println("Observadores a notificar: " + observadores.size());
        
        for (Observer observador : observadores) {
            observador.actualizar(evento, datos);
        }
    } 
}
    

