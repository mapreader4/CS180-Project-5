import java.io.*;
import java.util.Scanner;

/**
 * The class containing the main menu and supporting methods.
 *
 * @author Nathan Reed (mostly, add your name if you edit this part of the project)
 * @version November 11, 2021
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
    private static String createOrEditCourseMessage = "Would you like to create a new course or " +
            "edit an existing course?\n" +
            "1. Create course\n" +
            "2. Edit course\n" +
            "3. Quit";
    private static final String exitMessage = "Thank you for using the Online Quiz Navigator!";
    private static final String notValidMessage = "That is not a valid option. Please try again.";
    private static final String cannotBeBlank = "This field cannot be blank. Please try again.";

    private static final String studentAccountsFile = "studentAccounts.ser";
    private static final String teacherAccountsFile = "teacherAccounts.ser";

    /**
     * Displays a message containing multiple choices to the user, then ensures the user chooses an integer between the
     * minimum value and the maximum value by looping the message until the user inputs such a value
     *
     * @param scanner scanner to get input
     * @param message the message to display to the user as a prompt
     * @param minValue the minimum value of the options presented (inclusive)
     * @param maxValue the maximum value of the options presented (inclusive)
     * @return an integer between (inclusive) the minValue and the maxValue
     */
    private static int getIntegerFromScanner(Scanner scanner, String message, int minValue, int maxValue) {
        int integerToGet;
        do {
            System.out.println(message);
            try {
                integerToGet = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                integerToGet = minValue - 1;
            }
            if (integerToGet < minValue || integerToGet > maxValue) {
                System.out.println(notValidMessage);
            }
        } while (integerToGet < minValue || integerToGet >= maxValue);
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
    private static String getStringFromScanner(Scanner scanner, String message, boolean includeNewline) {
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
    private static User login(Scanner scanner) {
        int loginType = getIntegerFromScanner(scanner, loginOrCreate, 1, 3)
        if (loginType == 3) {
            return null;
        }

        User user = null;

        int accountType = getIntegerFromScanner(scanner, teacherOrStudent, 1, 2);

        if (loginType == 2) {
            do {
                String username = getStringFromScanner(scanner, "Username: ", false);
                String password = getStringFromScanner(scanner, "Password: ", false);

                try {
                    if (accountType == 1) {
                        user = new Teacher(username, password);
                        addUserToFile(user, teacherAccountsFile);
                        break;
                    } else if (accountType == 2) {
                        user = new Student(username, password);
                        addUserToFile(user, studentAccountsFile);
                        break;
                    }
                } catch (UserAlreadyExistsException e) {
                    user = null;
                    System.out.println(accountAlreadyExistsMessage);
                }
            } while (user == null);

            System.out.println(accountCreatedMessage);
            return user;
        } else if (loginType == 1) {
            do {
                String username = getStringFromScanner(scanner, "Username: ", false);
                String password = getStringFromScanner(scanner, "Password: ", false);

                try {
                    if (accountType == 1) {
                        user = retrieveUserFromFile(username, password, teacherAccountsFile);
                    } else if (accountType == 2) {
                        user = retrieveUserFromFile(username, password, studentAccountsFile);
                    }
                } catch (UserNotFoundException e) {
                    user = null;
                    System.out.println(accountInvalidMessage);
                }
            } while (user == null);

            System.out.println(loggedInMessage);
            return user;
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

    private static void createCourseMenu(Scanner scanner, User user) {

    }

    /**
     * the menu for the teacher
     *
     * @param scanner used for getting user input
     * @param user the account using the program
     */
    private static void teacherMenu(Scanner scanner, User user) {
        while (true) {
            Course currentCourse;
            int createOrEditCourseChoice = getIntegerFromScanner(scanner, createOrEditCourseMessage, 1, 3);
            if (createOrEditCourseChoice == 3) {
                return;
            } else if (createOrEditCourseChoice == 1) {
                //TODO: implement creating course (may want to create separate method)
            } else if (createOrEditCourseChoice == 2) {
                //TODO: implement editing course (may want to create separate method)
            }
            //TODO: handle course afterwards (may want to create separate method)
        }
    }

    /**
     * the menu for the student
     *
     * @param scanner used for getting user input
     * @param user the account using the program
     */
    private static void studentMenu(Scanner scanner, User user) {
        //TODO: implement this!
        //This method is just here right now to make sure everything compiles

        //all of this should be in a loop, with a quit option to break the loop in every menu
        //first ask whether a course should be added(?) or to ask to add an existing course
    }

    /**
     * Controls the highest level of the menu:
     * - creates scanner
     * - gets user's login
     * - directs user to relevant menu
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(welcomeMessage);
        User user = login(scanner);
        if (user == null) {
            //null user indicates user has quit on login menu
        } else if (user instanceof Teacher) {
            teacherMenu(scanner, user);
        } else if (user instanceof Student) {
            studentMenu(scanner, user);
        }
        System.out.println(exitMessage);
    }
}