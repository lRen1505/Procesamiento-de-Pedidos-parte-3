
package app.pedidos;

import java.util.List;


public class Main {


    public static void main(String[] args) {
      System.out.println("=== SISTEMA DE PROCESAMIENTO DE PEDIDOS V.3 ===\n");
    try {
            PedidoRepository pedidoRepository = new PedidoRepository();
            LegacyBillingSystem legacySystem = new LegacyBillingSystem();
            FacturaService facturaService = new FacturaAdapter(legacySystem);
            PedidoFacade pedidoFacade = new PedidoFacade(facturaService, pedidoRepository);
            pedidoFacade.setImpuestoStrategy(new IGV18Strategy());
            System.out.println("\n Configurando Patrón Observer...\n");
            
            PedidoObservable observable = new PedidoObservable();
            
            ClienteObserver clienteObserver = new ClienteObserver("Juan Pérez");
            InventarioObserver inventarioObserver = new InventarioObserver();
            LogObserver logObserver = new LogObserver();
            
            observable.agregarObservador(clienteObserver);
            observable.agregarObservador(inventarioObserver);
            observable.agregarObservador(logObserver);
            
            pedidoFacade.setObservable(observable);
            System.out.println("\n Patrón Observer configurado\n");
            System.out.println("=".repeat(70) + "\n");
            
            System.out.println("Creando Threads...\n");
            
                ProcesadorPedidosThread threadPedidos = new ProcesadorPedidosThread(
                pedidoFacade, observable);
                        
                GeneradorFacturasThread threadFacturas = new GeneradorFacturasThread(
                facturaService, observable);
                
                NotificadorThread threadNotificador = new NotificadorThread(observable);
                
            threadPedidos.start();
            threadFacturas.start();
            threadNotificador.start();
            
            System.out.println("Threads iniciados correctamente\n");
            System.out.println("=".repeat(70) + "\n");
            
            Thread.sleep(1000);
            
            System.out.println("Agregando pedidos a la cola de procesamiento...\n");
            threadPedidos.agregarPedido(new DatosPedido("Juan Pérez", "Laptop HP", 2));
            Thread.sleep(500);
            threadPedidos.agregarPedido(new DatosPedido("María García", "Mouse Logitech", 3));
            Thread.sleep(500);
            threadPedidos.agregarPedido(new DatosPedido("Carlos López", "Teclado Mecánico", 1));
            
            System.out.println("\n Agregando facturas a la cola...\n");
            threadFacturas.agregarFactura(new DatosFactura("Ana Torres", 250.50));
            Thread.sleep(500);
            
            threadFacturas.agregarFactura(new DatosFactura("Luis Ramírez", 180.00));
            
            System.out.println("\n Agregando notificaciones a la cola...\n");
            threadNotificador.agregarNotificacion("Su pedido ha sido confirmado");
            Thread.sleep(500);
            
            threadNotificador.agregarNotificacion("Su factura está lista para descargar");
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("Esperando que los threads procesen las tareas...");
            System.out.println("=".repeat(70) + "\n");
            Thread.sleep(8000);
            
    }catch(InterruptedException e){
    }
    
    }
}
