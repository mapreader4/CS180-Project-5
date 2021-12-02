import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8818;
        try {
            ServerSocket socket = new ServerSocket(port);
            while (true) {
                Socket client = socket.accept();
                Thread thread = new Concurrency(client);
                thread.start();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}
