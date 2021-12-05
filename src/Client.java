import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Client client=new Client();
        View view=new View(client);

    }
    public boolean connectToServer(String domainName, String inputPortNumber) {
        try {
            int portNumber = Integer.parseInt(inputPortNumber);
            Socket socket = new Socket(domainName, portNumber);
            if(socket.isConnected()){
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}
