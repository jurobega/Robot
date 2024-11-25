package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public record Zona(int ancho, int alto ) {
    public static final int ANCHO_MINIMO = 10;
    public static final int ANCHO_MAXIMO = 100;
    public static final int ALTO_MINIMO = 10;
    public static final int ALTO_MAXIMO = 100;

    public Zona {
        try {
            validarAncho(this.ancho());
            validarAlto(this.alto());
        } catch (IllegalArgumentException | RobotExcepcion e) {
            throw new IllegalArgumentException("El ancho y el alto no son validos." + e.getMessage());
        }
    }

    public Zona(){
        this(ANCHO_MINIMO, ALTO_MINIMO);
    }

    private void validarAncho(int ancho ) throws RobotExcepcion{
        if (ancho < ANCHO_MINIMO || ancho > ANCHO_MAXIMO) {
            throw new RobotExcepcion("El ancho no es válido.");
        }
    }

    private void validarAlto(int alto) throws RobotExcepcion{
        if (alto < ALTO_MINIMO || alto > ALTO_MAXIMO ){
            throw new RobotExcepcion("El alto no es válido.");
        }
    }

    public Coordenada getCentro(){
        return new Coordenada(this.ancho /2 ,this.alto / 2);
    }

    public boolean pertenece(Coordenada coordenada ){
        Objects.requireNonNull(coordenada,"La coordenada no puede ser nula");
        return (perteneceX(coordenada.x()) || pertenceY(coordenada.y()));
    }

    private boolean perteneceX(int x) {
        return (x > 0 || x < ancho);
    }

    private boolean pertenceY(int y) {
        return (y > 0 || y < alto);
    }

}
