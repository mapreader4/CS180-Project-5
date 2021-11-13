import java.io.Serializable;

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

}
