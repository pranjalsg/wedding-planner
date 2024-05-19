import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class WeddingPlanner extends JFrame {
    private ArrayList<ImageIcon> images;
    private JLabel imageLabel;
    private int currentIndex;
    private JPanel servicesPanel;

    public WeddingPlanner() {
        setTitle("Wedding Planner");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Banner Panel
        JPanel bannerPanel = new JPanel();
        bannerPanel.setBackground(Color.decode("#FFC8BC")); // Orange color
        JLabel bannerLabel = new JLabel("Wedding Planner");
        bannerLabel.setForeground(Color.WHITE);
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bannerPanel.add(bannerLabel);

        // Menu Panel
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        menuPanel.setBackground(Color.WHITE);

        // Menu items
        String[] menuItems = {"Home", "Existing User Login", "New User Registration"};
        for (String item : menuItems) {
            JButton menuItemButton = new JButton(item);
            menuItemButton.setFocusPainted(false);
            menuItemButton.setBackground(Color.WHITE);
            menuItemButton.setForeground(Color.decode("#FFC8BC")); // Orange color
            menuItemButton.setFont(new Font("Arial", Font.BOLD, 16));
            menuItemButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

            // Add action listener for menu items
            menuItemButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (item.equals("New User Registration")) {
						WeddingPlanner.this.setVisible(false);
						WeddingPlanner.this.dispose();
                        new SignInPage().setVisible(true);
                    } else if (item.equals("Existing User Login")) {
						WeddingPlanner.this.setVisible(false);
						WeddingPlanner.this.dispose();
                        new LoginFromMainPage().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(WeddingPlanner.this, "You're currently in the " + item + " page");
                    }
                }
            });

            menuPanel.add(menuItemButton);
        }

        // Image Panel
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        images = new ArrayList<>();
        images.add(new ImageIcon("wedding1.jpg"));
        images.add(new ImageIcon("wedding2.jpg"));
        images.add(new ImageIcon("wedding3.jpg"));
        images.add(new ImageIcon("wedding4.jpg"));
        images.add(new ImageIcon("wedding5.jpg"));
        imageLabel = new JLabel(images.get(0));
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // Slideshow Timer
        javax.swing.Timer timer = new javax.swing.Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex = (currentIndex + 1) % images.size();
                imageLabel.setIcon(images.get(currentIndex));
            }
        });
        timer.start();

        // Adding components to the content pane
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(bannerPanel, BorderLayout.NORTH);
        contentPane.add(menuPanel, BorderLayout.CENTER);
        contentPane.add(imagePanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeddingPlanner::new);
    }
}

class MainPage extends JFrame {

    public MainPage() {
        setTitle("Main Page");
        setSize(1920, 1080);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background Image Panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImg = new ImageIcon("background.jpg");
                g.drawImage(backgroundImg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Use null layout to position elements freely
		
		// Back to Home button
        JButton backToHomeButton = new JButton("Back to Home");
        backToHomeButton.setForeground(Color.decode("#82645e")); // Custom color
        backToHomeButton.setFont(new Font("Arial", Font.BOLD, 20));
        backToHomeButton.setBounds(350, 300, 200, 30);
        backToHomeButton.setOpaque(false);
        backToHomeButton.setContentAreaFilled(false);
        backToHomeButton.setBorderPainted(false);
        backToHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainPage.this.dispose(); // Close the Services page
                new WeddingPlanner().setVisible(true);
            }
        });
        backgroundPanel.add(backToHomeButton);

        setContentPane(backgroundPanel);
        setVisible(true);
    }

        
}

class SignInPage extends JFrame {

    public SignInPage() {
        setTitle("Sign In Page");
        setSize(1600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Background Image
        JLabel background = new JLabel(new ImageIcon("background1.jpg"));
        background.setBounds(0, 0, getWidth(), getHeight());
        add(background);

        // Sign In button
        JButton signInButton = new JButton("Sign Up");
        signInButton.setBounds(350, 250, 100, 30);
        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // Hide the sign-in page
                new LoginPage().setVisible(true); // Show the login page
            }
        });
        background.add(signInButton);

        // Back to Home button
        JButton backToHomeButton = new JButton("Back to Home");
        backToHomeButton.setBounds(500, 250, 150, 30);
        backToHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SignInPage.this.dispose(); // Close the Sign In page
                new WeddingPlanner().setVisible(true);
            }
        });
        background.add(backToHomeButton);

        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SignInPage::new);
    }
}

