package src.CLI;

import src.enums.CategoriaRecurso;
import src.enums.FormatoNotificacion;
import src.gestores.GestorBiblioteca;
import src.modelos.Notificacion;
import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.util.List;
import java.util.Scanner;

public class Consola {
    private final GestorBiblioteca gestorBiblioteca;
    private final Scanner scanner;

    public Consola(){
        gestorBiblioteca = new GestorBiblioteca();
        scanner = new Scanner(System.in);
        System.out.println("Iniciando sistema de gestión de biblioteca digital...");
    }

    public Consola(GestorBiblioteca gestorbiblioteca){
        gestorBiblioteca = gestorbiblioteca;
        scanner = new Scanner(System.in);
        System.out.println("Iniciando sistema de gestión de biblioteca digital...");
    }

    public void iniciar(){
        int inputUsuario;
        do {
            imprimirMenuPrincipal();

            System.out.print("Seleccione una opción (1-4): ");
            try {
                inputUsuario = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                inputUsuario = 0;
            }

            switch (inputUsuario) {
                case 1 -> submenuUsuarios();
                case 2 -> submenuRecursos();
                case 3 -> submenuPrestamos();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida. Ingrese un número entre 1 y 4.");
            }

        } while (inputUsuario != 4);
    }

    private void imprimirMenuPrincipal() {
        System.out.print("""
                ---------- Menú ----------
                1- Gestionar usuarios
                2- Gestionar recursos
                3- Gestionar préstamos
                4- Salir
                --------------------------
                """);
    }

    private void submenuUsuarios() {
        int inputUsuario;
        do {
            imprimirSubmenuUsuarios();

            System.out.print("Seleccione una opción (1-7): ");
            try {
                inputUsuario = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                inputUsuario = 0;
            }

            switch (inputUsuario) {
                case 1 -> System.out.println("Usuarios en el sistema:" + gestorBiblioteca.getUsuarios());
                case 2 -> {
                    String user = leerUser();
                    List<Usuario> usuarios = gestorBiblioteca.buscarUsuariosPorUser(user);
                    if (usuarios.isEmpty()) {
                        System.out.println("Usuario '" + user + "' no encontrado.");
                    } else {
                        System.out.println("Usuarios encontrados: " + usuarios);
                    }
                }
                case 3 -> {
                    try {
                        String user = leerUser();
                        System.out.print("Ingrese un nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Ingrese un apellido: ");
                        String apellido = scanner.nextLine();
                        System.out.print("Ingrese una dirección de email: ");
                        String email = scanner.nextLine();
                        System.out.print("Ingrese un número de teléfono: ");
                        long telefono = Long.parseLong(scanner.nextLine());

                        Usuario usuario = gestorBiblioteca.agregarUsuario(user, nombre, apellido, email, telefono);

                        System.out.println("Usuario '" + user + "' agregado exitosamente.");

                    } catch (Exception e) {
                        System.out.println("ERROR: Datos inválidos. Intente nuevamente.");
                    }
                }
                case 4 -> {
                    String user = leerUser();
                    Usuario usuario = gestorBiblioteca.eliminarUsuario(user);
                    if (usuario == null) {
                        System.out.println("Usuario '" + user + "' no encontrado.");
                    } else {
                        System.out.println("Usuario '" + user + "' eliminado exitosamente.");
                    }
                }
                case 5 -> {
                    String user = leerUser();
                    List<Notificacion> notificacionesPendientes = gestorBiblioteca.leerNotificacionesPendientes(user);

                    if (notificacionesPendientes == null) {
                        System.out.println("Usuario '" + user + "' no encontrado.");
                    } else if (notificacionesPendientes.isEmpty()) {
                        System.out.println("El usuario'" + user + "' no tiene notificaciones pendientes.");
                    } else {
                        System.out.println("Notificaciones del usuario'" + user + "': " + notificacionesPendientes);
                    }
                }
                case 6 -> {
                    String user = leerUser();
                    System.out.print("Ingrese el contenido de la notificación: ");
                    String contenido = scanner.nextLine();
                    System.out.print("Ingrese el formato de la notificación (SMS - EMAIL): ");
                    String formato = scanner.nextLine().toUpperCase();

                    if (!formato.equals("SMS") && !formato.equals("EMAIL")) {
                        System.out.println("ERROR: Datos inválidos. Intente nuevamente.");
                    } else {
                        FormatoNotificacion formatoNotificacion;
                        if (formato.equals("SMS")) {
                            formatoNotificacion = FormatoNotificacion.SMS;
                        } else {
                            formatoNotificacion = FormatoNotificacion.EMAIL;
                        }
                        Usuario usuario = gestorBiblioteca.agregarNotificacion(user, contenido, formatoNotificacion);
                        if (usuario == null) {
                            System.out.println("Usuario'" + user + "' no encontrado.");
                        } else {
                            System.out.println("Notificación enviada al usuario '" + user + "' a través de " + formato);
                        }
                    }
                }
                case 7 -> iniciar();
                default -> System.out.println("Opción inválida. Ingrese un número entre 1 y 7.");
            }

        } while (inputUsuario != 7);
    }

