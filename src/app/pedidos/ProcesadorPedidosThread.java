
package app.pedidos;

import java.util.concurrent.BlockingQueue;


public class ProcesadorPedidosThread extends Thread{
    private PedidoFacade pedidoFacade;
    private PedidoObservable observable;
    private BlockingQueue<DatosPedido> pedidosPendientes;
    private volatile boolean activo = true; 
    
    public ProcesadorPedidosThread(PedidoFacade pedidoFacade, PedidoObservable observable) {
        this.pedidoFacade = pedidoFacade;
        this.observable = observable;
        this.pedidosPendientes = new LinkedBlockingQueue<>();
        this.setName("Thread-ProcesadorPedidos");
    }
}