class LoginFromMainPage extends JFrame {

    public LoginFromMainPage() {
        setTitle("Login Page");
        setSize(1600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Background Image
        JLabel background = new JLabel(new ImageIcon("background1.jpg"));
        background.setBounds(0, 0, getWidth(), getHeight());
        add(background);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.decode("#a3584e")); // Custom color
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField usernameField = new JTextField(20);
        usernameLabel.setBounds(300, 200, 80, 20);
        usernameField.setBounds(400, 200, 150, 20);
        background.add(usernameLabel);
        background.add(usernameField);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.decode("#a3584e")); // Custom color
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JPasswordField passwordField = new JPasswordField(20);
        passwordLabel.setBounds(300, 240, 80, 20);
        passwordField.setBounds(400, 240, 150, 20);
        background.add(passwordLabel);
        background.add(passwordField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(350, 280, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if both fields are filled
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Check credentials
                    boolean userFound = checkCredentials(username, password);
                    if (userFound) {
                        // Check if user details exist
                        if (checkUserDetailsExist(username)) {
                            // Show planned wedding page
                            PlannedWeddingPage plannedWeddingPage = new PlannedWeddingPage(username);
                            plannedWeddingPage.setVisible(true);
                        } else {
                            // Show user details input page
                            UserDetailsPage userDetailsPage = new UserDetailsPage(username);
                            userDetailsPage.setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(LoginFromMainPage.this, "Invalid User ID or Password! Try again or Create new User or Contact 9877677767 for help!");
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFromMainPage.this, "Username and password are required!");
                }
            }
        });
        background.add(submitButton);

        // Back to Home button
        JButton backToHomeButton = new JButton("Back to Home");
        backToHomeButton.setBounds(500, 280, 150, 30);
        backToHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginFromMainPage.this.dispose(); // Close the Login page
                new WeddingPlanner().setVisible(true);
            }
        });
        background.add(backToHomeButton);

        setLayout(null);
        setVisible(true);
    }

    private boolean checkCredentials(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(username + ".txt"));
            String savedUsername = reader.readLine();
            String savedPassword = reader.readLine();
            reader.close();
            return username.equals(savedUsername) && password.equals(savedPassword);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkUserDetailsExist(String username) {
        File file = new File(username + "_details.txt");
        return file.exists();
    }
}

class LoginPage extends JFrame {
	
	private boolean checkUserExist(String username) {
        File file = new File(username + ".txt");
        return file.exists();
    }

    public LoginPage() {
        setTitle("Login Page");
        setSize(1600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Background Image
        JLabel background = new JLabel(new ImageIcon("background1.jpg"));
        background.setBounds(0, 0, getWidth(), getHeight());
        add(background);

        // Username label and text field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.decode("#a3584e")); // Custom color
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField usernameField = new JTextField(20);
        usernameLabel.setBounds(300, 200, 80, 20);
        usernameField.setBounds(400, 200, 150, 20);
        background.add(usernameLabel);
        background.add(usernameField);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.decode("#a3584e")); // Custom color
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JPasswordField passwordField = new JPasswordField(20);
               passwordLabel.setBounds(300, 240, 80, 20);
        passwordField.setBounds(400, 240, 150, 20);
        background.add(passwordLabel);
        background.add(passwordField);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(350, 280, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if both fields are filled
                if (!username.isEmpty() && !password.isEmpty()) {
                    // Save the username and password to a file
					if(checkUserExist(username))
						JOptionPane.showMessageDialog(LoginPage.this, "Username already exists!");
					else
					{
						saveCredentials(username, password);
						JOptionPane.showMessageDialog(LoginPage.this, "Credentials saved successfully!");
					}
                } else {
                    JOptionPane.showMessageDialog(LoginPage.this, "Username and password are required!");
                }
            }
        });
        background.add(submitButton);

        // Back to Home button
        JButton backToHomeButton = new JButton("Back to Home");
        backToHomeButton.setBounds(500, 280, 150, 30);
        backToHomeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginPage.this.dispose(); // Close the Login page
                new WeddingPlanner().setVisible(true);
            }
        });
        background.add(backToHomeButton);

        setLayout(null);
        setVisible(true);
    }

    private void saveCredentials(String username, String password) {
        try {
            FileWriter writer = new FileWriter(username + ".txt");
            writer.write(username + "\n");
            writer.write(password + "\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving credentials!");
        }
    }
}

