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
                            "Was not able to connect to the server with the given domain name and " +
                            "port number. Please try again.", "Unable to Connect", JOptionPane.ERROR_MESSAGE);
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
