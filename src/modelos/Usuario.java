package src.modelos;

import src.interfaces.Notificable;

import java.util.ArrayList;
import java.util.List;

public class Usuario implements Notificable {
    private String user;
    private String nombre;
    private String apellido;
    private String email;
    private long telefono;
    private List<Notificacion> notificacionesPendientes;

    public Usuario() {
    }

    public Usuario(String user, String nombre, String apellido, String email, long telefono) {
        this.user = user;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.notificacionesPendientes = new ArrayList<>();
    }

    public String getUser() {
        return user;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public long getTelefono() {
        return telefono;
    }

    @Override
    public List<Notificacion> leerNotificacionesPendientes() {
        List<Notificacion> pendientes = new ArrayList<>(notificacionesPendientes);
        notificacionesPendientes.clear();
        return pendientes;
    }

    @Override
    public void agregarNotificacion(Notificacion notificacion) {
        this.notificacionesPendientes.add(notificacion);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "user='" + user + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", notificacionesPendientes=" + notificacionesPendientes +
                '}';
    }
}
