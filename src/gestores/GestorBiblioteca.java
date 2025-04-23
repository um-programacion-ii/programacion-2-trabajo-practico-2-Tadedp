package src.gestores;

import src.enums.CategoriaRecurso;
import src.enums.FormatoNotificacion;
import src.modelos.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorBiblioteca {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private GestorNotificaciones gestorNotificaciones;

    public GestorBiblioteca() {
        gestorUsuarios = new GestorUsuarios();
        gestorRecursos = new GestorRecursos();
        gestorNotificaciones = new GestorNotificaciones();
    }

    public GestorBiblioteca(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos, GestorNotificaciones gestorNotificaciones) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.gestorNotificaciones = gestorNotificaciones;
    }

    public Map<String, Usuario> getUsuarios(){
        return gestorUsuarios.getUsuarios();
    }

    public Usuario agregarUsuario(String user, String nombre, String apellido, String email, long telefono) {
        return gestorUsuarios.agregar(user, nombre, apellido, email, telefono);
    }

    public Usuario eliminarUsuario(String user) {
        return gestorUsuarios.eliminar(user);
    }

    public List<Usuario> buscarUsuariosPorUser(String user) {
        return gestorUsuarios.buscarUsuariosPorUser(user);
    }

    public List<Notificacion> leerNotificacionesPendientes(String user) {
        return gestorUsuarios.leerNotificacionesPendientes(user);
    }

    public Usuario agregarNotificacion(String user, String contenido, FormatoNotificacion formatoNotificacion) {
        Usuario usuario = gestorUsuarios.buscarUsuarioPorUser(user);
        if (usuario != null) {
            Notificacion notificacion;
            if (formatoNotificacion == FormatoNotificacion.EMAIL) {
                notificacion = gestorNotificaciones.enviarNotificacion(contenido, formatoNotificacion, usuario.getEmail());
            } else {
                notificacion = gestorNotificaciones.enviarNotificacion(contenido, formatoNotificacion, String.valueOf(usuario.getTelefono()));
            }
            return gestorUsuarios.agregarNotificacion(user, notificacion);
        }
        return null;
    }

    public List<RecursoDigital> getRecursos() {
        return gestorRecursos.getRecursos();
    }

    public RecursoDigital agregarRecurso(CategoriaRecurso categoriaRecurso, String titulo, String autor, String paramStringRecurso, int paramIntRecurso) {
        return gestorRecursos.agregar(categoriaRecurso, titulo, autor, paramStringRecurso, paramIntRecurso);
    }

    public RecursoDigital eliminarRecurso(long id) {
        return gestorRecursos.eliminar(id);
    }

    public List<RecursoDigital> buscarRecursosPorTitulo(String titulo) {
        return gestorRecursos.buscarPorTitulo(titulo);
    }

    public List<RecursoDigital> buscarRecursosPorAutor(String autor) {
        return gestorRecursos.buscarPorAutor(autor);
    }

    public RecursoDigital prestar(long id){
        return gestorRecursos.prestar(id);
    }

    public RecursoDigital devolver(long id){
        return gestorRecursos.devolver(id);
    }

    public RecursoDigital renovar(long id){
        return gestorRecursos.renovar(id);
    }
}
