package src.server;

import java.io.IOException;
import java.net.ServerSocket;

public class ControleurServer 
{
    private int port = 8000;

    public void createServer()
    {
        try(ServerSocket serverSocket = new ServerSocket(port)) {
			// Accueillir les clients
			
		} catch(IOException e) {
			throw new RuntimeException("Impossible de créer le serveur : ", e);
		}
    }

    public static void main(String[] args)
    {

    } 
}


