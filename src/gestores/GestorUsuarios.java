package src.gestores;

import src.modelos.Notificacion;
import src.modelos.Usuario;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public GestorUsuarios() {
    }

    public GestorUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario agregar(String user, String nombre, String apellido, String email, long telefono) {
        if (usuarios.containsKey(user)) {
            return null;
        }
        Usuario nuevoUsuario = new Usuario(user, nombre, apellido, email, telefono);
        this.usuarios.put(user, nuevoUsuario);
        return nuevoUsuario;
    }

    public Usuario eliminar(String user) {
        return usuarios.remove(user);
    }

    public List<Usuario> buscarUsuariosPorUser(String user) {
        return usuarios.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(user) || entry.getKey().startsWith(user))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }


    public Usuario buscarUsuarioPorUser(String user) {
        return usuarios.get(user);
    }

    public List<Notificacion> leerNotificacionesPendientes(String user) {
        Usuario usuario = usuarios.get(user);
        if (usuario != null) {
            return usuario.leerNotificacionesPendientes();
        }
        return null;
    }

    public Usuario agregarNotificacion(String user, Notificacion notificacion) {
        Usuario usuario = usuarios.get(user);
        if (usuario != null) {
            usuario.agregarNotificacion(notificacion);
        }
        return usuario;
    }
}
