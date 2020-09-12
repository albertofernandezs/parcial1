package py.una.server.tcp;

import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import py.una.entidad.Persona;
import py.una.entidad.PersonaJSON;

import java.io.*;
import java.lang.reflect.UndeclaredThrowableException;

public class TCPServerHilo extends Thread {

    private Socket socket = null;

    TCPMultiServer servidor;
    
    public TCPServerHilo(Socket socket, TCPMultiServer servidor ) {
        super("TCPServerHilo");
        this.socket = socket;
        this.servidor = servidor;
    }

    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    socket.getInputStream()));
            out.println("Bienvenido!");
            String inputLine, outputLine="";

            while ((inputLine = in.readLine()) != null) {
                //System.out.println("Mensaje recibido: " + inputLine);
                
                //out.println(inputLine);
                
                //to-do: utilizar json
                if (inputLine.equals("Bye")) {
                    outputLine = "Usted apago el hilo";
                    break;
                    
                }else if (inputLine.equals("Terminar todo")) {
                    servidor.listening = false;
                    outputLine = "Usted apago todo";
                    break;
                    
                }else if (inputLine.equals("2")) {
                	outputLine="Se han listado";
                	
                	Iterator itera = servidor.usuarios.iterator();
                	
                	while (itera.hasNext()){
                   		
                	List <String []> iter = (List <String []>)itera.next();
                	
                	Iterator itera2= iter.iterator();
                	
                		while (itera.hasNext()){
                			System.out.println(itera2.next());
                		}
                	
                	
                	}
                   	

                }else {
                	try {
                	
                		String [] auto= {};
                		List<String []> list =new  ArrayList<String []>();
                		Persona	p= PersonaJSON.stringObjeto(inputLine);
                		if(servidor.usuarios.get(p.getCedula())!=null) {
                			list= servidor.usuarios.get(p.getCedula());
                			auto[0]=p.getChapa();
                    		auto[1]=p.getMarca();
                    		list.add(auto);
                    		servidor.usuarios.add(p.getCedula(), list);
                    		outputLine = "Se ha agregado un auto a la persona " ;
                		}else {
                			auto[0]=p.getChapa();
                    		auto[1]=p.getMarca();
                    		list.add(auto);
                    		servidor.usuarios.add(p.getCedula(), list);
                    		outputLine = "Se ha agregado un auto a la persona " ;
                		}
                		
                	}catch(Exception e) {
                		
                	}
                	
                }
                
                
                out.println(outputLine);
            }
            out.close();
            in.close();
            socket.close();
            System.out.println("Finalizando Hilo");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
