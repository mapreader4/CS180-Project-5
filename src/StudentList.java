import java.io.*;
import java.util.ArrayList;

public class StudentList implements Serializable{
    public static final String FILENAME="studentList.ser";
    private ArrayList<Student> students=new ArrayList<>();

    public boolean exists(Student student){
        return students.contains(student);
    }
    public boolean add(Student student){
        if(students.contains(student)) {
            return false; //already contains this
        }
        students.add(student);
        return true;
    }
    public boolean removes(Student student){
        if(students.contains(student)){
            students.remove(student);
            return true;
        }
        return false; // does not contain it
    }
    public void saveToFile(){
        if(students==null || students.size()==0){
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
    public static StudentList readFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            StudentList studentList=(StudentList)ois.readObject();
            return studentList;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            return new StudentList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
//    public static void main(String[] args) {
//        StudentList t=readFromFile();
//        System.out.println(t.students + " does it read from file");
//        Student student=new Student("j","m");
//        System.out.println(t.add(student) + "should return false");
//        Student student2=new Student("jay","mehta");
//        System.out.println(t.add(student2)+"should return true");
//        Teacher Student2=new Teacher("jay", "mehta");
//        System.out.println(t.add(teacher2) +"should return true");
//        System.out.println(t.teachers);
//        System.out.println(t.exists(student2) + "should return true");
//        t.saveToFile();
//
//    }
}