class UserDetailsPage extends JFrame {
    private String username;

    public UserDetailsPage(String username) {
        this.username = username;
        setTitle("User Details Page");
        setSize(1400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Background Image
        JLabel background = new JLabel(new ImageIcon("background.jpg"));
        background.setBounds(0, 0, getWidth(), getHeight());
        add(background);

        // User details labels and text fields
		
		JLabel phoneLabel = new JLabel("Enter Phone Number:");
        phoneLabel.setForeground(Color.decode("#a3584e")); // Custom color
        phoneLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField phoneField = new JTextField();
        phoneLabel.setBounds(100, 100, 200, 20);
        phoneField.setBounds(410, 100, 150, 20);
        background.add(phoneLabel);
        background.add(phoneField);
		
		JLabel emailLabel = new JLabel("Enter Email Address:");
        emailLabel.setForeground(Color.decode("#a3584e")); // Custom color
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField emailField = new JTextField();
        emailLabel.setBounds(100, 130, 200, 20);
        emailField.setBounds(410, 130, 150, 20);
        background.add(emailLabel);
        background.add(emailField);
		
        JLabel nameLabel = new JLabel("Enter Name of Groom:");
        nameLabel.setForeground(Color.decode("#a3584e")); // Custom color
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField nameField = new JTextField();
        nameLabel.setBounds(100, 160, 200, 20);
        nameField.setBounds(410, 160, 150, 20);
        background.add(nameLabel);
        background.add(nameField);

        JLabel brideLabel = new JLabel("Enter Name of Bride:");
        brideLabel.setForeground(Color.decode("#a3584e")); // Custom color
        brideLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField brideField = new JTextField();
        brideLabel.setBounds(100, 190, 200, 20);
        brideField.setBounds(410, 190, 150, 20);
        background.add(brideLabel);
        background.add(brideField);
		
		JLabel addressLabel = new JLabel("Enter Groom's Address:");
        addressLabel.setForeground(Color.decode("#a3584e")); // Custom color
        addressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField addressField = new JTextField();
        addressLabel.setBounds(100, 220, 200, 40);
        addressField.setBounds(410, 220, 150, 40);
        background.add(addressLabel);
        background.add(addressField);
		
		JLabel brideAddressLabel = new JLabel("Enter Bride's Address:");
        brideAddressLabel.setForeground(Color.decode("#a3584e")); // Custom color
        brideAddressLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField brideAddressField = new JTextField();
        brideAddressLabel.setBounds(100, 270, 200, 40);
        brideAddressField.setBounds(410, 270, 150, 40);
        background.add(brideAddressLabel);
        background.add(brideAddressField);

        JLabel dateLabel = new JLabel("Enter Proposed Date of Wedding:");
        dateLabel.setForeground(Color.decode("#a3584e")); // Custom color
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField dateField = new JTextField();
        dateLabel.setBounds(100, 320, 250, 20);
        dateField.setBounds(410, 320, 150, 20);
        background.add(dateLabel);
        background.add(dateField);

        JLabel placeLabel = new JLabel("Enter Proposed Place of Wedding:");
        placeLabel.setForeground(Color.decode("#a3584e")); // Custom color
        placeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField placeField = new JTextField();
        placeLabel.setBounds(100, 350, 250, 20);
        placeField.setBounds(410, 350, 150, 20);
        background.add(placeLabel);
        background.add(placeField);

        JLabel budgetLabel = new JLabel("Enter Your Budget:");
        budgetLabel.setForeground(Color.decode("#a3584e")); // Custom color
        budgetLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField budgetField = new JTextField();
        budgetLabel.setBounds(100, 380, 200, 20);
        budgetField.setBounds(410, 380, 150, 20);
        background.add(budgetLabel);
        background.add(budgetField);

        JLabel attendeesLabel = new JLabel("Enter the Number of Attendees:");
        attendeesLabel.setForeground(Color.decode("#a3584e")); // Custom color
        attendeesLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField attendeesField = new JTextField();
        attendeesLabel.setBounds(100, 410, 280, 20);
        attendeesField.setBounds(410, 410, 150, 20);
        background.add(attendeesLabel);
        background.add(attendeesField);
		
		JLabel specialLabel = new JLabel("Enter any special instructions (if any):");
        specialLabel.setForeground(Color.decode("#a3584e")); // Custom color
        specialLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        JTextField specialField = new JTextField();
        specialLabel.setBounds(100, 440, 280, 20);
        specialField.setBounds(410, 440, 150, 40);
        background.add(specialLabel);
        background.add(specialField);
		
		

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(300, 530, 100, 30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String phone = phoneField.getText();
				String gAddress = addressField.getText();
				String bAddress = brideAddressField.getText();
                String name = nameField.getText();
                String bride = brideField.getText();
                String date = dateField.getText();
                String place = placeField.getText();
                String budget = budgetField.getText();
                String attendees = attendeesField.getText();
				String special = specialField.getText();

                // Save user details
                saveUserDetails(username, name, bride, date, place, budget, attendees, email, phone, gAddress, bAddress, special);
                JOptionPane.showMessageDialog(UserDetailsPage.this, "Details Saved! Your bill will be sent by via email. After payment, the wedding will be planned!");
                UserDetailsPage.this.dispose();
            }
        });
        background.add(submitButton);

