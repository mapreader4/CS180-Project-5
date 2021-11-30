import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//TODO: Deconfuse my mental model of GUIs
//because I am very confused and am not going to make progress until I know what I'm doing

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
    Client client;

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
        JFrame frame = new JFrame("Online Quiz Navigator v2");
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());

        JButton button = new JButton("Test");
        content.add(button);

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
