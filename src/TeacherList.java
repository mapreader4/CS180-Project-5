import java.io.*;
import java.util.ArrayList;

public class TeacherList implements Serializable{
    public static final String FILENAME="teacherList.ser";
    private ArrayList<Teacher> teachers=new ArrayList<>();

    public boolean exists(Teacher teacher){
        return teachers.contains(teacher);
    }
    public boolean add(Teacher teacher){
        if(teachers.contains(teacher)) {
            return false; //already contains this
        }
        teachers.add(teacher);
        return true;
    }
    public void saveToFile(){
        if(teachers==null || teachers.size()==0){
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
    public static TeacherList readFromFile(){
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME))) {
            TeacherList teacherList=(TeacherList)ois.readObject();
            return teacherList;
        } catch(ClassNotFoundException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            return new TeacherList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

//    public static void main(String[] args) {
//        TeacherList t=readFromFile();
//        System.out.println(t.teachers + " does it read from file");
//        Teacher teacher=new Teacher("j","m");
//        System.out.println(t.add(teacher) + "should return false");
//        Teacher teacher2=new Teacher("jay", "mehta");
//        System.out.println(t.add(teacher2) +"should return true");
//        System.out.println(t.teachers);
//        System.out.println(t.exists(teacher) + "should return true");
//        t.saveToFile();
//
//    }
}
