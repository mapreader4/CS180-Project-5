import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//TODO: perform final review on Menu.java to ensure all functionalities are present

/**
 * The class containing the main menu and supporting methods.
 *
 * @author Nathan Reed (mostly, add your name if you edit this part of the project)
 * @version November 14, 2021
 */
public class Menu {
    private static final String welcomeMessage = "Welcome to the Online Quiz Navigator!";
    private static final String loginOrCreate = "Would you like to log in or create an account?\n" +
            "1. Log in\n" +
            "2. Create account\n" +
            "3. Quit";
    private static final String teacherOrStudent = "Are you a teacher or a student?\n" +
            "1. Teacher\n" +
            "2. Student";
    private static final String accountAlreadyExistsMessage = "Another account with the given username already exists."
            + " Please try again.";
    private static final String accountInvalidMessage = "The account with the given username and password" +
            " could not be found. Please try again.";
    private static final String accountCreatedMessage = "Account created!";
    private static final String loggedInMessage = "Logged in!";
    private static final String createOrEditCourseMessage = "Would you like to create a new course or " +
            "edit an existing course?\n" +
            "1. Create course\n" +
            "2. Edit course\n" +
            "3. Quit";
    private static final String teacherCourseOptionsMessage = "Course Menu Options:\n" +
            "1. Add quiz\n" +
            "2. Edit quiz\n" +
            "3. Delete quiz\n" +
            "4. Change course name\n" +
            "5. Remove course\n" +
            "6. Return to main menu";
    private static final String quizInputMethodMessage = "Do you want to import the quiz as a file " +
            "or enter it through the terminal?\n" +
            "1. Import as file\n" +
            "2. Enter through terminal";
    private static final String addOrExistingCourseMessage = "Would you like to add a new course or use an existing " +
            "course?\n" +
            "1. Add a new course\n" +
            "2. Use an existing course\n" +
            "3. Quit";
    private static final String studentCourseOptionsMessage = "Course Menu Options:\n" +
            "1. Take quiz\n" +
            "2. View previously taken quizzes\n" +
            "3. Remove course\n" +
            "4. Return to main menu";
    private static final String exitMessage = "Thank you for using the Online Quiz Navigator!";
    private static final String notValidMessage = "That is not a valid option. Please try again.";
    private static final String cannotBeBlank = "This field cannot be blank. Please try again.";

    public static final String studentAccountsFile = "studentAccounts.ser";
    public static final String teacherAccountsFile = "teacherAccounts.ser";
    public static final String coursesFile = "courses.ser";

    /**
     * Displays a message containing multiple choices to the user, then ensures the user chooses an integer between the
     * minimum value and the maximum value by looping the message until the user inputs such a value
     *
     * @param scanner scanner to get input
     * @param message the message to display to the user as a prompt
     * @param minValue the minimum value of the options presented (inclusive)
     * @param maxValue the maximum value of the options presented (inclusive)
     * @param includeNewline true if new line should be included when displaying the message
     * @return an integer between (inclusive) the minValue and the maxValue
     */
    public static int getIntegerFromScanner(Scanner scanner, String message, int minValue, int maxValue,
                                            boolean includeNewline) {
        int integerToGet;
        do {
            if (includeNewline) {
                System.out.println(message);
            } else {
                System.out.print(message);
            }
            try {
                integerToGet = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                integerToGet = minValue - 1;
            }
            if (integerToGet < minValue || integerToGet > maxValue) {
                System.out.println(notValidMessage);
            }
        } while (integerToGet < minValue || integerToGet > maxValue);
        return integerToGet;
    }

    /**
     * Displays a message prompting the user to enter a String, then ensures the user enters a non-blank String by
     * looping the message until the user inputs such a String
     *
     * @param scanner scanner to get input
     * @param message the message to display to the user as a prompt
     * @param includeNewline true if new line should be included when displaying the message
     * @return a non-blank String inputted by the user
     */
    public static String getStringFromScanner(Scanner scanner, String message, boolean includeNewline) {
        String stringToGet;
        do {
            if (includeNewline) {
                System.out.println(message);
            } else {
                System.out.print(message);
            }
            stringToGet = scanner.nextLine();
            if (stringToGet == null || stringToGet.isBlank()) {
                System.out.println(cannotBeBlank);
            }
        } while (stringToGet == null || stringToGet.isBlank());
        return stringToGet;
    }

