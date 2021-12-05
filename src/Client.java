import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8818);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean connectToServer(String domainName, String inputPortNumber) {
        boolean connected = false;
        try {
            int portNumber = Integer.parseInt(inputPortNumber);
            if (domainName != null) {
                connected = true;
            }

        } catch (NumberFormatException e) {
            connected = false;
        }
        return connected;
    }
    public boolean createAccount(String username, String password){
        return true;
        //We still need to implement this
    }
}
