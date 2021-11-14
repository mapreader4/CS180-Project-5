import java.io.*;
import java.util.ArrayList;

public class CourseList {
    public static final String FILENAME="courseList.ser";
    private ArrayList<Course> courses=new ArrayList<>();

    public ArrayList<Course> getCourses() {
        return courses;
    }
    public boolean exists(Course course){
        return courses.contains(courses);
    }
    public boolean add(Course course){
        if(courses.contains(course)) {
            return false; //already contains this
        }
        courses.add(course);
        return true;
    }

    public void saveToFile(){
        if(courses==null || courses.size()==0){
            return; // nothing to save
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME))) {
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static CourseList readFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            CourseList courseList=(CourseList)ois.readObject();
            return courseList;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            return new CourseList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

//    public static void main(String[] args) {
//        CourseList t=readFromFile();
//        System.out.println(t.courses + " does it read from file");
//        Course course=new Course("cs 180",);
//        System.out.println(t.add(course) + "should return true");
//        Course course2=new Course("ja", "mehta");
//        System.out.println(t.add(course2) +"should return false");
//        System.out.println(t.courses);
//        System.out.println(t.exists(course2) + "should return true");
//        t.saveToFile();
//    }

}
