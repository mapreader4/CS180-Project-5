import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class FileImports {
    public static String prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the file path to the Quiz!");
        String userAnswer = scanner.nextLine();
        return userAnswer;
    }
    public static ArrayList<String> readFile(String filePath) {
        ArrayList<String> userInput = new ArrayList<>();
        try {
            File quiz = new File(filePath);
            String quizQuestions = "";
            BufferedReader bfr = new BufferedReader(new FileReader(filePath));
            do {
               quizQuestions = bfr.readLine();
               userInput.add(quizQuestions);
            } while (quizQuestions != null);
        } catch (IOException e) {
            System.out.println("Please input the right file path!");
        }
        return userInput;
    }
}
