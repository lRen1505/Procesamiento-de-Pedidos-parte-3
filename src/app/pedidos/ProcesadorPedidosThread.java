
package app.pedidos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


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
    @Override
    public void run() {
        System.out.println("\n [" + getName() + "] Iniciado - Esperando pedidos...\n");
        
        while (activo) {
            try {
                // Toma un pedido de la cola (espera si no hay)
                DatosPedido datos = pedidosPendientes.take();
                
                System.out.println("\n [" + getName() + "] Procesando pedido de: " + datos.getCliente());
                
                // Notificar que el pedido está siendo procesado
                observable.notificarObservadores("PEDIDO_PROCESADO", datos.toString());
                
                // Simular tiempo de procesamiento
                Thread.sleep(1000);
                
                // Procesar el pedido usando el Facade
                String resultado = pedidoFacade.procesarPedido(
                    datos.getCliente(), 
                    datos.getProducto(), 
                    datos.getCantidad()
                );
                
                // Notificar que el pedido fue creado
                observable.notificarObservadores("PEDIDO_CREADO", datos.toString());
                
                System.out.println("\n [" + getName() + "] ✓ Pedido completado\n");
                System.out.println(resultado);
                
            } catch (InterruptedException e) {
                System.out.println("\n [" + getName() + "] Interrumpido");
                activo = false;
                // Opcional: break;
            }
        }
        
        System.out.println("\n [" + getName() + "] Finalizado\n");
    }
    public void agregarPedido(DatosPedido datos) {
        try{
            pedidosPendientes.put(datos);
            System.out.println("✓ Pedido agregado a la cola: " + datos.getCliente());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
