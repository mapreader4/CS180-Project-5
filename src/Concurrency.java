import java.io.*;
import java.lang.reflect.Array;
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
                } else if(line.equals("get-courses")){
                    getAccountCourses();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void login(String username, String  password,String typeOfUser) throws IOException {
        ArrayList<Object>result = new ArrayList<>();
        if(typeOfUser.equals("teacher")){
            if(teacherList.findTeacher(username, password)==null){
                result.add("failure");
            } else {
                result.add("success");
                typeOfAccount  = "Teacher";
            }
        } else if(typeOfUser.equals("student")){
            if(studentList.findStudent(username, password)==null){
                result.add("failure");
            } else {
                result.add("success");
                typeOfAccount = "Student";
            }
        } else {
            result.add("failure");
        }
        outputStream.writeObject(result);
        outputStream.flush();
    }
    public void createAccount(String username, String password, String typeOfUser) throws IOException {
        ArrayList<Object> result = new ArrayList<>();
        System.out.println("we are here= server create account");
        if(typeOfUser.equalsIgnoreCase("Student")){
            Student student = studentList.findStudent(username,password);
            if(student == null){ //if student is not in the list, the account is created
                studentList.add(new Student (username,password));
                studentList.saveToFile();
                updateLists();
                result.add("success");
                typeOfAccount  = "Student";
            }else{ //if student already exists in list
                result.add("failure");
            }
        } else if (typeOfUser.equalsIgnoreCase("Teacher")){
            Teacher teacher=teacherList.findTeacher(username, password);
            if(teacher==null){
                teacherList.add(new Teacher(username, password));
                teacherList.saveToFile();
                updateLists();
                result.add("success");
                typeOfAccount = "Teacher";
            } else {
                result.add("failure");
            }
        }else{
            result.add("failure");
        }
        System.out.println("we have completed server create account");
        System.out.println(result.get(0));
        outputStream.writeObject(result);
    }

    public void updateLists(){
        teacherList = TeacherList.readFromFile();
        studentList = StudentList.readFromFile();
        courseList = CourseList.readFromFile();
    }

}
