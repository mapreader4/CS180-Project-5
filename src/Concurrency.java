import java.io.*;
import java.net.Socket;

public class Concurrency extends Thread {
    private final Socket socket;

    private TeacherList teacherList;
    private StudentList studentList;
    private CourseList courseList;

    public Concurrency(Socket socket) {
        this.socket = socket;
        updateLists();
    }

    @Override
    public void run() {
        //Reader is used to get data from the server, Writer is used to send data to the server if you need to.
        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            while (true) {
                String line =  reader.readLine();
                String [] message = line.split(" ");
                if(message.length == 0 ){

                } else if (message[0].equalsIgnoreCase("login")){
                    String username = message[1];
                    String password = message[2];
                    String typeofAccount = message[3];
                    login(username,password,typeofAccount);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean login(String username, String  password,String typeOfUser){

    }
    public void updateLists(){
        teacherList = TeacherList.readFromFile();
        studentList = StudentList.readFromFile();
        courseList = CourseList.readFromFile();
    }

}
