import java.util.ArrayList;

public class Teacher extends User {

    private ArrayList<Course> courses;

    public Teacher (String username, String password) {

        super(username, password, true);
    }

    public void addCourse(String course) {
        boolean alreadyThere = false;
        for (Course c : courses) {
            if (c.getName.equals(course)) {
                alreadyThere = true;
            }
        }

        if (!alreadyThere) {
            courses.add(new Course(course));
        }
    }

    public void removeCourse(String course) {
        for (Course c : courses) {
            if (c.getName().equals(course)) {
                courses.remove(c);
            }
        }
    }

    public void editCourse(String oldCourse, String newCourse) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getName().equals(oldCourse)) {
                courses.get(i).setName(newCourse);
            }
        }
    }

}
