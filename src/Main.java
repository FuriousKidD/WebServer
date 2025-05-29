/**
 * @author TW MANHEMA
 * @version 3
 */
import com.http.server.HttpServer;

/**
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpServer server = new HttpServer(4321);
		server.serverRun();
	}

}
