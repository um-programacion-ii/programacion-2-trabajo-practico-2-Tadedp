package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.Prestable;
import src.interfaces.Renovable;

public class Libro extends RecursoDigital implements Prestable, Renovable {
    private int paginas;
    private String genero;
    private long vecesPrestado;
    private boolean disponible;
    private boolean renovable;

    public Libro(CategoriaRecurso categoriaRecurso, long id, String titulo, String autor) {
        super(id, titulo, autor, categoriaRecurso);
        this.vecesPrestado = 0;
        this.disponible = true;
        this.renovable = true;
    }

    public Libro(CategoriaRecurso categoriaRecurso, long id, String titulo, String autor, int paginas, String genero) {
        super(id, titulo, autor, categoriaRecurso);
        this.paginas = paginas;
        this.genero = genero;
        this.vecesPrestado = 0;
        this.disponible = true;
        this.renovable = true;
    }

    public int getPaginas() {
        return paginas;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public long getVecesPrestado() {
        return vecesPrestado;
    }

    @Override
    public void prestar() {
        this.disponible = false;
        vecesPrestado += 1;
        this.renovable = true;
    }

    @Override
    public void devolver() {
        this.disponible = true;
    }

    @Override
    public boolean puedePrestarse() {
        return disponible;
    }

    @Override
    public void renovar() {
        this.renovable = false;
    }

    @Override
    public boolean puedeRenovarse() {
        return renovable;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + this.getId() +
                ", titulo='" + this.getTitulo() + '\'' +
                ", autor='" + this.getAutor() + '\'' +
                ", estado=" + this.getEstadoRecurso() +
                ", paginas=" + paginas +
                ", genero='" + genero + '\'' +
                ", vecesPrestado=" + vecesPrestado +
                ", disponible=" + disponible +
                ", renovable=" + renovable +
                '}';
    }
}
