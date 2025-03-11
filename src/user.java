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
        String name;
        do {
            System.out.println("Enter your name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) System.out.println("Name cannot be empty!");
        } while (name.isEmpty());
    
        String email;
        do {
            System.out.println("Enter your email: ");
            email = scanner.nextLine().trim();
            if (email.isEmpty()) System.out.println("Email cannot be empty!");
        } while (email.isEmpty());
    
        String citizenshipNumber;
        do {
            System.out.println("Enter your citizenship number: ");
            citizenshipNumber = scanner.nextLine().trim();
            if (citizenshipNumber.isEmpty()) System.out.println("Citizenship number cannot be empty!");
        } while (citizenshipNumber.isEmpty());
    
        String password;
        do {
            System.out.println("Enter your password: ");
            password = scanner.nextLine().trim();
            if (password.isEmpty()) System.out.println("Password cannot be empty!");
        } while (password.isEmpty());
    
        int roleChoice;
        while (true) {
            System.out.println("Select your role (1. Normal User, 2. Admin): ");
            String input = scanner.nextLine();
            try {
                roleChoice = Integer.parseInt(input);
                if (roleChoice == 1 || roleChoice == 2) break;
                else System.out.println("Invalid choice. Enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number.");
            }
        }
        String role = (roleChoice == 1) ? "NormalUser" : "Admin";
    
        String userID = "USER" + (new Random().nextInt(9000) + 1000);
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
