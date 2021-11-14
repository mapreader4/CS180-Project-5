import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Online Quiz Navigator - Menu
 *
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
     * a menu for creating a course
     *
     * @param scanner used for getting user input
     * @param teacher the account using the program
     * @return the course that was generated
     */
    private static Course createCourseMenu(Scanner scanner, Teacher teacher, CourseList courseList) throws IOException{
        Course course = null;
        boolean courseAdded = false;
        do {
            System.out.println("Please enter the information for the course you want to add.");
            String courseName = getStringFromScanner(scanner, "Course Name: ", false);
            int courseNumber = getIntegerFromScanner(scanner, "Course Number (between 0 and 999999): ",
                    0, 999999, false);
            course = new Course(courseName, teacher, courseNumber);
            courseAdded = courseList.add(course);
            if (!courseAdded) {
                System.out.println("Another course with that number already exists. Please try again.");
            }
        } while (!courseAdded);
        teacher.addCourse(course);
        return course;
    }

    /**
     * the menu for the teacher
     *
     * @param scanner used for getting user input
     * @param teacher the account using the program
     */
    private static void teacherMenu(Scanner scanner, Teacher teacher, CourseList courseList) throws IOException{
        while (true) {
            Course course = null;
            int createOrEditCourseChoice = getIntegerFromScanner(scanner, createOrEditCourseMessage, 1, 3, true);
            if (createOrEditCourseChoice == 3) {
                break;
            } else if (createOrEditCourseChoice == 1) {
                course = createCourseMenu(scanner, teacher, courseList);
            } else if (createOrEditCourseChoice == 2) {
                do {
                    ArrayList<Course> courses = teacher.getCourses();
                    if(courses==null){
                        System.out.println("There are no courses");
                        return;
                    }
                    System.out.println("Here is a list of your courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course currentCourse = courses.get(i);
                        System.out.println(currentCourse.getCourseNumber() + ": " + currentCourse.getCourseName());
                    }
                    System.out.println("Please enter the number of the course you want to access.");
                    int courseNumber = getIntegerFromScanner(scanner, "Course Number: ", 0, 999999, false);
                    course = teacher.getCourse(courseNumber);
                    if (course == null) {
                        System.out.println(notValidMessage);
                    } else {
                        Course updatedCourse = courseList.getCourse(courseNumber);
                        ArrayList<Course> teacherCourses = teacher.getCourses();
                        for (int i = 0; i < teacherCourses.size(); i++) {
                            if (updatedCourse.getCourseNumber() == teacherCourses.get(i).getCourseNumber()) {
                                teacherCourses.set(i, updatedCourse);
                                course = teacher.getCourse(courseNumber);
                            }
                        }
                    }
                } while (course == null);
            }
            teacherCourseMenu(scanner, teacher, course, courseList);
        }
    }

    /**
     * handles menu once the user has selected a particular course
     *
     * @param scanner used for getting user input
     * @param teacher the account using the program
     * @param course the course being edited
     */
    private static void teacherCourseMenu(Scanner scanner, Teacher teacher, Course course, CourseList courseList) {
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
                    quiz = new Quiz(scanner, course);
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
                int quizNumber = getIntegerFromScanner(scanner, "Quiz Number: ", 0, quizzes.size(), false);
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
                int quizNumber = getIntegerFromScanner(scanner, "Quiz Number: ", 0, quizzes.size(), false);
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
        courseList.update(course);
    }

    /**
     * the menu for the student
     *
     * @param scanner used for getting user input
     * @param student the account using the program
     */
    private static void studentMenu(Scanner scanner, Student student, CourseList courseList) {
        while (true) {
            Course course = null;
            int addOrExistingCourseChoice = getIntegerFromScanner(scanner, addOrExistingCourseMessage, 1, 3, true);
            if (addOrExistingCourseChoice == 3) {
                break;
            } else if (addOrExistingCourseChoice == 1) {
                course = addCourseMenu(scanner, student, courseList);
            } else if (addOrExistingCourseChoice == 2) {
                do {
                    ArrayList<Course> courses = student.getCourses();
                    System.out.println("Here is a list of your courses:");
                    for (int i = 0; i < courses.size(); i++) {
                        Course currentCourse = courses.get(i);
                        System.out.println(currentCourse.getCourseNumber() + ": " + currentCourse.getCourseName());
                    }
                    System.out.println("Please enter the number of the course you want to access.");
                    int courseNumber = getIntegerFromScanner(scanner, "Course Number: ", 0, 999999, false);
                    course = student.getCourse(courseNumber);
                    if (course == null) {
                        System.out.println(notValidMessage);
                    } else {
                        Course updatedCourse = courseList.getCourse(courseNumber);
                        ArrayList<Course> studentCourses = student.getCourses();
                        for (int i = 0; i < studentCourses.size(); i++) {
                            if (updatedCourse.getCourseNumber() == studentCourses.get(i).getCourseNumber()) {
                                studentCourses.set(i, updatedCourse);
                                course = student.getCourse(courseNumber);
                            }
                        }
                    }
                } while (course == null);
            }
            studentCourseMenu(scanner, student, course, courseList);
        }
    }

    /**
     * Adds a new course to the student's portfolio and returns that course
     *
     * @param scanner used for input
     * @param student the user adding the course
     * @return the course to add, null the course selected does not exist
     */
    private static Course addCourseMenu(Scanner scanner, Student student, CourseList courseList) {
        System.out.println("Here is a list of courses you could add:");
        ArrayList<Course> courses = courseList.getCourses();
        for (int i = 0; i < courses.size(); i++) {
            Course currentCourse = courses.get(i);
            System.out.println(currentCourse.getCourseNumber() + ": " + currentCourse.getCourseName());
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
    private static void studentCourseMenu(Scanner scanner, Student student, Course course, CourseList courseList) {
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
                int quizNumber = getIntegerFromScanner(scanner, "Quiz Number: ", 0, quizzes.size(), false);
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
                int submissionNumber = getIntegerFromScanner(scanner, "Submission Number: ", 0, submissions.size(), false);
                Submission submission = submissions.get(submissionNumber);
                submission.view(scanner, submissionNumber);
            }
        }
        courseList.update(course);
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
        CourseList courseList = CourseList.readFromFile();

        try {
            System.out.println(welcomeMessage);
            User user = login(scanner, teacherList, studentList);
            if (user == null) {
                //null user indicates user has quit on login menu
            } else if (user instanceof Teacher) {
                Teacher teacher = (Teacher) user;
                teacherMenu(scanner, teacher, courseList);
            } else if (user instanceof Student) {
                Student student = (Student) user;
                studentMenu(scanner, student, courseList);
            }
            System.out.println(exitMessage);
        } catch (Exception e) {
            throw e;
        } finally {
            teacherList.saveToFile();
            studentList.saveToFile();
            courseList.saveToFile();
        }
    }
}