package org.iesalandalus.programacion.robot;

import org.iesalandalus.programacion.robot.modelo.ControladorRobot;
import org.iesalandalus.programacion.robot.modelo.Robot;
import org.iesalandalus.programacion.robot.modelo.RobotExcepcion;
import org.iesalandalus.programacion.robot.vista.Consola;


import static org.iesalandalus.programacion.robot.vista.Consola.*;

public class Main {

    private static ControladorRobot controladorRobot;

    public static void main(String[] args) throws RobotExcepcion {
        int opcion;
        do {
            opcion = Consola.elegirOpcion();
            ejecutarOpcion(opcion);
            Consola.mostrarRobot(controladorRobot);
        }while (opcion != 6 );
    }



    private static void ejecutarOpcion( int opcion ) throws RobotExcepcion {
        switch (opcion) {
            case 1 -> controlarRobotDefecto();
            case 2 -> controlarRobotZona();
            case 3 -> controlarRobotZonaOrientacion();
            case 4 -> controlarRobotZonaOrientacionCoordenada();
            case 5 -> ejecutarComando();
            case 6 -> despedirse();
            default -> throw new RobotExcepcion("Opción no valida");
        }
    }



    private static void controlarRobotDefecto() {
        controladorRobot = new ControladorRobot(new Robot());
    }

    private static void controlarRobotZona() {
        controladorRobot = new ControladorRobot(new Robot(Consola.elegirZona()));
    }

    private static void controlarRobotZonaOrientacion() {
        controladorRobot = new ControladorRobot(new Robot(Consola.elegirZona(), Consola.elegirOrientacion()));
    }

    private static void controlarRobotZonaOrientacionCoordenada() {
        controladorRobot = new ControladorRobot(new Robot(Consola.elegirZona(), Consola.elegirOrientacion(), Consola.elegirCoordenada()));
    }

    private static void ejecutarComando() throws RobotExcepcion {
        if (controladorRobot != null ) {
            char comando;
            try {
                comando = Consola.elegirComando();
                controladorRobot.ejecutar(comando);
            }catch (IllegalArgumentException | RobotExcepcion e) {
                System.out.println("ERROR: " +e.getMessage());
            }
        }
    }
}
