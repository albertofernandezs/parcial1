package py.una.server.tcp;


import java.io.*;
import java.net.*;

import py.una.entidad.Persona;
import py.una.entidad.PersonaJSON;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            unSocket = new Socket("localhost", 4444);
            // enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);

            //viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }

        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        String fromUser,nombre,apellido,chapa,marca;
        int opcion, cedula;
        while ((fromServer = in.readLine()) != null) {
            System.out.println("Servidor: " + fromServer);
            if (fromServer.equals("Bye")) {
                break;
            }
            System.out.println("Ingrese el (numero) tipo de operacion: ");
            System.out.println("Tipo de operación 1: Recibir datos de personas y vehiculos");
            System.out.println("Tipo de operación 2: Consultar la lista de vehiculos de una cedula ");
            opcion= Integer.parseInt(stdIn.readLine());
            //fromUser = stdIn.readLine();
            if(opcion==1) {
            	System.out.println("Ingrese el cedula, nombre, apellido, chapa y marca ");
            	cedula=Integer.parseInt(stdIn.readLine());
            	nombre= stdIn.readLine();
            	apellido=stdIn.readLine();
            	chapa=stdIn.readLine();
            	marca=stdIn.readLine();
            	Persona persona= new Persona(cedula, nombre, apellido, chapa, marca);
            	fromUser=PersonaJSON.objetoString(persona);
            	out.println(fromUser);
            }else if(opcion==2) {
            	fromUser="2";
            	out.println(fromUser);
            }
            /*if (fromUser != null) {
                System.out.println("Cliente: " + fromUser);

                //escribimos al servidor
                out.println(fromUser);
            }*/
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
    
    public void submenu() {
    	System.out.println("Ingrese el (numero) tipo de operacion: ");
    	System.out.println("Ingrese el (numero) tipo de operacion: ");
    }
}
