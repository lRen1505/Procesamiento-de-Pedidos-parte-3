
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
    @Override
    public void run() {
        System.out.println("\n[" + getName() + "] Iniciado - Esperando facturas...\n");
        while (activo) {
        try {
            DatosFactura datos = facturasPendientes.take();
            System.out.println("\n [" + getName() + "] Generando factura para: " + datos.getCliente());
            Thread.sleep(800);
            facturaService.generarFactura(datos.getCliente(), datos.getTotal());
            observable.notificarObservadores("FACTURA_GENERADA", datos.toString());
            System.out.println("\n [" + getName() + "] Factura generada exitosamente\n");
        }catch(InterruptedException e){
            System.out.println("\n🧵 [" + getName() + "] Interrumpido");
            activo = false;
        }
      }
        System.out.println("\n [" + getName() + "] Finalizado\n");
    }
}