        setLayout(null);
        setVisible(true);
    }

    private void saveUserDetails(String username, String name, String bride, String date, String place, String budget, String attendees, String email, String phone, String gAddress, String bAddress, String special) {
        try {
            FileWriter writer = new FileWriter(username + "_details.txt");
			writer.write("Email ID: " + email + "\n\n");
			writer.write("Phone Number: " + phone + "\n\n");
			writer.write("Address of Groom: " + gAddress + "\n\n");
			writer.write("Address of Bride: " + bAddress + "\n\n");
            writer.write("Name of Groom: " + name + "\n\n");
            writer.write("Name of Bride: " + bride + "\n\n");
            writer.write("Proposed Date of Wedding: " + date + "\n\n");
            writer.write("Proposed Place of Wedding: " + place + "\n\n");
            writer.write("Budget: " + budget + "\n\n");
            writer.write("Number of Attendees: " + attendees + "\n\n");
			writer.write("Special Instructions: " + special + "\n");
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user details!");
        }
    }
}

class PlannedWeddingPage extends JFrame {
    private String username;

    public PlannedWeddingPage(String username) {
        this.username = username;
        setTitle("Planned Wedding");
        setSize(1400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window

        // Create a custom JPanel with the background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load and draw the background image
                ImageIcon backgroundImage = new ImageIcon("background.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Use null layout for manual positioning

        // Read user details from file
        String weddingDetails = "<html>";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(username + "_plan.txt"));
            String line;
            int y = 50; // Initial Y position
            while ((line = reader.readLine()) != null) {
                JLabel detailLabel = new JLabel(line);
                detailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                detailLabel.setForeground(Color.decode("#82645e")); // Set text color to #82645e
                detailLabel.setBounds(140, y, 2000, 20); // Manually set bounds
                backgroundPanel.add(detailLabel);
                y += 30; // Increment Y position for next label
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Sorry, your wedding hasn't been planned yet! Check back again soon. For more details contact 9877677767 or visit our website www.whimsicalvows.com\nPlease close any blank window that may open!");
        }
        weddingDetails += "</html>";

        // Set the custom JPanel as the content pane
		new WeddingPlanner().setVisible(true);		
        setContentPane(backgroundPanel);

        setVisible(true);
    }
}