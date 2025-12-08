package src.interfaces;

import src.modelos.Notificacion;

import java.util.List;

public interface Notificable {
    void agregarNotificacion(Notificacion notificacion);
    List<Notificacion> leerNotificacionesPendientes();
}
