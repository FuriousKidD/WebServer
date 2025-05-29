/**
 * @author TW MANHEMA 221029907
 * @version Practical 03
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
