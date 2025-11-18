
package app.pedidos;

import java.util.concurrent.BlockingQueue;


public class NotificadorThread extends Thread{
    private PedidoObservable observable;
    private BlockingQueue<String> notificacionesPendientes;
    private volatile boolean activo = true;
}
