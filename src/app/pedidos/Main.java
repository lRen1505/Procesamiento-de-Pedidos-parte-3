
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
            System.out.println("\n Configurando Patron Observer...\n");
            
            PedidoObservable observable = new PedidoObservable();
            
            ClienteObserver clienteObserver = new ClienteObserver("Juan Perez");
            InventarioObserver inventarioObserver = new InventarioObserver();
            LogObserver logObserver = new LogObserver();
            
            observable.agregarObservador(clienteObserver);
            observable.agregarObservador(inventarioObserver);
            observable.agregarObservador(logObserver);
            
            pedidoFacade.setObservable(observable);
            System.out.println("\n Patron Observer configurado\n");
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
            threadPedidos.agregarPedido(new DatosPedido("Juan Perez", "Laptop HP", 2));
            Thread.sleep(500);
            threadPedidos.agregarPedido(new DatosPedido("Maria Garcia", "Mouse Logitech", 3));
            Thread.sleep(500);
            threadPedidos.agregarPedido(new DatosPedido("Carlos Lopez", "Teclado Mecanico", 1));
            
            System.out.println("\n Agregando facturas a la cola...\n");
            threadFacturas.agregarFactura(new DatosFactura("Ana Torres", 250.50));
            Thread.sleep(500);
            
            threadFacturas.agregarFactura(new DatosFactura("Luis Ramirez", 180.00));
            
            System.out.println("\n Agregando notificaciones a la cola...\n");
            threadNotificador.agregarNotificacion("Su pedido ha sido confirmado");
            Thread.sleep(500);
            
            threadNotificador.agregarNotificacion("Su factura esta lista para descargar");
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("Esperando que los threads procesen las tareas...");
            System.out.println("=".repeat(70) + "\n");
            Thread.sleep(8000);
            
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("Deteniendo threads...");
            System.out.println("=".repeat(70) + "\n");
            
            threadPedidos.detener();
            threadFacturas.detener();
            threadNotificador.detener();
            
            threadPedidos.join(2000);
            threadFacturas.join(2000);
            threadNotificador.join(2000);
            
             System.out.println("=== CONSULTA DE PEDIDOS, THREADS Y CONCURRENCIA  ===\n");
          
            
            System.out.println("FACADE: PedidoFacade coordina servicios");
            System.out.println("ADAPTER: FacturaAdapter integra sistema legacy");
            System.out.println("STRATEGY: Calculo dinamico de impuestos");
            System.out.println("REPOSITORY: Persistencia de pedidos");
            System.out.println("OBSERVER: Notificaciones automaticas a 3 observadores");
            System.out.println("THREADS: 3 hilos trabajando concurrentemente");
            System.out.println("  - Thread 1: Procesador de Pedidos");
            System.out.println("  - Thread 2: Generador de Facturas");
            System.out.println("  - Thread 3: Notificador");
            
            System.out.println("\nTotal de pedidos procesados: " + 
                             pedidoRepository.contarPedidos());
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println(" Programa finalizado exitosamente");
            System.out.println("=".repeat(70) + "\n");
            
    }catch(InterruptedException e){
        System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
