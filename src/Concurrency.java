import java.io.*;
import java.net.Socket;

public class Concurrency extends Thread {
    private final Socket socket;

    public Concurrency(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //Reader is used to get data from the server, Writer is used to send data to the server if you need to.
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
