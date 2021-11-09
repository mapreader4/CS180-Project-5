import java.io.*;
import java.util.ArrayList;


public class Course implements Serializable{
    private Teacher teacher;
    private int courseNumber;
    private ArrayList<Student> students;
    private ArrayList<Quiz> quizzes;
    public Course(Teacher teacher, int courseNumber, ArrayList<Student> students, ArrayList<Quiz> quizzes){
        this.teacher=teacher;
        this.courseNumber=courseNumber;
        this.students=students;
        this.quizzes=quizzes;
    }
    public Course(Teacher teacher, int courseNumber){
        this.teacher=teacher;
        this.courseNumber=courseNumber;
        this.students=new ArrayList<Student>();
        this.quizzes=new ArrayList<Quiz>();
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

    public boolean addQuiz(Quiz quiz){
        ArrayList<Quiz> quizzesOfFile=readingCourseFile();
        if(quizzesOfFile==null){
            return false;
        }
        for(int i=0;i<quizzesOfFile.size();i++){
            if((quizzesOfFile.get(i).getName()).equals(quiz.getName())){
                return false;
            }
        }
        boolean temp=writingQuizToFile(quiz);
        if(!temp){
            return false;
        }
        return true;
    }
    public ArrayList<Quiz> readingCourseFile(){
        File f=new File("Course");
        ArrayList<Quiz> quizzesOfFile=new ArrayList<Quiz>();
        try(ObjectInputStream obj=new ObjectInputStream(new FileInputStream(f))){
            Quiz line=(Quiz)(obj.readObject());
            while(line!=null){
                quizzesOfFile.add(line);
            }
        } catch(Exception e){
            return null;
        }
        return quizzesOfFile;
    }

    public boolean writingQuizToFile(Quiz quiz){
        File f=new File("Course");
        try(ObjectOutputStream obj=new ObjectOutputStream(new FileOutputStream(f,true))){
            obj.writeObject(quiz);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
