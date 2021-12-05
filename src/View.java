import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Online Quiz Navigator v2 - View
 *
 * Handles the GUI for the Online Quiz Navigator.
 * Listens for user updates, then sends them to Client for handling.
 * Listens for updates from Client, then refreshes the board as need be.
 *
 * @author Nathan Reed, lab sec L24
 * @version November 29, 2021
 */
public class View extends JComponent {
    private Client client;
    private JFrame frame;
    private JPanel mainPanel;
    private ArrayList<JComponent> activeComponents = new ArrayList<JComponent>();

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if (actionCommand.equals("send connection info")) {
                JTextField domainNameTxt = (JTextField) activeComponents.get(0);
                JTextField portNumberTxt = (JTextField) activeComponents.get(1);
                if (client.connectToServer(domainNameTxt.getText(), portNumberTxt.getText())) {
                    createLoginScreen();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Unable to connect to the server with the given domain name and " +
                            "port number. Please try again.", "Unable to Connect", JOptionPane.ERROR_MESSAGE);
                }
            } else if (actionCommand.equals("send login info")) {
                JTextField usernameTxt = (JTextField) activeComponents.get(0);
                JTextField passwordTxt = (JTextField) activeComponents.get(1);
                JRadioButton studentButton = (JRadioButton) activeComponents.get(2);
                JRadioButton teacherButton = (JRadioButton) activeComponents.get(3);
                String typeOfAccount;
                if (studentButton.isSelected()) {
                    typeOfAccount = "student";
                } else if (teacherButton.isSelected()) {
                    typeOfAccount = "teacher";
                } else {
                    typeOfAccount = null;
                }
                if (typeOfAccount != null
                        && client.login(usernameTxt.getText(), passwordTxt.getText(), typeOfAccount)) {
                    createMainMenu();
                } else if (typeOfAccount == null) {
                    JOptionPane.showMessageDialog(null, "Please select either a teacher " +
                            "or a student account.", "Type of account not selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Unable to log in to the account with the given username or password. " +
                            "Please try again.", "Unable to login", JOptionPane.ERROR_MESSAGE);
                }
            } else if (actionCommand.equals("send account creation info")) {
                JTextField usernameTxt = (JTextField) activeComponents.get(0);
                JTextField passwordTxt = (JTextField) activeComponents.get(1);
                JRadioButton studentButton = (JRadioButton) activeComponents.get(2);
                JRadioButton teacherButton = (JRadioButton) activeComponents.get(3);
                String typeOfAccount;
                if (studentButton.isSelected()) {
                    typeOfAccount = "student";
                } else if (teacherButton.isSelected()) {
                    typeOfAccount = "teacher";
                } else {
                    typeOfAccount = null;
                }
                if (typeOfAccount != null
                        && client.createAccount(usernameTxt.getText(), passwordTxt.getText(), typeOfAccount)) {
                    createMainMenu();
                } else if (typeOfAccount == null) {
                    JOptionPane.showMessageDialog(null, "Please select either a teacher " +
                            "or a student account.", "Type of account not selected", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "The account with the given username already exists. " +
                                    "Please try again.", "Unable to create account", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    //TODO: remove this method before submission
    /**
     * Constructor for testing without access to client
     */
    private View() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    /**
     * Sets up View object and Event Dispatch Thread when called by Client
     * Structure borrowed from Week 11 Wednesday lecture slide 75
     *
     * @param client the Client object used for this session
     */
    View(Client client) {
        this.client = client;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }

    //TODO: add actual content to this
    /**
     * Creates and displays initial GUI
     */
    private void createGUI() {
        frame = new JFrame("Online Quiz Navigator v2");
        mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        JLabel domainNameLabel = new JLabel("Domain Name:");
        JTextField domainNameTxt = new JTextField(30);
        activeComponents.add(domainNameTxt);
        JLabel portNumberLabel = new JLabel("Port Number:");
        JTextField portNumberTxt = new JTextField(30);
        activeComponents.add(portNumberTxt);

        JPanel domainNamePanel = new JPanel(new FlowLayout());
        JPanel portNumberPanel = new JPanel(new FlowLayout());
        domainNamePanel.add(domainNameLabel);
        domainNamePanel.add(domainNameTxt);
        portNumberPanel.add(portNumberLabel);
        portNumberPanel.add(portNumberTxt);

        JButton submitButton = new JButton("Submit");
        submitButton.setActionCommand("send connection info");
        submitButton.addActionListener(actionListener);

        mainPanel.add(domainNamePanel, BorderLayout.NORTH);
        mainPanel.add(portNumberPanel, BorderLayout.CENTER);
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void createLoginScreen() {
        mainPanel.removeAll();
        activeComponents.clear();

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameTxt = new JTextField(30);
        activeComponents.add(usernameTxt);
        JLabel passwordLabel = new JLabel("Password:");
        JTextField passwordTxt = new JTextField(30);
        activeComponents.add(passwordTxt);

        JButton createAccountButton = new JButton("Create Account");
        createAccountButton.setActionCommand("send account creation info");
        createAccountButton.addActionListener(actionListener);
        JButton loginButton = new JButton("Login");
        loginButton.setActionCommand("send login info");
        loginButton.addActionListener(actionListener);

        JRadioButton studentButton = new JRadioButton("Student", true);
        JRadioButton teacherButton = new JRadioButton("Teacher");
        ButtonGroup teacherOrStudentGroup = new ButtonGroup();
        teacherOrStudentGroup.add(studentButton);
        teacherOrStudentGroup.add(teacherButton);
        activeComponents.add(studentButton);
        activeComponents.add(teacherButton);

        JPanel usernamePanel = new JPanel(new FlowLayout());
        JPanel passwordPanel = new JPanel(new FlowLayout());
        JPanel teacherOrStudentPanel = new JPanel(new FlowLayout());
        JPanel loginPanel = new JPanel(new BorderLayout());
        JPanel submitPanel = new JPanel(new FlowLayout());
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTxt);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTxt);
        teacherOrStudentPanel.add(studentButton);
        teacherOrStudentPanel.add(teacherButton);
        loginPanel.add(usernamePanel, BorderLayout.NORTH);
        loginPanel.add(passwordPanel, BorderLayout.CENTER);
        loginPanel.add(teacherOrStudentPanel, BorderLayout.SOUTH);
        submitPanel.add(createAccountButton);
        submitPanel.add(loginButton);

        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.add(submitPanel, BorderLayout.SOUTH);

        mainPanel.validate();
        mainPanel.repaint();
    }

    //TODO: remove before submission
    /**
     * Main method for testing
     *
     * @param args
     */
    public static void main(String[] args) {
        View view = new View();
    }
}