    /**
     * Prompts user to log in or create account, and retrieves and returns that account
     *
     * @param scanner used for getting user input
     * @return a User object representing the user who just logged in or created an account, null if user quits
     */
    private static User login(Scanner scanner, TeacherList teacherList, StudentList studentList) {
        int loginType = getIntegerFromScanner(scanner, loginOrCreate, 1, 3, true);
        if (loginType == 3) {
            return null;
        }

        int accountType = getIntegerFromScanner(scanner, teacherOrStudent, 1, 2, true);

        if (accountType == 1) {
            Teacher teacher = null;

            if (loginType == 2) {
                do {
                    String username = getStringFromScanner(scanner, "Username: ", false);
                    String password = getStringFromScanner(scanner, "Password: ", false);
                    teacher = new Teacher(username, password);
                    if (teacherList.add(teacher)) {
                        System.out.println(accountCreatedMessage);
                    } else {
                        teacher = null;
                        System.out.println(accountAlreadyExistsMessage);
                    }
                } while (teacher == null);
            } else if (loginType == 1) {
                do {
                    String username = getStringFromScanner(scanner, "Username: ", false);
                    String password = getStringFromScanner(scanner, "Password: ", false);
                    teacher = teacherList.findTeacher(username, password);
                    if (teacher == null) {
                        System.out.println(accountInvalidMessage);
                    } else {
                        System.out.println(loggedInMessage);
                    }
                } while (teacher == null);
            }

            return teacher;
        } else if (accountType == 2) {
            Student student = null;

            if (loginType == 2) {
                do {
                    String username = getStringFromScanner(scanner, "Username: ", false);
                    String password = getStringFromScanner(scanner, "Password: ", false);
                    student = new Student(username, password);
                    if (studentList.add(student)) {
                        System.out.println(accountCreatedMessage);
                    } else {
                        student = null;
                        System.out.println(accountAlreadyExistsMessage);
                    }
                } while (student == null);
            } else if (loginType == 1) {
                do {
                    String username = getStringFromScanner(scanner, "Username: ", false);
                    String password = getStringFromScanner(scanner, "Password: ", false);
                    student = studentList.findStudent(username, password);
                    if (student == null) {
                        System.out.println(accountInvalidMessage);
                    } else {
                        System.out.println(loggedInMessage);
                    }
                } while (student == null);
            }

            return student;
        } else {
            return null;
        }
    }

