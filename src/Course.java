import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class Course implements Serializable{

    private String courseName;
    private Teacher teacher;
    private int courseNumber;
    private ArrayList<Student> students;
    private ArrayList<Quiz> quizzes;

//    public Course(String courseName,String filename,Teacher teacher, int courseNumber, ArrayList<Student> students, ArrayList<Quiz> quizzes){
//        this(courseName,teacher,courseNumber);
//        this.filename=filename;
//        this.students=students;
//        this.quizzes=quizzes;
//
//    }

    public Course(String courseName, Teacher teacher, int courseNumber){
//        try (ObjectOutputStream pw=new ObjectOutputStream(new FileOutputStream(new File(Menu.coursesFile),true))){
//            pw.writeObject(this);
//        } catch(FileNotFoundException e){
//            System.out.println("No file found");
//            return;
//        } catch (IOException e){
//            e.printStackTrace();
//            return;
//        }
        this.courseName=courseName;
        this.teacher=teacher;
        this.courseNumber=courseNumber;
        this.students=new ArrayList<Student>();
        this.quizzes=new ArrayList<Quiz>();

    }

    public String getCourseName(){
        return this.courseName;
    }

    public void setCourseName(String courseName){
        this.courseName=courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void setQuizzes(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    public boolean addQuiz(Quiz quiz) {
        for (int i = 0; i < quizzes.size(); i++) {
            if (quiz.equals(quizzes.get(i))) {
                return false;
            }
        }
        quizzes.add(quiz);
        return true;
    }

     public boolean removeQuiz(Quiz quiz){
        for(int i=0;i<quizzes.size();i++){
            if(quiz.equals(quizzes.get(i))){
                quizzes.remove(quizzes.get(i));
                return true;
            }
        }
        return false;
     }

    public boolean addStudent(Student student){
        for (int i = 0; i < students.size(); i++) {
            if (student.equals(students.get(i))) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    public boolean removeStudent(Student student){
        if (students.contains(student)) {
            students.remove(student);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return (getCourseNumber() == course.getCourseNumber());
    }

}
