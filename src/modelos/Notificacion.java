package src.modelos;

import src.enums.FormatoNotificacion;

import java.time.LocalDate;

public class Notificacion {
    private String contenido;
    private LocalDate fechaEnvio;
    private FormatoNotificacion formatoNotificacion;

    public Notificacion() {
        this.fechaEnvio = LocalDate.now();
    }

    public Notificacion(String contenido, FormatoNotificacion formatoNotificacion) {
        this.contenido = contenido;
        this.fechaEnvio = LocalDate.now();
        this.formatoNotificacion = formatoNotificacion;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public FormatoNotificacion getFormatoNotificacion() {
        return formatoNotificacion;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "contenido='" + contenido + '\'' +
                ", fechaEnvio=" + fechaEnvio +
                ", formatoNotificacion=" + formatoNotificacion +
                '}';
    }
}
