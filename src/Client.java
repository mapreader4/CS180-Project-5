import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket=null;
    PrintWriter pw=null;
    BufferedReader bfr=null;
    public static void main(String[] args) {
        Client client=new Client();
        View view=new View(client);

    }
    public boolean connectToServer(String domainName, String inputPortNumber) {
        try {
            int portNumber = Integer.parseInt(inputPortNumber);
            socket = new Socket(domainName, portNumber);
            if(socket.isConnected()){
                return true;
            }
            pw=new PrintWriter(socket.getOutputStream());
            bfr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    public boolean createAccount(String username, String password, String typeOfAccount){
        pw.println("create-account "+username+" "+ password + " "+typeOfAccount);
        try {
            String checker = bfr.readLine();
            if(checker.equals("success")){
                return true;
            }
        } catch (IOException e){
            throw new RuntimeException();
        }
        return false;
    }
    public boolean login(String username, String password, String typeOfAccount){
        pw.println("login "+username+" "+password+" "+ typeOfAccount);
        try {
            String checker =bfr.readLine();
            if(checker.equals("success")){
                return true;
            }
        } catch (IOException e){
            throw new RuntimeException();
        }
        return false;

    }

}
