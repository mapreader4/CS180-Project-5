import java.util.ArrayList;

public class Teacher extends User {

    private ArrayList<Course> courses; //list of courses created by teacher

    public Teacher (String username, String password) {

        super(username, password, true);
    }

    public void addCourse(int courseNumber, ArrayList<Student> students, ArrayList<Quiz> quizzes) {

        boolean alreadyThere = false; //ensures no duplicate course is added
        for (Course c : courses) {
            if (c.getCourseNumber() == courseNumber) {
                alreadyThere = true;
            }
        }

        if (!alreadyThere) {
            courses.add(new Course(this, courseNumber, students, quizzes));
        }
    }

    public void removeCourse(int courseNumber) {
        for (Course c : courses) {
            if (c.getCourseNumber() == courseNumber) {
                courses.remove(c);
            }
        }
    }

    public void editCourseName(String oldCourse, String newCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseName().equals(oldCourse)) { //ensures course exists before editing
                courses.get(i).setCourseName(newCourse);
            }
        }
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }    
}
