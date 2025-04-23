package src.modelos;

import src.enums.EstadoRecurso;
import src.interfaces.Prestable;
import src.interfaces.Renovable;

public class Revista extends RecursoDigital implements Prestable, Renovable {
    private String categoria;
    private int numero;
    private long vecesPrestado;
    private boolean disponible;
    private boolean renovable;

    public Revista(long id, String titulo, String autor) {
        super(id, titulo, autor);
        this.vecesPrestado = 0;
        this.disponible = true;
        this.renovable = true;
    }

    public Revista(long id, String titulo, String autor, String categoria, int numero) {
        super(id, titulo, autor);
        this.categoria = categoria;
        this.numero = numero;
        this.vecesPrestado = 0;
        this.disponible = true;
        this.renovable = true;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getNumero() {
        return numero;
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
        return "Revista{" +
                "id=" + this.getId() +
                ", titulo='" + this.getTitulo() + '\'' +
                ", autor='" + this.getAutor() + '\'' +
                ", estado=" + this.getEstadoRecurso() +
                ", categoria='" + categoria + '\'' +
                ", numero=" + numero +
                ", vecesPrestado=" + vecesPrestado +
                ", disponible=" + disponible +
                ", renovable=" + renovable +
                '}';
    }
}
