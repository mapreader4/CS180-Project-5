import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class Concurrency extends Thread {
    private final Socket socket;
    ObjectInputStream inputStream=null;
    ObjectOutputStream outputStream=null;
    private TeacherList teacherList;
    private StudentList studentList;
    private CourseList courseList;
    String typeOfAccount;
    User user;

    public Concurrency(Socket socket,TeacherList teacherList,StudentList studentList, CourseList courseList) {
        this.socket = socket;
        this.teacherList=teacherList;
        this.courseList=courseList;
        this.studentList=studentList;
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
                    String username = (String) objects.get(1);
                    String password = (String) objects.get(2);
                    String typeofAccount = (String) objects.get(3);
                    login(username,password,typeofAccount);
                } else if (line.equalsIgnoreCase("create-account")){
                    String username = (String)objects.get(1);
                    String password = (String)objects.get(2);
                    String typeofAccount = (String)objects.get(3);
                    createAccount(username,password,typeofAccount);
                } else if(line.equals("get-account-courses")){
                    getAccountCourses();
                } else if(line.equalsIgnoreCase("create-course")) {
                    String courseName=(String)objects.get(1);
                    int courseNumber=(Integer)objects.get(2);
                    createCourse(courseName,courseNumber);
                } else if(line.equalsIgnoreCase("get-quizzes")){
                    //getQuizzes();
                } else if(line.equalsIgnoreCase("add-student-to-course")){
                    int courseNumber=(Integer)objects.get(1);
                    addStudentToCourse(courseNumber);
                } else if(line.equalsIgnoreCase("set-active-course")){
                    int courseNumber=(Integer)objects.get(1);
                    setActiveCourse(courseNumber);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public void login(String username, String  password,String typeOfUser) {
        try {
            ArrayList<Object> result = new ArrayList<>();
            if (typeOfUser.equalsIgnoreCase("teacher")) {
                if (teacherList.findTeacher(username, password) == null) {
                    result.add("failure");
                } else {
                    user = teacherList.findTeacher(username, password);
                    result.add("success");
                    typeOfAccount = "Teacher";
                }
            } else if (typeOfUser.equalsIgnoreCase("student")) {
                if (studentList.findStudent(username, password) == null) {
                    result.add("failure");
                } else {
                    user = studentList.findStudent(username, password);
                    result.add("success");
                    typeOfAccount = "Student";
                }
            } else {
                result.add("failure");
            }

            if(result.get(0).equals("success")){
                result.add(user);
            }
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("login not created");
        }
    }
    public void createAccount(String username, String password, String typeOfUser) throws IOException {
        try {
            ArrayList<Object> result = new ArrayList<>();
            if (typeOfUser.equalsIgnoreCase("Student")) {
                Student student = studentList.findStudent(username, password);
                if (student == null) { //if student is not in the list, the account is created
                    user = new Student(username, password);
                    studentList.add((Student) user);
                    result.add("success");
                    typeOfAccount = "Student";
                } else { //if student already exists in list
                    result.add("failure");
                }
            } else if (typeOfUser.equalsIgnoreCase("Teacher")) {
                Teacher teacher = teacherList.findTeacher(username, password);
                if (teacher == null) {
                    user = new Teacher(username, password);
                    teacherList.add((Teacher) user);
                    result.add("success");
                    typeOfAccount = "Teacher";
                } else {
                    result.add("failure");
                }
            } else {
                result.add("failure");
            }
            if(result.get(0).equals("success")){
                result.add(user);
            }
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("createAccount not created");
        }
    }
    public void getAccountCourses() throws IOException{
        if(typeOfAccount.equalsIgnoreCase("teacher")){
            outputStream.writeObject(((Teacher)user).getCourses());
            outputStream.flush();
        } else if(typeOfAccount.equalsIgnoreCase("student")){
            outputStream.writeObject(((Student)user).getCourses());
            outputStream.flush();
        }
    }
    public void createCourse(String courseName, int courseNumber) {
        ArrayList<Object> result = new ArrayList<>();
        try {
            Course course = new Course(courseName, (Teacher) user, courseNumber);
            ((Teacher) user).addCourse(course);
            boolean temp= courseList.add(course);
            if(temp){
                result.add("success");
            } else {
                result.add("failure");
            }
            outputStream.writeObject(result);
            outputStream.flush();
        } catch(Exception e) {
            throw new RuntimeException("createCourse not working");
        }
    }
      //     OLD ONE
//    public void getQuizzes() {
//        try {
//            outputStream.writeObject(course.getQuizzes());
//        } catch (IOException e) {
//            throw new RuntimeException("getQuizzes not working");
//        }
//    }
    public void addStudentToCourse(int courseNumber){
        Course course=courseList.getCourse(courseNumber);
        ((Student)user).addToCourse(course);
        course.addStudent((Student) user);
    }
    public void setActiveCourse(int courseNumber){
        try {
            Course course = courseList.getCourse(courseNumber);
            outputStream.writeObject(course);
        } catch(IOException e) {
            throw new RuntimeException("setActiveCourse not working");
        }
    }

//    public void updateLists(){
//        teacherList = TeacherList.readFromFile();
//        studentList = StudentList.readFromFile();
//        courseList = CourseList.readFromFile();
//    }

}
