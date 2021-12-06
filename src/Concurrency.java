import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Concurrency extends Thread {
    private final Socket socket;
    ObjectInputStream inputStream=null;
    ObjectOutputStream outputStream=null;
    TeacherList teacherList = TeacherList.readFromFile();
    StudentList studentList = StudentList.readFromFile();
    CourseList courseList = CourseList.readFromFile();
    String typeOfAccount;

    public Concurrency(Socket socket) {
        this.socket = socket;
        updateLists();
    }

    @Override
    public void run() {
        //Reader is used to get data from the server, Writer is used to send data to the server if you need to.
        try {

            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                ArrayList<Object> objects=(ArrayList<Object>)inputStream.readObject();
                String line = (String)objects.get(0);
                //String [] message = line.split(" ");
                if(objects.size() == 0 ){

                } else if (line.equalsIgnoreCase("login")){
                    String username = (String)objects.get(1);
                    String password = (String)objects.get(2);
                    String typeofAccount = (String)objects.get(3);
                    login(username,password,typeofAccount);
                } else if (line.equalsIgnoreCase("create-account")){
                    String username = (String)objects.get(1);
                    String password = (String)objects.get(2);
                    String typeofAccount = (String)objects.get(3);
                    createAccount(username,password,typeofAccount);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void login(String username, String  password,String typeOfUser){
        if(typeOfUser.equals("teacher")){
            if(teacherList.findTeacher(username, password)==null){
                writer.println("failure");
            } else {
                writer.println("success");
                typeOfAccount  = "Teacher";
            }
        } else if(typeOfUser.equals("student")){
            if(studentList.findStudent(username, password)==null){
                writer.println("failure");
            } else {
                writer.println("success");
                typeOfAccount = "Student";
            }
        } else {
            writer.println("failure");
        }
        writer.flush();
    }
    public void createAccount(String username, String password, String typeOfUser){
        if(typeOfUser.equalsIgnoreCase("Student")){
            Student student = studentList.findStudent(username,password);
            if(student == null){ //if student is not in the list, the account is created
                studentList.add(new Student (username,password));
                studentList.saveToFile();
                updateLists();
                writer.println("success");
                typeOfAccount  = "Student";
            }else{ //if student already exists in list
                writer.println("failure");
            }
        } else if (typeOfUser.equalsIgnoreCase("Teacher")){
            Teacher teacher=teacherList.findTeacher(username, password);
            if(teacher==null){
                teacherList.add(new Teacher(username, password));
                teacherList.saveToFile();
                updateLists();
                writer.println("success");
                typeOfAccount = "Teacher";
            } else {
                writer.println("failure");
            }
        }else{
            writer.println("failure");
        }
        writer.flush();
    }
    public void
    public void updateLists(){
        teacherList = TeacherList.readFromFile();
        studentList = StudentList.readFromFile();
        courseList = CourseList.readFromFile();
    }

}
