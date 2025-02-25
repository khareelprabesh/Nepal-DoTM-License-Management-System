package src;
import java.util.*;

// User Class
class User {
    private String userID;
    private String name;
    private String role;
    private String email;
    private String citizenshipNumber;
    private String password;

    // Constructor
    public User(String userID, String name, String role, String email, String citizenshipNumber, String password) {
        this.userID = userID;
        this.name = name;
        this.role = role;
        this.email = email;
        this.citizenshipNumber = citizenshipNumber;
        this.password = password;
    }

    // Getters and Setters
    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public String getCitizenshipNumber() { return citizenshipNumber; }
    public String getPassword() { return password; }

    // Method to register a new user
    public static User register(Scanner scanner) {
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your email: ");
        String email = scanner.nextLine();
        System.out.println("Enter your citizenship number: ");
        String citizenshipNumber = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();
        System.out.println("Select your role (1. Normal User, 2. Admin): ");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        String role = (roleChoice == 1) ? "NormalUser" : "Admin";

        String userID = "USER" + (new Random().nextInt(9000) + 1000); // Generate a random userID
        return new User(userID, name, role, email, citizenshipNumber, password);
    }

    // Method to login
    public static User login(Scanner scanner, List<User> users) {
        System.out.println("Enter your citizenship number: ");
        String citizenshipNumber = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getCitizenshipNumber().equals(citizenshipNumber) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null; // Login failed
    }
}
