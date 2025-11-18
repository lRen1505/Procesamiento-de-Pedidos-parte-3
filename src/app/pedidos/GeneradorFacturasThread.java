
package app.pedidos;

import java.util.concurrent.BlockingQueue;


public class GeneradorFacturasThread extends Thread {
    private FacturaService facturaService;
    private PedidoObservable observable;
    private BlockingQueue<DatosFactura> facturasPendientes;
    private volatile boolean activo = true;
}
