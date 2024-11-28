package org.iesalandalus.programacion.robot.modelo;

import java.util.Objects;

public class Robot {
    private Coordenada coordenada;
    private Orientacion orientacion;
    private Zona zona;

    public Robot() {
        zona = new Zona();
        orientacion = Orientacion.NORTE;
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona ){
        setZona(zona);
        orientacion = Orientacion.NORTE;
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona, Orientacion orientacion ) {
        setZona(zona);
        setOrientacion(orientacion);
        coordenada = zona.getCentro();
    }

    public Robot(Zona zona, Orientacion orientacion, Coordenada coordenada ) {
        setZona(zona);
        setOrientacion(orientacion);
        setCoordenada(coordenada);
    }

    public Robot( Robot robot ) {
        Objects.requireNonNull(robot,"El robot no puede ser nulo.");
        coordenada = robot.coordenada;
        orientacion = robot.orientacion;
        zona = robot.zona;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        Objects.requireNonNull(coordenada,"La coordenada no puede ser nula.");
        if (!zona.pertenece(coordenada)){
            throw new IllegalArgumentException("La coordenada no pertenece a la zona." );
        }
        this.coordenada = coordenada;

    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = Objects.requireNonNull(orientacion, "La orientación no puede ser nula.");
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = Objects.requireNonNull(zona, "La zona no puede ser nula.");
    }

    public void avanzar() throws RobotExcepcion {
        int nuevaX = coordenada.x();
        int nuevaY = coordenada.y();
        switch (orientacion) {
            case NORTE:
                nuevaY++;
                break;
            case NORESTE:
                nuevaY++;
                nuevaX++;
                break;
            case ESTE:
                nuevaX++;
                break;
            case SURESTE:
                nuevaY--;
                nuevaX++;
                break;
            case SUR:
                nuevaY--;
                break;
            case SUROESTE:
                nuevaY--;
                nuevaX--;
                break;
            case OESTE:
                nuevaX--;
                break;
            case NOROESTE:
                nuevaY++;
                nuevaX--;
                break;
        }
        try {
            setCoordenada(new Coordenada(nuevaX , nuevaY));
        }catch (IllegalArgumentException e){
            throw new RobotExcepcion("No se puede avanzar, ya que se sale de la zona." );
        }
    }

    public void girarALaDerecha() {
        Orientacion nuevaOrientacion = null;
        switch (orientacion) {
            case NORTE -> nuevaOrientacion = Orientacion.NORESTE;
            case NORESTE -> nuevaOrientacion = Orientacion.ESTE;
            case ESTE -> nuevaOrientacion = Orientacion.SURESTE;
            case SURESTE -> nuevaOrientacion = Orientacion.SUR;
            case SUR -> nuevaOrientacion = Orientacion.SUROESTE;
            case SUROESTE -> nuevaOrientacion = Orientacion.OESTE;
            case OESTE -> nuevaOrientacion = Orientacion.NOROESTE;
            case NOROESTE -> nuevaOrientacion = Orientacion.NORTE;
        }
        try {
            orientacion = nuevaOrientacion;
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException("Giro no valido." +e.getMessage());
        }
    }

    public void girarALaIzquierda() {
        Orientacion nuevaOrientacion = null;
        switch (orientacion) {
            case NORTE -> nuevaOrientacion = Orientacion.NOROESTE;
            case NORESTE -> nuevaOrientacion = Orientacion.NORTE;
            case ESTE -> nuevaOrientacion = Orientacion.NORESTE;
            case SURESTE -> nuevaOrientacion = Orientacion.ESTE;
            case SUR -> nuevaOrientacion = Orientacion.SURESTE;
            case SUROESTE -> nuevaOrientacion = Orientacion.SUR;
            case OESTE -> nuevaOrientacion = Orientacion.SUROESTE;
            case NOROESTE -> nuevaOrientacion = Orientacion.OESTE;
        }
        try {
            orientacion = nuevaOrientacion;
        }catch (IllegalArgumentException e ){
            throw new IllegalArgumentException("Giro no valido." +e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(coordenada, robot.coordenada) && orientacion == robot.orientacion && Objects.equals(zona, robot.zona);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordenada, orientacion, zona);
    }

    @Override
    public String toString() {
        return String.format("[coordenada=%s, orientacion=%s, zona=%s]", coordenada, orientacion, zona);
    }
}
