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
-----
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
--------
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
--------
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


