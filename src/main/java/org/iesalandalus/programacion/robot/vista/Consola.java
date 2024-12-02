package org.iesalandalus.programacion.robot.vista;

import org.iesalandalus.programacion.robot.modelo.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {

    private Consola() {}

    public static void mostrarMenuprincipal() {
        System.out.println("--------Menu Principal----------");
        System.out.println("1-Controlar a un Robot por defecto.");
        System.out.println("2-Controlar a un Robot indicando su zona.");
        System.out.println("3-Controlar a un Robot indicando su zona y su orientación.");
        System.out.println("4-Controlar a un Robot indicando su zona, su orientación y su coordenada.");
        System.out.println("5-Ejecutar comando.");
        System.out.println("6-Salir");
    }

    public static int elegirOpcion() {
        mostrarMenuprincipal();
        int opcion;
        do {
            System.out.println("Elige una opción: ");
            opcion = Entrada.entero();
        } while (opcion < 0 || opcion > 6);
        return opcion;
    }

    public static Zona elegirZona() {
        int ancho;
        int alto;
        System.out.print("Dame el ancho de la zona: ");
        ancho = Entrada.entero();
        System.out.println("Dame el alto de la zona: ");
        alto = Entrada.entero();
        return new Zona(ancho, alto);
    }

    public static void mostrarMenuOrientacion() {
        System.out.println("Norte.");
        System.out.println("Noreste");
        System.out.println("Este.");
        System.out.println("Sureste.");
        System.out.println("Sur.");
        System.out.println("Suroeste.");
        System.out.println("Oeste.");
        System.out.println("Noroeste.");
    }

    public static Orientacion elegirOrientacion() {
        mostrarMenuOrientacion();
        String opcion;
        Orientacion orientacion;
        int i;
        do {
            System.out.println("Dame la orientación del robot: ");
            opcion = Entrada.cadena();
            orientacion = Orientacion.valueOf(opcion);
            i = orientacion.ordinal();
        } while ( i > 7 );
        return orientacion;
    }

    public static Coordenada elegirCoordenada() {
        int x;
        int y ;
        System.out.println("Dime la X que tendrá el robot: ");
        x = Entrada.entero();
        System.out.println("Dime la Y que tendrá el robot: ");
        y  = Entrada.entero();
        return new Coordenada(x, y );
    }

    public static char elegirComando() {
        char comando;
        System.out.println("Dime que comando quieres ejecutar: ");
        comando = Entrada.caracter();
        return comando;
    }

    public static void mostrarRobot(ControladorRobot controladorRobot) {
        try {
            System.out.println(controladorRobot.getRobot());
        } catch (NullPointerException e) {
            throw new NullPointerException("El robot es nulo, "+e.getMessage());
        }

    }

    public static void despedirse() {
        System.out.println("Se ha cerrado la aplicación");
    }

}
