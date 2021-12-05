import java.io.*;
import java.net.Socket;

public class Concurrency extends Thread {
    private final Socket socket;
    BufferedReader reader=null;
    PrintWriter writer=null;
    TeacherList teacherList = TeacherList.readFromFile();
    StudentList studentList = StudentList.readFromFile();
    CourseList courseList = CourseList.readFromFile();

    public Concurrency(Socket socket) {
        this.socket = socket;
        updateLists();
    }

    @Override
    public void run() {
        //Reader is used to get data from the server, Writer is used to send data to the server if you need to.
        try {

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
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
        if(typeOfUser.equals("teacher")){
            if(teacherList.findTeacher(username, password)==null){
                writer.println("failure");
            } else {
                writer.println("success");
            }
        } else if(typeOfUser.equals("student")){
            if(studentList.findStudent(username, password)==null){
                writer.println("failure");
            } else {
                writer.println("success");
            }
        }
        return false;

    }
    public String createAccount(String username, String password, String typeOfUser){
        if(typeOfUser.equalsIgnoreCase("Student")){
            Student student = studentList.findStudent(username,password);
            if(student == null){ //if student is not in the list, the account is created
                studentList.add(new Student (username,password));
                studentList.saveToFile();
                updateLists();
                return "success";
            }else{ //if student already exists in list
                return "failure";
            }
        } else if (typeOfUser.equalsIgnoreCase("Teacher")){

        }else{

        }
    }
    public void updateLists(){
        teacherList = TeacherList.readFromFile();
        studentList = StudentList.readFromFile();
        courseList = CourseList.readFromFile();
    }

}
