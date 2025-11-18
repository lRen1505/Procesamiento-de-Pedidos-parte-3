
package app.pedidos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogObserver implements Observer{
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void actualizar(String evento, String datos) {
      String timestamp = LocalDateTime.now().format(formatter);
      System.out.println("Evento registrado");
      System.out.println("Timestamp: " + timestamp);
      System.out.println("Tipo: " + evento);
      System.out.println("Datos: " + datos);

    }
}
