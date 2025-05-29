/**
 * @author TW MANHEMA
 * @version 3
 */
package com.http.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 */
public class HttpServer {
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
	private int port = 0;
	
	public HttpServer(int port) {
		this.port = port;
	}
	
	public void serverRun() {
		try {
			serverSocket = new ServerSocket(port);
			while(true) {
				System.out.println("Waiting for connection on port: " + port);
				Thread threading = new Thread(new ClientHandler( serverSocket.accept()));
				threading.start();
			}//end while
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
