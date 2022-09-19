package model;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
    private String userName;
    private String password;
    private String AccessLevel;
    private String status;
    public User() {
    }



    public User(String userId, String firstName, String lastName, String email, String contact, String userName, String password, String accessLevel, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contact = contact;
        this.userName = userName;
        this.password = password;
        AccessLevel = accessLevel;
        this.status = status;
    }

    public User(String userId,String firstName, String lastName, String contact, String accessLevel, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = contact;
        AccessLevel = accessLevel;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccessLevel() {
        return AccessLevel;
    }

    public void setAccessLevel(String accessLevel) {
        AccessLevel = accessLevel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", AccessLevel='" + AccessLevel + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
