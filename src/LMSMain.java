package src;
import java.util.*;
// Main Class
public class LMSMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        List<Application> applications = new ArrayList<>();
        List<License> licenses = new ArrayList<>();

        while (true) {
            System.out.println("=== License Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                User newUser = User.register(scanner);
                users.add(newUser);
                System.out.println("Registration successful. Your user ID is: " + newUser.getUserID());
            } else if (choice == 2) {
                User loggedInUser = User.login(scanner, users);
                if (loggedInUser != null) {
                    System.out.println("Login successful. Welcome, " + loggedInUser.getName() + "!");
                    if (loggedInUser.getRole().equals("NormalUser")) {
                        NormalUser normalUser = new NormalUser(loggedInUser.getUserID(), loggedInUser.getName(),
                                loggedInUser.getRole(), loggedInUser.getEmail(), loggedInUser.getCitizenshipNumber(),
                                loggedInUser.getPassword());
                        normalUserMenu(scanner, normalUser, applications, licenses);
                    } else if (loggedInUser.getRole().equals("Admin")) {
                        Admin admin = new Admin(loggedInUser.getUserID(), loggedInUser.getName(),
                                loggedInUser.getRole(), loggedInUser.getEmail(), loggedInUser.getCitizenshipNumber(),
                                loggedInUser.getPassword());
                        adminMenu(scanner, admin, applications, licenses);
                    }
                } else {
                    System.out.println("Invalid citizenship number or password.");
                }
            } else if (choice == 3) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Normal User Menu
    public static void normalUserMenu(Scanner scanner, NormalUser normalUser, List<Application> applications, List<License> licenses) {
        while (true) {
            System.out.println("=== Normal User Dashboard ===");
            System.out.println("1. Apply for License");
            System.out.println("2. Check Application Status");
            System.out.println("3. Renew License");
            System.out.println("4. Logout");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                normalUser.applyForLicense(scanner, applications, licenses);
            } else if (choice == 2) {
                normalUser.checkStatus(applications);
            } else if (choice == 3) {
                normalUser.renewLicense(scanner, licenses);
            } else if (choice == 4) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Admin Menu
    public static void adminMenu(Scanner scanner, Admin admin, List<Application> applications, List<License> licenses) {
        while (true) {
            System.out.println("=== Admin Dashboard ===");
            System.out.println("1. View Pending Applications");
            System.out.println("2. Approve/Reject Applications");
            System.out.println("3. Generate Report");
            System.out.println("4. Logout");
            System.out.println("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                for (Application app : applications) {
                    if (app.getStatus().equals("Pending")) {
                        System.out.println("Application ID: " + app.getApplicationID() + ", User ID: " + app.getUserID() +
                                ", Category: " + app.getRequestedCategory());
                    }
                }
            } else if (choice == 2) {
                System.out.println("Enter the application ID to approve/reject: ");
                String appID = scanner.nextLine();
                Application targetApp = null;
                for (Application app : applications) {
                    if (app.getApplicationID().equals(appID)) {
                        targetApp = app;
                        break;
                    }
                }
                if (targetApp != null) {
                    System.out.println("1. Approve\n2. Reject\nEnter your choice: ");
                    int action = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if (action == 1) {
                        admin.approveApplication(targetApp, licenses);
                    } else if (action == 2) {
                        admin.rejectApplication(targetApp);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Application not found.");
                }
            } else if (choice == 3) {
                admin.generateReport(applications, licenses);
            } else if (choice == 4) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}