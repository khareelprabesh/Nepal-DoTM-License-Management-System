package src;
import java.util.*;
import java.text.SimpleDateFormat;
// Admin Class (Inherits User)
class Admin extends User {
    public Admin(String userID, String name, String role, String email, String citizenshipNumber, String password) {
        super(userID, name, role, email, citizenshipNumber, password);
    }

    // Method to approve an application
    public void approveApplication(Application application, List<License> licenses) {
        application.setStatus("Approved");
        String licenseID = "LIC" + (new Random().nextInt(9000) + 1000); // Generate a random licenseID
        String issueDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        String expiryDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis() + 5L * 365 * 24 * 60 * 60 * 1000)); // 5 years validity
        licenses.add(new License(licenseID, application.getRequestedCategory(), issueDate, expiryDate, application.getUserID()));
        System.out.println("Application " + application.getApplicationID() + " has been approved. License ID: " + licenseID);
    }

    // Method to reject an application
    public void rejectApplication(Application application) {
        application.setStatus("Rejected");
        System.out.println("Application " + application.getApplicationID() + " has been rejected.");
    }

    // Method to generate reports
    public void generateReport(List<Application> applications, List<License> licenses) {
        int approved = 0, pending = 0, rejected = 0;
        for (Application app : applications) {
            if (app.getStatus().equals("Approved")) approved++;
            else if (app.getStatus().equals("Pending")) pending++;
            else if (app.getStatus().equals("Rejected")) rejected++;
        }
        System.out.println("=== Report ===");
        System.out.println("Approved Applications: " + approved);
        System.out.println("Pending Applications: " + pending);
        System.out.println("Rejected Applications: " + rejected);
        System.out.println("Total Licenses Issued: " + licenses.size());
    }
}
