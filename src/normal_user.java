package src;
import java.util.*;
import java.text.SimpleDateFormat;
// NormalUser Class (Inherits User)
class NormalUser extends User {
    public NormalUser(String userID, String name, String role, String email, String citizenshipNumber, String password) {
        super(userID, name, role, email, citizenshipNumber, password);
    }

    // Method to apply for a license
    public void applyForLicense(Scanner scanner, List<Application> applications, List<License> licenses) {
        System.out.println("Enter the license category you want to apply for (A, B, C): ");
        String category = scanner.nextLine();
        System.out.println("Enter your age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (age < 18) {
            System.out.println("You must be at least 18 years old to apply for Category " + category + ".");
            return;
        }

        // Check if the user already has a license in a compatible category
        for (License license : licenses) {
            if (license.getUserID().equals(this.getUserID())) {
                if (license.getCategory().equals("A") && category.equals("B")) {
                    System.out.println("You are eligible for Category B based on your existing Category A license.");
                    break;
                }
            }
        }

        String applicationID = "APP" + (new Random().nextInt(9000) + 1000); // Generate a random applicationID
        applications.add(new Application(applicationID, this.getUserID(), category, "Pending"));
        System.out.println("Application submitted successfully. Your application ID is: " + applicationID);
    }

    // Method to check application status
    public void checkStatus(List<Application> applications) {
        for (Application app : applications) {
            if (app.getUserID().equals(this.getUserID())) {
                System.out.println("Application ID: " + app.getApplicationID() + ", Status: " + app.getStatus());
            }
        }
    }

    // Method to renew license
    public void renewLicense(Scanner scanner, List<License> licenses) {
        System.out.println("Enter your license ID: ");
        String licenseID = scanner.nextLine();
        for (License license : licenses) {
            if (license.getLicenseID().equals(licenseID) && license.getUserID().equals(this.getUserID())) {
                String newExpiryDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis() + 5L * 365 * 24 * 60 * 60 * 1000)); // 5 years validity
                license.setExpiryDate(newExpiryDate);
                System.out.println("License " + licenseID + " renewed successfully. New expiry date: " + newExpiryDate);
                return;
            }
        }
        System.out.println("License not found or you do not have permission to renew it.");
    }
}
