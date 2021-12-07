import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
/** So, we need to update the course anytime a quiz is added as the quiz is being taken from the active course, maybe
 just send a thread with the course object, and if the course number matches the active course, change the active course
 */
public class Client {
    Socket socket=null;
    int courseNumber;
    int quizNumber;
    User user;
    int questionNumber;
    int submissionNumber;
    ObjectOutputStream oos=null;
    ObjectInputStream ois=null;
    public static void main(String[] args) {
        Client client=new Client();
        TestView view=new TestView(client);

    }
    //tested
    public boolean connectToServer(String domainName, String inputPortNumber) {
        try {
            int portNumber = Integer.parseInt(inputPortNumber);
            socket = new Socket(domainName, portNumber);
            if(socket.isConnected()){
                oos=new ObjectOutputStream(socket.getOutputStream());
                ois=new ObjectInputStream(socket.getInputStream());
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    //looks good
    public boolean createAccount(String username, String password, String typeOfAccount){
        try {
            ArrayList<Object> objects=new ArrayList<>();
            objects.add("create-account");
            objects.add(username);
            objects.add(password);
            objects.add(typeOfAccount);

            oos.writeObject(objects);
            oos.flush();
            ArrayList<Object> result=(ArrayList<Object>)ois.readObject();
            String checker =(String)(result.get(0));
            if(checker.equals("success")){
                user=(User)(result.get(1));
                return true;
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
        return false;
    }
    //looks good
    public boolean login(String username, String password, String typeOfAccount){

        try {
            ArrayList<Object> objects=new ArrayList<>();

            objects.add("login");
            objects.add(username);
            objects.add(password);
            objects.add(typeOfAccount);
            oos.writeObject(objects);
            oos.flush();
            ArrayList<Object> result=(ArrayList<Object>)ois.readObject();
            String checker =(String)(result.get(0));
            if(checker.equals("success")){
                user=(User)(result.get(1));
                return true;
            }
        } catch (Exception e){
            throw new RuntimeException();
        }
        return false;

    }
    //looks good
    public ArrayList<Course> getAccountCourses(){
        try{
            ArrayList<Object> objects=new ArrayList<>();
            objects.add("get-account-courses");
            oos.writeObject(objects);
            oos.flush();
            ArrayList<Course> courseList=(ArrayList<Course>) ois.readObject();
            return courseList;
        } catch(Exception e){
            throw new RuntimeException();
        }
    }
    public ArrayList<Course> getAllCourses() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("get-all-courses");
            oos.writeObject(objects);
            oos.flush();
            ArrayList<Course> courseList = (ArrayList<Course>) ois.readObject();
            return courseList;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    //looks good
    public ArrayList<Quiz> getQuizzes() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("get-quizzes");
            objects.add(courseNumber);
            oos.writeObject(objects);
            oos.flush();
            ArrayList<Quiz> quizList = (ArrayList<Quiz>) ois.readObject();
            return quizList;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public Quiz getCurrentQuiz() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("get-current-quiz");
            oos.writeObject(objects);
            oos.flush();
            Quiz currentQuiz = (Quiz) ois.readObject();
            return currentQuiz;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public ArrayList<Question> getQuestions() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("get-questions");
            oos.writeObject(objects);
            oos.flush();
            ArrayList<Question> questionList = (ArrayList<Question>) ois.readObject();
            return questionList;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public ArrayList<Submission> getAllSubmissions() {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("get-all-submissions");
            oos.writeObject(objects);
            oos.flush();
            ArrayList<Submission> submissionList = (ArrayList<Submission>) ois.readObject();
            return submissionList;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    //looks good
    public boolean createCourse(String courseName, int courseNumber) {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("create-course");
            objects.add(courseName);
            objects.add(courseNumber);
            oos.writeObject(objects);
            oos.flush();
            String checker =(String)(((ArrayList<Object>) ois.readObject()).get(0));
            if(checker.equals("success")){
                this.courseNumber=courseNumber;
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return false;
    }
    public boolean createQuiz(Quiz quiz) {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("create-quiz");
            objects.add(quiz);
            oos.writeObject(objects);
            oos.flush();
            String checker =(String)ois.readObject();
            if(checker.equals("success")){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return false;
    }

    public void addStudentToCourse(int courseNumber){
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("add-student-to-course");
            objects.add(courseNumber);
            oos.writeObject(objects);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

//    public void deleteQuiz() {
//
//    }
//
    public boolean addImportedQuiz(File f) {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("import-quiz-file");
            objects.add(f);
            oos.writeObject(objects);
            oos.flush();
            String checker = (String) ois.readObject();
            if(checker.equals("success")){
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return false;
    }

    public void addQuestionToQuiz(Question question) {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("add-question-to-quiz");
            objects.add(question);
            oos.writeObject(objects);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

//    public boolean deleteQuestion() {
//
//    }
//
    public void updateQuestion(Question question) {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("update-question");
            objects.add(question);
            oos.writeObject(objects);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public void submitSubmission(Submission submission) {
        try {
            ArrayList<Object> objects = new ArrayList<>();
            objects.add("submit-submission");
            objects.add(submission);
            oos.writeObject(objects);
            oos.flush();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

//
//    public void close() {
//
//    }
//

    public void setActiveQuestion(int questionNumber){
        this.questionNumber=questionNumber;
    }
    public void setActiveSubmission(int submissionNumber){
        this.submissionNumber=submissionNumber;
    }
    public void setActiveCourse(int courseNumber) {
        this.courseNumber=courseNumber;
    }
    public void setActiveQuiz(int quizNumber){
        this.quizNumber=quizNumber;
    }
    public void clearActiveCourse() {
        this.courseNumber=-1;
    }
    public void clearActiveSubmission() {
        this.submissionNumber=-1;
    }
    public void clearActiveQuiz(){
        this.quizNumber=-1;
    }
    //            OLD ONE

    //    public ArrayList<Quiz> getQuizzes(){
//        ArrayList<Quiz> quizzes = course.getQuizzes();
//        return quizzes;
//    }

    //         OLD ONE
//    public void setActiveQuiz(int quizNumber) {
//        try {
//            ArrayList<Object> objects = new ArrayList<>();
//            objects.add("set-active-quiz");
//            objects.add(quizNumber);
//            oos.writeObject(objects);
//            oos.flush();
//            quiz=(Quiz)ois.readObject();
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }

    // OLD ONE
//    public void setActiveSubmission(int submissionNumber) {
//        try {
//            ArrayList<Object> objects = new ArrayList<>();
//            objects.add("set-active-submission");
//            objects.add(submissionNumber);
//            oos.writeObject(objects);
//            oos.flush();
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }

    //     OLD ONE
//    public void setActiveQuestion(int questionNumber) {
//        try {
//            ArrayList<Object> objects = new ArrayList<>();
//            objects.add("set-active-question");
//            objects.add(questionNumber);
//            oos.writeObject(objects);
//            oos.flush();
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }
}
