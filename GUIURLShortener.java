import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.JPanel;

public class GUIURLShortener {
    private String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private int shortUrlLength = 6;
    private HashMap<String, String> longUrls = new HashMap<>();
    private Random random = new Random();

    public static void main(String[] args) {
        GUIURLShortener urlShortener = new GUIURLShortener();
        urlShortener.run();
    }

    public void run() {
        JFrame frame = new JFrame("URL Shortener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Create components
        JLabel longURLLabel = new JLabel("Long URL:");
        JTextField longURLField = new JTextField(20);

        JButton shortenButton = new JButton("Shorten");
        shortenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shortenURL(longURLField);
            }
        });

        JLabel shortURLLabel = new JLabel("Short URL:");
        JTextField shortURLField = new JTextField(20);

        JButton expandButton = new JButton("Expand");
        expandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                expandURL(shortURLField);
            }
        });

        // Layout components
        JPanel panel = new JPanel();
        panel.add(longURLLabel);
        panel.add(longURLField);
        panel.add(shortenButton);
        panel.add(shortURLLabel);
        panel.add(shortURLField);
        panel.add(expandButton);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    private void shortenURL(JTextField longURLField) {
        String longURL = longURLField.getText();
        if (longUrls.containsValue(longURL)) {
            String shortURL = "";
            for (Map.Entry<String, String> entry : longUrls.entrySet()) {
                if (entry.getValue().equals(longURL)) {
                    shortURL = entry.getKey();
                    break;
                }
            }
            longURLField.setText("");
            JOptionPane.showMessageDialog(null, "This URL is already shortened to: " + shortURL);
        } else {
            String shortURL = generateShortURL();
            while (longUrls.containsKey(shortURL)) {
                shortURL = generateShortURL();
            }
            longUrls.put(shortURL, longURL);
            longURLField.setText("");
            JOptionPane.showMessageDialog(null, "Shortened URL: " + shortURL);
        }
    }

    private void expandURL(JTextField shortURLField) {
        String shortURL = shortURLField.getText();
        if (longUrls.containsKey(shortURL)) {
            String longURL = longUrls.get(shortURL);
            shortURLField.setText("");
            JOptionPane.showMessageDialog(null, "Long URL: " + longURL);
        } else {
            shortURLField.setText("");
            JOptionPane.showMessageDialog(null, "URL not found");
        }
    }

    private String generateShortURL() {
        StringBuilder shortURL = new StringBuilder();
        for (int i = 0; i < shortUrlLength; i++) {
            int randomIndex = random.nextInt(alphabet.length());
            shortURL.append(alphabet.charAt(randomIndex));
        }
        return shortURL.toString();
    }
}