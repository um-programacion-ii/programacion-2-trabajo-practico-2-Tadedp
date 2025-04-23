package src.gestores;

import src.enums.FormatoNotificacion;
import src.interfaces.ServicioNotificaciones;
import src.modelos.Notificacion;
import src.servicios.ServicioNotificacionesEmail;
import src.servicios.ServicioNotificacionesSMS;

import java.util.ArrayList;
import java.util.List;

public class GestorNotificaciones {
    private List<ServicioNotificaciones> serviciosNotificacion;

    public GestorNotificaciones() {
        serviciosNotificacion = new ArrayList<>();
        serviciosNotificacion.add(new ServicioNotificacionesSMS());
        serviciosNotificacion.add(new ServicioNotificacionesEmail());
    }

    public GestorNotificaciones(List<ServicioNotificaciones> serviciosNotificacion) {
        this.serviciosNotificacion = serviciosNotificacion;
    }

    public Notificacion enviarNotificacion(String contenido, FormatoNotificacion formatoNotificacion, String destino) {
        Notificacion nuevaNotificacion = new Notificacion(contenido, formatoNotificacion);
        serviciosNotificacion.stream()
                .filter(servicio -> servicio.soportaFormato(formatoNotificacion))
                .forEach( servicio -> servicio.enviarNotificacion(destino, nuevaNotificacion));
        return nuevaNotificacion;
    }

}
