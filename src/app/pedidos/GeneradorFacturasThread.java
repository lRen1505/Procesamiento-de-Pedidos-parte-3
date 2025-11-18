
package app.pedidos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class GeneradorFacturasThread extends Thread {
    private FacturaService facturaService;
    private PedidoObservable observable;
    private BlockingQueue<DatosFactura> facturasPendientes;
    private volatile boolean activo = true;
    public GeneradorFacturasThread(FacturaService facturaService, PedidoObservable observable) {
        this.facturaService = facturaService;
        this.observable = observable;
        this.facturasPendientes = new LinkedBlockingQueue<>();
        this.setName("Thread-GeneradorFacturas");
    }
}
