package src;
// License Class
class License {
    private String licenseID;
    private String category;
    private String issueDate;
    private String expiryDate;
    private String userID;

    public License(String licenseID, String category, String issueDate, String expiryDate, String userID) {
        this.licenseID = licenseID;
        this.category = category;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.userID = userID;
    }

    // Getters and Setters
    public String getLicenseID() { return licenseID; }
    public String getCategory() { return category; }
    public String getIssueDate() { return issueDate; }
    public String getExpiryDate() { return expiryDate; }
    public String getUserID() { return userID; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
}
