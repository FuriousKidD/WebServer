/**
 * @author TW MANHEMA
 * @version 3
 */
package com.http.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 */
public class ClientHandler implements Runnable {
	private final String CRLF = "\r\n";
	private Socket clientSocket = null;
	private BufferedReader bin = null;
	private DataOutputStream dout = null;
	private File file = null;
	
	public ClientHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			processRequest();
			sendBytes();
		}//end try
		catch (Exception e) {
			//e.printStackTrace();
			System.err.println(e.getMessage());
		}//end catch
	}
	
	/**
	 * This method is used for processing the Requests made by the 
	 * client to the HTTP server
	 * @throws Exception
	 */
	private void processRequest() throws Exception{
		//handling of the I/O streams of the server
		bin = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		dout = new DataOutputStream(clientSocket.getOutputStream());
		
		
		//First line containing the request message
		
		String requestLine = bin.readLine();/* GET /url HTTP/version */
		String [] line = requestLine.split("\\s",3);
		
		String requestCommand  = line[0];/* takes the first cmd eg. POST*/
		String filePath = line[1].substring(1, line[1].length());/* removes the first / and takes url*/
		String httpVersion = line[2];

		int responseCode;
		//
		filePath = "data/" + filePath;
		file = new File(filePath);
		if(file.exists()) {
			
			if(requestCommand.matches("GET")) {
				responseCode = 200;
				sendResponse(responseCode, httpVersion, filePath, file);
				
			}//end inner if
			else {
				responseCode = 500;
				sendResponse(responseCode, httpVersion, filePath, file);
				
			}//end inner else
		}//end outter if
		else if(!file.exists()) {
			responseCode = 404;
			sendResponse(responseCode, httpVersion, filePath, file);
			
		}//end outter else
	
	}//end processRequest
	
	/**
	 * This method writes a response to the client based on the HTTP protocol
	 * Header lines
	 * Response code
	 * Content-Length
	 * Content-Type
	 * Extra Carriage Return Line Feed
	 * @param code response code
	 * @param version HTTP/version 
	 * @param filePath URL for the file path
	 * @param file 
	 * @throws Exception
	 */
	private void sendResponse(int code, String version, String filePath, File file) throws Exception{
		
		if(code == 200) {
			
			dout.writeBytes(version + " " + code + " OK" + CRLF);
			System.out.println(version + " " + code + " OK");
			
			dout.writeBytes("Content-Length: " + file.length() + CRLF);
			System.out.println("Content-Length: " + file.length());
			
			dout.writeBytes(contentType(filePath));
			System.out.println(contentType(filePath));
			
			dout.writeBytes(CRLF);
			
		}//end if
		else if(code == 404) {
			
			dout.writeBytes(version + " " + code + " Not Found" + CRLF);
			System.out.println(version + " " + code + " Not Found");
			
			dout.writeBytes("Content-Length: " + file.length() + CRLF);
			System.out.println("Content-Length: " + file.length());
			
			dout.writeBytes(contentType(filePath));
			System.out.println(contentType(filePath));
			
			dout.writeBytes(CRLF);
			
			String htmlMessage = "<html>"
					+ "<head><title> 404 NOT FOUND! </title></head>"
					+ "<body><p>File specified could not be found</p></body>"
					+ "</html>";
			dout.writeBytes(htmlMessage);
		
		}//end else if
		else if(code == 500) {
			
			dout.writeBytes(version + " " + code + " Internal Server Error" + CRLF);
			System.out.println(version + " " + code + " Internal Server Error");
			
			dout.writeBytes("Content-Length: " + file.length() + CRLF);
			System.out.println("Content-Length: " + file.length());
			
			dout.writeBytes(contentType(filePath));
			System.out.println(contentType(filePath));
			
			dout.writeBytes(CRLF);
		
		}//end else if
	}//end sendResponse
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	private String contentType(String file) {
		String content = "text/plain";
		
		if(file.endsWith(".htm") || file.endsWith(".html")) {
			content = "Content-Type: text/html" + CRLF;
		}//end if
		else if(file.endsWith(".jpg") || file.endsWith(".jpeg")) {
			content = "Content-Type: image/jpeg" + CRLF;
		}//end if
		else if(file.endsWith(".mp4") || file.endsWith(".m4v")) {
			content = "Content-Type: video/mp4" + CRLF;
		}//end if
		else {
			content = "Content-Type: text/html" + CRLF;
		}
		
		return content;
	}//end contentType
	
	
	/**
	 * This method reads the binary data of the files requested by the client
	 * and are written to the client as part of the output
	 * @throws Exception
	 */
	private void sendBytes() throws Exception{
		try (BufferedInputStream bin = new BufferedInputStream(new FileInputStream(file))) {
			byte [] buffer = new byte [1024];
			int bytes = 0;
			
			while((bytes = bin.read(buffer)) != -1) {
				dout.write(buffer, 0, bytes);
			}//end while
			dout.writeBytes(CRLF);
			dout.flush();
		}
	}//end sendBytes
	
	

}
