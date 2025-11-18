
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
    @Override
    public void run() {
        System.out.println("\n [" + getName() + "] Iniciado - Esperando notificaciones...\n");
        
        while (activo) {
            try {
                // Toma una notificación de la cola
                String mensaje = notificacionesPendientes.take();
                
                System.out.println("\n [" + getName() + "] Enviando notificación...");
                
                // Simular tiempo de envío (email, SMS, etc.)
                Thread.sleep(500);
                
                // Enviar notificación (simulado)
                System.out.println("Email enviado");
                System.out.println("SMS enviado");
                System.out.println("Push notification enviada");
                System.out.println("Mensaje: " + mensaje);
                
                System.out.println("\n [" + getName() + "] Notificaciones enviadas\n");
                
            } catch (InterruptedException e) {
                System.out.println("\n[" + getName() + "] Interrumpido");
                activo = false;
            }
        }
         System.out.println("\n🧵 [" + getName() + "] Finalizado\n");
    }
}
