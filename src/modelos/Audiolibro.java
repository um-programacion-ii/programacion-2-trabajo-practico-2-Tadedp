package src.modelos;

import src.enums.EstadoRecurso;
import src.interfaces.Prestable;

public class Audiolibro extends RecursoDigital implements Prestable {
    private int duracionMin;
    private String narrador;
    private long vecesPrestado;
    private boolean disponible;

    public Audiolibro(long id, String titulo, String autor) {
        super(id, titulo, autor);
        this.vecesPrestado = 0;
        this.disponible = true;
    }

    public Audiolibro(long id, String titulo, String autor, int duracionMin, String narrador) {
        super(id, titulo, autor);
        this.duracionMin = duracionMin;
        this.narrador = narrador;
        this.vecesPrestado = 0;
        this.disponible = true;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public String getNarrador() {
        return narrador;
    }

    @Override
    public long getVecesPrestado() {
        return vecesPrestado;
    }

    @Override
    public void prestar() {
        this.disponible = false;
        vecesPrestado += 1;
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
    public String toString() {
        return "Audiolibro{" +
                "id=" + this.getId() +
                ", titulo='" + this.getTitulo() + '\'' +
                ", autor='" + this.getAutor() + '\'' +
                ", estado=" + this.getEstadoRecurso() +
                ", duracionMin=" + duracionMin +
                ", narrador='" + narrador + '\'' +
                ", vecesPrestado=" + vecesPrestado +
                ", disponible=" + disponible +
                '}';
    }
}
