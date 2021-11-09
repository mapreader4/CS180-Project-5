import java.util.Scanner;

public class Menu {
    private static final String welcomeMessage = "Welcome to the Online Quiz Navigator!";
    private static final String loginOrCreate = "Would you like to log in or create an account?\n" +
            "1. Log in\n" +
            "2. Create account\n" +
            "3. Quit";
    private static final String teacherOrStudent = "Are you a teacher or a student?\n" +
            "1. Teacher\n" +
            "2. Student";
    private static final String accountInvalidMessage = "The account with the given username and password" +
            "could not be found."
    private static final String accountCreatedMessage = "Account created!";
    private static final String loggedInMessage = "Logged in!";
    private static final String exitMessage = "Thank you for using the Online Quiz Navigator!";
    private static final String notValidMessage = "That is not a valid option. Please try again.";
    private static final String cannotBeBlank = "This field cannot be blank. Please try again.";

    private static final String studentAccountsFile = "studentAccounts.ser";
    private static final String teacherAccountsFile = "teacherAccounts.ser";

    /**
     * Prompts user to log in or create account, and retrieves and returns that account
     *
     * @return a User object representing the user who just logged in or created an account, null if user quits
     */
    public static User login(Scanner scanner) {
        int loginType;
        do {
            System.out.println(loginOrCreate);
            try {
                loginType = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                loginType = -1;
                System.out.println(notValidMessage);
            }
            if (loginType <= 0 || loginType >= 4) {
                System.out.println(notValidMessage);
            }
        } while (loginType <= 0 || loginType >= 4);

        if (loginType == 3) {
            return null;
        }

        int accountType;
        do {
            System.out.println(teacherOrStudent);
            try {
                accountType = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                accountType = -1;
                System.out.println(notValidMessage);
            }
            if (accountType <= 0 || accountType >= 4) {
                System.out.println(notValidMessage);
            }
        } while (accountType <= 0 || accountType >= 4);

        User user;
        if (loginType == 2) {
            String username;
            do {
                System.out.print("Username: ");
                username = scanner.nextLine();
                if (username == null || username.isBlank()) {
                    System.out.println(cannotBeBlank);
                }
            } while (username == null || username.isBlank());

            String password;
            do {
                System.out.println("Password: ");
                password = scanner.nextLine();
                if (password == null || password.isBlank()) {
                    System.out.println(cannotBeBlank);
                }
            } while (password == null || password.isBlank());

            if (accountType == 1) {
                user = new Teacher(username, password);
                addUserToFile(user, teacherAccountsFile);
            } else if (accountType == 2) {
                user = new Student(username, password);
                addUserToFile(user, studentAccountsFile);
            }

            System.out.println(accountCreatedMessage);
            return user;
        } else if (loginType == 1) {
            do {
                String username;
                do {
                    System.out.print("Username: ");
                    username = scanner.nextLine();
                    if (username == null || username.isBlank()) {
                        System.out.println(cannotBeBlank);
                    }
                } while (username == null || username.isBlank());

                String password;
                do {
                    System.out.println("Password: ");
                    password = scanner.nextLine();
                    if (password == null || password.isBlank()) {
                        System.out.println(cannotBeBlank);
                    }
                } while (password == null || password.isBlank());

                try {
                    if (accountType == 1) {
                        user = retriveUserFromFile(username, password, teacherAccountsFile);
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
        }
    }

    public static void addUserToFile(User user, String filename) {
        //TODO: implement this!
        //This method is just here right now to make sure everything compiles
    }

    public static User retrieveUserFromFile(String username, String password, String filename) {
        //TODO: implement this!
        //This method is just here right now to make sure everything compiles
        return new Student("", "");
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
        User user = login(scanner, fileManager);
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