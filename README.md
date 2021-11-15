# CS180-Project-4
Group 087's Project 4 for CS18000
<br>
Option chosen - Option 2: Quizzes
<br>
<br>
Louis: Files (+help with randomizations in Quizzes)
<br>
Aditya: Accounts
<br>
Nathan R: Menu
<br>
Jay and Nathan S: Quizzes
<br>
<br>
information on the structure of the program and division of roles found in image file which Nathan R will upload
<br>
<br>
Classes
<br>
********
<br>
User (implements Serializable):
<br>
-------------------------------
<br>
String username - Stores username of Student/Teacher
<br>
String password - Stores password of Student/Teacher
<br>
boolean isTeacher - True if User is Teacher, False if User is Student
<br>
public User(String username, String password, boolean isTeacher) - Constructor initializing fields of User
<br>
public String getUsername() - Returns username
<br>
public String getPassword() - Returns password
<br>
public boolean isTeacher() - Returns isTeacher
<br>
public boolean equals(Object o) - Returns true or false comparing Object o to this instance of User
<br>
public static void testEquals() - Method used to test the equals(Object o) method
<br>
<br>
Student (extends User):
<br>
-----------------------
<br>
private ArrayList<Course> courses - Stores the courses the Student chooses to enroll in
<br>
private ArrayList<Submission> submissions - Stores the quiz submissions of the Student
<br>
public Student(String username, String password) - Sends username, password, false to super class
<br>
public ArrayList<Submission> getSubmissions() - Returns submissions
<br>
public void addToCourse(Course c) - Adds Course c to courses
<br>
public ArrayList<Course> getCourses() - Returns courses
<br> 
public Course getCourse(int i) - Returns Course object with courseNumber i; null otherwise
<br>
public void addSubmission(Submission s) - Adds Submission s to submissions
<br>
public String toString() - Returns String representation of Student
<br>
<br>
Teacher (extends User):
<br>
-----------------------
<br>
private ArrayList<Course> courses - Stores courses created by the Teacher
<br>
public Teacher(String username, String password) - Sends username, password, true to super class
<br>
public void addCourse(Course c) - Adds Course c to courses
<br>
public ArrayList<Course> getCourses() - Returns courses
<br>
public Course getCourse(int i) - Returns Course object with courseNumber i; null otherwise
<br>
public String toString() - Returns String representation of Teacher
<br>
<br>
Menu:
<br>
-----------------------
<br>
public static void main() - controls the highest level of menu flow
<br>
public static int getIntegerFromScanner(Scanner scanner, String message, int minValue, int maxValue, 
boolean includeNewline) - returns an int between the minValue and maxValue after prompting the user, reprompts if 
the value is invalid or outside the given range
<br>
public static String getStringFromScanner(Scanner scanner, String message, boolean includeNewline) - 
returns a String that is not blank after prompting the user, reprompts if the String is blank
<br>
private static User login(Scanner scanner, TeacherList teacherList, StudentList studentList) - 
prompts user to log in or create account, and retrieves and returns that account
<br>
private static void teacherMenu(Scanner scanner, Teacher teacher, CourseList courseList) - 
controls the upper menu for the teacher, allowing selection of a course, then directs the teacher to
teacherCourseMenu()
<br>
private static Course createCourseMenu(Scanner scanner, Teacher teacher, CourseList courseList) - 
returns a course after creating it and adding it to all relevant directories
<br>
private static void teacherCourseMenu(Scanner scanner, Teacher teacher, Course course, CourseList courseList) -
controls the lower menu for the teacher after course selection, allowing the teacher to add, edit, delete, or view
quizzes
<br>
private static void studentMenu(Scanner scanner, Student student, CourseList courseList) -
controls the upper menu for the student, allowing selection of a course, then directs the student to
studentCourseMenu()
<br>
private static Course addCourseMenu(Scanner scanner, Student student, CourseList courseList) -
returns a course after adding it to a student's list of courses
<br>
private static void studentCourseMenu(Scanner scanner, Student student, Course course, CourseList courseList) - 
controls the lower menu for the student after course selection, allowing the student to take quizzes or view previous
submissions
<br>
<br>
File Imports:
<br>
-----------------------
<br>
public static String prompt() - 
return the user's input path for quiz files
<br>
public static ArrayList<String> readFile(String filePath) - 
return an ArrayList of the contents of the quiz file for further processing.
