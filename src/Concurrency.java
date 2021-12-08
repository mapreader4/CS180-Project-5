import java.io.*;
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
                } else if(line.equals("get-all-courses")){
                    getAllCourses();
                } else if(line.equalsIgnoreCase("get-quizzes")){
                    int courseNumber=(Integer)objects.get(1);
                    getQuizzes(courseNumber);
                } else if(line.equals("get-current-quiz")){
                    int courseNumber=(Integer)objects.get(1);
                    int quizNumber=(Integer)objects.get(2);
                    getCurrentQuiz(courseNumber,quizNumber);
                } else if(line.equals("get-questions")){
                    int courseNumber=(Integer)objects.get(1);
                    int quizNumber=(Integer)objects.get(2);
                    getQuestions(courseNumber,quizNumber);
                } else if(line.equals("get-student-submissions")) {
                    getStudentSubmissions();
                }
                else if(line.equals("get-all-submissions")) {
                    int courseNumber=(Integer)objects.get(1);
                    int quizNumber=(Integer)objects.get(2);
                    getAllSubmissions(courseNumber,quizNumber);
                }
                else if(line.equalsIgnoreCase("create-course")) {
                    String courseName=(String)objects.get(1);
                    int courseNumber=(Integer)objects.get(2);
                    createCourse(courseName,courseNumber);
                } else if(line.equalsIgnoreCase("create-imported-quiz-file")){
                    int courseNumber=(Integer)objects.get(1);
                    File file=(File)objects.get(2);
                    addImportedQuiz(courseNumber,file);
                }else if(line.equalsIgnoreCase("create-true-false-question")){
                    int courseNumber = (int) objects.get(1);
                    int quizNumber = (int)objects.get(2);
                    String questionName = (String)objects.get(3);
                    int pointValue = (int) objects.get(4);
                    String trueFalse = (String)objects.get(5);
                    createTrueFalseQuestion(courseNumber,quizNumber,questionName,pointValue,trueFalse);
                } else if(line.equalsIgnoreCase("create-multiple-choice")){
                    int courseNumber = (int) objects.get(1);
                    int quizNumber = (int) objects.get(2);
                    String questionName = (String) objects.get(3);
                    int pointValue = (int) objects.get(4);
                    int numChoices = (int) objects.get(5);
                    ArrayList<String> answerChoices = (ArrayList<String>) objects.get(6);
                    int correctAnswerIndex = (int)objects.get(7);
                    createMultipleChoiceQuestion(courseNumber,quizNumber,questionName,pointValue,numChoices,answerChoices,correctAnswerIndex);
                }
                else if(line.equalsIgnoreCase("create-quiz")) {
                    int courseNumber=(Integer)objects.get(1);
                    String courseName=(String)objects.get(2);
                    String randomize=(String)objects.get(3);
                    createQuiz(courseNumber,courseName,randomize);
                }
                else if(line.equalsIgnoreCase("add-student-to-course")){
                    int courseNumber=(Integer)objects.get(1);
                    addStudentToCourse(courseNumber);
                } else if(line.equalsIgnoreCase("delete-quiz")) {
                    int courseNumber=(Integer)objects.get(1);
                    int quizNumber=(Integer)objects.get(2);
                    deleteQuiz(courseNumber,quizNumber);
                }
            }
        } catch (Exception e) {
            storeLists();
            throw new RuntimeException();
        }
    }
    //looks good
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
    //looks good
    public void getAccountCourses() {
        try {
            if (typeOfAccount.equalsIgnoreCase("teacher")) {
                outputStream.writeObject(((Teacher) user).getCourses());
                outputStream.flush();
            } else if (typeOfAccount.equalsIgnoreCase("student")) {
                outputStream.writeObject(((Student) user).getCourses());
                outputStream.flush();
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
    }
    // looks good
    public void getAllCourses() {
        try {
            outputStream.writeObject(courseList.getCourses());
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("getAllCourses not working");
        }
    }
    //looks good
    public void getQuizzes(int courseNumber) {
        try {
            Course course=courseList.getCourse(courseNumber);
            outputStream.writeObject(course.getQuizzes());
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException("getQuizzes not working");
        }
    }
    public void getCurrentQuiz(int courseNumber, int quizNumber){
        try {
            Course course=courseList.getCourse(courseNumber);
            Quiz quiz=course.getQuizzes().get(quizNumber);
            outputStream.writeObject(quiz);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("getCurrentQuiz not working");
        }
    }
    public void getQuestions(int courseNumber, int quizNumber){
        try {
            Course course = courseList.getCourse(courseNumber);
            Quiz quiz = course.getQuizzes().get(quizNumber);
            ArrayList<Question> questions = quiz.getQuiz();
            outputStream.writeObject(questions);
            outputStream.flush();
        } catch (Exception e){
            throw new RuntimeException("getQuestions not working");
        }
    }
    public void getStudentSubmissions() {
        try {
            ArrayList<Submission> submissions=((Student)user).getSubmissions();
            outputStream.writeObject(submissions);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("getStudentSubmission not working");
        }
    }
    public void getAllSubmissions(int courseNumber, int quizNumber) {
        try {
            Course course = courseList.getCourse(courseNumber);
            Quiz quiz = course.getQuizzes().get(quizNumber);
            ArrayList<Submission> submissions=quiz.getSubmission();
            outputStream.writeObject(submissions);
            outputStream.flush();
        } catch (Exception e){
            throw new RuntimeException("getAllSubmissions not working");
        }
    }
    //looks good
    public void createAccount(String username, String password, String typeOfUser) {
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

    //looks good
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
    public void addImportedQuiz(int courseNumber, File file){
        ArrayList<Object> result = new ArrayList<>();
        try {
            Course course = courseList.getCourse(courseNumber);
            Quiz quiz = new Quiz(course, file);
            course.addQuiz(quiz);
            int quizNumber = course.getQuizzes().indexOf(quiz);
            result.add("success");
            result.add(quizNumber);
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e){
            throw new RuntimeException("createImportedQuiz not working");
        }

    }
    public void createQuiz(int courseNumber,String quizName, String randomize){
        Course course=courseList.getCourse(courseNumber);
        course.addQuiz(new Quiz(courseNumber, quizName, randomize));
        ArrayList<Object> result = new ArrayList<>();
        result.add(course.getQuizzes().size() - 1);
        try {
            outputStream.writeObject(result);
            outputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("createQuiz not working");
        }
    }

    public void addStudentToCourse(int courseNumber){
        Course course=courseList.getCourse(courseNumber);
        ((Student)user).addToCourse(course);
        course.addStudent((Student) user);
    }
    public void deleteQuiz(int courseNumber,int quizNumber){
        Course course=courseList.getCourse(courseNumber);
        Quiz quiz=course.getQuizzes().get(quizNumber);
        course.removeQuiz(quiz);
    }
    //No need of this
//    public void setActiveCourse(int courseNumber){
//        try {
//            Course course = courseList.getCourse(courseNumber);
//            outputStream.writeObject(course);
//        } catch(IOException e) {
//            throw new RuntimeException("setActiveCourse not working");
//        }
//    }

//    public void updateLists(){
//        teacherList = TeacherList.readFromFile();
//        studentList = StudentList.readFromFile();
//        courseList = CourseList.readFromFile();
//    }
    public void storeLists(){
        teacherList.saveToFile();
        courseList.saveToFile();
        studentList.saveToFile();
    }
    public void createTrueFalseQuestion(int courseNumber,int quizNumber, String questionName, int pointValue,
                                        String trueFalse){
        Course c =courseList.getCourse(courseNumber);
        ArrayList<Quiz>quizzes = c.getQuizzes();
        Quiz q =quizzes.get(quizNumber);
        TrueFalse question = new TrueFalse(questionName,trueFalse,pointValue);
        q.addQuestion(question);
    }
    //createMultipleChoiceQuestion(courseNumber,quizNumber,questionName,pointValue,numChoices,answerChoices,correctAnswerIndex);
    public void createMultipleChoiceQuestion(int courseNumber, int quizNumber, String questionName,int pointValue,int numChoices,ArrayList<String>answerChoices,int correctAnswerIndex){
        Course c = courseList.getCourse(courseNumber);
        ArrayList<Quiz>quizzes = c.getQuizzes();
        Quiz q = quizzes.get(quizNumber);
        MultipleChoice question  = new MultipleChoice(questionName,numChoices,answerChoices,correctAnswerIndex,pointValue);
        q.addQuestion(question);
    }
}
