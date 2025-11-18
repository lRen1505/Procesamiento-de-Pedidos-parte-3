
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
    }
    
    }
}
