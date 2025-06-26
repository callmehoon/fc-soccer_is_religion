package toyproject.controller.dto;

public class RegisterRequestDto {

    // Only the requested fields
    private String userId;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String address; // This will hold the combined value of zipcode and detailAddress

    // Default constructor (no-args)
    public RegisterRequestDto() {
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}