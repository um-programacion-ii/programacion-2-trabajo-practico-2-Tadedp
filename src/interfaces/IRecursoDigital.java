package src.interfaces;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;

public interface IRecursoDigital {
    String getTitulo();
    String getAutor();
    long getId();
    EstadoRecurso getEstadoRecurso();
    CategoriaRecurso getCategoriaRecurso();
    void actualizarEstado (EstadoRecurso estadoRecurso);
}
