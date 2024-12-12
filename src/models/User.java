package models;

public class User {
    private String name;
    private String email;
    private String cnic;
    private String phone;
    private String password;
    private Boolean isRenting;

    public User(String name, String email, String cnic, String phone, String password, Boolean isRenting) {
        this.name = name;
        this.email = email;
        this.cnic = cnic;
        this.phone = phone;
        this.password = password;
        this.isRenting = isRenting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsRenting() {
        return isRenting;
    }

    public void setIsRenting(Boolean isRenting) {
        this.isRenting = isRenting;
    }

}

