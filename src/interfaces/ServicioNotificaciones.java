package src.interfaces;

import src.enums.FormatoNotificacion;
import src.modelos.Notificacion;

public interface ServicioNotificaciones {
    void enviarNotificacion(String destino, Notificacion notificacion);
    boolean soportaFormato(FormatoNotificacion formatoNotificacion);
}
