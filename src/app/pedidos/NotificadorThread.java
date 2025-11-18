
package app.pedidos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class NotificadorThread extends Thread{
    private PedidoObservable observable;
    private BlockingQueue<String> notificacionesPendientes;
    private volatile boolean activo = true;
    public NotificadorThread(PedidoObservable observable) {
        this.observable = observable;
        this.notificacionesPendientes = new LinkedBlockingQueue<>();
        this.setName("Thread-Notificador");
    }
}