    private void imprimirSubmenuUsuarios() {
        System.out.print("""
                ----- Menú Usuarios ------
                1- Listar usuarios
                2- Buscar usuario
                3- Agregar usuario
                4- Eliminar usuario
                5- Leer notificaciones
                6- Enviar notificación
                7- Volver
                --------------------------
                """);
    }

    private void submenuRecursos() {
        int inputUsuario;
        do {
            imprimirSubmenuRecursos();

            System.out.print("Seleccione una opción (1-5): ");
            try {
                inputUsuario = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                inputUsuario = 0;
            }

            switch (inputUsuario) {
                case 1 -> System.out.println("Recursos en el sistema: " + gestorBiblioteca.getRecursos());
                case 2 -> {
                    System.out.print("""
                                    Puede buscar por:
                                    1- Título
                                    2- Autor
                                    Seleccione una opción (1-2):\s""");

                    int inputBusqueda;
                    try {
                        inputBusqueda = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException exception) {
                        inputBusqueda = 0;
                    }

                    if (inputBusqueda == 1) {
                        System.out.print("Ingrese un título: ");
                        String titulo = scanner.nextLine();
                        List<RecursoDigital> recursos = gestorBiblioteca.buscarRecursosPorTitulo(titulo);

                        if (recursos.isEmpty()) {
                            System.out.println("Título '" + titulo + "' no encontrado.");
                        } else {
                            System.out.println("Recursos encontrados: " + recursos);
                        }
                    } else if (inputBusqueda == 2) {
                        System.out.print("Ingrese un autor: ");
                        String autor = scanner.nextLine();
                        List<RecursoDigital> recursos = gestorBiblioteca.buscarRecursosPorAutor(autor);

                        if (recursos.isEmpty()) {
                            System.out.println("Autor '" + autor + "' no encontrado.");
                        } else {
                            System.out.println("Recursos encontrados: " + recursos);
                        }
                    } else {
                        System.out.println("ERROR: Opción inválida. Intente nuevamente.");
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("Ingrese un título: ");
                        String titulo = scanner.nextLine();
                        System.out.print("Ingrese un autor: ");
                        String autor = scanner.nextLine();

                        System.out.print("""
                                    Puede agregar:
                                    1- Libro
                                    2- Revista
                                    3- Audiolibro
                                    Seleccione una opción (1-3):\s""");

                        int inputTipo;
                        try {
                            inputTipo = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException exception) {
                            inputTipo = 0;
                        }

                        RecursoDigital recurso = null;

                        if (inputTipo == 1) {
                            CategoriaRecurso categoriaRecurso = CategoriaRecurso.LIBRO;
                            System.out.print("Ingrese el género del libro: ");
                            String genero = scanner.nextLine();
                            System.out.print("Ingrese el número de páginas del libro: ");
                            int paginas = Integer.parseInt(scanner.nextLine());

                            recurso = gestorBiblioteca.agregarRecurso(categoriaRecurso, titulo, autor, genero, paginas);
                        } else if (inputTipo == 2) {
                            CategoriaRecurso categoriaRecurso = CategoriaRecurso.REVISTA;
                            System.out.print("Ingrese la categoría de la revista: ");
                            String categoria = scanner.nextLine();
                            System.out.print("Ingrese el número de la revista: ");
                            int numero = Integer.parseInt(scanner.nextLine());

                            recurso = gestorBiblioteca.agregarRecurso(categoriaRecurso, titulo, autor, categoria, numero);
                        } else if (inputTipo == 3) {
                            CategoriaRecurso categoriaRecurso = CategoriaRecurso.AUDIOLIBRO;
                            System.out.print("Ingrese el narrador del audiolibro: ");
                            String narrador = scanner.nextLine();
                            System.out.print("Ingrese la duración en minutos del audiolibro: ");
                            int duracionMin = Integer.parseInt(scanner.nextLine());

                            recurso = gestorBiblioteca.agregarRecurso(categoriaRecurso, titulo, autor, narrador, duracionMin);
                        } else {
                            System.out.println("ERROR: Opción inválida. Intente nuevamente.");
                        }

                        if (recurso != null) {
                            System.out.println("Recurso digital (ID: " + recurso.getId() + ") agregado exitosamente.");
                        }
                    } catch (Exception e) {
                        System.out.println("ERROR: Datos inválidos. Intente nuevamente.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese el id del recurso: ");

                    long inputId;
                    try {
                        inputId = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException exception) {
                        inputId = 0;
                    }

                    if (inputId <= 0) {
                        System.out.println("ERROR: id inválido. Intente nuevamente.");
                    } else {
                        RecursoDigital recurso = gestorBiblioteca.eliminarRecurso(inputId);
                        if (recurso == null) {
                            System.out.println("Recurso digital (ID: " + inputId + ") no encontrado.");
                        } else {
                            System.out.println("Recurso digital (ID: " + inputId + ") eliminado exitosamente.");
                        }
                    }
                }
                case 5 -> iniciar();
                default -> System.out.println("Opción inválida. Ingrese un número entre 1 y 5.");
            }

        } while (inputUsuario != 5);
    }

    private void imprimirSubmenuRecursos() {
        System.out.print("""
                ----- Menú Recursos ------
                1- Listar recursos
                2- Buscar recurso
                3- Agregar recurso
                4- Eliminar recurso
                5- Volver
                --------------------------
                """);
    }

    private void submenuPrestamos() {
        int inputUsuario;
        do {
            imprimirSubmenuPrestamos();

            System.out.print("Seleccione una opción (1-3): ");
            try {
                inputUsuario = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                inputUsuario = 0;
            }

            switch (inputUsuario) {
                case 1 -> {
                    System.out.print("Ingrese el id del recurso: ");

                    long inputId;
                    try {
                        inputId = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException exception) {
                        inputId = 0;
                    }

                    if (inputId <= 0) {
                        System.out.println("ERROR: id inválido. Intente nuevamente.");
                    } else {
                        RecursoDigital recurso = gestorBiblioteca.prestar(inputId);
                        if (recurso == null) {
                            System.out.println("El recurso (ID: " + inputId + ") no puede ser prestado.");
                        } else {
                            System.out.println("Recurso (ID: " + inputId + ") prestado exitosamente.");
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Ingrese el id del recurso: ");

                    long inputId;
                    try {
                        inputId = Long.parseLong(scanner.nextLine());
                    } catch (NumberFormatException exception) {
                        inputId = 0;
                    }

                    if (inputId <= 0) {
                        System.out.println("ERROR: id inválido. Intente nuevamente.");
                    } else {
                        RecursoDigital recurso = gestorBiblioteca.devolver(inputId);
                        if (recurso == null) {
                            System.out.println("El recurso (ID: " + inputId + ") no puede ser devuelto.");
                        } else {
                            System.out.println("Recurso (ID: " + inputId + ") devuelto exitosamente.");
                        }
                    }
                }
                case 3 -> iniciar();
                default -> System.out.println("Opción inválida. Ingrese un número entre 1 y 3.");
            }

        } while (inputUsuario != 3);
    }

    private void imprimirSubmenuPrestamos() {
        System.out.print("""
                ----- Menú Prestamos -----
                1- Prestar recurso
                2- Devolver recurso
                3- Volver
                --------------------------
                """);
    }

    private String leerUser() {
        System.out.print("Ingrese un user: ");
        return scanner.nextLine();
    }
}
