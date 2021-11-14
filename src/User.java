import java.io.Serializable;
import java.util.Objects;

/**
 * User Class
 *
 *@author Aditya Menon (add your name if you implemented any functionality)
 *@version Nov 13, 2021
 */

public class User implements Serializable {
    String username;
    String password;
    boolean isTeacher;

    public User(String username, String password, boolean isTeacher) {
        this.username = username;
        this.password = password;
        this.isTeacher = isTeacher;
    }

    public User() {
        username = null;
        password = null;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return (user.getUsername().equals(username) && user.isTeacher()==isTeacher);
    }
    public static void testEquals(){
        User a=new User("jay","mehta",true);
        User b=new User("j","m",true);
        User c=new User("jay","mehta",true);
        User d=new User("jay","mehta",false);

        System.out.println(a.equals(b) + " jay and j both teacher");
        System.out.println(a.equals(c) + "jay and jay both teacher");
        System.out.println(a.equals(d) + " jay and jay one student and one teacher");
    }

    public static void main(String[] args) {
        testEquals();
    }

}
