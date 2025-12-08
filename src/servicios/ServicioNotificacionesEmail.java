package src.servicios;

import src.enums.FormatoNotificacion;
import src.interfaces.ServicioNotificaciones;
import src.modelos.Notificacion;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(String emailDestino, Notificacion notificacion) {
        System.out.println("Correo enviado a " + emailDestino + ": " + notificacion.getContenido());
    }

    @Override
    public boolean soportaFormato(FormatoNotificacion formatoNotificacion) {
        return formatoNotificacion == FormatoNotificacion.EMAIL;
    }
}