    /**
     * looks for the user with the given username
     *
     * @param username the user's username
     * @param filename the name of the file to be searched
     * @return the user found, if one was found; null if not found
     */
    private static User searchForUser(String username, String filename) {
        File file = new File(filename);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object currentObject;
            while ((currentObject = ois.readObject()) != null) {
                User currentUser = (User) currentObject;
                if (currentUser.getUsername().equals(username)) {
                    return currentUser;
                }
            }
            return null;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /**
     * appends the new user to the end of the accounts file
     *
     * @param user the user object to be added
     * @param filename the name of the file
     */
    public static void addUserToFile(User user, String filename) throws UserAlreadyExistsException {
        if (searchForUser(user.getUsername(), filename) != null) {
            throw new UserAlreadyExistsException("an account with this username already exists");
        }

        File file = new File(filename);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            oos.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * retrieves the user with the given information from the file
     *
     * @param username the user's username
     * @param password the user's password
     * @param filename the name of the file to retrieve from
     * @return the user object
     * @throws UserNotFoundException if no user with that information is found, this exception is thrown
     */
    public static User retrieveUserFromFile(String username, String password, String filename)
            throws UserNotFoundException {
        User user = searchForUser(username, filename);
        if (user == null) {
            throw new UserNotFoundException("could not find this user");
        } else if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException("password incorrect");
        } else {
            return user;
        }
    }

    private static boolean addCourseToFile(Course course, String filename) throws IOException{
        File file = new File(filename);
        if(!file.exists()){
            file.createNewFile();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object currentObject;
            while ((currentObject = ois.readObject()) != null) {
                Course currentCourse = (Course) currentObject;
                if (currentCourse.getCourseNumber() == course.getCourseNumber()) {
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file, true))) {
            oos.writeObject(course);
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * when a user is finished using the program, this method rewrites the file with their updated details
     *
     * @param user the user to update
     * @param filename the name of the file that the update is performed in
     */
    private static void closeUser(User user, String filename) {
        File file = new File(filename);
        ArrayList<User> users = new ArrayList<User>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object currentObject;
            while ((currentObject = ois.readObject()) != null) {
                User currentUser = (User) currentObject;
                if (currentUser.getUsername().equals(user.getUsername())) {
                    users.add(user);
                } else {
                    users.add(currentUser);
                }
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        } catch (ClassNotFoundException e) {
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < users.size(); i++) {
                oos.writeObject(users.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * when the user is finished accessing a particular course, this method rewrites the file with that course's
     * updated details
     *
     * @param course the course to update
     * @param filename the name of the file that the update is performed in
     */
    private static void closeCourse(Course course, String filename) {
        File file = new File(filename);
        ArrayList<Course> courses = new ArrayList<Course>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object currentObject;
            while ((currentObject = ois.readObject()) != null) {
                Course currentCourse = (Course) currentObject;
                if (currentCourse.getCourseNumber() == course.getCourseNumber()) {
                    courses.add(course);
                } else {
                    courses.add(currentCourse);
                }
            }
        } catch (FileNotFoundException e) {
            return;
        } catch (IOException e) {
            return;
        } catch (ClassNotFoundException e) {
            return;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            for (int i = 0; i < courses.size(); i++) {
                oos.writeObject(courses.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a menu for creating a course
     *
     * @param scanner used for getting user input
     * @param teacher the account using the program
     * @return the course that was generated
     */
    private static Course createCourseMenu(Scanner scanner, Teacher teacher) throws IOException{
        Course course = null;
        boolean courseAlreadyExists = false;
        do {
            System.out.println("Please enter the information for the course you want to add.");
            String courseName = getStringFromScanner(scanner, "Course Name: ", false);
            int courseNumber = getIntegerFromScanner(scanner, "Course Number (between 0 and 999999): ",
                    0, 999999, false);
            course = new Course(courseName, teacher, courseNumber);
            courseAlreadyExists = addCourseToFile(course, coursesFile);
        } while (courseAlreadyExists);
        teacher.addCourse(course);
        return course;
    }

    /**
     * the menu for the teacher
     *
     * @param scanner used for getting user input
     * @param teacher the account using the program
     */
    private static void teacherMenu(Scanner scanner, Teacher teacher) throws IOException{
        while (true) {
            Course currentCourse = null;
            int createOrEditCourseChoice = getIntegerFromScanner(scanner, createOrEditCourseMessage, 1, 3, true);
            if (createOrEditCourseChoice == 3) {
                break;
            } else if (createOrEditCourseChoice == 1) {
                currentCourse = createCourseMenu(scanner, teacher);
            } else if (createOrEditCourseChoice == 2) {
                do {
                    ArrayList<Course> courses = teacher.getCourses();
                    if(courses==null){
                        System.out.println("There are no courses");
                        return;
                    }
                    System.out.println("Here is a list of your courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println(course.getCourseNumber() + ": " + course.getCourseName());
                    }
                    System.out.println("Please enter the number of the course you want to access.");
                    int courseNumber = getIntegerFromScanner(scanner, "Course Number: ", 0, 999999, false);
                    currentCourse = teacher.getCourse(courseNumber);
                    if (currentCourse == null) {
                        System.out.println(notValidMessage);
                    }
                } while (currentCourse == null);
            }
            teacherCourseMenu(scanner, teacher, currentCourse);
        }
        User user = (User) teacher;
        closeUser(teacher, teacherAccountsFile);
    }

    /**
     * handles menu once the user has selected a particular course
     *
     * @param scanner used for getting user input
     * @param teacher the account using the program
     * @param course the course being edited
     */
    private static void teacherCourseMenu(Scanner scanner, Teacher teacher, Course course) {
        while (true) {
            System.out.println(course.getCourseName());
            int actionChoice = getIntegerFromScanner(scanner, teacherCourseOptionsMessage, 1, 6, true);
            if (actionChoice == 6) {
                break;
            } else if (actionChoice == 5) {
                int courseNumber = course.getCourseNumber();
                teacher.removeCourse(courseNumber);
                System.out.println("Course removed!");
                break;
            } else if (actionChoice == 1) {
                Quiz quiz = null;
                int quizInputMethod = getIntegerFromScanner(scanner, quizInputMethodMessage, 1, 2, true);

                if (quizInputMethod == 1) {
                    do {
                        String filename = FileImports.prompt();
                        quiz = new Quiz(filename, scanner, course);
                    } while (quiz == null);
                } else if (quizInputMethod == 2) {
                    quiz = new Quiz(scanner,course);
                }

                if (course.addQuiz(quiz)) {
                    System.out.println("Quiz created!");
                } else {
                    System.out.println("That quiz already exists.");
                }
            } else if (actionChoice == 2) {
                ArrayList<Quiz> quizzes = course.getQuizzes();
                System.out.println("Here is a list of your quizzes:");
                for (int i = 0; i < quizzes.size(); i++) {
                    Quiz quiz = quizzes.get(i);
                    System.out.println(i + ": " + quiz.getName());
                }
                System.out.println("Please enter of the quiz you want to edit.");
                int quizNumber = getIntegerFromScanner(scanner, "Quiz Number: ", 1, quizzes.size(), false);
                Quiz quiz = quizzes.get(quizNumber);
                quiz.editQuiz(scanner);
            } else if (actionChoice == 3) {
                ArrayList<Quiz> quizzes = course.getQuizzes();
                System.out.println("Here is a list of your quizzes:");
                for (int i = 0; i < quizzes.size(); i++) {
                    Quiz quiz = quizzes.get(i);
                    System.out.println(i + ": " + quiz.getName());
                }
                System.out.println("Please enter of the quiz you want to delete.");
                int quizNumber = getIntegerFromScanner(scanner, "Quiz Number: ", 1, quizzes.size(), false);
                Quiz quiz = quizzes.get(quizNumber);
                if (course.removeQuiz(quiz)) {
                    System.out.println("Quiz removed!");
                } else {
                    System.out.println("Couldn't find quiz to remove.");
                }
            } else if (actionChoice == 4) {
                System.out.println("Please enter the new course name.");
                String courseName = getStringFromScanner(scanner, "Course Name: ", false);
                course.setCourseName(courseName);
            }
        }
        closeCourse(course, coursesFile);
    }

    /**
     * the menu for the student
     *
     * @param scanner used for getting user input
     * @param student the account using the program
     */
    private static void studentMenu(Scanner scanner, Student student) {
        while (true) {
            Course currentCourse = null;
            int addOrExistingCourseChoice = getIntegerFromScanner(scanner, addOrExistingCourseMessage, 1, 3, true);
            if (addOrExistingCourseChoice == 3) {
                break;
            } else if (addOrExistingCourseChoice == 1) {
                currentCourse = addCourseMenu(scanner, student);
            } else if (addOrExistingCourseChoice == 2) {
                do {
                    ArrayList<Course> courses = student.getCourses();
                    System.out.println("Here is a list of your courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course course = courses.get(i);
                        System.out.println(course.getCourseNumber() + ": " + course.getCourseName());
                    }
                    System.out.println("Please enter the number of the course you want to access.");
                    int courseNumber = getIntegerFromScanner(scanner, "Course Number: ", 0, 999999, false);
                    currentCourse = student.getCourse(courseNumber);
                    if (currentCourse == null) {
                        System.out.println(notValidMessage);
                    }
                } while (currentCourse == null);
            }
            studentCourseMenu(scanner, student, currentCourse);
        }
        User user = (User) student;
        closeUser(user, studentAccountsFile);
    }

    /**
     * Adds a new course to the student's portfolio and returns that course
     *
     * @param scanner used for input
     * @param student the user adding the course
     * @return the course to add, null the course selected does not exist
     */
    private static Course addCourseMenu(Scanner scanner, Student student) {
        System.out.println("Here is a list of courses you could add:");
        ArrayList<Course> courses = new ArrayList<Course>();

        File file = new File(coursesFile);
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object currentObject;
            int i = 0;
            while ((currentObject = ois.readObject()) != null) {
                Course currentCourse = (Course) currentObject;
                courses.add(currentCourse);
                System.out.println(currentCourse.getCourseNumber() + ": " + currentCourse.getCourseName());
            }
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }

        System.out.println("Please enter the course number of the course you want to add.");
        int courseNumber = getIntegerFromScanner(scanner, "Course Number: ", 0, 999999, false);
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseNumber() == courseNumber) {
                Course currentCourse = courses.get(i);
                student.addToCourse(currentCourse);

                return currentCourse;
            }
        }
        return null;
    }

    /**
     * handles menu once the student has selected a particular course
     *
     * @param scanner used for input
     * @param student the account accessing the program
     * @param course the course being viewed
     */
    private static void studentCourseMenu(Scanner scanner, Student student, Course course) {
        while (true) {
            System.out.println(course.getCourseName());
            int actionChoice = getIntegerFromScanner(scanner, studentCourseOptionsMessage, 1, 4, true);
            if (actionChoice == 4) {
                break;
            } else if (actionChoice == 3) {
                int courseNumber = course.getCourseNumber();
                student.removeCourse(course);
                System.out.println("Course removed!");
                break;
            } else if (actionChoice == 1) {
                ArrayList<Quiz> quizzes = course.getQuizzes();
                System.out.println("Here is a list of your quizzes:");
                for (int i = 0; i < quizzes.size(); i++) {
                    Quiz quiz = quizzes.get(i);
                    System.out.println(i + ": " + quiz.getName());
                }
                System.out.println("Please enter of the quiz you want to take.");
                int quizNumber = getIntegerFromScanner(scanner, "Quiz Number: ", 1, quizzes.size(), false);
                Quiz quiz = quizzes.get(quizNumber);
                Submission submission = new Submission(student, quiz);
                submission.takeQuiz(scanner);
            } else if (actionChoice == 2) {
                ArrayList<Submission> submissions = student.getSubmissions();
                System.out.println("Here is a list of your quizzes:");
                for (int i = 0; i < submissions.size(); i++) {
                    Submission submission = submissions.get(i);
                    if (submission.getCourse().equals(course)) {
                        System.out.println(i + ": " + submission.getQuiz().getName() +
                                " - " + submission.getTimestamp());
                    }
                }
                System.out.println("Please enter the number of the submission you want to view.");
                int submissionNumber = getIntegerFromScanner(scanner, "Submission Number: ", 1, submissions.size(), false);
                Submission submission = submissions.get(submissionNumber);
                submission.view(scanner);
            }
        }
        closeCourse(course, coursesFile);
    }

    /**
     * Controls the highest level of the menu:
     * - creates scanner
     * - gets user's login
     * - directs user to relevant menu
     *
     * @param args
     */
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);
        TeacherList teacherList = TeacherList.readFromFile();
        StudentList studentList = StudentList.readFromFile();

        try {
            System.out.println(welcomeMessage);
            User user = login(scanner, teacherList, studentList);
            if (user == null) {
                //null user indicates user has quit on login menu
            } else if (user instanceof Teacher) {
                Teacher teacher = (Teacher) user;
                teacherMenu(scanner, teacher);
            } else if (user instanceof Student) {
                Student student = (Student) user;
                studentMenu(scanner, student);
            }
            System.out.println(exitMessage);

            teacherList.saveToFile();
            studentList.saveToFile();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            teacherList.saveToFile();
            studentList.saveToFile();
        }
    }
}