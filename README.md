# WebServer — Multi-threaded HTTP Server in Java

## Overview  
This project implements a basic multi-threaded web server in Java that handles HTTP requests on port 4321. It serves static HTML and image files based on client requests, supports multiple simultaneous connections using multithreading, and responds with appropriate HTTP status codes (200, 404, 500).

The server is designed as part of a practical assignment focused on understanding core networking concepts, HTTP protocol handling, and socket programming.

## Demo  
*Below are screenshots demonstrating the WebServer handling requests on localhost:*  

# Plain HTML 
<figure>
  <img width="959" alt="example PlainHtml" src="https://github.com/user-attachments/assets/118dc402-33d4-4885-982b-ab8432342061"/>
  <figcaption> Figure 1.1: Demo of opening a plain html webpage</figcaption>
</figure>

# Image only
<figure>
  <img width="959" alt="Example2-Image retrieved-ServerToClient" src="https://github.com/user-attachments/assets/9b64a692-0346-4895-bd3f-f9f3846af8b7" />
  <figcaption> Figure 1.2: Demo of opening a image only webpage</figcaption>
</figure>

# HTML with image
<figure>
  <img width="959" alt="Example HtmlWithImages" src="https://github.com/user-attachments/assets/98b681b0-c423-4b07-9111-3fd7a21ab999" />
  <figcaption> Figure 1.3: Demo of opening an html with image webpage</figcaption>
  </figure>

# Error 404 not found
<figure>
  <img width="959" alt="Example1-Error Messages" src="https://github.com/user-attachments/assets/2dfe919c-8b9a-402c-855c-1fa24d504b60" />
  <figcaption> Figure 1.4: Demo of opening a non existing webpage</figcaption>
</figure>

--- 
## Features  
- Listens on port **4321** for incoming client connections.  
- Processes HTTP requests according to the official protocol.  
- Serves static files including HTML and binary image files.  
- Supports multiple clients concurrently using multithreading.  
- Returns correct HTTP response codes:
  - **200 OK** for successful requests  
  - **404 Not Found** when requested resource is unavailable  
  - **500 Internal Server Error** for unexpected server errors  
- Supports specific resource paths:
  - `/Joburg` → `Joburg.html`  
  - `/Durban` → `Durban.html`  
  - `/Cape` → `CapeWithImage.html`  
  - `/Africa.jpg` → `Africa.jpg`  

## Getting Started

### Prerequisites  
- Java Development Kit (JDK) 8 or higher  
- Any modern web browser (Chrome, Firefox, Opera, etc.) for testing  

### Running the Server  
You can run the server manually or use the provided batch file.

1. To compile and run manually:  
   
    ```bash
    javac WebServer.java
    java WebServer
    ```  

2. Or simply run the batch file to compile and start the server:  

    ```bash
    build.bat
    ```  

3. Open a web browser and visit one of these URLs:  
   - `http://localhost:4321/Joburg`  
   - `http://localhost:4321/Durban`  
   - `http://localhost:4321/Cape`  
   - `http://localhost:4321/Africa.jpg`  

### Testing  
- The server can be tested on localhost using any browser.  
- Try requesting valid pages to receive the HTML or image files.  
- Request a non-existent path to verify the **404 Not Found** response.  
- Any internal server errors during processing return a **500 Internal Server Error** status.

## Project Structure  
The project files are organized as follows:  

- `WebServer.java` — Main server implementation  
- `Joburg.html` — Static HTML file for Joburg page  
- `Durban.html` — Static HTML file for Durban page  
- `CapeWithImage.html` — Static HTML with embedded images  
- `Africa.jpg` — Image file served as binary content  
- `build.bat` — Batch script to compile and start the server  

## Implementation Details  
- Uses `ServerSocket` to listen for connections on port 4321.  
- Creates a new thread for each client connection to handle simultaneous requests.  
- Parses HTTP requests and determines requested resources.  
- Reads and serves static files from the project directory, handling binary data for images correctly.  
- Sends proper HTTP response headers and bodies.  

## Future Improvements  
- Support for additional HTTP methods like POST, PUT, DELETE.  
- Dynamic content generation.  
- Enhanced error handling and logging.  
- Support for HTTP/1.1 persistent connections.  
