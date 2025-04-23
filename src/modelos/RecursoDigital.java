package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.IRecursoDigital;

public abstract class RecursoDigital implements IRecursoDigital {
    private long id;
    private String titulo;
    private String autor;
    private EstadoRecurso estadoRecurso;
    private CategoriaRecurso categoriaRecurso;

    public RecursoDigital() {
    }

    public RecursoDigital(long id, String titulo, String autor, CategoriaRecurso categoriaRecurso) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.estadoRecurso = EstadoRecurso.DISPONIBLE;
        this.categoriaRecurso = categoriaRecurso;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    @Override
    public EstadoRecurso getEstadoRecurso() {
        return estadoRecurso;
    }

    @Override
    public CategoriaRecurso getCategoriaRecurso() {
        return categoriaRecurso;
    }

    @Override
    public void actualizarEstado(EstadoRecurso estadoRecurso) {
        this.estadoRecurso = estadoRecurso;
    }
}
