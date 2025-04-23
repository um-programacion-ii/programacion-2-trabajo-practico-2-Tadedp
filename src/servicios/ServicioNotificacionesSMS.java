package src.servicios;

import src.enums.FormatoNotificacion;
import src.interfaces.ServicioNotificaciones;
import src.modelos.Notificacion;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String telefonoDestino, Notificacion notificacion) {
        System.out.println("SMS enviado a " + telefonoDestino + ": " + notificacion.getContenido());
    }

    @Override
    public boolean soportaFormato(FormatoNotificacion formatoNotificacion) {
        return formatoNotificacion == FormatoNotificacion.SMS;
    }
}
