package src.interfaces;

import src.enums.EstadoRecurso;

public interface IRecursoDigital {
    String getTitulo();
    String getAutor();
    long getId();
    EstadoRecurso getEstadoRecurso();
    void actualizarEstado (EstadoRecurso estadoRecurso);
}
