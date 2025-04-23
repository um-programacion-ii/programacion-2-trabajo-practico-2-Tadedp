package src.gestores;

import src.enums.CategoriaRecurso;
import src.modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorRecursos {
    private List<RecursoDigital> recursos = new ArrayList<>();
    private int contadorRecursos;

    public GestorRecursos() {
        contadorRecursos = 0;
    }

    public GestorRecursos(List<RecursoDigital> recursos) {
        this.recursos = recursos;
        contadorRecursos = 0;
    }

    public List<RecursoDigital> getRecursos() {
        return recursos;
    }

    public RecursoDigital agregar(CategoriaRecurso categoriaRecurso, String titulo, String autor, String paramStringRecurso, int paramIntRecurso) {
        long nuevoId = contadorRecursos + 1;
        RecursoDigital nuevoRecurso;
        switch (categoriaRecurso) {
            case CategoriaRecurso.LIBRO -> nuevoRecurso = new Libro(categoriaRecurso, nuevoId, titulo, autor, paramIntRecurso, paramStringRecurso);
            case CategoriaRecurso.REVISTA -> nuevoRecurso = new Revista(categoriaRecurso, nuevoId, titulo, autor, paramStringRecurso, paramIntRecurso);
            case CategoriaRecurso.AUDIOLIBRO -> nuevoRecurso = new Audiolibro(categoriaRecurso, nuevoId, titulo, autor, paramIntRecurso, paramStringRecurso);
            default -> nuevoRecurso = null;
        }
        recursos.add(nuevoRecurso);
        contadorRecursos += 1;
        return nuevoRecurso;
    }

    public RecursoDigital eliminar(long id) {
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                recursos.remove(recurso);
                return recurso;
            }
        }
        return null;
    }

    public List<RecursoDigital> buscarPorTitulo(String titulo) {
        return recursos.stream()
                .filter(recurso -> recurso.getTitulo().equals(titulo) || recurso.getTitulo().startsWith(titulo))
                .collect(Collectors.toList());
    }

    public List<RecursoDigital> buscarPorAutor(String autor) {
        return recursos.stream()
                .filter(recurso -> recurso.getAutor().equals(autor) || recurso.getAutor().startsWith(autor))
                .collect(Collectors.toList());
    }

    public RecursoDigital prestar(long id){
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                switch (recurso) {
                    case Libro libro -> {
                        if (libro.puedePrestarse()){
                            libro.prestar();
                            return libro;
                        } else {
                            return null;
                        }
                    }
                    case Revista revista -> {
                        if (revista.puedePrestarse()){
                            revista.prestar();
                            return revista;
                        } else {
                            return null;
                        }
                    }
                    case Audiolibro audiolibro -> {
                        if (audiolibro.puedePrestarse()){
                            audiolibro.prestar();
                            return audiolibro;
                        } else {
                            return null;
                        }
                    }
                    default -> { return null; }
                }
            }
        }
        return null;
    }

    public RecursoDigital devolver(long id){
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                switch (recurso) {
                    case Libro libro -> {
                        if (!libro.puedePrestarse()){
                            libro.devolver();
                        } else {
                            return null;
                        }
                    }
                    case Revista revista -> {
                        if (!revista.puedePrestarse()){
                            revista.devolver();
                        } else {
                            return null;
                        }
                    }
                    case Audiolibro audiolibro -> {
                        if (!audiolibro.puedePrestarse()){
                            audiolibro.devolver();
                        } else {
                            return null;
                        }
                    }
                    default -> { return null; }
                }
                return recurso;
            }
        }
        return null;
    }

    public RecursoDigital renovar(long id){
        for (RecursoDigital recurso : recursos) {
            if (recurso.getId() == id) {
                switch (recurso) {
                    case Libro libro -> {
                        if (libro.puedeRenovarse()){
                            libro.renovar();
                        } else {
                            return null;
                        }
                    }
                    case Revista revista -> {
                        if (revista.puedeRenovarse()){
                            revista.renovar();
                        } else {
                            return null;
                        }
                    }
                    default -> { return null; }
                }
                return recurso;
            }
        }
        return null;
    }
}
