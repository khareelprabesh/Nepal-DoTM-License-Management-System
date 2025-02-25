package src;
// Application Class
class Application {
    private String applicationID;
    private String userID;
    private String requestedCategory;
    private String status;

    public Application(String applicationID, String userID, String requestedCategory, String status) {
        this.applicationID = applicationID;
        this.userID = userID;
        this.requestedCategory = requestedCategory;
        this.status = status;
    }

    // Getters and Setters
    public String getApplicationID() { return applicationID; }
    public String getUserID() { return userID; }
    public String getRequestedCategory() { return requestedCategory; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
